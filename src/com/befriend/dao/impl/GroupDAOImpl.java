package com.befriend.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.befriend.dao.GroupDAO;
import com.befriend.entity.Cis;
import com.befriend.entity.GroupChat;
import com.befriend.entity.GroupMembers;
import com.befriend.entity.Profile;

@Transactional
public class GroupDAOImpl implements GroupDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(GroupChat groupchat) {
		// TODO Auto-generated method stub
		entityManager.persist(groupchat);

	}

	@Override
	public void Remove(GroupChat groupchat) {
		// TODO Auto-generated method stub
		entityManager.remove(groupchat);
	}

	@Override
	public void Update(GroupChat groupchat) {
		// TODO Auto-generated method stub
		entityManager.merge(groupchat);
	}

	@Override
	public GroupChat Findbyid(int groupid) {
		Query query = entityManager.createQuery("select c from GroupChat c"
				+ " where c.id=:groupid");

		query.setParameter("groupid", groupid);
		List<GroupChat> g = query.getResultList();
		if (g.size() > 0) {
			return g.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<GroupChat> Findbyuserid(int userid) {
		Query query = entityManager.createQuery("select c from GroupChat c"
				+ " where c.userid=:userid  order" + " by c.time desc");

		query.setParameter("userid", userid);
		return query.getResultList();

	}

	@Override
	public void save(Profile Profile) {
		// TODO Auto-generated method stub
		entityManager.persist(Profile);

	}

	@Override
	public void Remove(Profile Profile) {
		// TODO Auto-generated method stub
		entityManager.remove(Profile);
	}

	@Override
	public void Update(Profile Profile) {
		// TODO Auto-generated method stub
		entityManager.merge(Profile);

	}

	

	@Override
	public void save(GroupMembers GroupMembers) {
		// TODO Auto-generated method stub
		entityManager.persist(GroupMembers);

	}

	@Override
	public void Remove(GroupMembers GroupMembers) {
		// TODO Auto-generated method stub
		entityManager.remove(GroupMembers);

	}

	@Override
	public void Update(GroupMembers GroupMembers) {
		// TODO Auto-generated method stub
		entityManager.merge(GroupMembers);

	}

	@Override
	public List<GroupMembers> FindGroupMembersbyid(int GroupChatid, int urp) {
		Query query = entityManager.createQuery("select c from GroupMembers c"
				+ " where c.groupid=:groupid and (c.urp=:urp or c.urp=3) order"
				+ " by c.time desc");
		query.setParameter("urp", urp);
		query.setParameter("groupid", GroupChatid);
		return query.getResultList();
	}

	@Override
	public List<GroupMembers> FindUseridGroupbyid(int userid, int urp) {
		Query query = entityManager.createQuery("select c from GroupMembers c"
				+ " where c.urp=:urp and c.userid=:userid order"
				+ " by c.time desc");
		query.setParameter("urp", urp);
		query.setParameter("userid", userid);
		return query.getResultList();
		

	}

	@Override
	public void save(Cis cis) {
		// TODO Auto-generated method stub
		entityManager.persist(cis);

	}

	@Override
	public void Remove(Cis cis) {
		// TODO Auto-generated method stub
		entityManager.remove(cis);

	}

	@Override
	public void Update(Cis cis) {
		// TODO Auto-generated method stub
		entityManager.merge(cis);

	}

	@Override
	public List<Cis> FindbyGroupChatiduseridCis(int GroupChatid, int userid) {

		Query query = entityManager.createQuery("select c from Cis c"
				+ " where c.groupid=:GroupChatid and c.userid=:userid order"
				+ " by c.time desc");
		query.setParameter("userid", userid);
		query.setParameter("GroupChatid", GroupChatid);
		return query.getResultList();

	}

	@Override
	public GroupChat Findbygroupno(int groupno) {
		Query query = entityManager.createQuery("select c from GroupChat c"
				+ " where c.groupno=:groupno ");

		query.setParameter("groupno", groupno);

		List<GroupChat> g = query.getResultList();
		if (g.size() > 0) {
			return g.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Profile> FindProfilebyids(int GroupChatid, int urp) {
		Query query = entityManager.createQuery("select c from Profile c"
				+ " where c.groupid=:GroupChatid and c.urp=:urp order"
				+ " by c.time desc");

		query.setParameter("GroupChatid", GroupChatid);
		query.setParameter("urp", urp);
		return query.getResultList();

	}

	@Override
	public List<Cis> Cisid(int GroupChatid, int Cisid) {
		Query query = entityManager.createQuery("select c from Cis c"
				+ " where c.groupid=:GroupChatid and c.id>:Cisid order"
				+ " by c.time asc");

		query.setParameter("GroupChatid", GroupChatid);
		query.setParameter("Cisid", Cisid);
		return query.getResultList();
	}

	@Override
	public List<Cis> Cisid(int GroupChatid) {
		Query query = entityManager.createQuery("select c from Cis c"
				+ " where c.groupid=:GroupChatid  order"
				+ " by c.time asc");

		query.setParameter("GroupChatid", GroupChatid);
		
		query.setMaxResults(50);
		return query.getResultList();
	}

	@Override
	public GroupMembers FindGroupMembersbygiduidurp(int GroupChatid,
			int userid, int urp) {
		Query query = entityManager.createQuery("select c from GroupMembers c"
				+ " where c.groupid=:groupid and c.urp=:urp and c.userid=:userid");
		query.setParameter("urp", urp);
		query.setParameter("groupid", GroupChatid);
		query.setParameter("userid", userid);
		
		List<GroupMembers> g = query.getResultList();
		if (g.size() > 0) {
			return g.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Profile FindProfilebyid(int GroupChatid, int userid) {
		Query query = entityManager.createQuery("select c from Profile c"
				+ " where c.groupid=:GroupChatid and c.userid=:userid");

		query.setParameter("GroupChatid", GroupChatid);
		query.setParameter("userid", userid);
		
		List<Profile> g = query.getResultList();
		if (g.size() > 0) {
			return g.get(0);
		} else {
			return null;
		}
	}

	@Override
	public GroupMembers FindGroupMembersbygiduidurp(int GroupChatid, int userid) {
		Query query = entityManager.createQuery("select c from GroupMembers c"
				+ " where c.groupid=:groupid and c.userid=:userid order"
				+ " by c.time desc");
		
		query.setParameter("groupid", GroupChatid);
		query.setParameter("userid", userid);
		
		List<GroupMembers> g = query.getResultList();
		if (g.size() > 0) {
			return g.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<GroupMembers> FindGroupMembersbyid(int GroupChatid) {
		Query query = entityManager.createQuery("select c from GroupMembers c"
				+ " where c.groupid=:groupid  order"
				+ " by c.time desc");
		
		query.setParameter("groupid", GroupChatid);
		return query.getResultList();
	}

	@Override
	public List<Profile> FindProfilebyid(int GroupChatid) {
		Query query = entityManager.createQuery("select c from Profile c"
				+ " where c.groupid=:GroupChatid order"
				+ " by c.time desc");

		query.setParameter("GroupChatid", GroupChatid);
		
		
		return query.getResultList();
		
		
	}

	@Override
	public GroupChat Findbygroupiduserid(int groupid, int userid) {
		Query query = entityManager.createQuery("select c from GroupChat c"
				+ " where c.groupid=:groupid and c.userid=:userid");

		query.setParameter("groupid", groupid);
		query.setParameter("userid", userid);
		List<GroupChat> g = query.getResultList();
		if (g.size() > 0) {
			return g.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Cis> Cisuid(int uid) {
		Query query = entityManager.createQuery("select c from Cis c"
				+ " where  c.userid=:uid and c.online=0 order"
				+ " by c.time asc");

		
		query.setParameter("uid", uid);
		return query.getResultList();
	}

	@Override
	public Cis Cisid(int Groupid, int Cisid, int Uid) {
		Query query = entityManager.createQuery("select c from Cis c"
				+ " where  c.userid=:Uid and c.id=:Cisid and c.groupid=:Groupid");
		query.setParameter("Groupid", Groupid);
		query.setParameter("Cisid", Cisid);
		query.setParameter("Uid", Uid);
		List<Cis> g=query.getResultList();
		if (g.size() > 0) {
			return g.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<GroupChat> findByAll() {
		Query query = entityManager.createQuery("select c from GroupChat c");

		
		return query.getResultList();
		
	}

	@Override
	public List<GroupMembers> FindGroupMembersbyurp(int GroupChatid, int urp) {
		Query query = entityManager.createQuery("select c from GroupMembers c"
				+ " where c.groupid=:groupid and c.urp=:urp order"
				+ " by c.time desc");
		
		query.setParameter("groupid", GroupChatid);
		query.setParameter("urp", urp);
		return query.getResultList();
	}

	@Override
	public List<GroupMembers> FindGroupMembersbygiduidAll(int GroupChatid,
			int userid) {
		Query query = entityManager.createQuery("select c from GroupMembers c"
				+ " where c.groupid=:groupid and c.userid=:userid order"
				+ " by c.time desc");
		
		query.setParameter("groupid", GroupChatid);
		query.setParameter("userid", userid);
		return query.getResultList();
	}

}
