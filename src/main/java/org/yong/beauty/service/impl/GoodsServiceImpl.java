package org.yong.beauty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yong.beauty.cache.RedisCache;
import org.yong.beauty.dao.GoodsDao;
import org.yong.beauty.dao.OrderDao;
import org.yong.beauty.dao.UserDao;
import org.yong.beauty.entity.Goods;
import org.yong.beauty.entity.User;
import org.yong.beauty.enums.ResultEnum;
import org.yong.beauty.exception.BizException;
import org.yong.beauty.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GoodsDao goodsDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private RedisCache cache;

	@Override
	public List<Goods> getGoodsList(int offset, int limit) {
		String cacheKey = RedisCache.CACHENAME + "|getGoodsList|" + offset + "|" + limit;
		List<Goods> resultCache = cache.getListCache(cacheKey, Goods.class);
		if (resultCache != null) {
			LOG.info("get cache with key:" + cacheKey);
		} else {
			resultCache = goodsDao.queryAll(offset, limit);
			cache.putListCacheWithExpireTime(cacheKey, resultCache, RedisCache.CACHETIME);
			LOG.info("put cache with key:" + cacheKey);
			return resultCache;
		}
		return resultCache;
	}

	@Transactional
	@Override
	public void buyGoods(long userPhone, long goodsId, boolean useProcedure) {
		// 用户验证
		User user = userDao.queryByPhone(userPhone);
		if (user == null) {
			throw new BizException(ResultEnum.INVALID_USER.getMsg());
		}

		if (useProcedure) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", user.getUserId());
			map.put("goodsId", goodsId);
			map.put("title", "抢购");
			map.put("result", null);
			goodsDao.bugWithProcedure(map);
			int result = MapUtils.getInteger(map, "result", -1);
			if (result <= 0) {
				// 购买失败
				throw new BizException(ResultEnum.INNER_ERROR.getMsg());
			}else{
				// 购买成功，缓存失效
				cache.deleteCacheWithPattern("getGoodsList");
				LOG.info("delete cache with key: getGoodsList");
				return;
			}
		}else {
			int insertCount = orderDao.insertOrder(user.getUserId(), goodsId, "普通购买");
			
			if (insertCount <= 0) {
				throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
			}else {
				int updateCount = goodsDao.reduceNumber(goodsId);
				if (updateCount <= 0) {
					throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
				}else {
					cache.deleteCacheWithPattern("getGoodsList*");
					LOG.info("delete cache with key: getGoodsList");
					return;
				}
			}
		}
	}

}
