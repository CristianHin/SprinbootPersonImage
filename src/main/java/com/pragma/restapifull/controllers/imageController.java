package com.pragma.restapifull.controllers;

import java.io.IOException;
import java.util.List;


import com.pragma.restapifull.services.interfaces.IImageClaudinary;
import com.pragma.restapifull.services.interfaces.IImagenService;
import com.pragma.restapifull.utils.exceptions.ApiBadRequest;
import com.pragma.restapifull.utils.validator.interfaces.IValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pragma.restapifull.dto.ImageDTO;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/image")
public class imageController {

	@Autowired
	IImagenService imageService;

	@Autowired
	IImageClaudinary imageCloudinary;
	
	@Autowired
	IValidator validator;

    @GetMapping(value = {"/",""}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ImageDTO>> getIamges(){
        return ResponseEntity.ok( imageService.findAll());
    }


	@PostMapping(value = "/upload/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadImage(@RequestParam MultipartFile multipartFile, @PathVariable int id) throws IOException, ApiBadRequest {
        
		 
        this.validator.validatorImage(multipartFile);
        
        this.validator.validatorIdPerson(id);
		
		if(imageCloudinary.uploadImageCloudinary(multipartFile,id)){
			return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Se subió la imágen correctamente\"}");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\":\"Esto se está quemando\"}");
    }
	
	@DeleteMapping(value = "/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteImage (@PathVariable int id) throws IOException, ApiBadRequest {
		
		this.validator.validatorIdPerson(id);
		if(imageCloudinary.deleteImageCloudinary(id)){
			return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Se borró correctamente\"}");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"Esta persona no tiene imágen\"}");
	}

}


