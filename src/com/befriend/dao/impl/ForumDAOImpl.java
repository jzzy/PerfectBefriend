package com.befriend.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.befriend.dao.ForumDAO;
import com.befriend.entity.ForumOne;
import com.befriend.entity.ForumThree;
import com.befriend.entity.ForumTwo;

@Transactional
public class ForumDAOImpl implements ForumDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(ForumOne ForumOne) {
		// TODO Auto-generated method stub
		entityManager.persist(ForumOne);

	}

	@Override
	public List<ForumOne> getForumOneALL(int pageSize, int currentPage) {
		// TODO 防止sql注入
		Query query = entityManager
				.createQuery("select f from ForumOne f order"
						+ " by f.time desc");
		int startRow = (currentPage - 1) * pageSize;
		if (startRow < 0) {
			startRow = 0;
		}
		// 第几页
		query.setFirstResult(startRow);
		// 每页显示几条数据
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public List<ForumOne> gettypeForumOneALL(int type) {
		// TODO 防止sql注入
		Query query = entityManager
				.createQuery("select f from ForumOne f where f.type=:type order"
						+ " by f.time desc");
		query.setParameter("type", type);
		return query.getResultList();
	}

	@Override
	public ForumOne getForumOne(int id) {
		Query query = entityManager
				.createQuery("select f from ForumOne f where f.id=:id");
		query.setParameter("id", id);
		List<ForumOne> f = query.getResultList();
		if (f.size() > 0)
			return f.get(0);
		return null;
	}

	@Override
	public void save(ForumTwo ForumTwo) {
		// TODO Auto-generated method stub
		entityManager.persist(ForumTwo);
	}

	@Override
	public void update(ForumOne ForumOne) {
		entityManager.merge(ForumOne);

	}

	@Override
	public List<ForumTwo> getForumTwoALL(int forumid) {
		Query query = entityManager
				.createQuery("select f from ForumTwo f where f.forumid=:forumid order"
						+ " by f.time desc");
		query.setParameter("forumid", forumid);
		return query.getResultList();
	}

	@Override
	public void save(ForumThree ForumThree) {
		// TODO Auto-generated method stub
		entityManager.persist(ForumThree);

	}

	@Override
	public List<ForumThree> getForumThreeALL(int forumid, int forumtwoid) {
		Query query = entityManager
				.createQuery("select f from ForumThree f where f.forumid=:forumid "
						+ " and f.forumtwoid=:forumtwoid "
						+ "order"
						+ " by f.time asc");
		query.setParameter("forumid", forumid);

		query.setParameter("forumtwoid", forumtwoid);

		return query.getResultList();
	}

	@Override
	public List<ForumOne> getForumOneareaALL(String area, int model) {
		Query query = entityManager
				.createQuery("select f from ForumOne f where f.type=:model and f.area=:area order"
						+ " by f.time desc");
		query.setParameter("area", area);
		query.setParameter("model", model);
		return query.getResultList();
	}

	@Override
	public List<ForumOne> getForumOneareasALL(String areas) {
		Query query = entityManager
				.createQuery("select f from ForumOne f where f.areas=:areas order"
						+ " by f.time asc");
		query.setParameter("areas", areas);
		return query.getResultList();
	}

	@Override
	public void Remove(ForumOne ForumOne) {
		// TODO Auto-generated method stub
		entityManager.remove(ForumOne);

	}

	@Override
	public void Remove(ForumTwo ForumTwo) {
		// TODO Auto-generated method stub
		entityManager.remove(ForumTwo);
	}

	@Override
	public void Remove(ForumThree ForumThree) {
		// TODO Auto-generated method stub
		entityManager.remove(ForumThree);
	}

	@Override
	public List<ForumThree> getForumThreeALL(int forumid) {
		Query query = entityManager
				.createQuery("select f from ForumThree f where f.forumid=:forumid "
						+ "order" + " by f.time asc");
		query.setParameter("forumid", forumid);

		return query.getResultList();
	}

	@Override
	public List<ForumOne> getForumOneareassALL(String area, String areas) {
		Query query = entityManager
				.createQuery("select f from ForumOne f where f.area=:area and  f.areas=:areas order"
						+ " by f.time asc");
		query.setParameter("area", area);
		query.setParameter("areas", areas);
		return query.getResultList();
	}

	@Override
	public List<ForumOne> getUseridForumOne(int id) {
		Query query = entityManager
				.createQuery("select f from ForumOne f where f.userid=:id order by f.time desc");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public List<ForumTwo> getFuserALL(int userid) {
		Query query = entityManager
				.createQuery("select f from ForumTwo f where f.userid=:userid order"
						+ " by f.time desc");
		query.setParameter("userid", userid);
		return query.getResultList();
	}

	@Override
	public List<ForumOne> getForumOneALL() {
		// TODO 防止sql注入
		Query query = entityManager
				.createQuery("select f from ForumOne f order"
						+ " by f.time desc");

		return query.getResultList();
	}

	@Override
	public List<ForumOne> getForumOneALL(int pageSize, int currentPage, int type) {
		// TODO 防止sql注入
		Query query = entityManager.createQuery("select f from ForumOne f"
				+ " where f.type=:type order" + " by f.time desc");
		query.setParameter("type", type);
		int startRow = (currentPage - 1) * pageSize;
		if (startRow < 0) {
			startRow = 0;
		}
		// 第几页
		query.setFirstResult(startRow);
		// 每页显示几条数据
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public List<ForumOne> getForumOneALL(int type) {
		// TODO 防止sql注入
		Query query = entityManager.createQuery("select f from ForumOne f"
				+ " where f.type=:type order" + " by f.time desc");
		query.setParameter("type", type);

		return query.getResultList();
	}

	@Override
	public ForumTwo getForumTwoid(int id) {
		Query query = entityManager
				.createQuery("select f from ForumTwo f where f.id=:id");
		query.setParameter("id", id);
		ForumTwo ftwo = (ForumTwo) query.getResultList().get(0);
		if (ftwo != null) {
			return ftwo;
		} else {
			return null;
		}

	}

	@Override
	public void update(ForumTwo forumTwo) {
		entityManager.merge(forumTwo);

	}

	@Override
	public List<ForumOne> getForumOneNotALL(int type) {
		// TODO 防止sql注入
		Query query = entityManager
				.createQuery("select f from ForumOne f where f.type!=:type order"
						+ " by f.time desc");
		query.setParameter("type", type);
		return query.getResultList();
	}

	@Override
	public List<ForumOne> getForumOneNotALL(int pageSize, int currentPage,
			int type) {
		Query query = entityManager.createQuery("select f from ForumOne f"
				+ " where f.type!=:type order" + " by f.time desc");
		query.setParameter("type", type);
		int startRow = (currentPage - 1) * pageSize;
		if (startRow < 0) {
			startRow = 0;
		}
		// 第几页
		query.setFirstResult(startRow);
		// 每页显示几条数据
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public List<ForumOne> getForumOneareaALL(int pageSize, int currentPage,
			String area, int model) {
		Query query = entityManager
				.createQuery("select f from ForumOne f where f.type=:model and f.area=:area order"
						+ " by f.time desc");
		query.setParameter("area", area);
		query.setParameter("model", model);
		int startRow = (currentPage - 1) * pageSize;
		if (startRow < 0) {
			startRow = 0;
		}
		// 第几页
		query.setFirstResult(startRow);
		// 每页显示几条数据
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public List<ForumOne> getForumOneareaALL(String area) {
		Query query = entityManager
				.createQuery("select f from ForumOne f where  f.area=:area order"
						+ " by f.time desc");
		query.setParameter("area", area);

		return query.getResultList();
	}

	@Override
	public List<ForumOne> getForumOneALL234() {
		// TODO 防止sql注入
		Query query = entityManager
				.createQuery("select f from ForumOne f where f.type=2 or f.type=3 or f.type=4 order"
						+ " by f.time desc");

		return query.getResultList();
	}

	@Override
	public List<ForumOne> getForumOneALL234(int pageSize, int currentPage) {
		Query query = entityManager.createQuery("select f from ForumOne f"
				+ " where f.type=2 or f.type=3 or f.type=4 order"
				+ " by f.time desc");

		int startRow = (currentPage - 1) * pageSize;
		if (startRow < 0) {
			startRow = 0;
		}
		// 第几页
		query.setFirstResult(startRow);
		// 每页显示几条数据
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public List<ForumOne> likeTitle(String title) {
		Query query = entityManager
				.createQuery("select f from ForumOne f where f.title LIKE :title order"
						+ " by f.time desc");
		query.setParameter("title", "%" + title + "%");

		return query.getResultList();
	}

	@Override
	public List<ForumOne> likeTitle(int pageSize, int currentPage, String title) {
		Query query = entityManager
				.createQuery("select f from ForumOne f where f.title LIKE :title order"
						+ " by f.time desc");
		query.setParameter("title", "%" + title + "%");
		int startRow = (currentPage - 1) * pageSize;
		if (startRow < 0) {
			startRow = 0;
		}
		// 第几页
		query.setFirstResult(startRow);
		// 每页显示几条数据
		query.setMaxResults(pageSize);

		return query.getResultList();
	}

	@Override
	public ForumThree getForumThree(int id) {
		Query query = entityManager
				.createQuery("select f from ForumThree f where f.id=:id");
		query.setParameter("id", id);

		List<ForumThree> fte=query.getResultList();
		if(fte.get(0)!=null){
			return fte.get(0);
		}else{
			return null;
		}
	}

}
