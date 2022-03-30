package com.jpa.onetomanymapping.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.jpa.onetomanymapping.dto.TutorialDTO;
import com.jpa.onetomanymapping.model.Tutorial;

@Mapper(componentModel = "spring")
public interface TutorialMapper {

	Tutorial dtoToModel(TutorialDTO tutorialDTO);

	List<TutorialDTO> modelToTutorialList(List<Tutorial> tutorial);

	TutorialDTO modelToDTO(Tutorial tutorial);

	void updateTutorial(TutorialDTO tutorialDTO, @MappingTarget Tutorial tutorial);

}
