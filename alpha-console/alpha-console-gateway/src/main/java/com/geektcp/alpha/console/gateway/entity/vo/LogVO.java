package com.geektcp.alpha.console.gateway.entity.vo;

import com.geektcp.alpha.console.gateway.entity.SysLog;
import lombok.Data;

import java.io.Serializable;

@Data
public class LogVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private SysLog sysLog;
    private String username;
}
