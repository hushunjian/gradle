package com.hushunjian.gradle.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class Response<T> implements Serializable {

	private static final long serialVersionUID = 1L;

    public static final String DEFAULT_MESSAGE_SUCCESS = "ok";

    public static final String DEFAULT_MESSAGE_FAILURE = "failure";

    public static final int DEFAULT_CODE_SUCCESS = 200;

    public static final int DEFAULT_CODE_FAILURE = 500;
    
    
    private String message;
    
    private int code;
    
    private T data;

    
    private Response() {
    }

    private Response(int code, String message, T data) {
        this(code, message);
        this.data = data;
    }

    private Response(int code, String message) {
        this.code = code;
        this.message = message;
    }
    
    /**
     * 系统默认成功
     *
     * @param data
     * @return
     */
    public Response success(T data) {
        Response response = new Response(DEFAULT_CODE_SUCCESS, DEFAULT_MESSAGE_SUCCESS, data);
        return response;
    }

    /**
     * 系统默认成功
     *
     * @return
     */
    public Response success() {
        Response response = new Response(DEFAULT_CODE_SUCCESS, DEFAULT_MESSAGE_SUCCESS);
        return response;
    }

    /**
     * 系统默认失败 ,失败并设置message
     */
    public Response failure(String message) {
        Response response = new Response(DEFAULT_CODE_FAILURE, message);
        return response;
    }

    /**
     * 系统默认失败
     *
     * @param message
     * @return
     */
    public Response failure(int code, String message) {
        Response response = new Response(code, message);
        return response;
    }

    /**
     * 请求成功
     *
     * @param code
     * @param message
     * @return
     */
    public Response success(int code, String message) {
        Response response = new Response(code, message);
        return response;
    }

    /**
     * 用户自定义成功
     *
     * @param code
     * @param message
     * @param data
     * @return
     */
    public Response success(int code, String message, T data) {
        Response response = new Response(code, message, data);
        return response;
    }

    /**
     * 用户自定义失败
     *
     * @param code
     * @param message
     * @param data
     * @return
     */
    public Response failure(int code, String message, T data) {
        Response response = new Response(code, message, data);
        return response;
    }
}
