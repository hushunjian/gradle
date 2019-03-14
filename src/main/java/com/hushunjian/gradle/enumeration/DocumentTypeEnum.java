package com.hushunjian.gradle.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author hushunjian
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DocumentTypeEnum {
	
	ENGINEERING_STAGE(1, "工程阶段"),
	
	PARTICIPATING_PARTY(2, "参建方"),

	UNIT_ENGINEERING(3, "单位工程"),
	 
	MAJOR(4, "专业");

    private int key;

    private String value;

    DocumentTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    @JsonCreator
    public static DocumentTypeEnum fromKey(int key) {
        for (DocumentTypeEnum s : DocumentTypeEnum.values()) {
            if (s.key == key) {
                return s;
            }
        }
        return ENGINEERING_STAGE;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
   
}
