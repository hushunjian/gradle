package com.hushunjian.gradle.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.ImportantTaskV2Entity;

public interface ImportantTaskV2Repo extends JpaRepository<ImportantTaskV2Entity, Long>{

	List<ImportantTaskV2Entity> findByGroupIsNull();

}

