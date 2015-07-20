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

public class ApputilAction {
	public OpeFunction util;// 工具类
	private ApputilDAO audao;// ApputilDAO
	private UserDAO userdao;// 用户dao
	private AppDAO adao;// appdao
	private String username;// 用户名
	private String information;//

	private File imgFile;// logo图片
	private String imgFileFileName;// 文件名
	private String imgFileContentType;// 文件类型

	private File imgFile1;// 应用截图1
	private String imgFile1FileName;// 文件名
	private String imgFile1ContentType;// 文件类型

	private File imgFile2;// 应用截图2
	private String imgFile2FileName;// 文件名
	private String imgFile2ContentType;// 文件类型

	private File imgFile3;// 应用截图3
	private String imgFile3FileName;// 文件名
	private String imgFile3ContentType;// 文件类型

	private String savePath;// 目录

	private File appFile;// app文件
	private String appFileFileName;// 文件名
	private String appFileContentType;// 文件类型

	private String vnum;// app版本号
	private String type;// app类别
	private String dpt;// 应用描述
	private String summary;// 应用概要

	private String name;// app名字
	private int num = 0;// app推荐指数

	private int currentPage = 1;// 页数
	private int id = 0;// id
	private int pageSize = 4;// 行数

	private int downloads = 0;// app下载次数

	private int apptv = 0;// 版本号
	private String updates;// 更新内容

	AppUp au = new AppUp();// appup实体类 家长之友更新用的类
	App app = new App();// app实体类
	Visitor vor = new Visitor();// 游客类
	public HttpServletRequest request = ServletActionContext.getRequest();// 获取request
	private String Mac;//
	private String os;// 系统
	private String time = util.getNowTime();
	private String province = "all";// 省级
	private int synlogin;// 登入次数
	private int downloaded;// 下载次数
	private int usersyned;// 同时在线数
	private int vored;// 当日新增游客量
	private int usersaved;// 当日注册人数

