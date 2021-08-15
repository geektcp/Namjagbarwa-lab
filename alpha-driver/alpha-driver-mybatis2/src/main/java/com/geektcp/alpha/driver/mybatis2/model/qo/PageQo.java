package com.geektcp.alpha.driver.mybatis2.model.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haiyang on 3/9/20 6:52 PM.
 */
@Data
@NoArgsConstructor
@ApiModel(value = "分页查询参数", description = "用于指定分页查询相关参数")
public class PageQo {

    @Min(value = 1)
    @ApiModelProperty(value = "当前页,从1开始", example = "1")
    protected int pageNo = 1;

    @Min(value = 2)
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
