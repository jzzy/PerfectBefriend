package com.befriend.dao;

import java.util.List;

import com.befriend.entity.Collect;
import com.befriend.entity.Follect;

public interface FollectDAO {
		//����forumid��ѯȫ���ղ�
		public List<Follect> Allf(int forumid);
		//����userid��ѯȫ���ղ�
		public List<Follect> Allu(int userid);
		//����userid forumid ����ղ� 
		public void save(Follect Follect);
		//����userid �� forumid  ��ѯ   ��ֹ�ظ��ղ�
		public Follect ufid(int userid,int forumid);
		//ɾ���ղ�
		public void remove(Follect Follect);
}
