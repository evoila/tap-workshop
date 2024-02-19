package com.evoila.usingservices.service;

import com.evoila.usingservices.PersonRepository;
import com.evoila.usingservices.model.Person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person insertPerson(Person person) {
        return personRepository.save(person);
    }

    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    public Person getPersonByLastNameAndFirstName(String lastName, String firstName) {
        return personRepository.findByLastNameAndFirstName(lastName, firstName).get(0);
    }

    public void deletePersonByLastNameAndFirstName(String lastName, String firstName) {
        personRepository.deleteByLastNameAndFirstName(lastName, firstName);
    }

}
