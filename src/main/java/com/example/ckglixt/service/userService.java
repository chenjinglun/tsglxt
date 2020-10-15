package com.example.ckglixt.service;

import com.example.ckglixt.dto.userDTO;
import com.example.ckglixt.utils.ResponceData;

public interface userService {
    public userDTO findByName(String name);

    public userDTO findById(String id);

    public Integer insert(userDTO userDTO);

    public ResponceData deleteUser(String id);
}
