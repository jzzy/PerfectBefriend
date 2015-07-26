package com.befriend.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.befriend.dao.NewsDAO;
import com.befriend.entity.News;
import com.befriend.entity.NewsLabel;

@Transactional
public class NewsDAOImpl implements NewsDAO {

	@PersistenceContext
	private EntityManager entityManager;

	// ��ѯȫ�����Ű��� �ղ� ������ ����ʱ�� ����
	@Override
	public List<News> All() {
		// TODO ��ֹsqlע��
		Query query = entityManager.createQuery("select u from News u order"
				+ " by u.collectnum desc,u.reviews desc,u.time desc");
		return query.getResultList();
	}

	// ��ѯnum���Ű��� �ղ� ������ ����ʱ�� ����
	@Override
	public List<News> Hottest(int num) {
		// TODO ��ֹsqlע��
		Query query = entityManager.createQuery("select u from News u order"
				+ " by u.collectnum desc,u.reviews desc,u.time desc");

		if (num != 0) {
			query.setMaxResults(num);
		}
		return query.getResultList();
	}

	@Override
	public void Upnews(News news) {
		entityManager.merge(news);

	}

	@Override
	public News byid(int newsid) {
		// TODO ��ֹsqlע��

		Query query = entityManager
				.createQuery("select u from News u where u.id=:newsid");
		query.setParameter("newsid", newsid);
		List<News> news = query.getResultList();
		if (news.size() > 0)
			return news.get(0);

		return null;
	}

	@Override
	public List<News> news(int num) {
		// TODO ��ֹsqlע��
		Query query = entityManager.createQuery("select u from News u order"
				+ " by u.time desc");
		query.setMaxResults(num);
		return query.getResultList();
	}

	@Override
	public List<News> Hottime(int num) {
		// TODO ��ֹsqlע��
		Query query = entityManager.createQuery("select u from News u  where "
				+ " u.type!=:type and u.type!=:type1 order by u.time desc ");
		query.setParameter("type", "������վ");
		query.setParameter("type1", "��������");
		if (num != 0) {
			query.setMaxResults(num);
		}
		return query.getResultList();
	}

	@Override
	public List<News> Hotarea(int num, String area) {
		// TODO ��ֹsqlע��
		Query query = entityManager
				.createQuery("select u from News u where u.area=:area  order"
						+ " by u.time desc");

		query.setParameter("area", area);

		if (num != 0) {
			query.setMaxResults(num);
		}
		return query.getResultList();
	}

	@Override
	public void Save(News n) {
		entityManager.persist(n);

	}

	@Override
	public List<News> type(int num, int type) {
		// TODO ��ֹsqlע��

		Query query = entityManager
				.createQuery("select u from News u where u.type=:type order"
						+ " by u.time desc,u.collectnum desc,u.reviews desc");
		query.setParameter("type", type);
		if (num != 0) {
			query.setMaxResults(num);
		}
		return query.getResultList();

	}

	@Override
	public List<News> types(String type) {
		// TODO ��ֹsqlע��
		Query query = entityManager
				.createQuery("select u from News u where u.types=:types order"
						+ " by u.time desc");
		// u.collectnum desc,u.reviews desc,
		query.setParameter("types", type);
		return query.getResultList();
	}

	@Override
	public List<News> cah(int num, String time, String qtime) {
		// TODO ��ֹsqlע��
		Query query = entityManager.createQuery("select n from  News n where"
				+ " n.time>=:qtime and n.time<=:time and n.type!=:type"
				+ " and n.type!=:type1 " + " order by n.cah desc,n.time desc");
		query.setParameter("type", "������վ");
		query.setParameter("type1", "��������");
		query.setParameter("time", time);
		query.setParameter("qtime", qtime);

		if (num != 0) {
			query.setMaxResults(num);
		}
		return query.getResultList();
	}

	@Override
	public List<News> area(String area, int num) {
		// TODO ��ֹsqlע��
		Query query = entityManager
				.createQuery("select u from News u where u.area=:area"
						+ " order" + " by u.time desc");

		query.setParameter("area", area);
		if (num != 0) {
			query.setMaxResults(num);
		}
		return query.getResultList();
	}

