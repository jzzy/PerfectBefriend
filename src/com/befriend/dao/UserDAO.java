package com.befriend.dao;
import java.util.List;
import com.befriend.entity.User;

public interface UserDAO {

	public User byMac(String mac);

	public User login(String username, String password);

	public User byUsernameAccnumnoPhone(String uap);

	public User byIdMax();

	public void save(User user);

	public void remove(User user);

	public void update(User user);

	public void updateAllXiaxian();

	public User byid(int id);

	public List<User> likeusername(String username);

	public List<User> likeusername(String username, String area);

	public List<User> getUser(int pageSize, int currentPage);

	public List<User> getUser(int pageSize, int currentPage, String area);

	public List<User> getUser();

	public List<User> getUser(String area);

	public List<User> getUseradmin(int pageSize, int currentPage);

	public List<User> getUseradmin(int pageSize, int currentPage, String area);

	public List<User> getUseradmin();

	public List<User> getUseradmin(String area);

	public int getUsertime(String timeq, String timeh);

	public List<User> getUserAll();

	public int getCount();

	public int getCountSyn();

	public List<User> getUsersynAll();

	public List<User> getUserAll(int pageSize, int currentPage);

	public List<User> getUserAll(String area, String timeq, String timeh);

	public List<User> getUserAll(String area, String areas, String timeq,
			String timeh);

	public List<User> getUserAll(String area, String areas);

	public List<User> getUserAll(String area);

	public List<User> getOnline(String os);

	public List<User> getOnline(String os, String province);

	public List<User> getOnline();

	public List<User> getFinaltime(String time, String os);

	public List<User> getSaveTime(String time, String os);

	public List<User> getSaveTime(String time, String os, String province);

	public List<User> getFinaltime(String time, String os, String province);

	public User getUserGroup(Integer userId, int status);
	
	public List<User> searchByKeyword(String keyword);

}
