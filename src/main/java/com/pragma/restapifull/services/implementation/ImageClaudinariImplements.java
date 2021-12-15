package com.pragma.restapifull.services.implementation;

import com.pragma.restapifull.dto.ImageDTO;
import com.pragma.restapifull.dto.PersonDTO;
import com.pragma.restapifull.services.interfaces.ICloudinaryService;
import com.pragma.restapifull.services.interfaces.IImageClaudinary;
import com.pragma.restapifull.services.interfaces.IImagenService;
import com.pragma.restapifull.services.interfaces.IPersonService;
import com.pragma.restapifull.utils.exceptions.ApiBadRequest;
import com.pragma.restapifull.utils.validator.interfaces.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Component
public class ImageClaudinariImplements implements IImageClaudinary {

    @Autowired
    ICloudinaryService cloudinaryService;

    @Autowired
    IImagenService imagenService;

    @Autowired
    IValidator validator;

    @Autowired
    IPersonService personService;

    @Override
    public boolean uploadImageCloudinary(MultipartFile multipartFile, int id) throws ApiBadRequest, IOException {

        try {
            PersonDTO person = this.personService.findByPersonId(id);
            this.validImageClaudinary(person);
            Map<?, ?> result = cloudinaryService.upload(multipartFile);
            ImageDTO imageDTO = new ImageDTO(id, (String) result.get("original_filename"), (String) result.get("url"),
                    (String) result.get("public_id"));
            person.setImage(imageDTO.getImageUrl());
            this.personService.save(person);
            //Si la la image tiene un usuario asignado, la cambiamos en mongo
            if (this.validImageMongo(id, imageDTO)) {
                return true;
            } else {
                imagenService.saveIamge(imageDTO);
                return true;

            }
        }catch (Exception e){
            e.getMessage();
            return false;
        }
    }

    @Override
    public boolean deleteImageCloudinary(int id) throws ApiBadRequest, IOException {
        if (imagenService.finByIdUser(id) != null) {
            ImageDTO imageDTO = imagenService.finByIdUser(id);
            cloudinaryService.delete(imageDTO.getImageId());
            imagenService.deleteImage(imageDTO.getId());
            PersonDTO person = personService.findByPersonId(id);
            person.setImage(null);
            personService.save(person);
            return true;
        }
        return false;
    }

    public void validImageClaudinary(PersonDTO person) throws IOException {
        if (person.getImage() != null) {

            //Obtenemos la url de la imágen para pasarla a claudinary y eliminarla
            ImageDTO image = imagenService.findByImageUrl(person.getImage());
            cloudinaryService.delete(image.getImageId());
        }
    }

    public boolean validImageMongo(int id, ImageDTO imageDTO){
        if (imagenService.finByIdUser(id) != null) {
            ImageDTO imageDB = imagenService.finByIdUser(id);
            imageDB.setImageId(imageDTO.getImageId());
            imageDB.setImageUrl(imageDTO.getImageUrl());
            imageDB.setName(imageDTO.getName());
            imagenService.saveIamge(imageDB);
            return true;
        }else{
            return false;
        }
    }

}
