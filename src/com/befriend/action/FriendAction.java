package com.befriend.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.befriend.base.BaseAction;
import com.befriend.dao.GroupFriendDAO;
import com.befriend.dao.UserDAO;
import com.befriend.dao.UserGroupDAO;
import com.befriend.entity.GroupFriend;
import com.befriend.entity.User;
import com.befriend.entity.UserGroup;
import com.befriend.util.Contact;
import com.befriend.util.JsonUtil;
import com.befriend.util.Message;
import com.befriend.util.OpeFunction;
import com.befriend.wechat.RefreshAccessToken;
import com.befriend.wechat.WechatKit;

public class FriendAction extends BaseAction
{
	private static final long serialVersionUID = 1L;
	public static final String YES = "y";
	public static final String NO = "n";
	public static final String REMOVE = "remove";
	public static final String KEEP = "keep";
	

	private UserDAO userDAO;
	private UserGroupDAO userGroupDAO;
	private GroupFriendDAO groupFriendDAO;

	private String userId;
	private String friendId;
	private String groupId;
	private String agree;
	private String remark;
	private String groupName;
	private String orderNum;
	private String type;
	private String phones;
	private String keyword;

	/**
	 * @param userId
	 * get friend list
	 * @throws IOException
	 */
	public void getFriendsList() throws IOException
	{
		Message msg = new Message();
		/**
		 * check userId is a number
		 */
		if (StringUtils.isNumeric(userId))
		{
			/**
			 * if there is a user found by userId
			 * get his or her list of friends
			 */
			User user = userDAO.getUserGroup(Integer.valueOf(userId),GroupFriend.FRIEND);
			if (user != null)
			{
				Set<UserGroup> userGroups = user.getUserGroup();
				UserGroup myFriend = userGroupDAO.findDefault(Integer.valueOf(userId), UserGroup.FRIEND_DEFAULT);
				UserGroup blacklist = userGroupDAO.findDefault(Integer.valueOf(userId), UserGroup.BLACKLIST_DEFAULT);
				if(myFriend == null)
				{
					myFriend = new UserGroup();
					myFriend.setUser(user);
					myFriend.setName(UserGroup.MY_FRIEND);
					myFriend.setIsDefault(UserGroup.FRIEND_DEFAULT);
					myFriend.setCreateTime(OpeFunction.getNowTime());
					userGroupDAO.save(myFriend);
					userGroups.add(myFriend);
				}
				if(blacklist == null)
				{
					blacklist = new UserGroup();
					blacklist.setUser(user);
					blacklist.setName(UserGroup.MY_BLACKLIST);
					blacklist.setIsDefault(UserGroup.BLACKLIST_DEFAULT);
					blacklist.setCreateTime(OpeFunction.getNowTime());
					userGroupDAO.save(blacklist);
					userGroups.add(blacklist);
				}
				
				for (UserGroup userGroup : userGroups)
				{

					Set<GroupFriend> groupFriends = new HashSet<GroupFriend>();
					for (GroupFriend groupFriend : userGroup.getGroupFriends())
					{
						/**
						 * find a friend by groupFriend's userId
						 */
						User friend = userDAO.byid(groupFriend.getUserId());
						if (friend != null)
						{
							groupFriend.setAccnumno(friend.getAccnumno());
							groupFriend.setUsername(friend.getUsername());
							groupFriend.setNickname(friend.getNickname());
							groupFriend.setImg(friend.getImg());
						}
						groupFriends.add(groupFriend);
					}
					userGroup.setGroupFriends(groupFriends);
					
					userGroups.add(userGroup);
				}
				user.setUserGroup(userGroups);
				msg.setCode(Message.SUCCESS);
				msg.setContent(user);
			}
			else
			{
				msg.setCode(Message.NULL);
				msg.setStatement("user is null");
			}
		} else
		{
			msg.setCode(Message.ERROR);
			msg.setStatement("userId is not a number");
		}
		/**
		 * write response
		 */
		this.getJsonResponse().getWriter().print(JsonUtil.toJsonExpose(msg));
	}
	
