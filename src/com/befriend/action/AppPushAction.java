package com.befriend.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.befriend.base.BaseAction;
import com.befriend.dao.NewsDAO;
import com.befriend.dao.UserDAO;
import com.befriend.entity.News;
import com.befriend.util.JsonUtil;
import com.befriend.util.Message;
import com.befriend.util.PageUtil;

/**
 * @author STerOTto app push����
 */
public class AppPushAction extends BaseAction
{

	private static final long serialVersionUID = 1L;

	private NewsDAO newsDAO;
	private UserDAO userDAO;

	private PageUtil page;
	private int currentPage;
	private Message msg;
	private String type;

	/**
	 * ��������
	 * 
	 * @throws IOException
	 */
	public void pushNews() throws IOException
	{
		msg = new Message();
		if (StringUtils.isEmpty(type))
		{
			switch (type.trim())
			{
			case News.TOP_NEWS:
				/**
				 * ͷ������
				 * ���������ࣨ���� �Ƽ��������� ������ʱ������
				 */

				break;
			case News.RECOMMEND:
				/**
				 * �Ƽ�
				 * ������Ϣ+�û���Ϊ
				 */
				break;
			case News.LOCAL:
				/**
				 * ����
				 * ��������ʾ���ţ�Ĭ����ʾ����
				 */

			default:
				/**
				 * ������������
				 */
				break;
			}
		}
		else
		{
			msg.setCode(Message.ERROR);
			msg.setContent("�������Ϊ�գ�");
		}
		this.getJsonResponse().getWriter().print(JsonUtil.toJson(msg));
	}

	public AppPushAction(NewsDAO newsDAO, UserDAO userDAO)
	{
		super();
		this.newsDAO = newsDAO;
		this.userDAO = userDAO;
	}

	public PageUtil getPage()
	{
		return page;
	}

	public void setPage(PageUtil page)
	{
		this.page = page;
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

	public Message getMsg()
	{
		return msg;
	}

	public void setMsg(Message msg)
	{
		this.msg = msg;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

}
