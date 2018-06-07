package com.hushunjian.gradle.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hushunjian.gradle.entity.TestDefault;
import com.hushunjian.gradle.repo.TestDefaultRepo;

@Service
@Transactional
public class TestDefaultService {

	@Autowired
	private TestDefaultRepo testDefaultRepo;
	
	public void addTestDefault(TestDefault testDefault) {
		testDefaultRepo.save(testDefault);
	}

	public void deleteTestDefaultById(Long id) {
		testDefaultRepo.deleteById(id);
	}

}
