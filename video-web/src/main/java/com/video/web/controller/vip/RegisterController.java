package com.video.web.controller.vip;

import com.video.common.annotation.Anonymous;
import com.video.common.core.domain.entity.EmailSend;
import com.video.common.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Autowired
    private EmailService emailService;


    @GetMapping("/register")
    @Anonymous
    public String register()
    {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    @Anonymous
    public AjaxResult ajaxRegister(MemberRegister user)
    {
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }

    @PostMapping("sendEmail")
    @ResponseBody
    @Anonymous
    public AjaxResult sendEmail(@RequestBody @Validated EmailSend emailSend){

        emailService.sendSimpleMail(emailSend.getEmail(),"注册验证码","验证码");

        return success();
    }
}
