package com.example.ckglixt.dao;

import com.example.ckglixt.requestDTO.BorrowDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface borrowMapper {
    /** 
    * @Description: 查询用户借阅数量 
    * @Param: userId
    * @return:  int
    * @Author: xukunyuan
    * @Date: 2020/10/21 
    */
    int getUserBorrowCnt(@Param("userId") String userId);
    /**
     * @Description: 从借阅信息表中统计查询用户借阅数量
     * @Param: userId
     * @return:  int
     * @Author: xukunyuan
     * @Date: 2020/10/21
     */
    Integer getBorrowCnt(@Param("userId") String userId);
    /**
    * @Description: 新增图书借阅信息
    * @Param:  userId，listIsbn,listCnt,listBorrowId
    * @return: int
    * @Author: xukunyuan
    * @Date: 2020/10/21
    */
    int borrowBook(@Param("userId") String userId, @Param("list") List<BorrowDTO> list);
    /**
    * @Description: 修改用户借书数量
    * @Param:  userId，cnt
    * @return:  int
    * @Author: xukunyuan
    * @Date: 2020/10/21
    */
    int updateUserCnt(@Param("userId") String userId,@Param("cnt") String cnt);
    /**
    * @Description: 归还图书
    * @Param:  listReturn,userId
    * @return:  int
    * @Author: xukunyuan
    * @Date: 2020/10/22
    */
    int returnBook(@Param("listReturn") List<String> listReturn,@Param("userId") String userId);
    /**
    * @Description: 查询图书详细信息
    * @Param:  borrowId
    * @return:  BorrowDTO
    * @Author: xukunyuan
    * @Date: 2020/10/22
    */
    List<BorrowDTO> getBorrowDetail(@Param("listBorrowId") List<String> listBorrowId);
    /**
     * @Description: 列表查询借阅信息
     * @Param: borrowDTO
     * @return: list
     * @Author: xukunyuan
     * @Date: 2020/10/22
     */
    List<BorrowDTO> listBorrow(@Param("borrowDTO") BorrowDTO borrowDTO,@Param("userName") String userName,@Param("bookAuthor") String bookAuthor,@Param("bookName") String bookName);
    /**
    * @Description: 修改书本库存量 （借阅图书）
    * @Param:  listIsbn、listCntInt
    * @return:  int
    * @Author: xukunyuan
    * @Date: 2020/10/27
    */
    int updateBookCnt(@Param("list1") List<BorrowDTO> list1);
    /**
     * @Description: 修改书本库存量 （归还图书）
     * @Param:  listIsbn、listCntInt
     * @return:  int
     * @Author: xukunyuan
     * @Date: 2020/10/27
     */
    int updateBookCntReturn(@Param("list1") List<BorrowDTO> list1);
}
