package com.geektcp.alpha.spring.shiro.common.validator;

import com.geektcp.alpha.spring.shiro.annotation.IsMobile;
import com.geektcp.alpha.spring.shiro.common.entity.Regexp;
import com.geektcp.alpha.spring.shiro.utils.AlphaUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验是否为合法的手机号码
 *
 * @author tanghaiyang
 */
public class MobileValidator implements ConstraintValidator<IsMobile, String> {

    @Override
    public void initialize(IsMobile isMobile) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isBlank(s)) {
                return true;
            } else {
                String regex = Regexp.MOBILE_REG;
                return AlphaUtil.match(regex, s);
            }
        } catch (Exception e) {
            return false;
        }
    }
}
