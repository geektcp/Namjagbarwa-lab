package com.geektcp.alpha.driver.mybatis2.controller;

import com.geektcp.alpha.driver.mybatis2.business.AppBusiness;
import com.geektcp.alpha.driver.mybatis2.model.vo.PageResponse;
import com.geektcp.alpha.driver.mybatis2.model.qo.UserQo;
import com.geektcp.alpha.driver.mybatis2.model.vo.UserVo;
import com.geektcp.alpha.driver.mybatis2.service.CardService;
import com.geektcp.alpha.driver.mybatis2.service.CityService;
import com.geektcp.alpha.driver.mybatis2.service.StudentService;
import com.geektcp.alpha.driver.mybatis2.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 测试
 *
 * @author tanghaiyang
 * @since 2018-09-01
 */
@RestController
@RequestMapping(value = "/app", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "App 测试示例")
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AppController {

    private CityService cityService;

    private AppBusiness appBusiness;

    private StudentService studentService;

    private CardService cardService;

    private UserService userService;

    @ApiOperation(value = "分页查询公司的用户列表")
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    @PostMapping("/findPage")
    public PageResponse<UserVo> findPage(@RequestBody @Valid UserQo userQo) {
        return userService.findPage(userQo);
    }

    /**
     * 经测试，@Transactional注解放在控制器，非接口实现的类也是有效的。
     * 加了注解后，要等所有代码跑完才集中执行sql，@Transactional实现了事务的作用。
     */
    @GetMapping("/insert")
    @Transactional
    public void testTransactional(){
        cardService.insert();
        cardService.put();
        log.info("insert finished!");
    }

}
