package com.example.ckglixt.requestDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ListUsersRequestDTO {
    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    private int pageSize;
    /**
     * 页数
     */
    @ApiModelProperty(value = "页数")
    private int pageNum;
}
