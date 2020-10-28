package com.example.ckglixt.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BackFrontDataDTO {
    @ApiModelProperty(value = "用户角色")
    private String userRole;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "token")
    private String Authorization;

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthorization() {
        return Authorization;
    }

    public void setAuthorization(String authorization) {
        Authorization = authorization;
    }
}
