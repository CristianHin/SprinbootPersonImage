package com.pragma.restapifull.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.pragma.restapifull.helpers.IMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pragma.restapifull.dto.ImageDTO;
import com.pragma.restapifull.helpers.Mapping;
import com.pragma.restapifull.models.Image;
import com.pragma.restapifull.repositories.ImageDao;
import com.pragma.restapifull.services.interfaces.IImagenService;

@Component
public class ImageImplement implements IImagenService {

    @Autowired
    ImageDao imageDao;

    @Autowired
    private IMapping iMapping;

    @Override
    public List<ImageDTO> findAll() {
        Iterable<Image> images = this.imageDao.findAll();
        List<ImageDTO> imagesDTO = new ArrayList<>();

        for(Image image : images) {
            ImageDTO imageDTO = iMapping.modelMapper().map(image, ImageDTO.class);
            imagesDTO.add(imageDTO);
        }
        return imagesDTO;
    }

    @Override
    public ImageDTO findById(String id) {
        Optional<Image> image = this.imageDao.findById(id);
        return iMapping.modelMapper().map(image, ImageDTO.class);
    }

    @Override
    public boolean saveIamge(ImageDTO imageDTO) {
        Image image = iMapping.modelMapper().map(imageDTO, Image.class);
        try{
           this.imageDao.save(image);
           return true;
        }catch (Exception e){
            return false;
        }
    }




    @Override
    public boolean deleteImage(String id) {
        try {
            this.imageDao.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

	@Override
	public ImageDTO findByImageUrl(String id) {

		try {
			Image image = imageDao.findByImageUrl(id);
			return iMapping.modelMapper().map(image, ImageDTO.class);
        }catch (Exception e){
            return null;
        }
	}

	@Override
	public ImageDTO finByIdUser(int id) {

		try {
			Image image = imageDao.findByIdUser(id);
			return iMapping.modelMapper().map(image, ImageDTO.class);
        }catch (Exception e){
            return null;
        }
	}
}
