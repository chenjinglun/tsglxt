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
}
