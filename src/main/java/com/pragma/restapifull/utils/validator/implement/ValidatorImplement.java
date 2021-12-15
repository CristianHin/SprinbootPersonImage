package com.pragma.restapifull.utils.validator.implement;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.pragma.restapifull.dto.PersonDTO;
import com.pragma.restapifull.services.interfaces.IPersonService;
import com.pragma.restapifull.utils.exceptions.ApiBadRequest;
import com.pragma.restapifull.utils.exceptions.ApiUnprocessableEntity;
import com.pragma.restapifull.utils.validator.interfaces.IValidator;

@Component
public class ValidatorImplement implements IValidator{

	@Autowired
	IPersonService personService;

	@Override
	public void validatorPerson(PersonDTO person) throws ApiUnprocessableEntity {

		if(person.getName() == null || person.getName().isEmpty() ||person.getName().trim().isEmpty()){
			this.message("El nombre es obligatorio");
		}

		if (person.getName().length() < 3 || person.getName().trim().length() <3)  {
			this.message("El nombre es demasiado corto, debe tener mas de tres caracteres");
		}

		if(person.getLastName() == null || person.getLastName().isEmpty() || person.getLastName().trim().isEmpty()) {
			this.message("El apellido es obligatorio");
		}

		if (person.getLastName().length() < 2 || person.getName().trim().length() <2) {
			this.message("El apellido es demasiado corto, debe tener mas de tres caracteres");
		}

		if(person.getAge() == null) {
			this.message("Esta persona no cumple años?");
		}

		if(person.getAge() <= 0) {
			this.message( "cumple años en reversa");
		}
		
		person.setName(person.getName().trim());
		person.setLastName(person.getLastName().trim());

	}

	@Override
	public void validatorIdPersons(List<PersonDTO> persons) throws ApiUnprocessableEntity {
		for (PersonDTO person : persons) {
			if(person.getName() == null || person.getName().isEmpty()) {
				this.message("El nombre es obligatorio");
			}

			if (person.getName().length() < 3) {
				this.message("El nombre de " + person.getName() + " es demasiado corto, debe tener mas de tres caracteres");
			}

			if(person.getLastName() == null || person.getName().isEmpty()) {
				this.message("El apellido es obligatorio");
			}

			if (person.getLastName().length() < 2) {
				this.message("El apellido de " + person.getLastName() + " es demasiado corto, debe tener mas de tres caracteres");
			}

			if(person.getAge() == null) {
				this.message(person.getName() + " No cumple años?");
			}

			if(person.getAge() <= 0) {
				this.message("La peresona " + person.getName() + " cumple años en reversa");
			}
		}

	}

	@Override
	public void validatorImage(MultipartFile multipartFile) throws IOException, ApiBadRequest {
		BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
		if(bi == null){
			this.messageBadRequest("No es una imagén");
		}
	}

	@Override
	public void validatorIdPerson(int id) throws ApiBadRequest {
		try {
			personService.findByPersonId(id);
		}catch(Exception e){
			this.messageBadRequest("El usuasrio no existe");
		}

	}

	private void message(String message) throws ApiUnprocessableEntity {
		throw new ApiUnprocessableEntity(message);
	}

	private void messageBadRequest(String message) throws ApiBadRequest {
		throw new ApiBadRequest(message);
	}






}
