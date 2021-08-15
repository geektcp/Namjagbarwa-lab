package com.geektcp.alpha.common.spring.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanghaiyang on 2018/5/3.
 */
@Data
@NoArgsConstructor
@ApiModel(value = "分页查询参数", description = "用于指定分页查询相关参数")
public class PageQo implements Serializable {

    @Min(value = 0)
    @ApiModelProperty(value = "当前页", example = "0")
    protected int pageNo = 0;

    @Min(value = 5)
    @ApiModelProperty(value = "每页显示大小", example = "10")
    protected int pageSize = 10;

    @ApiModelProperty(value = "排序字段列表")
    protected List<SortOrder> sortOrders = new ArrayList<>();

    public void addSortOrder(String property, Sort.Direction direction) {
        this.sortOrders.add(new SortOrder(property, direction));
    }

    public PageQo(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
