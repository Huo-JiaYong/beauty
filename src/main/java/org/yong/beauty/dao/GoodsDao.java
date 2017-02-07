package org.yong.beauty.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.yong.beauty.entity.Goods;

public interface GoodsDao {

	/**
	 * 根据偏移量查询可用商品列表
	 * @param offset
	 * @param limit
	 * @return
	 */
    List<Goods> queryAll(@Param("offset") Integer offset, @Param("limit") Integer limit);
	
	/**
	 * 商品减库存
	 * @param goodsId
	 * @return
	 */
	int reduceNumber(long goodsId);
	
	/**
	 * 使用存储过程执行抢购
	 * 
	 * 1.减少多个sql语句执行来回的网络延迟
	 * 2.通过mysql自身的事物提升效率
	 * 
	 * @param paramMap
	 */
	void bugWithProcedure(Map<String, Object> paramMap);
}
