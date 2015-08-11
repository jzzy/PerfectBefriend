package com.befriend.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.befriend.dao.AppDAO;
import com.befriend.dao.ApputilDAO;
import com.befriend.dao.UserDAO;
import com.befriend.entity.App;
import com.befriend.entity.AppUp;
import com.befriend.entity.Feedback;
import com.befriend.entity.Stas;
import com.befriend.entity.User;
import com.befriend.entity.Visitor;
import com.befriend.util.OpeFunction;
import com.befriend.wechat.WechatKit;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
@SuppressWarnings("all")
public class ApputilAction {
	public OpeFunction util;// ������
	private ApputilDAO audao;// ApputilDAO
	private UserDAO userdao;// �û�dao
	private AppDAO adao;// appdao
	private String username;// �û���
	private String information;//

	private File imgFile;// logoͼƬ
	private String imgFileFileName;// �ļ���
	private String imgFileContentType;// �ļ�����

	private File imgFile1;// Ӧ�ý�ͼ1
	private String imgFile1FileName;// �ļ���
	private String imgFile1ContentType;// �ļ�����

	private File imgFile2;// Ӧ�ý�ͼ2
	private String imgFile2FileName;// �ļ���
	private String imgFile2ContentType;// �ļ�����

	private File imgFile3;// Ӧ�ý�ͼ3
	private String imgFile3FileName;// �ļ���
	private String imgFile3ContentType;// �ļ�����

	private String savePath;// Ŀ¼

	private File appFile;// app�ļ�
	private String appFileFileName;// �ļ���
	private String appFileContentType;// �ļ�����

	private String vnum;// app�汾��
	private String type;// app���
	private String dpt;// Ӧ������
	private String summary;// Ӧ�ø�Ҫ

	private String name;// app����
	private int num = 0;// app�Ƽ�ָ��

	private int currentPage = 1;// ҳ��
	private int id = 0;// id
	private int pageSize = 4;// ����

	private int downloads = 0;// app���ش���

	private int apptv = 0;// �汾��
	private String updates;// ��������

	AppUp au = new AppUp();// appupʵ���� �ҳ�֮�Ѹ����õ���
	App app = new App();// appʵ����
	Visitor vor = new Visitor();// �ο���
	public HttpServletRequest request = ServletActionContext.getRequest();// ��ȡrequest
	private String Mac;//
	private String os;// ϵͳ
	@SuppressWarnings("static-access")
	private String time = util.getNowTime();
	private String province = "all";// ʡ��
	private int synlogin;// �������
	private int downloaded;// ���ش���
	private int usersyned;// ͬʱ������
	private int vored;// ���������ο���
	private int usersaved;// ����ע������

	
	public void aStas() {

		time = util.getNumTime(0);

		String sys = "";
		for (int i = 0; i < 4; i++) {
			if (province.equals("all") || province == null
					|| province.equals("null")) {
				continue;
			}
			switch (i) {
			case 0:
				sys = "web";
				break;
			case 1:
				sys = "android";
				break;
			case 2:
				sys = "ios";
				break;
			case 3:
				sys = "syn";
				break;

			default:
				sys = null;
				break;
			}
			if (sys == null) {
				continue;
			}
			Stas sta = audao.StasTimeDay(time, sys, province);

			usersaved = userdao.getSaveTime(time, sys, province).size();

			vored = audao.VisitorTime(time, sys, province).size();

			synlogin = userdao.getFinaltime(time, sys, province).size();
			if (synlogin != 0) {
				if (synlogin % 3 == 0) {
					usersyned = synlogin / 3;
				} else {
					usersyned = synlogin / 3 + 1;
				}
			} else {
				usersyned = 0;
			}

			if (sta == null) {
				sta = new Stas();
				sta.setProvince(province);
				sta.setTime(time);
				sta.setUserlogined(synlogin);
				if (downloads != 0) {
					sta.setDownloaded(1);
				} else {
					sta.setDownloaded(0);
				}
				sta.setOs(sys);
				sta.setUsersyned(usersyned);
				sta.setUsersaved(usersaved);
				sta.setVored(vored);

				audao.Save(sta);
				continue;
			}
			sta = audao.StasTimeDay(time, sys, province);

			if (sta == null) {
				continue;
			}

			sta.setUserlogined(synlogin);
			if (downloaded != 0 && sys.equals(os)) {
				sta.setDownloaded(sta.getDownloaded() + 1);
			}

			if (usersyned > sta.getUsersyned()) {
				sta.setUsersyned(usersyned);
			}
			sta.setUsersaved(usersaved);

			sta.setVored(vored);

			audao.Update(sta);

		}

		for (int i = 0; i < 4; i++) {

			switch (i) {
			case 0:
				sys = "web";
				break;
			case 1:
				sys = "android";
				break;
			case 2:
				sys = "ios";
				break;
			case 3:
				sys = "syn";
				break;

			default:
				sys = null;
				break;
			}
			if (sys == null) {
				continue;
			}

			Stas sta = audao.StasTimeDay(time, sys, "all");

			usersaved = userdao.getSaveTime(time, sys).size();

			vored = audao.VisitorTime(time, sys).size();
			

			synlogin = userdao.getFinaltime(time, sys).size();

			if (synlogin != 0) {
				if (synlogin % 3 == 0) {
					usersyned = synlogin / 3;
				} else {
					usersyned = synlogin / 3 + 1;
				}
			} else {
				usersyned = 0;
			}
			if (sta == null) {
				sta = new Stas();
				sta.setProvince(province);
				sta.setTime(time);
				sta.setUserlogined(synlogin);
				if (downloaded != 0 && sys.equals(os)) {
					sta.setDownloaded(1);
				} else {
					sta.setDownloaded(0);
				}
				sta.setOs(sys);
				sta.setUsersyned(usersyned);
				sta.setUsersaved(usersaved);
				sta.setVored(vored);
				audao.Save(sta);
				continue;
			}
			sta = audao.StasTimeDay(time, sys, "all");

			sta.setUserlogined(synlogin);
			if (downloaded != 0 && sys.equals(os)) {
				sta.setDownloaded(sta.getDownloaded() + 1);
			}

			sta.setUsersyned(usersyned);

			sta.setUsersaved(usersaved);

			sta.setVored(vored);

			audao.Update(sta);

		}

	}


