package com.geektcp.alpha.common.spring.model;

import alpha.common.base.constant.Status;
import alpha.common.base.exception.BaseException;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
    private Object message;

    @ApiModelProperty(value = "请求响应数据")
    private Payload<T> payload;

    public TResponse() {
        this(null);
    }

    public TResponse(T data) {
        this.setData(data);
    }

    public static TResponse success() {
        return new TResponse();
    }

    public static <T> TResponse success(T data) {
        return new TResponse(data);
    }

    public static TResponse error() {
        TResponse TResponse = new TResponse();
        TResponse.setSuccess(false);
        return TResponse;
    }

    public static TResponse error(String message) {
        TResponse TResponse = new TResponse();
        TResponse.setSuccess(false);
        TResponse.setMessage(message);
        return TResponse;
    }

    public static TResponse error(BaseException ex) {
        TResponse TResponse = new TResponse();
        TResponse.setSuccess(false);
        TResponse.setMessage(buildMessage(ex));
        return TResponse;
    }

    public static TResponse error(Status status) {
        TResponse TResponse = new TResponse();
        TResponse.setSuccess(false);
        TResponse.setMessage(buildMessage(status));
        return TResponse;
    }

    public static TResponse error(Status status, Object... args) {
        TResponse TResponse = new TResponse();
        TResponse.setSuccess(false);
        TResponse.setMessage(buildMessage(status, args));
        return TResponse;
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
        this.setMessage(status);
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
        @JSONField(ordinal = 1)
        private T data;
    }
}
