package com.geektcp.alpha.common.spring.jpa;

import alpha.common.base.constant.login.LoginContext;
import com.geektcp.alpha.common.spring.model.BasePo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author tanghaiyang on 2018/12/25.
 */
@NoRepositoryBean
public class JpaBase {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    protected JPAQueryFactory jpa;

    @PostConstruct
    public void initFactory() {
        jpa = new JPAQueryFactory(entityManager);
    }

    protected <T extends BasePo> T setUpdateBy(T bean) {
        String userId = LoginContext.getUserIdStr();
        if (StringUtils.isBlank(bean.getCreatedById())) {
            bean.setCreatedById(userId);
        }
        bean.setUpdateById(userId);
        return bean;
    }
}
