package com.geektcp.alpha.driver.mybatis2.model.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pda_batch_manage")
@NoArgsConstructor
public class PdaBatchManageEntity extends Model<PdaBatchManageEntity> {

    @TableId
    private Long id;
    private String batchNo;
    private String stationSrcName;
    private Long stationSrcCode;
    private String stationSrcShipperId;

    private String stationDstName;
    private Long stationDstCode;
    private String stationDstShipperId;

    private String shiftsNo;
    private String pipelineNo;

    private Long isEnable;
    private String createdById;
    private Date createdDate;
    private String updatedById;
    private Date updatedDate;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
