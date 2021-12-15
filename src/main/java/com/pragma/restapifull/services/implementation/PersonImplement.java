package com.pragma.restapifull.services.implementation;

import java.util.ArrayList;
import java.util.List;

import com.pragma.restapifull.helpers.IMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pragma.restapifull.dto.PersonDTO;
import com.pragma.restapifull.helpers.Mapping;
import com.pragma.restapifull.models.Person;
import com.pragma.restapifull.repositories.PersonDao;
import com.pragma.restapifull.services.interfaces.IPersonService;


@Component("PersonService")
public class PersonImplement implements  IPersonService{


	@Autowired
	private PersonDao personDao;

	@Autowired
	private IMapping iMapping;

	@Override
	public List<PersonDTO> findAll() {
		Iterable<Person> persons = this.personDao.findAll();
		return this.convertListPerson((List<Person>) persons);

	}

	@Override
	public List<PersonDTO> findByName(String name) {
		List<Person> persons = personDao.findByNameContaining(name);
		return this.convertListPerson(persons);
	}


	@Override
	public List<PersonDTO> findByLastName(String lastName) {
		List<Person> persons = personDao.findByLastNameContaining(lastName);
		return this.convertListPerson(persons);
	}

	@Override
	public List<PersonDTO> findByAge(Integer age) {
		List<Person> persons = personDao.findByAgeGreaterThan(age);
		return this.convertListPerson(persons);
	}

	@Override
	public PersonDTO findByPersonId(int id) {
		Person person = personDao.findById(id);
		return iMapping.modelMapper().map(person, PersonDTO.class);
	}

	@Override
	public boolean save(PersonDTO person) {

		Person personSave = iMapping.modelMapper().map(person, Person.class);

		try {
			personDao.save(personSave);
			return true;
		}
		catch (Exception ex) {
			ex.getMessage();
			return false;
		}
	}

	@Override
	public boolean saveAll(List<PersonDTO> persons) {

		for(PersonDTO person : persons) {
			Person personSave = iMapping.modelMapper().map(person, Person.class);
			personDao.save(personSave);
		}

		return true;
	}

	@Override
	public boolean deleteById(int id) {

		try {
			personDao.deleteById(id);
			return true;
		}
		catch (Exception ex) {
			ex.getMessage();
			return false;
		}
	}

	@Override
	public List<PersonDTO> convertListPerson(List<Person> persons){
		List<PersonDTO> personsDTO = new ArrayList<>();
		for(Person person : persons) {
			PersonDTO personDTO = this.convertPersonDTO(person);
			personsDTO.add(personDTO);
		}
		return personsDTO;
	}

	@Override
	public PersonDTO convertPersonDTO( Person person) {
		return iMapping.modelMapper().map(person, PersonDTO.class);
	}

	@Override
	public Person convertPerson( PersonDTO personDTO) {
		return iMapping.modelMapper().map(personDTO, Person.class);
	}

	@Override
	public PersonDTO updatePerson(int id, PersonDTO person) {

		Person personDB = personDao.findById(id);

		if(person.getName() != null ) {
			personDB.setName(person.getName());
		}

		if(person.getLastName() != null ) {
			personDB.setLastName(person.getLastName());
		}

		if(person.getAge() != null ) {
			personDB.setAge(person.getAge());
		}
		if(person.getCity() != null ) {
			personDB.setCity(person.getCity());
		}

		PersonDTO personDTO = iMapping.modelMapper().map(personDB, PersonDTO.class);


		return personDTO;
	}




}