	/**
	 * @param userId
	 * @param friendId
	 * @param groupId
	 * @param remark
	 * @describe add someone to friend
	 * check someone was friend
	 * check has send invitation and the groupId has been changed
	 * @throws IOException 
	 */
	public void addFriend() throws IOException
	{
		Message msg  = new Message();
		if(StringUtils.isNumeric(userId)&&StringUtils.isNumeric(friendId)&&StringUtils.isNumeric(groupId))
		{
			/**
			 * check the one is existed in friend list
			 */
			GroupFriend friend = groupFriendDAO.find(Integer.valueOf(userId), Integer.valueOf(friendId));
			if(friend != null)
			{
				/**
				 * if the one exist in friend list
				 * check his or her status
				 * check his or her group's information has changed
				 */
				if(friend.getStatus().intValue() == GroupFriend.FRIEND)
				{
					msg.setCode(Message.FAILED);
					msg.setStatement("he or her has been your friend");
				}
				else
					if(friend.getStatus().intValue() == GroupFriend.BLACKLIST)
					{
						msg.setCode(Message.FAILED);
						msg.setStatement("you have moved he or her to your blacklist");
					}
					else 
					{
						UserGroup userGroup = userGroupDAO.find(Integer.valueOf(groupId));
						if(userGroup != null)
						{
							friend.setUserGroup(userGroup);
							friend.setStatus(GroupFriend.INVITE);
							System.out.println("remark:"+remark);
							if(!StringUtils.isEmpty(remark))
								friend.setRemark(remark);
							
							groupFriendDAO.update(friend);
							msg.setCode(Message.SUCCESS);
							msg.setStatement("send a invitation success");
						}
							
					}
				
			}
			else
			{
				/**
				 * insert a new record
				 * send a invitation to the one your wanted be friend
				 */
				UserGroup userGroup = userGroupDAO.find(Integer.valueOf(groupId));
				if(userGroup != null)
				{
					GroupFriend groupFriend = new GroupFriend();
					groupFriend.setUserGroup(userGroup);
					groupFriend.setUserId(Integer.valueOf(friendId));
					groupFriend.setStatus(GroupFriend.INVITE);
					groupFriend.setCreateTime(OpeFunction.getNowTime());
					/**
					 * Persistence
					 */
					groupFriendDAO.save(groupFriend);
					
					msg.setCode(Message.SUCCESS);
					msg.setStatement("success");
				}
				else
				{
					msg.setCode(Message.ERROR);
					msg.setStatement("parameter error");
				}
			}
		}
		else
		{
			msg.setCode(Message.ERROR);
			msg.setStatement("parameter error");
		}
		this.getJsonResponse().getWriter().print(JsonUtil.toJsonExpose(msg));
	}
	
