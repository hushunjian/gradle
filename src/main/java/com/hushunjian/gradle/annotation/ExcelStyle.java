package com.hushunjian.gradle.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelStyle {
	
	/*
	 * 标题
	 */
	String title() default "";
	
	/*
	 * 是否需要合并标题样式 
	 */
	boolean needMerged() default false;
	
	/*
	 * 合并顺序
	 */
	int mergedOrder() default 0;
	
	/*
	 * 需要合并的列与行 
	 */
	int[] mergedRegion() default {0,0,0,0};

	/*
	 * 合并后是否需要添加新标题
	 */
	boolean needAddNewTitle() default false;
	
	/*
	 * 合并后标题
	 */
	String mergedTitle() default "";
	
}
