package com.hushunjian.gradle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hushunjian.gradle.entity.JPAEntityC;
import com.hushunjian.gradle.repo.JPA_3Repo;

@Service
public class JPA_3Service {
	
	@Autowired
	private JPA_3Repo jpa_3Repo;

	public JPAEntityC addJpa_3(String name) {
		JPAEntityC jpaEntityC = new JPAEntityC();
		jpaEntityC.setName(name);
		jpa_3Repo.save(jpaEntityC);
		return jpaEntityC;
	}

	public JPAEntityC updateJpa_3(Long id, String name) {
		JPAEntityC jpaEntityC = jpa_3Repo.findOne(id);
		if (jpaEntityC != null) {
			jpaEntityC.setName(name);
			jpa_3Repo.save(jpaEntityC);
		}
		return jpaEntityC;
	}

	public void removeJpa_3(Long id) {
		JPAEntityC jpaEntityC = jpa_3Repo.findOne(id);
		if (jpaEntityC != null) {
			jpa_3Repo.delete(id);
		}
	}

}
