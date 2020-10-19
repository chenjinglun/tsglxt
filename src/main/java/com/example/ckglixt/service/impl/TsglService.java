package com.example.ckglixt.service.impl;

import com.example.ckglixt.dao.TsglMapper;
import com.example.ckglixt.requestDTO.*;
import com.example.ckglixt.responseDTO.ListOfCgTsResponseDTO;
import com.example.ckglixt.responseDTO.addTsCkResponseDTO;
import com.example.ckglixt.utils.ResponceData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TsglService {
    @Resource
    private TsglMapper tsglDao;
    /**
     * 图书采购
     * @param tsglAddEntity
     * @return
     */
    public ResponceData addTs(TsglAddEntity tsglAddEntity) {
        if (tsglAddEntity.getBookName() == null || tsglAddEntity.getBookInType() == null || tsglAddEntity.getNum() == null || tsglAddEntity.getPrice() == null) {
            return ResponceData.bizError("图书采购参数丢失！");
        }
        List<String> Type = Arrays.asList(tsglAddEntity.getBookInType().split(","));
        List<String> BookName = Arrays.asList(tsglAddEntity.getBookName().split(","));
        List<String> Num = Arrays.asList(tsglAddEntity.getNum().split(","));
        List<String> Price = Arrays.asList(tsglAddEntity.getPrice().split(","));
        if (Type.size() != BookName.size() || BookName.size() != Num.size() || Num.size() != Price.size()) {
            return ResponceData.bizError("传参格式错误，请重新输入!");
        }
        // 批量插入数据的数量
        int num = Type.size();
        List<TsglAddRequestDTO> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            TsglAddRequestDTO addCgTs = new TsglAddRequestDTO();
            addCgTs.setId(UUID.randomUUID().toString());
            addCgTs.setBookInType(Type.get(i));
            addCgTs.setBookName(BookName.get(i));
            addCgTs.setStatus("2");
            addCgTs.setNum(Integer.parseInt(Num.get(i)));
            addCgTs.setPrice(Float.parseFloat(Price.get(i)));
            list.add(addCgTs);
        }
        int i = tsglDao.addTs(list);
        if (i == 0) {
            // 事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponceData.bizError("创建库存调拨单据失败！");
        }
        return ResponceData.success("采购图书成功！");
    }

//    /**
//     * 删除图书
//     * @param tsglDeleteEntity
//     * @return
//     */
//    public ResponceData deleteTs(TsglDeleteEntity tsglDeleteEntity) {
//        if(tsglDeleteEntity.getId() == null){
//            return ResponceData.bizError("删除图书参数丢失！");
//        }
//
//        //按照开发删除时应该选择对应分类然后传入对应的id,暂时不校验乱输入的id
//        tsglDao.deleteTs(tsglDeleteEntity);
//        int cnt = tsglDao.findTsByIdl(tsglDeleteEntity.getId());
//        if (cnt != 0){
//            return ResponceData.bizError("图书分类删除失败！");
//        }
//        return ResponceData.success("图书分类删除成功!");
//    }
    /**
     * 查询未入库图书
     * @param
     * @return
     */
    public ResponceData ListOfTs(ListUsersRequestDTO listUsersRequestDTO) {
        if (listUsersRequestDTO == null || listUsersRequestDTO.getPageNum() <= 0 || listUsersRequestDTO.getPageSize() <=0){
            return ResponceData.bizError("参数丢失");
        }
        PageHelper.startPage(listUsersRequestDTO.getPageNum(), listUsersRequestDTO.getPageSize());
        List<ListOfCgTsResponseDTO> List = tsglDao.ListOfTs();
        // 包装Page对象
        PageInfo<ListOfCgTsResponseDTO> pageData = new PageInfo<>(List);
        if (List.size() <=0 ){
            return ResponceData.bizError("未入库图书信息列表查询为空!");
        }
        else {
            return ResponceData.success("未入库图书信息列表查询成功",pageData);
        }
    }
    /**
     * 采购入库操作
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponceData addCk(AddCkRequestDTO addCkRequestDTO) {
        if (addCkRequestDTO.getBookInType() == null || addCkRequestDTO.getBookName() == null || addCkRequestDTO.getNum() == null || addCkRequestDTO.getPrice() == null){
            return ResponceData.bizError("参数丢失");
        }
        List<String> Type = Arrays.asList(addCkRequestDTO.getBookInType().split(","));
        List<String> ids = Arrays.asList(addCkRequestDTO.getId().split(","));
        List<String> BookName = Arrays.asList(addCkRequestDTO.getBookName().split(","));
        List<String> Num = Arrays.asList(addCkRequestDTO.getNum().split(","));
        List<String> Price = Arrays.asList(addCkRequestDTO.getPrice().split(","));
        if (Type.size() != BookName.size() || BookName.size() != Num.size() || Num.size() != Price.size()) {
            return ResponceData.bizError("传参格式错误，请重新输入!");
        }
        // 批量插入数据的数量
        int num = Type.size();
        List<addTsCkResponseDTO> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            addTsCkResponseDTO addCgTs = new addTsCkResponseDTO();
            addCgTs.setId(UUID.randomUUID().toString());
            addCgTs.setBookInType(Type.get(i));
            addCgTs.setBookName(BookName.get(i));
            addCgTs.setNum(Integer.parseInt(Num.get(i)));
            addCgTs.setPrice(Float.parseFloat(Price.get(i)));
            list.add(addCgTs);
        }
        int i = tsglDao.addCk(list);
        if (i == 0) {
            // 事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponceData.bizError("采购入库操作失败！");
        }
        int pp = tsglDao.updateStatus(ids);
        if (pp == 0) {
            // 事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponceData.bizError("采购入库操作失败！");
        }
        return ResponceData.success("采购入库操作成功！");
    }
//    /**
//     * 修改图书
//     * @param tsglAddEntity
//     * @return
//     */
//    public  ResponceData updateTs(TsglAddEntity tsglAddEntity) {
//        if(tsglAddEntity.getId() == null || tsglAddEntity.getTpyeNo() == null || tsglAddEntity.getName() == null){
//            return ResponceData.bizError("修改图书分类参数丢失！");
//        }
//        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date date = ft.parse(tsglAddEntity.getPubTime());
//            tsglAddEntity.setPubTime2(date);
//        }catch (ParseException e){
//            e.printStackTrace();
//        }
//        int cnt = tsglDao.judgeRepeat1(tsglAddEntity.getName(),tsglAddEntity.getId());
//        int cnt2 = tsglDao.judgeRepeat2(tsglAddEntity.getNo(),tsglAddEntity.getId());
//        if (cnt != 0 || cnt2 !=0){
//            return ResponceData.bizError("图书已存在，请重新输入图书编号和名称！");
//        }
//        tsglDao.updateTs(tsglAddEntity);
//        return ResponceData.success("修改成功！");
//    }
}