package com.example.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.data.vo.v2.PersonVOV2;
import com.example.models.Person;

@Service
public class PersonMapper {
    
    public PersonVOV2 convertToVo(Person person) {
        PersonVOV2 vo = new PersonVOV2();

        vo.setId(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setAddress(person.getAddress());
        vo.setGender(person.getGender());
        vo.setBirthDay(new Date());

        return vo;
    }

    public Person convertToRecord(PersonVOV2 person) {
        Person record = new Person();

        record.setId(person.getId());
        record.setFirstName(person.getFirstName());
        record.setLastName(person.getLastName());
        record.setAddress(person.getAddress());
        record.setGender(person.getGender());
        // record.setBirthDay(new Date());

        return record;
    }
}
