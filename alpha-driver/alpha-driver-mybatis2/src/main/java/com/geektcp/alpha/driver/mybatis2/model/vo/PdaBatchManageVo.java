package com.geektcp.alpha.driver.mybatis2.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author haiyang on 3/20/20 10:07 AM.
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "批次号管理PdaBatchVo", description = "批次号管理")
@NoArgsConstructor
public class PdaBatchManageVo {

    @ApiModelProperty(value = "批次号表主键id")
    private Long id;

    @ApiModelProperty(value = "批次号ID")
    private String batchNo;

    @ApiModelProperty(value = "操作站名称")
    private String stationSrcName;

    @ApiModelProperty(value = "操作站代码")
    private Long stationSrcCode;

    @ApiModelProperty(value = "流向站名称")
    private String stationDstName;

    @ApiModelProperty(value = "流向站代码")
    private Long stationDstCode;

    @ApiModelProperty(value = "班次")
    private String shiftsNo;

    @ApiModelProperty(value = "流水号")
    private String pipelineNo;

    @ApiModelProperty(value = "物流公司ID")
    private String stationSrcShipperId;

    @ApiModelProperty(value = "物流公司ID")
    private String stationDstShipperId;

    @ApiModelProperty(value = "物流公司名称")
    private String logisticsCompanyName;

    @ApiModelProperty(value = "车牌号")
    private String vehicleNo;

    @ApiModelProperty(value = "发货人|app")
    private String senderName;

    @ApiModelProperty(value = "收货人|app")
    private String receiverName;



}
