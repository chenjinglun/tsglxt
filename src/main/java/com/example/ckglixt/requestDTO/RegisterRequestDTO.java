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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserAccout() {
        return userAccout;
    }

    public void setUserAccout(String userAccout) {
        this.userAccout = userAccout;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
