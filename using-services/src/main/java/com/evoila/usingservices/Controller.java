package com.evoila.usingservices;


import com.evoila.usingservices.model.Person;
import com.evoila.usingservices.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    public String deletePerson(@RequestParam String lastName, String firstName) {
        personService.deletePersonByLastNameAndFirstName(lastName, firstName);
        return "Person deleted successfully";
    }
}
