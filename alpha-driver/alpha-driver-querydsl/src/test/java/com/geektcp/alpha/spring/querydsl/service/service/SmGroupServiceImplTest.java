package com.geektcp.alpha.spring.querydsl.service.service;

import com.geektcp.alpha.spring.querydsl.service.dao.SmGroupDao;
import com.geektcp.alpha.spring.querydsl.service.model.po.QSmGroupPo;
import alpha.common.base.jpa.JpaBase;
import com.geektcp.alpha.spring.querydsl.service.model.po.SmGroupPo;
import com.geektcp.alpha.spring.querydsl.service.model.qo.SmGroupQo;
import com.geektcp.alpha.spring.querydsl.service.util.ConditionUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import alpha.common.base.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanghaiyang on 2018/12/24.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "")
@EnableJpaRepositories({"com.geektcp.gap"})
@EntityScan({"com.geektcp.gap"})
@ComponentScan({"com.geektcp.gap"})
public class SmGroupServiceImplTest extends JpaBase {

    @Autowired
    private SmGroupDao smGroupDao;

    @Test
    public void findByName() {
        log.info("start ===== findByName");

        String name = "tee";
        Object ret = smGroupDao.findByName(name);

        log.info(ret.toString());
    }

    @Test
    public void findAll() {
        log.info("start ===== findAll");

        String name = "tee";
        String field = "name";

//        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = new PageRequest(0, 2);

        Specification<SmGroupPo> specification = buildSpecification(field, name);
        Page<SmGroupPo> list = smGroupDao.findAll(specification, pageable);
        List<SmGroupPo> ret = list.getContent();

        log.info("+++++++++++++++++");
        log.info(ret.toString());
        log.info("-------------------------");
    }


    private Specification<SmGroupPo> buildSpecification(String field, String name) {
        return new Specification<SmGroupPo>() {
            @Override
            public Predicate toPredicate(Root<SmGroupPo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(name)) {
                    Predicate likeNickName = cb.like(root.get(field), "%" + name + "%");
                    predicates.add(likeNickName);
                }
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };
    }


    /*
    * select方式返回的是元组，不是bena的list，相对于findAll这类自带接口不太好转换
    * */
    @Test
    public void search() {
        SmGroupQo qo = new SmGroupQo();
        qo.setName("tee");
        QSmGroupPo table = QSmGroupPo.smGroupPo;
//        QSmGroupPo table2 = QSmGroupPo.smGroupPo;
//        QSmGroupPo table3 = QSmGroupPo.smGroupPo;
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.isNotEmpty(qo.getName())) {
            builder = builder.and(table.name.like(ConditionUtil.wrap(qo.getName())));
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

        System.out.println(Response.success(results.getResults()));

    }
}
