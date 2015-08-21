package com.befriend.util;

import com.google.gson.annotations.Expose;

public class Contact 
{
	public static int ONLINE = 1;
	public static int OFFLINE = 0;
	@Expose
	private String phone;
	@Expose
	private boolean exist = false;
	@Expose
	private int userId;
	@Expose
	private int status = 0;
	@Expose
	private String userName;
	@Expose
	private String img;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public boolean isExist() {
		return exist;
	}
	public void setExist(boolean exist) {
		this.exist = exist;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}
