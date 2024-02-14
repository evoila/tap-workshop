package com.evoila.usingservices.service;

import com.evoila.usingservices.PersonRepository;
import com.evoila.usingservices.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person insertPerson(Person person) {
        return personRepository.save(person);
    }
    /*
    public Person getPersonByLastAndFirstName(String lastName, String firstName) {
        return personRepository.findByLastAndFirstName(lastName, firstName).get(0);
    }
    */
}
