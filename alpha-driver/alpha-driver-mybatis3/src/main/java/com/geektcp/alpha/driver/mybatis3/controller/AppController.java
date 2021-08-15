package com.geektcp.alpha.driver.mybatis3.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fengwenyi.api_result.helper.ResultHelper;
import com.fengwenyi.api_result.model.ResultModel;
import com.geektcp.alpha.driver.mybatis3.business.AppBusiness;
import com.geektcp.alpha.driver.mybatis3.enums.GenderEnum;
import com.geektcp.alpha.driver.mybatis3.model.City;
import com.geektcp.alpha.driver.mybatis3.model.Card;
import com.geektcp.alpha.driver.mybatis3.model.Student;
import com.geektcp.alpha.driver.mybatis3.service.CityService;
import com.geektcp.alpha.driver.mybatis3.service.StudentService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测试
 *
 * @author tanghaiyang
 * @since 2018-09-01
 */
@RestController
@RequestMapping(value = "/app", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "App 测试示例")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AppController {

    private CityService cityService;

    private AppBusiness appBusiness;

    private StudentService studentService;

    // 查询所有城市
    @GetMapping("/queryCityAll")
    public ResultModel queryCityAll() {
        List<City> cities = cityService.queryCityAll();
        return ResultHelper.success("Success", cities);
    }


    // 添加城市
    @PostMapping("/addCity")
    public ResultModel addCity(String name) {
        if (StringUtils.isEmpty(name))
            return ResultHelper.error("名称不能为空");
        boolean rs = cityService.addCity(new City().setName(name));
        if (rs)
            return ResultHelper.success("Success", null);
        return ResultHelper.error("添加失败");
    }

    // 添加学生
    @PostMapping("/addStudent")
    public ResultModel addStudent(String name, Integer age, String gender, String info, String idCardCode, String cityName) {
        if (StringUtils.isEmpty(name)
                || age == null
                || StringUtils.isEmpty(gender)
                || StringUtils.isEmpty(info)
                || StringUtils.isEmpty(idCardCode)
                || StringUtils.isEmpty(cityName))
            return ResultHelper.error("参数不合法");

        // 获取GenderEnum
        GenderEnum genderEnum = GenderEnum.getEnumByDesc(gender);

        // student
        Student student = new Student()
                .setName(name)
                .setAge(age)
                .setGender(genderEnum)
                .setInfo(info);

        // city
        City city = new City().setName(cityName);

        // idCard
        Card card = new Card().setCode(idCardCode);

        // service
        boolean rs = appBusiness.addStudent(student, city, card);
        if (rs)
            return ResultHelper.success("Success", null);
        return ResultHelper.error("添加失败");
    }

    // 分页查询学生
    @GetMapping("/queryStudentByPage/{currentPage}")
    public ResultModel queryStudentByPage(@PathVariable("currentPage") Long currentPage) {
        if (currentPage == null)
            return ResultHelper.error("当前页不能为空");
        IPage<Student> studentIPage = studentService.queryStudentByPage(currentPage);
        return ResultHelper.success("Success", studentIPage);
    }
}
