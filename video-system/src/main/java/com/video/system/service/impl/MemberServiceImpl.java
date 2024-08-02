package com.video.system.service.impl;

import com.video.common.core.domain.entity.MemberInfo;
import com.video.system.service.MemberService;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Override
    public MemberInfo selectUserByLoginName(String username) {
        return null;
    }

    @Override
    public boolean checkLoginNameUnique(MemberInfo user) {
        return false;
    }

    @Override
    public boolean registerUser(MemberInfo user) {
        return false;
    }
}
