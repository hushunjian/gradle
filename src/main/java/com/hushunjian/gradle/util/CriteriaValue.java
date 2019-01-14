package com.hushunjian.gradle.util;

import com.hushunjian.gradle.enumeration.CriteriaEnum;
import com.hushunjian.gradle.enumeration.CriteriaType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriteriaValue {
	
	/**
	 * 查询条件
	 */
	private String propertyName;
	
	/**
	 * 值
	 */
	private Object value;
	
	/**
	 * 条件
	 */
	private CriteriaEnum criteria;
	
	/**
	 * 
	 */
	private CriteriaType criteriaType;
}
