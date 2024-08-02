package com.video.common.core.domain.entity;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.*;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.video.common.annotation.Excel;
import com.video.common.annotation.Excel.ColumnType;
import com.video.common.annotation.Excel.Type;
import com.video.common.annotation.Excels;
import com.video.common.xss.Xss;

/**
 * 用户注册
 * 
 */
@Data
public class MemberRegister
{

    /** 用户邮箱 */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不满足")
    private String email;

    /** 密码 */
    @NotBlank(message = "密码不能未空")
    @Min(value = 8,message = "长度不能小于8个字符")
    @Max(value = 30,message = "长度不能大于30个字符")
    private String password;
    //邮箱验证码
    @NotBlank(message = "邮箱验证码不能为空")
    private String emailCode;



}
