package com.example.ckglixt.dao;


import com.example.ckglixt.requestDTO.*;
import com.example.ckglixt.responseDTO.ListOfCgTsResponseDTO;
import com.example.ckglixt.responseDTO.ListOfCkTsResponseDTO;
import com.example.ckglixt.responseDTO.addTsCkResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TsglMapper {
    /**
     * 采购图书
     * @param list
     * @return
     */
    Integer addTs(List<TsglAddRequestDTO>list);
    /**
     * 根据名称判断图书是否存在
     * @param
     * @return
     */
    Integer findBookByName(@Param("bookName") String bookName);
    /**
     * 删除图书
     * @param id
     * @return
     */
    Integer deleteTs(List<String>id);
    /**
     * 采购图书单据删除
     * @param id
     * @return
     */
    Integer deleteTsFromCg(List<String>id);
    /**
     * 图书列表分页查询
     * @return
     */
    List<ListOfCgTsResponseDTO> ListOfTs();
    /**
     * 采购入库操作
     * @param list
     * @return
     */
    Integer addCk(List<addTsCkResponseDTO>list);
    /**
     * 采购入库操作->更新采购状态
     * @param list
     * @return
     */
    Integer updateStatus(List<String>list);
    /**
     * 采购入库操作->更新数量
     * @param Num,bookName
     * @return
     */
    Integer updateNum(@Param("bookName") String bookName,@Param("Num") Integer Num);
    /**
     * 入库图书列表分页查询
     * @return
     */
    List<ListOfCkTsResponseDTO> ListOfRkTs(ListUsersRequestDTO listUsersRequestDTO);
    /**
     * 修改图书
     * @param updateTsRequestDTO
     * @return
     */
    Integer updateTs(UpdateTsRequestDTO updateTsRequestDTO);

}
