package com.befriend.util;
import java.io.Serializable;

import com.google.gson.annotations.Expose;


/**
 * 
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
	@Expose
	private Integer code;
	@Expose
	private String statement;
	@Expose
	private Object content;
	
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
	@Override
	public String toString() {
		return "Message [code=" + code + ", statement=" + statement + ", content=" + content + "]";
	}
	

}