	/**
	 * @param userId passive
	 * @param friendId initiative
	 * @param groupId passive
	 * @param remark choice
	 * @param agree
	 * @throws IOException 
	 * @describe user deal with the invitation his or her got
	 */
	public void handleInvitation() throws IOException
	{
		Message msg = new Message();
		if(StringUtils.isNumeric(userId)&&StringUtils.isNumeric(friendId))
		{
				if(YES.equalsIgnoreCase(agree))
				{
					/**
					 * find friend relationship from database
					 * if there is no relationship
					 * insert a new one
					 * else update database
					 * and the one who is invited need a group id
					 */
					GroupFriend initiative = groupFriendDAO.find(Integer.valueOf(friendId), Integer.valueOf(userId));
					if(initiative == null)
					{
						/**
						 * he or her didn't invite you to be a friend
						 */
						msg.setCode(Message.FAILED);
						msg.setStatement("he or her didn't invite you to be a friend");
					}
					else
					{
						/**
						 * if agree to be a friend and update the relationship success in ease mob
						 * update the relationship in our service
						 */
						String url = RefreshAccessToken.BASE_URL+"/users/"+userId+"/contacts/users/"+friendId;
						String result = WechatKit.post(url, null, RefreshAccessToken.access_token);
						System.out.println("the result of handleInvitation:"+result);
						try {
							JSONObject jsonObject = new JSONObject(result);
							JSONArray entities = jsonObject.getJSONArray("entities");
							boolean activated = false;
							if(entities.length()>0)
							{
								JSONObject entity = entities.getJSONObject(0);
								activated = entity.getBoolean("activated");
								if(activated)
								{
									String time = OpeFunction.getNowTime();
									/**
									 * update the initiative relationship
									 */
									initiative.setStatus(GroupFriend.FRIEND);
									initiative.setCreateTime(time);
									groupFriendDAO.update(initiative);
									
									GroupFriend passive = groupFriendDAO.find(Integer.valueOf(userId),Integer.valueOf(friendId));
									UserGroup userGroup = userGroupDAO.find(Integer.valueOf(groupId));
									if(passive == null)
									{
										passive = new GroupFriend();
									}
									passive.setUserGroup(userGroup);
									passive.setUserId(Integer.valueOf(friendId));
									passive.setStatus(GroupFriend.FRIEND);
									passive.setRemark(remark);
									passive.setCreateTime(time);
									/**
									 * if passive's id is null update will insert a new record
									 */
									groupFriendDAO.update(passive);
									msg.setCode(Message.SUCCESS);
									msg.setStatement("now he or her is your friend");
									msg.setContent(true);
								}
							}
						} catch (JSONException e) {
							e.printStackTrace();
							msg.setCode(Message.FAILED);
							msg.setStatement("error in add a friend in ease mob");
						}
						
					}
					
				}
				else
				{
					GroupFriend initiative = groupFriendDAO.find(Integer.valueOf(friendId), Integer.valueOf(userId));
					if(initiative == null)
					{
						/**
						 * he or her didn't invite you to be a friend
						 */
						msg.setCode(Message.FAILED);
						msg.setStatement("he or her didn't invite you to be a friend");
					}
					else
					{
						initiative.setStatus(GroupFriend.REFUSE);
						groupFriendDAO.update(initiative);
						msg.setCode(Message.SUCCESS);
						msg.setStatement("you has be refused");
					}
				}
				this.getJsonResponse().getWriter().print(JsonUtil.toJsonExpose(msg));
		}
	}
	/**
	 * @param userId
	 * @throws IOException 
	 */
	public void createDefaultGroup() throws IOException
	{
		Message msg = new Message();
		if(StringUtils.isNumeric(userId))
		{
			User user = userDAO.byid(Integer.valueOf(userId));
			if(user != null)
			{
				UserGroup myFriend = userGroupDAO.findDefault(Integer.valueOf(userId), UserGroup.FRIEND_DEFAULT);
				UserGroup blacklist = userGroupDAO.findDefault(Integer.valueOf(userId), UserGroup.BLACKLIST_DEFAULT);
				if(myFriend == null)
				{
					myFriend = new UserGroup();
					myFriend.setUser(user);
					myFriend.setName(UserGroup.MY_FRIEND);
					myFriend.setIsDefault(UserGroup.FRIEND_DEFAULT);
					myFriend.setCreateTime(OpeFunction.getNowTime());
					userGroupDAO.save(myFriend);
				}
				if(blacklist == null)
				{
					blacklist = new UserGroup();
					blacklist.setUser(user);
					blacklist.setName(UserGroup.MY_BLACKLIST);
					blacklist.setIsDefault(UserGroup.BLACKLIST_DEFAULT);
					blacklist.setCreateTime(OpeFunction.getNowTime());
					userGroupDAO.save(blacklist);
				}
				msg.setCode(Message.SUCCESS);
				msg.setStatement("sucess");
			}
			else
			{
				msg.setCode(Message.ERROR);
				msg.setStatement("user is null");
			}
		}
		else
		{
			msg.setCode(Message.ERROR);
			msg.setStatement("parameter is error");
		}
		this.getJsonResponse().getWriter().println(JsonUtil.toJsonExpose(msg));
	}
	
