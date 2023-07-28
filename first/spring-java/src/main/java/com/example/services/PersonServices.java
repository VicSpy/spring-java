package com.example.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.data.vo.v1.PersonVO;
import com.example.data.vo.v2.PersonVOV2;
import com.example.excpetions.ResourceNotFoundExcpetion;
import com.example.mapper.DozerMapper;
import com.example.mapper.custom.PersonMapper;
import com.example.models.Person;
import com.example.repositories.PersonRepository;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

    public List<PersonVO> findAll() {
        logger.info("List");

        return DozerMapper.parseObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {
        logger.info("Show");

        var object = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcpetion("Not found"));

        return DozerMapper.parseObject(object, PersonVO.class);
    }

    public PersonVO create(PersonVO person) {
        logger.info("Create");

        var object = DozerMapper.parseObject(person, Person.class);

        var vo = DozerMapper.parseObject(repository.save(object), PersonVO.class);

        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 person) {
        logger.info("Create");

        var object = mapper.convertToRecord(person);

        var vo = mapper.convertToVo(repository.save(object));

        return vo;
    }

    public PersonVO update(PersonVO person) {
        logger.info("Update");

        var object = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundExcpetion("Not found"));

        object.setFirstName(person.getFirstName());
        object.setLastName(person.getLastName());
        object.setAddress(person.getAddress());
        object.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(object), PersonVO.class);

        return vo;
    }

    public void delete(Long id) {
        logger.info("Delete");

        var record = repository.findById(id).orElseThrow(() -> new ResourceNotFoundExcpetion("Not found"));

        repository.delete(record);
    }

}
