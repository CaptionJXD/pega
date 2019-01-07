package com.lms.redis;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import com.lms.ctaa.pojo.Customs;

public class RedisTemplateTest {
	RedisTemplate redistemplate=null;
	
	@Before
	public void before(){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-mybatis.xml");
		redistemplate=ctx.getBean(RedisTemplate.class);
	}
	
	public void Test(){
		Customs c=new Customs();
		c.setId(1101);
		c.setCustomsName("哗哗");           
		redistemplate.opsForValue().set("customs", c);
		Customs c1=(Customs) redistemplate.opsForValue().get("customs");
		System.out.println(c1.getCustomsName());
		System.out.println(c1.getSuoeriorSustoms());
		
	}

}
