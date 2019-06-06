package com.hushunjian.gradle.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.AuditProcess;
import com.hushunjian.gradle.enumeration.AuditProcessTypeEnum;

public interface AuditProcessRepo extends JpaRepository<AuditProcess, Long>{
	
	List<AuditProcess> findByProjectIdAndAuditProcessType(String projectId, AuditProcessTypeEnum auditProcessType);
}
