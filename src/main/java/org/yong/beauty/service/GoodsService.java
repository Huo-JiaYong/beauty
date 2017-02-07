package org.yong.beauty.service;

import java.util.List;

import org.yong.beauty.entity.Goods;

public interface GoodsService {

	/**
	 * 根据偏移量获取商品列表
	 * 
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Goods> getGoodsList(int offset, int limit);

	/**
	 * 购买商品
	 * 
	 * @param userPhone
	 * @param goodsId
	 * @param useProcedure
	 *            是否使用存储过程
	 */
	void buyGoods(long userPhone, long goodsId, boolean useProcedure);
}
