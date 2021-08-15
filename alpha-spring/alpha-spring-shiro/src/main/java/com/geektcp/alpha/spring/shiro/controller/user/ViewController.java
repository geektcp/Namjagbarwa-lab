package com.geektcp.alpha.spring.shiro.controller.user;

import com.geektcp.alpha.spring.shiro.authentication.ShiroHelper;
import com.geektcp.alpha.spring.shiro.common.controller.BaseController;
import com.geektcp.alpha.spring.shiro.common.entity.AlphaConstant;
import com.geektcp.alpha.spring.shiro.utils.DateUtil;
import com.geektcp.alpha.spring.shiro.utils.AlphaUtil;
import com.geektcp.alpha.spring.shiro.entity.user.User;
import com.geektcp.alpha.spring.shiro.service.IUserService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tanghaiyang
 */
@Controller("systemView")
public class ViewController extends BaseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ShiroHelper shiroHelper;

    @GetMapping("login")
    @ResponseBody
    public Object login(HttpServletRequest request) {
        if (AlphaUtil.isAjaxRequest(request)) {
            throw new ExpiredSessionException();
        } else {
            ModelAndView mav = new ModelAndView();
            mav.setViewName(AlphaUtil.view("login"));
            return mav;
        }
    }

    @GetMapping("unauthorized")
    public String unauthorized() {
        return AlphaUtil.view("error/403");
    }


    @GetMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @GetMapping("index")
    public String index(Model model) {
        AuthorizationInfo authorizationInfo = shiroHelper.getCurrentuserAuthorizationInfo();
        User user = super.getCurrentUser();
        User currentUserDetail = userService.findByName(user.getUsername());
        currentUserDetail.setPassword("It's a secret");
        model.addAttribute("user", currentUserDetail);
        model.addAttribute("permissions", authorizationInfo.getStringPermissions());
        model.addAttribute("roles", authorizationInfo.getRoles());
        return "index";
    }

    @GetMapping(AlphaConstant.VIEW_PREFIX + "layout")
    public String layout() {
        return AlphaUtil.view("layout");
    }

    @GetMapping(AlphaConstant.VIEW_PREFIX + "password/update")
    public String passwordUpdate() {
        return AlphaUtil.view("system/user/passwordUpdate");
    }

    @GetMapping(AlphaConstant.VIEW_PREFIX + "user/profile")
    public String userProfile() {
        return AlphaUtil.view("system/user/userProfile");
    }

    @GetMapping(AlphaConstant.VIEW_PREFIX + "user/avatar")
    public String userAvatar() {
        return AlphaUtil.view("system/user/avatar");
    }

    @GetMapping(AlphaConstant.VIEW_PREFIX + "user/profile/update")
    public String profileUpdate() {
        return AlphaUtil.view("system/user/profileUpdate");
    }

    @GetMapping(AlphaConstant.VIEW_PREFIX + "system/user")
    @RequiresPermissions("user:view")
    public String systemUser() {
        return AlphaUtil.view("system/user/user");
    }

    @GetMapping(AlphaConstant.VIEW_PREFIX + "system/user/add")
    @RequiresPermissions("user:add")
    public String systemUserAdd() {
        return AlphaUtil.view("system/user/userAdd");
    }

    @GetMapping(AlphaConstant.VIEW_PREFIX + "system/user/detail/{username}")
    @RequiresPermissions("user:view")
    public String systemUserDetail(@PathVariable String username, Model model) {
        resolveUserModel(username, model, true);
        return AlphaUtil.view("system/user/userDetail");
    }

    @GetMapping(AlphaConstant.VIEW_PREFIX + "system/user/update/{username}")
    @RequiresPermissions("user:update")
    public String systemUserUpdate(@PathVariable String username, Model model) {
        resolveUserModel(username, model, false);
        return AlphaUtil.view("system/user/userUpdate");
    }

    @GetMapping(AlphaConstant.VIEW_PREFIX + "system/role")
    @RequiresPermissions("role:view")
    public String systemRole() {
        return AlphaUtil.view("system/role/role");
    }

    @GetMapping(AlphaConstant.VIEW_PREFIX + "system/menu")
    @RequiresPermissions("menu:view")
    public String systemMenu() {
        return AlphaUtil.view("system/menu/menu");
    }

    @GetMapping(AlphaConstant.VIEW_PREFIX + "system/dept")
    @RequiresPermissions("dept:view")
    public String systemDept() {
        return AlphaUtil.view("system/dept/dept");
    }

    @RequestMapping(AlphaConstant.VIEW_PREFIX + "index")
    public String pageIndex() {
        return AlphaUtil.view("index");
    }

    @GetMapping(AlphaConstant.VIEW_PREFIX + "404")
    public String error404() {
        return AlphaUtil.view("error/404");
    }

    @GetMapping(AlphaConstant.VIEW_PREFIX + "403")
    public String error403() {
        return AlphaUtil.view("error/403");
    }

    @GetMapping(AlphaConstant.VIEW_PREFIX + "500")
    public String error500() {
        return AlphaUtil.view("error/500");
    }

    private void resolveUserModel(String username, Model model, Boolean transform) {
        User user = userService.findByName(username);
        model.addAttribute("user", user);
        if (transform) {
            String ssex = user.getSex();
            if (User.SEX_MALE.equals(ssex)) user.setSex("男");
            else if (User.SEX_FEMALE.equals(ssex)) user.setSex("女");
            else user.setSex("保密");
        }
        if (user.getLastLoginTime() != null)
            model.addAttribute("lastLoginTime", DateUtil.getDateFormat(user.getLastLoginTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
    }
}
