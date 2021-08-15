package com.geektcp.alpha.driver.mybatis2.service;

import com.geektcp.alpha.driver.mybatis2.model.po.StudentPo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tanghaiyang
 * @since 2018-08-31
 */
public interface StudentService {

    boolean addStudent(StudentPo studentPo);

    StudentPo queryStudentByIdCardId(Long idCardId);


    void test1();

    void test2();

    void test3();

    void test4();

    void selectAll();


    /**
     * 查询部分数据
     * @return List<StudentPo>
     */
    List<StudentPo> findList();

    /**
     * 查询一条数据
     * @return StudentPo
     */
    StudentPo findOne();

    /**
     * 根据主键ID查询数据
     * @param id 主键ID，为null，返回null
     * @return StudentPo
     */
    StudentPo findById(Long id);

    //------------------------------------------

    List<StudentPo> findByNameAndAge(String name, Integer age);
}
