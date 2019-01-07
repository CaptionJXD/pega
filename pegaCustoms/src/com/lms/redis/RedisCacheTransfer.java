package com.lms.redis;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

public class RedisCacheTransfer {
	
	
	//private static JedisConnectionFactory jedisConnectionFactory;
	@Autowired
	public void setConnectionFactory(JedisConnectionFactory jedisConnectionFactory){
		//Spring 会找名字叫connectionFactory bean。set前边字符
		System.out.println(jedisConnectionFactory+"$$$$$$$$$$$$$$$$$$$$$$$$");
		RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
	}

}
