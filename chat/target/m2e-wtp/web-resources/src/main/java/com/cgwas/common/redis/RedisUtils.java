package com.cgwas.common.redis;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 类RedisUtils.java的实现描述：Redis操作类
 * 
 * @author yubangqiong 
 */
@Component
public class RedisUtils {

    private RedisTemplate<String, String> redisTemplate;

    public String createKey(String prefix) {
        return prefix + UUID.randomUUID().toString().replaceAll("\\-", "");
    }

    /**
     * 往redis中放需要缓存的内容，默认失效时间30分钟
     * 
     * @param key
     * @param value
     */
    public void setValue(String key, String value) {
        setValue(key, value, 1800L);
    }

    /**
     * 往redis中放需要缓存的内容
     * 
     * @param key
     * @param value
     * @param timeout
     * @param timeType 设置过期时间类型
     */
    public void setValue(String key, String value, Long timeout,int timeType) {
        redisTemplate.opsForValue().set(key, value);
        if(timeType == 1){
        	redisTemplate.expire(key, timeout, TimeUnit.MINUTES); //分钟
        }else if(timeType == 2){
        	redisTemplate.expire(key, timeout, TimeUnit.HOURS); //小时
        }else if(timeType == 3){
        	redisTemplate.expire(key, timeout, TimeUnit.DAYS); //天
        }
    }
    
    /**
     * 往redis中放需要缓存的内容
     * 
     * @param key
     * @param value
     * @param timeout
     */
    public void setValue(String key, String value, Long timeout) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, timeout == null ? 1800L : timeout, TimeUnit.SECONDS);
    }

    /**
     * 从redis中取出缓存的内容
     * 
     * @param key
     * @return
     */
    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    
	public RedisTemplate<String,String> getRedisTemplate() {
		return redisTemplate;
	}

	@Resource(name = "redisTemplate")
	public void setRedisTemplate(RedisTemplate<String,String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
    
}
