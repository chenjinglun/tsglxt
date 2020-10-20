package com.example.ckglixt.configer;


//import com.example.ckglixt.session.LoginSessionManger;
import com.example.ckglixt.session.LoginSessionManger;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class shiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setLoginUrl("/Api/autherror?code=1");
        shiroFilterFactoryBean.setUnauthorizedUrl("/Api/autherror?code=2");

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加Shiro内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *    常用的过滤器：
         *       anon: 无需认证（登录）可以访问
         *       authc: 必须认证才可以访问
         *       user: 如果使用rememberMe的功能可以直接访问
         *       perms： 该资源必须得到资源权限才可以访问
         *       role: 该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap = new LinkedHashMap<String,String>();
        //授权过滤器
        //注意：当前授权拦截后，shiro会自动跳转到未授权页面
//        filterMap.put("/add", "perms[user:add]");
//        filterMap.put("/update", "perms[user:update]");
//        filterMap.put("/*", "authc");
        filterMap.put("/Api/autherror", "anon");
//        filterMap.put("/Api/ListUsers", "roles[0]");
        filterMap.put("/Api/getImage", "anon");
        filterMap.put("/Api/register", "anon");
        filterMap.put("/Api/out", "authc");
//        filterMap.put("/Api/user/deleteUser","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManger(@Qualifier("shirorealm") shirorealm shirorealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(shirorealm);
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        // 自定义缓存实现 使用redis的cachemanger
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    /**
     * redis控制器，操作redis 结合笔记场景图
     */
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setTimeout(1800);
        return redisManager;
    }
    /**
     * sessionDao
     */
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setRedisManager(redisManager());
        return  sessionDAO;
    }
    /**
     * 会话管理器
     */
    public DefaultWebSessionManager sessionManager(){
        LoginSessionManger sessionManger = new LoginSessionManger();
        sessionManger.setSessionDAO(redisSessionDAO());
        return  sessionManger;
    }
    /**
     * 缓存管理器
     */
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setPrincipalIdFieldName("userID");
        redisCacheManager.setExpire(18000);
        return redisCacheManager;
    }

    /**
     * 创建Realm
     */
    @Bean(name = "shirorealm")
    public  shirorealm getRealm(){
        shirorealm shirorealm = new shirorealm();
//        //开始缓存管理
//        shirorealm.setCacheManager(new EhCacheManager());
//        shirorealm.setCachingEnabled(true);//开启全局缓存
//        shirorealm.setAuthenticationCachingEnabled(true);//认证缓存
//        shirorealm.setAuthenticationCacheName("authenticationCache");
//        shirorealm.setAuthorizationCachingEnabled(true);//开始缓存授权
//        shirorealm.setAuthorizationCacheName("authorizationCache");

        return shirorealm;

    }
    /**
     * 开启对shrio注解的支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;

    }
}
