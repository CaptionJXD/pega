package com.lms.redis;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

public class RedisCache implements Cache {
	private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);
	
	private static JedisConnectionFactory jedisConnectionFactory;
	
	private final String id;
	
    private final ReadWriteLock readWriteLook = new ReentrantReadWriteLock();
    

    public RedisCache(final String id){
    	if(null==id){
    		throw new IllegalArgumentException("Cache instance require an ID");
    	}
    	logger.debug("MyBatisRedisCache:id"+id);
    	System.out.println(id+"%%%%%%%%%%%%%%%%%%%%%%");
    
    	
    	this.id=id;
    }
	@Override
	public String getId() {
		
		return this.id;
	}

	@Override
	public int getSize() {
		int result=0;
		JedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			result = Integer.valueOf(connection.dbSize().toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(connection != null){
				connection.close();
				}
		}
		return result;
	}

	@Override
	public void putObject(Object key, Object value) {
		JedisConnection connection = null;
		try {
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>putObject:"+key+"="+value);
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			connection.set(serializer.serialize(key),serializer.serialize(value));	
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(connection != null){
			connection.close();
			}
		}
		
	}

	@Override
	public Object getObject(Object key) {
		Object result = null;
		JedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			result = serializer.deserialize(connection.get(serializer.serialize(key)));
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(connection != null){
			connection.close();
			}
		}
		
		return result;
	}

	@Override
	public Object removeObject(Object key) {
		JedisConnection connection = null;
		Object result = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			result = connection.expire(serializer.serialize(key), 0);
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			if(connection !=null){
				connection.close();
			}
		}
		return result;
	}

	@Override
	public void clear() {
		JedisConnection connection =null;
		try {
			connection = jedisConnectionFactory.getConnection();
			connection.flushDb();
			connection.flushAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(connection !=null){
				connection.close();
			}
		}
		
	}


	public static void setJedisConnectionFactory(
			JedisConnectionFactory jedisConnectionFactory) {
		RedisCache.jedisConnectionFactory = jedisConnectionFactory;
	}
	@Override
	public ReadWriteLock getReadWriteLock() {
		// TODO Auto-generated method stub
		return this.readWriteLook;
	}
	

}
