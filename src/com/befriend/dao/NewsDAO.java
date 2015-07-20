package com.befriend.dao;

import java.util.List;

import com.befriend.entity.News;

/**
 * 新闻方法
 * 
 * @author Administrator
 *
 */
public interface NewsDAO {
	// 1 查询 看有多少条更新
	public List<News> n2ews(int newsid);
	// 删除新闻
	public void rm(News n);

	// 分页查询
	public List<News> Pagination(int pageSize, int currentPage);

	// 1 查询num 按照时间排序
	public List<News> Hottimes(int num);

	// 2 查询num 按照时间排序 不包括 轻松驿站 健康导航 类
	public List<News> Hottime(int num);

	// 查询全部新闻按照 收藏 评论数 发布时间 排序
	public List<News> All();

	// 2 按热点查询 num条新闻按照 收藏 评论数 发布时间 排序
	public List<News> Hottest(int num);
	// 2 按热点查询 num条新闻按照 收藏 评论数 发布时间 排序 分页查询
	public List<News> Hottest(int pageSize,int currentPage);
	
	
	// 3 本地 省级 市级 排序 按搜藏 评论数 时间
	public List<News> Hotarea(int num, String area);
	// 3 本地 省级 市级 排序 按搜藏 评论数 时间 分页查询
	public List<News> Hotarea(String area, String areas, int pageSize,int currentPage);
	// 3 本地 省级 市级 排序 按搜藏 评论数 时间 分页查询
	public List<News> Hotarea(String area, int pageSize,int currentPage);

	
	// 更新新闻被收藏的次数
	public void Upnews(News news);

	// 通过newsid查询 新闻
	public News byid(int newsid);

	// 获取 时间最新的 新闻
	public List<News> news(int num);

	// 添加新闻
	public void Save(News n);

	// 按照8大类查询
	public List<News> type(int num, String type);


	// 按照8大类查询 的4小类
	public List<News> types(String type);

	// 新闻按点击数 +收藏数 时间 排序 可以 定义查询 多少天之前的
	public List<News> cah(int num, String time, String qtime);

	// 新闻按点击数 +收藏数 时间 排序
	public List<News> cah(int num);

	// 新闻按点击数 +收藏数 时间 排序 分页查询
	public List<News> cah(int pageSize, int currentPage);

	// 新闻按照 省 查询
	public List<News> area(String area, int num);
	// 新闻按照 省 查询 本省的 专家新闻
	public List<News> area(String area);

	// 按照8大类查询 分页查询
	public List<News> type(int num, String type, int pageSize, int currentPage);

	//按照8大类 4小类查询 分页查询
	
	public List<News> types(String type, int pageSize, int currentPage);
}
