package com.evoila.usingservices;

import com.evoila.usingservices.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, String> {
    //List<Person> findByLastAndFirstName(String lastName, String firstName);
}
