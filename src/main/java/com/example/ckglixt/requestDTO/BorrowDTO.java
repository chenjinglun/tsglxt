package com.example.ckglixt.requestDTO;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
/**
* @Description: 借阅归还书籍实体类
* @Param:
* @return:
* @Author: xukunyuan
* @Date: 2020/10/20
*/
public class BorrowDTO {
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
     * 借阅人编号
     */
    @ApiModelProperty(value = "借阅人编号")
    private String userId;
    /**
     * 借阅人名称
     */
    @ApiModelProperty(value = "借阅人名称")
    private String userName;
    /**
     * 借阅时间
     */
    @ApiModelProperty(value = "借阅时间")
    private String startTime;
    /**
     * 归还时间
     */
    @ApiModelProperty(value = "归还时间")
    private String endTime;
    /**
     * 借阅id
     */
    @ApiModelProperty(value = "借阅id")
    private String borrowId;
    /**
     * 借阅图书编号
     */
    @ApiModelProperty(value = "图书编号")
    private String isbn;
    /**
     * 借阅数量
     */
    @ApiModelProperty(value = "借阅数量")
    private String bookCnt;
    /**
     * 借阅数量(int)
     */
    @ApiModelProperty(value = "借阅数量")
    private Integer bookCntInt;

    /**
     * 借阅状态：0为未归还,1为已归还
     */
    @ApiModelProperty(value = "借阅状态")
    private String Status;

    /**
     * 用户手机号
     */
    @ApiModelProperty(value = "用户手机号")
    private String userPhone;

    /**
     *书籍名称
     */
    @ApiModelProperty(value = "书籍名称")
    private String bookName;
    /**
     *作者
     */
    @ApiModelProperty(value = "作者")
    private String bookAuthor;
    /**
     *地址
     */
    @ApiModelProperty(value = "地址")
    private String bookPlace;

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPlace() {
        return bookPlace;
    }

    public void setBookPlace(String bookPlace) {
        this.bookPlace = bookPlace;
    }

    public Integer getBookCntInt() {
        return bookCntInt;
    }

    public void setBookCntInt(Integer bookCntInt) {
        this.bookCntInt = bookCntInt;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookCnt() {
        return bookCnt;
    }

    public void setBookCnt(String bookCnt) {
        this.bookCnt = bookCnt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

}
