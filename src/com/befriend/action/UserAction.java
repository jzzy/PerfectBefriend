package com.befriend.action;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.Case;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import com.befriend.dao.ApputilDAO;
import com.befriend.dao.GroupDAO;
import com.befriend.dao.RegistrationDAO;
import com.befriend.dao.UserDAO;
import com.befriend.entity.Admin;
import com.befriend.entity.Cis;
import com.befriend.entity.Password;
import com.befriend.entity.Profile;
import com.befriend.entity.Registrationsa;
import com.befriend.entity.Stas;
import com.befriend.entity.User;
import com.befriend.udp.UDPServer;
import com.befriend.util.OpeFunction;
import com.befriend.wechat.RefreshAccessToken;
import com.befriend.wechat.WechatKit;
import com.opensymphony.xwork2.Action;

public class UserAction {
	private User u = new User();
	public OpeFunction util;
	private ApputilDAO adao;// ApputilDAO
	private UserDAO userdao;
	private GroupDAO gdao;
	private RegistrationDAO regdao;

	List<User> us = new ArrayList<User>();
	HttpServletRequest request = ServletActionContext.getRequest();
	private String code;// ��֤��
	List<Cis> cis = new ArrayList<Cis>();// �����¼
	// String account = "cf_wcskdxyz";//�û��� cf_wcsk_jztd
	// String account = "cf_wcsk_jztd";// �û��� cf_wcsk_jztd
	// String pwd = "wcsk1212";// ���� wcsk1212
	// String postUrl =
	// "http://106.ihuyi.cn/webservice/sms.php?method=Submit";// ��ַ

	private String sex;// �ҵ��Ա�
	private String signature;// ����ǩ��
	private String childrensex;// �����Ա�
	private String newpassword;// ������

	private String childrenage;// ��������
	private String mac;
	private String phone;// �ֻ���
	private String username;// �û���
	private String password;// ����
	private String newpwd;// ����������
	private int id = -1;// �û�id
	private String school;// ����ѧУ
	private String stage;// �׶�
	private String time = util.getNowTime();// ʱ��
	private String nickname;// �ǳ�
	private String address;// ��ַ ʡ��
	private String os = "web";// ϵͳ
	private String addcity;// ��ַ �м�

	private HttpSession session = ServletActionContext.getRequest()
			.getSession();

	private int competence2 = -1;// �û�id competence �û�Ȩ�� 0-��ͨ�û�
									// 1-�м���̳����Ա
									// 2-ʡ����̳����Ա
	private int gag = -1;// 0������ 1 �� ����
	private int pageSize = 50;// ÿҳ��ʾ ����������
	private int currentPage = 1;// ���ǵڶ���ҳ

	private String timeq;// ��ѯ��ʼʱ��

	private String timeh;// ��ѯ����ʱ��
	private File file;// logoͼƬ
	private String fileFileName;// �ļ���
	private String fileContentType;// �ļ�����

	private String accnumno;//
	private int port = -1;//
	private String ip;// �û�ip
	private String province;// ��ȡǰ�˵� ʡ��
	private String city;// ��ȡǰ�˵� �м�
	private String age;// ����

	// �����û�ע��
	private String url = "https://a1.easemob.com/topLong/wcfriend/users";
	private Password pd = new Password();

	/**
	 * �鿴ͳ����Ϣ
	 * 
	 * @throws IOException
	 */
	public String ViewStatistics() throws IOException {

		String url = "http://127.0.0.1/PerfectBefriend/aStas";
		WechatKit.sendGet(url);
		List<Stas> ios = adao.StasTime("all", "ios");
		List<Stas> android = adao.StasTime("all", "android");
		List<Stas> web = adao.StasTime("all", "web");
		List<Stas> other = adao.StasTimeother("all");
		List<Stas> syn = adao.StasTime("all", "syn");
		request.setAttribute("ios", ios);
		request.setAttribute("android", android);
		request.setAttribute("web", web);
		request.setAttribute("other", other);
		request.setAttribute("syn", syn);
		return Action.SUCCESS;

	}

