package com.example.ckglixt.requestDTO;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateTsRequestDTO {
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
     * 出版社
     */
    @ApiModelProperty(value = "出版社")
    private String bookPress;
    /**
     * 价格
     */
    @ApiModelProperty(value = "价格")
    private Float price;
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer num;
    /**
     * 书本存放地方
     */
    @ApiModelProperty(value = "书本存放地方")
    private String bookPlace;
    /**
     * 书籍介绍
     */
    @ApiModelProperty(value = "书籍介绍")
    private String detail;
}
