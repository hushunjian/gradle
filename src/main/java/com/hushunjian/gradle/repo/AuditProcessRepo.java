package com.hushunjian.gradle.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hushunjian.gradle.entity.AuditProcess;
import com.hushunjian.gradle.enumeration.AuditProcessTypeEnum;

public interface AuditProcessRepo extends JpaRepository<AuditProcess, String>{
	
	List<AuditProcess> findByProjectIdAndAuditProcessType(String projectId, AuditProcessTypeEnum auditProcessType);
	
	@Modifying
	@Query(value = "delete from AuditProcess where id = :id")
	void deleteA(@Param("id") String id);
}
