package com.video.common.core.domain.entity;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 发送邮箱验证码
 */
@Data
public class SendEmailCode {
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    @NotBlank(message = "图片验证码不能为空")
    private String imgCode;

}
