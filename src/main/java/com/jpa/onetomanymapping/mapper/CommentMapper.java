package com.jpa.onetomanymapping.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.jpa.onetomanymapping.dto.CommentDTO;
import com.jpa.onetomanymapping.model.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

	Comment dtoToModel(CommentDTO comment);

	List<CommentDTO> modelToCommentList(List<Comment> comments);

	CommentDTO modelToDTO(Comment comment);

	void updateComment(CommentDTO commentDTO, @MappingTarget Comment comment);

}