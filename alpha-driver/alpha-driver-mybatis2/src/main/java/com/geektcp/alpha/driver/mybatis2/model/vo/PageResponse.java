package com.geektcp.alpha.driver.mybatis2.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.geektcp.alpha.driver.mybatis2.model.qo.PageQo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author haiyang on 3/10/20 9:55 AM.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "分页查询响应数据", description = "用于返回分页查询响应的内容")
public class PageResponse<T> {

    @ApiModelProperty(value = "请求是否成功：true（成功）；false（失败）", example = "true")
    private boolean success;

    private Object message;

    /**
     * 错误码
     */
    private Integer statusCode;


    @ApiModelProperty(value = "请求错误信息")
    private String statusMsg;

    /**
     * 鉴权token
     */
    private String jwtToken;

    /**
     * 返回数据json
     */
    private T data;

    @ApiModelProperty(value = "请求响应数据")
    private PagePayload<T> payload;

    public PageResponse() {
        this(0, 1, 10);
    }

    public PageResponse(long total, int pageNo, int pageSize) {
        this(null, total, pageNo, pageSize);
    }

    public PageResponse(List<T> data, long total, int pageNo, int pageSize) {
        this.setPage(data, total, pageNo, pageSize);
    }

    public static PageResponse success() {
        return new PageResponse();
    }

    public static <T> PageResponse success(List<T> data, long total, PageQo pageQo) {
        return new PageResponse(data, total, pageQo.getPageNo(), pageQo.getPageSize());
    }

    public static PageResponse error() {
        PageResponse response = new PageResponse();
        response.setSuccess(false);
        return response;
    }

    public PageResponse setPage(List<T> data, long total, int pageNo, int pageSize) {
        if (payload == null) {
            payload = new PagePayload();
        }
        this.payload.setTotal(total);
        this.payload.setPageNo(pageNo);
        this.payload.setPageSize(pageSize);
        this.payload.setData(data);
        return this;
    }

    public PageResponse setPage(List<T> data, Page page) {
        int total = page.getTotal();
        int pageNo = page.getCurrent();
        int pageSize = page.getSize();
        if (payload == null) {
            payload = new PagePayload();
        }
        this.payload.setTotal(total);
        this.payload.setPageNo(pageNo);
        this.payload.setPageSize(pageSize);
        this.payload.setData(data);
        return this;
    }

    public PageResponse(Integer statusCode, String statusMsg, T data) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
        this.data = data;
        this.success = true;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(value = "分页接口数据", description = "用于表示分页接口内容")
    public static class PagePayload<T> {

        @ApiModelProperty(value = "总记录数", example = "100")
        @JSONField(ordinal = 1)
        private long total;

        @ApiModelProperty(value = "当前页", example = "1")
        @JSONField(ordinal = 2)
        private int pageNo = 1;

        @ApiModelProperty(value = "每页显示记录数", example = "10")
        @JSONField(ordinal = 3)
        private int pageSize = 10;

        @JSONField(ordinal = 4)
        public long getTotalPages() {
            return (total + pageSize - 1) / pageSize;
        }

        @ApiModelProperty(value = "数据内容")
        @JSONField(ordinal = 5)
        private List<T> data;

    }
}
