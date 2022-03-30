package com.jpa.onetomanymapping.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.onetomanymapping.dto.CommentDTO;
import com.jpa.onetomanymapping.dto.TutorialDTO;
import com.jpa.onetomanymapping.mapper.CommentMapper;
import com.jpa.onetomanymapping.mapper.TutorialMapper;
import com.jpa.onetomanymapping.model.Comment;
import com.jpa.onetomanymapping.model.Tutorial;
import com.jpa.onetomanymapping.repository.CommentRepository;
import com.jpa.onetomanymapping.repository.TutorialRepository;

@Service
public class TutorialCommentService {

	private final TutorialMapper tutorialMapper;
	private final TutorialRepository tutorialRepository;
	private final CommentMapper commentMapper;
	private final CommentRepository commentRepository;

	@Autowired
	public TutorialCommentService(TutorialMapper tutorialMapper, TutorialRepository tutorialRepositoty,
			CommentMapper commentMapper, CommentRepository commentRepository) {
		this.tutorialMapper = tutorialMapper;
		this.tutorialRepository = tutorialRepositoty;
		this.commentMapper = commentMapper;
		this.commentRepository = commentRepository;
	}

	public void saveTutorial(TutorialDTO tutorialDTO) {
		Tutorial tutorial = tutorialMapper.dtoToModel(tutorialDTO);
		tutorial = tutorialRepository.save(tutorial);
		System.out.println("Tutorial_id : " + tutorial.getId());
	}

	public List<TutorialDTO> getTutorialList() {
		return tutorialMapper.modelToTutorialList((List<Tutorial>) tutorialRepository.findAll());
	}

	public TutorialDTO getTutorial(BigInteger tutorialId) {
		Optional<Tutorial> tutorialOptional = tutorialRepository.findById(tutorialId);
		Tutorial tutorial = tutorialOptional.get();
		return tutorialMapper.modelToDTO(tutorial);

	}

	public void updateTutorial(BigInteger tutorialId, TutorialDTO tutorialDTO) {
		Optional<Tutorial> optionalTutorial = tutorialRepository.findById(tutorialId);
		if (optionalTutorial.isPresent()) {
			tutorialMapper.updateTutorial(tutorialDTO, optionalTutorial.get());
			tutorialRepository.save(optionalTutorial.get());
		} else {
			System.out.println("Exception will be thrown");
		}
	}

	public void patchTutorial(BigInteger tutorialId, Map<String, String> updateTutorial) {
		Optional<Tutorial> tutorialOptional = tutorialRepository.findById(tutorialId);
		if (updateTutorial.containsKey("published")) {
			Tutorial tutorialPatch = tutorialOptional.get();
			if (updateTutorial.get("published").equalsIgnoreCase("true")) {
				tutorialPatch.setPublished(true);
			} else if (updateTutorial.get("published").equalsIgnoreCase("false")) {
				tutorialPatch.setPublished(false);
			}
			tutorialRepository.save(tutorialPatch);
		} else {
			System.out.print("Exception thrown");
		}
	}

	public void deleteTutorialCommentFirst(BigInteger tutorialId) {
		Optional<List<Comment>> commentListOptional = Optional.of(commentRepository.findByTutorialId(tutorialId));
		Optional<Tutorial> tutorialOptional = tutorialRepository.findById(tutorialId);
		if (commentListOptional.isPresent()) {
			commentRepository.deleteByTutorialId(tutorialId);
			tutorialRepository.delete(tutorialOptional.get());
		} else {
			tutorialRepository.delete(tutorialOptional.get());
		}

	}
	
	

//---- --------------------------------Comment---------------------------------------- -----//
	
	

	public void saveComment(BigInteger tutorialId, CommentDTO commentDTO) {
		Optional<Tutorial> tutorialOptional = tutorialRepository.findById(tutorialId);
		Comment comment = commentMapper.dtoToModel(commentDTO);
		comment.setTutorial(tutorialOptional.get());
		commentRepository.save(comment);
	}

	public List<CommentDTO> getCommentList(BigInteger tutorialId) {
		List<CommentDTO> commentdto = commentMapper.modelToCommentList(commentRepository.findByTutorialId(tutorialId));
		return commentdto;
	}

	public CommentDTO getComment(BigInteger tutorialId, BigInteger commentId) {
		Optional<Tutorial> tutorialOptional = tutorialRepository.findById(tutorialId);
		Optional<Comment> commentOptional = commentRepository.findById(commentId);
		if (tutorialOptional.isPresent() && commentOptional.isPresent()) {
			Comment comment = commentOptional.get();
			return commentMapper.modelToDTO(comment);

		} else {
			return null;
		}

	}

	public void updateComment(BigInteger tutorialId, BigInteger commentId, CommentDTO commentDTO) {
		Optional<Tutorial> tutorialOptional = tutorialRepository.findById(tutorialId);
		Optional<Comment> commentOptional = commentRepository.findById(commentId);

		if (tutorialOptional.isPresent() && commentOptional.isPresent()) {
			commentMapper.updateComment(commentDTO, commentOptional.get());
			commentRepository.save(commentOptional.get());

		} else {
			System.out.println("Exception to be thrown");
		}

	}

	public void deleteComment(BigInteger tutorialId, BigInteger commentId) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		commentRepository.delete(comment.get());

	}

	public void deleteCommentByTutorialId(BigInteger tutorialId) {
		Optional<List<Comment>> commentListOptional = Optional.of(commentRepository.findByTutorialId(tutorialId));

		if (commentListOptional.isPresent()) {
			commentRepository.deleteByTutorialId(tutorialId);
		}
	}

}