	public void visitorMac() throws IOException {
		if (Mac == null) {
			util.Out().print(false);
			return;
		}
		vor = audao.sVisitor(Mac);
		if(os==null){
			util.Out().print("null");
			return;
		}
		if (os.equals("android")) {
			os = "android";
		} else if (os.equals("ios")) {
			os = "ios";
		} else {

			os = null;
			util.Out().print("null");
			return;
		}

		if (vor == null) {
		
			
			vor = new Visitor();
			vor.setAppmac(Mac);
			vor.setNickname(Mac);
			vor.setOs(os);
			vor.setProvince(province);
			vor.setTime(util.getNowTime());
			audao.Save(vor);
			String url = "http://127.0.0.1/PerfectBefriend/aStas?os=" + os
					+ "&province="+province;
			WechatKit.sendGet(url);
			util.Out().print(true);
			return;
		}
		util.Out().print(false);
	}


	public void JztdAppm() throws IOException {
		au = audao
				.select("http://182.92.100.235/PerfectBefriend/AppUp/FamilyGroup.apk");
		if (au != null) {
			System.out.println(apptv);
			System.out.println(updates);
			au.setApptv(apptv);
			au.setTime(OpeFunction.getNowTime());
			au.setUpdates(updates);

			if (au.getUpnum() <= 0) {
				au.setUpnum(0);
			}

			audao.Update(au);
			OpeFunction.Out().print("�޸ĳɹ�");
		} else {
			OpeFunction.Out().print("�޸�ʧ��");
		}
	}

	/**
	 * ���ڼҳ���ؿͻ��˵ĸ��� ���� ���� ɾ��ԭ���� ���µ� ��������Ǻ㶨�� FamilyGroup.apk
	 * 
	 * @throws IOException
	 */

