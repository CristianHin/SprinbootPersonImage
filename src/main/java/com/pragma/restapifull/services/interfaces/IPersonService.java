package com.pragma.restapifull.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pragma.restapifull.dto.PersonDTO;
import com.pragma.restapifull.models.Person;


@Service("Person Servive")
public interface IPersonService {

	List<PersonDTO> findAll();

	List<PersonDTO> findByName(String name);

	List<PersonDTO> findByLastName(String lastName);

	List<PersonDTO> findByAge(Integer age);

	PersonDTO findByPersonId(int id);

	boolean save(PersonDTO person);

	boolean saveAll(List<PersonDTO> persons);

	boolean deleteById(int id);

	PersonDTO updatePerson(int id, PersonDTO person);

	Person convertPerson(PersonDTO person);

	List<PersonDTO> convertListPerson(List<Person> persons);

	PersonDTO convertPersonDTO( Person person);

}
