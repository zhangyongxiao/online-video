package com.video.framework.shiro.service;

import com.video.common.core.domain.entity.MemberInfo;
import com.video.system.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.video.common.constant.ShiroConstants;
import com.video.common.constant.UserConstants;
import com.video.common.enums.UserStatus;
import com.video.common.exception.user.CaptchaException;
import com.video.common.exception.user.UserBlockedException;
import com.video.common.exception.user.UserDeleteException;
import com.video.common.exception.user.UserNotExistsException;
import com.video.common.exception.user.UserPasswordNotMatchException;
import com.video.common.utils.ServletUtils;
import com.video.common.utils.StringUtils;

/**
 * 登录校验方法
 * 
 * @author ruoyi
 */
@Component
public class LoginService
{
    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private MemberService memberService;

    /**
     * 登录
     */
    public MemberInfo login(String email, String password)
    {
        // 验证码校验
        if (ShiroConstants.CAPTCHA_ERROR.equals(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
        {
            throw new CaptchaException();
        }
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password))
        {
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            throw new UserPasswordNotMatchException();
        }

        // 查询用户信息
        MemberInfo user = memberService.selectUserByLoginName(email);


        if (user == null)
        {
            throw new UserNotExistsException();
        }
        
        if (UserStatus.DELETED.getCode().equals(user.getStatus()))
        {
            throw new UserDeleteException();
        }
        
        if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            throw new UserBlockedException();
        }

        passwordService.validate(user, password);

        return user;
    }

    /**
    private boolean maybeEmail(String username)
    {
        if (!username.matches(UserConstants.EMAIL_PATTERN))
        {
            return false;
        }
        return true;
    }

    private boolean maybeMobilePhoneNumber(String username)
    {
        if (!username.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN))
        {
            return false;
        }
        return true;
    }
    */


}
