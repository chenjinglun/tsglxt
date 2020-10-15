package com.example.ckglixt.service.impl;

import com.example.ckglixt.dao.userMapper;
import com.example.ckglixt.dto.userDTO;
import com.example.ckglixt.service.userService;
import com.example.ckglixt.utils.ResponceData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class userServiceImpl implements userService {
    //注入Mapper接口
    @Resource
    private com.example.ckglixt.dao.userMapper userMapper;

    @Override
    public userDTO findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public userDTO findById(String id) {
        return userMapper.findById(id);
    }

    @Override
    public Integer insert(userDTO userDTO) {
        return userMapper.insert(userDTO);
    }

    @Override
    public ResponceData deleteUser(String id) {
        Integer cnt = userMapper.deleteUser(id);
        if (cnt == null){
            return ResponceData.bizError("刪除失敗!");
        }
        return ResponceData.success("刪除成功!");
    }

}
