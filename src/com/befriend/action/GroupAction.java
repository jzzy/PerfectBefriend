package com.befriend.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
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
import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("all")
public class GroupAction{
	private UDPServer udp;
	private OpeFunction util;
	private GroupDAO gdao;
	private UserDAO udao;
	private int id = 0;
	User u = new User();
	GroupChat groupchat = new GroupChat();
	Profile profile = new Profile();
	GroupMembers groupMembers = new GroupMembers();
	Cis cis = new Cis();
	List<User> lu = new ArrayList<User>();
	List<GroupMembers> lgroupMembers = new ArrayList<GroupMembers>();
	List<GroupChat> lgroupchat = new ArrayList<GroupChat>();
	List<Profile> lprofile = new ArrayList<Profile>();
	List<Cis> lcis = new ArrayList<Cis>();
	private String name=null;
	private int userid;
	private String img=null;
	private String schoolname=null;
	private String schooladdress=null;
	private String grade=null;
	private String gclass=null;
	private String headteachername=null;
	private int groupno=0;
	private Boolean b = true;
	private File file=null;
	private String fileFileName=null;
	private String fileContentType=null;
	private int urp;
	private String authentication=null;
	private String ip=null;
	private String sdname=null;
	private String kip=null;
	private String phone=null;
	private int ddb = -1;
	private int judge;
	private String rsbs;
	private int senduserid;
	private String information=null;
	private String time = util.getNowTime();
	private int groupid;
	private String hxgroupid=null;
	private int condtion;
	private int type;
	private String area=null;
	private String areas=null;
	private int classgroup;
	private String gclassintroduction=null;
	private HttpSession session = ServletActionContext.getRequest()
			.getSession();
	/**
	 * �ͻ��� ͬ��Ⱥ�� Ⱥ��Ա
	 * 
	 * @throws JSONException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public synchronized void synchrGroup() throws JSONException, InterruptedException,
			IOException {
		Admin admin=(Admin)session.getAttribute("admin");
		if(admin==null){
			util.Out().print("�㲻�ǹ���!");
			return;
		}
		if(admin.getLevel()!=1){
			util.Out().print("�㲻�ǹ���!");
			return;
		}
		String url = "https://a1.easemob.com/topLong/wcfriend/chatgroups";

		lgroupchat = gdao.findByAll();
		for (int i = 0; i < lgroupchat.size(); i++) {

			System.out.println("��" + i + "��Ⱥ!");
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
			System.out.println("�ҵ�json:" + json.toString());
			System.out.println("���� ���ص� json:" + wk);
			if (wk != null) {
				JSONObject son = new JSONObject(wk);
				String Hxgroupid = son.getJSONObject("data").getString(
						"groupid");

				System.out.println("����Ⱥid" + Hxgroupid);
				if (Hxgroupid != null) {
					groupchat = lgroupchat.get(i);
					groupchat.setGroupid(Hxgroupid);
					gdao.Update(groupchat);
					System.out.println("��ӳɹ�!");
				}
			}

		}
		util.Out().print("ͬ�����!");
	}

	/**
	 * ����Ⱥ
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void saveGroup() throws IOException, InterruptedException {
		System.out.println("����Ⱥ!");
		System.out.println("gclass" + gclass);
		System.out.println("grade" + grade);
		System.out.println("headteachername" + headteachername);
		System.out.println("userid" + userid);
		System.out.println("phone" + phone);
		System.out.println("name" + name);
		System.out.println("schooladdress" + schooladdress);
		System.out.println("hxgroupid" + hxgroupid);
		System.out.println("schoolname" + schoolname);
		System.out.println("condtion" + condtion);
		System.out.println("gclassintroduction" + gclassintroduction);
		System.out.println("classgroup" + classgroup);

		u = udao.byid(userid);
		// �ж� �Ƿ�������û� name == null
		if (u == null) {
			util.Out().print("unull");
			return;
		}

		// ��Щ���ݵ��ڿ� �������
		// if (gclass == null || grade == null || headteachername == null
		// || /**phone == null ||*/
		// /**hxgroupid == null||*/gclassintroduction==null||condtion<=0) {
		// util.Out().print("null");
		// return;
		// }
		if (hxgroupid == null || condtion <= 0) {
			util.Out().print("null");
			return;
		}
		// �ϴ�Ⱥͷ��
		if (file != null) {

			img = "/IMG/Groupimg/" + OpeFunction.getNameDayTime();
			img = util.ufileToServer(img, file, "jpg");
			System.out.println(img);
			groupchat.setImg(img);// Ⱥͼ��
			System.out.println("ͷ���ϴ��ɹ�");
		}
		/**
		 * ���Ⱥ��Ϣ
		 */
		groupchat.setClassgroup(classgroup);// 1Ⱥ 2�༶
		groupchat.setType(type);// ���
		groupchat.setArea(area);// ����ʡ
		groupchat.setAreas(areas);// ������
		groupchat.setGroupid(hxgroupid);// ���� Ⱥid
		groupchat.setJoincondition(condtion);// 1 �����κ��� 2 ��Ҫ��֤��Ϣ 3�������κ���
		groupchat.setGclass(gclass);// �༶
		groupchat.setGrade(grade);// �꼶
		groupchat.setHeadteachername(headteachername);// ����������
		groupchat.setUserid(userid);// Ⱥ������
		groupchat.setGclassintroduction(gclassintroduction);// �༶���
		groupchat.setPhone(phone);// �����ε绰
		groupchat.setSchoolname(schoolname);// ѧУ����
		groupchat.setName(name);// Ⱥ����
		groupchat.setSchooladdress(schooladdress);// ѧУ��ַ
		groupchat.setTime(time);// ʱ��
		synchronized (this) {

			GroupChat grpc = gdao.maxGroupno();
			if (grpc != null) {
				groupno = grpc.getGroupno() + 1;
				groupchat.setGroupno(groupno);// Ⱥ��

			} else {
				groupno = 10000000;
				groupchat.setGroupno(groupno);// Ⱥ��
			}

			gdao.save(groupchat);// ���Ⱥ
			System.out.println("�����ɹ�!");
		}
		// ͨ��Ⱥ�Ų�ѯȺ
		groupchat = gdao.Findbygroupno(groupno);
		if (groupchat == null) {
			util.Out().print("gnull");
			return;
		}

