package com.hushunjian.gradle.copier;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.hushunjian.gradle.dto.AuditProcessDTO;
import com.hushunjian.gradle.entity.AuditProcess;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuditProcessMapper {
    AuditProcessMapper AUDIT_PROCESS = Mappers.getMapper(AuditProcessMapper.class);

    List<AuditProcessDTO> toAuditProcessDTO(List<AuditProcess> beans);
}
