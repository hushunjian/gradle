package com.hushunjian.gradle.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.DocumentTypeRelationEntity;

public interface DocumentRelationRepo extends JpaRepository<DocumentTypeRelationEntity, Long>{
	
	List<DocumentTypeRelationEntity> findByChosenDocIdAndParentProjectIdAndTypeAndTemplateAndFirstLevel(String chosenDocId, String parentProjectId, String type, String template, String firstLevel);
	
	List<DocumentTypeRelationEntity> findByChosenDocIdAndParentProjectIdAndTypeAndTemplateAndParentAndFirstLevel(String chosenDocId, String parentProjectId, String type, String template, String parent, String firstLevel);
	
	DocumentTypeRelationEntity findByChosenDocIdAndSubordinateDocIdAndFirstLevel(String chosenDocId, String subordinateDocId, String firstLevel);
}
