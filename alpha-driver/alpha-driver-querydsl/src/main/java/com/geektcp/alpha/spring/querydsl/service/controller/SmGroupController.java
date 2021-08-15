package com.geektcp.alpha.spring.querydsl.service.controller;

import com.geektcp.alpha.common.spring.model.PageResponse;
import com.geektcp.alpha.common.spring.model.TResponse;
import com.geektcp.alpha.spring.querydsl.service.model.qo.SmGroupQo;
import com.geektcp.alpha.spring.querydsl.service.model.suo.SmGroupSuo;
import com.geektcp.alpha.spring.querydsl.service.service.SmGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * Created by HaiyangWork on 2018/12/27.
 */
@Api(description = "[服务发布]-分组")
@RestController
@RequestMapping("/service/group")
public class SmGroupController {

    @Autowired
    private SmGroupService smGroupService;

    @ApiOperation(value = "增加或编辑")
    @PostMapping(value = "/saveOrUpdate")
    public TResponse saveOrUpdate(@RequestBody @Valid SmGroupSuo smGroupSuo) {
        return smGroupService.saveOrUpdate(smGroupSuo);
    }

    @ApiOperation(value = "删除")
    @PostMapping(value = "/delete")
    public TResponse delete(@RequestBody @Valid SmGroupSuo smGroupSuo) {
        return smGroupService.delete(smGroupSuo);
    }

    @ApiOperation(value = "修改")
    @PostMapping(value = "/update")
    public TResponse update(@RequestBody @Valid SmGroupSuo smGroupSuo) {
        return smGroupService.saveOrUpdate(smGroupSuo);
    }

    @ApiOperation(value = "查询")
    @PostMapping(value = "/find")
    public TResponse find(@RequestBody @Valid SmGroupQo smGroupQo) {
        return smGroupService.find(smGroupQo);
    }

    @ApiOperation(value = "查询")
    @PostMapping(value = "/findPage")
    public PageResponse findPage(@RequestBody @Valid SmGroupQo smGroupQo) {
        return smGroupService.findPage(smGroupQo);
    }

    @ApiOperation(value = "查询")
    @PostMapping(value = "/search")
    public TResponse search(@RequestBody @Valid SmGroupQo smGroupQo) {
        return smGroupService.search(smGroupQo);
    }


}