	/**
	 * @param userId
	 * @param groupName
	 * user add a group
	 * @throws IOException 
	 */
	public void addUserGroup() throws IOException
	{
		Message msg = new Message();
		if(StringUtils.isNumeric(userId)&&!StringUtils.isEmpty(groupName))
		{
			User user = userDAO.byid(Integer.valueOf(userId));
			if(user != null)
			{
				UserGroup userGroup = userGroupDAO.find(Integer.valueOf(userId), groupName);
				if(userGroup==null)
				{
					/**
					 * if not existed a group
					 * insert a new group
					 */
					userGroup = new UserGroup();
					userGroup.setUser(user);
					userGroup.setName(groupName);
					userGroup.setIsDefault(UserGroup.NOT_DEFAULT);
					userGroup.setOrderNum(0);
					userGroup.setCreateTime(OpeFunction.getNowTime());
					userGroupDAO.save(userGroup);
					msg.setCode(Message.SUCCESS);
					msg.setStatement("add "+groupName+" success");
					
				}
				else
				{
					msg.setCode(Message.FAILED);
					msg.setStatement("there was a group named by "+groupName+" already");
				}
			}
			else
			{
				msg.setCode(Message.ERROR);
				msg.setStatement("user not exist");
			}
		}
		else
		{
			msg.setCode(Message.ERROR);
			msg.setStatement("parameter is error");
		}
		this.getJsonResponse().getWriter().print(JsonUtil.toJsonExpose(msg));
	}
	
	/**
	 * @param groupId
	 * @param groupName choice
	 * @param orderNum choice
	 * @throws IOException 
	 * 
	 * @describe edit user's group
	 * 
	 */
	public void editUserGroup() throws IOException
	{
		Message msg = new Message();
		if(StringUtils.isNumeric(groupId))
		{
			UserGroup userGroup = userGroupDAO.find(Integer.valueOf(groupId));
			if(userGroup != null)
			{
				/**
				 * if the group is not null and group is not default
				 * update the group
				 */
				if(userGroup.getIsDefault() != UserGroup.BLACKLIST_DEFAULT && userGroup.getIsDefault() != UserGroup.FRIEND_DEFAULT)
				{
					if(!StringUtils.isEmpty(groupName))
						userGroup.setName(groupName);
					if(StringUtils.isNumeric(orderNum))
						userGroup.setOrderNum(Integer.valueOf(orderNum));
					userGroupDAO.update(userGroup);
					msg.setCode(Message.SUCCESS);
					msg.setStatement("edit user group success");
				}
				else
				{
					msg.setCode(Message.FAILED);
					msg.setStatement("default");
				}
				
			}
			else
			{
				msg.setCode(Message.FAILED);
				msg.setStatement("the userGroup is not exist");
			}
		}
		else
		{
			msg.setCode(Message.ERROR);
			msg.setStatement("parameter error");
		}
		this.getJsonResponse().getWriter().print(JsonUtil.toJsonExpose(msg));
	}
	/**
	 * @param groupId
	 * @throws IOException 
	 * @describe delete user group
	 */
	public void deleteUserGroup() throws IOException
	{
		Message msg = new Message();
		if(StringUtils.isNumeric(groupId))
		{
			UserGroup userGroup = userGroupDAO.find(Integer.valueOf(groupId));
			if(userGroup != null)
			{
				/**
				 * if the group is not null and group is not default
				 * delete the group
				 */
				if(userGroup.getIsDefault() != UserGroup.BLACKLIST_DEFAULT && userGroup.getIsDefault() != UserGroup.FRIEND_DEFAULT)
				{
					Set<GroupFriend> groupFriends = userGroup.getGroupFriends();
					UserGroup defaultGroup = userGroupDAO.findDefault(userGroup.getUser().getId().intValue(), UserGroup.FRIEND_DEFAULT);
					if(defaultGroup != null)
					{
						/**
						 * if the default group is not exist
						 * create a default group
						 */
						this.userId = userGroup.getUser().getId().toString();
						createDefaultGroup();
						/**
						 * after insert default group 
						 * get the default group
						 */
						defaultGroup = userGroupDAO.findDefault(userGroup.getUser().getId().intValue(), UserGroup.FRIEND_DEFAULT);
					}
					/**
					 * move the friends of the group to default group
					 */
					for (GroupFriend groupFriend : groupFriends) 
					{
						groupFriend.setUserGroup(defaultGroup);
						groupFriendDAO.update(groupFriend);
					}
					userGroupDAO.remove(userGroup);
					msg.setCode(Message.SUCCESS);
					msg.setStatement("delete user group success");
				}
				else
				{
					msg.setCode(Message.FAILED);
					msg.setStatement("default");
				}
				
			}
			else
			{
				msg.setCode(Message.FAILED);
				msg.setStatement("the userGroup is not exist");
			}
		}
		else
		{
			msg.setCode(Message.ERROR);
			msg.setStatement("parameter error");
		}
		this.getJsonResponse().getWriter().print(JsonUtil.toJsonExpose(msg));
	}
	
