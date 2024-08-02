package com.video.system.service;

import com.video.common.core.domain.entity.MemberInfo;
import com.video.common.core.domain.entity.MemberRegister;

/**
 * 会员服务
 */
public interface MemberService {

    MemberInfo selectUserByLoginName(String username);

    boolean checkLoginNameUnique(MemberInfo user);

    boolean registerUser(MemberInfo user);
}
