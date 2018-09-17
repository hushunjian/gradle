package com.hushunjian.gradle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hushunjian.gradle.dto.StringToIntegerDTO;
import com.hushunjian.gradle.repo.StringToIntegerRepo;

@Service
public class TestService {
	
	@Autowired
	private StringToIntegerRepo stringToIntegerRepo;

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
	

}
