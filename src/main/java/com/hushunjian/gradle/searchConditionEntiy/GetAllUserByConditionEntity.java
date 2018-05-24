package com.hushunjian.gradle.searchConditionEntiy;

import lombok.Data;

@Data
public class GetAllUserByConditionEntity {
	
	private String name;
	
	private int pageIndex;
	
	private int pageSize;
}
