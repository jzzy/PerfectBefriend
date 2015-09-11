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
import com.befriend.entity.Profile;
import com.befriend.entity.Registrationsa;
import com.befriend.entity.Stas;
import com.befriend.entity.User;
import com.befriend.udp.UDPServer;
import com.befriend.util.OpeFunction;
import com.befriend.wechat.RefreshAccessToken;
import com.befriend.wechat.WechatKit;
import com.opensymphony.xwork2.Action;
@SuppressWarnings("all")
public class UserAction {
	private User u = new User();
	public OpeFunction util;
	private ApputilDAO adao;
	private UserDAO userdao;
	private GroupDAO gdao;
	private RegistrationDAO regdao;

	List<User> us = new ArrayList<User>();
	HttpServletRequest request = ServletActionContext.getRequest();
	private String code=null;
	List<Cis> cis = new ArrayList<Cis>();
	private String sex=null;
	private String signature=null;
	private String childrensex=null;
	private String newpassword=null;

	private String childrenage=null;
	private String mac=null;
	private String phone=null;
	private String username=null;
	private String password=null;
	private String newpwd=null;
	private int id = -1;
	private String school=null;
	private String stage=null;
	private String time = util.getNowTime();
	private String nickname=null;
	private String address=null;
	private String os =null;
	private String addcity=null;

	private HttpSession session = ServletActionContext.getRequest()
			.getSession();

	private int competence2 = -1;
	private int gag = -1;
	private int pageSize = 50;
	private int currentPage = 1;

	private String timeq=null;

	private String timeh=null;
	private File file=null;
	private String fileFileName=null;
	private String fileContentType=null;

	private String accnumno=null;
	private int port = -1;
	private String ip=null;
	private String province=null;
	private String city=null;
	private String age=null;

	
	private final static String URL = "https://a1.easemob.com/topLong/parentsfriend/users";

