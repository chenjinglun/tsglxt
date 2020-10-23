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
     * 作者
     */
    @ApiModelProperty(value = "作者")
    private String bookAuthor;
}
