package com.hushunjian.gradle.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hushunjian.gradle.copier.DocumentTypeMapper;
import com.hushunjian.gradle.dto.DocumentDTO;
import com.hushunjian.gradle.entity.DocumentEntity;
import com.hushunjian.gradle.entity.DocumentTypeRelationEntity;
import com.hushunjian.gradle.enumeration.DocumentTypeEnum;
import com.hushunjian.gradle.repo.DocumentRelationRepo;
import com.hushunjian.gradle.repo.DocumentRepo;
import com.hushunjian.gradle.request.AddDocumentRequest;
import com.hushunjian.gradle.request.AddRelationRequest;
import com.hushunjian.gradle.request.QueryRelationRequest;

@Service
@Transactional
public class DocumentService {
	@Autowired
	private DocumentRepo documentRepo;
	@Autowired
	private DocumentRelationRepo documentRelationRepo;

	/**
	 * 添加文档
	 * 
	 * @param addDocumentRequest
	 */
	public void addDocument(AddDocumentRequest addDocumentRequest) {
		DocumentEntity document = DocumentTypeMapper.INSTANCE.toDocumentTypeEntity(addDocumentRequest);
		documentRepo.save(document);
	}

	/**
	 * 根据type获取
	 * 
	 * @param documentType
	 * @return
	 */
	public List<DocumentDTO> findByType(DocumentTypeEnum documentType) {
		List<DocumentEntity> documents = documentRepo.findByDocumentType(documentType);
		return DocumentTypeMapper.INSTANCE.toDocumentDTOs(documents);
	}
	
	/**
	 * 添加文档间关系
	 * 
	 * @param addRelationRequest
	 */
	public void addRelation(AddRelationRequest addRelationRequest) {
		String parentId = null;
		if (StringUtils.isNotBlank(addRelationRequest.getParent())) {
			DocumentTypeRelationEntity parent = documentRelationRepo.findByChosenDocIdAndSubordinateDocIdAndFirstLevel(addRelationRequest.getParent(), addRelationRequest.getChosenDocId(), addRelationRequest.getFirstLevel());
			parentId = parent.getId();
		}
		List<DocumentTypeRelationEntity> documentTypeRelations = new ArrayList<>();
		for(String subordinateDocId : addRelationRequest.getSubordinateDocIds()){
			DocumentTypeRelationEntity documentTypeRelation = new DocumentTypeRelationEntity();
			documentTypeRelation.setChosenDocId(addRelationRequest.getChosenDocId());
			documentTypeRelation.setParentProjectId(addRelationRequest.getParentProjectId());
			documentTypeRelation.setTemplate(addRelationRequest.getTemplate());
			documentTypeRelation.setType(addRelationRequest.getType());
			documentTypeRelation.setSubordinateDocId(subordinateDocId);
			documentTypeRelation.setParent(parentId);
			documentTypeRelation.setFirstLevel(addRelationRequest.getFirstLevel());
			documentTypeRelations.add(documentTypeRelation);
		}
		documentRelationRepo.save(documentTypeRelations);
	}

	/**
	 * 查询文档关系
	 * 
	 * @param queryRelationRequest
	 * @return
	 */
	public List<DocumentDTO> findRelation(QueryRelationRequest queryRelationRequest) {
		List<DocumentTypeRelationEntity> documentTypeRelations = new ArrayList<>();
		if (StringUtils.isBlank(queryRelationRequest.getParent())) {
			documentTypeRelations = documentRelationRepo.findByChosenDocIdAndParentProjectIdAndTypeAndTemplateAndFirstLevel(queryRelationRequest.getChosenDocId(), queryRelationRequest.getParentProjectId(), queryRelationRequest.getType(), queryRelationRequest.getTemplate(), queryRelationRequest.getFirstLevel());
		}else{
			// 如果parent不为空,则获取到父级的id 
			DocumentTypeRelationEntity parent = documentRelationRepo.findByChosenDocIdAndSubordinateDocIdAndFirstLevel(queryRelationRequest.getParent(), queryRelationRequest.getChosenDocId(), queryRelationRequest.getFirstLevel());
			documentTypeRelations = documentRelationRepo.findByChosenDocIdAndParentProjectIdAndTypeAndTemplateAndParentAndFirstLevel(queryRelationRequest.getChosenDocId(), queryRelationRequest.getParentProjectId(), queryRelationRequest.getType(), queryRelationRequest.getTemplate(), parent.getId(), queryRelationRequest.getFirstLevel());
		}
		Set<String> ids = documentTypeRelations.stream().map(DocumentTypeRelationEntity::getSubordinateDocId).collect(Collectors.toSet());
		List<DocumentEntity> documents = documentRepo.findByIdIn(ids);
		return DocumentTypeMapper.INSTANCE.toDocumentDTOs(documents);
	}
}
