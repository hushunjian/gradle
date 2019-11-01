package com.hushunjian.gradle.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BooleanReq {

	@NotNull
	private Boolean is;
}
