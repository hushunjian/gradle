package com.hushunjian.gradle.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hushunjian.gradle.copier.TestMapper2;
import com.hushunjian.gradle.dto.JPAEntity3DTO;
import com.hushunjian.gradle.entity.JPAEntityC;
import com.hushunjian.gradle.repo.JPA_3Repo;

@Service
@Transactional
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

	public JPAEntityC testVersion(Long id, String name) {
		JPAEntityC jPAEntityC = jpa_3Repo.findOne(id);
		jPAEntityC.setName(name);
		jpa_3Repo.save(jPAEntityC);
		return jPAEntityC;
	}

	public JPAEntity3DTO testVersionDTO(Long id, String name) {
		JPAEntityC jPAEntityC = jpa_3Repo.findOne(id);
		jPAEntityC.setName(name);
		jpa_3Repo.save(jPAEntityC);
		return TestMapper2.INSTANCE.toJPAEntity3DTO(jPAEntityC);
	}

	public JPAEntity3DTO testVersionDTO(JPAEntity3DTO jPAEntity3DTO) {
		JPAEntityC jPAEntityC = jpa_3Repo.findOne(jPAEntity3DTO.getId());
		TestMapper2.INSTANCE.copyValue(jPAEntity3DTO, jPAEntityC);
		jpa_3Repo.save(jPAEntityC);
		return TestMapper2.INSTANCE.toJPAEntity3DTO(jPAEntityC);
	}

	public void testCopy(JPAEntity3DTO jPAEntity3DTO) {
		JPAEntityC jPAEntityC = jpa_3Repo.findOne(jPAEntity3DTO.getId());
		TestMapper2.INSTANCE.copyValue(jPAEntity3DTO, jPAEntityC);
	}

}
