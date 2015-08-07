package com.befriend.dao;

import java.util.List;

import com.befriend.entity.News;
import com.befriend.entity.NewsLabel;

public interface NewsDAO {

	public List<News> n2ews();

	public void rm(News n);

	public List<News> Pagination(int pageSize, int currentPage);

	public List<News> Pagination(int pageSize, int currentPage, String admin);

	public int Hottimes(int num);

	public int Hottimes(String admin);

	public List<News> Hottime(int num);

	public List<News> All();

	public List<News> Hottest(int num);

	public List<News> Hottest(int pageSize, int currentPage);

	public List<News> Hotarea(int num, String area);

	public List<News> Hotarea(String area, String areas, int pageSize,
			int currentPage);

	public List<News> Hotarea(String area, int pageSize, int currentPage);

	public void Upnews(News news);

	public News byid(int newsid);

	public List<News> news(int num);

	public void Save(News n);

	public List<News> type(int num, int type);

	public List<News> types(String type);

	public List<News> cah(int num, String time, String qtime);

	public List<News> cah(int num);

	public List<News> cah(int pageSize, int currentPage);

	public List<News> area(String area, int num);

	public List<News> area(String area);

	public List<News> type(int num, int type, int pageSize, int currentPage);

	public List<News> types(String type, int pageSize, int currentPage);

	public List<News> getThreeByDay(String day);

	public List<News> getRecentlyNews(int type, int pageSize, int currentPage);

	public List<News> getRecentlyNews(int pageSize, int currentPage);

	public List<News> getRecentlyNews(int type, String province, String city,
			int pageSize, int currentPage);

	public List<News> getRecentlyNewsByTime(String startTime, String endTime);

	public void Save(NewsLabel nlab);

	public List<NewsLabel> getNewsLabelAll();

	public NewsLabel byNewsLabelName(String NewsLabelName);

}

