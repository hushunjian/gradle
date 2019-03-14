package com.hushunjian.gradle.copier;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.hushunjian.gradle.dto.DocumentDTO;
import com.hushunjian.gradle.entity.DocumentEntity;
import com.hushunjian.gradle.request.AddDocumentRequest;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DocumentTypeMapper {
    DocumentTypeMapper INSTANCE = Mappers.getMapper(DocumentTypeMapper.class);
    
    DocumentEntity toDocumentTypeEntity(AddDocumentRequest bean);
    
    List<DocumentDTO> toDocumentDTOs(List<DocumentEntity> beans);
}
