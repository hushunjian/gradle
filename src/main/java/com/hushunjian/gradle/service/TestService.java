package com.hushunjian.gradle.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.hushunjian.gradle.copier.AuditProcessMapper;
import com.hushunjian.gradle.copier.TestMapper2;
import com.hushunjian.gradle.dto.AuditProcessDTO;
import com.hushunjian.gradle.dto.StringToIntegerDTO;
import com.hushunjian.gradle.entity.AuditProcess;
import com.hushunjian.gradle.entity.DateTestEntity;
import com.hushunjian.gradle.entity.TestStringToInteger;
import com.hushunjian.gradle.repo.AuditProcessRepo;
import com.hushunjian.gradle.repo.DateTestRepo;
import com.hushunjian.gradle.repo.StringToIntegerRepo;
import com.hushunjian.gradle.request.TestListInRequest;

@Service
@Transactional
public class TestService {
	
	@Autowired
	private StringToIntegerRepo stringToIntegerRepo;
	
	@Autowired
	private AuditProcessRepo auditProcessRepo;
	
	@Autowired
	private DateTestRepo dateTestRepo;
	
	@PersistenceContext
	private EntityManager entityManager;

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

	public List<StringToIntegerDTO> testContain(String number) {
		List<TestStringToInteger> contains =  stringToIntegerRepo.findByNumberContains(number);
		return TestMapper2.INSTANCE.asStringToIntegerDTO(contains);
	}

	public List<StringToIntegerDTO> testNotContain(String number) {
		List<TestStringToInteger> notContains =  stringToIntegerRepo.findByNumberNotContains(number);
		return TestMapper2.INSTANCE.asStringToIntegerDTO(notContains);
	}

	public List<StringToIntegerDTO> findAll() {
		List<TestStringToInteger> notContains =  stringToIntegerRepo.findAll();
		return TestMapper2.INSTANCE.asStringToIntegerDTO(notContains);
	}

	public Long testCount(String number) {
		return stringToIntegerRepo.countByNumber(number);
	}
	
	
	public List<StringToIntegerDTO> findByIdIn(List<Long> ids){
		List<TestStringToInteger> in = stringToIntegerRepo.findByIdIn(ids);
		TestStringToInteger add = new TestStringToInteger();
		add.setId(1000L);
		in.add(add);
		return TestMapper2.INSTANCE.asStringToIntegerDTO(in);
	}
	
	public List<StringToIntegerDTO> findAll(List<Long> ids){
		List<TestStringToInteger> findAll = stringToIntegerRepo.findAll(ids);
		TestStringToInteger add = new TestStringToInteger();
		add.setId(1000L);
		findAll.add(add);
		return TestMapper2.INSTANCE.asStringToIntegerDTO(findAll);
	}
	
	public void find(Long... ids){
	}

	public List<StringToIntegerDTO> findByJPA(String number) {
		List<TestStringToInteger> findByNumberStartingWith = stringToIntegerRepo.findByNumberStartingWith(number);
		return TestMapper2.INSTANCE.asStringToIntegerDTO(findByNumberStartingWith);
	}

	public List<StringToIntegerDTO> findByQuery(String number) {
		List<TestStringToInteger> findByNumberQuery = stringToIntegerRepo.findByNumberQuery(number);
		return TestMapper2.INSTANCE.asStringToIntegerDTO(findByNumberQuery);
	}

	public void test7() {
		for(int i = 0; i < 4; i++){
			stringToIntegerRepo.findAll();
		}
	}

	public DateTestEntity testDate() {
		Date now = DateUtils.round(new Date(), Calendar.SECOND);
		String sendTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);
		DateTestEntity date = new DateTestEntity();
		date.setSendTime(now);
		date.setTimeStr(sendTime);
		try {
			date.setSendTimeA(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sendTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ZonedDateTime sendTimeB = ZonedDateTime.now();
		date.setSendTimeB(sendTimeB);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());
		date.setSendTimeBStr(sendTimeB.format(formatter));
		dateTestRepo.save(date);
		Long[] ids = new Long[]{1L, 2L, 3L, 5L};
		List<DateTestEntity> findByIdIn = dateTestRepo.findByIdIn(ids);
		findByIdIn.size();
		return date;
	}

	public DateTestEntity saveAndFlush() {
		DateTestEntity date = new DateTestEntity();
		date.setSendTime(new Date());
		dateTestRepo.save(date);
		entityManager.refresh(date);
		//List<DateTestEntity> dates = new ArrayList<>();
		//entityManager.refresh(dates);
		return date;
	}

	public DateTestEntity testSaveNullPro(Long id) {
		DateTestEntity date = dateTestRepo.findOne(id);
		date.setTimeStr("aaaa");
		return date;
	}

	public List<StringToIntegerDTO> testInEmpty() {
		List<String> numbers = new ArrayList<>();
		String number0 = "0";
		List<TestStringToInteger> findByNumberInAndNumber0 = stringToIntegerRepo.findByNumberInAndNumber0(numbers, number0);
		return TestMapper2.INSTANCE.asStringToIntegerDTO(findByNumberInAndNumber0);
	}

	public void checkHasChildrenParentId() {
		List<String> parentIds = new ArrayList<>();
		parentIds.add("444499");
		List<String> checkHasChildrenParentId = stringToIntegerRepo.checkHasChildrenParentId(parentIds);
		System.out.println(checkHasChildrenParentId.size());
		System.out.println(checkHasChildrenParentId);
	}

	public void testUpdat() {
		TestStringToInteger findOne = stringToIntegerRepo.findOne(2L);
		findOne.setId(20L);
	}

	public void testVersion() {
		// TODO Auto-generated method stub
		AuditProcess findOne = auditProcessRepo.findOne("4028b8816e22b9d4016e22b9fd570000");
		for(int i = 0; i < 2; i++) {
			findOne.setOrderNum(i);
		}
		for(int i = 0; i < 3; i++) {
			findOne.setName(i + "");
			auditProcessRepo.save(findOne);
		}
		test(findOne);
	}
	
	private void test(AuditProcess findOne) {
		AuditProcessDTO dto = AuditProcessMapper.AUDIT_PROCESS.toAuditProcessDTO(findOne);
		for(int i = 0; i < 4; i++) {
			dto.setProjectId(i + "");
		}
		auditProcessRepo.save(AuditProcessMapper.AUDIT_PROCESS.toAuditProcess(dto));
		test(dto);
	}
	
	private void test(AuditProcessDTO dto) {
		List<AuditProcess> list = Lists.newArrayList();
		List<AuditProcessDTO> list1 = Lists.newArrayList();
		for(int i = 0; i < 5; i++) {
			dto.setProjectId(i + "");
			list1.add(dto);
			list.add(AuditProcessMapper.AUDIT_PROCESS.toAuditProcess(dto));
		}
		auditProcessRepo.deleteA("4028b8816e22b9d4016e22b9fd570000");
		auditProcessRepo.save(list);
		test(list1);
	}
	
	
	private void test(List<AuditProcessDTO> dto) {
		List<AuditProcess> auditProcess = AuditProcessMapper.AUDIT_PROCESS.toAuditProcess(dto);
		auditProcessRepo.save(auditProcess);
	}
}
