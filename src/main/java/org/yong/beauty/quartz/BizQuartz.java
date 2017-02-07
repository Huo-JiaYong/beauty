package org.yong.beauty.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.yong.beauty.cache.RedisCache;
import org.yong.beauty.dao.UserDao;

@Component
public class BizQuartz {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RedisCache redisCache;
	
	@Scheduled(cron="0 0/1 9-17 * * ? ")
	public void addUserScore(){
		LOG.info("@Scheduled ------- addUserScore");
		userDao.addScore(10);
	}
	
	@Scheduled(cron = "0 0/5 * * * ? ")
	public void cacheClear() {
		LOG.info("@Scheduled ------ cacheClear");
		redisCache.clearCache();
	}
	
}