	public void JztdApp() throws IOException {
		try {

			if (appFile == null) {
				OpeFunction.Out().print("FamilyGroup.apkΪnull");
				return;
			}
			if (!appFileFileName.equals("FamilyGroup.apk")) {
				OpeFunction.Out().print("���ֱ���Ϊ'FamilyGroup.apk'");
				return;
			}
			System.out.println(appFileFileName);
			au = audao
					.select("http://182.92.100.235/PerfectBefriend/AppUp/FamilyGroup.apk");
			Boolean b = false;
			// ���ڿ� ˵�� û�� �Ͳ���Ҫɾ��
			if (au != null) {
				File file1 = new File(ServletActionContext.getServletContext()
						.getRealPath("/AppUp/FamilyGroup.apk"));
				b = file1.delete();
				audao.Remove(au);
			}

			savePath = "AppUp";
			AppUp ap = new AppUp();

			String upth = "http://182.92.100.235/PerfectBefriend/"
					+ OpeFunction.fileToServer(savePath, appFile,
							appFileFileName, appFileContentType, false);
			// ����һ��
			savePath = "AppUp/Past";
			OpeFunction.fileToServer(savePath, appFile, appFileFileName,
					appFileContentType, true);
			ap.setApptv(downloads);
			ap.setTime(OpeFunction.getNowTime());
			ap.setUpdates(type);
			ap.setUpnum(0);
			ap.setPath(upth);
			audao.Save(ap);

			OpeFunction.Out().print("���³ɹ� ɾ���ɰ汾�ɹ�?" + b);
		} catch (Exception e) {
			OpeFunction.Out().print(e.getMessage());
		}

	}

