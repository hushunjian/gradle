package com.hushunjian.gradle.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hushunjian.gradle.dto.AuditProcessDTO;
import com.hushunjian.gradle.entity.AuditProcess;
import com.hushunjian.gradle.enumeration.AuditProcessTypeEnum;
import com.hushunjian.gradle.repo.AuditProcessRepo;
import static com.hushunjian.gradle.copier.AuditProcessMapper.AUDIT_PROCESS;;

@Service
@Transactional
public class AuditProcessService {

	@Autowired
	private AuditProcessRepo auditProcessRepo;
	
	public List<AuditProcessDTO> findByProjectIdAndProcessType(String projectId, AuditProcessTypeEnum auditProcessType) {
		List<AuditProcess> auditProcess = auditProcessRepo.findByProjectIdAndAuditProcessType(projectId, auditProcessType);
		return AUDIT_PROCESS.toAuditProcessDTO(auditProcess);
	}

}
