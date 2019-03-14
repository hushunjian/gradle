package com.hushunjian.gradle.request;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class TestListEmptyRequest {
	
	@NotEmpty
	private List<String> list;
}
