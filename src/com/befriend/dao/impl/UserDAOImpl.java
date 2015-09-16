package com.befriend.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.befriend.dao.UserDAO;
import com.befriend.entity.GroupFriend;
import com.befriend.entity.User;
import com.befriend.entity.UserGroup;
@SuppressWarnings("all")
@Transactional
public class UserDAOImpl implements UserDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(User user) {

		entityManager.persist(user);

	}

	@Override
	public void remove(User user) {

		entityManager.remove(user);

	}

	@Override
	public void update(User user) {
		entityManager.merge(user);

	}

	@Override
	public User byid(int id) {
		Query query = entityManager
				.createQuery("select u from User u where u.id=:id ");
		query.setParameter("id", id);

		query.setMaxResults(1);
		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		if (users.size() > 0)
			return users.get(0);
		return null;
	}

	
	@Override
	public List<User> getUser(int pageSize, int currentPage) {
		Query query = entityManager
				.createQuery("select u from User u where u.address!=null and u.addcity!=null and u.competence=0 order"
						+ " by u.time asc ");
		int startRow = (currentPage - 1) * pageSize;
		if (startRow < 0) {
			startRow = 0;
		}
		// �ڼ�ҳ
		query.setFirstResult(startRow);
		// ÿҳ��ʾ��������
		query.setMaxResults(pageSize);
		return query.getResultList();

	}

	@Override
	public List<User> getUseradmin(int pageSize, int currentPage) {
		Query query = entityManager
				.createQuery("select u from User u where u.address!=null and u.addcity!=null and u.competence>0 order"
						+ " by u.time asc ");
		int startRow = (currentPage - 1) * pageSize;
		if (startRow < 0) {
			startRow = 0;
		}
		// �ڼ�ҳ
		query.setFirstResult(startRow);
		// ÿҳ��ʾ��������
		query.setMaxResults(pageSize);

		return query.getResultList();
	}

	@Override
	public List<User> likeusername(String username) {
		System.out.println(username);
		Query query = entityManager
				.createQuery("select u from User u where u.username LIKE :username or  u.phone LIKE :username or u.accnumno LIKE :username");
		query.setParameter("username", "%" + username + "%");

		query.setMaxResults(10);

		return query.getResultList();

	}

	@Override
	public List<User> getUser() {
		Query query = entityManager
				.createQuery("select u from User u where u.address!=null and u.addcity!=null and u.competence=0 order"
						+ " by u.time asc ");

		return query.getResultList();
	}

	@Override
	public List<User> getUseradmin() {
		Query query = entityManager
				.createQuery("select u from User u where u.address!=null and u.addcity!=null and u.competence>0 order"
						+ " by u.time asc ");

		return query.getResultList();
	}

	@Override
	public int getUsertime(String timeq, String timeh) {
		Query query = entityManager
				.createQuery("select u from User u where u.time>=:timeq and u.time<=:timeh  order"
						+ " by u.time desc ");
		query.setParameter("timeq", timeq);
		query.setParameter("timeh", timeh);
		return query.getResultList().size();
	}

	@Override
	public List<User> getUserAll() {
		Query query = entityManager.createQuery("select u from User u order"
				+ " by u.time desc");
		return query.getResultList();
	}

	@Override
	public List<User> getUserAll(int pageSize, int currentPage) {
		Query query = entityManager.createQuery("select u from User u  order"
				+ " by u.loginnum desc ");

		int startRow = (currentPage - 1) * pageSize;
		if (startRow < 0) {
			startRow = 0;
		}
		// �ڼ�ҳ u.finaltime desc,
		query.setFirstResult(startRow);
		// ÿҳ��ʾ��������
		query.setMaxResults(pageSize);

		return query.getResultList();
	}

	@Override
	public List<User> getUserAll(String area, String timeq, String timeh) {
		Query query = entityManager
				.createQuery("select u from User u where u.address=:area and"
						+ " (u.time>=:timeq and u.time<=:timeh)  order"
						+ " by u.time desc");
		query.setParameter("area", area);

		query.setParameter("timeq", timeq);
		query.setParameter("timeh", timeh);
		return query.getResultList();
	}

	@Override
	public List<User> getUserAll(String area, String areas, String timeq,
			String timeh) {
		Query query = entityManager
				.createQuery("select u from User u where u.address=:area and u.addcity=:areas and"
						+ " (u.time>=:timeq and u.time<:timeh)  order"
						+ " by u.time desc");
		query.setParameter("area", area);
		query.setParameter("areas", areas);
		query.setParameter("timeq", timeq);
		query.setParameter("timeh", timeh);
		return query.getResultList();
	}

	@Override
	public List<User> getUserAll(String area) {
		Query query = entityManager
				.createQuery("select u from User u where u.address=:area and u.addcity is not null order"
						+ " by u.time desc");
		query.setParameter("area", area);

		return query.getResultList();
	}

	@Override
	public List<User> getUserAll(String area, String areas) {
		Query query = entityManager
				.createQuery("select u from User u where u.address=:area and u.addcity=:areas "
						+ "  order" + " by u.time desc");
		query.setParameter("area", area);
		query.setParameter("areas", areas);

		return query.getResultList();
	}

	@Override
	public List<User> getUseradmin(int pageSize, int currentPage, String area) {
		// TODO Auto-generated method stub
		//
		Query query = entityManager
				.createQuery("select u from User u where u.address!=null and u.address=:area "
						+ "and u.addcity!=null"
						+ " and u.competence>0 order"
						+ " by u.time asc ");
		query.setParameter("area", area);
		int startRow = (currentPage - 1) * pageSize;
		if (startRow < 0) {
			startRow = 0;
		}
		// �ڼ�ҳ
		query.setFirstResult(startRow);
		// ÿҳ��ʾ��������
		query.setMaxResults(pageSize);

		return query.getResultList();

	}

	@Override
	public List<User> getUser(int pageSize, int currentPage, String area) {
		Query query = entityManager
				.createQuery("select u from User u where u.address!=null and u.addcity!=null and u.address=:area and u.competence=0 order"
						+ " by u.time asc ");
		query.setParameter("area", area);
		int startRow = (currentPage - 1) * pageSize;
		if (startRow < 0) {
			startRow = 0;
		}
		// �ڼ�ҳ
		query.setFirstResult(startRow);
		// ÿҳ��ʾ��������
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public List<User> getUser(String area) {
		Query query = entityManager
				.createQuery("select u from User u where u.address!=null and u.addcity!=null "
						+ "and u.address=:area and u.competence=0 order"
						+ " by u.time asc ");
		query.setParameter("area", area);
		return query.getResultList();
	}

	@Override
	public List<User> getUseradmin(String area) {
		Query query = entityManager
				.createQuery("select u from User u where "
						+ "u.address=:area and u.address!=null and u.addcity!=null and u.competence>0 order"
						+ " by u.time asc ");
		query.setParameter("area", area);

		return query.getResultList();
	}

	@Override
	public List<User> likeusername(String username, String area) {
		System.out.println(username);
		Query query = entityManager
				.createQuery("select u from User u where u.address=:area and u.username LIKE :username  ");
		query.setParameter("username", "%" + username + "%");
		query.setParameter("area", area);
		query.setMaxResults(10);

		return query.getResultList();
	}

	@Override
	public List<User> getOnline(String os) {
		Query query = entityManager
				.createQuery("select u from User u where u.online=1 and u.os=:os order by u.finaltime desc  ");
		query.setParameter("os", os);
		return query.getResultList();
	}

	@Override
	public void updateAllXiaxian() {

		Query query = entityManager
				.createQuery("UPDATE User SET online=0 where online=1");
		query.executeUpdate();

	}

	/**
	 * @Override public User accnumNo(String accnumno) { Query query =
	 *           entityManager
	 *           .createQuery("select u from User u where u.accnumno=:accnumno "
	 *           ); query.setParameter("accnumno", accnumno);
	 *           query.setMaxResults(1); List<User> users =
	 *           query.getResultList(); if (users.size() > 0) return
	 *           users.get(0); return null; }
	 */

	@Override
	public User byUsernameAccnumnoPhone(String uap) {
		Query query = entityManager
				.createQuery("select u from User u where u.username=:username or u.phone=:username or u.accnumno=:username");
		query.setParameter("username", uap);

		query.setMaxResults(1);
		List<User> users = query.getResultList();
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	

	

	@Override
	public List<User> getUsersynAll() {
		Query query = entityManager
				.createQuery("select u from User u where u.come is not null order"
						+ " by u.time desc");
		return query.getResultList();
	}

	@Override
	public int getCount() {

		Query query = entityManager.createQuery("select count(u) from User u");
		// int count = new Long((long)query.getSingleResult()).intValue();
		int count = (int) (long) query.getSingleResult();
		return count;

	}

	@Override
	public int getCountSyn() {

		Query query = entityManager
				.createQuery("select count(u) from User u where u.come is not null");
		// count = new Long((long)query.getSingleResult()).intValue();
		int count = (int) (long) query.getSingleResult();
		return count;
	}

	@Override
	public List<User> getFinaltime(String time, String os) {
		Query query = entityManager
				.createQuery("select u from User u where u.finaltime LIKE :time and u.os=:os");
		query.setParameter("time", time + "%");
		query.setParameter("os", os);
		return query.getResultList();

	}

	@Override
	public List<User> getSaveTime(String time, String os) {
		Query query = entityManager
				.createQuery("select u from User u where u.time LIKE :time and u.os=:os");
		query.setParameter("time", time + "%");
		query.setParameter("os", os);
		return query.getResultList();

	}

	@Override
	public List<User> getOnline() {
		Query query = entityManager
				.createQuery("select u from User u where u.online=1 order by u.finaltime desc  ");

		return query.getResultList();
	}

	@Override
	public List<User> getSaveTime(String time, String os, String province) {
		Query query = entityManager
				.createQuery("select u from User u where u.time LIKE :time and u.os=:os and u.address=:province");
		query.setParameter("time", time + "%");
		query.setParameter("os", os);
		query.setParameter("province", province);
		return query.getResultList();
	}

	@Override
	public List<User> getFinaltime(String time, String os, String province) {
		Query query = entityManager
				.createQuery("select u from User u where u.finaltime LIKE :time and u.os=:os and u.address=:province");
		query.setParameter("time", time + "%");
		query.setParameter("os", os);
		query.setParameter("province", province);
		return query.getResultList();

	}

	@Override
	public List<User> getOnline(String os, String province) {
		Query query = entityManager
				.createQuery("select u from User u where u.online=1 and u.os=:os and u.address=:province order by u.finaltime desc  ");
		query.setParameter("os", os);
		query.setParameter("province", province);
		return query.getResultList();
	}

	@Override
	public User byMac(String mac) {
		Query query = entityManager
				.createQuery("select u from User u where u.mac=:mac");
		query.setParameter("mac", mac);

		query.setMaxResults(1);
		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		if (users.size() > 0)
			return users.get(0);
		return null;
	}

	@Override
	public User byAccnumnoIdMax() {
		Query query = entityManager
				.createQuery("select u from User u order by u.accnumno desc");
		

		query.setMaxResults(1);
		List<User> users = query.getResultList();
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	
	@Override
	public User getUserGroup(Integer userId,int status)
	{
		Query query = entityManager.createQuery("select u from User u where u.id = :userId");
		query.setParameter("userId", userId);
		if(query.getResultList().size()>0)
		{
			User user = (User) query.getResultList().get(0);
			Set<UserGroup> userGroups = new HashSet<UserGroup>();
			for (UserGroup userGroup : user.getUserGroup()) {
				Set<GroupFriend> groupFriends = userGroup.getGroupFriends();
				for (GroupFriend groupFriend : groupFriends) {
					if(groupFriend.getStatus() != status)
						groupFriends.remove(groupFriend);
				}
				userGroups.add(userGroup);
			}
			user.setUserGroup(userGroups);
			return user;
		}
		else
			return null;
	}

	@Override
	public User login(String username, String password) {
		Query query = entityManager
				.createQuery("select u from User u where (u.username=:username or u.phone=:username or u.accnumno=:username) and  u.password=:password");
		query.setParameter("password", password);
		query.setParameter("username", username);
		query.setMaxResults(1);
		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		if (users.size() > 0)
			return users.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> searchByKeyword(String keyword) {
		Query query = entityManager.createQuery("select u from User u where u.accnumno like :keyword or u.phone like :keyword or u.nickname like :keyword or u.username like :keyword");
		query.setParameter("keyword", "%"+keyword+"%");
		return query.getResultList();
	}

}
