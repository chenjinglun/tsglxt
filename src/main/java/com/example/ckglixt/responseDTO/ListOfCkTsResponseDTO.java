package com.example.ckglixt.responseDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ListOfCkTsResponseDTO {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;
    /**
     * 作者
     */
    @ApiModelProperty(value = "作者")
    private String bookAuthor;
    /**
     * 书名
     */
    @ApiModelProperty(value = "书名")
    private String bookName;
    /**
     * 出版社
     */
    @ApiModelProperty(value = "出版社")
    private String bookPress;
    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private String isBn;
    /**
     * 价格
     */
    @ApiModelProperty(value = "价格")
    private Float price;
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer Num;
    /**
     * 书本存放地方
     */
    @ApiModelProperty(value = "书本存放地方")
    private String bookPlace;
    /**
     * 书本介绍
     */
    @ApiModelProperty(value = "书本介绍")
    private String detail;
}
