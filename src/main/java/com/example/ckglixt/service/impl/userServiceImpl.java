package com.example.ckglixt.service.impl;

import com.example.ckglixt.dao.userMapper;
import com.example.ckglixt.dto.userDTO;
import com.example.ckglixt.requestDTO.ListUsersRequestDTO;
import com.example.ckglixt.requestDTO.RegisterRequestDTO;
import com.example.ckglixt.service.userService;
import com.example.ckglixt.utils.ResponceData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class userServiceImpl implements userService {

    //注入Mapper接口
    @Resource
    private com.example.ckglixt.dao.userMapper userMapper;
    /**
     * 根据用户名查找用户信息
     * @param name
     * @return
     */
    @Override
    public userDTO findByName(String name) {
       return  userMapper.findByName(name);
    }

    /**
     * 注册
     * @param registerRequestDTO
     * @return
     */
    @Override
    public ResponceData insert(RegisterRequestDTO registerRequestDTO) {
        if (registerRequestDTO.getUserAccout() ==null || registerRequestDTO.getUserPassWord() == null ||registerRequestDTO.getUserSex() == null || registerRequestDTO.getUserPhone() == null){
            return ResponceData.bizError("所需参数不完整，请重新输入");
        }
        /**
         * 对密码进行加密校验
         */
        Md5Hash md5Hash2 = new Md5Hash(registerRequestDTO.getUserPassWord(),"xo*7ps",1024);
        registerRequestDTO.setUserPassWord(md5Hash2.toString());
        registerRequestDTO.setRole("2");
        registerRequestDTO.setUserID(UUID.randomUUID().toString());
        Integer count = userMapper.findUserAcout(registerRequestDTO.getUserAccout());
        if (count == 1){
            return ResponceData.bizError("用户已存在，请重新输入");
        }
        Integer cnt = userMapper.insert(registerRequestDTO);
        if (cnt == 1){
            return ResponceData.success("注册成功");
        }
        return ResponceData.bizError("注册失败");
    }
    /**
     * 删除用户
     * @param  user_id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponceData deleteUser(String user_id) {
        if (user_id == null){
            return ResponceData.bizError("参数丢失");
        }
        List<String> listylYl = Arrays.asList(user_id.split(","));
        Integer cnt = userMapper.deleteUser(listylYl);
        if (cnt == null){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponceData.bizError("用户刪除失敗!");
        }
        return ResponceData.success("用户刪除成功!");
    }
    /**
     * 新增用户
     * @param registerRequestDTO
     * @return Response
     * @author scout
     * @Date 2020年10月19日15:22:41.
     */
    @Override
    public ResponceData saveUser(RegisterRequestDTO registerRequestDTO) {
        if (registerRequestDTO.getUserAccout() ==null || registerRequestDTO.getUserPassWord() == null ||registerRequestDTO.getUserSex() == null || registerRequestDTO.getUserPhone() == null
        || registerRequestDTO.getRole() == null){
            return ResponceData.bizError("所需参数不完整，请重新输入");
        }
        registerRequestDTO.setUserID(UUID.randomUUID().toString());
        Md5Hash md5Hash2 = new Md5Hash(registerRequestDTO.getUserPassWord(),"xo*7ps",1024);
        registerRequestDTO.setUserPassWord(md5Hash2.toString());
        Integer count = userMapper.findUserAcout(registerRequestDTO.getUserAccout());
        if (count == 1){
            return ResponceData.bizError("用户已存在，请重新输入");
        }
        Integer pp = userMapper.addUser(registerRequestDTO);
        if (pp == 1){
            return ResponceData.success("新增用户成功！");
        }
        return ResponceData.bizError("新增用户失败！");
    }
    /**
     * 修改用户
     *
     * @param registerRequestDTO
     * @return Response
     * @author scout
     * @Date 2020年10月19日15:59:54
     */
    //有缺陷后期做,角色修改问题
    @Override
    public ResponceData updateUser(RegisterRequestDTO registerRequestDTO) {
        if (registerRequestDTO.getUserPassWord() == null ||registerRequestDTO.getUserSex() == null || registerRequestDTO.getUserPhone() == null
                 ||registerRequestDTO.getUserID() == null || registerRequestDTO.getRole() == null ){
            return ResponceData.bizError("所需参数不完整，请重新输入");
        }
        Md5Hash md5Hash2 = new Md5Hash(registerRequestDTO.getUserPassWord(),"xo*7ps",1024);
        registerRequestDTO.setUserPassWord(md5Hash2.toString());
        Integer pp = userMapper.updateUser(registerRequestDTO);
        if (pp == 1){
            return ResponceData.success("修改用户成功！");
        }
        return ResponceData.bizError("修改用户失败！");
    }
    /**
     * 查询用户详情
     *
     * @param user_id
     * @return AResponse
     * @author scout
     * @Date 2020年10月19日16:18:57
     */
    @Override
    public ResponceData getUserDetail(String user_id) {
        if (user_id == null){
            return ResponceData.bizError("参数丢失!");
        }
        RegisterRequestDTO registerRequestDTO = userMapper.getUserDetail(user_id);
        if (registerRequestDTO != null ){
            return ResponceData.success("查询用户详情成功！",registerRequestDTO);
        }
        return ResponceData.bizError("查询用户详情失败！");
    }
    /**
     * 用户列表(分页)
     *
     * @param listUsersRequestDTO
     * @return Response
     * @author scout
     * @Date 2020年10月19日16:26:40
     */
    @Override
    public ResponceData listUsers(ListUsersRequestDTO listUsersRequestDTO) {
        if(listUsersRequestDTO == null || listUsersRequestDTO.getPageNum() <= 0 || listUsersRequestDTO.getPageNum() <= 0){
            return ResponceData.bizError("参数传递错误或者丢失！");
        }
        PageHelper.clearPage();
        PageHelper.startPage(listUsersRequestDTO.getPageNum(), listUsersRequestDTO.getPageSize());
        List<RegisterRequestDTO> lists = userMapper.listUsers(listUsersRequestDTO);
        if (lists.size() != 0) {
            return ResponceData.success("查询用户分页列表成功！",new PageInfo<>(lists));
        } else {
            return ResponceData.bizError("查询用户分页列表失败");
        }
    }

    @Override
    public ResponceData findRoleById(String name) {
        if (name == null ){
            return ResponceData.bizError("参数传递错误或者丢失！");
        }
        String role = userMapper.findRoleById(name);
        if (role != null ){
            return ResponceData.success(role);
        }
        return ResponceData.bizError("角色查询失败");
    }
}
