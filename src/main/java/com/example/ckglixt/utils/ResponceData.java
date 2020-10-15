package com.example.ckglixt.utils;

import java.io.Serializable;

/**
 * <p>系统统一返回格式</p>
 * <p>创建日期：2020-3-25</p>
 * @author scout
 */
public class ResponceData<T> implements Serializable {

    /**
     * 返回编码
     */
    private int code;


    /**
     * 消息描述
     */
    private String msg;

    /**
     * 返回内容
     */
    private T data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public ResponceData(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static <T> AppResponseBuilder builder() {
        return new AppResponseBuilder<T>();
    }

    /**
     *
     * @return
     */
    public static ResponceData success() {
        return builder().code(ResponceDataState.SUCCESS).build();
    }

    public static ResponceData success(String msg) {
        return builder().code(ResponceDataState.SUCCESS).msg(msg).build();
    }

    public static <T> ResponceData success(String msg, T data) {
        return builder().code(ResponceDataState.SUCCESS).msg(msg).data(data).build();
    }

    public static ResponceData bizError(String msg) {
        return builder().code(ResponceDataState.BIZ_ERROR).msg(msg).build();
    }

    public static ResponceData versionError(String msg) {
        return builder().code(ResponceDataState.ERROR).msg(msg).build();
    }
    public static class AppResponseBuilder<T> {

        private int code;

        private String msg;

        private T data;

        public ResponceData build() {
            ResponceData appResponse = new ResponceData<>(this.code, this.msg, this.data);
            return appResponse;
        }

        public AppResponseBuilder code(ResponceDataState status) {
            this.code = status.code;
            return this;
        }

        public AppResponseBuilder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public AppResponseBuilder data(T data) {
            this.data = data;
            return this;
        }
    }

}
