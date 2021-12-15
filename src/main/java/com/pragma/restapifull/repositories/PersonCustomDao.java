package com.pragma.restapifull.repositories;

import com.pragma.restapifull.models.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Repository
public interface PersonCustomDao {

    Person findById(int id);


    List<Person> findByNameContaining(String name);


    List<Person> findByLastNameContaining(String lastName);


    List<Person> findByAgeGreaterThan(Integer age);
}
