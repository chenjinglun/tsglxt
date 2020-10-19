package com.example.ckglixt.web.rt;



import com.example.ckglixt.requestDTO.ListUsersRequestDTO;
import com.example.ckglixt.requestDTO.RegisterRequestDTO;
import com.example.ckglixt.service.userService;
import com.example.ckglixt.utils.ResponceData;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/Api")
public class Usercontroller {
    private static final Logger logger = LoggerFactory.getLogger(Usercontroller.class);

    @Resource
    private com.example.ckglixt.service.userService userService;
    /**
     * 新增用户
     * @param registerRequestDTO
     * @return Response
     * @author scout
     * @Date 2020年10月19日15:22:41.
     */
    @PostMapping("saveUser")
    @ApiOperation(value="新增用户", notes="没有返回值")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "用户id", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "userAccout", value = "用户账号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "userPassWord", value = "用户密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "userName", value = "用户名名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "userSex", value = "用户性别：0男1女", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "userPhone", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "role", value = "0为超级管理员，1为管理员，2为客户", required = true, dataType = "String")
    })
    public ResponceData saveUser(RegisterRequestDTO registerRequestDTO){
        try{
            return userService.saveUser(registerRequestDTO);
        }catch (Exception e){
            logger.error("用户新增失败",e);
            System.out.println(e.toString());
            throw  e;
        }
    }

    /**
     * 修改用户
     *
     * @param registerRequestDTO
     * @return Response
     * @author scout
     * @Date 2020年10月19日15:59:54
     */

    @PostMapping("updateUser")
    @ApiOperation(value="修改用户", notes="没有返回值")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userID", value = "用户id", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "userAccout", value = "用户账号", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "userPassWord", value = "用户密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "userName", value = "用户名名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "userSex", value = "用户性别：0男1女", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "userPhone", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "role", value = "有缺陷后期做,角色修改问题，为超级管理员，1为管理员，2为客户", required = false, dataType = "String")
    })
    public ResponceData updateUser(RegisterRequestDTO registerRequestDTO){
            try{
                return userService.updateUser(registerRequestDTO);
        }catch (Exception e){
            logger.error("修改用户失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 删除用户
     * @param user_id
     * @return Response
     * @author scout
     * @Date 2020-03-25
     */

      @PostMapping("deleteUser")
      @ApiOperation(value="删除用户", notes="没返回值，删除用户")
      @ApiImplicitParams({
              @ApiImplicitParam(paramType="query", name = "user_id", value = "用户id", required = true, dataType = "String")
      })
    public ResponceData deleteUser(String user_id){
          try{
              return userService.deleteUser(user_id);
          }catch (Exception e){
              logger.error("用户删除失败",e);
              System.out.println(e.toString());
              throw e;
          }
      }
    /**
     * 查询用户详情
     *
     * @param user_id
     * @return AResponse
     * @author scout
     * @Date 2020年10月19日16:18:57
     */
      @PostMapping("getUserDetail")
      @ApiOperation(value="查询用户详情", notes="返回用户信息")
      @ApiImplicitParams({
              @ApiImplicitParam(paramType="query", name = "user_id", value = "用户id", required = true, dataType = "String")
      })
      public ResponceData getUserDetail(String user_id) {
          try {

              return userService.getUserDetail(user_id);
          } catch (Exception e) {
              logger.error("用户查询失败",e);
              System.out.println(e.toString());
              throw e;
          }
      }
    /**
     * 用户列表(分页)
     *
     * @param listUsersRequestDTO
     * @return Response
     * @author scout
     * @Date 2020年10月19日16:26:40
     */
    @PostMapping(value = "ListUsers")
    @ApiOperation(value="用户列表分页查询", notes="返回用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "页码", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "页数", required = true, dataType = "int")
    })
    public ResponceData listUsers(ListUsersRequestDTO listUsersRequestDTO){
        try{
            return userService.listUsers(listUsersRequestDTO);
        }catch (Exception e){
            logger.error("用户查询错误");
            System.out.println(e.toString());
            throw e;
        }
    }
}
