package com.pragma.restapifull.repositories;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pragma.restapifull.models.Person;

@Repository
public interface PersonDao extends CrudRepository<Person, Integer>, PersonCustomDao {


}
