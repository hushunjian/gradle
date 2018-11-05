package com.hushunjian.gradle.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hushunjian.gradle.dto.StringToIntegerDTO;
import com.hushunjian.gradle.entity.TestStringToInteger;
import com.hushunjian.gradle.repo.StringToIntegerRepo;

@Service
@Transactional
public class TestService {
	
	@Autowired
	private StringToIntegerRepo stringToIntegerRepo;
	
	@PersistenceContext
    EntityManager entityManager;

	public void tesetReturn(Integer num) {
		
	}

	public void test() {
		List<StringToIntegerDTO> stringToIntegerDTOs = stringToIntegerRepo.test();
		int sum = 0;
		for(StringToIntegerDTO stringToIntegerDTO : stringToIntegerDTOs){
			sum += stringToIntegerDTO.getNumber();
		}
		System.out.println(sum);
		StringToIntegerDTO stringToIntegerDTO = stringToIntegerRepo.test1();
		System.out.println(stringToIntegerDTO.getSum());
	}

	public void testSave() {
		List<TestStringToInteger> save = new ArrayList<>();
		stringToIntegerRepo.save(save);
	}

	public Long testSave1(Long num) {
		System.out.println("开始生成数据库实体,需生成个数:" + num);
		Date start = new Date();
		for(int i=0;i<num;i++){
			TestStringToInteger testStringToInteger = new TestStringToInteger();
			testStringToInteger.setNumber(String.valueOf(i));
			testStringToInteger.setNumber0(String.valueOf(i));
			testStringToInteger.setNumber1(String.valueOf(i));
			testStringToInteger.setNumber2(String.valueOf(i));
			testStringToInteger.setNumber3(String.valueOf(i));
			testStringToInteger.setNumber4(String.valueOf(i));
			testStringToInteger.setNumber5(String.valueOf(i));
			testStringToInteger.setNumber6(String.valueOf(i));
			testStringToInteger.setNumber7(String.valueOf(i));
			testStringToInteger.setNumber8(String.valueOf(i));
			testStringToInteger.setNumber9(String.valueOf(i));
			testStringToInteger.setNumber10(String.valueOf(i));
			testStringToInteger.setNumber11(String.valueOf(i));
			testStringToInteger.setNumber12(String.valueOf(i));
			testStringToInteger.setNumber13(String.valueOf(i));
			testStringToInteger.setNumber14(String.valueOf(i));
			stringToIntegerRepo.save(testStringToInteger);
		}
		Date end = new Date();
		Long time = end.getTime()-start.getTime();
		System.out.println("共耗时[" + time +"]毫秒");
		return time;
	}

	public Long testSave2(Long num) {
		System.out.println("开始生成数据库实体,需生成个数:" + num);
		List<TestStringToInteger> testStringToIntegers = new ArrayList<>();
		Date start = new Date();
		for(int i=0;i<num;i++){
			TestStringToInteger testStringToInteger = new TestStringToInteger();
			testStringToInteger.setNumber(String.valueOf(i));
			testStringToInteger.setNumber0(String.valueOf(i));
			testStringToInteger.setNumber1(String.valueOf(i));
			testStringToInteger.setNumber2(String.valueOf(i));
			testStringToInteger.setNumber3(String.valueOf(i));
			testStringToInteger.setNumber4(String.valueOf(i));
			testStringToInteger.setNumber5(String.valueOf(i));
			testStringToInteger.setNumber6(String.valueOf(i));
			testStringToInteger.setNumber7(String.valueOf(i));
			testStringToInteger.setNumber8(String.valueOf(i));
			testStringToInteger.setNumber9(String.valueOf(i));
			testStringToInteger.setNumber10(String.valueOf(i));
			testStringToInteger.setNumber11(String.valueOf(i));
			testStringToInteger.setNumber12(String.valueOf(i));
			testStringToInteger.setNumber13(String.valueOf(i));
			testStringToInteger.setNumber14(String.valueOf(i));
			testStringToIntegers.add(testStringToInteger);
		}
		stringToIntegerRepo.save(testStringToIntegers);
		Date end = new Date();
		Long time = end.getTime()-start.getTime();
		System.out.println("共耗时[" + time +"]毫秒");
		return time;
	}

	public Long testSave3(Long num) {
		System.out.println("开始生成数据库实体,需生成个数:" + num);
		Date start = new Date();
		for(int i=0;i<num;i++){
			TestStringToInteger testStringToInteger = new TestStringToInteger();
			testStringToInteger.setNumber(String.valueOf(i));
			testStringToInteger.setNumber0(String.valueOf(i));
			testStringToInteger.setNumber1(String.valueOf(i));
			testStringToInteger.setNumber2(String.valueOf(i));
			testStringToInteger.setNumber3(String.valueOf(i));
			testStringToInteger.setNumber4(String.valueOf(i));
			testStringToInteger.setNumber5(String.valueOf(i));
			testStringToInteger.setNumber6(String.valueOf(i));
			testStringToInteger.setNumber7(String.valueOf(i));
			testStringToInteger.setNumber8(String.valueOf(i));
			testStringToInteger.setNumber9(String.valueOf(i));
			testStringToInteger.setNumber10(String.valueOf(i));
			testStringToInteger.setNumber11(String.valueOf(i));
			testStringToInteger.setNumber12(String.valueOf(i));
			testStringToInteger.setNumber13(String.valueOf(i));
			testStringToInteger.setNumber14(String.valueOf(i));
			entityManager.persist(testStringToInteger);
		}
		Date end = new Date();
		Long time = end.getTime()-start.getTime();
		System.out.println("共耗时[" + time +"]毫秒");
		return time;
	}
}
