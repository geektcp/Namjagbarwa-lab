package com.geektcp.alpha.spring.querydsl.service.model.po;

import com.geektcp.alpha.spring.querydsl.service.model.suo.SmGroupSuo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Created by HaiyangWork on 2018/12/24.
 */
@Data
@Entity
@Table(name = "sm_group")
@NoArgsConstructor
public class SmGroupPo extends BasePo {
    @NotNull
    @Column(name = "name", length = 50)
    private String name;

    @NotNull
    @Column(name = "type", length = 50)
    private String type;

    @NotNull
    @Column(name = "enabled_flag", length = 10)
    private char enabledFlag;

    public SmGroupPo(SmGroupSuo suo) {
        this.id = Objects.isNull(suo.getId()) ? 0 : suo.getId();
        this.name = suo.getName();
        this.type = suo.getType();
        this.enabledFlag = suo.getEnabledFlag();
    }

}
