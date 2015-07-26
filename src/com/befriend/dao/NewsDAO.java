package com.befriend.dao;

import java.util.List;

import com.befriend.entity.News;
import com.befriend.entity.NewsLabel;

/**
 * ���ŷ���
 * 
 * @author Administrator
 *
 */
public interface NewsDAO {
	// 1 ��ѯ ���ж���������
	public List<News> n2ews(int newsid);
	// ɾ������
	public void rm(News n);

	// ��ҳ��ѯ
	public List<News> Pagination(int pageSize, int currentPage);
	// ��ҳ��ѯ
	public List<News> Pagination(int pageSize, int currentPage,String admin);
	// 1 ��ѯnum ����ʱ������
	public int Hottimes(int num);
	// 1 ��ѯnum ����ʱ������
	public int Hottimes(String admin);
	// 2 ��ѯnum ����ʱ������ ������ ������վ �������� ��
	public List<News> Hottime(int num);

	// ��ѯȫ�����Ű��� �ղ� ������ ����ʱ�� ����
	public List<News> All();

	// 2 ���ȵ��ѯ num�����Ű��� �ղ� ������ ����ʱ�� ����
	public List<News> Hottest(int num);
	// 2 ���ȵ��ѯ num�����Ű��� �ղ� ������ ����ʱ�� ���� ��ҳ��ѯ
	public List<News> Hottest(int pageSize,int currentPage);
	
	
	// 3 ���� ʡ�� �м� ���� ���Ѳ� ������ ʱ��
	public List<News> Hotarea(int num, String area);
	// 3 ���� ʡ�� �м� ���� ���Ѳ� ������ ʱ�� ��ҳ��ѯ
	public List<News> Hotarea(String area, String areas, int pageSize,int currentPage);
	// 3 ���� ʡ�� �м� ���� ���Ѳ� ������ ʱ�� ��ҳ��ѯ
	public List<News> Hotarea(String area, int pageSize,int currentPage);

	
	// �������ű��ղصĴ���
	public void Upnews(News news);

	// ͨ��newsid��ѯ ����
	public News byid(int newsid);

	// ��ȡ ʱ�����µ� ����
	public List<News> news(int num);

	// �������
	public void Save(News n);

	// ����8�����ѯ
	public List<News> type(int num, int type);


	// ����8�����ѯ ��4С��
	public List<News> types(String type);

	// ���Ű������ +�ղ��� ʱ�� ���� ���� �����ѯ ������֮ǰ��
	public List<News> cah(int num, String time, String qtime);

	// ���Ű������ +�ղ��� ʱ�� ����
	public List<News> cah(int num);

	// ���Ű������ +�ղ��� ʱ�� ���� ��ҳ��ѯ
	public List<News> cah(int pageSize, int currentPage);

	// ���Ű��� ʡ ��ѯ
	public List<News> area(String area, int num);
	// ���Ű��� ʡ ��ѯ ��ʡ�� ר������
	public List<News> area(String area);

	// ����8�����ѯ ��ҳ��ѯ
	public List<News> type(int num, int type, int pageSize, int currentPage);

	//����8���� 4С���ѯ ��ҳ��ѯ
	
	public List<News> types(String type, int pageSize, int currentPage);
	
	/**
	 * ��ѯĳ���3����������
	 * @param day
	 * @return
	 */
	public List<News> getThreeByDay(String day);
	
	/**
	 * ��ѯ���������
	 * @param type
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public List<News> getRecentlyNews(int type,int pageSize,int currentPage);
	/**
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public List<News> getRecentlyNews(int pageSize,int currentPage);
	/**
	 * @param type
	 * @param city
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public List<News> getRecentlyNews(int type,String province,String city,int pageSize,int currentPage);
	/**
	 * ��ʱ��β�ѯ���������
	 * @param startTime
	 * @param endTime
	 * @return
	 */

	public List<News> getRecentlyNews(String startTime,String endTime);
	/**
	 * ��ӱ�ǩ
	 */
	public void Save(NewsLabel nlab);
	/**
	 * ��ѯ���б�ǩ
	 */
	public List<NewsLabel> getNewsLabelAll();
	/**
	 * �鿴��ǩ
	 */
	public NewsLabel byNewsLabelName(String NewsLabelName);

	public List<News> getRecentlyNewsByTime(String startTime,String endTime);
}

