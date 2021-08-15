package com.geektcp.alpha.sys.auth.shiro.util;

import com.geektcp.alpha.sys.auth.shiro.model.UserRoleVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.Objects;

/**
 * @author tanghaiyang on 2018/1/4.
 */
public class ShiroUtils {

    public static Long getUserID() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            if (Objects.isNull(currentUser)) {
                return null;
            }
            UserRoleVo roleVo = (UserRoleVo) currentUser.getPrincipal();
            if (Objects.isNull(roleVo)){
                return null;
            }
            return roleVo.getId();
        } catch (Exception e) {
            // do noting
        }
        return null;
    }
}
