package com.geektcp.alpha.spring.querydsl.service.service.impl;

import com.geektcp.alpha.common.spring.jpa.JpaBase;
import com.geektcp.alpha.common.spring.model.PageQo;
import com.geektcp.alpha.common.spring.model.PageResponse;
import com.geektcp.alpha.common.spring.model.TResponse;
import com.geektcp.alpha.spring.querydsl.service.constant.ServiceStatus;
import com.geektcp.alpha.spring.querydsl.service.dao.SmGroupDao;
import com.geektcp.alpha.spring.querydsl.service.model.po.QSmGroupPo;
import com.geektcp.alpha.spring.querydsl.service.model.po.SmGroupPo;
import com.geektcp.alpha.spring.querydsl.service.model.qo.SmGroupQo;
import com.geektcp.alpha.spring.querydsl.service.model.suo.SmGroupSuo;
import com.geektcp.alpha.spring.querydsl.service.model.vo.SmGroupVo;
import com.geektcp.alpha.spring.querydsl.service.service.SmGroupService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import alpha.common.base.exception.UnexpectedStatusException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by HaiyangWork on 2018/12/22.
 */
@Slf4j
@Service("smGroupService")
public class SmGroupServiceImpl extends JpaBase implements SmGroupService {

    @Autowired
    private SmGroupDao smGroupDao;

    public TResponse saveOrUpdate(SmGroupSuo suo) {
        try {
            SmGroupPo smGroupPo = new SmGroupPo(suo);
            smGroupDao.save(smGroupPo);
        } catch (Exception e) {
            throw new UnexpectedStatusException(ServiceStatus.GROUP_SAVE_ERROR, e);
        }
        return TResponse.success();
    }

    public TResponse delete(SmGroupSuo suo) {
        try {
            smGroupDao.delete(suo.getId());
        } catch (Exception e) {
            throw new UnexpectedStatusException(ServiceStatus.GROUP_DELETE_ERROR, e);
        }
        return TResponse.success();
    }

    public TResponse find(SmGroupQo qo) {
        try {
            QSmGroupPo table = QSmGroupPo.smGroupPo;
            BooleanBuilder builder = new BooleanBuilder();
            if (StringUtils.isNotEmpty(qo.getName())) {
                builder = builder.and(table.name.like(qo.getName()));
            }
            Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "updatedDt"));
            Iterable<SmGroupPo> results = smGroupDao.findAll(builder, sort);
            List<SmGroupVo> rows = new ArrayList<>();
            results.forEach((smGroupPo) -> {
                rows.add(new SmGroupVo(smGroupPo));
            });
            return TResponse.success(rows);
        } catch (Exception e) {
            throw new UnexpectedStatusException(ServiceStatus.GROUP_FIND_ERROR, e);
        }
    }

    public PageResponse findPage(SmGroupQo qo) {
        QSmGroupPo table = QSmGroupPo.smGroupPo;
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.isNotEmpty(qo.getName())) {
            builder = builder.and(table.name.like(qo.getName()));
        }
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "updatedDt"));
        PageQo pageQo = qo.getPage();
        PageRequest pageRequest = new PageRequest(pageQo.getPageNo(), pageQo.getPageSize(), sort);
        Page<SmGroupPo> page = smGroupDao.findAll(builder, pageRequest);

        return PageResponse.success(page.getContent(), page.getTotalPages(), pageQo);
    }

    public TResponse search(SmGroupQo qo) {
        QSmGroupPo table = QSmGroupPo.smGroupPo;
//        QSmGroupPo table2 = QSmGroupPo.smGroupPo;
//        QSmGroupPo table3 = QSmGroupPo.smGroupPo;
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.isNotEmpty(qo.getName())) {
            builder = builder.and(table.name.like(qo.getName()));
        }

        //        .from(table2)
//        .leftJoin(table3)
//        .on(builder)
        JPAQuery<Tuple> jpaQuery = jpa.select(table.id, table.name, table.createdBy, table.createdDt, table.type)
                .from(table)
                .where(builder)
                .orderBy(table.updatedDt.desc())
                .offset(qo.getPage().getPageNo())
                .limit(qo.getPage().getPageSize());

        QueryResults<Tuple> results = jpaQuery.fetchResults();
//
//        List<SmGroupVo> rows = new ArrayList<>();
//        results.getResults().forEach((smGroupPo) -> {rows.add(new SmGroupVo(smGroupPo));});
//        return TResponse.success(rows);


        System.out.println(results.getResults());
//        System.out.println(TResponse.success(results.getResults()));
        return TResponse.success(results.getResults());

    }

}