	public String ViewStatistics() throws IOException {

		String url = "http://127.0.0.1"+request.getContextPath() +"/aStas";
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
		 * 1("����֮ǰ��"); // �������� for (int i = 0; i < a.length; i++) {
		 * System.out.print(a[i] + " "); } // ֱ�Ӳ������� for (int i = 1; i <
		 * a.length; i++) { // ������Ԫ�� int temp = a[i]; int j;
		 * 
		 * for (j = i - 1; j >= 0; j--) { // ������temp�������ƶ�һλ if (a[j] >
		 * temp) { a[j + 1] = a[j]; } else { break; } } a[j + 1] = temp;
		 * 1("========="); for (int x = 0; x < a.length; x++) { if (x ==
		 * a.length - 1) { 1(a[x]); } else { System.out.print(a[x] + " "); }
		 * 
		 * } } 1(); 1("����֮��"); for (int i = 0; i < a.length; i++) {
		 * System.out.print(a[i] + " "); }
		 * 
		 * // ð������ for (int i = 0; i < a.length; i++) { for (int j = 0; j <
		 * a.length - i - 1; j++) { //
		 * ����-i��Ҫ��ÿ����һ�ζ�������i�������������ȥ�ˣ�û�б�Ҫ���滻�� if (a[j] >
		 * a[j + 1]) { int temp = a[j]; a[j] = a[j + 1]; a[j + 1] = temp; } } }
		 * 1(); 1("����֮��"); for (int i = 0; i < a.length; i++) {
		 * System.out.print(a[i] + " "); }
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
			util.Out().print(false);
			return;
		}
		if (admin.getLevel() != 1) {
			util.Out().print(false);
			return;
		}
		u = userdao.byid(id);
		if (u == null) {
			util.Out().print(false);
			return;
		}
		u.setPhone(null);
		userdao.update(u);
		util.Out().print(true);

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

				continue;

			}
			if (!util.isEmpty(u.getAccnumno())) {

				continue;
			}

			User macU = userdao.byAccnumnoIdMax();
			if (macU != null) {
				Integer ing = Integer.parseInt(macU.getAccnumno()) + 1;
				accnumno = ing.toString();
			} else {
				accnumno = "10000000";
			}

			u.setAccnumno(accnumno);
			userdao.update(u);

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

				return;
			}
			String w = WechatKit.post(URL, json,
					RefreshAccessToken.access_token);

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

		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/GetUserAll");

	}

	public void onlineUserSend() throws IOException, InterruptedException {

		u = userdao.byid(id);
		if (u != null) {
			cis = gdao.Cisuid(u.getId());

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

		u = userdao.byid(id);
		if (u != null) {

			u.setIp(ip);
			u.setPort(port);
			userdao.update(u);
			util.Out().print(true);
		} else {

			util.Out().print(false);
		}

	}

	public void onlineStatus() throws IOException, InterruptedException {

		u = userdao.byid(id);
		if (u == null) {
			u = userdao.byUsernameAccnumnoPhone(username);
		}
		if (u != null) {

			u.setOnline(0);
			userdao.update(u);

			util.Out().print(true);
			return;

			/**
			 * int uid = u.getId();
			 * 
			 * u.setOnline(1); userdao.update(u);
			 * 
			 * cis = gdao.Cisuid(uid); 1("�û���:" + u.getUsername() + cis.size()
			 * + "��δ����Ϣ!" + "Online:" + u.getOnline());
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

	public void websessionrom() throws IOException {

		session.removeAttribute("u");
		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/SimulationApp/login.html");
	}

	public void webpassModification() throws IOException {

		User u = (User) session.getAttribute("u");
		if (u == null) {

			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/SimulationApp/login.html");

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

				((HttpServletResponse) util.response()).sendRedirect(request
						.getContextPath() + "/SimulationApp/login.html");

				return;
			}

			u = userdao.byid(u.getId());

			if (u == null) {

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
				path = "/IMG/Userimg/" +OpeFunction.getNameDayTime();
				String pah = util.ufileToServer(path, file, "jpg");
				u.setImg(pah);

			}
			if (nickname != null) {
				u.setNickname(nickname);

			}

			if (u.getCompetence() == 0) {
				if (city != null && !city.equals("������") && city != "") {
					u.setAddcity(city);

				} else {

					Object Ocity = session.getAttribute("city");

					if (Ocity != null) {
						city = Ocity.toString();
						u.setAddcity(city);
					}

				}
				if (province != null && !province.equals("ʡ����")
						&& province != "") {
					u.setAddress(province);

				} else {

					Object Oprovince = session.getAttribute("province");
					if (Oprovince != null) {
						province = Oprovince.toString();
						u.setAddress(province);
					}

				}
			} else {

			}

			// ��֤�û���
			String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";

			if (username != null) {
				if (!username.matches(reg)) {

					util.Out().print("�û�����ʽ���ԣ�");
					return;
				}
			}
			if (u.getUsername() == null) {

				u.setUsername(username);
			}
			if (school != null) {
				u.setSchool(school);

			}

			if (stage != null) {
				u.setStage(stage);

			}

			userdao.update(u);
			session.setAttribute("u", u);

			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath()
					+ "/SimulationApp/personal_information.jsp");
		} catch (Exception e) {

		}

	}

	public String webRegistration() throws IOException, JSONException {

		phone = request.getParameter("phone");
		password = request.getParameter("password");
		username = request.getParameter("username");

		if (password == null) {

			util.Out().print("����Ϊ��");
			return null;
		}
		if (phone == null) {

			util.Out().print("�ֻ���Ϊ��");
			return null;
		}

		String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";

		if (username != null) {
			if (!username.matches(reg)) {

				util.Out().print("�û�����ʽ���ԣ�");
				return null;
			}

		}
		// ��֤����
		reg = "[A-Za-z0-9_]{6,18}";
		if (!password.matches(reg)) {

			util.Out().print("�����ʽ���ԣ�");
			return null;
		}
		// ��֤�ֻ���
		String regp = "[0-9]{11}";
		if (!phone.matches(regp)) {

			util.Out().print("�����ʽ���ԣ�");
			return null;
		}

		if (userdao.byUsernameAccnumnoPhone(phone) != null) {

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

			request.setAttribute("ue", username);
			return Action.ERROR;
		}
		User macU = userdao.byAccnumnoIdMax();
		if (macU != null) {
			Integer ing = Integer.parseInt(macU.getAccnumno()) + 1;
			accnumno = ing.toString();
		} else {
			accnumno = "10000000";
		}

		u.setAccnumno(accnumno);
		userdao.save(u);
		u = userdao.byUsernameAccnumnoPhone(accnumno);
		if (u == null) {
			util.Out().print("�쳣������ע��");
			return null;
		}

		u.setPassword(password);
		session.setAttribute("u", u);

		u = userdao.byUsernameAccnumnoPhone(phone);
		if (u != null) {

			JSONObject json = new JSONObject();
			json.put("username", u.getId());

			json.put("password", "123456");
			String w = WechatKit.post(URL, json,
					RefreshAccessToken.access_token);
		}

		return Action.SUCCESS;

	}

	public String getUsermhh() throws IOException {

		User ue = (User) session.getAttribute("useradmin");

		if (ue == null) {
			util.Out().print("�û�����Ϊ��");
			return null;
		}
		if (username.length() >= 1) {
			us = userdao.likeusername(username, ue.getAddress());
		}

		request.setAttribute("usaha", us);

		return Action.SUCCESS;

	}

	public void SetQU() throws IOException, InterruptedException {

		User ue = (User) session.getAttribute("useradmin");

		u = userdao.byid(id);
		if (u == null || ue == null) {
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/Judge/No.html");
			return;
		}
		if (u.getId().equals(ue.getId())) {

			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/Judge/No.html");
			return;
		}
		if (!u.getAddress().equals(ue.getAddress()) || u.getAddress() == null) {
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/Judge/No.html");
			return;
		}

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

		request.setAttribute("u", u);
		return Action.SUCCESS;

	}

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

		u = (User) session.getAttribute("useradmin");
		if (u != null) {

			if (u.getCompetence() == 1) {

				us = userdao.getUserAll(u.getAddress(), u.getAddcity(), timeq,
						timeh);

			}

			else if (u.getCompetence() == 2) {

				if (!addcity.equals("nu")) {

					us = userdao.getUserAll(u.getAddress(), addcity, timeq,
							timeh);
				} else {

					us = userdao.getUserAll(u.getAddress(), timeq, timeh);

				}

			} else {

				util.Out().print("�����µ���");
				return null;
			}

			request.setAttribute("GetUsertimeus", us);
			request.setAttribute("timeq", timeq);
			request.setAttribute("timeh", timeh);
		}

		return Action.SUCCESS;
	}

	public String RegionalAdministrator() throws IOException {
		try {

			u = userdao.byUsernameAccnumnoPhone(username);
			if (u == null) {
				util.Out().print("�˺Ż��������");

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

			}

			// ����
			String time0 = util.getNumTime(-1);
			// ����ǰ
			String time7 = util.getNumTime(7);
			// һ����ǰ
			String time30 = util.getNumTime(30);
			// һ��ǰ
			String time365 = util.getNumTime(365);

			for (int c = 0; c < a.size(); c++) {

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

				rga = regdao.addressty(u.getAddress(), u.getAddcity());

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

	public void Gag() throws IOException {

		u = userdao.byUsernameAccnumnoPhone(username);
		if (gag == 1) {
			u.setGag(1);
			util.Out().print("���û�" + username + "���Գɹ�");
		} else {
			u.setGag(0);
			util.Out().print("���û�" + username + "ȡ�����Գɹ�");
		}
		userdao.update(u);
	}

	public String getUserin() throws IOException {

		u = userdao.byid(id);
		if (u == null) {
			util.Out().print("û������û�");
			return null;

		}

		request.setAttribute("u", u);
		return Action.SUCCESS;

	}

	public String getUsermh() throws IOException {

		if (username.length() >= 1) {
			us = userdao.likeusername(username);
		}

		request.setAttribute("usaha", us);

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

		u = userdao.byid(id);

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

	public String getUser() throws IOException {

		if (currentPage < 1) {
			currentPage = 1;
		}

		us = userdao.getUser(pageSize, currentPage);
		request.setAttribute("us", us);
		// request.setAttribute("competence2", competence2);
		request.setAttribute("currentPage", currentPage);

		return Action.SUCCESS;

	}

	public void AdminLogin() throws IOException {
		
	
		
		try {
			request.setAttribute("luntan", "UserAdmin.jsp");

			System.out.print("username-" + username + "-password-" + password);

			u = userdao.byUsernameAccnumnoPhone(username);
			if (u == null) {
				util.Out().print("�˺Ż��������");

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

	public void Login() throws IOException {
		try {
			System.out.println("Login username:"+username+",password:"+password);
			if(util.isEmpty(username)||util.isEmpty(password)){
				
				util.Out().print(false);
				return;
			}
			 u = userdao.login(username, password);
			if (u == null) {
				if(userdao.byUsernameAccnumnoPhone(username)!=null){
					util.Out().print(false);
				}else{
					util.Out().print("null");
				}
		
				return;
			}

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

			util.Out().print(util.ToJson(u));

			time = util.getNumTime(0);
			String url = "";
			if (province != null) {
				url = "http://127.0.0.1"+request.getContextPath() +"/aStas?os=" + os
						+ "&province=" + province;
			} else {
				url = "http://127.0.0.1"+request.getContextPath() +"/aStas?os=" + os;
			}
			WechatKit.sendGet(url);

		} catch (Exception e) {

		}

	}

	public void newLogin() throws IOException {
		try {

			u = userdao.byUsernameAccnumnoPhone(username);
			if (u == null) {

				util.Out().print("null");
				return;
			}

			int ut = u.getLoginnum();
			if (ut > 0) {
				ut = ++ut;
				u.setLoginnum(ut);
			} else {
				u.setLoginnum(1);
			}

			u.setFinaltime(time);

			userdao.update(u);
			String result = "{\"user\":" + util.ToJson(u) + "}";
			util.Out().print(result);

		} catch (Exception e) {

		}

	}

	/**
	 * syn �û�����
	 * 
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public void synLogin() throws IOException, NoSuchAlgorithmException {

		os = "web";
		u = userdao.byUsernameAccnumnoPhone(username);
		if (u == null) {

			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/webNewsA10");
			return;
		}

		if (u != null) {

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
			time = util.getNumTime(0);

			session.setAttribute("u", u);

			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/webNewsA10");
			String url = "";
			if (u.getAddress() != null) {
				url = "http://127.0.0.1"+request.getContextPath() +"/aStas?os=" + os
						+ "&province=" + u.getAddress();
			} else {
				url = "http://127.0.0.1"+request.getContextPath() +"/aStas?os=" + os;
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

		u = userdao.byUsernameAccnumnoPhone(username);
		if (u == null) {

			util.Out().print("�˻�Ϊ��");
			return null;
		}

		if (u != null) {

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

			String url = "";
			if (u.getAddress() != null) {
				url = "http://127.0.0.1"+request.getContextPath() +"/aStas?os=" + os
						+ "&province=" + u.getAddress();
			} else {
				url = "http://127.0.0.1"+request.getContextPath() +"/aStas?os=" + os;
			}
			WechatKit.sendGet(url);
		}

		return null;

	}

	/**
	 * ͨ���ֻ����һ������һ�����
	 */
	public void appSendCodePassword() throws IOException {
		try {

			//
			String regp = "[0-9]{11}";
			if (phone == null) {

				util.Out().print("null");
				return;
			}
			if (!phone.matches(regp)) {

				util.Out().print("null");
				return;
			}

			if (userdao.byUsernameAccnumnoPhone(phone) == null) {
				util.Out().print(false);

				return;
			}

			int num = (int) ((Math.random() * 9 + 1) * 100000);

			session.setAttribute("app_code", String.valueOf(num));
			session.setMaxInactiveInterval(90);

			String content = new String("您的验证码是：" + num
					+ "。请不要把验证码泄露给其他人。如非本人操作，可不用理会！");
			util.setphone(phone, content);
			System.out.println(content);
			util.Out().print(true);

		} catch (Exception e) {
			// TODO: handle exception������
			e.printStackTrace();
		}

	}

	/**
	 * to mge phone
	 * 
	 * @throws IOException
	 */
	public void appSendCode() throws IOException {
		try {
			//
			String regp = "[0-9]{11}";

			if (phone == null) {

				util.Out().print("null");
				return;
			}

			if (!phone.matches(regp)) {
				util.Out().print("null");
				return;
			}

			if (userdao.byUsernameAccnumnoPhone(phone) != null) {

				util.Out().print(false);

				return;
			}

			int num = (int) ((Math.random() * 9 + 1) * 100000);

			session.setAttribute("app_code", String.valueOf(num));
			session.setMaxInactiveInterval(600);

			String content = new String("您的验证码是：" + num
					+ "。请不要把验证码泄露给其他人。如非本人操作，可不用理会！");
			util.setphone(phone, content);
			System.out.println(phone+" "+content);
			util.Out().print(true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void appCheckCode() throws IOException {
		try {

			String codes = (String) session.getAttribute("app_code");

			if (codes == null) {

				util.Out().print("null");
				return;
			}
			if (codes.equalsIgnoreCase(code)) {

				util.Out().print(true);
				/**
				 * session.invalidate()是让当前浏览器的session销毁,也就是一个session被销毁
				 * 而removeAttribute()可以指定销毁session中的某个属性
				 */
				session.removeAttribute("app_code");
			} else {
				util.Out().print(false);
			}

		} catch (Exception e) {

		}

	}

	public synchronized void save() throws IOException, JSONException {

		String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";

		if (!util.isEmpty(username)) {
			if (!username.matches(reg)) {

				util.Out().print(false);
				return;
			}
		}

		reg = "[A-Za-z0-9_]{6,18}";
		if (password == null || !password.matches(reg)) {

			util.Out().print(false);
			return;
		}

		String regp = "[0-9]{11}";
		if (phone == null || !phone.matches(regp)) {

			util.Out().print(false);
			return;
		}

		if (!util.isEmpty(username)) {
			u.setUsername(username);
			u.setNickname(username);
		}
		if (!util.isEmpty(nickname)) {
			u.setNickname(nickname);
		}

		u.setAccnumno(accnumno);
		u.setPhone(phone);

		u.setFinaltime(time);

		u.setOs(os);
		u.setOnline(1);
		u.setTime(time);
		u.setCompetence(0);
		u.setGag(0);
		synchronized (this) {
			if (userdao.byUsernameAccnumnoPhone(phone) != null) {
				util.Out().print(false);
				return;

			}
			if (userdao.byUsernameAccnumnoPhone(username) != null) {
				util.Out().print(false);
				return;

			}
			User macU = userdao.byAccnumnoIdMax();
			if (macU != null) {
				Integer ing = Integer.parseInt(macU.getAccnumno()) + 1;
				accnumno = ing.toString();
			} else {
				accnumno = "10000000";
			}
			u.setPassword(password);
			u.setAccnumno(accnumno);
			userdao.save(u);
		}
		u = userdao.byUsernameAccnumnoPhone(phone);
		if (u != null) {
			if (file != null) {

				String path = "/IMG/Userimg/" +OpeFunction.getNameDayTime();
				String pah = util.ufileToServer(path, file, "jpg");
				u.setImg(pah);
				userdao.update(u);
			} 
			String url = "http://127.0.0.1" + request.getContextPath()
					+ "/createDefaultGroup?userId=" + u.getId();
			WechatKit.sendGet(url);

			JSONObject json = new JSONObject();
			json.put("username", u.getId());

			json.put("password", "123456");

			String w = WechatKit.post(URL, json,
					RefreshAccessToken.access_token);
			System.out.println(w);
			util.Out().print(util.ToJson(u));
			url = "http://127.0.0.1" + request.getContextPath()
					+ "/aStas?province=" + address + "&os=" + os;
			WechatKit.sendGet(url);
			return;
		} else {
			util.Out().print("null");
			return;
		}

	}

	/**
	 * ID
	 * 
	 * @throws IOException
	 * @throws JSONException
	 */
	public synchronized void oneSave() throws IOException, JSONException {

		u = userdao.byMac(mac);
		System.out.println("oneSave Mac:" + mac);
		if (u != null || util.isEmpty(mac)) {
			util.Out().print(util.ToJson(u));
			return;
		}

		u = new User();

		u.setMac(mac);

		u.setFinaltime(time);

		u.setOs(os);
		u.setOnline(1);
		u.setTime(time);
		u.setCompetence(0);
		u.setGag(0);
		synchronized (this) {

			User macU = userdao.byAccnumnoIdMax();
			if (macU != null) {
				Integer ing = Integer.parseInt(macU.getAccnumno()) + 1;
				accnumno = ing.toString();
			} else {
				accnumno = "10000000";
			}

			u.setAccnumno(accnumno);
			userdao.save(u);
			System.out.println("oneSave accnumno ID:" + accnumno);
		}

		u = userdao.byUsernameAccnumnoPhone(accnumno);
		if (u != null) {
			if (file != null) {

				String path = "/IMG/Userimg/" +OpeFunction.getNameDayTime();
				String pah = util.ufileToServer(path, file, "jpg");
				u.setImg(pah);
				userdao.update(u);
			} 

			JSONObject json = new JSONObject();
			json.put("username", u.getId());
			json.put("password", "123456");
			String w = WechatKit.post(URL, json,
					RefreshAccessToken.access_token);
			System.out.println("在环信注册 的返回值:"+w);
			util.Out().print(util.ToJson(u));
			String url = "http://127.0.0.1" + request.getContextPath()
					+ "/aStas?province=" + address + "&os=" + os;
			WechatKit.sendGet(url);
			return;
		} else {
			util.Out().print("null");
		}

	}

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
			u.setAddress(address);
			u.setAddcity(addcity);
			u.setFinaltime(time);

			u.setTime(time);
			u.setCompetence(0);// ��ͨ�û�
			u.setGag(0);// ���Դ�����̳
			if (userdao.byUsernameAccnumnoPhone(username) != null) {
				util.Out().print(false);
				return;

			}
			User macU = userdao.byAccnumnoIdMax();
			if (macU != null) {
				Integer ing = Integer.parseInt(macU.getAccnumno()) + 1;
				accnumno = ing.toString();
			} else {
				accnumno = "10000000";
			}

			u.setAccnumno(accnumno);
			userdao.save(u);

			u = userdao.byUsernameAccnumnoPhone(accnumno);
			if (u != null) {
				if (file != null) {

					String path = "/IMG/Userimg/"  +OpeFunction.getNameDayTime();
					String pah = util.ufileToServer(path, file, "jpg");
					u.setImg(pah);
					userdao.update(u);
				} else {

				}

				JSONObject json = new JSONObject();
				json.put("username", u.getId());
				// �û�id
				json.put("password", "123456");
				// �û�����
				String w = WechatKit.post(URL, json,
						RefreshAccessToken.access_token);

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

	public void Modtionphone() throws IOException {

		u = userdao.byUsernameAccnumnoPhone(phone);
		if (u == null) {

			util.Out().print(false);
			return;
		}

		if (password == null) {

			util.Out().print("null");
			return;
		}
		// ��֤����
		String reg = "[A-Za-z0-9_]{6,18}";
		if (password.matches(reg)) {
			u.setPassword(password);
			userdao.update(u);
			util.Out().print(true);
			return;
		} else {

			util.Out().print("pfalse");
			return;
		}

	}

	public void Modification() throws IOException {
		try {

			String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";
			String p = request.getParameter("p");
			System.out.println("p "+p+" "+accnumno+" "+password);
			if (p != null) {
				
				u = userdao.login(accnumno, password);
				if (u == null) {
					util.Out().print(false);
					return;
				}
				if (!OpeFunction.isEmpty(newpassword)) {

					reg = "[A-Za-z0-9_]{6,18}";
					if (newpassword.matches(reg)) {
						System.out.println("修改成功"+newpassword);
						u.setPassword(newpassword);
						userdao.update(u);

						util.Out().print(true);
						return;

					} else {
						System.out.println("修改失败"+newpassword);
						util.Out().print("nfalse");
						return;
					}
				}
			}
			u = userdao.byUsernameAccnumnoPhone(accnumno);
			if (u == null) {
				util.Out().print("null");
				return;
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
				path = "/IMG/Userimg/" + OpeFunction.getNameDayTime();
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
			if (!util.isEmpty(signature)) {
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

	public void phoneSend() throws IOException {

		String regp = "[0-9]{11}";
		if (phone == null) {

			util.Out().print("null");
			return;
		}
		if (!phone.matches(regp)) {

			util.Out().print("null");
			return;
		}
		if (userdao.byUsernameAccnumnoPhone(phone) == null) {

			util.Out().print(false);

			return;
		}
		int num = (int) ((Math.random() * 9 + 1) * 100000);
		session.setAttribute("app_code", String.valueOf(num));
		//session.setMaxInactiveInterval(90);

		String content = new String("您的验证码是：" + num
				+ "。请不要把验证码泄露给其他人。如非本人操作，可不用理会！");
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

		int count = userdao.getUsertime(timeq, timeh);

		request.setAttribute("timeh", timeh);
		// request.setAttribute("GetUsertimeus", us);
		request.setAttribute("timeq", timeq);
		request.setAttribute("count", count);

		return Action.SUCCESS;
	}

	public String GetUserAll() throws IOException {
		competence2 = userdao.getCount();// ȫ���û�����
		int syn = userdao.getCountSyn();// ͬ�����û�����
		List<User> ul = userdao.getOnline();// ��ѯ�����û�
		int a = competence2;

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
