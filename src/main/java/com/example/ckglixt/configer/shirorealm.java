package com.example.ckglixt.configer;

import com.example.ckglixt.dto.userDTO;
import com.example.ckglixt.service.impl.userServiceImpl;
import com.example.ckglixt.service.userService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;

/**
 * 自定义realm
 */
public class shirorealm extends AuthorizingRealm {
    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        System.out.println("执行授权逻辑");

        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //添加资源的授权字符串
        //info.addStringPermission("user:add");

        //到数据库查询当前登录用户的授权字符串
        //获取当前登录用户
//        Subject subject = SecurityUtils.getSubject();
//        userDTO user = (userDTO)subject.getPrincipal();
//        userDTO dbUser = userSerivce.findById(user.getUserID());
//
////        info.addStringPermission(dbUser.getPerms());

        return info;
    }

    @Resource
    private userService userSerivce;

    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        //编写shiro判断逻辑，判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken)arg0;
        userDTO user = userSerivce.findByName(token.getUsername());
        if(user==null){
            //用户名不存在
            return null;//shiro底层会抛出UnKnowAccountException
        }
        //2.判断密码
        return new SimpleAuthenticationInfo(user,user.getUserPassWord(),"");
    }

}
