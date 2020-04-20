package com.xb.crm.web.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @Description: <P>Redis缓存配置类</P>
 * @author: xiongbiao
 * @since: 2020/4/16 14:18
 * @history: 1.2020/4/16 created by xiongbiao
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
//public class RedisConfig{

    private final static Logger LOG = LoggerFactory.getLogger(RedisConfig.class);
    /**
     * 序列化配置
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate
    (LettuceConnectionFactory redisConnectionFactory) {
        LOG.info("RedisConfig == >> redisTemplate ");
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//        // 初始化缓存管理器，在这里我们可以缓存的整体过期时间什么的，我这里默认没有配置
//        LOG.info("初始化 -> [{}]", "CacheManager RedisCacheManager Start");
//        RedisCacheManager manager = RedisCacheManager.create(connectionFactory);
//        return manager;
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
//        //设置序列化
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        // 配置redisTemplate
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
//        redisTemplate.setConnectionFactory(connectionFactory);
//        RedisSerializer stringSerializer = new StringRedisSerializer();
//        // key序列化
//        redisTemplate.setKeySerializer(stringSerializer);
//        // value序列化
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        // Hash key序列化
//        redisTemplate.setHashKeySerializer(stringSerializer);
//        // Hash value序列化
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }

}
