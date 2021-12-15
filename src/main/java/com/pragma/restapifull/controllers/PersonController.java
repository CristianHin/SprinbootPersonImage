package com.pragma.restapifull.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pragma.restapifull.dto.PersonDTO;
import com.pragma.restapifull.services.interfaces.IPersonService;
import com.pragma.restapifull.utils.exceptions.ApiBadRequest;
import com.pragma.restapifull.utils.exceptions.ApiUnprocessableEntity;
import com.pragma.restapifull.utils.validator.interfaces.IValidator;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/persons")
public class PersonController {

	@Autowired
	private IPersonService personService;

	@Autowired
	private IValidator Validator;

	@GetMapping(value= {"","/"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PersonDTO>> getPersons(){

		return ResponseEntity.ok(personService.findAll());
	}

	@GetMapping(value=("/search/{register}/{name}"),  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getPersonByName(@PathVariable("register") String register, @PathVariable("name") String name){
			switch (register) {
			case "lastname":
				return ResponseEntity.ok(personService.findByLastName(name));
			case "name":
				return ResponseEntity.ok(personService.findByName(name));

			default:
				return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"El path no existe\"}");
			}
	}



	@GetMapping(value=("/search/age/{age}"),  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getByAge(@PathVariable("age") Integer age){

			return ResponseEntity.ok(personService.findByAge(age));
	}

	@GetMapping(value=("/search/id/{id}"),  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getById(@PathVariable("id") Integer id) throws ApiBadRequest {

			Validator.validatorIdPerson(id);
			try {
				return ResponseEntity.ok(personService.findByPersonId(id));
			}catch(Exception e){
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\":\"Comuníquese con el administrador\"}");
			}

		}


	@PostMapping(value=("/save"),consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> savePerson( @RequestBody PersonDTO person) throws ApiUnprocessableEntity {


			Validator.validatorPerson(person);
			try {
				personService.save(person);
				return  ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Se creó correctamente\"}");
			}catch(Exception e){
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\":\"Comuníquese con el administrador\"}");
			}

	}

	@PostMapping(value=("/save/all"),consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> savePersonAll( @RequestBody List<PersonDTO> person) throws ApiUnprocessableEntity {


			Validator.validatorIdPersons(person);
			try {
				personService.saveAll(person);
				return  ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Se creó correctamente\"}");
			}catch(Exception e){
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\":\"Comuníquese con el administrador\"}");
			}

	}

	@DeleteMapping(value=("/delete/{id}"), produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deletePerson( @PathVariable("id") Integer id ) throws ApiBadRequest {

		Validator.validatorIdPerson(id);
		try {
			personService.deleteById(id);
			return  ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Se eliminó correctamente\"}");
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\":\"Comuníquese con el administrador\"}");
		}

	}

	@PutMapping(value=("/update/{id}"), consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<String> updatePerson( @PathVariable("id") Integer id, @RequestBody PersonDTO person ) throws ApiBadRequest, ApiUnprocessableEntity{
		Validator.validatorIdPerson(id);
		PersonDTO personDTO = personService.updatePerson(id,person);
		Validator.validatorPerson(personDTO);
		try {
			personService.save(personDTO);
			return  ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Se actualizó correctamente\"}");
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\":\"Comuníquese con el administrador\"}");
		}
	}
}
