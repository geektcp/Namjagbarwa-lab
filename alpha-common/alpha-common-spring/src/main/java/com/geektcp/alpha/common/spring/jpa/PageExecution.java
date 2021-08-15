package com.geektcp.alpha.common.spring.jpa;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;

public final class PageExecution {

    private PageExecution() {
    }

    public static Page getPage(JPAQuery jpaQuery, Pageable pageable) {
        return PageableExecutionUtils.getPage(jpaQuery.fetch(), pageable, new PageableExecutionUtils.TotalSupplier() {
            public long get() {
                return jpaQuery.fetchCount();
            }
        });
    }
}
