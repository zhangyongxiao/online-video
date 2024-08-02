package com.video.web.controller.vip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.video.common.core.controller.BaseController;
import com.video.common.core.domain.AjaxResult;
import com.video.common.core.domain.entity.MemberRegister;
import com.video.common.utils.StringUtils;
import com.video.framework.shiro.service.RegisterService;

/**
 * 注册验证
 * 
 * @author ruoyi
 */
@Controller
public class RegisterController extends BaseController
{
    @Autowired
    private RegisterService registerService;


    @GetMapping("/register")
    public String register()
    {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public AjaxResult ajaxRegister(MemberRegister user)
    {
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
}
