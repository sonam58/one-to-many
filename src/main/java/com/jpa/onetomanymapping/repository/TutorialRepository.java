package com.jpa.onetomanymapping.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import com.jpa.onetomanymapping.model.Tutorial;

public interface TutorialRepository extends CrudRepository<Tutorial, BigInteger> {

}
