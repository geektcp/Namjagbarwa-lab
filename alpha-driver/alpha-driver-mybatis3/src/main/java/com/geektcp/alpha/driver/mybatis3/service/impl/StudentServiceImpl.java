package com.geektcp.alpha.driver.mybatis3.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geektcp.alpha.driver.mybatis3.config.ConstantConfig;
import com.geektcp.alpha.driver.mybatis3.model.Student;
import com.geektcp.alpha.driver.mybatis3.dao.StudentDao;
import com.geektcp.alpha.driver.mybatis3.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {

    @Override
    public boolean addStudent(Student student) {

        if (queryStudentByIdCardId(student.getIdcardId()) == null) {
            return save(student);
        }

        return true;
    }

    @Override
    public Student queryStudentByIdCardId(Long idCardId) {
        QueryWrapper<Student> queryWrapper =
                new QueryWrapper<Student>()
                        .eq(Student.IDCARD_ID, idCardId);
        List<Student> studentList = list(queryWrapper);

        if (studentList == null || studentList.isEmpty()) {
            return null;
        }

        if (studentList.size() > 1) {
            log.error("queryStudentByIdCardId 有多个结果，idCardId={}", idCardId);
        }
        return studentList.get(0);
    }

    // 简单分页查询
    public IPage<Student> queryStudentByPage(Long currentPage) {
        return page(new Page<>(currentPage, ConstantConfig.PAGE_SITE), null);
    }

    // 通过名字进行筛选
    @Override
    public void test1() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Student::getName, "冯文议");
        List<Student> studentList = list(queryWrapper);
        for (Student student : studentList)
            log.info(JSON.toJSONString(student));
    }

    @Override
    public void test2() {

        IPage<Student> page = page(
                new Page<>(1, 2),
                null);

        log.info(JSON.toJSONString(page));
    }

    @Override
    public void test3() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Student::getName, "冯文议")
//                .eq(Student::getAge, 26)
                .eq(Student::getAge, 25);
        List<Student> studentList = list(queryWrapper);
        for (Student student : studentList)
            log.info(JSON.toJSONString(student));
    }

    @Override
    public void test4() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .and(obj ->
                        obj.eq(Student::getName, "冯文议")
                                .eq(Student::getAge, 26));

        List<Student> studentList = list(queryWrapper);
        for (Student student : studentList)
            log.info(JSON.toJSONString(student));
    }

    @Override
    public void test5() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .or(obj1 -> obj1.eq(Student::getName, "冯文议"))
                .or(obj2 -> obj2.eq(Student::getName, "1"));
        List<Student> studentList = list(queryWrapper);
        for (Student student : studentList)
            log.info(JSON.toJSONString(student));
    }

    @Override
    public void test6() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Student::getName, "冯文议")
                .or()
                .eq(Student::getName, "1");
        List<Student> studentList = list(queryWrapper);
        for (Student student : studentList)
            log.info(JSON.toJSONString(student));
    }

    @Resource
    StudentDao studentDao;

    @Override
    public void test7() {
        List<Student> studentList = studentDao.selectAll();
        for (Student student : studentList)
            log.info(JSON.toJSONString(student));
    }

    @Override
    public List<Student> findAll() {
        return list(null);
    }

    @Override
    public List<Student> findList() {
        return list(null);
    }

    @Override
    public Student findOne() {
        return getOne(null);
    }

    @Override
    public Student findById(Long id) {
        return getById(id);
    }

    @Override
    public List<Student> findByNameAndAge(String name, Integer age) {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            lambdaQueryWrapper.eq(Student::getName, name);
        }

        if (age != null) {
            lambdaQueryWrapper.eq(Student::getAge, age);
        }

        List<Student> studentList = list(lambdaQueryWrapper);
        for (Student student : studentList)
            log.info(JSON.toJSONString(student));
        return null;
    }

    //--------------------------------------------------test end
}
