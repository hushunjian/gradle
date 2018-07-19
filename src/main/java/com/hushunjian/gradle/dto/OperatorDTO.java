package com.hushunjian.gradle.dto;

import lombok.Data;

@Data
public class OperatorDTO {

    private Long id;  
  
	private String operatorName; 
	
	private int age;  
	
	private String createdBy;  
	
	private String createdTime;  
	
	private String updatedBy;  
	
	private String updatedTime;  
	
	private int status;
}
