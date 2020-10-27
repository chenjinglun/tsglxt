package com.example.ckglixt.dao;

import com.example.ckglixt.dto.userDTO;
import com.example.ckglixt.requestDTO.ListUsersRequestDTO;
import com.example.ckglixt.requestDTO.RegisterRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface  userMapper {
    /**
     * 查找用户是否存在
     * @param userAcout
     * @return
     */
     Integer findUserAcout(@Param("userAcout") String userAcout);
    /**
     * 根据用户名查找用户信息
     * @param value
     * @return
     */
    userDTO findByName(@Param("value") String value);
    /**
     * 注册
     * @param registerRequestDTO
     * @return
     */
    Integer insert(RegisterRequestDTO registerRequestDTO);
    /**
     * 删除用户
     * @param  listylYl
     * @return
     */
    int deleteUser(List<String>listylYl);
    /**
     * 新增用户
     * @param registerRequestDTO
     * @return Response
     * @author scout
     * @Date 2020年10月19日15:22:41.
     */
    Integer addUser(RegisterRequestDTO registerRequestDTO);
    /**
     * 修改用户
     *
     * @param registerRequestDTO
     * @return Response
     * @author scout
     * @Date 2020年10月19日15:59:54
     */
    Integer updateUser(RegisterRequestDTO registerRequestDTO);
    /**
     * 查询用户详情
     *
     * @param user_id
     * @return AResponse
     * @author scout
     * @Date 2020年10月19日16:18:57
     */
    RegisterRequestDTO getUserDetail(@Param("user_id") String user_id);
    /**
     * 用户列表(分页)
     * @param
     * @return Response
     * @author scout
     * @Date 2020年10月19日16:26:40
     */
    List<RegisterRequestDTO>listUsers(ListUsersRequestDTO listUsersRequestDTO);
    /**
     * 用户列表(分页)
     * @param
     * @return Response
     * @author scout
     * @Date 2020年10月19日16:26:40
     */
    String findRoleById(String name);
}
