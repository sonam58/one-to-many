package com.jpa.onetomanymapping.repository;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.jpa.onetomanymapping.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, BigInteger> {
	List<Comment> findByTutorialId(BigInteger tutorialId);

	@Transactional
	void deleteByTutorialId(BigInteger tutorialId);

}
