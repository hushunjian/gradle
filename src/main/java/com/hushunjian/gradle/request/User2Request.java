package com.hushunjian.gradle.request;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class User2Request {

	@NotBlank
	private String userName2;
}
