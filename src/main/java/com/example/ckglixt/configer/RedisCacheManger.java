//package com.example.ckglixt.configer;
//
//import org.apache.shiro.cache.Cache;
//import org.apache.shiro.cache.CacheException;
//import org.apache.shiro.cache.CacheManager;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import javax.annotation.Resource;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
//public class RedisCacheManger implements CacheManager {
////    //参数1：认证或者是授权缓存的统一名字
////    @Override
////    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
////        System.out.println(cacheName);
////        return new RedisCache<K,V>();
////    }
////    private long cacheLive;    //cache存活时间
////    private String cacheKeyPrefix;      //cache前缀
////    @Resource
////    private RedisTemplate redisTemplate;
////    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();
////    @Override
////    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
////        Cache cache = this.caches.get(name);
////        if (cache == null) {
////            //自定义shiroCache
////            cache = new RedisCache<K, V>(redisTemplate, cacheLive, cacheKeyPrefix);
////            this.caches.put(name, cache);
////        }
////        return cache;
////    }
////    public void setCacheLive(long cacheLive) {
////        this.cacheLive = cacheLive;
////    }
////    public void setCacheKeyPrefix(String cacheKeyPrefix) {
////        this.cacheKeyPrefix = cacheKeyPrefix;
////    }
////    public void setRedisTemplate(RedisTemplate redisTemplate) {
////        this.redisTemplate = redisTemplate;
////    }
//
//    @Resource
//    private RedisTemplate redisTemplate; // RedisTemplate，如果不明白怎么使用的，请参考http://blog.csdn.net/liuchuanhong1/article/details/54601037
//
//    @Override
//    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
//        System.out.println("name:"+name);
//        return new RedisCache<K, V>(120, redisTemplate);// 为了简化代码的编写，此处直接new一个Cache
//    }
//}
