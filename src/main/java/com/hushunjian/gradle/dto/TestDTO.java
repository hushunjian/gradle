package com.hushunjian.gradle.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TestDTO extends ParentDTO {
	
	private String path;
}
