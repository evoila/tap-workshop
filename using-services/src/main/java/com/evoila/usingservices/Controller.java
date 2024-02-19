package com.evoila.usingservices;


import com.evoila.usingservices.model.Person;
import com.evoila.usingservices.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import com.nebhale.bindings.Binding;
import com.nebhale.bindings.Bindings;

import java.util.Collections;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
public class Controller {

    @Autowired
    private PersonService personService;

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        return personService.insertPerson(person);
    }

    @GetMapping("/persons")
    public String findAllPersons() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(personService.findAllPersons());
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @GetMapping("/person")
    public String findPerson(@RequestParam String lastName, @RequestParam String firstName) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(personService.getPersonByLastNameAndFirstName(lastName, firstName));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @DeleteMapping("/person")
    public String deltePerson(@RequestParam String lastName, String firstName) {
        personService.deletePersonByLastNameAndFirstName(lastName, firstName);
        return "Person deleted successfully";
    }
}
