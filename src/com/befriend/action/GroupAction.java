package com.befriend.action;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.core.net.server.UdpSocketServer;
import org.apache.struts2.ServletActionContext;
import org.hibernate.mapping.Array;
import org.json.JSONException;
import org.json.JSONObject;

import com.befriend.dao.GroupDAO;
import com.befriend.dao.UserDAO;
import com.befriend.entity.Admin;
import com.befriend.entity.Cis;
import com.befriend.entity.GroupChat;
import com.befriend.entity.GroupMembers;
import com.befriend.entity.Profile;
import com.befriend.entity.User;
import com.befriend.udp.UDPServer;
import com.befriend.util.OpeFunction;
import com.befriend.wechat.RefreshAccessToken;
import com.befriend.wechat.WechatKit;

public class GroupAction {
	private UDPServer udp;// 自建工具类
	private OpeFunction util;// 自建工具类
	private GroupDAO gdao;// 群聊dao
	private UserDAO udao;// 用户dao

	private int id = 0;// id

	User u = new User();// 用户对象
	GroupChat groupchat = new GroupChat();// 群资料
	Profile profile = new Profile();// 群 用户资料表
	GroupMembers groupMembers = new GroupMembers();// 群与用户 关系表
	Cis cis = new Cis();// 用户聊天表
	List<User> lu = new ArrayList<User>();// 用户对象
	List<GroupMembers> lgroupMembers = new ArrayList<GroupMembers>();// 群与用户 关系表
	List<GroupChat> lgroupchat = new ArrayList<GroupChat>();// 群资料
	List<Profile> lprofile = new ArrayList<Profile>();// 群 用户资料表
	List<Cis> lcis = new ArrayList<Cis>();// 群 用户聊天资料表
	/**
	 * GroupChat 表 群资料表
	 */
	/**
	 * 群名称
	 */
	private String name;
	/**
	 * 群创建者
	 */
	private int userid;
	/**
	 * 群图标 群图标 /IMG/Group 在班级中我的头像 /IMG/Group/Userimg
	 */
	private String img;
	/**
	 * 学校名称
	 */
	private String schoolname;
	/**
	 * 学校地址
	 */
	private String schooladdress;
	/**
	 * 年级
	 */
	private String grade;
	/**
	 * 班级
	 */
	private String gclass;
	/**
	 * 班主任姓名
	 */
	private String headteachername;
	/**
	 * 群号
	 */
	private int groupno;
	/**
	 * 初始是 true
	 */
	private Boolean b = true;
	/**
	 * 班级图片文件
	 */
	private File file;
	/**
	 * 文件名
	 */
	private String fileFileName;
	/**
	 * 文件类型
	 */
	private String fileContentType;

	/**
	 * GroupMembers 群关系表
	 * 
	 */
	/**
	 * 代表 0等待审核 1 是成员 2 被踢的 3是群主
	 */
	private int urp;
	/**
	 * 审核信息
	 */
	private String authentication;
	/**
	 * ip地址
	 */
	private String ip;

	/**
	 * 用户群资料表 Profile 家长独有
	 */
	/**
	 * 学生姓名
	 */
	private String sdname;

	/**
	 * 亲属关系
	 */
	private String kip;
	/**
	 * 共有
	 */
	/**
	 * 电话
	 */
	private String phone;

	/**
	 * 消息免打扰 0是接收 1是不接收
	 */
	private int ddb = -1;

	/**
	 * 0是家长 1是老师
	 */
	private int judge;
	/**
	 * 老师负责的科目
	 */
	private String rsbs;

