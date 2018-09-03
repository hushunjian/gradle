package com.hushunjian.gradle.dto;

import java.io.Serializable;

import com.hushunjian.gradle.annotation.ExcelStyle;

import lombok.Data;

@Data
public class OperatorDTOExcelStyle implements Serializable {

	private static final long serialVersionUID = 1L;

	@ExcelStyle(title = "工程名称", needMerged = true, mergedOrder = 0, mergedRegion = {0,1,0,0})
    private Long id;  
  
	@ExcelStyle(title = "类别", needMerged = true, mergedOrder = 1, mergedRegion = {0,1,1,1})
	private String operatorName; 
	
	@ExcelStyle(title = "总产值(万元)", needMerged = true, mergedOrder = 2, mergedRegion = {0,0,2,3}, needAddNewTitle = true, mergedTitle = "工程概况")
	private int age;  
	
	@ExcelStyle(title = "结构类型", needMerged = true, mergedOrder = 2)
	private String createdBy;  
	
	@ExcelStyle(title = "合同范围", needMerged = true, mergedOrder = 3, mergedRegion = {0,0,4,6}, needAddNewTitle = true, mergedTitle = "合同情况")
	private String createdTime;  
	
	@ExcelStyle(title = "合同工期", needMerged = true, mergedOrder = 3)
	private String updatedBy;  
	
	@ExcelStyle(title = "合同重大变动", needMerged = true, mergedOrder = 3)
	private String updatedTime;  
	
	private int status;
}