	/**
	 * sha1 ����
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public void sha1() throws NoSuchAlgorithmException, IOException {

		/**
		 * int[] a = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1 };
		 * System.out.println("����֮ǰ��"); // �������� for (int i = 0; i <
		 * a.length; i++) { System.out.print(a[i] + " "); } // ֱ�Ӳ������� for
		 * (int i = 1; i < a.length; i++) { // ������Ԫ�� int temp = a[i]; int j;
		 * 
		 * for (j = i - 1; j >= 0; j--) { // ������temp�������ƶ�һλ if (a[j] >
		 * temp) { a[j + 1] = a[j]; } else { break; } } a[j + 1] = temp;
		 * System.out.println("========="); for (int x = 0; x < a.length; x++) {
		 * if (x == a.length - 1) { System.out.println(a[x]); } else {
		 * System.out.print(a[x] + " "); }
		 * 
		 * } } System.out.println(); System.out.println("����֮��"); for (int i =
		 * 0; i < a.length; i++) { System.out.print(a[i] + " "); }
		 * 
		 * // ð������ for (int i = 0; i < a.length; i++) { for (int j = 0; j <
		 * a.length - i - 1; j++) { //
		 * ����-i��Ҫ��ÿ����һ�ζ�������i�������������ȥ�ˣ�û�б�Ҫ���滻�� if (a[j] >
		 * a[j + 1]) { int temp = a[j]; a[j] = a[j + 1]; a[j + 1] = temp; } } }
		 * System.out.println(); System.out.println("����֮��"); for (int i = 0;
		 * i < a.length; i++) { System.out.print(a[i] + " "); }
		 */

	}

	/**
	 * ��̨ע���ֻ���
	 * 
	 * @throws IOException
	 */
	public void userLogout() throws IOException {
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			util.Out().print("�㲻�ǹ���!");
			return;
		}
		if (admin.getLevel() != 1) {
			util.Out().print("�㲻�ǹ���!");
			return;
		}
		u = userdao.byid(id);
		if (u == null) {
			util.Out().print("û������û�!");
			return;
		}
		u.setPhone(null);
		userdao.update(u);
		util.Out().print("ע���ɹ�!");

	}

	/**
	 * һ��ע�� �û���
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void accnumnoczc() throws InterruptedException, IOException {
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			util.Out().print("�㲻�ǹ���!");
			return;
		}
		if (admin.getLevel() != 1) {
			util.Out().print("�㲻�ǹ���!");
			return;
		}

		us = userdao.getUserAll();
		for (int i = 0; i < us.size(); i++) {
			u = us.get(i);
			if (u == null) {
				System.out.println("�û�Ϊ�գ�");
				continue;

			}
			if (!util.isEmpty(u.getAccnumno())) {
				System.out.println("�û����˺��ˣ�:" + u.getAccnumno() + "��" + i
						+ "����");
				continue;
			}

			User macU = userdao.byIdMac();
			if (macU != null) {
				Integer ing = Integer.parseInt(macU.getAccnumno()) + 1;
				accnumno = ing.toString();
			} else {
				accnumno = "10000000";
			}
			System.out.println("����ID:" + accnumno);
			u.setAccnumno(accnumno);
			userdao.update(u);
			System.out.println("�����˺ųɹ����� " + i + " �����˺�Ϊ:" + accnumno);
			util.Out().print("ͬ�����!");
			// Thread.sleep(1000);
		}

	}

	/**
	 * ע�ỷ�� һ��ע��ȫ���û�
	 * 
	 * @throws JSONException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void huanxinzc() throws JSONException, InterruptedException,
			IOException {

		System.out.println("����huanxinzc");
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			util.Out().print("�㲻�ǹ���!");
			return;
		}
		if (admin.getLevel() != 1) {
			util.Out().print("�㲻�ǹ���!");
			return;
		}
		us = userdao.getUserAll();
		for (int i = 0; i < us.size(); i++) {
			if (us.get(i) == null) {
				continue;
			}
			Thread.sleep(40);
			JSONObject json = new JSONObject();
			json.put("username", us.get(i).getId()); // �û�id
			json.put("password", "123456"); // �û�����
			if (RefreshAccessToken.access_token == null) {
				util.Out().print("û�л�ȡ��access_token!");
				System.out.println("û�л�ȡ��access_token!");
				return;
			}
			String w = WechatKit.post(url, json,
					RefreshAccessToken.access_token);
			System.out.println("��" + (i + 1) + "���û�ע�� ����:" + w);
		}
		util.Out().print("ͬ�����!");

	}

	/**
	 * һ������
	 * 
	 * @throws IOException
	 */
	public void updateAllXiaxian() throws IOException {
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			util.Out().print("�㲻�ǹ���!");
			return;
		}
		if (admin.getLevel() != 1) {
			util.Out().print("�㲻�ǹ���!");
			return;
		}
		userdao.updateAllXiaxian();
		System.out.println("һ�����߳ɹ�!");
		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/GetUserAll");

	}

	/**
	 * ���ͷ���
	 * 
	 * @throws InterruptedException
	 */
	public void onlineUserSend() throws IOException, InterruptedException {
		System.out.println("id:" + id);
		u = userdao.byid(id);
		if (u != null) {
			cis = gdao.Cisuid(u.getId());
			System.out.println("�û���:" + u.getUsername() + cis.size()
					+ "��δ����Ϣ!" + "Online:" + u.getOnline());

			if (cis.size() == 0) {
				OpeFunction.Out().print(true);
				return;
			}

			for (int i = 0; i < cis.size(); i++) {
				if (cis.get(i).getOnline() == 1) {
					continue;
				}
				Profile profile = gdao.FindProfilebyid(cis.get(i).getGroupid(),
						cis.get(i).getSenduserid());
				User su = userdao.byid(cis.get(i).getSenduserid());

				String result = "{\"u\":" + util.ToJson(su) + ",\"cis\":"
						+ util.ToJson(cis.get(i)) + ",\"profile\":"
						+ util.ToJson(profile) + "}";

				String ip = u.getIp();

				port = u.getPort();

				if (ip != null && port > 0) {

					try {

						UDPServer.Send(result, ip, port);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}

			}

			util.Out().print(true);

		} else {// �ж����ӵ� �û��Ƿ����!
			util.Out().print(false);

		}// �ж����ӵ� �û��Ƿ����!

	}

	/**
	 * �����û����͵�udp ��ȡip port
	 * 
	 * @throws IOException
	 */
	public void uIpPort() throws IOException {

		System.out.println("ip:" + ip);
		System.out.println("port:" + port);

		u = userdao.byid(id);
		if (u != null) {

			u.setIp(ip);
			u.setPort(port);
			userdao.update(u);
			util.Out().print(true);
		} else {

			util.Out().print(false);
		}
		System.out.println("�޸ĳɹ�");
	}

	/**
	 * �����û�����״̬
	 * 
	 * @throws InterruptedException
	 */
	public void onlineStatus() throws IOException, InterruptedException {
		System.out.println("id:" + id);
		u = userdao.byid(id);
		if (u == null) {
			u = userdao.byUsernameAccnumnoPhone(username);
		}
		if (u != null) {

			System.out.println("");

			u.setOnline(0);
			userdao.update(u);
			System.out.println(id + "������!");
			util.Out().print(true);
			return;

			/**
			 * int uid = u.getId();
			 * 
			 * u.setOnline(1); userdao.update(u);
			 * 
			 * cis = gdao.Cisuid(uid); System.out.println("�û���:" +
			 * u.getUsername() + cis.size() + "��δ����Ϣ!" + "Online:" +
			 * u.getOnline());
			 * 
			 * Thread.sleep(5000); u = userdao.byid(uid); if (cis.size() == 0) {
			 * OpeFunction.Out().print(true); return; }
			 * 
			 * for (int i = 0; i < cis.size(); i++) {
			 * 
			 * Profile profile = gdao.FindProfilebyid(cis.get(i).getGroupid(),
			 * cis.get(i).getSenduserid());
			 * 
			 * User su = userdao.byid(cis.get(i).getSenduserid());
			 * 
			 * String result = "{\"u\":" + util.ToJson(su) + ",\"cis\":" +
			 * util.ToJson(cis.get(i)) + ",\"profile\":" + util.ToJson(profile)
			 * + "}";
			 * 
			 * String ip = u.getIp();
			 * 
			 * port = u.getPort();
			 * 
			 * if (ip != null && port > 0) {
			 * 
			 * UDPServer.Send(result, ip, port); }
			 * 
			 * }
			 */

		} else {
			util.Out().print(false);

		}

	}

	/**
	 * web�û� ע�� ����session
	 * 
	 * @throws IOException
	 */
	public void websessionrom() throws IOException {
		// ����session
		session.removeAttribute("u");
		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/SimulationApp/login.html");
	}

	/**
	 * web�û� �޸�����
	 * 
	 * @throws IOException
	 */
	public void webpassModification() throws IOException {

		User u = (User) session.getAttribute("u");
		if (u == null) {

			System.out.println("�����µ���!");
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/SimulationApp/login.html");

			return;
		}
		Password pd = userdao.login(u.getId(), password);
		if (pd != null) {
			// ����������ж�
			String reg = "[A-Za-z0-9_]{6,18}";

			if (newpwd.matches(reg)) {
				pd.setPassword(newpwd);
				System.out.println("�޸�������");
				util.Out().print("�����޸ĳɹ�!" + newpwd);
				userdao.update(pd);
				session.removeAttribute("u");
				((HttpServletResponse) util.response()).sendRedirect(request
						.getContextPath() + "/SimulationApp/login.html");
				return;
			} else {
				util.Out().print("û�л�ȡ��������!" + newpwd);
				return;
			}

		} else {
			util.Out().print("ԭ�������!");
			return;
		}
	}

	/**
	 * web �û� �޸� & ���� ��Ϣ
	 * 
	 * @throws IOException
	 */
	public void webModification() throws IOException {
		try {

			User u = (User) session.getAttribute("u");
			if (u == null) {

				System.out.println("�����µ���!");
				((HttpServletResponse) util.response()).sendRedirect(request
						.getContextPath() + "/SimulationApp/login.html");

				return;
			}

			u = userdao.byid(u.getId());
			System.out.println("����webModification");
			System.out.println("�û�����" + username);
			System.out.println("��ַʡ��" + province);
			System.out.println("��ַ�м�" + city);
			System.out.println("���ӽ׶�" + stage);
			System.out.println("�ֻ���" + phone);
			System.out.println("ѧУ��" + school);
			System.out.println("������" + password);
			System.out.println("�ǳ���" + nickname);

			if (u == null) {

				System.out.println("�����µ���!");
				((HttpServletResponse) util.response()).sendRedirect(request
						.getContextPath() + "/SimulationApp/login.html");

				return;
			}
			String path = "";
			if (u.getImg() != null) {
				path = u.getImg();
			}
			if (file != null) {

				util.fileRemove(path);
				path = "/IMG/Userimg/" + u.getId();
				String pah = util.ufileToServer(path, file, "jpg");
				u.setImg(pah);

			}
			if (nickname != null) {
				u.setNickname(nickname);
				System.out.println("�޸����ǳ�");
			}
			if (phone != null) {
				// u.setPhone(phone);
				System.out.println("�޸����ֻ���");
			}
			// =0 Ϊ��ͨ�û�������ʱ�޸ĵ�ַ ����Ա��Ҫ��ȡ������Ա �ڽ����޸�

			if (u.getCompetence() == 0) {
				if (city != null && !city.equals("������") && city != "") {
					u.setAddcity(city);
					System.out.println("�޸��˵�ַ�м�");
				} else {

					Object Ocity = session.getAttribute("city");
					System.err.print("session�е��м�Ϊ" + Ocity);
					if (Ocity != null) {
						city = Ocity.toString();
						u.setAddcity(city);
					}

				}
				if (province != null && !province.equals("ʡ����")
						&& province != "") {
					u.setAddress(province);
					System.out.println("�޸��˵�ַʡ��");
				} else {

					Object Oprovince = session.getAttribute("province");
					if (Oprovince != null) {
						province = Oprovince.toString();
						u.setAddress(province);
					}

				}
			} else {
				System.out.println(u.getUsername() + "�ǹ���Ա!");
			}

			// ��֤�û���
			String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";
			System.out.println(username);
			System.out.println(password);
			if (username != null) {
				if (!username.matches(reg)) {
					System.out.println("�û�����ʽ���ԣ�");
					util.Out().print("�û�����ʽ���ԣ�");
					return;
				}
			}
			if (u.getUsername() == null) {
				System.out.println("�޸����û���!");
				u.setUsername(username);
			}
			if (school != null) {
				u.setSchool(school);
				System.out.println("�޸��˺���ѧУ");
			}

			if (stage != null) {
				u.setStage(stage);
				System.out.println("�޸��˺��ӽ׶�");
			}

			userdao.update(u);
			session.setAttribute("u", u);

			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath()
					+ "/SimulationApp/personal_information.jsp");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * web �û�ע�� ֻ��Ҫ �ֻ��� �û��� ���� ����
	 * 
	 * @throws IOException
	 * @throws JSONException
	 */
	public String webRegistration() throws IOException, JSONException {

		phone = request.getParameter("phone");
		password = request.getParameter("password");
		username = request.getParameter("username");

		if (password == null) {
			System.out.println("����Ϊ��");
			util.Out().print("����Ϊ��");
			return null;
		}
		if (phone == null) {
			System.out.println("�ֻ���Ϊ��");
			util.Out().print("�ֻ���Ϊ��");
			return null;
		}
		// ��֤�û���
		String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";
		System.out.println(username);
		System.out.println(password);
		if (username != null) {
			if (!username.matches(reg)) {
				System.out.println("�û�����ʽ���ԣ�");
				util.Out().print("�û�����ʽ���ԣ�");
				return null;
			}

		}
		// ��֤����
		reg = "[A-Za-z0-9_]{6,18}";
		if (!password.matches(reg)) {
			System.out.println("�����ʽ���ԣ�");
			util.Out().print("�����ʽ���ԣ�");
			return null;
		}
		// ��֤�ֻ���
		String regp = "[0-9]{11}";
		if (!phone.matches(regp)) {
			System.out.println("�����ʽ���ԣ�");
			util.Out().print("�����ʽ���ԣ�");
			return null;
		}
		System.out.println("����web�û�ע���ֻ���Ϊ:" + phone);
		System.out.println("����ʱ��" + util.getNowTime());
		System.out.println("�ֻ���" + phone);
		System.out.println("�û���" + username);

		// �ж��ֻ����Ƿ�ע���
		if (userdao.byUsernameAccnumnoPhone(phone) != null) {
			System.out.println("���ֻ���  �Ѿ�ע���");
			request.setAttribute("ph", phone);
			return Action.ERROR;
		}

		u.setAccnumno(accnumno);
		if (username != null) {
			u.setUsername(username);
		}
		u.setPhone(phone);
		u.setStage("δ��д");
		u.setAddress("����");
		u.setAddcity("������");
		u.setNickname(username);// û�����ù���ʾ�û���
		u.setSchool("δ��д");
		u.setTime(util.getNowTime());
		u.setCompetence(0);// ��ͨ�û�
		u.setGag(0);// ���Դ�����̳
		if (userdao.byUsernameAccnumnoPhone(username) != null) {
			System.out.println("���û���  �Ѿ�ע���");
			request.setAttribute("ue", username);
			return Action.ERROR;
		}
		User macU = userdao.byIdMac();
		if (macU != null) {
			Integer ing = Integer.parseInt(macU.getAccnumno()) + 1;
			accnumno = ing.toString();
		} else {
			accnumno = "10000000";
		}
		System.out.println("����ID:" + accnumno);
		u.setAccnumno(accnumno);
		userdao.save(u);
		u = userdao.byUsernameAccnumnoPhone(accnumno);
		if (u == null) {
			util.Out().print("�쳣������ע��");
			return null;
		}
		pd.setUid(u.getId());
		pd.setPassword(password);
		userdao.save(pd);
		session.setAttribute("u", u);
		System.out
				.println("ע��ɹ�" + "username:" + username + ",pw:" + password);

		// ע�ỷ��
		u = userdao.byUsernameAccnumnoPhone(phone);
		if (u != null) {
			// ��װ��json
			// ��ȡtoken��
			JSONObject json = new JSONObject();
			json.put("username", u.getId());
			// �û�id
			json.put("password", "123456"); // �û�����
			String w = WechatKit.post(url, json,
					RefreshAccessToken.access_token);
		}

		return Action.SUCCESS;

	}

	/**
	 * ʡ������ ģ����ѯuser��Ϣ
	 */
	public String getUsermhh() throws IOException {

		User ue = (User) session.getAttribute("useradmin");
		Map session = (Map) util.getsession();// ���� map

		if (ue == null) {
			util.Out().print("�û�����Ϊ��");
			return null;
		}
		if (username.length() >= 1) {
			us = userdao.likeusername(username, ue.getAddress());
		}

		System.out.println("username:" + username);

		request.setAttribute("usaha", us);

		return Action.SUCCESS;

	}

	/**
	 * ��user����Ȩ��
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void SetQU() throws IOException, InterruptedException {

		User ue = (User) session.getAttribute("useradmin");
		System.out.println("ʡ������ �����û�");
		System.out.println("id��-" + id);
		u = userdao.byid(id);
		if (u == null || ue == null) {
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/Judge/No.html");
			return;
		}
		if (u.getId().equals(ue.getId())) {
			// util.Out().print("�Ƿ�����");
			System.out.println("�Լ������Ը����Լ���Ȩ��!");
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/Judge/No.html");
			return;
		}
		if (!u.getAddress().equals(ue.getAddress()) || u.getAddress() == null) {
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/Judge/No.html");
			return;
		}
		System.out.println("competence2��-" + competence2);
		if (competence2 == 2) {

			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/Judge/No.html");

			return;
		}
		if (competence2 == 3) {
			// ȡ����Ȩ
			u.setCompetence(0);
			userdao.update(u);
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/getUadmin");
			return;
		}

		if (competence2 == 1) {

			if (u.getAddcity() == null) {
				((HttpServletResponse) util.response()).sendRedirect(request
						.getContextPath() + "/Judge/No.html");

				return;
			}
			u.setCompetence(competence2);

			userdao.update(u);
			util.Out().print("�ɹ������м���̳����ԱȨ��");
			// �ȴ�3s
			// Thread.sleep(3000);
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/getUsera");
			return;
		}

	}

	/**
	 * ��ѯuser��Ϣ
	 */
	public String getUserine() throws IOException {
		User ue = (User) session.getAttribute("useradmin");

		if (ue == null) {
			util.Out().print("�����µ���");
			return null;
		}

		u = userdao.byUsernameAccnumnoPhone(username);
		if (!u.getAddress().equals(ue.getAddress())) {
			util.Out().print("û��Ȩ��");
			return null;
		}
		System.out.println("��ѯuser��Ϣusername:" + username);

		request.setAttribute("u", u);
		return Action.SUCCESS;

	}

	/**
	 * ��ȡuser��Ϣ
	 */
	public String getUsera() throws IOException {
		u = (User) session.getAttribute("useradmin");

		if (u == null) {
			util.Out().print("ϵͳ�쳣");
			return null;
		}

		competence2 = userdao.getUser(u.getAddress()).size();// ��ȡ
																// ���ж���������

		if (competence2 % pageSize == 0) {
			competence2 = competence2 / pageSize;// ��ȡ ���ж���ҳ
		} else {
			competence2 = competence2 / pageSize + 1;// ��ȡ ���ж���ҳ
		}
		if (currentPage >= competence2) {
			currentPage = competence2;
		}
		if (currentPage < 1) {
			currentPage = 1;
		}

		us = userdao.getUser(pageSize, currentPage, u.getAddress());
		request.setAttribute("us", us);
		request.setAttribute("competence2", competence2);
		request.setAttribute("currentPage", currentPage);

		return Action.SUCCESS;

	}

	/**
	 * ��ȡuser����Ա��Ϣ
	 */
	public String getUadmin() throws IOException {
		u = (User) session.getAttribute("useradmin");

		if (u == null) {
			util.Out().print("ϵͳ�쳣");
			return null;
		}

		competence2 = userdao.getUseradmin(u.getAddress()).size();// ��ȡ
																	// ���ж���������

		if (competence2 % pageSize == 0) {
			competence2 = competence2 / pageSize;// ��ȡ ���ж���ҳ
		} else {
			competence2 = competence2 / pageSize + 1;// ��ȡ ���ж���ҳ
		}
		if (currentPage >= competence2) {
			currentPage = competence2;
		}
		if (currentPage < 1) {
			currentPage = 1;
		}

		us = userdao.getUseradmin(pageSize, currentPage, u.getAddress());

		request.setAttribute("usa", us);
		request.setAttribute("competence2", competence2);
		request.setAttribute("currentPage", currentPage);
		return Action.SUCCESS;

	}

	/**
	 * ����ʱ���ѯ ���� �û�����
	 * 
	 * @throws IOException
	 */
	public String GetUsertimeares() throws IOException {

		System.out.println("����GetUsertimeares�������û���Ϊ" + username);
		System.out.println("��ʼʱ��" + timeq);
		System.out.println("��ֹʱ��" + timeh);
		System.out.println("����" + addcity);

		u = (User) session.getAttribute("useradmin");
		if (u != null) {
			System.out.println("������" + u.getAddress() + addcity);

			if (u.getCompetence() == 1) {
				System.out.println("�м�");

				us = userdao.getUserAll(u.getAddress(), u.getAddcity(), timeq,
						timeh);

			}

			else if (u.getCompetence() == 2) {

				System.out.println("ʡ��");

				if (!addcity.equals("nu")) {

					us = userdao.getUserAll(u.getAddress(), addcity, timeq,
							timeh);
				} else {

					us = userdao.getUserAll(u.getAddress(), timeq, timeh);

				}

			} else {

				System.out.println("�����µ���");
				util.Out().print("�����µ���");
				return null;
			}
			System.out.println("��" + us.size() + "���û�");

			request.setAttribute("GetUsertimeus", us);
			request.setAttribute("timeq", timeq);
			request.setAttribute("timeh", timeh);
		}

		return Action.SUCCESS;
	}

	/**
	 * ʡ�����м� �û�����Ա ��ѯ�û���Ϣ
	 */

	public String RegionalAdministrator() throws IOException {
		try {

			System.out.println(util.getNumTime(-1));

			System.out.print("username-" + username + "-password-" + password);

			u = userdao.byUsernameAccnumnoPhone(username);
			if (u == null) {
				util.Out().print("�˺Ż��������");

				return null;
			}
			Password pd = userdao.login(u.getId(), password);
			if (pd == null) {
				System.out.println("���벻��!");
				util.Out().print("���벻��!");
				return null;
			}

			int cpe = 0;
			cpe = u.getCompetence();

			if (cpe == 0) {
				util.Out().print("������ͨ�û�������������Ȩ��");

				return null;
			}

			List<User> ul = userdao.getUserAll(u.getAddress());
			List a = new ArrayList();
			Boolean bool = true;
			for (int i = 0; i < ul.size(); i++) {

				for (int b = 0; b < a.size(); b++) {
					if (a.get(b).equals(ul.get(i).getAddcity())) {
						bool = false;
					}
				}

				if (bool) {
					a.add(ul.get(i).getAddcity());
				}
				//
				bool = true;

			}

			for (int c = 0; c < a.size(); c++) {

				System.out.println("ʡ�õ���Ϊ" + c + u.getAddress() + a.get(c));
			}

			// ����
			String time0 = util.getNumTime(-1);
			// ����ǰ
			String time7 = util.getNumTime(7);
			// һ����ǰ
			String time30 = util.getNumTime(30);
			// һ��ǰ
			String time365 = util.getNumTime(365);
			System.out.println("����" + time0);
			System.out.println("7��ǰ" + time7);
			System.out.println("30��ǰ" + time30);
			System.out.println("365��ǰ" + time365);

			for (int c = 0; c < a.size(); c++) {
				System.out.println("a" + a.get(c).toString() + c);
				// ��ѯ30������
				int i30 = userdao.getUserAll(u.getAddress(),
						a.get(c).toString(), time30, time0).size();
				// 7
				int i7 = userdao.getUserAll(u.getAddress(),
						a.get(c).toString(), time7, time0).size();
				// 365
				int i365 = userdao.getUserAll(u.getAddress(),
						a.get(c).toString(), time365, time0).size();

				// ����
				int i0 = userdao.getUserAll(u.getAddress(),
						a.get(c).toString(), util.getNumTime(0), time0).size();
				// ȫ��
				int all = userdao.getUserAll(u.getAddress(),
						a.get(c).toString()).size();
				System.out.println(u.getAddress() + a.get(c).toString() + i0
						+ i7 + i30 + i365 + all);
				// ��ѯ�Ƿ���
				System.out.println("ˢ�µ���Ϊ" + u.getAddress()
						+ a.get(c).toString());
				Registrationsa rg = new Registrationsa();

				rg = regdao.addressty(u.getAddress(), a.get(c).toString());

				if (rg == null) {
					Registrationsa reg = new Registrationsa();
					reg.setAddress(u.getAddress());
					reg.setAddcity(a.get(c).toString());
					reg.setNote7(i7);
					reg.setNote30(i30);
					reg.setNote365(i365);
					reg.setNote(i0);
					reg.setNoteall(all);
					reg.setTime(time);
					regdao.save(reg);
				} else {
					rg.setNote7(i7);
					rg.setNote30(i30);
					rg.setNote365(i365);
					rg.setNote(i0);
					rg.setNoteall(all);
					rg.setTime(time);
					regdao.update(rg);
				}

			}
			session.setAttribute("useradmin", u);
			if (cpe == 1) {

				Registrationsa rga = new Registrationsa();
				System.out.println("�鿴�ĳ�����" + u.getAddress()
						+ u.getAddcity());

				rga = regdao.addressty(u.getAddress(), u.getAddcity());
				System.out.println("rg" + rga.getAddress());
				session.setAttribute("rg", rga);
				session.setAttribute("num", "1");
				return Action.SUCCESS;

			}
			if (cpe == 2) {

				List<Registrationsa> lg = regdao.address(u.getAddress());
				session.setAttribute("lg", lg);
				return Action.SUCCESS;
			}
			util.Out().print("û��Ȩ��!");
			return null;
		} catch (Exception e) {
			util.Out().print("�쳣" + e.getMessage());
		}
		return null;

	}

	/**
	 * ���� ��ֹ�û�������̳
	 * 
	 * @throws IOException
	 */
	public void Gag() throws IOException {

		u = userdao.byUsernameAccnumnoPhone(username);
		System.out.println("gag==" + gag);
		if (gag == 1) {
			u.setGag(1);
			util.Out().print("���û�" + username + "���Գɹ�");
		} else {
			u.setGag(0);
			util.Out().print("���û�" + username + "ȡ�����Գɹ�");
		}
		userdao.update(u);
	}

	/**
	 * ��ѯuser��Ϣ
	 */
	public String getUserin() throws IOException {

		u = userdao.byid(id);
		if (u == null) {
			util.Out().print("û������û�");
			return null;

		}
		System.out.println("��ѯuser��Ϣuserid:" + id);

		request.setAttribute("u", u);
		return Action.SUCCESS;

	}

	/**
	 * ģ����ѯuser��Ϣ
	 */
	public String getUsermh() throws IOException {

		if (username.length() >= 1) {
			us = userdao.likeusername(username);
		}
		List<Password> pl = new ArrayList<Password>();
		for (int i = 0; i < us.size(); i++) {
			u = us.get(i);
			if (u != null) {
				pd = userdao.select(u.getId());
				pl.add(pd);
			} else {
				pl.add(null);

			}
		}

		System.out.println("username:" + username);

		request.setAttribute("usaha", us);
		request.setAttribute("pl", pl);
		return Action.SUCCESS;

	}

	/**
	 * ��ȡuser����Ա��Ϣ
	 */
	public String getUseradmin() throws IOException {

		competence2 = userdao.getUseradmin().size();// ��ȡ ���ж���������

		if (competence2 % pageSize == 0) {
			competence2 = competence2 / pageSize;// ��ȡ ���ж���ҳ
		} else {
			competence2 = competence2 / pageSize + 1;// ��ȡ ���ж���ҳ
		}
		if (currentPage >= competence2) {
			currentPage = competence2;
		}
		if (currentPage < 1) {
			currentPage = 1;
		}

		us = userdao.getUseradmin(pageSize, currentPage);
		request.setAttribute("usa", us);
		request.setAttribute("competence2", competence2);
		request.setAttribute("currentPage", currentPage);

		return Action.SUCCESS;

	}

	/**
	 * ��user����Ȩ��
	 * 
	 * @throws IOException
	 */
	public void SetQ() throws IOException {

		System.out.println("id��-" + id);
		u = userdao.byid(id);

		System.out.println("competence2��-" + competence2);
		if (competence2 == 3) {
			util.Out().print("�ɹ�������̳����ԱȨ��");

			u.setCompetence(0);
			userdao.update(u);
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/getUseradmin");
			return;
		}
		if (competence2 == 2) {
			if (u.getAddress() == null) {
				util.Out().print("���û�ʡ��Ϊ�գ�");
				return;
			}
			u.setCompetence(competence2);
			util.Out().print("�ɹ�����ʡ����̳����ԱȨ��");

			userdao.update(u);
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/getUser");
			return;
		}
		if (competence2 == 1) {
			if (u.getAddcity() == null) {
				util.Out().print("���û��м�Ϊ��!");
				return;
			}
			u.setCompetence(competence2);
			util.Out().print("�ɹ������м���̳����ԱȨ��");

			userdao.update(u);
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/getUser");
			return;
		}
		if (competence2 == 4) {

			u.setCompetence(competence2);
			util.Out().print("�ɹ��������");

			userdao.update(u);
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/getUser");
			return;
		}

	}

	/**
	 * ��ȡuser��Ϣ
	 */
	public String getUser() throws IOException {
		/**
		 * competence2 = userdao.getUser().size();// ��ȡ ���ж���������
		 * 
		 * if (competence2 % pageSize == 0) { competence2 = competence2 /
		 * pageSize;// ��ȡ ���ж���ҳ } else { competence2 = competence2 /
		 * pageSize + 1;// ��ȡ ���ж���ҳ } if (currentPage >= competence2) {
		 * currentPage = competence2; }
		 */
		if (currentPage < 1) {
			currentPage = 1;
		}

		us = userdao.getUser(pageSize, currentPage);
		request.setAttribute("us", us);
		// request.setAttribute("competence2", competence2);
		request.setAttribute("currentPage", currentPage);

		return Action.SUCCESS;

	}

	/**
	 * �û���̳����Ա������ ��֤
	 * 
	 * @throws IOException
	 */

	public void AdminLogin() throws IOException {
		try {
			request.setAttribute("luntan", "UserAdmin.jsp");

			System.out.print("username-" + username + "-password-" + password);

			u = userdao.byUsernameAccnumnoPhone(username);
			if (u == null) {
				util.Out().print("�˺Ż��������");

				return;
			}
			Password pd = userdao.login(u.getId(), password);
			if (pd == null) {
				System.out.println("���벻��!");
				util.Out().print(false);
				return;
			}

			int cpe = 0;
			cpe = u.getCompetence();

			if (cpe == 0) {
				util.Out().print("������ͨ�û�������������Ȩ��");

				return;
			}
			session.setAttribute("useradmin", u);

			if (cpe == 1) {

				session.setAttribute("home", request.getContextPath()
						+ "/ForumLookuser");
				((HttpServletResponse) util.response()).sendRedirect(request
						.getContextPath() + "/ForumLookuser");
				return;
			}

			if (cpe == 2) {

				session.setAttribute("home", request.getContextPath()
						+ "/ForumLookuser");
				((HttpServletResponse) util.response()).sendRedirect(request
						.getContextPath() + "/ForumLookuser");
				return;
			}

			if (cpe == 4) {

				session.setAttribute("home", request.getContextPath()
						+ "/ForumLookuser");

				((HttpServletResponse) util.response()).sendRedirect(request
						.getContextPath() + "/ForumLookuser");
				return;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * �û����� ��֤
	 * 
	 * @throws IOException
	 */
	public void Login() throws IOException {
		try {

			System.out.println("����Login");

			System.out.println("�û���" + username);
			System.out.println("����" + password);

			u = userdao.byUsernameAccnumnoPhone(username);
			if (u == null) {
				System.out.println("�˻�Ϊ��");
				util.Out().print("null");
				return;
			}
			Password pd = userdao.login(u.getId(), password);
			if (pd == null) {
				System.out.println("���벻��!");
				util.Out().print(false);
				return;
			}

			System.out.println("����ɹ���");

			int ut = u.getLoginnum();
			if (ut > 0) {
				ut = ++ut;
				u.setLoginnum(ut);
			} else {
				u.setLoginnum(1);
			}
			u.setOs(os);
			u.setFinaltime(time);
			u.setOnline(1);
			userdao.update(u);
			u.setPassword(pd.getPassword());
			util.Out().print(util.ToJson(u));
			// ���������û�����
			time = util.getNumTime(0);
			String url = "";
			if (province != null) {
				url = "http://127.0.0.1/PerfectBefriend/aStas?os=" + os
						+ "&province=" + province;
			} else {
				url = "http://127.0.0.1/PerfectBefriend/aStas?os=" + os;
			}
			WechatKit.sendGet(url);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * �û����� ��֤
	 * 
	 * @throws IOException
	 */
	public void newLogin() throws IOException {
		try {

			System.out.println("����newLogin");

			System.out.println("�û���" + username);
			System.out.println("����" + password);

			u = userdao.byUsernameAccnumnoPhone(username);
			if (u == null) {
				System.out.println("�˻�Ϊ��");
				util.Out().print("null");
				return;
			}
			Password pd = userdao.login(u.getId(), password);
			if (pd == null) {
				System.out.println("���벻��!");
				util.Out().print("false");
				return;
			}

			System.out.println("����ɹ���");

			int ut = u.getLoginnum();
			if (ut > 0) {
				ut = ++ut;
				u.setLoginnum(ut);
			} else {
				u.setLoginnum(1);
			}

			u.setFinaltime(time);

			userdao.update(u);
			String result = "{\"user\":" + util.ToJson(u) + ",\"pwd\":"
					+ util.ToJson(pd) + "}";
			util.Out().print(result);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * syn �û�����
	 * 
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public void synLogin() throws IOException, NoSuchAlgorithmException {

		System.out.println("����synLogin");

		System.out.println("�û���" + username);
		System.out.println("����" + password);
		os = "web";
		u = userdao.byUsernameAccnumnoPhone(username);
		if (u == null) {
			System.out.println("�˻�Ϊ��");
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/webNewsA10");
			return;
		}
		Password pd = userdao.login(u.getId(), password);
		if (pd == null) {
			System.out.println("���벻��!");
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/webNewsA10");
			return;
		}

		if (u != null) {
			System.out.println("ok");

			int ut = u.getLoginnum();
			if (ut > 0) {
				ut = ++ut;
				u.setLoginnum(ut);
			} else {
				u.setLoginnum(1);
			}
			u.setOnline(1);
			u.setFinaltime(time);
			u.setIp(request.getRemoteAddr());
			u.setOs("syn");

			userdao.update(u);
			// u.setPassword(pd.getPassword());
			// ���������û�����
			time = util.getNumTime(0);

			session.setAttribute("u", u);

			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/webNewsA10");
			String url = "";
			if (u.getAddress() != null) {
				url = "http://127.0.0.1/PerfectBefriend/aStas?os=" + os
						+ "&province=" + u.getAddress();
			} else {
				url = "http://127.0.0.1/PerfectBefriend/aStas?os=" + os;
			}
			WechatKit.sendGet(url);
		}

	}

	/**
	 * WEB �û����� ��֤
	 * 
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public String webLogin() throws IOException, NoSuchAlgorithmException {

		System.out.println("����webLogin");

		System.out.println("�û���" + username);
		System.out.println("����" + password);

		u = userdao.byUsernameAccnumnoPhone(username);
		if (u == null) {
			System.out.println("�˻�Ϊ��");
			util.Out().print("�˻�Ϊ��");
			return null;
		}
		Password pd = userdao.login(u.getId(), password);
		if (pd == null) {
			System.out.println("���벻��!");
			util.Out().print("���벻��");
			return null;
		}

		if (u != null) {
			System.out.println("ok");

			int ut = u.getLoginnum();
			if (ut > 0) {
				ut = ++ut;
				u.setLoginnum(ut);
			} else {
				u.setLoginnum(1);
			}
			u.setOnline(1);
			u.setFinaltime(time);
			u.setIp(request.getRemoteAddr());
			os = "web";
			u.setOs(os);
			userdao.update(u);
			// u.setPassword(pd.getPassword());
			// ���������û�����
			time = util.getNumTime(0);

			session.setAttribute("u", u);

			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/webNewsA10");
			System.out.println("ת������");
			String url = "";
			if (u.getAddress() != null) {
				url = "http://127.0.0.1/PerfectBefriend/aStas?os=" + os
						+ "&province=" + u.getAddress();
			} else {
				url = "http://127.0.0.1/PerfectBefriend/aStas?os=" + os;
			}
			WechatKit.sendGet(url);
		}

		return null;

	}

	/**
	 * ͨ���ֻ����һ������һ�����
	 */
	public void appSendCodePasswrod() throws IOException {
		try {
			System.out.println("����appSendCodePasswrod");
			//
			String regp = "[0-9]{11}";
			if (phone == null) {
				System.out.println("�ֻ��ſգ�");
				util.Out().print("null");
				return;
			}
			if (!phone.matches(regp)) {
				System.out.println("�ֻ��Ÿ�ʽ���ԣ�");
				util.Out().print("null");
				return;
			}

			System.out.println("����appSendCode���ֻ���Ϊ:" + phone);

			System.out.println("����ʱ��" + util.getNowTime());

			System.out.println("�ֻ���" + phone);
			if (userdao.byUsernameAccnumnoPhone(phone) == null) {
				System.out.println("���ֻ���û��ע���");

				util.Out().print(false);

				return;
			}

			int num = (int) ((Math.random() * 9 + 1) * 100000);

			session.setAttribute("app_code", String.valueOf(num));
			session.setMaxInactiveInterval(90);
			System.out.println("1��ȷ��֤��Ϊ" + num);

			String content = new String("������֤���ǣ�" + num
					+ "���벻Ҫ����֤��й¶�������ˡ���Ǳ��˲������ɲ�����ᣡ");
			util.setphone(phone, content);
			util.Out().print(true);

		} catch (Exception e) {
			// TODO: handle exception������
			e.printStackTrace();
		}

	}

	/**
	 * �� �ֻ��� ������֤ phone
	 * 
	 * @throws IOException
	 */
	public void appSendCode() throws IOException {
		try {
			System.out.println("����appSendCode");
			System.out.println("phone:" + phone);
			//
			String regp = "[0-9]{11}";

			if (phone == null) {

				util.Out().print("null");
				return;
			}

			if (!phone.matches(regp)) {
				System.out.println("�ֻ��Ÿ�ʽ���ԣ�");
				util.Out().print("null");
				return;
			}

			System.out.println("����appSendCode���ֻ���Ϊ:" + phone);

			System.out.println("����ʱ��" + util.getNowTime());

			System.out.println("�ֻ���" + phone);
			if (userdao.byUsernameAccnumnoPhone(phone) != null) {
				System.out.println("���ֻ���  �Ѿ�ע���");

				util.Out().print(false);

				return;
			}

			int num = (int) ((Math.random() * 9 + 1) * 100000);

			session.setAttribute("app_code", String.valueOf(num));
			session.setMaxInactiveInterval(90);
			System.out.println("1��ȷ��֤��Ϊ" + num);

			String content = new String("������֤���ǣ�" + num
					+ "���벻Ҫ����֤��й¶�������ˡ���Ǳ��˲������ɲ�����ᣡ");
			util.setphone(phone, content);
			util.Out().print(true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * �ж���֤�� �Ƿ���ȷ
	 * 
	 * @throws IOException
	 */
	public void appCheckCode() throws IOException {
		try {

			System.out.println("�ж�ʱ��" + util.getNowTime());

			String codes = (String) session.getAttribute("app_code");

			System.out.println("session��!��ȷ��֤��Ϊ" + codes);
			System.out.println("�û��������֤��codeΪ" + code);

			if (codes == null) {
				System.out.println("session�ѹ���");
				util.Out().print("null");
				return;
			}
			if (codes.equalsIgnoreCase(code)) {
				System.out.println("��֤��" + code + "��ȷ");
				util.Out().print(true);
				session.invalidate();
			} else {
				System.out.println("��֤��" + code + "����");
				util.Out().print(false);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * ���û�ע�� username password phone ������Ϊnull
	 * 
	 * @throws IOException
	 */
	public void save() throws IOException {
		try {
			// ��֤�û���
			String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";

			if (username != null) {
				if (!username.matches(reg)) {
					System.out.println("�û�����ʽ���ԣ�");
					util.Out().print(false);
					return;
				}
			}

			if (password == null) {
				util.Out().print(false);
				return;

			}
			// ��֤����
			reg = "[A-Za-z0-9_]{6,18}";
			if (!password.matches(reg)) {
				System.out.println("�����ʽ���ԣ�");
				util.Out().print(false);
				return;
			}

			if (phone == null) {
				util.Out().print(false);
				return;
			}
			String regp = "[0-9]{11}";
			if (!phone.matches(regp)) {
				System.out.println("�ֻ��Ÿ�ʽ���ԣ�");
				util.Out().print(false);
				return;
			}

			System.out.println("�û���:" + username);
			System.out.println("����:" + password);
			System.out.println("�û���:" + accnumno);
			if (nickname != null) {
				u.setNickname(nickname);
			} else {
				u.setNickname("δ����");
			}
			if (username != null) {
				u.setUsername(username);
				u.setNickname(username);// û�����ù���ʾ�û���
			}

			u.setAccnumno(accnumno);
			u.setPhone(phone);
			u.setStage("δ��д");
			if (address == null) {
				u.setAddress("����");
				u.setAddcity("������");
			} else {
				u.setAddress(address);
				u.setAddcity("δ����");
			}

			u.setFinaltime(time);
			u.setSchool("δ��д");
			u.setOs(os);
			u.setOnline(1);
			u.setTime(time);
			u.setCompetence(0);// ��ͨ�û�
			u.setGag(0);// ���Դ�����̳
			if (userdao.byUsernameAccnumnoPhone(phone) != null) {
				util.Out().print(false);
				return;

			}
			if (userdao.byUsernameAccnumnoPhone(username) != null) {
				util.Out().print(false);
				return;

			}
			User macU = userdao.byIdMac();
			if (macU != null) {
				Integer ing = Integer.parseInt(macU.getAccnumno()) + 1;
				accnumno = ing.toString();
			} else {
				accnumno = "10000000";
			}
			System.out.println("����ID:" + accnumno);
			u.setAccnumno(accnumno);
			userdao.save(u);
			System.out.println("ע��ɹ� phone" + phone + "accnumno:" + accnumno
					+ ",pw:" + password);

			// ע�ỷ��
			u = userdao.byUsernameAccnumnoPhone(phone);
			if (u != null) {
				if (file != null) {

					String path = "/IMG/Userimg/" + u.getId();
					String pah = util.ufileToServer(path, file, "jpg");
					u.setImg(pah);
					userdao.update(u);
				} else {
					System.out.println("û�л�ȡ��ͷ��!");
				}
				pd.setUid(u.getId());
				pd.setPassword(password);
				userdao.save(pd);
				JSONObject json = new JSONObject();
				json.put("username", u.getId());
				// �û�id
				json.put("password", "123456");
				// �û�����
				String w = WechatKit.post(url, json,
						RefreshAccessToken.access_token);
				System.out.println(w);
				u.setPassword(password);

				util.Out().print(util.ToJson(u));
				String url = "http://127.0.0.1/PerfectBefriend/aStas?province="
						+ address + "&os=" + os;
				WechatKit.sendGet(url);
				return;
			} else {
				util.Out().print("null");
				return;
			}

		} catch (Exception e) {
		}
	}

	/**
	 * ���û�ע�� ͨ��mac ����Ϊ����ID
	 * 
	 * @throws IOException
	 * @throws JSONException
	 */
	public void oneSave() throws IOException, JSONException {

		u = userdao.byMac(mac);
		if (u != null) {
			util.Out().print(util.ToJson(u));
			return;
		}

		u = new User();

		u.setMac(mac);
		u.setStage("δ��д");
		if (address == null) {
			u.setAddress("����");
			u.setAddcity("������");
		} else {
			u.setAddress(address);
			u.setAddcity("δ����");
		}

		u.setFinaltime(time);
		u.setSchool("δ��д");
		// u.setOs(os);
		u.setOnline(1);
		u.setTime(time);
		u.setCompetence(0);// ��ͨ�û�
		u.setGag(0);// ���Դ�����̳
		User macU = userdao.byIdMac();
		if (macU != null) {
			Integer ing = Integer.parseInt(macU.getAccnumno()) + 1;
			accnumno = ing.toString();
		} else {
			accnumno = "10000000";
		}
		System.out.println("����ID:" + accnumno);
		u.setAccnumno(accnumno);
		userdao.save(u);
		System.out.println("ע��ɹ� phone" + phone + "accnumno:" + accnumno
				+ ",pw:" + password);

		// ע�ỷ��
		u = userdao.byUsernameAccnumnoPhone(accnumno);
		if (u != null) {
			if (file != null) {

				String path = "/IMG/Userimg/" + u.getId();
				String pah = util.ufileToServer(path, file, "jpg");
				u.setImg(pah);
				userdao.update(u);
			} else {
				System.out.println("û�л�ȡ��ͷ��!");
			}
			pd.setUid(u.getId());
			pd.setPassword(accnumno.toString());// ��ʼ����
			userdao.save(pd);
			JSONObject json = new JSONObject();
			json.put("username", u.getId());
			// �û�id
			json.put("password", "123456");
			// �û�����
			String w = WechatKit.post(url, json,
					RefreshAccessToken.access_token);
			System.out.println(w);
			u.setPassword(accnumno.toString());

			util.Out().print(util.ToJson(u));
			String url = "http://127.0.0.1/PerfectBefriend/aStas?province="
					+ address + "&os=" + os;
			WechatKit.sendGet(url);
			return;
		} else {
			util.Out().print("null");
		}

	}

	/**
	 * ���û�ע�� username password phone ������Ϊnull
	 * 
	 * @throws IOException
	 */
	public void synSave() throws IOException {
		try {
			String key = request.getParameter("key");

			if (!key.equals("tryunk")) {
				util.Out().print("keynull");
				return;
			}
			// ��֤�û���
			// String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";

			if (username == null) {
				util.Out().print("ufalse");
				return;
			}

			if (password == null) {
				util.Out().print("pfalse");
				return;

			}
			/**
			 * // ��֤���� reg = "[A-Za-z0-9_]{6,18}"; if
			 * (!password.matches(reg)) { System.out.println("�����ʽ���ԣ�");
			 * util.Out().print(false); return; }
			 * 
			 * 
			 * if (phone == null) { util.Out().print(false); return; }
			 * 
			 * String regp = "[0-9]{11}"; if (!phone.matches(regp)) {
			 * System.out.println("�ֻ��Ÿ�ʽ���ԣ�"); util.Out().print(false);
			 * return; }
			 * 
			 * if (userdao.byUsernameAccnumnoPhone(phone) != null) {
			 * util.Out().print(false); return;
			 * 
			 * }
			 */

			System.out.println("�û���:" + username);
			System.out.println("����:" + password);
			System.out.println("�û���:" + accnumno);
			if (username != null) {
				u.setUsername(username);
				u.setNickname(username);// û�����ù���ʾ�û���
			}
			if (nickname != null) {
				u.setNickname(nickname);
			}

			u.setCome("syn");
			u.setOs("syn");
			u.setAccnumno(accnumno);
			u.setPhone(phone);
			u.setStage("δ��д");
			u.setAddress(address);
			u.setAddcity(addcity);
			u.setFinaltime(time);
			u.setSchool("δ��д");
			u.setTime(time);
			u.setCompetence(0);// ��ͨ�û�
			u.setGag(0);// ���Դ�����̳
			if (userdao.byUsernameAccnumnoPhone(username) != null) {
				util.Out().print(false);
				return;

			}
			User macU = userdao.byIdMac();
			if (macU != null) {
				Integer ing = Integer.parseInt(macU.getAccnumno()) + 1;
				accnumno = ing.toString();
			} else {
				accnumno = "10000000";
			}
			System.out.println("����ID:" + accnumno);
			u.setAccnumno(accnumno);
			userdao.save(u);
			System.out.println("ע��ɹ� phone" + phone + "accnumno:" + accnumno
					+ ",pw:" + password);

			// ע�ỷ��
			u = userdao.byUsernameAccnumnoPhone(accnumno);
			if (u != null) {
				if (file != null) {

					String path = "/IMG/Userimg/" + u.getId();
					String pah = util.ufileToServer(path, file, "jpg");
					u.setImg(pah);
					userdao.update(u);
				} else {
					System.out.println("û�л�ȡ��ͷ��!");
				}
				pd.setUid(u.getId());
				pd.setPassword(password);
				userdao.save(pd);
				JSONObject json = new JSONObject();
				json.put("username", u.getId());
				// �û�id
				json.put("password", "123456");
				// �û�����
				String w = WechatKit.post(url, json,
						RefreshAccessToken.access_token);
				System.out.println(w);
				u.setPassword(password);

				util.Out().print(true);
				return;
			} else {
				util.Out().print("null");
				return;
			}

		} catch (Exception e) {
		}
	}

	/**
	 * �û�ͨ���ֻ����޸�����
	 * 
	 * @throws IOException
	 */
	public void Modtionphone() throws IOException {
		/**
		 * String codes = (String) session.getAttribute("app_code"); if (codes
		 * == null) { System.out.println("session�ѹ���");
		 * util.Out().print("cnull"); return; } if
		 * (!codes.equalsIgnoreCase(code)) { util.Out().print("cnull"); return;
		 * } session.invalidate();
		 */
		u = userdao.byUsernameAccnumnoPhone(phone);
		if (u == null) {
			System.out.println("�˻�������!");
			util.Out().print(false);
			return;
		}
		pd = userdao.select(u.getId());
		if (pd == null) {
			System.out.println("�˻�������!");
			util.Out().print(false);
			return;
		}
		if (password == null) {
			System.out.println("����Ϊ��!");
			util.Out().print("null");
			return;
		}
		// ��֤����
		String reg = "[A-Za-z0-9_]{6,18}";
		if (password.matches(reg)) {
			pd.setPassword(password);
			userdao.update(pd);
			System.out.println("�����޸ĳɹ�!");
			util.Out().print(true);
			return;
		} else {
			System.out.println("���벻�Ϸ�!");
			util.Out().print("pfalse");
			return;
		}

	}

	/**
	 * �û��޸� ��Ϣ
	 * 
	 * @throws IOException
	 */
	public void Modification() throws IOException {
		try {

			String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";
			String p = request.getParameter("p");
			u = userdao.byUsernameAccnumnoPhone(accnumno);
			if (u == null) {
				util.Out().print("null");
				return;
			}

			if (p != null) {
				pd = userdao.login(u.getId(), password);
				if (pd == null) {

					util.Out().print(false);
					return;
				}

				if (!OpeFunction.isEmpty(newpassword)) {

					reg = "[A-Za-z0-9_]{6,18}";
					if (newpassword.matches(reg)) {
						pd.setPassword(newpassword);
						userdao.update(pd);

						util.Out().print(true);
						return;

					} else {

						util.Out().print("nfalse");
						return;
					}
				}
			}

			if (u.getCompetence() == 0) {
				if (!util.isEmpty(addcity)) {
					u.setAddcity(addcity);
				}
				if (!util.isEmpty(address)) {
					u.setAddress(address);

				}
			}

			if (u.getUsername() == null) {
				if (username != null) {
					reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";
					if (username.matches(reg)) {
						if (userdao.byUsernameAccnumnoPhone(username) != null) {

							util.Out().print("usernamefalse");
							return;
						}
						u.setUsername(username);
						userdao.update(u);

					} else {

						util.Out().print("ufalse");
						return;
					}
				}

			}

			if (nickname != null) {
				u.setNickname(nickname);

			}

			if (school != null) {
				u.setSchool(school);

			}
			String path = "";

			if (file != null) {
				path = u.getImg();
				if (path != null) {
					util.fileRemove(path);
				}
				path = "/IMG/Userimg/" + OpeFunction.getDayTime(1) + "/"
						+ OpeFunction.getDayTime(2) + OpeFunction.getDayTime(3)
						+ "/" + u.getId();
				path = util.ufileToServer(path, file, "jpg");

				u.setImg(path);
			}

			if (!util.isEmpty(stage)) {
				u.setStage(stage);

			}
			if (!util.isEmpty(childrenage)) {
				u.setChildrenage(childrenage);
			}
			if (!util.isEmpty(phone)) {
				if (userdao.byUsernameAccnumnoPhone(phone) != null) {
					util.Out().print("phonefalse");
					return;
				}
				u.setPhone(phone);
			}
			if (!util.isEmpty(age)) {
				u.setAge(age);
			}
			if (!util.isEmpty(sex)) {
				u.setSex(sex);
			}
			if (signature != null) {
				u.setSignature(signature);
			}
			if (!util.isEmpty(childrensex)) {
				u.setChildrensex(childrensex);
			}
			u.setMac(null);

			userdao.update(u);
			util.Out().print(util.ToJson(u));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �û������ֻ���ʱ���ֻ�������֤��
	 * 
	 * @throws IOException
	 */
	public void phoneSend() throws IOException {
		// phone="13555717521";
		// int num=123456789;
		System.out.println("����phonetext");
		//
		String regp = "[0-9]{11}";
		if (phone == null) {
			System.out.println("�ֻ��ſգ�");
			util.Out().print("null");
			return;
		}
		if (!phone.matches(regp)) {
			System.out.println("�ֻ��Ÿ�ʽ���ԣ�");
			util.Out().print("null");
			return;
		}
		if (userdao.byUsernameAccnumnoPhone(phone) == null) {
			System.out.println("���ֻ���û��ע���");

			util.Out().print(false);

			return;
		}
		int num = (int) ((Math.random() * 9 + 1) * 100000);
		session.setAttribute("app_code", String.valueOf(num));
		session.setMaxInactiveInterval(90);
		System.out.println("1��ȷ��֤��Ϊ" + num);

		String content = new String("������֤���ǣ�" + num
				+ "���벻Ҫ����֤��й¶�������ˡ���Ǳ��˲������ɲ�����ᣡ");
		util.setphone(phone, content);
		util.Out().print(true);

	}

	/**
	 * ����ʱ���ѯ �û� �� �û�����
	 * 
	 * @throws IOException
	 */
	public String GetUsertime() throws IOException {
		// Map session = (Map) util.getsession();// ���� map

		System.out.println(timeq);
		System.out.println(timeh);

		int count = userdao.getUsertime(timeq, timeh);

		System.out.println("��" + us.size() + "���û�");
		request.setAttribute("timeh", timeh);
		// request.setAttribute("GetUsertimeus", us);
		request.setAttribute("timeq", timeq);
		request.setAttribute("count", count);

		return Action.SUCCESS;
	}

	/**
	 * ��ѯȫ���û�
	 * 
	 * @throws IOException
	 */
	public String GetUserAll() throws IOException {
		Map session = (Map) util.getsession();// ���� map
		competence2 = userdao.getCount();// ȫ���û�����
		int syn = userdao.getCountSyn();// ͬ�����û�����
		List<User> ul = userdao.getOnline();// ��ѯ�����û�
		int a = competence2;
		System.out.println("�û�����" + competence2);
		System.out.println("��" + competence2 + "���û�");

		if (competence2 % pageSize == 0) {
			competence2 = competence2 / pageSize;// ��ȡ ���ж���ҳ
		} else {
			competence2 = competence2 / pageSize + 1;// ��ȡ ���ж���ҳ
		}
		if (currentPage >= competence2) {
			currentPage = competence2;
		}
		if (currentPage < 1) {
			currentPage = 1;
		}

		us = userdao.getUserAll(pageSize, currentPage);

		request.setAttribute("GetUserAllus", us);
		request.setAttribute("competence2", competence2);
		request.setAttribute("a", a);
		request.setAttribute("ul", ul);
		request.setAttribute("syn", syn);
		return Action.SUCCESS;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getAddcity() {
		return addcity;
	}

	public void setAddcity(String addcity) {
		this.addcity = addcity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getCompetence2() {
		return competence2;
	}

	public void setCompetence2(int competence2) {
		this.competence2 = competence2;
	}

	public int getGag() {
		return gag;
	}

	public void setGag(int gag) {
		this.gag = gag;
	}

	public String getTimeq() {
		return timeq;
	}

	public void setTimeq(String timeq) {
		this.timeq = timeq;
	}

	public String getTimeh() {
		return timeh;
	}

	public void setTimeh(String timeh) {
		this.timeh = timeh;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAccnumno() {
		return accnumno;
	}

	public void setAccnumno(String accnumno) {
		this.accnumno = accnumno;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getMac() {
		return mac;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public UserAction(ApputilDAO adao, UserDAO userdao, GroupDAO gdao,
			RegistrationDAO regdao) {
		super();
		this.adao = adao;
		this.userdao = userdao;
		this.gdao = gdao;
		this.regdao = regdao;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getChildrensex() {
		return childrensex;
	}

	public void setChildrensex(String childrensex) {
		this.childrensex = childrensex;
	}

	public String getChildrenage() {
		return childrenage;
	}

	public void setChildrenage(String childrenage) {
		this.childrenage = childrenage;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

}
