package com.pragma.restapifull.services.implementation;

import com.pragma.restapifull.dto.PersonDTO;
import com.pragma.restapifull.helpers.IMapping;
import com.pragma.restapifull.helpers.Mapping;
import com.pragma.restapifull.models.Person;
import com.pragma.restapifull.repositories.PersonDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;


class PersonImplementTest {

    @InjectMocks
    private  PersonImplement personImplement;

    @Mock
    private IMapping iMapping;

    @Mock
    private PersonDao personDao;


    @Autowired
    private Person person;
    @Autowired
    private PersonDTO personDTO;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);



        person = new Person();
        person.setId(1);
        person.setName("Cristian");
        person.setLastName("Calderon");
        person.setAge(15);
        person.setCity("Medllín");


        personDTO = new PersonDTO();
        personDTO.setId(1);
        personDTO.setName("Cristian");
        personDTO.setLastName("Calderon");
        personDTO.setAge(15);
        personDTO.setCity("Medllín");



    }



    @Test
    void findAll() {
        when(personImplement.findAll()).thenReturn(Arrays.asList(personDTO));
        assertNotNull(personImplement.findAll());

    }

    @Test
    void findByName() {

        when(personImplement.findByName("c")).thenReturn(Arrays.asList( personDTO));
        assertNotNull(personImplement.findByName("C"));
        assertEquals( new ArrayList<PersonDTO>(),personImplement.findByName("K"));
    }

    @Test
    void findByLastName() {
        when(personImplement.findByLastName("c")).thenReturn(Arrays.asList( personDTO));
        this.setUp();
        assertNotNull(personImplement.findByLastName("c"));
        assertEquals(personImplement.findByLastName("k"), new ArrayList<PersonDTO>());
    }

    @Test
    void findByAge() {
        this.setUp();

        when(personImplement.findByAge(1)).thenReturn(Arrays.asList( personDTO));

        assertNotNull(personImplement.findByAge(1));
        assertEquals(personImplement.findByAge(30), new ArrayList<PersonDTO>());
    }

    @Test
    void findByPersonId() {
    }

    @Test
    void save() {

        when(personImplement.save(personDTO)).thenReturn(true);
    }

    @Test
    void saveAll() {
    }

    @Test
    void deleteById() {
        this.setUp();
        when(personImplement.deleteById(1)).thenReturn(true);
        assertTrue(personImplement.deleteById(1));
        assertFalse(personImplement.deleteById(10));

    }

    @Test
    void convertPerson() {
    }

    @Test
    void updatePerson() {
    }
}