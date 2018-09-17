package com.hushunjian.gradle.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author hushunjian
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CourseEnum {
	
    /**
     * 语文
     */
    Chinese(0, "语文"),
    /**
     * 数学
     */
    Math(1, "数学"),
    /**
     * 英语
     */
    English(2, "英语");


    private int key;

    private String value;

    CourseEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    @JsonCreator
    public static CourseEnum fromKey(@JsonProperty("key") int key) {
        for (CourseEnum s : CourseEnum.values()) {
            if (s.key == key) {
                return s;
            }
        }
        return Chinese;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
