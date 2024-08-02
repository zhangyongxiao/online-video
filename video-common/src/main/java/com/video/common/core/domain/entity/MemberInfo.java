package com.video.common.core.domain.entity;

import lombok.Data;

@Data
public class MemberInfo {
    private Long userId;

    private String email;

    private String password;

    private String status;

    public String salt;

}
