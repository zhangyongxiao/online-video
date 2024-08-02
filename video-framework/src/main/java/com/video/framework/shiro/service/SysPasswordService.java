package com.video.framework.shiro.service;

import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;

import com.video.common.core.domain.entity.MemberInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.video.common.constant.Constants;
import com.video.common.constant.ShiroConstants;
import com.video.common.exception.user.UserPasswordNotMatchException;
import com.video.common.exception.user.UserPasswordRetryLimitExceedException;
import com.video.common.utils.MessageUtils;
import com.video.framework.manager.AsyncManager;

/**
 * 登录密码方法
 * 
 * @author ruoyi
 */
@Component
public class SysPasswordService
{
    @Autowired
    private CacheManager cacheManager;

    private Cache<String, AtomicInteger> loginRecordCache;

    @Value(value = "${user.password.maxRetryCount}")
    private String maxRetryCount;

    @PostConstruct
    public void init()
    {
        loginRecordCache = cacheManager.getCache(ShiroConstants.LOGIN_RECORD_CACHE);
    }

    public void validate(MemberInfo user, String password)
    {
        String email = user.getEmail();

        AtomicInteger retryCount = loginRecordCache.get(email);

        if (retryCount == null)
        {
            retryCount = new AtomicInteger(0);
            loginRecordCache.put(email, retryCount);
        }
        if (retryCount.incrementAndGet() > Integer.valueOf(maxRetryCount).intValue())
        {
            throw new UserPasswordRetryLimitExceedException(Integer.valueOf(maxRetryCount).intValue());
        }

        if (!matches(user, password))
        {
            loginRecordCache.put(email, retryCount);
            throw new UserPasswordNotMatchException();
        }
        else
        {
            clearLoginRecordCache(email);
        }
    }

    public boolean matches(MemberInfo user, String newPassword)
    {
        return user.getPassword().equals(encryptPassword(user.getEmail(), newPassword, user.getSalt()));
    }

    public void clearLoginRecordCache(String loginName)
    {
        loginRecordCache.remove(loginName);
    }

    public String encryptPassword(String loginName, String password, String salt)
    {
        return new Md5Hash(loginName + password + salt).toHex();
    }
}
