package com.hushunjian.gradle.enumeration;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author hushunjian
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CriteriaEnum {
	eq,
	notEq,
	isNull,
	in,
	ge,
	between,
	le;
}
