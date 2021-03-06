package com.geektcp.alpha.console.mps.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geektcp.alpha.console.common.core.constants.SecurityConstants;
import com.geektcp.alpha.console.common.core.utils.R;
import com.geektcp.alpha.console.mps.mapper.MemberMapper;
import com.geektcp.alpha.console.mps.mapper.MemberRoleMapper;
import com.geektcp.alpha.console.mps.model.dto.MemberInfo;
import com.geektcp.alpha.console.mps.model.entity.Member;
import com.geektcp.alpha.console.mps.model.entity.MemberRole;
import com.geektcp.alpha.console.mps.model.vo.MemberVO;
import com.geektcp.alpha.console.mps.model.vo.PermissionVO;
import com.geektcp.alpha.console.mps.model.vo.RoleVO;
import com.geektcp.alpha.console.mps.service.PermissionService;
import com.geektcp.alpha.console.mps.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
    private static final PasswordEncoder ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private MemberMapper memberMapper;
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
    @Resource
    private MemberRoleMapper memberRoleMapper;

    @Autowired
    private PermissionService permissionService;

    @Override
    public MemberInfo findMemberInfo(MemberVO memberVO) {
        Member condition = new Member();
        condition.setMembername(memberVO.getMembername());
        Member member = this.getOne(new QueryWrapper<>(condition));

        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMember(member);
        //??????????????????
        List<RoleVO> roleList = memberVO.getRoleVoList();
        List<String> roleNames = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(roleList)) {
            for (RoleVO role : roleList) {
                if (!StringUtils.equals(SecurityConstants.BASE_ROLE, role.getRoleName())) {
                    roleNames.add(role.getRoleName());
                }
            }
        }
        String[] roles = roleNames.toArray(new String[roleNames.size()]);
        memberInfo.setRoles(roles);

        //?????????????????????permission???
        Set<PermissionVO> permissionVoSet = new HashSet<>();
        for (String role : roles) {
            List<PermissionVO> permissionVos = permissionService.findPermissionByRoleName(role);
            permissionVoSet.addAll(permissionVos);
        }
        Set<String> permissions = new HashSet<>();
        for (PermissionVO permissionVo : permissionVoSet) {
            if (StringUtils.isNotEmpty(permissionVo.getPermission())) {
                String permission = permissionVo.getPermission();
                permissions.add(permission);
            }
        }
        memberInfo.setPermissions(permissions.toArray(new String[permissions.size()]));
        return memberInfo;
    }




    @Override
    public void insertMember(Member member, String[] roleIds) {

        member.setCreateTime(new Date());
        member.setPassword(ENCODER.encode(member.getPassword()));
        //????????????
        memberMapper.insert(member);
        //????????????
        if(ArrayUtils.isNotEmpty(roleIds)){
            for(String rid : roleIds){
                MemberRole memberRole = new MemberRole();
                memberRole.setMemberId(member.getMemberId());
                memberRole.setRoleId(new Integer(rid));
                memberRoleMapper.insert(memberRole);
            }
        }
    }

    @Override
    public void updateMember(Member member, String[] roleIds) {
        member.setPassword(null);
        //????????????
        memberMapper.updateById(member);
        //??????????????????
        memberRoleMapper.delete(new QueryWrapper<MemberRole>().eq("member_id",member.getMemberId()));
        //??????????????????
        if(ArrayUtils.isNotEmpty(roleIds)){
            for(String rid : roleIds){
                MemberRole memberRole = new MemberRole();
                memberRole.setMemberId(member.getMemberId());
                memberRole.setRoleId(new Integer(rid));
                memberRoleMapper.insert(memberRole);
            }
        }

    }

    @Override
    public Member getById(Serializable id) {
        Long lid = Long.valueOf(id.toString().replace(",",""));
        return super.getById(lid);
    }

    /**
     * ???????????????????????????randomStr??????
     *
     * @param randomStr ???????????????
     * @param imageCode ???????????????
     */
    @Override
    public void saveImageCode(String randomStr, String imageCode) {
        redisTemplate.opsForValue().set(SecurityConstants.DEFAULT_CODE_KEY + randomStr, imageCode, SecurityConstants.DEFAULT_IMAGE_EXPIRE, TimeUnit.SECONDS);
    }

    /**
     * ???????????????
     * <p>
     * 1. ??????redis ???????????? 60S???????????????
     * 2. ???????????? ???????????????????????? ? false :??????4?????????  ?????????-?????????
     * 3. ??????????????????-???????????????
     * 4. ??????redis
     *
     * @param mobile ?????????
     * @return true???false
     */
    @Override
    public R<Boolean> sendSmsCode(String mobile) {
        Object tempCode = redisTemplate.opsForValue().get(SecurityConstants.DEFAULT_CODE_KEY + mobile);
        if (tempCode != null) {
            log.error("??????:{}??????????????????{}", mobile, tempCode);
            return new R<>(false, "?????????????????????????????????????????????");
        }

        Member params = new Member();
        params.setPhone(mobile);
        List<Member> memberList = this.list(new QueryWrapper<Member>(params));

        if (CollectionUtils.isEmpty(memberList)) {
            log.error("?????????????????????{}??????????????????", mobile);
            return new R<>(false, "??????????????????");
        }

        String code = RandomStringUtils.random(4);
        JSONObject contextJson = new JSONObject();
        contextJson.put("code", code);
        contextJson.put("product", "Alpha4Cloud");
        log.info("?????????????????????????????? -> ?????????:{} -> ????????????{}", mobile, code);
//        rabbitTemplate.convertAndSend(MqQueueConstant.MOBILE_CODE_QUEUE,
//                new MobileMsgTemplate(
//                        mobile,
//                        contextJson.toJSONString(),
//                        CommonConstant.ALIYUN_SMS,
//                        EnumSmsChannelTemplate.LOGIN_NAME_LOGIN.getSignName(),
//                        EnumSmsChannelTemplate.LOGIN_NAME_LOGIN.getTemplate()
//                ));
        redisTemplate.opsForValue().set(SecurityConstants.DEFAULT_CODE_KEY + mobile, code, SecurityConstants.DEFAULT_IMAGE_EXPIRE, TimeUnit.SECONDS);
        return new R<>(true);
    }



}
