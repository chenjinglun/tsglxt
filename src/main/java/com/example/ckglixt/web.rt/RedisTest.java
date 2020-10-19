//package com.example.ckglixt.web.rt;
//
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/test")
//public class RedisTest {
//    private StringRedisTemplate templatel;
//    ValueOperations<String,String>redisOperations;
//    RedisTest(StringRedisTemplate templatel){
//        this.templatel=templatel;
//        redisOperations=templatel.opsForValue();
//    }
//    @GetMapping("/set")
//    public String set(){
//        redisOperations.set("namexyx","xyz");
//        return "sucess";
//    }
//    @GetMapping("/get")
//    public String get(){
//        return redisOperations.get("namexyx");
//    }
//    @GetMapping("/hashkey")
//    public String hashkey(){
//        return templatel.hasKey("namexyx").toString();
//    }
//
//
//}
