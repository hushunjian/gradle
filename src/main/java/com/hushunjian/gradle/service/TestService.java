package com.hushunjian.gradle.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hushunjian.gradle.copier.TestMapper2;
import com.hushunjian.gradle.dto.StringToIntegerDTO;
import com.hushunjian.gradle.entity.TestStringToInteger;
import com.hushunjian.gradle.repo.StringToIntegerRepo;
import com.hushunjian.gradle.request.TestListInRequest;

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

	public Integer testIdInByArray() {
		List<TestStringToInteger> findByIdIn = stringToIntegerRepo.findByIdIn(1L,2L,3L);
		return findByIdIn.size();
	}

	public Integer testIdInByList() {
		List<Long> ids = new ArrayList<>();
		ids.add(1L);
		ids.add(2L);
		ids.add(3L);
		List<TestStringToInteger> findByIdIn = stringToIntegerRepo.findByIdIn(ids);
		return findByIdIn.size();
	}

	public List<StringToIntegerDTO> findDTO() {
		List<TestStringToInteger> findAll = stringToIntegerRepo.findAll();
		return TestMapper2.INSTANCE.asStringToIntegerDTO(findAll);
	}

	public void testSaveAndEdit(StringToIntegerDTO stringToIntegerDTO) {
		TestStringToInteger testStringToInteger = TestMapper2.INSTANCE.asTestStringToInteger(stringToIntegerDTO);
		testStringToInteger.setNumber0("0");
		testStringToInteger.setNumber1("0");
		testStringToInteger.setNumber2("0");
		testStringToInteger.setNumber3("0");
		testStringToInteger.setNumber4("0");
		testStringToInteger.setNumber5("0");
		testStringToInteger.setNumber6("0");
		testStringToInteger.setNumber7("0");
		testStringToInteger.setNumber8("0");
		testStringToInteger.setNumber9("0");
		testStringToInteger.setNumber10("0");
		testStringToInteger.setNumber11("0");
		testStringToInteger.setNumber12("0");
		testStringToInteger.setNumber13("0");
		testStringToInteger.setNumber14("0");
		testStringToInteger.setNumber15("0");
		stringToIntegerRepo.save(testStringToInteger);
	}

	public void testDelete(List<Long> ids) {
		List<TestStringToInteger> in = stringToIntegerRepo.findByIdIn(ids);
		stringToIntegerRepo.delete(in);
	}

	public List<StringToIntegerDTO> testListIn(List<TestListInRequest> request) {
		List<String> strs = new ArrayList<>();
		request.forEach(re ->{
			strs.add(String.format("%s|%s",re.getId(),re.getNumber()));
		});
		List<TestStringToInteger> in = stringToIntegerRepo.testListIn(strs);
		return TestMapper2.INSTANCE.asStringToIntegerDTO(in);
	}
}
