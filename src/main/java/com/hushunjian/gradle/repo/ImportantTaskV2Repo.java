package com.hushunjian.gradle.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hushunjian.gradle.entity.ImportantTaskV2Entity;

public interface ImportantTaskV2Repo extends JpaRepository<ImportantTaskV2Entity, Long>, JpaSpecificationExecutor<ImportantTaskV2Entity>{

	List<ImportantTaskV2Entity> findByGroupIsNull();

}

