package com.example.ckglixt.service.impl;

import com.example.ckglixt.dao.TsglMapper;
import com.example.ckglixt.requestDTO.*;
import com.example.ckglixt.responseDTO.ListOfCgTsResponseDTO;
import com.example.ckglixt.responseDTO.ListOfCkTsResponseDTO;
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

import static com.example.ckglixt.utils.AuthUtils.generateMixString;

@Service
public class TsglService {
    @Resource
    private TsglMapper tsglDao;
    /**
     * 图书采购
     * @param tsglAddEntity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
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

    /**
     * 删除图书
     * @param tsglDeleteEntity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponceData deleteTsFromCk(TsglDeleteEntity tsglDeleteEntity) {
        if(tsglDeleteEntity.getId() == null){
            return ResponceData.bizError("删除图书参数丢失！");
        }
        List<String> listylYl = Arrays.asList(tsglDeleteEntity.getId().split(","));
        //按照开发删除时应该选择对应分类然后传入对应的id,暂时不校验乱输入的id
        Integer cnt = tsglDao.deleteTs(listylYl);
        if (cnt == 0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponceData.bizError("图书出库失败！");
        }
        return ResponceData.success("图书出库成功!");
    }

    /**
     * 采购图书单据删除
     * @param tsglDeleteEntity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponceData deleteTsFromCg(TsglDeleteEntity tsglDeleteEntity) {
        if(tsglDeleteEntity.getId() == null){
            return ResponceData.bizError("参数丢失！");
        }
        List<String> listCgTs = Arrays.asList(tsglDeleteEntity.getId().split(","));
        //按照开发删除时应该选择对应分类然后传入对应的id,暂时不校验乱输入的id
        Integer cnt = tsglDao.deleteTsFromCg(listCgTs);
        if (cnt == 0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponceData.bizError("采购图书单据删除失败！");
        }
        return ResponceData.success("采购图书单据删除成功!");
    }
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
        if (addCkRequestDTO.getId() == null || addCkRequestDTO.getBookName() == null || addCkRequestDTO.getNum() == null || addCkRequestDTO.getPrice() == null){
            return ResponceData.bizError("参数丢失");
        }
//        List<String> Type = Arrays.asList(addCkRequestDTO.getBookInType().split(","));
        List<String> ids = Arrays.asList(addCkRequestDTO.getId().split(","));
        List<String> BookName = Arrays.asList(addCkRequestDTO.getBookName().split(","));
        List<String> Num = Arrays.asList(addCkRequestDTO.getNum().split(","));
        List<String> Price = Arrays.asList(addCkRequestDTO.getPrice().split(","));
        if (ids.size() != BookName.size() || BookName.size() != Num.size() || Num.size() != Price.size()) {
            return ResponceData.bizError("传参格式错误，请重新输入!");
        }
        // 批量插入数据的数量
        int num = ids.size();
        List<addTsCkResponseDTO> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            addTsCkResponseDTO addCgTs = new addTsCkResponseDTO();
            Integer cfo = tsglDao.findBookByName(BookName.get(i));
            if (cfo == 0){
                addCgTs.setId(UUID.randomUUID().toString());
                addCgTs.setIsBn(generateMixString(9));
                addCgTs.setBookName(BookName.get(i));
                addCgTs.setNum(Integer.parseInt(Num.get(i)));
                addCgTs.setPrice(Float.parseFloat(Price.get(i)));
                list.add(addCgTs);
            }
            else {
                Integer pop = tsglDao.updateNum(BookName.get(i),Integer.parseInt(Num.get(i)));
                if (pop == 0){
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return ResponceData.bizError("采购入库操作失败！");
                }
            }
        }
        if (list.size() > 0){
            int i = tsglDao.addCk(list);
            if (i == 0 ){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResponceData.bizError("采购入库操作失败！");
            }
        }
        int pp = tsglDao.updateStatus(ids);
        if (pp == 0){
            // 事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponceData.bizError("采购入库操作失败！");
        }
        return ResponceData.success("采购入库操作成功！");
    }

    /**
     * 分页查询入库图书
     * @param
     * @return
     */
    public ResponceData ListOfRkTs(ListUsersRequestDTO listUsersRequestDTO) {
        if (listUsersRequestDTO == null || listUsersRequestDTO.getPageNum() <= 0 || listUsersRequestDTO.getPageSize() <=0){
            return ResponceData.bizError("参数丢失");
        }
        PageHelper.clearPage();
        PageHelper.startPage(listUsersRequestDTO.getPageNum(), listUsersRequestDTO.getPageSize());
        List<ListOfCkTsResponseDTO> ListTs = tsglDao.ListOfRkTs(listUsersRequestDTO);
        // 包装Page对象
        PageInfo<ListOfCkTsResponseDTO> pageData = new PageInfo<>(ListTs);
        if (ListTs.size() <=0 ){
            return ResponceData.bizError("已入库图书信息列表查询为空!");
        }
        else {
            return ResponceData.success("已入库图书信息列表查询成功",pageData);
        }
    }
    /**
     * 修改图书
     * @param updateTsRequestDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public  ResponceData updateTs(UpdateTsRequestDTO updateTsRequestDTO) {
        if(updateTsRequestDTO.getId() == null || updateTsRequestDTO.getNum() == null || updateTsRequestDTO.getPrice() == null ||
                updateTsRequestDTO.getBookAuthor() == null || updateTsRequestDTO.getBookPlace() == null || updateTsRequestDTO.getBookPress() == null ||updateTsRequestDTO.getDetail() == null ){
            return ResponceData.bizError("修改图书分类参数丢失！");
        };
        Integer pp = tsglDao.updateTs(updateTsRequestDTO);
        if (pp == 0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponceData.bizError("修改图书失败！");
        }
        return ResponceData.success("修改图书成功！");
    }
}
