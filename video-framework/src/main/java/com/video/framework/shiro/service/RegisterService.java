package com.video.framework.shiro.service;

import com.video.common.core.domain.entity.MemberInfo;
import com.video.common.utils.bean.BeanUtils;
import com.video.system.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.video.common.constant.Constants;
import com.video.common.constant.ShiroConstants;
import com.video.common.constant.UserConstants;
import com.video.common.core.domain.entity.MemberRegister;
import com.video.common.utils.MessageUtils;
import com.video.common.utils.ServletUtils;
import com.video.common.utils.ShiroUtils;
import com.video.common.utils.StringUtils;
import com.video.framework.manager.AsyncManager;

/**
 * 注册校验方法
 * 
 * @author ruoyi
 */
@Component
public class RegisterService
{
    @Autowired
    private MemberService memberService;

    @Autowired
    private SysPasswordService passwordService;

    /**
     * 注册
     */
    public String register(MemberRegister user)
    {
        String msg = "", loginName = user.getEmail(), password = user.getPassword();
        MemberInfo memberInfo = new MemberInfo();
        BeanUtils.copyBeanProp(memberInfo,user);
        if (ShiroConstants.CAPTCHA_ERROR.equals(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
        {
            msg = "验证码错误";
        }
        else if (StringUtils.isEmpty(loginName))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }
        else if (loginName.length() < UserConstants.USERNAME_MIN_LENGTH
                || loginName.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (!memberService.checkLoginNameUnique(memberInfo))
        {
            msg = "保存用户'" + loginName + "'失败，注册账号已存在";
        }
        else
        {
            memberInfo.setEmail(loginName);
            memberInfo.setSalt(ShiroUtils.randomSalt());
            memberInfo.setPassword(passwordService.encryptPassword(loginName, password, memberInfo.getSalt()));
            boolean regFlag = memberService.registerUser(memberInfo);
            if (!regFlag)
            {
                msg = "注册失败,请联系系统管理人员";
            }
        }
        return msg;
    }
}
