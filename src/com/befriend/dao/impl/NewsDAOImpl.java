package com.befriend.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.befriend.dao.NewsDAO;
import com.befriend.entity.News;
@Transactional
public class NewsDAOImpl implements NewsDAO
{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	//查询全部新闻按照 收藏  评论数 发布时间   排序
	@Override
	public List<News> All()
	{
		// TODO 防止sql注入
		Query query = entityManager.createQuery("select u from News u order"
			      + " by u.collectnum desc,u.reviews desc,u.time desc");
		return query.getResultList();
	}

	//查询num新闻按照 收藏  评论数 发布时间   排序
	@Override
	public List<News> Hottest(int num)
	{
		// TODO 防止sql注入
		Query query = entityManager.createQuery("select u from News u order"
				 + " by u.collectnum desc,u.reviews desc,u.time desc");
		
		
		if(num!=0){
		query.setMaxResults(num);
		}
		return query.getResultList();
	}

	@Override
	public void Upnews(News news)
	{
		entityManager.merge(news);
		
	}

	@Override
	public News byid(int newsid)
	{
		// TODO 防止sql注入
	       
		Query query = entityManager.createQuery("select u from News u where u.id=:newsid");
		query.setParameter("newsid", newsid);
		List<News> news = query.getResultList();
		if (news.size() > 0)
				return news.get(0);
		
			return null;
	}

	@Override
	public List<News> news(int num) {
		// TODO 防止sql注入
		Query query = entityManager.createQuery("select u from News u order"
			      + " by u.time desc");
		query.setMaxResults(num);
		return query.getResultList();
	}

	@Override
	public List<News> Hottime(int num) {
				// TODO 防止sql注入
				Query query = entityManager.createQuery("select u from News u  where "
						+ " u.type!=:type and u.type!=:type1 order by u.time desc ");
				query.setParameter("type","轻松驿站");
				query.setParameter("type1","健康导航");
				if(num!=0){
				query.setMaxResults(num);
				}
				return query.getResultList();
	}

	@Override
	public List<News> Hotarea(int num,String area) {
				// TODO 防止sql注入
				Query query = entityManager.createQuery("select u from News u where u.area=:area  order"
						 + " by u.time desc");
				
				query.setParameter("area",area);
				
				
				if(num!=0){
				query.setMaxResults(num);
				}
				return query.getResultList();
	}

	@Override
	public void Save(News n) {
		entityManager.persist(n);
		
	}

	@Override
	public List<News> type(int num,String type) {
				// TODO 防止sql注入
				/**
				if(type.equals("轻松驿站")||type.equals("健康导航")||type.equals("社会广角")){
				*/
					Query query = entityManager.createQuery("select u from News u where u.type=:type order"
							 + " by u.time desc,u.collectnum desc,u.reviews desc");
					query.setParameter("type",type);
					if(num!=0){
					    query.setMaxResults(num);
					}
					return query.getResultList();
				/**}else{
					
				Query query = entityManager.createQuery("select u from News u where u.type=:type order"
						 + " by u.collectnum desc,u.reviews desc,u.time desc");
				query.setParameter("type",type);
				if(num!=0){
				    query.setMaxResults(num);
				}
				return query.getResultList();
				}*/
			
				
			
				
				
				
		
	}

	@Override
	public List<News> types(String type) {
		// TODO 防止sql注入
		Query query = entityManager.createQuery("select u from News u where u.types=:types order"
				 + " by u.time desc");
		//u.collectnum desc,u.reviews desc,
		query.setParameter("types",type);
		return query.getResultList();
	}

	@Override
	public List<News> cah(int num,String time,String qtime) {
	 // TODO 防止sql注入
	 		Query query = entityManager.createQuery("select n from  News n where"
	 				+ " n.time>=:qtime and n.time<=:time and n.type!=:type"
	 				+ " and n.type!=:type1 "
	 				+ " order by n.cah desc,n.time desc");
	 		query.setParameter("type","轻松驿站");
			query.setParameter("type1","健康导航");
			query.setParameter("time",time);
			query.setParameter("qtime",qtime);
		
	 		if(num!=0){
	 		query.setMaxResults(num);
	 		}
	 		return query.getResultList();
	}

	@Override
	public List<News> area(String area,int num) {
	    	// TODO 防止sql注入
		Query query = entityManager.createQuery("select u from News u where u.area=:area"
				+ " order"
				 + " by u.time desc");
		
		query.setParameter("area",area);
		if(num!=0){
		query.setMaxResults(num);
		}
		return query.getResultList();
	}

