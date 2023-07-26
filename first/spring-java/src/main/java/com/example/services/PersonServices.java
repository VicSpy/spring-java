package com.example.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.excpetions.ResourceNotFoundExcpetion;
import com.example.models.Person;
import com.example.repositories.PersonRepository;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll() {
        logger.info("List");

        return repository.findAll();
    }

    public Person findById(Long id) {
        logger.info("Finding one person");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcpetion("Not found"));
    }

    public Person create(Person person) {
        logger.info("Create");

        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("Update");

        var record = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundExcpetion("Not found"));

        record.setFirstName(person.getFirstName());
        record.setLastName(person.getLastName());
        record.setAddress(person.getAddress());
        record.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id) {
        logger.info("Delete");

        var record = repository.findById(id).orElseThrow(() -> new ResourceNotFoundExcpetion("Not found"));

        repository.delete(record);
    }

}
