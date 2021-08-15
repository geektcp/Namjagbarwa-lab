package com.geektcp.alpha.driver.mybatis2.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.geektcp.alpha.driver.mybatis2.model.po.StudentPo;
import com.geektcp.alpha.driver.mybatis2.dao.StudentDao;
import com.geektcp.alpha.driver.mybatis2.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tanghaiyang
 * @since 2018-08-31
 */
@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentDao, StudentPo> implements StudentService {

    @Override
    public boolean addStudent(StudentPo studentPo) {

        if (queryStudentByIdCardId(studentPo.getIdcardId()) == null) {
            return this.insert(studentPo);
        }

        return true;
    }

    @Override
    public StudentPo queryStudentByIdCardId(Long idCardId) {
        Wrapper<StudentPo> queryWrapper = new EntityWrapper<>();
        queryWrapper.eq(StudentPo.IDCARD_ID, idCardId);
        List<StudentPo> studentPoList = this.selectList(queryWrapper);
        if (studentPoList == null || studentPoList.isEmpty()) {
            return null;
        }
        if (studentPoList.size() > 1) {
            log.error("queryStudentByIdCardId 有多个结果，idCardId={}", idCardId);
        }
        return studentPoList.get(0);
    }



    // 通过名字进行筛选
    @Override
    public void test1() {
        Wrapper<StudentPo> queryWrapper = new EntityWrapper<>();
        queryWrapper.eq("name", "冯文议");
        List<StudentPo> studentPoList = this.selectList(queryWrapper);
        for (StudentPo studentPo : studentPoList)
            log.info(JSON.toJSONString(studentPo));
    }



    @Override
    public void test2() {
        Wrapper<StudentPo> queryWrapper = new EntityWrapper<>();
        queryWrapper.eq("name", "冯文议")
//                .eq(StudentPo::getAge, 26)
                .eq("age", 25);
        List<StudentPo> studentPoList = this.selectList(queryWrapper);
        for (StudentPo studentPo : studentPoList)
            log.info(JSON.toJSONString(studentPo));
    }

    @Override
    public void test3() {
        Wrapper<StudentPo> queryWrapper = new EntityWrapper<>();
        queryWrapper.and();
        queryWrapper.eq("name", "冯文议");
        queryWrapper.eq("age", 26);

        List<StudentPo> studentPoList = this.selectList(queryWrapper);
        for (StudentPo studentPo : studentPoList) {
            log.info(JSON.toJSONString(studentPo));
        }
    }

    @Override
    public void test4() {
        Wrapper<StudentPo> queryWrapper = new EntityWrapper<>();
        queryWrapper.or();
        queryWrapper.eq("name", "冯文议");
        queryWrapper.or("age", "1");
        List<StudentPo> studentPoList = selectList(queryWrapper);
        for (StudentPo studentPo : studentPoList)
            log.info(JSON.toJSONString(studentPo));
    }


    @Resource
    StudentDao studentDao;

    @Override
    public void selectAll() {
        Page page = new Page();
        List<StudentPo> studentPoList = studentDao.selectAll(page);
        for (StudentPo studentPo : studentPoList)
            log.info(JSON.toJSONString(studentPo));
    }



    @Override
    public StudentPo findOne() {
        return selectOne(null);
    }

    @Override
    public StudentPo findById(Long id) {
        return selectById(id);
    }

    @Override
    public List<StudentPo> findByNameAndAge(String name, Integer age) {
        Wrapper<StudentPo> lambdaQueryWrapper = new EntityWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            lambdaQueryWrapper.eq("age", name);
        }

        if (age != null) {
            lambdaQueryWrapper.eq("age", age);
        }

        List<StudentPo> studentPoList = selectList(lambdaQueryWrapper);
        for (StudentPo studentPo : studentPoList) {
            log.info(JSON.toJSONString(studentPo));
        }
        return studentPoList;
    }

    @Override
    public List<StudentPo> findList() {
        return null;
    }
}
