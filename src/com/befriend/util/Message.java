package com.befriend.util;

import java.io.Serializable;


/**
 * ��Ϣʵ����
 * @author STerOTto
 *
 */
public class Message implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final Integer SUCCESS = 1;
	public static final Integer FAILED = 0;
	public static final Integer NULL = -1;
	public static final Integer ERROR = -2;
	
	private Integer code;//״̬��
	private String statement;//��ע
	private Object content;//��������
	
	public Integer getCode()
	{
		return code;
	}
	public void setCode(Integer code)
	{
		this.code = code;
	}
	public String getStatement()
	{
		return statement;
	}
	public void setStatement(String statement)
	{
		this.statement = statement;
	}
	public Object getContent()
	{
		return content;
	}
	public void setContent(Object content)
	{
		this.content = content;
	}
	

}
