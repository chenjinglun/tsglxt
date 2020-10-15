package com.example.ckglixt.web.rt;

import com.example.ckglixt.dto.userDTO;
import com.example.ckglixt.service.userService;
import com.example.ckglixt.utils.RandomServlet;
import com.example.ckglixt.utils.ResponceData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@Api(value = "图书管理系统登陆接口")
@RequestMapping("/Api")
@CrossOrigin
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private userService userSerivce;
    @Autowired
    private HttpSession session;
    /**
     * 注销
     * @return
     */
    @RequestMapping("/out")
    public ResponceData doLogOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResponceData.success("注销成功");
    }
    /**
     * 设置登录
     * @param code
     * @return
     */
    @GetMapping(value = "/autherror")
    @ApiOperation(value="设置未登录未授权跳转页", notes="无需使用")
    public String autherror(int code){
        return  code == 1?"未登录啦":"未授权哦";
    }
    /**
     * 登录逻辑处理
     */
    @PostMapping(value = "/login")
    @ApiOperation(value="账号登陆", notes="没有返回值")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "name", value = "账号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "code", value = "验证码", required = true, dataType = "String")
    })
    public ResponceData login(String name, String password,String code){
        String codes = (String) session.getAttribute("verCode");
        if (codes == null){
            codes = "1";
        }
        /**
         * 对密码进行加密校验
         */
        Md5Hash md5Hash2 = new Md5Hash(password,"xo*7ps",1024);
        password = md5Hash2.toString();
        /**
         * realm设置md5加密规则
         */
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");

        /**
         * 使用Shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
//        //1.1获取session
        String sid = (String)subject.getSession().getId();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        //3.执行登录方法
        try {
            if (codes.equalsIgnoreCase(code)){
                //登录成功
                //跳转到test.html
                subject.login(token);
                return ResponceData.success("登录成功",sid);
            }
            else {
                return ResponceData.bizError("验证码错误");
            }
        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            //登录失败:用户名不存在
            return ResponceData.bizError("用户名不存在");
        }catch (IncorrectCredentialsException e) {
            //e.printStackTrace();
            //登录失败:密码错误
            return ResponceData.bizError("密码错误");
        }
    }
    /**
     * 注册（也做的，但是用不上）
     */
    @PostMapping(value = "/register")
    public ResponceData register(userDTO userDTO ,String code,HttpSession session){
        System.out.println("进入这里：---------------------");
        /**
         * 对密码进行加密校验
         */
        Md5Hash md5Hash2 = new Md5Hash(userDTO.getUserPassWord(),"xo*7ps",1024);
        userDTO.setUserPassWord(md5Hash2.toString());
        userDTO.setUserID(UUID.randomUUID().toString());
        //3.执行保存方法
        try {
            Integer success = userSerivce.insert(userDTO);
            return ResponceData.success("注册成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResponceData.bizError("注册失败");
        }
        }

    /**
     * 验证码获取
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping(value="/getImage")
    @ApiOperation(value="验证码获取", notes="获取验证码：无需传值")
    public void authImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 生成随机字串
        String verifyCode = RandomServlet.generateVerifyCode(4);
        // 存入会话session
        session = request.getSession(true);
        // 删除以前的
        session.removeAttribute("verCode");
        session.removeAttribute("codeTime");
        session.setAttribute("verCode", verifyCode.toLowerCase());
        session.setAttribute("codeTime", LocalDateTime.now());
        // 生成图片
        int w = 100, h = 45;
        OutputStream out = response.getOutputStream();
        RandomServlet.outputImage(w, h, out, verifyCode);
    }

}