	/**
	 * @param userId myId
	 * @param friendId
	 * @throws IOException 
	 * @describe first delete the friend in ease mob then delete the friend in our service
	 */
	public void deleteFriend() throws IOException
	{
		Message msg = new Message();
		if(StringUtils.isNumeric(userId)&&StringUtils.isNumeric(friendId))
		{
			/**
			 * if userId and friendId is number
			 * delete the friend in ease mob
			 * delete the friend in our service
			 */
			String url = RefreshAccessToken.BASE_URL+"/users/"+userId+"/contacts/users/"+friendId;
			String result = WechatKit.delete(url, RefreshAccessToken.access_token);
			try {
				JSONObject jsonObject = new JSONObject(result);
				JSONArray entities = jsonObject.getJSONArray("entities");
				boolean activated = false;
				if(entities.length()>0)
				{
					JSONObject entity = entities.getJSONObject(0);
					activated = entity.getBoolean("activated");
					if(activated)
					{
						/**
						 * delete success in ease mob
						 * delete the friend in our service
						 */
						GroupFriend initiative = groupFriendDAO.find(Integer.valueOf(userId), Integer.valueOf(friendId));
						if(initiative != null)
						{
							groupFriendDAO.remove(initiative);
						}
						GroupFriend passive = groupFriendDAO.find(Integer.valueOf(friendId), Integer.valueOf(userId));
						if(passive != null)
						{
							groupFriendDAO.remove(passive);
						}
						msg.setCode(Message.SUCCESS);
						msg.setContent("delete the friend success");
					}
					else
					{
						msg.setCode(Message.FAILED);
						msg.setStatement("error to delete the friend in ease mob");
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
				msg.setCode(Message.ERROR);
				msg.setStatement("error to delete the friend in ease mob");
			}
		}
		else
		{
			msg.setCode(Message.ERROR);
			msg.setStatement("parameter error");
		}
		this.getJsonResponse().getWriter().print(JsonUtil.toJsonExpose(msg));
	}

	/**
	 * @param userId myId
	 * @param friendId 
	 * @throws IOException 
	 * @describe add a friend to black list
	 */
	public void addToBlackList() throws IOException
	{
		Message msg = new Message();
		if(StringUtils.isNumeric(userId)&&StringUtils.isNumeric(friendId))
		{
			/**
			 * if userId and friendId is number
			 * add the friend to black list in ease mob
			 * add the friend to black list in our service
			 */
			String url = RefreshAccessToken.BASE_URL+"/users/"+userId+"/blocks/users";
			try {
				JSONObject jsonObject = new JSONObject();
				String [] usernames = {friendId};
				jsonObject.put("usernames", Arrays.toString(usernames));
				System.out.println(Arrays.toString(usernames));
				WechatKit.post(url,jsonObject, RefreshAccessToken.access_token);
				GroupFriend groupFriend = groupFriendDAO.find(Integer.valueOf(userId), Integer.valueOf(friendId));
				if(groupFriend != null)
				{
					groupFriend.setStatus(GroupFriend.BLACKLIST);
					groupFriendDAO.update(groupFriend);
				}
				msg.setCode(Message.SUCCESS);
				msg.setStatement("move friend to black list success");
			} catch (JSONException e) {
				e.printStackTrace();
				msg.setCode(Message.ERROR);
				msg.setStatement("error when move friend to black list in ease mob");
			}
		}
		else
		{
			msg.setCode(Message.ERROR);
			msg.setStatement("parameter error");
		}
		this.getJsonResponse().getWriter().print(JsonUtil.toJsonExpose(msg));
	}
	
	/**
	 * @param userId myId
	 * @param friendId
	 * @describe move friend out of black list
	 */
	public void moveFromBlckList()
	{
		Message msg = new Message();
		String url = RefreshAccessToken.BASE_URL+"/users/"+userId+"/blocks/users/"+friendId;
		if(StringUtils.isNumeric(userId)&&StringUtils.isNumeric(friendId))
		{
			/**
			 * remove from ease mob
			 */
			WechatKit.delete(url, RefreshAccessToken.access_token);
			GroupFriend groupFriend = groupFriendDAO.find(Integer.valueOf(userId), Integer.valueOf(friendId));
			if(groupFriend != null)
			{
				groupFriend.setStatus(GroupFriend.BLACKLIST);
				groupFriendDAO.update(groupFriend);
			}
			msg.setCode(Message.SUCCESS);
			msg.setStatement("sucess");
		}
		else
		{
			msg.setCode(Message.ERROR);
			msg.setStatement("parameter error");
		}
		
		
	}
	
	/**
	 * @param phones
	 * @throws IOException 
	 */
	public void inviteContacts() throws IOException
	{
		String [] userPhones = phones.split(",|\\,");
		List<Contact> contacts = new ArrayList<Contact>();
		for (String phone : userPhones) 
		{
			Contact contact = new Contact();
			User user = userDAO.byUsernameAccnumnoPhone(phone);
			if(user != null)
			{
				contact.setPhone(phone);
				contact.setExist(true);
				contact.setUserId(user.getId());
				contact.setUserName(user.getUsername());
				contact.setImg(user.getImg());
				
				String url = RefreshAccessToken.BASE_URL+"/users/"+user.getId()+"/status";
				String result = WechatKit.get(url, RefreshAccessToken.access_token);
				if(result != null)
				{
					try {
						JSONObject jsonObject = new JSONObject(result);
						JSONObject data = jsonObject.getJSONObject("data");
						String stliu = (String) data.get("stliu");
						if(stliu!=null&&stliu.equalsIgnoreCase("online"))
						{
							contact.setStatus(Contact.ONLINE);
						}
						else
						{
							contact.setStatus(Contact.ONLINE);
						}
					} catch (JSONException e) {
						contact.setStatus(Contact.ONLINE);
						e.printStackTrace();
					}
				}
				else
				{
					contact.setStatus(Contact.ONLINE);
				}
			}
			else
			{
				contact.setPhone(phone);
				contact.setExist(false);
			}
			contacts.add(contact);
		}
		this.getJsonResponse().getWriter().print(JsonUtil.toJsonExpose(contacts));
	}
	
	/**
	 * @param userId
	 * @throws IOException 
	 */
	public void getUserInfo() throws IOException
	{
		Message msg = new Message();
		if(StringUtils.isNumeric(userId))
		{
			User user = userDAO.byid(Integer.valueOf(userId));
			if(user != null)
			{
				msg.setCode(Message.SUCCESS);
				msg.setStatement("success");
				msg.setContent(user);
			}
			else
			{
				msg.setCode(Message.NULL);
				msg.setStatement("user is not exist");
			}
		}
		else
		{
			msg.setCode(Message.ERROR);
			msg.setStatement("parameter error");
		}
		this.getJsonResponse().getWriter().print(JsonUtil.toJsonExpose(msg));
	}
	
	/**
	 * @describe Search user by keyword
	 * @param keyword
	 * @throws IOException 
	 */
	public void searchUser() throws IOException
	{
		Message msg = new Message();
		List<User> users = userDAO.searchByKeyword(keyword);
		msg.setCode(Message.SUCCESS);
		msg.setContent(users);
		this.getJsonResponse().getWriter().println(JsonUtil.toJsonExpose(users));
	}
	
	public FriendAction(UserDAO userDAO, UserGroupDAO userGroupDAO, GroupFriendDAO groupFriendDAO) {
		super();
		this.userDAO = userDAO;
		this.userGroupDAO = userGroupDAO;
		this.groupFriendDAO = groupFriendDAO;
	}
	

	public String getOrderNum()
	{
		return orderNum;
	}

	public void setOrderNum(String orderNum)
	{
		this.orderNum = orderNum;
	}

	public String getGroupName()
	{
		return groupName;
	}

	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getAgree() {
		return agree;
	}

	public void setAgree(String agree) {
		this.agree = agree;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
