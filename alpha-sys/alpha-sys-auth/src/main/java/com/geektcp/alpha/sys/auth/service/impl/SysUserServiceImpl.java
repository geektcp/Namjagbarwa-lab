package com.geektcp.alpha.sys.auth.service.impl;


import alpha.common.base.exception.UnexpectedStatusException;
import com.geektcp.alpha.common.spring.jpa.JQL;
import com.geektcp.alpha.common.spring.jpa.JpaBase;
import com.geektcp.alpha.common.spring.model.PageQo;
import com.geektcp.alpha.common.spring.model.PageResponse;
import com.geektcp.alpha.common.spring.model.TResponse;
import com.geektcp.alpha.sys.auth.constant.AuthStatus;
import com.geektcp.alpha.sys.auth.dao.SysUserDao;
import com.geektcp.alpha.sys.auth.dao.SysUserRoleDao;
import com.geektcp.alpha.sys.auth.model.po.QSysUserPo;
import com.geektcp.alpha.sys.auth.model.po.SysUserPo;
import com.geektcp.alpha.sys.auth.model.po.SysUserRolePo;
import com.geektcp.alpha.sys.auth.model.qo.SysUserQo;
import com.geektcp.alpha.sys.auth.model.suo.SysUserSuo;
import com.geektcp.alpha.sys.auth.service.SysUserService;
import com.geektcp.alpha.sys.auth.shiro.util.ShiroUtils;
import com.geektcp.alpha.sys.auth.util.AuthUtils;
import com.querydsl.core.BooleanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author tanghaiyang on 2018/1/4.
 */
@Slf4j
@Service
public class SysUserServiceImpl extends JpaBase implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public PageResponse findPage(SysUserQo qo) {
        try {
            QSysUserPo table = QSysUserPo.sysUserPo;
            BooleanBuilder builder = new BooleanBuilder();
            if (StringUtils.isNotEmpty(qo.getUserNo())) {
                builder = builder.or(table.userNo.like(JQL.likeWrap(qo.getUserNo())));
            }
            Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "updatedDt"));
            PageQo pageQo = qo.getPage();
            PageRequest pageRequest = new PageRequest(pageQo.getPageNo() - 1, pageQo.getPageSize(), sort);
            Page<SysUserPo> page = sysUserDao.findAll(builder, pageRequest);
            return PageResponse.success(page.getContent(), page.getTotalPages(), pageQo);
        } catch (Exception e) {
            throw new UnexpectedStatusException(AuthStatus.USER_FIND_PAGE_ERROR, e);
        }
    }

    @Override
    public boolean existsUser(String userNo) {
        try {
            SysUserPo po = sysUserDao.findByUserNo(userNo);
            return Objects.nonNull(po);
        } catch (Exception e) {
            throw new UnexpectedStatusException(AuthStatus.USER_IS_EXISTS, e);
        }
    }

    @Override
    public TResponse confirmPassword(String password) {
        try {
            SysUserPo userPo = sysUserDao.findOne(ShiroUtils.getUserID());
            if (null == userPo) {
                throw new UnexpectedStatusException(AuthStatus.USER_NOT_EXISTS);
            }
            String dbPassword = userPo.getPassword();
            boolean success = AuthUtils.validPassword(password, dbPassword);
            if (success) {
                return TResponse.success();
            }
        } catch (UnexpectedStatusException e) {
            log.error(e.getMessage());
        }
        return TResponse.error();
    }

    @Override
    public TResponse detail(){
        return null;
    }

    @Override
    public TResponse save(SysUserSuo suo) {
        try {
            Long roleId = suo.getRoleId();
            SysUserPo po = new SysUserPo();
            po.update(suo, true);
            SysUserPo poUpdated = this.setUpdateBy(po);
            SysUserPo result = sysUserDao.save(poUpdated);
            if(Objects.nonNull(result)) {
                Long userId = result.getId();
                SysUserRolePo sysUserRolePo = new SysUserRolePo();
                sysUserRolePo.setUserId(userId);
                sysUserRolePo.setRoleId(roleId);
                sysUserRoleDao.save(sysUserRolePo);
            }
        } catch (Exception e) {
            throw new UnexpectedStatusException(AuthStatus.USER_SAVE_ERROR, e);
        }
        return TResponse.success();
    }

    @Override
    public TResponse update(SysUserSuo suo) {
        try {
            Long id = suo.getId();
            if(Objects.isNull(id)){
                throw new UnexpectedStatusException(AuthStatus.USER_UPDATE_ERROR);
            }
            SysUserPo record = sysUserDao.findById(id);
            if(Objects.nonNull(record)) {
                record.update(suo, false);
                SysUserPo poUpdated = this.setUpdateBy(record);
                sysUserDao.save(poUpdated);
            }
        } catch (Exception e) {
            throw new UnexpectedStatusException(AuthStatus.USER_SAVE_ERROR, e);
        }
        return TResponse.success();
    }

    @Override
    public TResponse delete(SysUserSuo suo) {
        try {
            Long userId = suo.getId();
            sysUserDao.delete(userId);
        } catch (Exception e) {
            throw new UnexpectedStatusException(AuthStatus.USER_DELETE_ERROR, e);
        }
        return TResponse.success();
    }

    @Override
    public TResponse updatePassword(SysUserSuo suo) {
        String userNo = suo.getUserNo();
        if(Objects.isNull(userNo)){
            throw new UnexpectedStatusException(AuthStatus.USER_UPDATE_ERROR);
        }
        SysUserPo record = sysUserDao.findByUserNo(userNo);
        String encryptedPwd = record.getPassword();
        String passwordOld = suo.getPasswordOld();
        if(!AuthUtils.validPassword(passwordOld, encryptedPwd)) {
            throw new UnexpectedStatusException(AuthStatus.USER_RESET_PSW_WRONG);
        }
        try {
            record.setPassword(AuthUtils.getEncryptedPwd(suo.getPassword()));
            SysUserPo poUpdated = this.setUpdateBy(record);
            sysUserDao.save(poUpdated);
        } catch (Exception e) {
            throw new UnexpectedStatusException(AuthStatus.USER_RESET_ERROR, e);
        }
        return TResponse.success();
    }

}
