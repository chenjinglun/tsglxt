package com.example.ckglixt.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.crazycake.shiro.AuthCachePrincipal;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class userDTO implements Serializable, AuthCachePrincipal {
    @ApiModelProperty(value = "用户id")
    private String userID;

    @ApiModelProperty(value = "用户账号")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    private String userPassWord;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }

    @Override
    public String getAuthCacheKey() {
        return null;
    }

}
