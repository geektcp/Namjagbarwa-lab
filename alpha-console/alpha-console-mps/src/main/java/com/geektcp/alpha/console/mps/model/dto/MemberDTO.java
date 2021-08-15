package com.geektcp.alpha.console.mps.model.dto;

import com.geektcp.alpha.console.mps.model.entity.Member;
import lombok.Data;

import java.util.List;


@Data
public class MemberDTO extends Member {
    /**
     * 角色ID
     */
    private List<Integer> role;

    /**
     * 新密码
     */
    private String newpassword1;
}
