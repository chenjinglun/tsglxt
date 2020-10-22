package com.example.ckglixt.service.impl;

import com.example.ckglixt.dao.borrowMapper;
import com.example.ckglixt.requestDTO.BorrowDTO;
import com.example.ckglixt.requestDTO.ListUsersRequestDTO;
import com.example.ckglixt.service.borrowService;
import com.example.ckglixt.utils.ResponceData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 借阅信息查询以及借书还书
 * @author xukunyuan
 * @date 2020-10-20 17:01
 */
@Service
public class BorrowServicelmpl implements borrowService {
    //注入Mapper接口
    @Resource
    private com.example.ckglixt.dao.borrowMapper borrowMapper;

    /**
    * @Description: 图书借阅
    * @Param:  borrowDTO
    * @return:
    * @Author: xukunyuan
    * @Date: 2020/10/20
    */
    @Override
    public ResponceData borrowBook(BorrowDTO borrowDTO){
        if (borrowDTO.getUserId() ==null || borrowDTO.getBookCnt() == null ||borrowDTO.getIsbn() == null ){
            return ResponceData.bizError("所需参数不完整，请重新输入");
        }
        /**
         * 分割借书编号,图书数量
         */
        List<String> listIsbn = Arrays.asList(borrowDTO.getIsbn().split(","));
        List<String> listCnt = Arrays.asList(borrowDTO.getBookCnt().split(","));
        /**
         * 本次借书的数量
         */
        int cntNow = listIsbn.size();
        /**
         * 判断借书是否超过规定的数量
         */
        if (cntNow > 3){
            return ResponceData.bizError("超过可借数量，借书失败，请重试");
        }
        /**
         * 随机生成借阅编号
         */
        List<String> listBorrowId = new ArrayList<>();
        for ( int i = 0 ; i < listIsbn.size();i++) {
            int borrowId = (int)((Math.random()*9+1)*100000);
            String borrowIdS = String.valueOf(borrowId);
            listBorrowId.add(borrowIdS);
        }
        for ( int i = 0;i < listBorrowId.size();i++){
            System.out.println(listBorrowId.get(i));
        }
        /**
         * 判断该用户是否借书已超过3本
         */
        int cnt = borrowMapper.getUserBorrowCnt(borrowDTO.getUserId());
        /**
         * 当借书数量已经达到三本
         */
        if (cnt == 3){
            return ResponceData.bizError("该用户借书已经达到3本，请先归还图书再进行借书操作");
        }
        /**
         * 借书数量为两本，还能借一本
         */
        else if(cnt == 2){
            if (cntNow > 1){
                return ResponceData.bizError("借书超过可借数量,请重试");
            }
        }
        /**
         * 借书数量为1本，还能借2本
         */
        else if (cnt == 1){
            if (cntNow > 2){
                return ResponceData.bizError("借书超过可借数量,请重试");
            }
        }
        /**
         * 将信息存入list中
         */
        List<BorrowDTO> list =  new ArrayList<>();
        for (int i = 0;i < listBorrowId.size();i++){
            BorrowDTO t1 = new BorrowDTO();
            t1.setBorrowId(listBorrowId.get(i));
            t1.setBookCnt(listCnt.get(i));
            t1.setIsbn(listIsbn.get(i));
            list.add(t1);
        }
        /**
         * 新增借阅信息
         */
        if (borrowMapper.borrowBook(borrowDTO.getUserId(),list) == 0){
            return ResponceData.bizError("新增借书信息失败，请重试");
        }
        /**
         * 查看新的用户借阅数量
         */
        int cnt1 = borrowMapper.getUserBorrowCnt(borrowDTO.getUserId());
        /**
         * 将数量从int类型转为String
         */
        String cnt3 = String.valueOf(cnt1);
        System.out.println("----------------------------"+ cnt3);
        /**
         * 修改用户借书数量信息
         */
        System.out.println(borrowDTO.getUserId());
        System.out.println(cnt3);
        if (borrowMapper.updateUserCnt(borrowDTO.getUserId(),cnt3) == 0){
            return ResponceData.bizError("修改用户借阅数量失败，请重试");
        }
        return ResponceData.success("借书成功，请按时归还");

    }
    /**
    * @Description: 归还图书
    * @Param:  borrowDTO
    * @return:  Response
    * @Author: xukunyuan
    * @Date: 2020/10/22
    */
    @Override
    public ResponceData returnBook(BorrowDTO borrowDTO) {
        if (borrowDTO.getUserId() ==null || borrowDTO.getBorrowId() == null ){
            return ResponceData.bizError("所需参数不完整，请重新输入");
        }
        /**
         * 将借阅编号分割开
         */
        List<String> listReturn= Arrays.asList(borrowDTO.getBorrowId().split(","));
        /**
         * 还书
         */
        if (borrowMapper.returnBook(listReturn,borrowDTO.getUserId()) == 0){
            return ResponceData.bizError("归还失败，请重试");
        }
        return ResponceData.success("归还成功");
    }
    /**
    * @Description: 查询借阅详情
    * @Param:  borrowId
    * @return:  int
    * @Author: xukunyuan
    * @Date: 2020/10/22
    */
    @Override
    public ResponceData getBorrowDetail(String borrowId) {
        BorrowDTO borrowDTO1 = borrowMapper.getBorrowDetail(borrowId);
        if (borrowDTO1 == null){
            return ResponceData.bizError("查询失败，请重试");
        }
        return ResponceData.success("查询成功",borrowDTO1);
    }
    /**
    * @Description: 列表查询借阅信息
    * @Param: borrowDTO
    * @return: list
    * @Author: xukunyuan
    * @Date: 2020/10/22
    */
    @Override
    public ResponceData listBorrow(BorrowDTO borrowDTO) {
        PageHelper.startPage(borrowDTO.getPageNum(),borrowDTO.getPageNum());
        /**
         * 将列表信息装入List中
         */
        List<BorrowDTO> borrowDTOList = borrowMapper.listBorrow(borrowDTO,borrowDTO.getUserName());
        //包装list
        PageInfo<BorrowDTO> borrowDTOPageInfo = new PageInfo<>(borrowDTOList);
        if(borrowDTOList.size() == 0){
            return ResponceData.bizError("查询失败，请重试");
        }
        return ResponceData.success("查询成功",borrowDTOPageInfo);
    }


}