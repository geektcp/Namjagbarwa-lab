package com.geektcp.alpha.spring.aop.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Slf4j
@Aspect
@Component
public class RefreshUserInfoAspect {

    @Pointcut("@annotation(RefreshUserInfo)")
    public void aspect() {
        // do nothing
    }

    @AfterReturning(pointcut = "aspect()")
    public void doAfterReturning(final JoinPoint joinPoint) {
        log.info("Aspect method name|{}:{}", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
    }


}
