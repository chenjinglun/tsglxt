//package com.example.ckglixt.configer;
//
//
//import com.example.ckglixt.utils.ApplicationContextUtils;
//import javafx.application.Application;
//import org.apache.catalina.core.ApplicationContext;
//import org.apache.shiro.cache.Cache;
//import org.apache.shiro.cache.CacheException;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.ui.context.support.UiApplicationContextUtils;
//
//import javax.annotation.Resource;
//import java.util.*;
//import java.util.concurrent.TimeUnit;
//
///**
// * 自定义redis缓存的实现
// * author：scout
// * time:2020年8月31日
// */
//public class RedisCache<K, V> implements Cache<K, V> {
//
////    @Resource
////    private RedisTemplate template;
////    @Override
////    public v get(k k) throws CacheException {
////        System.out.println("get key :"+k);
////        template = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
////        template.setKeySerializer(new StringRedisSerializer());
////        return (v) template.opsForValue().get(k.toString());
//////        return null;
////    }
////
////    @Override
////    public v put(k k, v v) throws CacheException {
////        System.out.println("put key :"+k);
////        System.out.println("put value :"+v);
////        template = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
////        template.setKeySerializer(new StringRedisSerializer());
////
////        template.opsForValue().set(k.toString(),v);
////        return null;
////    }
////
////    @Override
////    public v remove(k k) throws CacheException {
////        return null;
////    }
////
////    @Override
////    public void clear() throws CacheException {
////
////    }
////
////    @Override
////    public int size() {
////        return 0;
////    }
////
////    @Override
////    public Set<k> keys() {
////        return null;
////    }
////
////    @Override
////    public Collection<v> values() {
////        return null;
////    }
////
////    private long cacheLive = 120;
////    private String cacheKeyPrefix;
////    private RedisTemplate redisTemplate;
////
////    @Override
////    public V get(K k) throws CacheException {
////        return (V) this.redisTemplate.opsForValue().get(this.getRedisCacheKey(k));
////    }
////    @Override
////    public V put(K k, V v) throws CacheException {
////        redisTemplate.opsForValue().set(this.getRedisCacheKey(k), v, cacheLive, TimeUnit.MINUTES);
////        return v;
////    }
////    @Override
////    public V remove(K k) throws CacheException {
////        V obj = (V) this.redisTemplate.opsForValue().get(this.getRedisCacheKey(k));
////        redisTemplate.delete(this.getRedisCacheKey(k));
////        return obj;
////    }
////    @Override
////    public void clear() throws CacheException {
////        Set keys = this.redisTemplate.keys(this.cacheKeyPrefix + "*");
////        if (null != keys && keys.size() > 0) {
////            Iterator itera = keys.iterator();
////            this.redisTemplate.delete(itera.next());
////        }
////    }
////
////    @Override
////
////    public int size() {
////        Set<K> keys = this.redisTemplate.keys(this.cacheKeyPrefix + "*");
////        return keys.size();
////    }
////    @Override
////    public Set<K> keys() {
////        return this.redisTemplate.keys(this.cacheKeyPrefix + "*");
////    }
////    @Override
////    public Collection<V> values() {
////        Set<K> keys = this.redisTemplate.keys(this.cacheKeyPrefix + "*");
////        Set<V> values = new HashSet<V>(keys.size());
////        for (K key : keys) {
////            values.add((V) this.redisTemplate.opsForValue().get(this.getRedisCacheKey(key)));
////        }
////        return values;
////    }
////    private String getRedisCacheKey(K key) {
////        Object redisKey = this.getStringRedisKey(key);
////        if (redisKey instanceof String) {
////            return this.cacheKeyPrefix + redisKey;
////        } else {
////
////            return String.valueOf(redisKey);
////        }
////    }
////    private Object getStringRedisKey(K key) {
////        Object redisKey;
////        if (key instanceof PrincipalCollection) {
////            redisKey = this.getRedisKeyFromPrincipalCollection((PrincipalCollection) key);
////        } else {
////            redisKey = key.toString();
////        }
////        return redisKey;
////    }
////    private Object getRedisKeyFromPrincipalCollection(PrincipalCollection key) {
////        List realmNames = this.getRealmNames(key);
////        Collections.sort(realmNames);
////        Object redisKey = this.joinRealmNames(realmNames);
////        return redisKey;
////
////    }
////    private List<String> getRealmNames(PrincipalCollection key) {
////        ArrayList realmArr = new ArrayList();
////        Set realmNames = key.getRealmNames();
////        Iterator i$ = realmNames.iterator();
////        while (i$.hasNext()) {
////            String realmName = (String) i$.next();
////            realmArr.add(realmName);
////        }
////        return realmArr;
////    }
////
////    private Object joinRealmNames(List<String> realmArr) {
////        StringBuilder redisKeyBuilder = new StringBuilder();
////        for (int i = 0; i < realmArr.size(); ++i) {
////            String s = realmArr.get(i);
////            redisKeyBuilder.append(s);
////        }
////        String redisKey = redisKeyBuilder.toString();
////        return redisKey;
////    }
////    public RedisCache(RedisTemplate redisTemplate, long cacheLive, String cachePrefix) {
////
////        this.redisTemplate = redisTemplate;
////
////        this.cacheLive = cacheLive;
////
////        this.cacheKeyPrefix = cachePrefix;
////
////    }
//    private long expireTime = 120;// 缓存的超时时间，单位为s
//
//    private RedisTemplate<K, V> redisTemplate;// 通过构造方法注入该对象
//
//    public RedisCache() {
//        super();
//    }
//
//    public RedisCache(long expireTime, RedisTemplate<K, V> redisTemplate) {
//        super();
//        this.expireTime = expireTime;
//        this.redisTemplate = redisTemplate;
//    }
//
//    /**
//     * 通过key来获取对应的缓存对象
//     * 通过源码我们可以发现，shiro需要的key的类型为Object，V的类型为AuthorizationInfo对象
//     */
//    @Override
//    public V get(K key) throws CacheException {
//        return redisTemplate.opsForValue().get(key);
//    }
//
//    /**
//     * 将权限信息加入缓存中
//     */
//    @Override
//    public V put(K key, V value) throws CacheException {
//        redisTemplate.opsForValue().set(key, value, this.expireTime, TimeUnit.SECONDS);
//        return value;
//    }
//
//    /**
//     * 将权限信息从缓存中删除
//     */
//    @Override
//    public V remove(K key) throws CacheException {
//        V v = redisTemplate.opsForValue().get(key);
//        redisTemplate.opsForValue().getOperations().delete(key);
//        return v;
//    }
//
//    @Override
//    public void clear() throws CacheException {
//
//    }
//
//    @Override
//    public int size() {
//        return 0;
//    }
//
//    @Override
//    public Set<K> keys() {
//        return null;
//    }
//
//    @Override
//    public Collection<V> values() {
//        return null;
//    }
//
//}
//
