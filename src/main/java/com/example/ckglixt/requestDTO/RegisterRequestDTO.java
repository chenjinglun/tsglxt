package com.example.ckglixt.requestDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterRequestDTO {
    @ApiModelProperty(value = "用户id")
    private String userID;

    @ApiModelProperty(value = "用户账号")
    private String userAccout;

    @ApiModelProperty(value = "用户密码")
    private String userPassWord;

    @ApiModelProperty(value = "用户名名称")
    private String userName;

    @ApiModelProperty(value = "用户性别")
    private String userSex;

    @ApiModelProperty(value = "手机号")
    private String userPhone;

    @ApiModelProperty(value = "角色")
    private String role;
}
