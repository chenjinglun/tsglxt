package com.example.ckglixt.responseDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class addTsCkResponseDTO {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;
    /**
     * 采购书名
     */
    @ApiModelProperty(value = "采购书名")
    private String bookName;
    /**
     * 采购数量
     */
    @ApiModelProperty(value = "采购数量")
    private Integer num;
    /**
     * 采购类型
     */
    @ApiModelProperty(value = "采购类型,1:购买，2：捐赠，3：回收")
    private String bookInType;
    /**
     * 价格
     */
    @ApiModelProperty(value = "价格")
    private Float price;
    /**
     * 入库时间
     */
    @ApiModelProperty(value = "入库时间")
    private Date createTime;
}
