package org.yong.beauty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.yong.beauty.entity.User;

public interface UserDao {

	/**
	 * 根据手机号获取用户
	 * @param userPhone 
	 * @return
	 */
	User queryByPhone(long userPhone);

	/**
	 * 根据偏移量获取用户列表
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<User> queryAll(@Param("offset") int offset, @Param("limit") int limit);

	/**
	 * 所有用户新增积分
	 * @param add
	 */
	void addScore(@Param("add") int add);
}
