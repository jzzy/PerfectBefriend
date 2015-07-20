package com.befriend.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.befriend.dao.FollectDAO;
import com.befriend.dao.ForumDAO;
import com.befriend.dao.UserDAO;
import com.befriend.entity.Admin;
import com.befriend.entity.Follect;
import com.befriend.entity.ForumOne;
import com.befriend.entity.ForumThree;
import com.befriend.entity.ForumTwo;
import com.befriend.entity.User;
import com.befriend.util.OpeFunction;
import com.opensymphony.xwork2.Action;

public class ForumAction {
	private OpeFunction util;// 自建工具类
	// Map session = (Map) util.getsession();// 创建 sessionmap
	private UserDAO userdao;// 用户dao
	private ForumDAO forumdao;// 论坛dao
	private FollectDAO foldao;// 论坛收藏dao
	List<User> us = new ArrayList<User>();
	Follect fo = new Follect();// 论坛收藏类
	ForumOne fone = new ForumOne();// 论坛主类
	ForumTwo ftwo = new ForumTwo();// 论坛主页类
	ForumThree fehree = new ForumThree();// 论坛回复类
	List<Follect> fos = new ArrayList<Follect>();// 论坛收藏类List
	List<ForumOne> fones = new ArrayList<ForumOne>();// 论坛主类List
	List<ForumTwo> ftwos = new ArrayList<ForumTwo>();// 论坛主类List
	List<ForumThree> fehrees = new ArrayList<ForumThree>();// 论坛主类List
	private HttpSession session = ServletActionContext.getRequest()
			.getSession();// 获取 HttpSession
	public HttpServletRequest request = ServletActionContext.getRequest();// 获取request
	/**
	 * 论坛主类字段
	 */
	private int id = 0;// id
	/**
	 * 论坛类别 1专家答疑 2学前 3小学 4中学
	 */
	private int model = 0;
	private String time = util.getNowTime();// 时间

	private int total = 0;// 论坛总回复数
	private int userid = 0;// 论坛创建者
	private String content;// 论坛内容
	private String title;// 论坛标题
	private String area;// 论坛地区省级
	private String areas;// 论坛地区市级
	private String img;// 论坛图片
	public File file;// 论坛图片文件
	private String fileFileName;// 文件名
	private String fileContentType;// 文件类型

	/**
	 * 论坛回复字段
	 */
	private String reply; // 留言内容
	private int forumid = 0; // 论坛id
	private int touserid = 0;// 楼主id&& three 给谁回复
	private int forumtwoid = 0;// 排序 按楼层id

	private int pageSize = 0;// 每页显示的数据
	private int currentPage = 0;// 当前页数
	private int cpe = 0;// 共有多少页

	/**
	 * 模糊查询 根据title 搜索论坛
	 */
	public void likeTitle() throws IOException {

		System.out.println("likeTitle title:" + title);

		cpe = forumdao.likeTitle(title).size();
		if (cpe == 0) {
			System.out.println("没有要搜索的论坛title!");
			util.Out().print("null");
			return;
		}

		List<ForumTwo> ftwosa = new ArrayList<ForumTwo>();
		if (pageSize <= 0) {
			pageSize = 10;
		}

		if (cpe % pageSize == 0) {
			cpe = cpe / pageSize;
		} else {
			cpe = cpe / pageSize + 1;
		}
		if (currentPage <= 0) {
			currentPage = 1;
		}
		if (currentPage >= cpe) {
			currentPage = cpe;
		}
		System.out.println("cpe:" + cpe + "currentPage:" + currentPage
				+ "pageSize:" + pageSize);
		fones = forumdao.likeTitle(pageSize, currentPage, title);
		for (int i = 0; i < fones.size(); i++) {

			User uf = userdao.byid(fones.get(i).getUserid());

			us.add(uf);

			ftwos = forumdao.getForumTwoALL(fones.get(i).getId());

			int g = ftwos.size() - 1;
			System.out.println("g==" + g);
			if (g >= 0) {
				System.out.println("添加了" + i);
				ftwo = ftwos.get(g);

				ftwosa.add(ftwo);
			} else {
				System.out.println("没有添加" + i);
				ftwosa.add(null);
			}

		}
		System.out.println("likeTitle应该相同比例！" + fones.size() + "-"
				+ ftwosa.size() + "-" + us.size());

		String result = "{\"fones\":" + util.ToJson(fones) + ",\"ftwos\":"
				+ util.ToJson(ftwosa) + ",\"us\":" + util.ToJson(us)
				+ ",\"cpe\":" + cpe + "" + ",\"currentPage\":" + currentPage
				+ "}";
		util.Out().print(result);

	}

	/**
	 * 后台web admin删除 回复信息
	 * 
	 * @throws IOException
	 */

	public void webAdminRemoveForumThree() throws IOException {
		System.out.println("进入webProfessorRemoveForumThreeadmin");
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			util.Out().print("请登入!");
			return;
		}
		fehree = forumdao.getForumThree(id);
		if (fehree != null) {
			fone = forumdao.getForumOne(fehree.getForumid());
			forumid = fehree.getForumid();
			forumdao.Remove(fehree);
		} else {
			util.Out().print("没有该信息!");
			return;
		}
		System.out.println("webProfessorRemoveForumThreeadmin forumid"
				+ forumid);

