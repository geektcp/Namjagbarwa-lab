package com.geektcp.alpha.spring.security.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by tanghaiyang
 * 23:10 2018/9/6
 */
@Data
@NoArgsConstructor
public class UserVo {

    private String userName;
    private String userDesc;
    private List<String> roleCodes;

    public UserVo(String userDesc){
        this.userDesc = userDesc;
    }

}
