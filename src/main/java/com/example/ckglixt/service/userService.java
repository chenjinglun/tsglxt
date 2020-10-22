package com.example.ckglixt.service;

import com.example.ckglixt.dto.userDTO;
import com.example.ckglixt.requestDTO.ListUsersRequestDTO;
import com.example.ckglixt.requestDTO.RegisterRequestDTO;
import com.example.ckglixt.utils.ResponceData;


public interface userService {
    /**
     * 根据名称查找用户
     * @param name
     * @return
     */
    public userDTO findByName(String name);
    /**
     * 注册
     * @param registerRequestDTO
     * @return
     */
    public ResponceData insert(RegisterRequestDTO registerRequestDTO);
    /**
     * 新增用户
     * @param registerRequestDTO
     * @return Response
     * @author scout
     * @Date 2020年10月19日15:22:41.
     */
    public ResponceData saveUser(RegisterRequestDTO registerRequestDTO);
    /**
     * 删除用户
     * @param  id
     * @return
     */
    public ResponceData deleteUser(String id);
    /**
     * 修改用户
     *
     * @param registerRequestDTO
     * @return Response
     * @author scout
     * @Date 2020年10月19日15:59:54
     */
    public ResponceData updateUser(RegisterRequestDTO registerRequestDTO);
    /**
     * 查询用户详情
     *
     * @param user_id
     * @return AResponse
     * @author scout
     * @Date 2020年10月19日16:18:57
     */
    public ResponceData getUserDetail(String user_id);
    /**
     * 用户列表(分页)
     *
     * @param listUsersRequestDTO
     * @return Response
     * @author scout
     * @Date 2020年10月19日16:26:40
     */
    public ResponceData listUsers(ListUsersRequestDTO listUsersRequestDTO);
}
