package com.hushunjian.gradle.dto;

import java.io.Serializable;

import com.hushunjian.gradle.annotation.ExcelTitle;

import lombok.Data;

@Data
public class OperatorDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ExcelTitle("id")
    private Long id;  
  
	@ExcelTitle("操作者名称")
	private String operatorName; 
	
	@ExcelTitle("年龄")
	private int age;  
	
	@ExcelTitle("创建者名称")
	private String createdBy;  
	
	@ExcelTitle("创建时间")
	private String createdTime;  
	
	@ExcelTitle("修改者名称")
	private String updatedBy;  
	
	@ExcelTitle("修改时间")
	private String updatedTime;  
	
	private int status;
}
