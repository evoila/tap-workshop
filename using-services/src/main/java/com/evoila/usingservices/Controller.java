package com.evoila.usingservices;


import com.evoila.usingservices.model.Person;
import com.evoila.usingservices.service.PersonService;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class Controller {

    @Autowired
    private PersonService personService;

    @PostMapping("/persons")
    public Person addPerson(@RequestBody Person person) {
        return personService.insertPerson(person);
    }

}
