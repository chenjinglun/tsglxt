package com.example.ckglixt.dao;

import com.example.ckglixt.dto.userDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface  userMapper {
    public userDTO findByName(@Param("value") String name);

    public userDTO findById(@Param("value") String id);

    Integer insert(userDTO userDTO);

    int deleteUser(@Param("id") String id);
}
