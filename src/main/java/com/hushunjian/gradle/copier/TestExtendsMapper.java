package com.hushunjian.gradle.copier;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.hushunjian.gradle.dto.AuditProcessDTO;
import com.hushunjian.gradle.dto.DocumentDTO;
import com.hushunjian.gradle.dto.ParentDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TestExtendsMapper {
    TestExtendsMapper TEST_TENDES_MAPPER = Mappers.getMapper(TestExtendsMapper.class);

    void copyProperties(DocumentDTO source , @MappingTarget ParentDTO target);
    
    @Mapping(target = "test", defaultValue = "false")
    DocumentDTO toDocumentDTO(AuditProcessDTO bean);
}
