package com.example.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import com.example.controllers.PersonController;
import com.example.data.vo.v1.PersonVO;
import com.example.data.vo.v2.PersonVOV2;
import com.example.excpetions.ObjectIsNullExcpetion;
import com.example.excpetions.ResourceNotFoundExcpetion;
import com.example.mapper.DozerMapper;
import com.example.mapper.custom.PersonMapper;
import com.example.models.Person;
import com.example.repositories.PersonRepository;
import com.github.dozermapper.core.DozerConverter;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

    public List<PersonVO> findAll() {
        logger.info("List");

        var people = DozerMapper.parseObjects(repository.findAll(), PersonVO.class);
        people.stream().forEach(p -> {
            try {
                p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return people;
    }

    public PersonVO findById(Long id) throws Exception {
        logger.info("Show");

        var object = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcpetion("Not found"));

        var vo = DozerMapper.parseObject(object, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO person) throws Exception {
        if (person == null)
            throw new ObjectIsNullExcpetion();

        var object = DozerMapper.parseObject(person, Person.class);

        var vo = DozerMapper.parseObject(repository.save(object), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO person) throws Exception {
        if (person == null)
            throw new ObjectIsNullExcpetion();

        var object = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundExcpetion("Not found"));

        object.setFirstName(person.getFirstName());
        object.setLastName(person.getLastName());
        object.setAddress(person.getAddress());
        object.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(object), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public void delete(Long id) {
        logger.info("Delete");

        var record = repository.findById(id).orElseThrow(() -> new ResourceNotFoundExcpetion("Not found"));

        repository.delete(record);
    }

}
