package com.video.common.core.domain.entity;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class EmailSend {

    @Email(message = "请输入有效的邮箱")
    private String email;

    private String imgCode;
}
