package com.geektcp.alpha.spring.security.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.geektcp.alpha.spring.security.constant.status.Status;
import com.geektcp.alpha.spring.security.exception.BaseException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author tanghaiyang on 2018/5/2.
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "接口响应数据", description = "用于返回接口响应的内容")
public class TResponse<T> {

    @ApiModelProperty(value = "请求是否成功：true（成功）；false（失败）", example = "true")
    private boolean success = true;

    @ApiModelProperty(value = "请求错误信息")
    private String message;

    @ApiModelProperty(value = "请求响应数据")
    private Payload<T> payload;

    @ApiModelProperty(value = "code")
    private int code;

    public TResponse(){

    }

    public TResponse(T data) {
        this.setData(data);
    }

    public TResponse(Status status) {
        this.setCode(status.getCode());
        this.setMessage(status.getDesc());
    }

    public TResponse(int code) {
        this.setCode(code);
    }
    public TResponse(int code, String msg) {
        this.setCode(code);
        this.setMessage(msg);
    }

    public TResponse(HttpStatus httpStatus, String msg) {
        this.setCode(httpStatus.value());
        this.setMessage(msg);
    }



    public static TResponse success() {
        return new TResponse();
    }

    public static <T> TResponse success(T data) {
        return new TResponse(data);
    }

    public static TResponse error() {
        TResponse response = new TResponse<>();
        response.setSuccess(false);
        return response;
    }

    public static TResponse error(String message) {
        TResponse response = new TResponse();
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }

    public static TResponse error(BaseException ex) {
        TResponse response = new TResponse();
        response.setSuccess(false);
        return response;
    }

    public static TResponse error(Status status) {
        TResponse response = new TResponse();
        response.setSuccess(false);
        return response;
    }

    public static TResponse error(Status status, Object... args) {
        TResponse response = new TResponse();
        response.setSuccess(false);
        return response;
    }

    protected static Map<String, Object> buildMessage(BaseException ex) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", ex.getCode());
        map.put("desc", ex.getDesc());
        return map;
    }

    protected static Map<String, Object> buildMessage(Status status) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", status.getCode());
        map.put("desc", status.getDesc());
        return map;
    }

    protected static Map<String, Object> buildMessage(Status status, Object... args) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", status.getCode());
        map.put("desc", format(status.getDesc(), args));
        return map;
    }

    private static String format(String pattern, Object... arguments) {
        return MessageFormat.format(pattern, arguments);
    }

    public TResponse setErrorInfo(Status status) {
        this.setMessage(status.getDesc());
        return this;
    }

    public TResponse setData(T data) {
        if (payload == null) {
            payload = new Payload(data);
        }
        this.payload.setData(data);
        return this;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(value = "接口数据", description = "用于表示接口数据内容")
    public static class Payload<T> {

        @ApiModelProperty(value = "数据内容")
        private T data;
    }
}
