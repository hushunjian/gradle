package com.hushunjian.gradle.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hushunjian.gradle.entity.JPAEntityA;
import com.hushunjian.gradle.repo.JPAXMLRepo;

@Service
@Transactional
public class JPAXMLService {
	
	@Autowired
	private JPAXMLRepo jPAXMLRepo;

	public List<JPAEntityA> test(String userName) {
		return jPAXMLRepo.test(userName);
	}

}
