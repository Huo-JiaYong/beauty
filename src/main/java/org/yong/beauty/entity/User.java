package org.yong.beauty.entity;

import java.util.Date;

/**
 * 用户
 * @author Yong
 *
 */
public class User {

	private long userId;
	
	private String userName;
	
	private long userPhone;
	
	private Date crateTime;
	
	private int score;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}

	public Date getCrateTime() {
		return crateTime;
	}

	public void setCrateTime(Date crateTime) {
		this.crateTime = crateTime;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}
