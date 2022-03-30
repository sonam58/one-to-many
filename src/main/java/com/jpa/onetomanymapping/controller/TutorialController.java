package com.jpa.onetomanymapping.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.onetomanymapping.dto.CommentDTO;
import com.jpa.onetomanymapping.dto.TutorialDTO;
import com.jpa.onetomanymapping.service.TutorialCommentService;

@RestController
public class TutorialController {

	@Autowired

	TutorialCommentService tutorialCommentService;

	@PostMapping(path = "/tutorials")
	public ResponseEntity<?> saveTutorial(@RequestBody TutorialDTO tutorialDTO) {
		tutorialCommentService.saveTutorial(tutorialDTO);
		return ResponseEntity.ok("Saved Tutorial");
	}

	@GetMapping(path = "/tutorials")
	public ResponseEntity<List<TutorialDTO>> getTutorial() {
		List<TutorialDTO> tutorialDTO = tutorialCommentService.getTutorialList();
		return ResponseEntity.ok(tutorialDTO);
	}

	@GetMapping(path = "/tutorials/{tutorialId}")
	public ResponseEntity<?> getTutorial(@PathVariable("tutorialId") BigInteger tutorialId) {
		TutorialDTO tutorialDTO = tutorialCommentService.getTutorial(tutorialId);
		return ResponseEntity.ok(tutorialDTO);
	}

	@PutMapping(path = "/tutorials/{tutorialId}")
	public ResponseEntity<?> updateTutorial(@PathVariable("tutorialId") BigInteger tutorialId,
			@RequestBody TutorialDTO tutorialDTO) {
		tutorialCommentService.updateTutorial(tutorialId, tutorialDTO);
		return ResponseEntity.ok("Entity updated successfully");
	}

	@PatchMapping(path = "/tutorials/{tutorialId}")
	public ResponseEntity<?> patchTutorial(@PathVariable("tutorialId") BigInteger tutorialId,
			@RequestBody Map<String, String> updateTutorial) {
		tutorialCommentService.patchTutorial(tutorialId, updateTutorial);
		return ResponseEntity.ok("Tutorial is patched");
	}

	@DeleteMapping(path = "/tutorials/{tutorialId}")
	public ResponseEntity<?> deleteTutorial(@PathVariable("tutorialId") BigInteger tutorialId) {
		tutorialCommentService.deleteTutorialCommentFirst(tutorialId);
		return ResponseEntity.ok("Tutorial is deleted");
	}
	
	
//	--------------------------------Comment-----------------------------------------//
	
	

	@PostMapping(path = "/tutorials/{tutorialId}/comments")
	public ResponseEntity<?> saveComment(@PathVariable(value = "tutorialId") BigInteger tutorialId,
			@RequestBody CommentDTO commentDTO) {
		tutorialCommentService.saveComment(tutorialId, commentDTO);
		return ResponseEntity.ok("Comment Added");
	}

	@GetMapping(path = "/tutorials/{tutorialId}/comments")
	public ResponseEntity<List<CommentDTO>> getComments(@PathVariable(value = "tutorialId") BigInteger tutorialId) {
		List<CommentDTO> commentDTO = tutorialCommentService.getCommentList(tutorialId);
		return ResponseEntity.ok(commentDTO);
	}

	@GetMapping(path = "/tutorials/{tutorialId}/comments/{commentId}")
	public ResponseEntity<?> getComment(@PathVariable("tutorialId") BigInteger tutorialId,
			@PathVariable("commentId") BigInteger commentId) {
		CommentDTO commentDTO = tutorialCommentService.getComment(tutorialId, commentId);
		return ResponseEntity.ok(commentDTO);
	}

	@PutMapping(path = "/tutorials/{tutorialId}/comments/{commentId}")
	public ResponseEntity<?> updateComment(@PathVariable("tutorialId") BigInteger tutorialId,
			@PathVariable("commentId") BigInteger commentId, @RequestBody CommentDTO commentDTO) {
		tutorialCommentService.updateComment(tutorialId, commentId, commentDTO);
		return ResponseEntity.ok("Entity updated successfully");
	}

	@DeleteMapping(path = "/tutorials/{tutorialId}/comments/{commentId}")
	public ResponseEntity<?> deleteComment(@PathVariable("tutorialId") BigInteger tutorialId,
			@PathVariable("commentId") BigInteger commentId) {
		tutorialCommentService.deleteComment(tutorialId, commentId);
		return ResponseEntity.ok("Comment deleted successfully");
	}

	@DeleteMapping(path = "/tutorials/{tutorialId}/comments")
	public ResponseEntity<?> deleteCommentByTutorialId(@PathVariable("tutorialId") BigInteger tutorialId) {
		tutorialCommentService.deleteCommentByTutorialId(tutorialId);
		return ResponseEntity.ok("Comment Deleted Successfully" + (HttpStatus.OK));
	}

}
