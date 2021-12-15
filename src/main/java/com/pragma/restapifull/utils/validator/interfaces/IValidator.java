package com.pragma.restapifull.utils.validator.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pragma.restapifull.dto.PersonDTO;
import com.pragma.restapifull.utils.exceptions.ApiBadRequest;
import com.pragma.restapifull.utils.exceptions.ApiUnprocessableEntity;

@Service
public interface IValidator {

	void validatorPerson (PersonDTO person) throws ApiUnprocessableEntity;
	void validatorIdPerson (int id) throws ApiBadRequest;
	void validatorIdPersons (List<PersonDTO> persons) throws ApiUnprocessableEntity;
	void validatorImage(MultipartFile multipartFile) throws IOException, ApiBadRequest;
}