		// ��ȡȺid
		groupMembers.setGroupid(groupchat.getId());
		/**
		 * ���� 0�ȴ���� 1 �ǳ�Ա 2 ���ߵ� 3��Ⱥ��
		 */
		groupMembers.setUrp(3);
		groupMembers.setUserid(userid);
		groupMembers.setTime(time);
		// ���Ⱥ��ϵ Ⱥ��Ҳ�����Ⱥ��
		gdao.save(groupMembers);
		util.Out().print(util.ToJson(groupchat));
		System.out.println("�ɹ�����Ⱥ��Ϣ!");

	}

	/**
	 * ���
	 * 
	 * @throws IOException
	 */
	public void GroupCheck() throws IOException {
		if(urp==0){
			urp=1;
		}
		// ͨ��Ⱥid �� ���� 0�ȴ���� 1 �ǳ�Ա 2 ���ߵ� 3��Ⱥ����ѯȺ��Ա���� �ȴ���˵�
		lgroupMembers = gdao.FindGroupMembersbyid(groupid, urp);
		// ����0 û�� �����Ϣ���� û��Ⱥ��Ա
		if (lgroupMembers.size() == 0) {
			util.Out().print("null");
			return;
		}
		// ��ȡȺ��Ϣ
		groupchat = gdao.Findbyid(groupid);
		for (int i = 0; i < lgroupMembers.size(); i++) {
			// ��ȡ �û�id һ��һ��ȡ��
			userid = lgroupMembers.get(i).getUserid();
			// ȡ�� ���
			lu.add(udao.byid(userid));

		}
		String result = "{\"lu\":" + util.ToJson(lu) + ",\"groupchat\":"
				+ util.ToJson(groupchat) + "}";
		util.Out().print(result);

	}

	/**
	 * �ж��Ƿ����� userid groupid
	 */
	public void Jointionok() throws IOException {

		groupchat = gdao.Findbyid(groupid);
		if (groupchat == null) {
			util.Out().print(false);
			System.out.println("û�и�Ⱥ");
			return;
		}

		groupMembers = gdao.FindGroupMembersbygiduidurp(groupid, userid, 1);
		if (groupMembers != null) {
			util.Out().print(false);
			System.out.println("�Ѿ������");
			return;
		}

		if (userid == groupchat.getUserid()) {
			util.Out().print(false);
			System.out.println("�Լ����ܼ����Լ���Ⱥ");
			return;
		}
		util.Out().print(true);
		System.out.println("����Լ���" + groupchat.getSchoolname());
		return;

	}

	/**
	 * �����Ⱥ  judge==0 �Ǽҳ����� judge==1 ����ʦ����
	 * 
	 * @throws IOException
	 */
	public synchronized void Jointion() throws IOException {
		System.out.println("�û����Ⱥ �� ");
		System.out.println("Ⱥ��: " + groupno);
		System.out.println("��ʦ�ҳ�����" + name);
		System.out.println("��ʦ�ҳ��绰" + phone);
		System.out.println("0�ҳ� 1��ʦ " + judge);
		System.out.println("ѧ������" + sdname);
		System.out.println("�ҳ���ѧ����ϵ" + kip);
		System.out.println("��ʦ�����Ŀ" + rsbs);
		System.out.println("�û�id" + userid);

		urp = 1;// ����0�ǽ������
		u = udao.byid(userid);
		if (u == null) {
			util.Out().print("unull");
			return;
		}
		// ͨ��Ⱥ�Ų�ѯȺ
		
		groupchat = gdao.Findbyid(groupid);
		if (groupchat == null) {
			util.Out().print("gnull");
			System.out.println("û�и�Ⱥ");
			return;
		}
		if(groupchat.getJoincondition()==1){
			urp=1;
		}else if(groupchat.getJoincondition()==2){
			urp=1;
		}else if(groupchat.getJoincondition()==3){
			util.Out().print("no");
			return;
		}
		groupid = groupchat.getId();
		// ��ȡȺ��ϵ
		groupMembers = gdao.FindGroupMembersbygiduidAll(groupid, userid);
		if(groupMembers!=null){
			util.Out().print(false);
			return;
		}	
		profile.setName(name);// ���� ͨ
		profile.setGroupid(groupid);// Ⱥid ͨ
		profile.setPhone(phone);// �绰 ͨ
		profile.setJudge(judge);// �ж��Ƿ�Ϊ��ʦ // 0�Ǽҳ� 1����ʦ
		profile.setUserid(userid);// �û�id
		profile.setDdb(0);// �Ƿ����Ⱥ��Ϣ
		profile.setTime(time);// ��Ⱥʱ��
		/**
		 * �ҳ�����
		 */
		profile.setSdname(sdname);// ѧ������
		profile.setKip(kip);// ��ѧ����ϵ

		/**
		 * ��ʦ����
		 */
		profile.setRsbs(rsbs);// �����Ŀ
		gdao.save(profile);
		System.out.println("���Ⱥ �������ϳɹ�");
		groupMembers=new GroupMembers();
		// ������ϵ
		groupMembers.setGroupid(groupid);
		groupMembers.setUrp(urp);
		groupMembers.setUserid(userid);
		groupMembers.setTime(time);
		gdao.save(groupMembers);
		System.out.println("���Ⱥ����˹�ϵ�ɹ� ����Ⱥid:" + groupchat.getGroupid());
		util.Out().print(true);
	}

	/**
	 * Ⱥ�� �鿴��� ���� �û�userid
	 * 
	 * @throws IOException
	 */
	public void GroupJointion() throws IOException {
		// ͨ��id��ѯ�û�
		lgroupchat = gdao.Findbyuserid(userid,classgroup);
		if (lgroupchat.size() == 0) {
			util.Out().print("null");
			return;
		}
		// ���ڴ��û�list
		List listlu = new ArrayList();
		// ���ڴ� Ⱥ����
		List<GroupChat> listgroupchat = new ArrayList<GroupChat>();
		for (int i = 0; i < lgroupchat.size(); i++) {
			// ��ȡȺ
			groupchat = lgroupchat.get(i);
			// ȡ��Ⱥid ��ȡ �ȴ���˵��û�list
			lgroupMembers = gdao.FindGroupMembersbyid(groupchat.getId(), urp);
			if (lgroupMembers.size() == 0) {
				// ��������ѭ��
				continue;
			}
			for (int y = 0; y < lgroupMembers.size(); y++) {
				// ȡ���û�id
				userid = lgroupMembers.get(y).getUserid();
				// ����û�
				lu.add(udao.byid(userid));

			}
			// ��ȡ�������� ���û���Ϣ �浽listlu��
			listlu.add(lu);
			// ��ȡ��������� Ⱥ �浽listgroupchat��
			listgroupchat.add(groupchat);
		}

		String result = "{\"lu\":" + util.ToJson(listlu) + ",\"groupchat\":"
				+ util.ToJson(listgroupchat) + "}";
		util.Out().print(result);

	}
	public void likeGroupquiry() throws IOException {
		Map<String, String> paras = new HashMap<String, String>();
		if (!OpeFunction.isEmpty(area)) {
			paras.put("area", area);
		}
		if (!OpeFunction.isEmpty(areas)) {
			paras.put("areas", areas);
		}
		if (!OpeFunction.isEmpty(gclass)) {
			paras.put("gclass", gclass);
		}
		if (!OpeFunction.isEmpty(schoolname)) {
			paras.put("schoolname", schoolname);
		}
		
		List<GroupChat> gl = gdao.likeGroupChat(classgroup, name,paras);
		util.Out().print(util.ToJson(gl));

	}
	public void Groupquiry() throws IOException {
		groupchat = gdao.Findbyid(groupid);
		util.Out().print(util.ToJson(groupchat));

	}


	/**
	 * �޸�Ⱥ���� groupid userid
	 * 
	 * @throws IOException
	 */
	public void GroupModification() throws IOException {
		System.out.println("�����޸�Ⱥ���Ϸ���");
		System.out.println("Ⱥgroupid��:" + groupid);
		System.out.println("Ⱥuserid��:" + userid);
		// ͨ��id��ѯ��Ⱥ
		groupchat = gdao.Findbyid(groupid);

		if (groupchat == null) {
			System.out.println("û�����Ⱥ");
			util.Out().print("null");
			return;
		}
		// �ж� �޸�Ⱥ���ϵ� �� �Ƿ�ΪȺ��
		if (userid != groupchat.getUserid()) {
			System.out.println("user����Ⱥ��");
			util.Out().print(false);
			return;
		}
		// �ϴ�Ⱥͷ��
		if (file != null) {
			img = groupchat.getImg();
			if (img != null) {
				util.fileRemove(img);
				
			}
			img = "/IMG/Groupimg/" + groupchat.getGroupno();
			img = util.ufileToServer(img, file, "jpg");
			groupchat.setImg(img);
			

		}

		/**
		 * �޸�Ⱥ��Ϣ
		 */
		System.out.println(schoolname + "ѧУ����");
		if (schoolname != null) {
			groupchat.setSchoolname(schoolname);// ѧУ����
			System.out.println(schoolname + "ѧУ����");
		}

		if (gclass != null) {
			groupchat.setGclass(gclass);// �༶

		}

		if (grade != null) {
			groupchat.setGrade(grade);// �꼶
		}

		if (headteachername != null) {
			groupchat.setHeadteachername(headteachername);// ����������
		}

		if (phone != null) {
			groupchat.setPhone(phone);// �����ε绰
		}

		if (name != null) {
			groupchat.setName(name);// Ⱥ����
		}

		if (schooladdress != null) {
			groupchat.setSchooladdress(schooladdress);// ѧУ��ַ
		}
		if(gclassintroduction!=null){
			groupchat.setGclassintroduction(gclassintroduction);
		}
		if(area!=null){
			groupchat.setArea(area);
		}
		if(areas!=null){
			groupchat.setAreas(areas);
		}
		if(type>0){
		groupchat.setType(type);
		}
		// ����
		gdao.Update(groupchat);
		util.Out().print(true);
	}

	/**
	 * �Ҵ����İ༶���Ҽ����
	 * 
	 * @throws IOException
	 */
	public void Idtgroup() throws IOException {
		// userid = 81;
		urp = 1;// ͨ�����
		// �Ҽ����Ⱥ
		lgroupMembers = gdao.FindUseridGroupbyid(userid, urp);
		// �Ҵ�����Ⱥ
		lgroupchat = gdao.Findbyuserid(userid,classgroup);

		int lg = lgroupchat.size();
		// System.out.println(userid+"��" + lg + "��Ⱥ");
		for (int i = 0; i < lgroupMembers.size(); i++) {
			// ��ȡȺid
			id = lgroupMembers.get(i).getGroupid();
			// ���Ⱥ
			lgroupchat.add(gdao.Findbyclassgroupid(id,classgroup));
		}

		if (lgroupchat.size() <= 0) {
			util.Out().print("null");
			return;
		}
		System.out.println(userid + "��" + lgroupchat.size() + "��Ⱥ");
		String result = "{\"num\":" + lg + ",\"group\":"
				+ util.ToJson(lgroupchat) + "}";
		util.Out().print(result);
	}

	/**
	 * ��ѯ�Ҵ�����Ⱥ
	 * 
	 * @throws IOException
	 */
	public void Usergroup() throws IOException {
		// userid=81;
		System.out.println("�����ѯ�� userid" + userid);
		u = udao.byid(userid);
		lgroupchat = gdao.Findbyuserid(userid,classgroup);
		// �ж��Ƿ�Ϊ��
		if (lgroupchat.size() == 0 || u == null) {
			util.Out().print("null");
			return;
		}

		String result = "{\"u\":" + util.ToJson(u) + ",\"group\":"
				+ util.ToJson(lgroupchat) + "}";
		util.Out().print(result);
	}

	/**
	 * ͨ��Ⱥid �û�id ��Ϣid �ĳ��Ѷ���Ϣ
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
		// ��ѯ�����¼
		Cis c = gdao.Cisid(groupid, id, userid);

		if (c != null) {

			c.setOnline(1);

			c.setIp(ip);
			System.out.println("��ϢΪ:" + c.getInformation());
			gdao.Update(c);
			// gdao.Remove(c);
			System.out.println("groupCisUidDelete�����Ѷ��ɹ���");
			util.Out().print(true);
		} else {
			System.out.println("groupCisUidDelete�����Ѷ��ɹ���");
			util.Out().print(false);
		}
	}

	/**
	 * Ⱥid �û� groupSendҪ���͵� ���� // groupid = 7;// Ⱥid Ϊ7 // senduserid = 81; //
	 * information = "Hi Ben" + a;
	 * 
	 * @throws IOException
	 */
	
	public void groupSend() throws IOException {
		try {
			System.out.println(util.request().getContextPath());
			// groupid = 7; Ⱥid
			// senduserid = 81;������Ϣ���û�id
			// information = "���͵�����;
			System.out.println("groupid" + groupid);
			System.out.println("senduserid" + senduserid);
			System.out.println("information" + information);
			if (information.getBytes().length >= 300) {
				util.Out().print(false);
				return;
			}
			User user = udao.byid(senduserid);// ������Ϣ���û�
			if (user == null) {
				System.out.println("user�ǿյ�");
				util.Out().print(false);
				return;
			}
			// ͨ��Ⱥid �� ���� 0�ȴ���� 1 �ǳ�Ա 2 ���ߵ� ��ѯȺ��Ա���� �ȴ���˵�
			lgroupMembers = gdao.FindGroupMembersbyid(groupid, 1);// ��ȡ���Ⱥ��Ⱥ��Ա
			// ��ȡ�û���Ⱥ�е� ����
			profile = gdao.FindProfilebyid(groupid, senduserid);

			// �ȸ��Լ���
			Cis cis = new Cis();// �û������
			System.out.println("--------------=---------------------");

			cis.setGroupid(groupid);
			cis.setOnline(0);// Ĭ��δ��
			cis.setInformation(information);// ��Ϣ����
			cis.setSenduserid(senduserid);// ����Ϣ���û�id
			cis.setTime(time);
			cis.setUserid(senduserid);// һ��Ⱥ��� ��� �û���id
			gdao.save(cis);

			try {

				String url = "http://" + util.getLIP()
						+ util.request().getContextPath()
						+ "/onlineUserSend?id=" + user.getId();
				WechatKit.sendGet(url);

			} catch (Exception e) {
				// TODO: handle exception
			}

			System.out.println("��Ҫ���͸������� �������Լ�" + lgroupMembers.size());
			for (int i = 0; i < lgroupMembers.size(); i++) {

				Cis ci = new Cis();// �û������
				userid = lgroupMembers.get(i).getUserid();
				// ��ȡ�û�
				u = udao.byid(userid);

				if (u == null) {
					continue;
				}
				if (u.getId() == user.getId()) {
					continue;
				}
				System.out.println("--------------=---------------------");
				System.out.println("��Ҫ���û�:" + u.getUsername());
				ci.setGroupid(groupid);

				ci.setInformation(information);// ��Ϣ����
				ci.setSenduserid(senduserid);// ����Ϣ���û�id
				ci.setTime(time);
				ci.setOnline(0);// Ĭ��δ��
				ci.setUserid(userid);// һ��Ⱥ��� ��� �û���id
				gdao.save(ci);
				/** && u.getOnline() == 1 */

				if (u.getIp() != null && u.getPort() != 0 && u.getOnline() == 1) {

					// ���� Ҫ���͵� ip �˿ں�
					// udp.Send(result, u.getIp(), u.getPort());
					// �����˷�
					try {
						String url = "http://" + util.getLIP()
								+ util.request().getContextPath()
								+ "/onlineUserSend?id=" + u.getId();
						WechatKit.sendGet(url);

					} catch (Exception e) {
						// TODO: handle exception
					}
					// �鿴�û��Ƿ���δ����Ϣ

					System.out.println("�����Ƿ�ɹ���");

				} else {
					System.out.println(u.getUsername() + "�����ߣ�");

				}

			}

			util.Out().print(true);

		} catch (Exception e) {
			System.out.println("������Ϣ���쳣" + e.getMessage());
			util.Out().print("������Ϣ���쳣" + e.getMessage());
		}

	}

	/**
	 * ���û��յ���Ϣ֮�� ���ҷ��� �ҽ���ɾ��
	 * 
	 * ɾ����ȡ����Ϣ�� �û�������Ϣ Ⱥid �û�id
	 * 
	 * @throws IOException
	 */
	public void RemoveCis() throws IOException {
		// userid = 72;
		// ȡ�� ���û��ڸ�Ⱥ �����������Ϣ
		// groupid = 7;
		lcis = gdao.FindbyGroupChatiduseridCis(groupid, userid);

		int lci = lcis.size();
		System.out.println("�м�����Ϣ��" + lci);
		if (lci == 0) {
			util.Out().print(false);
			return;
		}
		for (int i = 0; i < lci; i++) {
			// ѭ��ɾ����
			gdao.Remove(lcis.get(i));

		}
		util.Out().print(true);

	}

	/**
	 * Ⱥid �û� senduserid Ҫ���͵� ���� ���
	 * 
	 * @throws IOException
	 */
	public void Sendf() throws IOException {

		System.out.println("�û�����Ⱥ��Ϣ");
		// groupid = 7;// Ⱥid Ϊ7
		// senduserid = 70;
		// information = "Hi Ben" + a;
		if (groupid <= 0 || senduserid <= 0 || information == null) {

			util.Out().print("groupid����senduserid����informationΪnull");
			return;
		}

		Cis ci = new Cis();
		ci.setGroupid(groupid);
		ci.setInformation(information);// ��Ϣ����
		ci.setSenduserid(senduserid);// ����Ϣ���û�id
		ci.setOnline(0);
		ci.setTime(time);
		ci.setUserid(userid);// һ��Ⱥ��� ��� �û���id
		gdao.save(ci);

		util.Out().print(true);

	}

	/**
	 * ��ȡ������Ϣ groupid=7;
	 * 
	 * @return
	 */
	public void Sendjieshou() throws IOException {
		// groupid=7;
		System.out.println("Ⱥid" + groupid + "������Ϣ��id" + id);
		// ��ȡ������Ϣ
		lcis = gdao.Cisid(groupid);
		if (lcis.size() == 0) {
			util.Out().print("null");
		}

		// ��ȡ������Ϣ
		// lcis = gdao.Cisid(groupid,id);
		lcis = gdao.Cisid(groupid);
		for (int i = 0; i < lcis.size(); i++) {
			// ��ȡ������Ϣ��˭����
			userid = lcis.get(i).getSenduserid();
			u = udao.byid(userid);
			profile = gdao.FindProfilebyid(groupid, userid);
			lprofile.add(profile);
			if (profile == null) {
				System.out.println("��ȡ�û�Ⱥ����" + profile);
			} else {
				System.out.println("��ȡ�û�Ⱥ����" + profile.getJudge());
			}
			lu.add(u);

		}
		// System.out.println("���ϳ���" + lprofile.size());
		// System.out.println(lu.size() + "�û�=��Ϣ" + lcis.size());
		String result = "{\"lu\":" + util.ToJson(lu) + ",\"lcis\":"
				+ util.ToJson(lcis) + ",\"lprofile\":" + util.ToJson(lprofile)
				+ "}";
		util.Out().print(result);

	}

	/**
	 * ��Ⱥ�ķ��� groupid,userid
	 * 
	 * @return
	 */

	public void RetreatGroup() throws IOException {
		// 1��Ⱥ��Ա
		urp = 1;
		System.out.println("Ⱥid" + groupid + "�û�id" + userid);
		// ��ѯ��Ⱥ
		groupchat = gdao.Findbyid(groupid);
		if (groupchat == null) {
			util.Out().print("gnull");
			return;

		}
		groupMembers = gdao.FindGroupMembersbygiduidAll(groupid, userid);
		// ���ڿ� ˵���������Ⱥû��ϵ
		if (groupMembers!=null) {
			gdao.Remove(groupMembers);
		}else{
			util.Out().print(false);
			return;
		}

		// ��ȡ�����û��ڸ�Ⱥ�������¼
		lcis = gdao.FindbyGroupChatiduseridCis(groupid, userid);
		// �������¼��ɾ��
		if (lcis.size() > 0) {
			// ѭ��ɾ����
			for (int i = 0; i < lcis.size(); i++) {
				gdao.Remove(lcis.get(i));

			}
		}
		// ��ѯȺ����
		profile = gdao.FindProfilebyid(groupid, userid);
		// ������nullɾ��
		if (profile != null) {
			gdao.Remove(profile);
		}else{
			util.Out().print(false);
			return;
		}
		util.Out().print(groupchat.getGroupid());
		System.out.println("��Ⱥ�ɹ�! ����Ⱥid" + groupchat.getGroupid());

	}

	/**
	 * �û��޸��Լ���Ⱥ���� groupid,userid
	 * 
	 * @return
	 */

	public void ModifyGroupIn() throws IOException {
		System.out.println("�û��޸�����");
		System.out.println("Ⱥid" + groupid);
		System.out.println("�û�id" + userid);
		// 1��Ⱥ��Ա
		urp = 1;
		// ��ȡ�����û���Ⱥ��ϵ
		groupMembers = gdao.FindGroupMembersbygiduidurp(groupid, userid, urp);
		// ���ڿ� ˵���������Ⱥû��ϵ
		if (groupMembers == null) {
			System.out.println("���û���Ⱥ��Ա �����޸�Ⱥ����");
			util.Out().print(false);
			return;
		}
		profile = gdao.FindProfilebyid(groupid, userid);
		if (profile == null) {
			System.out.println("û��Ⱥ����");
			util.Out().print(false);
			return;
		}
		// ��ȡ 0�Ǽҳ� 1����ʦ
		int Je = profile.getJudge();
		if (name != null) {
			profile.setName(name);
		}
		if (phone != null) {
			profile.setPhone(phone);
		}
		if (ddb != -1) {
			profile.setDdb(ddb);// �Ƿ����Ⱥ��Ϣ 0���� 1������
		}
		// �ж��Ǽҳ� ������ʦ 1��ʦ 0�ҳ�
		if (Je == 1) {
			if (rsbs != null) {
				profile.setRsbs(rsbs);// ���߿�Ŀ
			}

		} else {
			if (sdname != null) {
				profile.setSdname(sdname);// ѧ������
			}
			if (kip != null) {
				profile.setKip(kip);// ������ϵ
			}

		}
		// �޸�
		gdao.Update(profile);
		util.Out().print(true);
		System.out.println("�޸ĳɹ�");

	}

	/**
	 * �鿴��Ⱥ��Ա��Ϣ groupid
	 * 
	 * @return
	 */

	public void ViewGroupMembers() throws IOException {
		// groupid=27;
		System.out.println("Ⱥid" + groupid);
		urp = 1;
		// ��ѯ����Ⱥ
		groupchat = gdao.Findbyid(groupid);
		if (groupchat == null) {
			System.out.println("û�и�Ⱥ");
			util.Out().print(false);
			return;
		}
		// �鵽Ⱥ��Ա
		lgroupMembers = gdao.FindGroupMembersbyid(groupid, urp);
		for (int i = 0; i < lgroupMembers.size(); i++) {
			// ȡ���û�id
			userid = lgroupMembers.get(i).getUserid();
			// ȡ���û�
			u = udao.byid(userid);
			lu.add(u);
			profile = gdao.FindProfilebyid(groupid, userid);
			lprofile.add(profile);

		}

		String result = "{\"lu\":" + util.ToJson(lu) + ",\"lprofile\":"
				+ util.ToJson(lprofile) + "" + ",\"groupchat\":"
				+ util.ToJson(groupchat) + "}";
		util.Out().print(result);

	}

	/**
	 * �鿴��Ա��Ϣ groupid
	 * 
	 * @return
	 */

	public void ViewGMembers() throws IOException {
		System.out.println("Ⱥid" + groupid + "�û�id" + userid);
		profile = gdao.FindProfilebyid(groupid, userid);
		// �ǿ��ж�
		if (profile == null) {
			System.out.println("��Ⱥû�и�����Ϣ");
			util.Out().print(false);
			return;
		}
		util.Out().print(util.ToJson(profile));
	}

	/**
	 * ɾ��Ⱥ groupid userid
	 * 
	 * @return
	 */
	public void RemoveGroup() throws IOException {

		System.out.println("ɾ��Ⱥ!");
		// ��ѯ����Ⱥ
		groupchat = gdao.Findbyid(groupid);

		if (groupchat == null) {
			System.out.println("û�и�Ⱥ");
			util.Out().print("gnull");
			return;
		}

		// �ж��ǲ��ǽ�Ⱥ��
		if (groupchat.getUserid() != userid) {
			System.out.println("�㲻�ǽ�Ⱥ��");
			util.Out().print(false);
			return;
		}

		/**
		 * ɾ����ϵ
		 */
		// ��ѯ�����Ⱥ���û�
		lgroupMembers = gdao.FindGroupMembersbyid(groupid);
		System.out.println("Ⱥ����" + lgroupMembers.size() + "����");
		// ѭ��ɾ����
		for (int i = 0; i < lgroupMembers.size(); i++) {
			gdao.Remove(lgroupMembers.get(i));

		}
		/**
		 * ɾ�������¼
		 */
		// ��ѯ�����Ⱥ�������¼
		lcis = gdao.Cisid(groupid);
		System.out.println("Ⱥ����" + lcis.size() + "�����¼");
		// ѭ��ɾ����
		for (int i = 0; i < lcis.size(); i++) {
			gdao.Remove(lcis.get(i));

		}
		/**
		 * ɾ��Ⱥ���û�����
		 */
		// ��ѯ�����Ⱥ���û�����
		lprofile = gdao.FindProfilebyid(groupid);
		System.out.println("Ⱥ����" + lprofile.size() + "����");
		// ѭ��ɾ����
		for (int i = 0; i < lprofile.size(); i++) {
			gdao.Remove(lprofile.get(i));

		}

		System.out.println("ɾ���ɹ�!Ⱥͼ��ɾ���Ƿ�ɹ���"
				+ util.fileRemove(groupchat.getImg()));

		// ��ȡ ���� Ⱥid
		hxgroupid = groupchat.getGroupid();
		// ɾ�����Ⱥ
		gdao.Remove(groupchat);
		System.out.println("ɾ���ɹ�! ���� Ⱥid:" + hxgroupid);
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAreas() {
		return areas;
	}

	public void setAreas(String areas) {
		this.areas = areas;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getClassgroup() {
		return classgroup;
	}

	public void setClassgroup(int classgroup) {
		this.classgroup = classgroup;
	}

	public int getCondtion() {
		return condtion;
	}

	public void setCondtion(int condtion) {
		this.condtion = condtion;
	}

	public String getGclassintroduction() {
		return gclassintroduction;
	}

	public void setGclassintroduction(String gclassintroduction) {
		this.gclassintroduction = gclassintroduction;
	}

}