	/**
	 * �����ж� ���� �ҳ���� ����Դ�� ��׿ ���� ƻ�� ��׿����Ӧ�ñ�
	 *
	 * @throws IOException
	 */
	public void weixinJzFoIosAndAndroid() throws IOException {
		boolean b = false;
		try {

			System.out.println("����html");
			HttpServletRequest request = OpeFunction.request();
			HttpServletResponse response = OpeFunction.response();
			String sUA = request.getHeader("user-agent");
			System.out.println(sUA);
			// pc�汾����
			List<String> al = new ArrayList<String>();

			al.add("Mac OS X");

			for (int i = 0; i < al.size(); i++) {
				if (sUA.indexOf(al.get(i)) != -1) {
					b = true;
					System.out.println("��ʲôλ�ó��ֵ�!" + sUA.indexOf(al.get(i)));
					break;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		if (b == true) {
			System.out.println("Mac OS X");
			String url = "http://127.0.0.1/PerfectBefriend/aStas?os=" + "ios"
					+ "&downloaded=1";
			WechatKit.sendGet(url);
			// ת��
			((HttpServletResponse) util.response())
					.sendRedirect("https://itunes.apple.com/cn/app/jia-zhang-zhi-you/id995642623?mt=8");

		} else {
			// ת��
			System.out.println("������");
			String url = "http://127.0.0.1/PerfectBefriend/aStas?os=" + "android"
					+ "&downloaded=1";
			WechatKit.sendGet(url);
			((HttpServletResponse) util.response())
					.sendRedirect("http://fusion.qq.com/cgi-bin/qzapps/unified_jump?appid=11679248&from=singlemessage&isTimeline=false&actionFlag=0&params=pname%3Dcn.com.parentsfriend%26versioncode%3D39%26channelid%3D%26actionflag%3D0&isappinstalled=1");

		}

	}

	/**
	 * �����ж� ���� �ҳ���� ����Դ�� ��׿ ���� ƻ��
	 *
	 * @throws IOException
	 */
	public void JzFoIosAndAndroid() throws IOException {
		boolean b = false;
		try {

			System.out.println("����html");
			HttpServletRequest request = OpeFunction.request();
			HttpServletResponse response = OpeFunction.response();
			String sUA = request.getHeader("user-agent");
			System.out.println(sUA);
			// pc�汾����
			List<String> al = new ArrayList<String>();

			al.add("Mac OS X");

			for (int i = 0; i < al.size(); i++) {
				if (sUA.indexOf(al.get(i)) != -1) {
					b = true;
					System.out.println("��ʲôλ�ó��ֵ�!" + sUA.indexOf(al.get(i)));
					break;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		if (b == true) {
			/**
			 * ��¼ÿ�����ش���ios
			 */
			String url = "http://127.0.0.1/PerfectBefriend/aStas?os=" + "ios"
					+ "&downloaded=1";
			WechatKit.sendGet(url);
			System.out.println("Mac OS X");
			// ת��
			((HttpServletResponse) util.response())
					.sendRedirect("https://itunes.apple.com/cn/app/jia-zhang-zhi-you/id995642623?mt=8");

		} else {
			/**
			 * ��¼ÿ�����ش���Android
			 */
			String url = "http://127.0.0.1/PerfectBefriend/aStas?os=" + "android"
					+ "&downloaded=1";
			WechatKit.sendGet(url);

			// ת��
			System.out.println("������");
			((HttpServletResponse) util.response())
					.sendRedirect("http://182.92.100.235/PerfectBefriend/AppUp/FamilyGroup.apk");

		}

	}

	/**
	 * �����ж� ���� �ҳ���� ����Դ�� �ֻ� ���� pc
	 *
	 * @throws IOException
	 */
	public void Jwebandwap() throws IOException {
		try {

			System.out.println("����html");
			HttpServletRequest request = OpeFunction.request();
			HttpServletResponse response = OpeFunction.response();
			String sUA = request.getHeader("user-agent");
			System.out.println(sUA);
			// pc�汾����
			List<String> al = new ArrayList<String>();
			al.add("Windows 98");
			al.add("Windows ME");
			al.add("Windows 2000");
			al.add("Windows XP");
			al.add("Windows NT");
			al.add("Ubuntu");
			al.add("Mac OS X");

			boolean b = false;
			for (int i = 0; i < al.size(); i++) {
				if (sUA.indexOf(al.get(i)) != -1
						&& sUA.indexOf(al.get(i)) == 13) {
					b = true;
					System.out
							.println("�ж� 13λ�� ʲôϵͳ!" + sUA.indexOf(al.get(i)));
					break;
				}
			}

			if (b == true) {
				System.out.println("PC");

				request.getRequestDispatcher("/webjztd.html").forward(request,
						response);
			} else {
				System.out.println("phone");

				request.getRequestDispatcher("/iwebjztd.html").forward(request,
						response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 8���Ƽ� app �Ĺ��� ��ʾ
	 * 
	 * @return
	 */
	public String Appset() {
		pageSize = 4;

		System.out.println("currentPage:" + currentPage);
		if (currentPage < 1) {
			currentPage = 1;
		}
		int n = adao.ALL().size();
		if (n % 4 == 0) {
			n = n / 4;
		} else {
			n = n / 4 + 1;
		}
		if (currentPage >= n) {
			currentPage = n;
		}
		List<App> l = adao.FAll(currentPage, pageSize);
		Map session = (Map) ActionContext.getContext().get("session");
		session.put("l", l);
		session.put("n", n);
		session.put("currentPage", currentPage);
		return Action.SUCCESS;
	}

	/**
	 * ɾ��app ͨ��id
	 * 
	 * @throws IOException
	 */
	public void Deleteapp() throws IOException {
		try {

			System.out.println("id:" + id);
			app = adao.byid(id);
			if (app == null) {
				util.Out().print("ɾ��ʧ�� û��Ҫɾ����app��");
				return;
			}
			System.out.println("app" + app);
			if (app == null) {
				OpeFunction.Out().print("null");
				return;
			}
			System.out.println(app.getPathapk());

			File file = new File(ServletActionContext.getServletContext()
					.getRealPath(app.getPathapk()));
			file.delete();

			System.out.println(app.getPathimg());
			File fil = new File(ServletActionContext.getServletContext()
					.getRealPath(app.getPathimg()));
			fil.delete();
			adao.remove(app);
			OpeFunction.Out().print(true);
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/Appset");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * ͳ��app���ش���
	 */
	public void AppDs() {

		app = adao.byid(id);
		int iz = app.getRealds() + 1;
		int i = app.getDownloads() + 1;
		System.out.println(app.getName() + "��ʵ���ش�����" + iz);
		System.out.println(app.getName() + "���ش�����" + i);
		app.setRealds(iz);
		app.setDownloads(i);
		adao.Ds(app);

	}

	/**
	 * �жϼҳ�����Ƿ� �и���
	 * 
	 * @throws IOException
	 */
	public void Appup() throws IOException {
		try {
			au = audao.UP();

			System.out.println("������Appup ������" + au.getUpdates());
			OpeFunction.Out().print(OpeFunction.ToJson(au));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * �������ʱʹ�� ������´���
	 * 
	 * @throws IOException
	 */
	public void Appupnum() throws IOException {
		try {
			au = audao.UP();
			num = au.getUpnum();
			if (num > 0) {
				num = ++num;
				au.setUpnum(num);
			} else {
				au.setUpnum(1);
			}
			audao.Update(au);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * �û���� ����
	 */
	public void SaveFeedback() {
		try {

			System.out.println("������  SaveFeedback" + "," + username + ","
					+ information);
			User u = userdao.byUsernameAccnumnoPhone(username);
			if (u == null) {
				OpeFunction.Out().print(false);
				System.out.println("�û�������!");
				return;
			}
			Feedback f = new Feedback();
			f.setUsername(username);
			f.setInformation(information);
			f.setTime(OpeFunction.getNowTime());
			audao.Save(f);
			String Email = "781369549@qq.com";
			String sg = u.getPhone() + " �û��ķ���:" + information;
			Email = "user@jiazhangtd.net";
			OpeFunction.Email(Email, sg);
			OpeFunction.Out().print(true);
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
	}

	/**
	 * �ϴ� �Ƽ� app
	 */
	public void AUP() throws IOException {

		System.out.println(vnum);
		System.out.println(type);
		System.out.println(summary);
		System.out.println(name);
		System.out.println(imgFile);
		System.out.println(imgFile1);
		System.out.println(imgFile2);
		System.out.println(imgFile3);
		System.out.println(appFile);
		System.out.println(imgFile1FileName);
		System.out.println(imgFile2FileName);
		System.out.println(imgFile3FileName);
		savePath = "/appimg";
		if (imgFile == null) {
			OpeFunction.Out().print("logoNULL");
			return;
		}
		if (appFile == null) {
			OpeFunction.Out().print("appNULL");
			return;
		}
		if (imgFile1 == null) {
			OpeFunction.Out().print("Ӧ�ý�ͼ1NULL");
			return;
		}
		if (imgFile2 == null) {
			OpeFunction.Out().print("Ӧ�ý�ͼ2NULL");
			return;
		}
		if (imgFile3 == null) {
			OpeFunction.Out().print("Ӧ�ý�ͼ3NULL");
			return;
		}

		String img = OpeFunction.fileToServer(savePath, imgFile,
				imgFileFileName, imgFileContentType, true);

		String img1 = OpeFunction.fileToServer(savePath, imgFile1,
				imgFile1FileName, imgFile1ContentType, true);
		String img2 = OpeFunction.fileToServer(savePath, imgFile2,
				imgFile2FileName, imgFile2ContentType, true);
		String img3 = OpeFunction.fileToServer(savePath, imgFile3,
				imgFile3FileName, imgFile3ContentType, true);

		savePath = "/app";

		String apk = OpeFunction.fileToServer(savePath, appFile,
				appFileFileName, appFileContentType, false);
		float fl = 0;
		fl = OpeFunction.fileSizem(appFile);
		System.out.println("app��С" + fl);
		App app = new App();
		app.setName(name);
		app.setPathapk(apk);
		app.setPathimg(img);

		app.setPathimg1(img1);
		app.setPathimg2(img2);
		app.setPathimg3(img3);

		app.setAppsize(fl);
		app.setVnum(vnum);
		app.setType(type);
		app.setRealds(0);

		if (downloads <= 0) {
			downloads = 0;

			System.out.println("downloads" + downloads);
		}
		app.setDownloads(downloads);

		if (num <= 0) {
			num = 0;
			System.out.println("num" + num);

		}
		app.setSequence(num);
		app.setTime(OpeFunction.getNowTime());
		app.setSummary(summary);
		app.setDpt(dpt);
		adao.Save(app);

		OpeFunction.Out().print(true);
		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/Appset");

	}

	/**
	 * ͨ��id��ѯapp��Ϣ
	 * 
	 * @throws IOException
	 */
	public void getbyidapp() throws IOException {
		if (id == 0) {
			OpeFunction.Out().print(OpeFunction.ToJson("idnull"));
			return;
		}
		app = adao.byid(id);
		if (app != null) {
			OpeFunction.Out().print(OpeFunction.ToJson(app));
		} else {
			OpeFunction.Out().print(OpeFunction.ToJson("null"));
		}
	}

	/**
	 * ���ظ� �ͻ��� ��8��app
	 */
	public void getapp() {
		try {
			// num����0 ����Ĭ��ֵ
			if (num == 0) {
				num = 8;
			}

			List<App> a = adao.All(num);
			OpeFunction.Out().print(OpeFunction.ToJson(a));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * ���ظ� web���� ��n��app
	 */
	public String webGetapp() {
		try {
			// num����0 ����Ĭ��ֵ
			if (num == 0) {
				num = 8;
			}
			// ��ѯapp��Ϣ
			List<App> la = adao.All(0);
			request.setAttribute("la", la);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Action.SUCCESS;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}

	public String getImgFileContentType() {
		return imgFileContentType;
	}

	public void setImgFileContentType(String imgFileContentType) {
		this.imgFileContentType = imgFileContentType;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getAppFileFileName() {
		return appFileFileName;
	}

	public void setAppFileFileName(String appFileFileName) {
		this.appFileFileName = appFileFileName;
	}

	public String getAppFileContentType() {
		return appFileContentType;
	}

	public void setAppFileContentType(String appFileContentType) {
		this.appFileContentType = appFileContentType;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public File getAppFile() {
		return appFile;
	}

	public void setAppFile(File appFile) {
		this.appFile = appFile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public ApputilAction(ApputilDAO audao, AppDAO adao, UserDAO userdao) {
		super();
		this.audao = audao;
		this.adao = adao;
		this.userdao = userdao;
	}

	public int getDownloads() {
		return downloads;
	}

	public void setDownloads(int downloads) {
		this.downloads = downloads;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getApptv() {
		return apptv;
	}

	public void setApptv(int apptv) {
		this.apptv = apptv;
	}

	public String getUpdates() {
		return updates;
	}

	public void setUpdates(String updates) {
		this.updates = updates;
	}

	public File getImgFile1() {
		return imgFile1;
	}

	public void setImgFile1(File imgFile1) {
		this.imgFile1 = imgFile1;
	}

	public File getImgFile2() {
		return imgFile2;
	}

	public void setImgFile2(File imgFile2) {
		this.imgFile2 = imgFile2;
	}

	public File getImgFile3() {
		return imgFile3;
	}

	public void setImgFile3(File imgFile3) {
		this.imgFile3 = imgFile3;
	}

	public String getImgFile1FileName() {
		return imgFile1FileName;
	}

	public void setImgFile1FileName(String imgFile1FileName) {
		this.imgFile1FileName = imgFile1FileName;
	}

	public String getImgFile1ContentType() {
		return imgFile1ContentType;
	}

	public void setImgFile1ContentType(String imgFile1ContentType) {
		this.imgFile1ContentType = imgFile1ContentType;
	}

	public String getImgFile2FileName() {
		return imgFile2FileName;
	}

	public void setImgFile2FileName(String imgFile2FileName) {
		this.imgFile2FileName = imgFile2FileName;
	}

	public String getImgFile2ContentType() {
		return imgFile2ContentType;
	}

	public void setImgFile2ContentType(String imgFile2ContentType) {
		this.imgFile2ContentType = imgFile2ContentType;
	}

	public String getImgFile3FileName() {
		return imgFile3FileName;
	}

	public void setImgFile3FileName(String imgFile3FileName) {
		this.imgFile3FileName = imgFile3FileName;
	}

	public String getImgFile3ContentType() {
		return imgFile3ContentType;
	}

	public void setImgFile3ContentType(String imgFile3ContentType) {
		this.imgFile3ContentType = imgFile3ContentType;
	}

	public String getVnum() {
		return vnum;
	}

	public void setVnum(String vnum) {
		this.vnum = vnum;
	}

	public String getDpt() {
		return dpt;
	}

	public void setDpt(String dpt) {
		this.dpt = dpt;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getMac() {
		return Mac;
	}

	public void setMac(String mac) {
		Mac = mac;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public int getSynlogin() {
		return synlogin;
	}

	public void setSynlogin(int synlogin) {
		this.synlogin = synlogin;
	}

	public int getDownloaded() {
		return downloaded;
	}

	public void setDownloaded(int downloaded) {
		this.downloaded = downloaded;
	}

	public int getUsersyned() {
		return usersyned;
	}

	public void setUsersyned(int usersyned) {
		this.usersyned = usersyned;
	}

	public int getVored() {
		return vored;
	}

	public void setVored(int vored) {
		this.vored = vored;
	}

	public int getUsersaved() {
		return usersaved;
	}

	public void setUsersaved(int usersaved) {
		this.usersaved = usersaved;
	}

}
