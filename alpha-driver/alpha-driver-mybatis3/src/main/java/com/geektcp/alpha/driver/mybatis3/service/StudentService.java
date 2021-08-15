package com.geektcp.alpha.driver.mybatis3.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.geektcp.alpha.driver.mybatis3.model.Student;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tanghaiyang
 * @since 2018-08-31
 */
public interface StudentService extends IService<Student> {

    boolean addStudent(Student student);

    Student queryStudentByIdCardId(Long idCardId);

    IPage<Student> queryStudentByPage(Long currentPage);

    void test1();

    void test2();

    void test3();

    void test4();

    void test5();

    void test6();

    void test7();

    //------------------------------------------ 封装接口

    /**
     * 查询所有数据
     * @return List<Student>
     */
    List<Student> findAll();

    /**
     * 查询部分数据
     * @return List<Student>
     */
    List<Student> findList();

    /**
     * 查询一条数据
     * @return Student
     */
    Student findOne();

    /**
     * 根据主键ID查询数据
     * @param id 主键ID，为null，返回null
     * @return Student
     */
    Student findById(Long id);

    //------------------------------------------

    List<Student> findByNameAndAge(String name, Integer age);
}
