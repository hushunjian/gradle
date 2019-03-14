package com.hushunjian.gradle.repo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.DocumentEntity;
import com.hushunjian.gradle.enumeration.DocumentTypeEnum;

public interface DocumentRepo extends JpaRepository<DocumentEntity, Long>{

	List<DocumentEntity> findByDocumentType(DocumentTypeEnum documentType);
	
	List<DocumentEntity> findByIdIn(Collection<String> ids);
}
