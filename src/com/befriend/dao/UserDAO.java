package com.befriend.dao;

import java.util.List;

import com.befriend.entity.User;
import com.befriend.entity.UserGroup;

public interface UserDAO
{

	
	
	
	
	public User byMac(String mac);
	
	public User login(String username, String password);
	
	public User byUsernameAccnumnoPhone(String uap);
	public User byIdMac();
	
	// ע��
	public void save(User user);

	// ɾ��
	public void remove(User user);

	// �޸�
	public void update(User user);
	//һ������
	public void updateAllXiaxian();
	// ͨ��id��ѯ
	public User byid(int id);

	
	// ͨ���û���ģ����ѯ
	public List<User> likeusername(String username);
	// ͨ���û���ģ����ѯ
	public List<User> likeusername(String username,String area);

	
	// ��ѯ�ǹ���Ա���û�
	public List<User> getUser(int pageSize, int currentPage);
	// ��ѯ�ǹ���Ա���û�  ���� ʡ��
	public List<User> getUser(int pageSize, int currentPage,String area);
	public List<User> getUser();
	//��ʡ  ��ѯ�ǹ���
	public List<User> getUser(String area);
	// ��ѯ����Ա���û�
	public List<User> getUseradmin(int pageSize, int currentPage);
	// ��ѯĳʡ����Ա
	public List<User> getUseradmin(int pageSize, int currentPage,String area);
	//��ѯ  ����
	public List<User> getUseradmin();
	//��ʡ��ѯ����
	public List<User> getUseradmin(String area);
	//��ѯʲôʱ�䵽ʲôʱ��ע����û�
	public int getUsertime(String timeq,String timeh);
	//��ѯȫ���û�
	public List<User> getUserAll();
	//��ѯȫ���û�
	public int getCount();
	//��ѯȫ���û�
	public int getCountSyn();

	//��ѯsynȫ���û�
	public List<User> getUsersynAll();
	
	//��ѯȫ���û� ��ҳ��ѯ
	public List<User> getUserAll(int pageSize, int currentPage);
	/**
	 * ��ѯʡ���û� +����ʲôʱ����ʲôʱ���ѯ ʡ +�� + ��ʼʱ�� +����ʱ��
	 * @param area
	 * @return
	 */
	public List<User> getUserAll(String area,String timeq,String timeh);
	/**
	 * ��ѯʡ��+�м��û� +����ʲôʱ����ʲôʱ���ѯ ʡ +�� + ��ʼʱ�� +����ʱ��
	 * @param area
	 * @param areas
	 * @return
	 */
	public List<User> getUserAll(String area,String areas,String timeq,String timeh);
	/**
	 * ��ѯʡ��+�м��û�  
	 * @param area
	 * @param areas
	 * @return
	 */
	public List<User> getUserAll(String area,String areas);
	/**
	 * ��ѯʡ���û� �鿴  �ж�����
	 * @param area
	 * @return
	 */
	public List<User> getUserAll(String area);
	/**
	 * ��ѯ�����û�
	 * @param area
	 * @return
	 */
	public List<User> getOnline(String os);
	/**
	 * ��ѯ�����û�
	 * @param area
	 * @return
	 */
	public List<User> getOnline(String os,String province);
	/**
	 * ��ѯ�����û�
	 * @param area
	 * @return
	 */
	public List<User> getOnline();
	/**
	 * ��ѯ �����������û�
	 */
	public List<User> getFinaltime(String time,String os);
	/**
	 * ��ѯ����ע����û�
	 */
	public List<User> getSaveTime(String time,String os);
	/**
	 * ��ѯ����ע����û�  ������
	 */
	public List<User> getSaveTime(String time,String os,String province);
	/**
	 * ��ѯ �����������û� ������
	 */
	public List<User> getFinaltime(String time,String os,String province);
	
	public User getUserGroup(Integer userId,int status);

}
