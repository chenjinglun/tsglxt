package com.example.ckglixt.dao;


import com.example.ckglixt.requestDTO.TsglAddEntity;
import com.example.ckglixt.requestDTO.TsglAddRequestDTO;
import com.example.ckglixt.requestDTO.TsglDeleteEntity;
import com.example.ckglixt.responseDTO.ListOfCgTsResponseDTO;
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
//    /**
//     * 根据编号或者名称判断图书是否存在
//     * @param
//     * @return
//     */
//    Integer findEixtTs(@Param("Name") String Name, @Param("No") String No);
//    /**
//     * 根据ID查找图书
//     * @param
//     * @return
//     */
//    Integer findTsByIdl(@Param("id") String id);
//    /**
//     * 删除图书
//     * @param tsglDeleteEntity
//     * @return
//     */
//    void deleteTs(TsglDeleteEntity tsglDeleteEntity);
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
//    /**
//     * 根据名称是否重复
//     * @param
//     * @return
//     */
//    Integer judgeRepeat1(@Param("Name") String name, @Param("id") String id);
//    /**
//     * 根据编号是否重复
//     * @param
//     * @return
//     */
//    Integer judgeRepeat2(@Param("No") String No, @Param("id") String id);
//    /**
//     * 修改分类
//     * @param tsglAddEntity
//     * @return
//     */
//    void updateTs(TsglAddEntity tsglAddEntity);

}
