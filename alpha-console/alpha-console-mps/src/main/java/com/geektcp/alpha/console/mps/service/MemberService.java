package com.geektcp.alpha.console.mps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geektcp.alpha.console.common.core.utils.R;
import com.geektcp.alpha.console.mps.model.dto.MemberInfo;
import com.geektcp.alpha.console.mps.model.entity.Member;
import com.geektcp.alpha.console.mps.model.vo.MemberVO;


public interface MemberService extends IService<Member> {


    /**
     * 查询用户信息
     *
     * @param memberVO 角色名
     * @return MemberInfo
     */
    MemberInfo findMemberInfo(MemberVO memberVO);

    /**
     * 保存验证码
     *  @param randomStr 随机串
     * @param imageCode 验证码*/
    void saveImageCode(String randomStr, String imageCode);


    /**
     * 发送验证码
     * @param mobile 手机号
     * @return R
     */
    R<Boolean> sendSmsCode(String mobile);


    /**
     * 保存用户
     */
    void insertMember(Member member, String[] roleIds);
    /**
     * 更新用户
     */
    void updateMember(Member member, String[] roleIds);
}