		if (fone != null) {
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/ForumLook?id=" + fone.getId());
		} else {
			util.Out().print("没有获取到论坛");
		}

	}

	/**
	 * 后台web admin 删除评论 以及删除 回复信息
	 * 
	 * @throws IOException
	 */

	public void webAdminRemoveForumTwo() throws IOException {

		System.out.println("进入webProfessorRemoveForumThreeadmin");
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			util.Out().print("请登入!");
			return;
		}

		System.out.println("webAdminRemoveForumTwo id=" + id);

		ftwo = forumdao.getForumTwoid(id);
		if (ftwo != null) {
			try {

				fehrees = forumdao.getForumThreeALL(forumid, ftwo.getId());

				for (int i = 0; i < fehrees.size(); i++) {
					if (fehrees.get(i) != null) {
						forumdao.Remove(fehrees.get(i));
					}

				}

				fone = forumdao.getForumOne(ftwo.getForumid());
				if (fone != null) {
					if (fone.getFrs() >= 1) {
						fone.setFrs((fone.getFrs() - 1));
					} else {
						fone.setFrs(0);
					}
					forumdao.update(fone);

				}

				forumdao.Remove(ftwo);
			} catch (Exception e) {
				util.Out().print("删除异常!" + e.getMessage());
			}

			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/ForumLook?id=" + ftwo.getForumid());

		} else {
			System.out.println("没有该评论");
			util.Out().print("没有该评论");
		}
	}

	/**
	 * 后台web 专家删除 回复信息
	 * 
	 * @throws IOException
	 */

	public void webProfessorRemoveForumThree() throws IOException {

		User user = (User) session.getAttribute("useradmin");
		if (user == null) {
			util.Out().print("用户为空");
			return;
		}
		if (user.getCompetence() != 4) {
			util.Out().print("你不是专家");
			return;
		}

		fehree = forumdao.getForumThree(id);
		if (fehree != null) {
			forumid = fehree.getForumid();
			forumdao.Remove(fehree);
		}

		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/ForumLookiduser?id=" + forumid);
	}

	/**
	 * 后台web 专家删除评论 以及删除 回复信息
	 * 
	 * @throws IOException
	 */

	public void webProfessorRemoveForumTwo() throws IOException {

		User user = (User) session.getAttribute("useradmin");
		if (user == null) {
			util.Out().print("用户为空");
			return;
		}
		if (user != null) {
			if (user.getCompetence() != 4) {
				util.Out().print("你不是专家");
				return;
			}
		}

		System.out.println("webProfessorRemoveForumTwo id=" + id);

		ftwo = forumdao.getForumTwoid(id);
		if (ftwo != null) {
			try {

				fehrees = forumdao.getForumThreeALL(forumid, ftwo.getId());

				for (int i = 0; i < fehrees.size(); i++) {
					if (fehrees.get(i) != null) {
						forumdao.Remove(fehrees.get(i));
					}

				}

				fone = forumdao.getForumOne(ftwo.getForumid());
				if (fone != null) {
					if (fone.getFrs() >= 1) {
						fone.setFrs((fone.getFrs() - 1));
					} else {
						fone.setFrs(0);
					}					
					forumdao.update(fone);

				}
				forumdao.Remove(ftwo);
			} catch (Exception e) {
				util.Out().print("删除异常!" + e.getMessage());
			}

			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath()
					+ "/ForumLookiduser?id="
					+ ftwo.getForumid());

		} else {
			System.out.println("没有该评论");
			util.Out().print("没有该评论");
		}
	}

	/**
	 * 后台web 专家修改回复回复
	 * 
	 * @throws IOException
	 */

	public void webProfessorUpdateForumTwo() throws IOException {
		User user = (User) session.getAttribute("useradmin");
		if (user == null) {
			util.Out().print("用户为空");
			return;
		}
		if (user.getCompetence() != 4) {
			util.Out().print("你不是专家");
			return;
		}
		System.out.println("webProfessorUpdateForumTwo id=" + id);
		ftwo = forumdao.getForumTwoid(id);
		if (ftwo != null) {
			ftwo.setReply(reply);
			forumdao.update(ftwo);
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath()
					+ "/ForumLookiduser?id="
					+ ftwo.getForumid());
		} else {
			System.out.println("没有该评论");
			util.Out().print("没有该评论");
		}
	}

	/**
	 * 后台web 专家回复用户
	 * 
	 * @throws IOException
	 */

	public void webForumtwosaveProfessor() throws IOException {

		System.out.println("webForumtwosaveProfessor forumid=" + forumid);
		User user = (User) session.getAttribute("useradmin");
		if (user == null) {
			util.Out().print("用户为空");
			return;
		}
		if (user.getCompetence() != 4) {
			util.Out().print("你不是专家");
			return;
		}
		fone = forumdao.getForumOne(forumid);
		if (fone == null) {
			util.Out().print("论坛为空");
			return;
		}

		ftwo.setForumid(forumid);
		ftwo.setReply(reply);
		ftwo.setTime(time);
		fone.setTotal(fone.getTotal()+1);
		ftwo.setTouserid(fone.getUserid());
		ftwo.setUserid(user.getId());
		forumdao.save(ftwo);
		fone.setFrs((fone.getFrs() + 1));
		forumdao.update(fone);
		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/ForumLookiduser?id=" + forumid);

	}

	/**
	 * 创建论坛 web端
	 * 
	 * @throws IOException
	 */

	public void webForumonesaveapp() throws IOException {
		System.out.println("进入web添加论坛！");

		User u = (User) session.getAttribute("u");
		if (u == null) {

			System.out.println("请重新登入!");
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/SimulationApp/login.html");
			return;
		}

		userid = u.getId();
		if (userid <= 0) {

			System.out.println("id错误");
			return;

		}

		if (model <= 0) {
			System.out.println("model <= 0");
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/SimulationApp/login.html");
			return;
		}
		area = u.getAddress();
		areas = u.getAddcity();
		System.out.println("添加论坛" + area + areas);

		if (file != null) {

			img = "/IMG/Forumimg/"+userid;
			img = util.ufileToServer(img, file, fileFileName, "jpg", true);
			System.out.println(img);
			fone.setImg(img);

			System.out.println("头像上传成功!");
		}

		fone.setArea(area);
		fone.setAreas(areas);
		fone.setTitle(title);
		fone.setTime(time);
		fone.setTotal(total);
		fone.setType(model);
		fone.setUserid(userid);
		fone.setContent(content);
		forumdao.save(fone);
		System.out.println("添加论坛成功!论坛类型" + model);

		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/webForumApptype?model=" + model + "");
		return;

	}

	/**
	 * web Three 用户回复用户
	 * 
	 * @param forumdao
	 * @throws ServletException
	 */
	public void webForumthreesappadd() throws IOException, ServletException {

		User u = (User) session.getAttribute("u");
		if (u == null) {

			System.out.println("请重新登入!");
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/SimulationApp/login.html");
			return;
		}

		userid = u.getId();
		/**
		 * if(userid==touserid){ System.out.println("阻止了  自己回复自己的评论");
		 * util.Out().print(false); return; }
		 */

		System.out.println("论坛id " + forumid);
		System.out.println("回复内容 " + reply);
		System.out.println("回复给 " + touserid);
		System.out.println("我的id " + userid);
		System.out.println("我的楼层 id" + forumtwoid);
		if (forumid <= 0) {
			util.Out().print(false);
			return;
		}
		if (touserid <= 0) {
			util.Out().print(false);
			return;
		}
		if (userid <= 0) {
			util.Out().print(false);
			return;
		}
		if (forumtwoid <= 0) {
			util.Out().print(false);
			return;
		}
		if (reply == null) {
			util.Out().print(false);
			return;
		}
		fone = forumdao.getForumOne(forumid);
		if (fone == null) {
			util.Out().print(false);
			return;
		}
		if (fone.getType() == 1 && u.getCompetence() != 4) {
			util.Out().print("您没有教授权限！");
			return;
		}
		fehree.setForumid(forumid);
		fehree.setReply(reply);
		fehree.setTime(time);
		fehree.setTouserid(touserid);
		fehree.setUserid(userid);
		fehree.setForumtwoid(forumtwoid);
		forumdao.save(fehree);
		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/webForumLook?id=" + forumid + "");

	}

	/**
	 * web appTwo 添加论坛用户回复楼主
	 * 
	 * @throws IOException
	 */
	public void webForumtwosaveapp() throws IOException {

		User u = (User) session.getAttribute("u");
		if (u == null) {

			System.out.println("请重新登入!");
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/SimulationApp/login.html");
			return;
		}

		userid = u.getId();
		System.out.println("进入web添加论坛回复");
		System.out.println("论坛id" + forumid);
		System.out.println("回复内容" + reply);

		System.out.println("我的id" + userid);

		fone = forumdao.getForumOne(forumid);

		ftwos = forumdao.getForumTwoALL(forumid);
		/**
		 * if(userid==touserid){ System.out.println("阻止了  自己回复自己的评论");
		 * util.Out().print(false); return; }
		 */
		if (userid <= 0) {
			util.Out().print("null");
			return;

		}

		if (fone != null) {
			if (fone.getType() == 1 && u.getCompetence() != 4) {
				util.Out().print("您没有教授权限");
				return;
			}

			touserid = fone.getUserid();
			ftwo.setForumid(forumid);
			ftwo.setReply(reply);

			ftwo.setTime(time);
			ftwo.setTouserid(fone.getUserid());
			ftwo.setUserid(userid);
			int g = fone.getTotal() + 1;
			fone.setTotal(g);		
			forumdao.save(ftwo);
			fone.setFrs((fone.getFrs() + 1));
			forumdao.update(fone);
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/webForumLook?id=" + forumid + "");

			return;
		}
		util.Out().print("null");
		return;

	}

	/**
	 * web 通过用户userid 查询我创建的论坛
	 */
	public String webForumoneTouseid() throws IOException {
		try {

			User u = (User) session.getAttribute("u");
			if (u == null) {

				System.out.println("请重新登入!");
				((HttpServletResponse) util.response()).sendRedirect(request
						.getContextPath() + "/SimulationApp/login.html");
				return null;
			}

			userid = u.getId();
			System.out.println("通过用户userid查询论坛id是:" + userid);
			if (userid <= 0) {
				System.out.println("userid不合法!");
				return null;

			}

			fones = forumdao.getUseridForumOne(userid);
			/**
			 * for (int i = 0; i < fones.size(); i++) {
			 * 
			 * us.add(userdao.byid(fones.get(i).getUserid()));
			 * 
			 * }
			 */
			request.setAttribute("fones", fones);

			/**
			 * if (fones.size() != 0) { System.out.println("正常"); String result
			 * = "{\"fones\":" + util.ToJson(fones) + ",\"us\":" +
			 * util.ToJson(us) + "}"; util.Out().print(result); } else {
			 * System.out.println("空  没有论坛"); util.Out().print("null"); }
			 */
		} catch (Exception e) {
			System.out.println("异常" + e.getMessage());
		}
		return Action.SUCCESS;

	}

	/**
	 * web 用户通过id查看论坛
	 * 
	 * @param forumdao
	 * @throws IOException
	 */
	public String webForumLook() throws IOException {
		try {
			System.out.println("web用户查看论坛");
			System.out.println("论坛id   ==" + id);
			if (id > 0) {
				fone = forumdao.getForumOne(id);

				if (fone == null) {
					util.Out().print("没有这个论坛!");
					System.out.println("没有这个论坛!");
					return null;
				}
				System.out.println("论坛标题:" + fone.getTitle());
				fone.setfHits(fone.getfHits() + 1);

				ftwos = forumdao.getForumTwoALL(id);
				User user = userdao.byid(fone.getUserid());

				forumdao.update(fone);
				List fl = new ArrayList();
				List fu = new ArrayList();
				List fut = new ArrayList();
				System.out.println("评论数" + ftwos.size());

				forumid = id;

				for (int i = 0; i < ftwos.size(); i++) {
					System.out.println(i);

					userid = ftwos.get(i).getUserid();
					forumtwoid = ftwos.get(i).getId();

					User u = userdao.byid(userid);

					us.add(u);
					System.out.println("user" + u.getUsername());

					fehrees = forumdao.getForumThreeALL(forumid, forumtwoid);

					if (fehrees.size() == 0) {
						System.out.println("fehrees==null第" + i + "次循环");

						fl.add(null);
						fut.add(null);
						fu.add(null);
					} else {
						System.out.println("fehrees第" + i + "次循环 信息为"
								+ fehrees.size() + "条");
						List<User> userz = new ArrayList<User>();
						List<User> touser = new ArrayList<User>();

						for (int p = 0; p < fehrees.size(); p++) {

							int uid = fehrees.get(p).getUserid();
							int touid = fehrees.get(p).getTouserid();
							User uz = userdao.byid(uid);

							userz.add(uz);
							User tuz = userdao.byid(touid);

							touser.add(tuz);

						}

						fut.add(touser);

						fu.add(userz);

						fl.add(fehrees);
					}

				}
				System.out.println("threes回复数" + fl.size());

				request.setAttribute("fone", fone);
				request.setAttribute("ftwos", ftwos);
				request.setAttribute("fl", fl);
				request.setAttribute("fu", fu);
				request.setAttribute("fut", fut);
				request.setAttribute("us", us);
				request.setAttribute("u", user);
				System.out.println(user.getUsername());
				System.out.println("------------------------------");

			} else {
				System.out.println("id不正确");
			}

		} catch (Exception e) {
			util.Out().print(e.getMessage());
		}
		return Action.SUCCESS;

	}

	/**
	 * web 根据 类型 查询论坛 论坛
	 * 
	 * @throws IOException
	 */
	public String webForumApptype() throws IOException {

		System.out.println(" web用户按类型 查看全国论坛");
		System.out.println();
		User user = (User) session.getAttribute("u");
		if (user == null) {
			 ((HttpServletResponse) util.response())
			  .sendRedirect(request.getContextPath()+
			  "/SimulationApp/login.html");
			return null;
		}
		if (model <= 0) {
			System.out.println("请重新登入!");
			util.Out().print("model <= 0");
			
			
			 
			return null;
		}
		cpe = forumdao.gettypeForumOneALL(model).size();
		System.out.println("类型type是 ：" + model+user.getAddress()+"有" + cpe + "个论坛");

		if (pageSize <= 0) {
			pageSize = 10;
		}

		if (cpe % pageSize == 0) {
			cpe = cpe / pageSize;
		} else {
			cpe = cpe / pageSize + 1;
		}
		if (currentPage <= 0) {
			currentPage = 1;
		}
		if (currentPage >= cpe) {
			currentPage = cpe;
		}
		System.out.println("currentPage" + currentPage);

		fones = forumdao.getForumOneALL(pageSize, currentPage, model);

		List<ForumTwo> ftwosa = new ArrayList<ForumTwo>();

		for (int i = 0; i < fones.size(); i++) {

			us.add(userdao.byid(fones.get(i).getUserid()));

			ftwos = forumdao.getForumTwoALL(fones.get(i).getId());
			int g = ftwos.size() - 1;
			System.out.println("g==" + g);
			if (g >= 0) {
				System.out.println("添加了");
				ftwo = ftwos.get(0);

				ftwosa.add(ftwo);
			} else {
				System.out.println("没有添加");
				ftwosa.add(null);
			}

		}
		System.out.println("应该相同比例！" + fones.size() + "-" + ftwosa.size() + "-"
				+ us.size());

		request.setAttribute("fones", fones);

		request.setAttribute("id", model);

		request.setAttribute("ftwos", ftwosa);

		request.setAttribute("us", us);

		request.setAttribute("cpe", cpe);

		request.setAttribute("currentPage", currentPage);
		return Action.SUCCESS;

	}

	/**
	 * 取消收藏
	 */
	public void FolR() throws IOException {
		fo = foldao.ufid(userid, forumid);

		if (fo != null) {

			foldao.remove(fo);

			fone = forumdao.getForumOne(forumid);
			if (fone == null) {
				util.Out().print("null");
				return;
			}
			int num = fone.getFollectnum();

			if (num > 0) {
				num = --num;
				fone.setFollectnum(num);
			} else {

				fone.setFollectnum(0);
			}
			forumdao.update(fone);
			util.Out().print(true);
		} else {
			util.Out().print(false);
		}
	}

	/**
	 * 判断是否收藏过
	 */
	public void Folft() throws IOException {

		if (foldao.ufid(userid, forumid) != null) {
			util.Out().print(true);
		} else {
			util.Out().print(false);
		}
	}

	/**
	 * 查看我收藏的论坛
	 */
	public void Folook() throws IOException {

		System.out.println("进入查看收藏userid" + userid);
		if (userid > 0) {
			fos = foldao.Allu(userid);

			if (fos.size() <= 0) {
				util.Out().print("null");
				return;
			}

			for (int i = 0; i < fos.size(); i++) {

				fone = forumdao.getForumOne(fos.get(i).getForumid());

				User u = userdao.byid(fone.getUserid());
				if (fone != null && u != null) {
					us.add(u);
					fones.add(fone);
				} else {
					System.out.println("用户 或 收藏的论坛已被删除！论坛id为:"
							+ fos.get(i).getForumid());

				}

			}

			String result = "{\"fones\":" + util.ToJson(fones) + ",\"us\":"
					+ util.ToJson(us) + "}";
			util.Out().print(result);

		} else {
			util.Out().print("null");
		}
	}

	/**
	 * 添加论坛收藏
	 */
	public void Fosave() throws IOException {
		System.out.println("app添加论坛收藏");

		Follect fo = new Follect();
		System.out.println("论坛id" + forumid + "用户id" + userid);

		if (forumid > 0 && userid > 0) {

			if (foldao.ufid(userid, forumid) != null) {
				util.Out().print(false);
				return;
			}

			fone = forumdao.getForumOne(forumid);
			int num = fone.getFollectnum();

			if (num > 0) {
				num = ++num;
				fone.setFollectnum(num);
			} else {

				fone.setFollectnum(1);
			}

			forumdao.update(fone);
			fo.setForumid(forumid);
			fo.setUserid(userid);
			fo.setTime(time);

			foldao.save(fo);
			util.Out().print(true);
		} else {
			util.Out().print("论坛id或者用户id 为空");
		}

	}

	/**
	 * app 根据 类型 查询论坛 论坛
	 * 
	 * @throws IOException
	 */
	public void ForumApptype() throws IOException {

		System.out.println("类型type是 ：" + model);
		if (area == null) {
			cpe = forumdao.getForumOneALL(model).size();
		} else {
			cpe = forumdao.getForumOneareaALL(area, model).size();
		}
		System.out.println("全国有" + cpe + "个论坛");
		if (pageSize <= 0) {
			System.out.println("pageSize<=0");
			pageSize = 10;
		}
		System.out.println("pageSize" + pageSize);
		System.out.println("currentPage" + currentPage);
		if (cpe % pageSize == 0) {
			cpe = cpe / pageSize;
		} else {
			cpe = cpe / pageSize + 1;
		}
		if (currentPage <= 0) {
			currentPage = 1;
		}
		if (currentPage >= cpe) {
			currentPage = cpe;
		}
		System.out.println("currentPage1" + currentPage);
		if (area == null) {
			fones = forumdao.getForumOneALL(pageSize, currentPage, model);
		} else {
			fones = forumdao.getForumOneareaALL(pageSize, currentPage, area,
					model);
		}
		List<ForumTwo> ftwosa = new ArrayList<ForumTwo>();
		for (int i = 0; i < fones.size(); i++) {

			User uf = userdao.byid(fones.get(i).getUserid());

			us.add(uf);

			ftwos = forumdao.getForumTwoALL(fones.get(i).getId());

			int g = ftwos.size() - 1;
			System.out.println("g==" + g);
			if (g >= 0) {
				System.out.println("添加了" + i);
				ftwo = ftwos.get(g);

				ftwosa.add(ftwo);
			} else {
				System.out.println("没有添加" + i);
				ftwosa.add(null);
			}

		}
		System.out.println("应该相同比例！" + fones.size() + "-" + ftwosa.size() + "-"
				+ us.size());

		String result = "{\"fones\":" + util.ToJson(fones) + ",\"ftwos\":"
				+ util.ToJson(ftwosa) + ",\"us\":" + util.ToJson(us)
				+ ",\"cpe\":" + cpe + "" + ",\"currentPage\":" + currentPage
				+ "}";
		util.Out().print(result);

	}

	/**
	 * app 论坛删除
	 * 
	 * @throws IOException
	 */
	public void ForumAppRemove() throws IOException {
		System.out.println("forumid   ==" + forumid);
		System.out.println("userid   ==" + userid);
		if (forumid > 0 && userid > 0) {
			User u = userdao.byid(userid);
			if (u == null) {
				util.Out().print("unull");
				return;
			}

			fone = forumdao.getForumOne(forumid);
			if (fone == null) {
				util.Out().print("fnull");
				return;
			}
			if (userid != fone.getUserid()) {
				util.Out().print(false);
				return;
			}
			fos = foldao.Allf(forumid);

			for (int i = 0; i < fos.size(); i++) {

				foldao.remove(fos.get(i));
			}

			ftwos = forumdao.getForumTwoALL(forumid);

			fehrees = forumdao.getForumThreeALL(forumid);

			for (int i = 0; i < ftwos.size(); i++) {
				forumdao.Remove(ftwos.get(i));
				System.out.println("循环删除论坛评论" + i);
			}

			for (int i = 0; i < fehrees.size(); i++) {
				forumdao.Remove(fehrees.get(i));
				System.out.println("循环删除论坛回复信息" + i);
			}

			if (fone.getImg() != null) {
				try {

					System.out.println("是否删除成功"
							+ util.fileRemove(fone.getImg()));

				} catch (Exception e) {
					System.out.println("删除异常!");
				}
			}

			forumdao.Remove(fone);
			util.Out().print(true);
		} else {
			util.Out().print(false);
		}

	}

	/**
	 * 查看全部论坛
	 */
	public void Forumall() throws IOException {
		System.out.println(" appuser查看全国论坛");

		cpe = forumdao.getForumOneALL234().size();

		System.out.println("全国有" + cpe + "个论坛");
		if (pageSize <= 0) {
			System.out.println("pageSize<=0");
			pageSize = 10;
		}
		if (cpe % pageSize == 0) {
			cpe = cpe / pageSize;
		} else {
			cpe = cpe / pageSize + 1;
		}

		if (currentPage <= 0) {
			currentPage = 1;
		}
		if (currentPage >= cpe) {
			currentPage = cpe;
		}
		System.out.println("有" + cpe + "页");
		System.out.println("pageSize" + pageSize);
		System.out.println("currentPage" + currentPage);
		if (currentPage != 0 && pageSize != 0) {
			fones = forumdao.getForumOneALL234(pageSize, currentPage);
			List<ForumTwo> ftwosa = new ArrayList<ForumTwo>();
			for (int i = 0; i < fones.size(); i++) {

				User uf = userdao.byid(fones.get(i).getUserid());

				us.add(uf);

				ftwos = forumdao.getForumTwoALL(fones.get(i).getId());

				int g = ftwos.size() - 1;
				System.out.println("g==" + g);
				if (g >= 0) {
					System.out.println("添加了" + i);
					ftwo = ftwos.get(g);

					ftwosa.add(ftwo);
				} else {
					System.out.println("没有添加" + i);
					ftwosa.add(null);
				}

			}
			System.out.println("应该相同比例！" + fones.size() + "-" + ftwosa.size()
					+ "-" + us.size());

			String result = "{\"fones\":" + util.ToJson(fones) + ",\"ftwos\":"
					+ util.ToJson(ftwosa) + ",\"us\":" + util.ToJson(us)
					+ ",\"cpe\":" + cpe + "" + ",\"currentPage\":"
					+ currentPage + "}";

			util.Out().print(result);
		} else {
			util.Out().print("null");
		}

	}

	/**
	 * 通过用户id 查询我评论过的 论坛
	 */
	public void Fuseid() throws IOException {

		if (userid <= 0) {
			util.Out().print("null");
			return;

		}
		ftwos = forumdao.getFuserALL(userid);
		List<Integer> l = new ArrayList<Integer>();
		List<ForumTwo> fow = new ArrayList<ForumTwo>();
		Boolean b = true;
		for (int i = 0; i < ftwos.size(); i++) {

			for (int y = 0; y < l.size(); y++) {
				System.out.println("y=" + y);
				if (l.get(y) == ftwos.get(i).getForumid()) {

					b = false;
					break;
				}
			}
			System.out.println("b=" + b);

			if (b) {
				fow.add(ftwos.get(i));
				fones.add(forumdao.getForumOne(ftwos.get(i).getForumid()));
				l.add(ftwos.get(i).getForumid());
			}
			b = true;

		}

		for (int i = 0; i < fones.size(); i++) {
			System.out.println("id" + fones.get(i).getId());

			User uf = userdao.byid(fones.get(i).getUserid());

			us.add(uf);

		}

		if (fones.size() > 0) {
			System.out.println("ftwos" + fow.size());
			System.out.println("用户" + us.size());
			System.out.println("论坛" + fones.size());

			String result = "{\"fones\":" + util.ToJson(fones) + ",\"us\":"
					+ util.ToJson(us) + ",\"ftwos\":" + util.ToJson(fow) + "}";

			util.Out().print(result);

		} else {
			System.out.println("空  没有论坛");
			util.Out().print("null");

		}
	}

	/**
	 * 通过用户id查询论坛
	 */
	public void ForumoneTouseid() throws IOException {
		System.out.println("通过用户userid查询论坛id是:" + userid);
		if (userid <= 0) {
			util.Out().print("null");
			return;

		}
		fones = forumdao.getUseridForumOne(userid);
		for (int i = 0; i < fones.size(); i++) {

			User uf = userdao.byid(fones.get(i).getUserid());

			us.add(uf);

		}

		if (fones.size() != 0) {
			System.out.println("正常");
			String result = "{\"fones\":" + util.ToJson(fones) + ",\"us\":"
					+ util.ToJson(us) + "}";
			util.Out().print(result);
		} else {
			System.out.println("空  没有论坛");
			util.Out().print("null");
		}

	}

	/**
	 * appTwo 添加论坛回复 userid 用户id forumid 论坛id reply 回复内容
	 * 
	 * @throws IOException
	 */
	public void Forumtwosaveapp() throws IOException {
		System.out.println("进入app添加论坛回复");
		System.out.println("论坛id" + forumid);
		System.out.println("回复内容" + reply);

		System.out.println("我的id" + userid);
		fone = forumdao.getForumOne(forumid);

		// ftwos = forumdao.getForumTwoALL(forumid);// 查询评论
		/**
		 * if(userid==touserid){ System.out.println("阻止了  自己回复自己的评论");
		 * util.Out().print(false); return; }
		 */
		User u = userdao.byid(userid);
		if (u == null) {
			util.Out().print("null");
			return;

		}
		if (reply == null) {
			util.Out().print("null");
			return;

		}

		if (fone != null) {
			if (fone.getType() == 1 && u.getCompetence() != 4) {
				util.Out().print(false);
				return;
			}

			touserid = fone.getUserid();
			ftwo.setForumid(forumid);
			ftwo.setReply(reply);

			ftwo.setTime(time);
			ftwo.setTouserid(fone.getUserid());
			ftwo.setUserid(userid);
			int g = fone.getTotal() + 1;
			fone.setTotal(g);
			fone.setFrs(fone.getFrs() + 1);
			forumdao.update(fone);
			forumdao.save(ftwo);

			util.Out().print(true);

			return;
		}
		util.Out().print("null");
		return;

	}

	/**
	 * app 用户 通过 id 查看论坛详情
	 * 
	 * @param forumdao
	 * @throws IOException
	 */
	public void ForumLookusers() throws IOException {
		try {

			System.out.println("fid   ==" + id);

			if (id > 0) {
				fone = forumdao.getForumOne(id);
				if (fone == null) {
					util.Out().print("没有这个论坛!");
					System.out.println("没有这个论坛!");
					return;
				}
				System.out.println("论坛标题:" + fone.getTitle());
				fone.setfHits(fone.getfHits() + 1);

				ftwos = forumdao.getForumTwoALL(id);
				User user = userdao.byid(fone.getUserid());

				forumdao.update(fone);

				List fl = new ArrayList();
				List fu = new ArrayList();
				List fut = new ArrayList();
				System.out.println("评论数" + ftwos.size());

				forumid = id;

				for (int i = 0; i < ftwos.size(); i++) {
					System.out.println(i);

					userid = ftwos.get(i).getUserid();

					forumtwoid = ftwos.get(i).getId();
					User u = userdao.byid(userid);

					us.add(u);
					System.out.println("user" + u.getUsername());

					fehrees = forumdao.getForumThreeALL(forumid, forumtwoid);

					if (fehrees.size() == 0) {
						System.out.println("fehrees==null第" + i + "次循环");

						fl.add(null);
						fut.add(null);
						fu.add(null);
					} else {
						System.out.println("fehrees第" + i + "次循环 信息为"
								+ fehrees.size() + "条");
						List<User> userz = new ArrayList<User>();
						List<User> touser = new ArrayList<User>();

						for (int p = 0; p < fehrees.size(); p++) {

							int uid = fehrees.get(p).getUserid();
							int touid = fehrees.get(p).getTouserid();
							User uf = userdao.byid(uid);

							User utr = userdao.byid(touid);

							userz.add(uf);
							touser.add(utr);

						}

						fut.add(touser);

						fu.add(userz);

						fl.add(fehrees);
					}

				}
				System.out.println("threes回复数" + fl.size());
				String result = "{\"fone\":" + util.ToJson(fone)
						+ ",\"ftwos\":" + util.ToJson(ftwos) + ",\"fl\":"
						+ util.ToJson(fl) + ",\"fu\":" + util.ToJson(fu)
						+ ",\"fut\":" + util.ToJson(fut) + ",\"us\":"
						+ util.ToJson(us) + ",\"u\":" + util.ToJson(user) + "}";
				util.Out().print(result);

			} else {
				util.Out().print("null");
			}

		} catch (Exception e) {
			util.Out().print(e.getMessage());
		}

	}

	/**
	 * 查询 当前 省级 市级 论坛
	 * 
	 * @throws IOException
	 */
	public void ForumArea() throws IOException {

		System.out.println("ForumArea地区为" + area + areas);
		fones = forumdao.getForumOneareassALL(area, areas);
		System.out.println(area + areas + "有" + fones.size() + "个论坛");
		if (fones.size() != 0) {
			System.out.println("正常");
			util.Out().print(util.ToJson(fones));
		} else {
			System.out.println("空  没有论坛");
			util.Out().print("null");
		}
	}

	/**
	 * Three 用户回复用户
	 * 
	 * @param forumdao
	 * @throws ServletException
	 */
	public void Forumthreesappadd() throws IOException, ServletException {

		/**
		 * if(userid==touserid){ System.out.println("阻止了  自己回复自己的评论");
		 * util.Out().print(false); return; }
		 */

		System.out.println("论坛id " + forumid);
		System.out.println("回复内容 " + reply);
		System.out.println("回复给 " + touserid);
		System.out.println("我的id " + userid);
		System.out.println("我的楼层 id" + forumtwoid);
		if (forumid <= 0) {
			util.Out().print(false);
			return;
		}
		if (touserid <= 0) {
			util.Out().print(false);
			return;
		}
		if (userid <= 0) {
			util.Out().print(false);
			return;
		}
		if (forumtwoid <= 0) {
			util.Out().print(false);
			return;
		}
		if (reply == null) {
			util.Out().print(false);
			return;
		}
		User u = userdao.byid(userid);
		if (u == null) {
			util.Out().print(false);
			return;
		}
		fone = forumdao.getForumOne(forumid);
		if (fone != null) {
			if (fone.getType() == 1 && u.getCompetence() != 4) {
				util.Out().print(false);
				return;
			}
			fehree.setForumid(forumid);
			fehree.setReply(reply);
			fehree.setTime(time);
			fehree.setTouserid(touserid);
			fehree.setUserid(userid);
			fehree.setForumtwoid(forumtwoid);
			forumdao.save(fehree);
			util.Out().print(true);
			return;
		}
		util.Out().print(false);
	}

	/**
	 * 添加论坛 app端
	 * 
	 * @throws IOException
	 */
	public void Forumonesaveapp() throws IOException {

		System.out.println(util.getfileName());

		System.out.println("model" + model);
		if (model <= 0) {

			util.Out().print("null");
			return;

		}
		if (userid <= 0) {

			util.Out().print("null");
			return;

		}
		if (area == null) {

			util.Out().print("null");
			return;

		}
		if (areas == null) {

			util.Out().print("null");
			return;

		}
		if (content == null) {

			util.Out().print("null");
			return;

		}
		if (title == null) {

			util.Out().print("null");
			return;

		}
		User u = userdao.byid(userid);
		if (u == null) {
			System.out.println("userid不合法!");
			util.Out().print(false);
			return;
		}

		System.out.println("添加论坛" + area + areas);

		if (file != null) {

			img = "/IMG/Forumimg/"+userid;
			img = util.ufileToServer(img, file, fileFileName, "jpg", true);
			System.out.println(img);
			fone.setImg(img);

			System.out.println("头像上传成功！");
		}

		fone.setArea(area);
		fone.setAreas(areas);
		fone.setTitle(title);
		fone.setTime(time);
		fone.setTotal(total);
		fone.setType(model);
		fone.setUserid(userid);
		fone.setContent(content);
		forumdao.save(fone);
		System.out.println("添加成功");
		util.Out().print(true);

	}

	/**
	 * WEB
	 */

	/**
	 * 添加论坛 网页
	 * 
	 * @throws IOException
	 */
	public void Forumonesave() throws IOException {

		User u = userdao.byUsernameAccnumnoPhone(reply);

		if (u == null) {

			util.Out().print("<p>userid==null<p>");
			return;

		}
		userid = u.getId();
		if (model <= 0) {
			System.out.println("id标示不正确");
			util.Out().print("id标示不正确");
			return;
		}
		System.out.println("添加论坛" + area + areas);
		fone.setArea(u.getAddress());
		fone.setAreas(u.getAddcity());
		fone.setTitle(title);
		fone.setTime(time);
		fone.setTotal(total);
		fone.setType(model);
		fone.setUserid(userid);
		fone.setContent(content);
		if (file != null) {

			img = "/IMG/Forumimg/"+userid;
			img = util.ufileToServer(img, file, fileFileName, "jpg", true);
			System.out.println(img);
			fone.setImg(img);

			System.out.println("头像上传成功!");
		}
		forumdao.save(fone);

		util.Out().print(true);

		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/ForumLookall");
		return;

	}

	/**
	 * user管理员查看论坛
	 */
	public String ForumLookuser() throws IOException {

		User user = (User) session.getAttribute("useradmin");

		if (user == null) {
			util.Out().print("请登入");
			return null;

		}
		System.out.println(" 用户管理员查看" + user.getUsername() + "查看论坛");

		if (user.getCompetence() == 2) {
			fones = forumdao.getForumOneareaALL(user.getAddress());
			request.setAttribute("fones", fones);

		}

		if (user.getCompetence() == 1) {

			fones = forumdao.getForumOneareasALL(user.getAddcity());
			request.setAttribute("fones", fones);

		}
		if (user.getCompetence() == 4) {
			model = 1;

			int a = 0;
			a = forumdao.getForumOneALL(model).size();
			pageSize = 20;
			if (a % pageSize == 0) {
				a = a / pageSize;
			} else {
				a = a / pageSize + 1;
			}
			request.setAttribute("cpe", a);
			if (currentPage >= a) {
				currentPage = a;
			}
			if (currentPage <= 0) {
				currentPage = 1;
			}
			fones = forumdao.getForumOneALL(pageSize, currentPage, model);
			request.setAttribute("fones", fones);

			request.setAttribute("currentPage", currentPage);
		}
		return Action.SUCCESS;
	}

	/**
	 * 查看全部论坛 admin 和 论坛超级管理员
	 */
	public String ForumLookall() throws IOException {

		System.out.println(" admin 和 论坛超级管理员查看全国论坛");
		cpe = forumdao.getForumOneNotALL(1).size();
		System.out.println("全国有" + cpe + "个论坛");
		pageSize = 20;
		if (cpe % pageSize == 0) {
			cpe = cpe / pageSize;
		} else {
			cpe = cpe / pageSize + 1;
		}
		if (currentPage <= 0) {
			currentPage = 1;
		}
		if (currentPage >= cpe) {
			currentPage = cpe;
		}

		fones = forumdao.getForumOneNotALL(pageSize, currentPage, 1);

		for (int i = 0; i < fones.size(); i++) {

			us.add(userdao.byid(fones.get(i).getUserid()));

		}
		request.setAttribute("us", us);
		request.setAttribute("fones", fones);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("cpe", cpe);
		request.setAttribute("ar", "全国");

		System.out.println("全国有" + fones.size() + "个论坛 用户" + us.size());

		return Action.SUCCESS;

	}

	/**
	 * 查看全部论坛 admin 和 论坛超级管理员 查看专家答疑论坛
	 */
	public String ForumLookalltype() throws IOException {

		System.out.println("admin和论坛超级管理员按类型查看全国论坛");
		cpe = forumdao.getForumOneALL(model).size();
		System.out.println("全国有" + cpe + "个论坛");
		pageSize = 20;
		if (cpe % pageSize == 0) {
			cpe = cpe / pageSize;
		} else {
			cpe = cpe / pageSize + 1;
		}
		if (currentPage <= 0) {
			currentPage = 1;
		}
		if (currentPage >= cpe) {
			currentPage = cpe;
		}

		fones = forumdao.getForumOneALL(pageSize, currentPage, model);

		for (int i = 0; i < fones.size(); i++) {

			us.add(userdao.byid(fones.get(i).getUserid()));

		}
		request.setAttribute("us", us);
		request.setAttribute("fones", fones);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("cpe", cpe);

		return Action.SUCCESS;

	}

	/**
	 * admin 和 论坛超级管理员 通过id查看论坛
	 * 
	 * @param forumdao
	 * @throws IOException
	 */
	public String ForumLook() throws IOException {
		try {
			System.out.println(" ForumLook进入查看论坛");
			System.out.println("论坛id   ==" + id);
			if (id > 0) {
				fone = forumdao.getForumOne(id);
				if (fone == null) {
					util.Out().print("没有这个论坛!");
					System.out.println("没有这个论坛!");
					return null;
				}
				System.out.println("论坛标题:" + fone.getTitle());
				fone.setfHits(fone.getfHits() + 1);

				ftwos = forumdao.getForumTwoALL(id);
				User user = userdao.byid(fone.getUserid());

				forumdao.update(fone);
				List fl = new ArrayList();
				List fu = new ArrayList();
				List fut = new ArrayList();
				System.out.println("评论数" + ftwos.size());

				forumid = id;

				for (int i = 0; i < ftwos.size(); i++) {
					System.out.println(i);

					userid = ftwos.get(i).getUserid();
					forumtwoid = ftwos.get(i).getId();

					User u = userdao.byid(userid);

					us.add(u);
					System.out.println("user" + u.getUsername());

					fehrees = forumdao.getForumThreeALL(forumid, forumtwoid);

					if (fehrees.size() == 0) {
						System.out.println("fehrees==null第" + i + "次循环");

						fl.add(null);
						fut.add(null);
						fu.add(null);
					} else {
						System.out.println("fehrees第" + i + "次循环 信息为"
								+ fehrees.size() + "条");
						List<User> userz = new ArrayList<User>();
						List<User> touser = new ArrayList<User>();

						for (int p = 0; p < fehrees.size(); p++) {

							int uid = fehrees.get(p).getUserid();
							int touid = fehrees.get(p).getTouserid();
							User uz = userdao.byid(uid);

							userz.add(uz);
							User tuz = userdao.byid(touid);

							touser.add(tuz);

						}

						fut.add(touser);

						fu.add(userz);

						fl.add(fehrees);
					}

				}
				System.out.println("threes回复数" + fl.size());

				request.setAttribute("fone", fone);
				request.setAttribute("ftwos", ftwos);
				request.setAttribute("fut", fut);
				request.setAttribute("fl", fl);
				request.setAttribute("fu", fu);
				request.setAttribute("us", us);
				request.setAttribute("u", user);
				System.out.println(user.getUsername());
				System.out.println("------------------------------");

			} else {
				util.Out().print("id等于空");
			}

		} catch (Exception e) {
			util.Out().print(e.getMessage());
		}
		return Action.SUCCESS;

	}

	/**
	 * 用户管理员通过id查看论坛
	 * 
	 * @param forumdao
	 * @throws IOException
	 */
	public String ForumLookiduser() throws IOException {
		try {

			List fu = new ArrayList();
			List fut = new ArrayList();
			if (id > 0) {
				fone = forumdao.getForumOne(id);
				if (fone == null) {
					util.Out().print("没有这个论坛!");
					System.out.println("没有这个论坛!");
					return null;
				}
				System.out.println("论坛标题:" + fone.getTitle());

				User user = userdao.byid(fone.getUserid());

				fone.setfHits(fone.getfHits() + 1);

				forumdao.update(fone);

				ftwos = forumdao.getForumTwoALL(id);

				List fl = new ArrayList();
				System.out.println("评论数" + ftwos.size());

				forumid = id;
				for (int i = 0; i < ftwos.size(); i++) {
					System.out.println(i);
					userid = ftwos.get(i).getUserid();
					forumtwoid = ftwos.get(i).getId();

					fehrees = forumdao.getForumThreeALL(forumid, forumtwoid);

					User u = userdao.byid(ftwos.get(i).getUserid());

					us.add(u);

					if (fehrees.size() == 0) {
						System.out.println("fehrees==null第" + i + "次循环");

						fl.add(null);
					} else {
						System.out.println("fehrees第" + i + "次循环 信息为"
								+ fehrees.size() + "条");

						List<User> userz = new ArrayList<User>();

						List<User> touser = new ArrayList<User>();

						for (int p = 0; p < fehrees.size(); p++) {

							int uid = fehrees.get(p).getUserid();
							int touid = fehrees.get(p).getTouserid();
							User uz = userdao.byid(uid);

							userz.add(uz);
							User tuz = userdao.byid(touid);

							touser.add(tuz);

						}

						fut.add(touser);

						fu.add(userz);

						fl.add(fehrees);
					}

				}
				System.out.println("threes回复数" + fl.size());

				request.setAttribute("fone", fone);
				request.setAttribute("ftwos", ftwos);
				request.setAttribute("fl", fl);
				request.setAttribute("fut", fut);
				request.setAttribute("fu", fu);
				request.setAttribute("us", us);
				request.setAttribute("u", user);

			} else {
				util.Out().print("id等于空");
			}

		} catch (Exception e) {
			util.Out().print(e.getMessage());
		}
		return Action.SUCCESS;

	}

	/**
	 * Two 添加论坛回复
	 * 
	 * @throws IOException
	 */
	public void Forumtwosave() throws IOException {

		fone = forumdao.getForumOne(forumid);
		User u = userdao.byid(userid);
		if (u == null) {
			util.Out().print(false);
			return;
		}

		if (reply == null) {
			util.Out().print(false);
			return;
		}
		if (fone != null) {
			if (fone.getType() == 1 && u.getCompetence() != 4) {
				util.Out().print(false);
				return;
			}
			fone.setFrs(fone.getFrs() + 1);
			touserid = fone.getUserid();
			ftwo.setForumid(forumid);
			ftwo.setReply(reply);

			ftwo.setTime(time);
			ftwo.setTouserid(fone.getUserid());
			ftwo.setUserid(userid);
			int g = fone.getTotal() + 1;
			fone.setTotal(g);
			forumdao.update(fone);
			forumdao.save(ftwo);
			util.Out().print(true);
		} else {
			util.Out().print(false);
		}

	}

	/**
	 * Three 用户回复用户
	 * 
	 * @param forumdao
	 * @throws ServletException
	 */
	public void Forumthreesave() throws IOException, ServletException {
		System.out.println("论坛id " + forumid);
		System.out.println("回复内容 " + reply);
		System.out.println("回复给 " + touserid);
		System.out.println("我的id " + userid);
		System.out.println("我的楼层id " + forumtwoid);
		if (forumid <= 0) {
			util.Out().print(false);
			return;
		}
		if (touserid <= 0) {
			util.Out().print(false);
			return;
		}
		if (userid <= 0) {
			util.Out().print(false);
			return;
		}
		if (forumtwoid <= 0) {
			util.Out().print(false);
			return;
		}

		if (reply == null) {
			util.Out().print(false);
			return;
		}
		User u = userdao.byid(userid);
		if (u == null) {
			util.Out().print(false);
			return;
		}
		fone = forumdao.getForumOne(forumid);
		if (fone != null) {
			if (fone.getType() == 1 && u.getCompetence() != 4) {
				util.Out().print(false);
				return;
			}
			fehree.setForumid(forumid);
			fehree.setReply(reply);
			fehree.setTime(time);
			fehree.setTouserid(touserid);
			fehree.setUserid(userid);
			fehree.setForumtwoid(forumtwoid);
			forumdao.save(fehree);
			util.Out().print(true);
			return;
		}
		util.Out().print(false);
	}

	/**
	 * 用户管理员 论坛删除
	 * 
	 * @throws IOException
	 */
	public void webUserForumRemove() throws IOException {
		User user = (User) session.getAttribute("useradmin");

		if (user == null) {
			util.Out().print("请登入");
			return;

		}

		if (forumid > 0) {

			fone = forumdao.getForumOne(forumid);
			if (fone == null) {
				util.Out().print("fnull");
				return;
			}

			fos = foldao.Allf(forumid);
			for (int i = 0; i < fos.size(); i++) {

				foldao.remove(fos.get(i));
			}

			if (fone == null) {
				util.Out().print("没有这个论坛");
				return;
			}

			ftwos = forumdao.getForumTwoALL(forumid);

			fehrees = forumdao.getForumThreeALL(forumid);

			for (int i = 0; i < ftwos.size(); i++) {
				forumdao.Remove(ftwos.get(i));
				System.out.println("循环删除论坛评论" + i);
			}

			for (int i = 0; i < fehrees.size(); i++) {
				forumdao.Remove(fehrees.get(i));
				System.out.println("循环删除论坛回复信息" + i);
			}

			if (fone.getImg() != null) {
				try {

					System.out.println("是否删除成功"
							+ util.fileRemove(fone.getImg()));

				} catch (Exception e) {
					System.out.println("删除异常!");
				}
			}

			forumdao.Remove(fone);

			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/ForumLookuser");
			return;

		}

	}

	/**
	 * admin论坛删除
	 * 
	 * @throws IOException
	 */
	public void webForumRemove() throws IOException {
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			util.Out().print("您没有登入！");
			return;
		}
		if (admin.getLevel() > 2 || admin.getLevel() <= 0) {
			util.Out().print("您权限不对！");
			return;
		}
		System.out.println("fid   ==" + forumid);
		if (forumid > 0) {

			fone = forumdao.getForumOne(forumid);
			if (fone == null) {
				util.Out().print("fnull");
				return;
			}
			fos = foldao.Allf(forumid);
			for (int i = 0; i < fos.size(); i++) {

				foldao.remove(fos.get(i));
			}

			ftwos = forumdao.getForumTwoALL(forumid);

			fehrees = forumdao.getForumThreeALL(forumid);

			for (int i = 0; i < ftwos.size(); i++) {
				forumdao.Remove(ftwos.get(i));
				System.out.println("循环删除论坛评论" + i);
			}

			for (int i = 0; i < fehrees.size(); i++) {
				forumdao.Remove(fehrees.get(i));
				System.out.println("循环删除论坛回复信息" + i);
			}

			if (fone.getImg() != null) {
				try {

					System.out.println("是否删除成功"
							+ util.fileRemove(fone.getImg()));

				} catch (Exception e) {
					System.out.println("删除异常!");
				}
			}

			forumdao.Remove(fone);

		}

	}

	/**
	 * 论坛删除 客户端 只能本人删除
	 * 
	 * @throws IOException
	 */
	public void ForumRemove() throws IOException {
		System.out.println("forumid   ==" + forumid);
		System.out.println("userid    ==" + userid);
		if (forumid > 0 && userid > 0) {

			fone = forumdao.getForumOne(forumid);
			if (fone == null) {
				util.Out().print("fnull");
				return;
			}
			if (userid != fone.getUserid()) {
				util.Out().print("fufalse");
				return;
			}

			fos = foldao.Allf(forumid);
			for (int i = 0; i < fos.size(); i++) {

				foldao.remove(fos.get(i));
			}

			ftwos = forumdao.getForumTwoALL(forumid);

			fehrees = forumdao.getForumThreeALL(forumid);

			for (int i = 0; i < ftwos.size(); i++) {
				forumdao.Remove(ftwos.get(i));
				System.out.println("循环删除论坛评论" + i);
			}

			for (int i = 0; i < fehrees.size(); i++) {
				forumdao.Remove(fehrees.get(i));
				System.out.println("循环删除论坛回复信息" + i);
			}

			if (fone.getImg() != null) {
				try {

					System.out.println("是否删除成功"
							+ util.fileRemove(fone.getImg()));

				} catch (Exception e) {
					System.out.println("删除异常!");
				}
			}

			forumdao.Remove(fone);
			util.Out().print(true);
		} else {
			util.Out().print(false);
		}

	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getUserid() {
		return userid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public int getForumid() {
		return forumid;
	}

	public void setForumid(int forumid) {
		this.forumid = forumid;
	}

	public int getTouserid() {
		return touserid;
	}

	public void setTouserid(int touserid) {
		this.touserid = touserid;
	}

	public int getForumtwoid() {
		return forumtwoid;
	}

	public void setForumtwoid(int forumtwoid) {
		this.forumtwoid = forumtwoid;
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

	public void setUserid(int userid) {
		this.userid = userid;
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

	public int getCpe() {
		return cpe;
	}

	public void setCpe(int cpe) {
		this.cpe = cpe;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public ForumAction(UserDAO userdao, ForumDAO forumdao, FollectDAO foldao) {
		super();
		this.userdao = userdao;
		this.forumdao = forumdao;
		this.foldao = foldao;
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

}