	@Override
	public List<News> Pagination(int pageSize, int currentPage) {
		Query query = entityManager
				.createQuery("select u from News u order by u.id desc");
		// query.setMaxResults(4);
		// currentPageҳ��
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
	public int Hottimes(int num) {
		Query query = entityManager
				.createQuery("select count(u) from News u order"
						+ " by u.time desc");

		if (num != 0) {
			query.setMaxResults(num);
		}
		return (int) (long) query.getSingleResult();
	}

	@Override
	public void rm(News n) {
		entityManager.remove(n);

	}

	@Override
	public List<News> type(int num, int type, int pageSize, int currentPage) {
		// TODO ��ֹsqlע��
		Query query = entityManager
				.createQuery("select u from News u where u.type=:type order"
						+ " by u.time desc,u.collectnum desc,u.reviews desc");
		query.setParameter("type", type);

		int startRow = (currentPage - 1) * pageSize;
		if (startRow < 0) {
			startRow = 0;
		}
		// �ڼ�ҳ
		query.setFirstResult(startRow);
		// ÿҳ��ʾ��������
		query.setMaxResults(pageSize);

		if (num != 0) {
			query.setMaxResults(num);
		}

		return query.getResultList();
	}

	@Override
	public List<News> cah(int pageSize, int currentPage) {
		// TODO ��ֹsqlע��
		Query query = entityManager
				.createQuery("select u from News u where u.type!=:type and u.type!=:type1 order"
						+ " by u.cah desc,u.time desc");
		query.setParameter("type", "������վ");
		query.setParameter("type1", "��������");

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
	public List<News> Hotarea(String area, String areas, int pageSize,
			int currentPage) {
		// TODO ��ֹsqlע��
		Query query = entityManager
				.createQuery("select u from News u where u.area=:area and u.areas=:areas order"
						+ " by u.time desc");

		query.setParameter("area", area);
		query.setParameter("areas", areas);
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
	public List<News> cah(int num) {
		// TODO ��ֹsqlע��
		Query query = entityManager
				.createQuery("select n from   News n  where  "
						+ " n.type!=:type" + " and n.type!=:type1 "
						+ " order by n.cah desc,n.time desc");
		query.setParameter("type", "������վ");
		query.setParameter("type1", "��������");

		if (num != 0) {
			query.setMaxResults(num);
		}
		return query.getResultList();
	}

	@Override
	public List<News> area(String area) {
		// TODO ��ֹsqlע�� ��ѯ ���� ��ȡ �˴��� ���� Ϊר�ҵ� ����
		Query query = entityManager
				.createQuery("select u from News u where (u.area=:area or u.area is null)"
						+ " and u.expert=1 order" + " by u.time desc");

		query.setParameter("area", area);
		return query.getResultList();
	}

	@Override
	public List<News> types(String type, int pageSize, int currentPage) {
		// TODO ��ֹsqlע��
		Query query = entityManager
				.createQuery("select u from News u where u.types=:type order"
						+ " by u.time desc,u.collectnum desc,u.reviews desc");
		query.setParameter("type", type);

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
	public List<News> Hottest(int pageSize, int currentPage) {
		// TODO ��ֹsqlע��
		Query query = entityManager.createQuery("select u from News u order"
				+ " by u.collectnum desc,u.reviews desc,u.time desc");
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
	public List<News> Hotarea(String area, int pageSize, int currentPage) {
		// TODO ��ֹsqlע��
		Query query = entityManager
				.createQuery("select u from News u where u.area=:area  order"
						+ " by u.time desc");

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
	public List<News> n2ews(int newsid) {
		Query query = entityManager.createQuery("select u from News u order"
				+ " by u.id desc");

		// query.setParameter("newsid",newsid);
		query.setMaxResults(1);

		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> getThreeByDay(String day) {
		Query query = entityManager
				.createQuery("select u from News u where to_char(u.time,'yyyy-MM-dd') = :day order by u.hits desc,u.cah desc");
		query.setParameter("day", day);
		query.setMaxResults(3);

		return query.getResultList();
	}

	@Override
	public List<News> Pagination(int pageSize, int currentPage, String admin) {
		Query query = entityManager
				.createQuery("select u from News u where admin=:admin order by u.id desc");
		// query.setMaxResults(4);
		// currentPageҳ��
		int startRow = (currentPage - 1) * pageSize;
		if (startRow < 0) {
			startRow = 0;
		}
		query.setParameter("admin", admin);
		// �ڼ�ҳ
		query.setFirstResult(startRow);
		// ÿҳ��ʾ��������
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public int Hottimes(String admin) {

		Query query = entityManager
				.createQuery("select count(u) from News u where admin=:admin order"
						+ " by u.time desc");
		query.setParameter("admin", admin);

		return (int) (long) query.getSingleResult();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> getRecentlyNews(int type, int pageSize, int currentPage) {
		Query query = entityManager
				.createQuery("select u from News u where u.type = :type order by u.time desc");
		query.setParameter("type", type);


		int startRow = (currentPage-1)*pageSize;
		if(startRow<query.getResultList().size())
		{
			query.setFirstResult(startRow);
			query.setMaxResults(pageSize);
		
			return query.getResultList();
		}
		else
			return new ArrayList<News>();

	}

	@SuppressWarnings("unchecked")
	@Override


	public List<News> getRecentlyNews(int pageSize, int currentPage)
	{
		Query query = entityManager.createQuery("select u from News u order by u.time desc");
		int startRow = (currentPage-1)*pageSize;
		if(startRow<query.getResultList().size())
		{
			query.setFirstResult(startRow);
			query.setMaxResults(pageSize);
		
			return query.getResultList();
		}
		else
			return new ArrayList<News>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> getRecentlyNews(int type,String province, String city, int pageSize,
			int currentPage)
	{
		Query query =  entityManager.createQuery("select u from News u where u.type = :type and u.area = :province and u.areas = :city order by u.time desc");
		query.setParameter("type", type);
		query.setParameter("area", province);
		query.setParameter("city", city);
		int startRow = (currentPage-1)*pageSize;
		if(startRow<query.getResultList().size())
		{
			query.setFirstResult(startRow);
			query.setMaxResults(pageSize);
		
			return query.getResultList();
		}
		else
			return new ArrayList<News>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> getRecentlyNewsByTime(String startTime, String endTime)
	{
		Query query = entityManager.createQuery("select u from News u where u.time >= :startTime and u.time <= :endTime order by u.time desc");
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
		return query.getResultList();
	}

	@Override
	public void Save(NewsLabel nlab) {
		// TODO Auto-generated method stub
		entityManager.persist(nlab);
	}

	@Override
	public List<NewsLabel> getNewsLabelAll() {
		Query query = entityManager.createQuery("select u from NewsLabel u order by u.time desc");
		
		return query.getResultList();
	}

	@Override
	public NewsLabel byNewsLabelName(String NewsLabelName) {
		Query query = entityManager.createQuery("select u from NewsLabel u where u.label=:NewsLabelName");
		query.setParameter("NewsLabelName", NewsLabelName);
		List<NewsLabel> nbl=query.getResultList();
		if(nbl.size()>0){
			return nbl.get(0);
		}
		return null;
	}


}
