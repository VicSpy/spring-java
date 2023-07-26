package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {}
