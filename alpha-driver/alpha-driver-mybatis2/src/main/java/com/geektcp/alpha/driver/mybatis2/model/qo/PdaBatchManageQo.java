package com.geektcp.alpha.driver.mybatis2.model.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author haiyang on 3/20/20 10:02 AM.
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "批次号管理PdaBatchQo", description = "批次号管理")
public class PdaBatchManageQo extends PageQoBase {

    @ApiModelProperty(value = "批次号主键ID")
    private Long id;

    @ApiModelProperty(value = "批次号")
    private String batchNo;

    @ApiModelProperty(value = "操作站名称|生成批次号接口|站点信息")
    private String stationSrcName;

    @ApiModelProperty(value = "操作站代码|生成批次号接口|站点信息")
    private Long stationSrcCode;

    @ApiModelProperty(value = "操作站物流公司id|生成批次号接口|站点信息")
    private String stationSrcShipperId;

    @ApiModelProperty(value = "流向站名称|生成批次号接口|站点信息")
    private String stationDstName;

    @ApiModelProperty(value = "流向站代码|生成批次号接口|站点信息")
    private Long stationDstCode;

    @ApiModelProperty(value = "流向站物流公司id|生成批次号接口|站点信息")
    private String stationDstShipperId;

    @ApiModelProperty(value = "班次|生成批次号接口")
    private String shiftsNo;

    @ApiModelProperty(value = "流水号|生成批次号接口, 1-9")
    private String pipelineNo;

    @ApiModelProperty(value = "小马单号|app")
    private String logisticNo;

    @ApiModelProperty(value = "车辆码|app")
    private String vehicleNo;

    @ApiModelProperty(value = "封签号|app")
    private String labelNo;

    @ApiModelProperty(value = "批次号ID,list")
    private List<String> batchNoList;

    @ApiModelProperty(value = "封签号list|app")
    private List<String> labelNoList;

}
