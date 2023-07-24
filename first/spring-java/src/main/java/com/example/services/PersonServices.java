package com.example.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.example.models.Person;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll() {
        logger.info("Finding all people");

        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 4;) {
            Person person = mockPerson(i);
            people.add(person);
        }
        return people;
    }

    public Person findById(String id) {
        logger.info("Finding one person");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Victor");
        person.setLastName("MK");
        person.setAddress("Brasil");
        person.setGender("Male");
        return person;
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Name" + i);
        person.setLastName("Last Name" + i);
        person.setAddress("Brasil" + i);
        person.setGender("Male");
        return person;
    }
}