	/**
	 * 发送者的id
	 */
	private int senduserid;
	/**
	 * 发送的 消息
	 */
	private String information;
	/**
	 * 时间
	 */
	private String time = util.getNowTime();
	/**
	 * 群id
	 */
	private int groupid;
	/**
	 * 环信 群id
	 */
	private String hxgroupid;
	private HttpSession session = ServletActionContext.getRequest()
			.getSession();
	/**
	 * 和环信 同步群和 群成员
	 * 
	 * @throws JSONException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void synchrGroup() throws JSONException, InterruptedException,
			IOException {
		Admin admin=(Admin)session.getAttribute("admin");
		if(admin==null){
			util.Out().print("你不是管理!");
			return;
		}
		if(admin.getLevel()!=1){
			util.Out().print("你不是管理!");
			return;
		}
		String url = "https://a1.easemob.com/topLong/wcfriend/chatgroups";

		lgroupchat = gdao.findByAll();
		for (int i = 0; i < lgroupchat.size(); i++) {

			System.out.println("第" + i + "个群!");
			Thread.sleep(100);
			
			if (lgroupchat.get(i) == null
					|| lgroupchat.get(i).getGroupid()!=null) {
				continue;
			}
			

			JSONObject json = new JSONObject();
			json.put("groupname",
					String.valueOf(lgroupchat.get(i).getGroupno()));
			json.put("desc", String.valueOf(lgroupchat.get(i).getGroupno()));
			json.put("public", true);
			json.put("approval", false);
			json.put("owner", String.valueOf(lgroupchat.get(i).getUserid()));
			json.put("maxusers", 500);

			lgroupMembers = gdao.FindGroupMembersbyurp(lgroupchat.get(i)
					.getId(), 1);
			List<String> members = new ArrayList<String>();
			for (int y = 0; y < lgroupMembers.size(); y++) {
				members.add(String.valueOf(lgroupMembers.get(y).getUserid()));
			}
			System.out.println(members.toString());

			json.put("members", members);
			System.out.println("token" + RefreshAccessToken.access_token);
			String wk = WechatKit.post(url, json,
					RefreshAccessToken.access_token);
			System.out.println("我的json:" + json.toString());
			System.out.println("环信 返回的 json:" + wk);
			if (wk != null) {
				JSONObject son = new JSONObject(wk);
				String Hxgroupid = son.getJSONObject("data").getString(
						"groupid");

				System.out.println("环信群id" + Hxgroupid);
				if (Hxgroupid != null) {
					groupchat = lgroupchat.get(i);
					groupchat.setGroupid(Hxgroupid);
					gdao.Update(groupchat);
					System.out.println("添加成功!");
				}
			}

		}
		util.Out().print("同步完成!");
	}

	/**
	 * 老师创建群
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void TEdificatora() throws IOException, InterruptedException {
		System.out.println("创建群!");
		System.out.println("gclass" + gclass);
		System.out.println("grade" + grade);
		System.out.println("headteachername" + headteachername);
		System.out.println("uid" + id);
		System.out.println("phone" + phone);
		System.out.println("name" + name);
		System.out.println("schooladdress" + schooladdress);
		System.out.println("hxgroupid" + hxgroupid);
		System.out.println("schoolname" + schoolname);

		u = udao.byid(id);

		// 判断 是否有这个用户 name == null
		if (u != null) {
			// 这些数据等于空 不能添加
			if (gclass == null || grade == null || headteachername == null
					|| phone == null || schoolname == null
					|| schooladdress == null || hxgroupid == null) {
				util.Out().print("null");
				return;
			}

			// 判断生成群号 会不会和以前冲突
			while (b) {
				// 随机生成8位随机数 作为 群号
				groupno = (int) ((Math.random() * 9 + 1) * 10000000);
				if (gdao.Findbygroupno(groupno) == null) {
					b = false;
				}

			}

			// 上传群头像
			if (file != null) {

				img = "/IMG/Groupimg/" + groupno;
				img = util.ufileToServer(img, file, fileFileName, "jpg", true);
				System.out.println(img);
				groupchat.setImg(img);// 群图标
				System.out.println(file.getPath());
				System.out.println("头像上传成功");
			}
			/**
			 * 添加群信息
			 */
			groupchat.setGroupid(hxgroupid);// 环信 群id
			groupchat.setGroupno(groupno);// 群号
			groupchat.setGclass(gclass);// 班级
			groupchat.setGrade(grade);// 年级
			groupchat.setHeadteachername(headteachername);// 班主任名字
			groupchat.setUserid(id);// 群创建者
			groupchat.setHtphone(phone);// 班主任电话
			groupchat.setSchoolname(schoolname);// 学校名字
			groupchat.setName(name);// 群名字
			groupchat.setSchooladdress(schooladdress);// 学校地址
			groupchat.setTime(time);// 时间
			gdao.save(groupchat);// 添加群
			System.out.println("创建成功!");
			// 通过群号查询群
			groupchat = gdao.Findbygroupno(groupno);
			if (groupchat == null) {
				util.Out().print("gnull");
				return;
			}

			// 获取群id
			groupMembers.setGroupid(groupchat.getId());
			/**
			 * 代表 0等待审核 1 是成员 2 被踢的 3是群主
			 */
			groupMembers.setUrp(3);
			groupMembers.setUserid(id);

