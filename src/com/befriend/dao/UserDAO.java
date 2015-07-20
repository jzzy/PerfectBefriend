package com.befriend.dao;

import java.util.List;

import com.befriend.entity.Password;
import com.befriend.entity.User;

public interface UserDAO
{
	// 删除
	public void remove(Password password);
	// 修改
	public void update(Password password);
	//同步密码
	public void save(Password password);
	//通过uid 密码 验证密码
	public Password login(int uid, String password);
	//通过uid 密码 验证密码
	public Password select(int uid);
	
	
	
	
	
	// 登入  通过 (手机号  用户号  用户名 )+密码  都可以登入
	//public User login(String username, String password);
	//通过 手机号  用户号  用户名 查询 是否为空
	public User byUsernameAccnumnoPhone(String uap);
	/**
	// 通过用户名查
	public User byusername(String username);
	// 查看 通过用户号
	public User accnumNo(String accnumno);	
	// 通过手机号查询
	public User byphone(String phone);
	*/
	
	// 注册
	public void save(User user);

	// 删除
	public void remove(User user);

	// 修改
	public void update(User user);
	//一键下线
	public void updateAllXiaxian();
	// 通过id查询
	public User byid(int id);

	
	// 通过用户名模糊查询
	public List<User> likeusername(String username);
	// 通过用户名模糊查询
	public List<User> likeusername(String username,String area);

	
	// 查询非管理员的用户
	public List<User> getUser(int pageSize, int currentPage);
	// 查询非管理员的用户  按照 省级
	public List<User> getUser(int pageSize, int currentPage,String area);
	public List<User> getUser();
	//按省  查询非管理
	public List<User> getUser(String area);
	// 查询管理员的用户
	public List<User> getUseradmin(int pageSize, int currentPage);
	// 查询某省管理员
	public List<User> getUseradmin(int pageSize, int currentPage,String area);
	//查询  管理
	public List<User> getUseradmin();
	//按省查询管理
	public List<User> getUseradmin(String area);
	//查询什么时间到什么时间注册的用户
	public int getUsertime(String timeq,String timeh);
	//查询全部用户
	public List<User> getUserAll();
	//查询全部用户
	public int getCount();
	//查询全部用户
	public int getCountSyn();
		
	//查询syn全部用户
	public List<User> getUsersynAll();
	
	//查询全部用户 分页查询
	public List<User> getUserAll(int pageSize, int currentPage);
	/**
	 * 查询省级用户 +按照什么时间至什么时间查询 省 +市 + 开始时间 +结束时间
	 * @param area
	 * @return
	 */
	public List<User> getUserAll(String area,String timeq,String timeh);
	/**
	 * 查询省级+市级用户 +按照什么时间至什么时间查询 省 +市 + 开始时间 +结束时间
	 * @param area
	 * @param areas
	 * @return
	 */
	public List<User> getUserAll(String area,String areas,String timeq,String timeh);
	/**
	 * 查询省级+市级用户  
	 * @param area
	 * @param areas
	 * @return
	 */
	public List<User> getUserAll(String area,String areas);
	/**
	 * 查询省级用户 查看  有多少市
	 * @param area
	 * @return
	 */
	public List<User> getUserAll(String area);
	/**
	 * 查询在线用户
	 * @param area
	 * @return
	 */
	public List<User> getOnline(String os);
	/**
	 * 查询在线用户
	 * @param area
	 * @return
	 */
	public List<User> getOnline(String os,String province);
	/**
	 * 查询在线用户
	 * @param area
	 * @return
	 */
	public List<User> getOnline();
	/**
	 * 查询 当天登入过的用户
	 */
	public List<User> getFinaltime(String time,String os);
	/**
	 * 查询当天注册的用户
	 */
	public List<User> getSaveTime(String time,String os);
	/**
	 * 查询当天注册的用户  按地区
	 */
	public List<User> getSaveTime(String time,String os,String province);
	/**
	 * 查询 当天登入过的用户 按地区
	 */
	public List<User> getFinaltime(String time,String os,String province);
	

}
