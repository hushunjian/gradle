package com.hushunjian.gradle.dto;

import lombok.Data;

@Data
public class StringToIntegerDTO {
	private Long id;
	
	private int number;
	
	private Long sum;
	
	public StringToIntegerDTO(){
		
	}

	public StringToIntegerDTO(Long id, int number) {
		super();
		this.id = id;
		this.number = number;
	}
	public StringToIntegerDTO(Long sum) {
		super();
		this.sum = sum;
	}
}
