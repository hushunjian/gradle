package com.hushunjian.gradle.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class JPAEntityDTO {
	private Long id;
	
	private String userId;
	
	private BigDecimal score;
}