	@Override
	public List<News> Pagination(int pageSize, int currentPage) {
	    Query query = entityManager.createQuery("select u from News u order by u.time desc");
		//query.setMaxResults(4);
		//currentPage页数
		int startRow = (currentPage-1)*pageSize;
		if(startRow<0){
			startRow=0;
		}
		//第几页
		query.setFirstResult(startRow);
		//每页显示几条数据
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public List<News> Hottimes(int num) {
	    Query query = entityManager.createQuery("select u from News u order"
			 + " by u.time desc");
	    
	    if(num!=0){
		query.setMaxResults(num);
	    }
	    return query.getResultList();
		}

	@Override
	public void rm(News n) {
	   entityManager.remove(n);
	    
	}

	@Override
	public List<News> type(int num, String type, int pageSize, int currentPage) {
		// TODO 防止sql注入
		Query query = entityManager.createQuery("select u from News u where u.type=:type order"
				 + " by u.time desc,u.collectnum desc,u.reviews desc");
		query.setParameter("type",type);
		
		int startRow = (currentPage-1)*pageSize;
		if(startRow<0){
			startRow=0;
		}
		//第几页
		query.setFirstResult(startRow);
		//每页显示几条数据
		query.setMaxResults(pageSize);
	
		if(num!=0){
		    query.setMaxResults(num);
		}
		
		return query.getResultList();
	}

	@Override
	public List<News> cah(int pageSize, int currentPage) {
		 // TODO 防止sql注入
 		Query query = entityManager.createQuery("select u from News u where u.type!=:type and u.type!=:type1 order"
 				 + " by u.cah desc,u.time desc");
 		query.setParameter("type","轻松驿站");
		query.setParameter("type1","健康导航");
 		
 		int startRow = (currentPage-1)*pageSize;
		if(startRow<0){
			startRow=0;
		}
		//第几页
		query.setFirstResult(startRow);
		//每页显示几条数据
		query.setMaxResults(pageSize);
 		return query.getResultList();
	}

	@Override
	public List<News> Hotarea(String area, String areas, int pageSize,
			int currentPage) {
		// TODO 防止sql注入
		Query query = entityManager.createQuery("select u from News u where u.area=:area and u.areas=:areas order"
				 + " by u.time desc");
		
		query.setParameter("area",area);
		query.setParameter("areas",areas);
		int startRow = (currentPage-1)*pageSize;
		if(startRow<0){
			startRow=0;
		}
		//第几页
		query.setFirstResult(startRow);
		//每页显示几条数据
		query.setMaxResults(pageSize);
		
		return query.getResultList();
	}

	@Override
	public List<News> cah(int num) {
		 // TODO 防止sql注入
 		Query query = entityManager.createQuery("select n from   News n  where  "
 				+ " n.type!=:type"
 				+ " and n.type!=:type1 "
 				+ " order by n.cah desc,n.time desc");
 		query.setParameter("type","轻松驿站");
		query.setParameter("type1","健康导航");
		
	
 		if(num!=0){
 		query.setMaxResults(num);
 		}
 		return query.getResultList();
	}

	@Override
	public List<News> area(String area) {
		// TODO 防止sql注入 查询  本地 获取  八大类 里面 为专家的 新闻  
		Query query = entityManager.createQuery("select u from News u where (u.area=:area or u.area is null)"
				+ " and u.expert=1 order"
				 + " by u.time desc");
		
		query.setParameter("area",area);		
		return query.getResultList();
	}

	@Override
	public List<News> types(String type, int pageSize, int currentPage) {
		// TODO 防止sql注入
				Query query = entityManager.createQuery("select u from News u where u.types=:type order"
						 + " by u.time desc,u.collectnum desc,u.reviews desc");
				query.setParameter("type",type);
				
				int startRow = (currentPage-1)*pageSize;
				if(startRow<0){
					startRow=0;
				}
				//第几页
				query.setFirstResult(startRow);
				//每页显示几条数据
				query.setMaxResults(pageSize);
				return query.getResultList();
	}

	@Override
	public List<News> Hottest(int pageSize, int currentPage) {
		// TODO 防止sql注入
				Query query = entityManager.createQuery("select u from News u order"
						 + " by u.collectnum desc,u.reviews desc,u.time desc");
				int startRow = (currentPage-1)*pageSize;
				if(startRow<0){
					startRow=0;
				}
				//第几页
				query.setFirstResult(startRow);
				//每页显示几条数据
				query.setMaxResults(pageSize);
				return query.getResultList();
	}

	@Override
	public List<News> Hotarea(String area, int pageSize, int currentPage) {
		// TODO 防止sql注入
				Query query = entityManager.createQuery("select u from News u where u.area=:area  order"
						 + " by u.time desc");
				
				query.setParameter("area",area);
				
				int startRow = (currentPage-1)*pageSize;
				if(startRow<0){
					startRow=0;
				}
				//第几页
				query.setFirstResult(startRow);
				//每页显示几条数据
				query.setMaxResults(pageSize);
				
				return query.getResultList();
	}

	@Override
	public List<News> n2ews(int newsid) {
		Query query = entityManager.createQuery("select u from News u order"
				 + " by u.id desc");
		    
		//query.setParameter("newsid",newsid);
			query.setMaxResults(1);
		    
		    return query.getResultList();
			
	}

	

	
	

}
