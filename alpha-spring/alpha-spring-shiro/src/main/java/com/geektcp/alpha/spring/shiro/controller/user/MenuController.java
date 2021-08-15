package com.geektcp.alpha.spring.shiro.controller.user;


import com.geektcp.alpha.spring.shiro.annotation.ControllerEndpoint;
import com.geektcp.alpha.spring.shiro.common.controller.BaseController;
import com.geektcp.alpha.spring.shiro.common.entity.Response;
import com.geektcp.alpha.spring.shiro.common.entity.MenuTree;
import com.geektcp.alpha.spring.shiro.exception.AlphaException;
import com.geektcp.alpha.spring.shiro.entity.user.Menu;
import com.geektcp.alpha.spring.shiro.entity.user.User;
import com.geektcp.alpha.spring.shiro.service.IMenuService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author tanghaiyang
 */
@Slf4j
@RestController
@RequestMapping("menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @GetMapping("{username}")
    public Response getUserMenus(@NotBlank(message = "{required}") @PathVariable String username) throws AlphaException {
        User currentUser = getCurrentUser();
        if (!StringUtils.equalsIgnoreCase(username, currentUser.getUsername()))
            throw new AlphaException("您无权获取别人的菜单");
        MenuTree<Menu> userMenus = this.menuService.findUserMenus(username);
        return new Response().data(userMenus);
    }

    @GetMapping("tree")
    @ControllerEndpoint(exceptionMessage = "获取菜单树失败")
    public Response getMenuTree(Menu menu) {
        MenuTree<Menu> menus = this.menuService.findMenus(menu);
        return new Response().success().data(menus.getChilds());
    }

    @PostMapping
    @RequiresPermissions("menu:add")
    @ControllerEndpoint(operation = "新增菜单/按钮", exceptionMessage = "新增菜单/按钮失败")
    public Response addMenu(@Valid Menu menu) {
        this.menuService.createMenu(menu);
        return new Response().success();
    }

    @GetMapping("delete/{menuIds}")
    @RequiresPermissions("menu:delete")
    @ControllerEndpoint(operation = "删除菜单/按钮", exceptionMessage = "删除菜单/按钮失败")
    public Response deleteMenus(@NotBlank(message = "{required}") @PathVariable String menuIds) {
        this.menuService.deleteMeuns(menuIds);
        return new Response().success();
    }

    @PostMapping("update")
    @RequiresPermissions("menu:update")
    @ControllerEndpoint(operation = "修改菜单/按钮", exceptionMessage = "修改菜单/按钮失败")
    public Response updateMenu(@Valid Menu menu) {
        this.menuService.updateMenu(menu);
        return new Response().success();
    }

    @GetMapping("excel")
    @RequiresPermissions("menu:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(Menu menu, HttpServletResponse response) {
        List<Menu> menus = this.menuService.findMenuList(menu);
        ExcelKit.$Export(Menu.class, response).downXlsx(menus, false);
    }
}
