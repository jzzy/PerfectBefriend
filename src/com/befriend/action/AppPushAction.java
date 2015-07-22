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
 * @author STerOTto app push规则
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
	 * 推送新闻
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
				 * 头条新闻
				 * 其他所有类（除了 推荐）的新闻 按最新时间排序
				 */

				break;
			case News.RECOMMEND:
				/**
				 * 推荐
				 * 个人信息+用户行为
				 */
				break;
			case News.LOCAL:
				/**
				 * 本地
				 * 按地区显示新闻，默认显示北京
				 */

			default:
				/**
				 * 其它类别的新闻
				 */
				break;
			}
		}
		else
		{
			msg.setCode(Message.ERROR);
			msg.setContent("秦秋参数为空！");
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
