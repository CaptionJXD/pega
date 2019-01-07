package com.lms.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;



public class JedisPoolUtil  {
	private static volatile JedisPool jedispool=null;
	private JedisPoolUtil (){};	
	
	public static JedisPool getJedisPoolInstance(){
		if(jedispool==null){
			synchronized (JedisPoolUtil.class) {
				if(jedispool==null){
					JedisPoolConfig poolConfig=new JedisPoolConfig();
					poolConfig.setMaxTotal(1000);
					poolConfig.setMaxIdle(32);
					poolConfig.setMaxWaitMillis(100*1000);
					poolConfig.setTestOnBorrow(true);				
					jedispool = new JedisPool(poolConfig,"127.0.0.1",6379);									
				}
				
			}
		}
		
		return jedispool;
	}
	
}