			groupMembers.setTime(time);
			// 添加群关系 群主也是这个群的
			gdao.save(groupMembers);
			util.Out().print(util.ToJson(groupchat));
			System.out.println("成功返回群信息!");
		} else {
			util.Out().print(false);
			System.out.println("用户为空!");
		}

	}

	/**
	 * 查看群成员！
	 * 
	 * @throws IOException
	 */
	public void GroupCheck() throws IOException {

		// 通过群id 和 代表 0等待审核 1 是成员 2 被踢的 3是群主查询群成员或者 等待审核的
		lgroupMembers = gdao.FindGroupMembersbyid(groupid, 1);
		// 等于0 没有 审核信息或者 没有群成员
		if (lgroupMembers.size() == 0) {
			util.Out().print("null");
			return;
		}
		// 获取群信息
		groupchat = gdao.Findbyid(groupid);
		for (int i = 0; i < lgroupMembers.size(); i++) {
			// 获取 用户id 一个一个取出
			userid = lgroupMembers.get(i).getUserid();
			// 取出 添加
			lu.add(udao.byid(userid));

		}
		String result = "{\"lu\":" + util.ToJson(lu) + ",\"groupchat\":"
				+ util.ToJson(groupchat) + "}";
		util.Out().print(result);

	}

	/**
	 * 判断是否加入过 userid groupid
	 */
	public void Jointionok() throws IOException {

		groupchat = gdao.Findbyid(groupid);
		if (groupchat == null) {
			util.Out().print(false);
			System.out.println("没有该群");
			return;
		}

		groupMembers = gdao.FindGroupMembersbygiduidurp(groupid, userid, 1);
		if (groupMembers != null) {
			util.Out().print(false);
			System.out.println("已经加入过");
			return;
		}

		if (userid == groupchat.getUserid()) {
			util.Out().print(false);
			System.out.println("自己不能加入自己的群");
			return;
		}
		util.Out().print(true);
		System.out.println("你可以加入" + groupchat.getSchoolname());
		return;

	}

	/**
	 * 申请进群 判断是否加入过 judge==0 是家长加入 judge==1 是老师加入
	 * 
	 * @throws IOException
	 */
	public void Jointion() throws IOException {
		System.out.println("用户添加群 ！ ");
		System.out.println("群号: " + groupno);
		System.out.println("老师家长名字" + name);
		System.out.println("老师家长电话" + phone);
		System.out.println("0家长 1老师 " + judge);
		System.out.println("学生名字" + sdname);
		System.out.println("家长与学生关系" + kip);
		System.out.println("老师负则科目" + rsbs);
		System.out.println("用户id" + userid);

		urp = 1;// 等于0是进入审核
		// 通过群号查询群
		groupchat = gdao.Findbygroupno(groupno);
		if (groupchat == null) {
			util.Out().print("gnull");
			System.out.println("没有该群");
			return;
		}
		groupid = groupchat.getId();
		// 查询资料
		Profile pe = gdao.FindProfilebyid(groupid, userid);
		if (pe != null) {
			util.Out().print(false);
			System.out.println("已经加入过");
			return;

		}
		u = udao.byid(userid);
		if (u == null) {
			util.Out().print("unull");
			return;
		}
		profile.setName(name);// 名字 通
		profile.setGroupid(groupid);// 群id 通
		profile.setPhone(phone);// 电话 通
		profile.setJudge(judge);// 判断是否为老师 // 0是家长 1是老师
		profile.setUserid(userid);// 用户id
		profile.setDdb(0);// 是否接收群信息
		profile.setTime(time);// 进群时间
		/**
		 * 家长独有
		 */
		profile.setSdname(sdname);// 学生姓名
		profile.setKip(kip);// 与学生关系

		/**
		 * 老师独有
		 */
		profile.setRsbs(rsbs);// 负责科目
		gdao.save(profile);
		System.out.println("添加群 个人质料成功");
		// 获取群关系 删除掉
		lgroupMembers = gdao.FindGroupMembersbygiduidAll(groupid, userid);
		for (int i = 0; i < lgroupMembers.size(); i++) {
			if (lgroupMembers.get(i) != null) {
				gdao.Remove(lgroupMembers.get(i));
			}
		}
		// 新增关系
		groupMembers.setGroupid(groupid);
		groupMembers.setUrp(1);
		groupMembers.setUserid(userid);
		groupMembers.setTime(time);
		gdao.save(groupMembers);

		System.out.println("添加群与个人关系成功 环信群id:" + groupchat.getGroupid());
		util.Out().print(groupchat.getGroupid());
	}

	/**
	 * 群主 查看审核 参数 用户userid
	 * 
	 * @throws IOException
	 */
	public void GroupJointion() throws IOException {
		System.out.println(time);
		// 代表 0等待审核 1 是成员
		if (urp == 0) {
			urp = 0;// 等于0是进入审核
		}

		// 通过id查询用户
		u = udao.byid(userid);
		if (u == null) {
			util.Out().print("null");
			return;
		}
		lgroupchat = gdao.Findbyuserid(u.getId());
		if (lgroupchat.size() == 0) {
			util.Out().print("null");
			return;
		}
		// 用于存用户list
		List listlu = new ArrayList();
		// 用于存 群对象
		List<GroupChat> listgroupchat = new ArrayList<GroupChat>();
		for (int i = 0; i < lgroupchat.size(); i++) {
			// 获取群
			groupchat = lgroupchat.get(i);
			// 取得群id 获取 等待审核的用户list
			lgroupMembers = gdao.FindGroupMembersbyid(groupchat.getId(), urp);
			if (lgroupMembers.size() == 0) {
				// 结束本次循环
				continue;
			}
			for (int y = 0; y < lgroupMembers.size(); y++) {
				// 取得用户id
				userid = lgroupMembers.get(y).getUserid();
				// 添加用户
				lu.add(udao.byid(userid));

			}
			// 将取到的申请 的用户信息 存到listlu中
			listlu.add(lu);
			// 将取到的申请的 群 存到listgroupchat中
			listgroupchat.add(groupchat);
		}

		String result = "{\"lu\":" + util.ToJson(listlu) + ",\"groupchat\":"
				+ util.ToJson(listgroupchat) + "}";
		util.Out().print(result);

	}

	/**
	 * 通过群号查询群
	 * 
	 * @throws IOException
	 */
	public void Groupquiry() throws IOException {

		// 查询到群
		groupchat = gdao.Findbygroupno(groupno);
		if (groupchat == null) {
			util.Out().print("null");
			return;
		}
		util.Out().print(util.ToJson(groupchat));

	}

	/**
	 * 修改群资料 groupid userid
	 * 
	 * @throws IOException
	 */
	public void GroupModification() throws IOException {
		System.out.println("进入修改群资料方法");
		System.out.println("群groupid是:" + groupid);
		System.out.println("群userid是:" + userid);
		// 通过id查询到群
		groupchat = gdao.Findbyid(groupid);

		if (groupchat == null) {
			System.out.println("没有这个群");
			util.Out().print("null");
			return;
		}
		// 判断 修改群资料的 人 是否为群主
		if (userid != groupchat.getUserid()) {
			System.out.println("user不是群主");
			util.Out().print(false);
			return;
		}
		// 上传群头像
		if (file != null) {
			img = groupchat.getImg();
			if (img != null) {
				util.fileRemove(img);
				
			}
			img = "/IMG/Groupimg/" + groupchat.getGroupno();
			img = util.ufileToServer(img, file, fileFileName, "jpg", true);
			groupchat.setImg(img);
			

		}

		/**
		 * 修改群信息
		 */
		System.out.println(schoolname + "学校名称");
		if (schoolname != null) {
			groupchat.setSchoolname(schoolname);// 学校名称
			System.out.println(schoolname + "学校名称");
		}

		if (gclass != null) {
			groupchat.setGclass(gclass);// 班级

		}

		if (grade != null) {
			groupchat.setGrade(grade);// 年级
		}

		if (headteachername != null) {
			groupchat.setHeadteachername(headteachername);// 班主任名字
		}

		if (phone != null) {
			groupchat.setHtphone(phone);// 班主任电话
		}

		if (name != null) {
			groupchat.setName(name);// 群名字
		}

		if (schooladdress != null) {
			groupchat.setSchooladdress(schooladdress);// 学校地址
		}
		// 更新
		gdao.Update(groupchat);
		util.Out().print(true);
	}

	/**
	 * 我创建的班级和我加入的
	 * 
	 * @throws IOException
	 */
	public void Idtgroup() throws IOException {
		// userid = 81;
		urp = 1;// 通过审核
		// 我加入的群
		lgroupMembers = gdao.FindUseridGroupbyid(userid, urp);
		// 我创建的群
		lgroupchat = gdao.Findbyuserid(userid);

		int lg = lgroupchat.size();
		// System.out.println(userid+"有" + lg + "个群");
		for (int i = 0; i < lgroupMembers.size(); i++) {
			// 获取群id
			id = lgroupMembers.get(i).getGroupid();
			// 添加群
			lgroupchat.add(gdao.Findbyid(id));
		}

		if (lgroupchat.size() <= 0) {
			util.Out().print("null");
			return;
		}
		System.out.println(userid + "有" + lgroupchat.size() + "个群");
		String result = "{\"num\":" + lg + ",\"group\":"
				+ util.ToJson(lgroupchat) + "}";
		util.Out().print(result);
	}

	/**
	 * 查询我创建的群
	 * 
	 * @throws IOException
	 */
	public void Usergroup() throws IOException {
		// userid=81;
		System.out.println("进入查询我 userid" + userid);
		u = udao.byid(userid);
		lgroupchat = gdao.Findbyuserid(userid);
		// 判断是否为空
		if (lgroupchat.size() == 0 || u == null) {
			util.Out().print("null");
			return;
		}

		String result = "{\"u\":" + util.ToJson(u) + ",\"group\":"
				+ util.ToJson(lgroupchat) + "}";
		util.Out().print(result);
	}

	/**
	 * 通过群id 用户id 消息id 改成已读信息
	 * 
	 * @throws IOException
	 */
	public void groupCisUidDelete() throws IOException {
		System.out.println("groupid, id, userid" + groupid + "," + id + ","
				+ userid);
		if (ip == null) {
			System.out.println("ip==null");
			util.Out().print("ip==null");
			return;
		}
		// 查询聊天记录
		Cis c = gdao.Cisid(groupid, id, userid);

		if (c != null) {

			c.setOnline(1);

			c.setIp(ip);
			System.out.println("消息为:" + c.getInformation());
			gdao.Update(c);
			// gdao.Remove(c);
			System.out.println("groupCisUidDelete更改已读成功！");
			util.Out().print(true);
		} else {
			System.out.println("groupCisUidDelete更改已读成功！");
			util.Out().print(false);
		}
	}

	/**
	 * 群id 用户 groupSend要发送的 内容 // groupid = 7;// 群id 为7 // senduserid = 81; //
	 * information = "Hi Ben" + a;
	 * 
	 * @throws IOException
	 */
	public void groupSend() throws IOException {
		try {
			System.out.println(util.request().getContextPath());
			// groupid = 7; 群id
			// senduserid = 81;发送信息的用户id
			// information = "发送的内容;
			System.out.println("groupid" + groupid);
			System.out.println("senduserid" + senduserid);
			System.out.println("information" + information);
			if (information.getBytes().length >= 300) {
				util.Out().print(false);
				return;
			}
			User user = udao.byid(senduserid);// 发送信息的用户
			if (user == null) {
				System.out.println("user是空的");
				util.Out().print(false);
				return;
			}
			// 通过群id 和 代表 0等待审核 1 是成员 2 被踢的 查询群成员或者 等待审核的
			lgroupMembers = gdao.FindGroupMembersbyid(groupid, 1);// 获取这个群的群成员
			// 获取用户在群中的 资料
			profile = gdao.FindProfilebyid(groupid, senduserid);

			// 先给自己发
			Cis cis = new Cis();// 用户聊天表
			System.out.println("--------------=---------------------");

			cis.setGroupid(groupid);
			cis.setOnline(0);// 默认未读
			cis.setInformation(information);// 信息内容
			cis.setSenduserid(senduserid);// 发信息的用户id
			cis.setTime(time);
			cis.setUserid(senduserid);// 一个群里的 别的 用户的id
			gdao.save(cis);

			try {

				String url = "http://" + util.getLIP()
						+ util.request().getContextPath()
						+ "/onlineUserSend?id=" + user.getId();
				WechatKit.sendGet(url);

			} catch (Exception e) {
				// TODO: handle exception
			}

			System.out.println("需要发送给几个人 包括我自己" + lgroupMembers.size());
			for (int i = 0; i < lgroupMembers.size(); i++) {

				Cis ci = new Cis();// 用户聊天表
				userid = lgroupMembers.get(i).getUserid();
				// 获取用户
				u = udao.byid(userid);

				if (u == null) {
					continue;
				}
				if (u.getId() == user.getId()) {
					continue;
				}
				System.out.println("--------------=---------------------");
				System.out.println("需要给用户:" + u.getUsername());
				ci.setGroupid(groupid);

				ci.setInformation(information);// 信息内容
				ci.setSenduserid(senduserid);// 发信息的用户id
				ci.setTime(time);
				ci.setOnline(0);// 默认未读
				ci.setUserid(userid);// 一个群里的 别的 用户的id
				gdao.save(ci);
				/** && u.getOnline() == 1 */

				if (u.getIp() != null && u.getPort() != 0 && u.getOnline() == 1) {

					// 数据 要发送的 ip 端口号
					// udp.Send(result, u.getIp(), u.getPort());
					// 给别人发
					try {
						String url = "http://" + util.getLIP()
								+ util.request().getContextPath()
								+ "/onlineUserSend?id=" + u.getId();
						WechatKit.sendGet(url);

					} catch (Exception e) {
						// TODO: handle exception
					}
					// 查看用户是否有未读消息

					System.out.println("发送是否成功！");

				} else {
					System.out.println(u.getUsername() + "不在线！");

				}

			}

			util.Out().print(true);

		} catch (Exception e) {
			System.out.println("发送信息的异常" + e.getMessage());
			util.Out().print("发送信息的异常" + e.getMessage());
		}

	}

	/**
	 * 在用户收到信息之后 给我返回 我进行删除
	 * 
	 * 删除收取到信息的 用户聊天信息 群id 用户id
	 * 
	 * @throws IOException
	 */
	public void RemoveCis() throws IOException {
		// userid = 72;
		// 取到 该用户在该群 保存的聊天信息
		// groupid = 7;
		lcis = gdao.FindbyGroupChatiduseridCis(groupid, userid);

		int lci = lcis.size();
		System.out.println("有几条信息？" + lci);
		if (lci == 0) {
			util.Out().print(false);
			return;
		}
		for (int i = 0; i < lci; i++) {
			// 循环删除掉
			gdao.Remove(lcis.get(i));

		}
		util.Out().print(true);

	}

	/**
	 * 群id 用户 senduserid 要发送的 内容 添加
	 * 
	 * @throws IOException
	 */
	public void Sendf() throws IOException {

		System.out.println("用户发送群消息");
		// groupid = 7;// 群id 为7
		// senduserid = 70;
		// information = "Hi Ben" + a;
		if (groupid <= 0 || senduserid <= 0 || information == null) {

			util.Out().print("groupid或者senduserid或者information为null");
			return;
		}

		Cis ci = new Cis();
		ci.setGroupid(groupid);
		ci.setInformation(information);// 信息内容
		ci.setSenduserid(senduserid);// 发信息的用户id
		ci.setOnline(0);
		ci.setTime(time);
		ci.setUserid(userid);// 一个群里的 别的 用户的id
		gdao.save(ci);

		util.Out().print(true);

	}

	/**
	 * 获取聊天信息 groupid=7;
	 * 
	 * @return
	 */
	public void Sendjieshou() throws IOException {
		// groupid=7;
		System.out.println("群id" + groupid + "聊天信息的id" + id);
		// 获取聊天信息
		lcis = gdao.Cisid(groupid);
		if (lcis.size() == 0) {
			util.Out().print("null");
		}

		// 获取聊天信息
		// lcis = gdao.Cisid(groupid,id);
		lcis = gdao.Cisid(groupid);
		for (int i = 0; i < lcis.size(); i++) {
			// 获取到该信息是谁发的
			userid = lcis.get(i).getSenduserid();
			u = udao.byid(userid);
			profile = gdao.FindProfilebyid(groupid, userid);
			lprofile.add(profile);
			if (profile == null) {
				System.out.println("获取用户群资料" + profile);
			} else {
				System.out.println("获取用户群资料" + profile.getJudge());
			}
			lu.add(u);

		}
		// System.out.println("资料长度" + lprofile.size());
		// System.out.println(lu.size() + "用户=信息" + lcis.size());
		String result = "{\"lu\":" + util.ToJson(lu) + ",\"lcis\":"
				+ util.ToJson(lcis) + ",\"lprofile\":" + util.ToJson(lprofile)
				+ "}";
		util.Out().print(result);

	}

	/**
	 * 退群的方法 groupid,userid
	 * 
	 * @return
	 */

	public void RetreatGroup() throws IOException {
		// 1是群成员
		urp = 1;
		System.out.println("群id" + groupid + "用户id" + userid);
		// 查询该群
		groupchat = gdao.Findbyid(groupid);
		if (groupchat == null) {
			util.Out().print("gnull");
			return;

		}
		lgroupMembers = gdao.FindGroupMembersbygiduidAll(groupid, userid);
		// 等于空 说明他和这个群没关系
		if (lgroupMembers.size() <= 0) {
			System.out.println("改用户非群成员 不能解除群关系");
			util.Out().print(false);
			return;
		}
		for (int i = 0; i < lgroupMembers.size(); i++) {
			if (lgroupMembers.get(i) != null) {
				gdao.Remove(lgroupMembers.get(i));
			}
		}

		// 获取到该用户在该群的聊天记录
		lcis = gdao.FindbyGroupChatiduseridCis(groupid, userid);
		// 有聊天记录就删除
		if (lcis.size() > 0) {
			// 循环删除掉
			for (int i = 0; i < lcis.size(); i++) {
				gdao.Remove(lcis.get(i));

			}
		}
		// 查询群治疗
		profile = gdao.FindProfilebyid(groupid, userid);
		// 不等于null删除
		if (profile != null) {
			gdao.Remove(profile);
		}
		util.Out().print(groupchat.getGroupid());
		System.out.println("退群成功! 环信群id" + groupchat.getGroupid());

	}

	/**
	 * 用户修改自己的群资料 groupid,userid
	 * 
	 * @return
	 */

	public void ModifyGroupIn() throws IOException {
		System.out.println("用户修改治疗");
		System.out.println("群id" + groupid);
		System.out.println("用户id" + userid);
		// 1是群成员
		urp = 1;
		// 获取到该用户的群关系
		groupMembers = gdao.FindGroupMembersbygiduidurp(groupid, userid, urp);
		// 等于空 说明他和这个群没关系
		if (groupMembers == null) {
			System.out.println("改用户非群成员 不能修改群资料");
			util.Out().print(false);
			return;
		}
		profile = gdao.FindProfilebyid(groupid, userid);
		if (profile == null) {
			System.out.println("没有群资料");
			util.Out().print(false);
			return;
		}
		// 获取 0是家长 1是老师
		int Je = profile.getJudge();
		if (name != null) {
			profile.setName(name);
		}
		if (phone != null) {
			profile.setPhone(phone);
		}
		if (ddb != -1) {
			profile.setDdb(ddb);// 是否接收群信息 0接受 1不接受
		}
		// 判断是家长 还是老师 1老师 0家长
		if (Je == 1) {
			if (rsbs != null) {
				profile.setRsbs(rsbs);// 负者科目
			}

		} else {
			if (sdname != null) {
				profile.setSdname(sdname);// 学生姓名
			}
			if (kip != null) {
				profile.setKip(kip);// 亲属关系
			}

		}
		// 修改
		gdao.Update(profile);
		util.Out().print(true);
		System.out.println("修改成功");

	}

	/**
	 * 查看该群成员信息 groupid
	 * 
	 * @return
	 */

	public void ViewGroupMembers() throws IOException {
		// groupid=27;
		System.out.println("群id" + groupid);
		urp = 1;
		// 查询到该群
		groupchat = gdao.Findbyid(groupid);
		if (groupchat == null) {
			System.out.println("没有该群");
			util.Out().print(false);
			return;
		}
		// 查到群成员
		lgroupMembers = gdao.FindGroupMembersbyid(groupid, urp);
		for (int i = 0; i < lgroupMembers.size(); i++) {
			// 取到用户id
			userid = lgroupMembers.get(i).getUserid();
			// 取到用户
			u = udao.byid(userid);
			lu.add(u);
			profile = gdao.FindProfilebyid(groupid, userid);
			lprofile.add(profile);

		}
		System.out.println(lu.size() + "==" + lprofile.size() + "应该一样多");

		String result = "{\"lu\":" + util.ToJson(lu) + ",\"lprofile\":"
				+ util.ToJson(lprofile) + "" + ",\"groupchat\":"
				+ util.ToJson(groupchat) + "}";
		util.Out().print(result);

	}

	/**
	 * 查看成员信息 groupid
	 * 
	 * @return
	 */

	public void ViewGMembers() throws IOException {
		System.out.println("群id" + groupid + "用户id" + userid);
		profile = gdao.FindProfilebyid(groupid, userid);
		// 非空判断
		if (profile == null) {
			System.out.println("该群没有该人信息");
			util.Out().print(false);
			return;
		}
		util.Out().print(util.ToJson(profile));
	}

	/**
	 * 删除群 groupid userid
	 * 
	 * @return
	 */
	public void RemoveGroup() throws IOException {

		System.out.println("删除群!");
		// 查询到该群
		groupchat = gdao.Findbyid(groupid);

		if (groupchat == null) {
			System.out.println("没有该群");
			util.Out().print("gnull");
			return;
		}

		// 判断是不是建群者
		if (groupchat.getUserid() != userid) {
			System.out.println("你不是建群者");
			util.Out().print(false);
			return;
		}

		/**
		 * 删除关系
		 */
		// 查询到这个群的用户
		lgroupMembers = gdao.FindGroupMembersbyid(groupid);
		System.out.println("群里有" + lgroupMembers.size() + "个人");
		// 循环删除掉
		for (int i = 0; i < lgroupMembers.size(); i++) {
			gdao.Remove(lgroupMembers.get(i));

		}
		/**
		 * 删除聊天记录
		 */
		// 查询到这个群的聊天记录
		lcis = gdao.Cisid(groupid);
		System.out.println("群里有" + lcis.size() + "聊天记录");
		// 循环删除掉
		for (int i = 0; i < lcis.size(); i++) {
			gdao.Remove(lcis.get(i));

		}
		/**
		 * 删除群里用户资料
		 */
		// 查询到这个群的用户资料
		lprofile = gdao.FindProfilebyid(groupid);
		System.out.println("群里有" + lprofile.size() + "资料");
		// 循环删除掉
		for (int i = 0; i < lprofile.size(); i++) {
			gdao.Remove(lprofile.get(i));

		}

		System.out.println("删除成功!群图标删除是否成功？"
				+ util.fileRemove(groupchat.getImg()));

		// 获取 环信 群id
		hxgroupid = groupchat.getGroupid();
		// 删除这个群
		gdao.Remove(groupchat);
		System.out.println("删除成功! 环信 群id:" + hxgroupid);
		util.Out().print(hxgroupid);

	}

	public String getRsbs() {
		return rsbs;
	}

	public void setRsbs(String rsbs) {
		this.rsbs = rsbs;
	}

	/**
	 * @param gdao
	 * @param udao
	 */
	public GroupAction(GroupDAO gdao, UserDAO udao) {
		super();
		this.gdao = gdao;
		this.udao = udao;
	}

	public int getSenduserid() {
		return senduserid;
	}

	public void setSenduserid(int senduserid) {
		this.senduserid = senduserid;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSdname() {
		return sdname;
	}

	public void setSdname(String sdname) {
		this.sdname = sdname;
	}

	public String getKip() {
		return kip;
	}

	public void setKip(String kip) {
		this.kip = kip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getDdb() {
		return ddb;
	}

	public void setDdb(int ddb) {
		this.ddb = ddb;
	}

	public int getJudge() {
		return judge;
	}

	public void setJudge(int judge) {
		this.judge = judge;
	}

	public int getGroupno() {
		return groupno;
	}

	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GroupChat getGroupchat() {
		return groupchat;
	}

	public void setGroupchat(GroupChat groupchat) {
		this.groupchat = groupchat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	public String getSchooladdress() {
		return schooladdress;
	}

	public void setSchooladdress(String schooladdress) {
		this.schooladdress = schooladdress;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getGclass() {
		return gclass;
	}

	public void setGclass(String gclass) {
		this.gclass = gclass;
	}

	public String getHeadteachername() {
		return headteachername;
	}

	public void setHeadteachername(String headteachername) {
		this.headteachername = headteachername;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public Boolean getB() {
		return b;
	}

	public void setB(Boolean b) {
		this.b = b;
	}

	public String getAuthentication() {
		return authentication;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	public int getUrp() {
		return urp;
	}

	public void setUrp(int urp) {
		this.urp = urp;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHxgroupid() {
		return hxgroupid;
	}

	public void setHxgroupid(String hxgroupid) {
		this.hxgroupid = hxgroupid;
	}

}
