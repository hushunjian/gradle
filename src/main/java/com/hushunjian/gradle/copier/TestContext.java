package com.hushunjian.gradle.copier;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;

import com.hushunjian.gradle.dto.OperatorDTO;

public class TestContext {
	
	@AfterMapping
	public void b(@MappingTarget OperatorDTO operatorDTO,int num){
		operatorDTO.setNum(num);
	}
	

	@BeforeMapping
	public void a(@MappingTarget OperatorDTO operatorDTO){
		operatorDTO.setNum(10);
	}
	
	
}

