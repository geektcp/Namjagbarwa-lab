package com.geektcp.alpha.driver.mybatis2.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.geektcp.alpha.driver.mybatis2.enums.Status;
import com.geektcp.alpha.driver.mybatis2.model.qo.PageQo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

/**
 * @author haiyang on 3/10/20 9:55 AM.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "分页查询响应数据", description = "用于返回分页查询响应的内容")
public class PageResponseDTO<T> {

    @ApiModelProperty(value = "状态码")
    private Integer statusCode;

    @ApiModelProperty(value = "备注信息")
    private String statusMsg;

    @ApiModelProperty(value = "token")
    private String jwtToken;

    @ApiModelProperty(value = "请求响应数据")
    private PagePayload<T> data;

    public PageResponseDTO() {
        this(0, 1, 10);
    }

    public PageResponseDTO(List<T> data, PageInfo pageInfo) {
        this.setPage(data, pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPageSize());
    }

    public PageResponseDTO(Status status, List<T> data, PageInfo pageInfo) {
        if(Objects.isNull(pageInfo)){
            pageInfo = new PageInfo();
        }
        this.setPage(data, pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPageSize());
        this.statusCode = status.getCode();
        this.statusMsg = status.getDesc();
    }

    public PageResponseDTO(long total, int pageNo, int pageSize) {
        this(null, total, pageNo, pageSize);
    }

    public PageResponseDTO(List<T> data, long total, int pageNo, int pageSize) {
        this.setPage(data, total, pageNo, pageSize);
    }

    public PageResponseDTO success() {
        return new PageResponseDTO();
    }

    public <T> PageResponseDTO success(List<T> data, long total, PageQo pageQo) {
        if(Objects.isNull(pageQo)){
            pageQo = new PageQo();
        }
        return new PageResponseDTO<>(data, total, pageQo.getPageNo(), pageQo.getPageSize());
    }

    public static PageResponseDTO error() {
        PageResponseDTO response = new PageResponseDTO<>();
        response.setStatusMsg("error");
        response.setStatusCode(500);
        return response;
    }

    public void setPage(List<T> data, long total, int pageNo, int pageSize) {
        if (this.data == null) {
            this.data = new PagePayload<>();
        }
        this.data.setTotal(total);
        this.data.setPageNum(pageNo);
        this.data.setPageSize(pageSize);
        this.data.setList(data);
    }

    public PageResponseDTO setPage(List<T> data, Page page) {
        if(Objects.isNull(page)){
            page = new Page();
        }
        int total = page.getTotal();
        int pageNo = page.getCurrent();
        int pageSize = page.getSize();
        if (this.data == null) {
            this.data = new PagePayload<>();
        }
        this.data.setTotal(total);
        this.data.setPageNum(pageNo);
        this.data.setPageSize(pageSize);
        this.data.setList(data);
        return this;
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
        private int pageNum = 1;

        @ApiModelProperty(value = "每页显示记录数", example = "10")
        @JSONField(ordinal = 3)
        private int pageSize = 10;

        @ApiModelProperty(value = "总页数", example = "10")
        @JSONField(ordinal = 4)
        private int pages;

        @ApiModelProperty(value = "数据内容")
        @JSONField(ordinal = 5)
        private List<T> list;

        public long getPages() {
            if (Objects.isNull(pageSize) || pageSize == 0) {
                return 0L;
            }
            return (total + pageSize - 1) / pageSize;
        }

    }
}
