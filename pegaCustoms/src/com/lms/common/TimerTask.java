package com.lms.common;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimerTask {
	@Scheduled(cron = "36 0 0 1 * ?")//每月1号0点0分36秒触发
	public void test(){	
		System.out.println("job2 开始执行");
	}

}