	// 当日注册人数
	/**
	 * 记录统计变化 初始化
	 */
	public void aStas() {
		System.out.println("province"+province);
		// 更新在线用户数量
		time = util.getNumTime(0);
	
		
		// 初始化3大系统统计量
		String sys = "";
		for (int i = 0; i < 4; i++) {
			if(province.equals("all")||province==null||province.equals("null")){
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
			Stas sta = audao.StasTimeDay(time, sys,province);
			// 获取当天注册用户数量
			usersaved = userdao.getSaveTime(time, sys,province).size();
			//获取当日新增游客
			vored=audao.VisitorTime(time, sys,province).size();
			System.out.println("11111111111111111"+vored+province+sys);
			// 获取当天登入的用户数量
			synlogin = userdao.getFinaltime(time, sys,province).size();
			if(synlogin!=0){
				if(synlogin%3==0){
					usersyned=synlogin/3;
				}else{
					usersyned=synlogin/3+1;
				}
				}else{
					usersyned=0;
				}
			// 获取在线用户
			//usersyned = userdao.getOnline(sys,province).size();
			if (sta == null) {
				sta = new Stas();
				sta.setProvince(province);// 地区
				sta.setTime(time);// 统计时间
				sta.setUserlogined(synlogin);// 登入次数
				if(downloads!=0){
				sta.setDownloaded(1);
				}else{
					sta.setDownloaded(0);
				}// 下载数量
				sta.setOs(sys);// 来自的系统
				sta.setUsersyned(usersyned);// 同时在线数
				sta.setUsersaved(usersaved);// 当日注册人数				
				sta.setVored(vored);// 当日新增游客量
				
				audao.Save(sta);
				continue;
			}
			sta = audao.StasTimeDay(time, sys,province);
			
			if (sta==null) {
				continue;
			}
			// sta.setProvince(province);//统计全部
			// sta.setTime(time);// 统计时间

			sta.setUserlogined(synlogin);// 登入次数
			if (downloaded != 0&&sys.equals(os)) {
				sta.setDownloaded(sta.getDownloaded() + 1);// 下载数量
			}
			// sta.setOs(os);//来自的系统
			if (usersyned > sta.getUsersyned()) {
				sta.setUsersyned(usersyned);// 同时在线数
			}
			sta.setUsersaved(usersaved);// 当日注册人数
			
			sta.setVored(vored);// 当日新增游客量
			
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
		
			Stas sta = audao.StasTimeDay(time, sys,"all");
			// 获取当天注册用户数量
			usersaved = userdao.getSaveTime(time, sys).size();
			System.out.println("当天注册量"+usersaved+sys);
			//获取当日新增游客
			vored=audao.VisitorTime(time, sys).size();
			System.out.println(vored+"vored"+sys+time);
			// 获取当天登入的用户数量
			synlogin = userdao.getFinaltime(time, sys).size();
			// 获取在线用户
			//usersyned = userdao.getOnline(sys).size();
			if(synlogin!=0){
			if(synlogin%3==0){
				usersyned=synlogin/3;
			}else{
				usersyned=synlogin/3+1;
			}
			}else{
				usersyned=0;
			}
			if (sta == null) {
				sta = new Stas();
				sta.setProvince(province);// 地区
				sta.setTime(time);// 统计时间
				sta.setUserlogined(synlogin);// 登入次数
				if (downloaded != 0&&sys.equals(os)) {
					sta.setDownloaded(1);// 下载数量
				}else{
					sta.setDownloaded(0);// 下载数量
				}
				sta.setOs(sys);// 来自的系统
				sta.setUsersyned(usersyned);// 同时在线数
				sta.setUsersaved(usersaved);// 当日注册人数
				sta.setVored(vored);// 当日新增游客量
				audao.Save(sta);
				continue;
			}
			sta = audao.StasTimeDay(time, sys,"all");			
			// sta.setProvince(province);//统计全部
			// sta.setTime(time);// 统计时间

			sta.setUserlogined(synlogin);// 登入次数
			if (downloaded != 0&&sys.equals(os)) {
				sta.setDownloaded(sta.getDownloaded() + 1);// 下载数量
			}
			// sta.setOs(os);//来自的系统
			
				sta.setUsersyned(usersyned);// 同时在线数
			
			sta.setUsersaved(usersaved);// 当日注册人数
			
			
			sta.setVored(vored);// 当日新增游客量
			
			audao.Update(sta);

		}

	}

	/**
	 * 记录新增 游客 appmac
	 */
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
			/**
			 * 记录每天新增游客
			 */
			
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

	/**
	 * 修改版本号 或者 停止更新使用
	 * 
	 * @throws IOException
	 */
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
			OpeFunction.Out().print("修改成功");
		} else {
			OpeFunction.Out().print("修改失败");
		}
	}

	/**
	 * 用于家长天地客户端的更新 更新 就是 删除原来的 传新的 这个命名是恒定的 FamilyGroup.apk
	 * 
	 * @throws IOException
	 */

	public void JztdApp() throws IOException {
		try {

			if (appFile == null) {
				OpeFunction.Out().print("FamilyGroup.apk为null");
				return;
			}
			if (!appFileFileName.equals("FamilyGroup.apk")) {
				OpeFunction.Out().print("名字必须为'FamilyGroup.apk'");
				return;
			}
			System.out.println(appFileFileName);
			au = audao
					.select("http://182.92.100.235/PerfectBefriend/AppUp/FamilyGroup.apk");
			Boolean b = false;
			// 等于空 说明 没有 就不需要删除
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
			// 备份一份
			savePath = "AppUp/Past";
			OpeFunction.fileToServer(savePath, appFile, appFileFileName,
					appFileContentType, true);
			ap.setApptv(downloads);
			ap.setTime(OpeFunction.getNowTime());
			ap.setUpdates(type);
			ap.setUpnum(0);
			ap.setPath(upth);
			audao.Save(ap);

			OpeFunction.Out().print("更新成功 删除旧版本成功?" + b);
		} catch (Exception e) {
			OpeFunction.Out().print(e.getMessage());
		}

	}

	/**
	 * 用于判断 访问 家长天地 是来源于 安卓 还是 苹果 安卓跳到应用宝
	 *
	 * @throws IOException
	 */
	public void weixinJzFoIosAndAndroid() throws IOException {
		boolean b = false;
		try {

			System.out.println("进入html");
			HttpServletRequest request = OpeFunction.request();
			HttpServletResponse response = OpeFunction.response();
			String sUA = request.getHeader("user-agent");
			System.out.println(sUA);
			// pc版本代号
			List<String> al = new ArrayList<String>();

			al.add("Mac OS X");

			for (int i = 0; i < al.size(); i++) {
				if (sUA.indexOf(al.get(i)) != -1) {
					b = true;
					System.out.println("在什么位置出现的!" + sUA.indexOf(al.get(i)));
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
			// 转发
			((HttpServletResponse) util.response())
					.sendRedirect("https://itunes.apple.com/cn/app/jia-zhang-zhi-you/id995642623?mt=8");

		} else {
			// 转发
			System.out.println("是其他");
			String url = "http://127.0.0.1/PerfectBefriend/aStas?os=" + "android"
					+ "&downloaded=1";
			WechatKit.sendGet(url);
			((HttpServletResponse) util.response())
					.sendRedirect("http://fusion.qq.com/cgi-bin/qzapps/unified_jump?appid=11679248&from=singlemessage&isTimeline=false&actionFlag=0&params=pname%3Dcn.com.parentsfriend%26versioncode%3D39%26channelid%3D%26actionflag%3D0&isappinstalled=1");

		}

	}

	/**
	 * 用于判断 访问 家长天地 是来源于 安卓 还是 苹果
	 *
	 * @throws IOException
	 */
	public void JzFoIosAndAndroid() throws IOException {
		boolean b = false;
		try {

			System.out.println("进入html");
			HttpServletRequest request = OpeFunction.request();
			HttpServletResponse response = OpeFunction.response();
			String sUA = request.getHeader("user-agent");
			System.out.println(sUA);
			// pc版本代号
			List<String> al = new ArrayList<String>();

			al.add("Mac OS X");

			for (int i = 0; i < al.size(); i++) {
				if (sUA.indexOf(al.get(i)) != -1) {
					b = true;
					System.out.println("在什么位置出现的!" + sUA.indexOf(al.get(i)));
					break;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		if (b == true) {
			/**
			 * 记录每天下载次数ios
			 */
			String url = "http://127.0.0.1/PerfectBefriend/aStas?os=" + "ios"
					+ "&downloaded=1";
			WechatKit.sendGet(url);
			System.out.println("Mac OS X");
			// 转发
			((HttpServletResponse) util.response())
					.sendRedirect("https://itunes.apple.com/cn/app/jia-zhang-zhi-you/id995642623?mt=8");

		} else {
			/**
			 * 记录每天下载次数Android
			 */
			String url = "http://127.0.0.1/PerfectBefriend/aStas?os=" + "android"
					+ "&downloaded=1";
			WechatKit.sendGet(url);

			// 转发
			System.out.println("是其他");
			((HttpServletResponse) util.response())
					.sendRedirect("http://182.92.100.235/PerfectBefriend/AppUp/FamilyGroup.apk");

		}

	}

	/**
	 * 用于判断 访问 家长天地 是来源于 手机 还是 pc
	 *
	 * @throws IOException
	 */
	public void Jwebandwap() throws IOException {
		try {

			System.out.println("进入html");
			HttpServletRequest request = OpeFunction.request();
			HttpServletResponse response = OpeFunction.response();
			String sUA = request.getHeader("user-agent");
			System.out.println(sUA);
			// pc版本代号
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
							.println("判断 13位是 什么系统!" + sUA.indexOf(al.get(i)));
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
	 * 8个推荐 app 的管理 显示
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
	 * 删除app 通过id
	 * 
	 * @throws IOException
	 */
	public void Deleteapp() throws IOException {
		try {

			System.out.println("id:" + id);
			app = adao.byid(id);
			if (app == null) {
				util.Out().print("删除失败 没有要删除的app！");
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
	 * 统计app下载次数
	 */
	public void AppDs() {

		app = adao.byid(id);
		int iz = app.getRealds() + 1;
		int i = app.getDownloads() + 1;
		System.out.println(app.getName() + "真实下载次数是" + iz);
		System.out.println(app.getName() + "下载次数是" + i);
		app.setRealds(iz);
		app.setDownloads(i);
		adao.Ds(app);

	}

	/**
	 * 判断家长天地是否 有更新
	 * 
	 * @throws IOException
	 */
	public void Appup() throws IOException {
		try {
			au = audao.UP();

			System.out.println("进入了Appup 更新了" + au.getUpdates());
			OpeFunction.Out().print(OpeFunction.ToJson(au));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 点击更新时使用 计算更新次数
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
	 * 用户添加 反馈
	 */
	public void SaveFeedback() {
		try {

			System.out.println("进入了  SaveFeedback" + "," + username + ","
					+ information);
			User u = userdao.byUsernameAccnumnoPhone(username);
			if (u == null) {
				OpeFunction.Out().print(false);
				System.out.println("用户不存在!");
				return;
			}
			Feedback f = new Feedback();
			f.setUsername(username);
			f.setInformation(information);
			f.setTime(OpeFunction.getNowTime());
			audao.Save(f);
			String Email = "781369549@qq.com";
			String sg = u.getPhone() + " 用户的反馈:" + information;
			Email = "user@jiazhangtd.net";
			OpeFunction.Email(Email, sg);
			OpeFunction.Out().print(true);
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
	}

	/**
	 * 上传 推荐 app
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
			OpeFunction.Out().print("应用截图1NULL");
			return;
		}
		if (imgFile2 == null) {
			OpeFunction.Out().print("应用截图2NULL");
			return;
		}
		if (imgFile3 == null) {
			OpeFunction.Out().print("应用截图3NULL");
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
		System.out.println("app大小" + fl);
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
	 * 通过id查询app信息
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
	 * 返回给 客户端 的8个app
	 */
	public void getapp() {
		try {
			// num等于0 启用默认值
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
	 * 返回给 web户端 的n个app
	 */
	public String webGetapp() {
		try {
			// num等于0 启用默认值
			if (num == 0) {
				num = 8;
			}
			// 查询app信息
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
