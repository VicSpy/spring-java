package com.example.unittests.mapper.mockito.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.data.vo.v1.PersonVO;
import com.example.excpetions.ObjectIsNullExcpetion;
import com.example.models.Person;
import com.example.repositories.PersonRepository;
import com.example.services.PersonServices;
import com.example.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonServices service;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() throws Exception {
        List<Person> list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);

        var result = service.findAll();
        assertNotNull(result);
        assertEquals(14, result.size());

        var person1 = result.get(1);
        assertNotNull(person1);
        assertNotNull(person1.getKey());
        assertNotNull(person1.getLinks());

        assertTrue(person1.toString().contains("links: [</person/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", person1.getAddress());
        assertEquals("First Name Test1", person1.getFirstName());
        assertEquals("Last Name Test1", person1.getLastName());
        assertEquals("Female", person1.getGender());
  
        var person7 = result.get(7);
        assertNotNull(person7);
        assertNotNull(person7.getKey());
        assertNotNull(person7.getLinks());

        assertTrue(person7.toString().contains("links: [</person/7>;rel=\"self\"]"));
        assertEquals("Addres Test7", person7.getAddress());
        assertEquals("First Name Test7", person7.getFirstName());
        assertEquals("Last Name Test7", person7.getLastName());
        assertEquals("Female", person7.getGender());
    
        var person10 = result.get(10);
        assertNotNull(person10);
        assertNotNull(person10.getKey());
        assertNotNull(person10.getLinks());

        assertTrue(person10.toString().contains("links: [</person/10>;rel=\"self\"]"));
        assertEquals("Addres Test10", person10.getAddress());
        assertEquals("First Name Test10", person10.getFirstName());
        assertEquals("Last Name Test10", person10.getLastName());
        assertEquals("Male", person10.getGender());
    }

    @Test
    void testFindById() throws Exception {
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    // TODO
    // @Test
    // void testCreate() throws Exception {
    // Person person = input.mockEntity(1);

    // Person persisted = person;
    // persisted.setId(1L);

    // PersonVO vo = input.mockVO(1);
    // vo.setKey(1L);

    // lenient().when(repository.save(person)).thenReturn(persisted);

    // var result = service.create(vo);

    // assertNotNull(result);
    // assertNotNull(result.getKey());
    // assertNotNull(result.getLinks());

    // assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
    // assertEquals("Addres Test1", result.getAddress());
    // assertEquals("First Name Test1", result.getFirstName());
    // assertEquals("Last Name Test1", result.getLastName());
    // assertEquals("Female", result.getGender());
    // }

    @Test
    void testCreateWithNull() throws Exception {
        Exception exception = assertThrows(ObjectIsNullExcpetion.class, () -> {
            service.create(null);
        });

        String expectedMessage = "It is not allowed to persisted a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdate() throws Exception {
        Person person = input.mockEntity(1);
        person.setId(1L);

        Person persisted = person;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        when(repository.save(person)).thenReturn(persisted);

        var result = service.update(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testUpdateWithNull() throws Exception {
        Exception exception = assertThrows(ObjectIsNullExcpetion.class, () -> {
            service.update(null);
        });

        String expectedMessage = "It is not allowed to persisted a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testDelete() throws Exception {
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        service.delete(1L);
    }
}
