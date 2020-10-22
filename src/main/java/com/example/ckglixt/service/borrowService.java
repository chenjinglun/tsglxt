package com.example.ckglixt.service;

import com.example.ckglixt.requestDTO.BorrowDTO;
import com.example.ckglixt.requestDTO.ListUsersRequestDTO;
import com.example.ckglixt.utils.ResponceData;

public interface borrowService {
    /**
     * @Description 图书借阅
     * @author xukunyuan
     * @date 2020-10-20 17:24
     */
    public ResponceData borrowBook(BorrowDTO borrowDTO);
    /**
     * @Description 图书归还
     * @author xukunyuan
     * @date 2020-10-20 17:27
     */
    public ResponceData returnBook(BorrowDTO borrowDTO);
    /**
     * @Description 查询借阅详细信息
     * @author xukunyuan
     * @date 2020-10-20 17:29
     */
    public ResponceData getBorrowDetail(String borrowId);
    /**
     * @Description 列表查询详细信息
     * @author xukunyuan
     * @date 2020-10-20 17:30
     */
    public ResponceData listBorrow(BorrowDTO borrowDTO);
}
