package com.befriend.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.befriend.dao.CollectDAO;
import com.befriend.dao.FollectDAO;
import com.befriend.dao.ForumDAO;
import com.befriend.dao.UserDAO;
import com.befriend.entity.Admin;
import com.befriend.entity.Attention;
import com.befriend.entity.Follect;
import com.befriend.entity.ForumOne;
import com.befriend.entity.ForumOneType;
import com.befriend.entity.ForumThree;
import com.befriend.entity.ForumTwo;
import com.befriend.entity.ForumTwoType;
import com.befriend.entity.Support;
import com.befriend.entity.User;
import com.befriend.util.JsonUtil;
import com.befriend.util.OpeFunction;
import com.befriend.wechat.WechatKit;
import com.opensymphony.xwork2.Action;

@SuppressWarnings("static-access")
public class ForumAction implements ServletRequestAware, ServletResponseAware,
		ServletContextAware {

	OpeFunction util;
	private UserDAO userdao;
	private ForumDAO forumdao;
	private FollectDAO foldao;
	private CollectDAO cdao;
	List<User> us = new ArrayList<User>();
	Follect fo = new Follect();
	ForumOne fone = new ForumOne();
	ForumTwo ftwo = new ForumTwo();
	ForumThree fehree = new ForumThree();
	List<Follect> fos = new ArrayList<Follect>();
	List<ForumOne> fones = new ArrayList<ForumOne>();
	List<ForumTwo> ftwos = new ArrayList<ForumTwo>();
	List<ForumThree> fehrees = new ArrayList<ForumThree>();

	private HttpServletRequest request;
	private HttpServletResponse response;
	@SuppressWarnings("unused")
	private ServletContext context;

	private HttpSession session = ServletActionContext.getRequest()
			.getSession();
	private int id = 0;

	private int model = 0;

	private int types = 0;
	private String time = util.getNowTime();

	private int total = 0;
	private int userid = 0;
	private String content;
	private String title;
	private String area;
	private String areas;
	private String img;
	public File file;
	private String fileFileName;
	private String fileContentType;
	private String reply;
	private int forumid = 0;
	private int touserid = 0;
	private int forumtwoid = 0;
	private int forutypeid = 0;
	private int pageSize = 0;
	private int currentPage = 0;
	private int cpe = 0;
	Support st = new Support();
	Attention af = new Attention();
	List<Attention> afl = new ArrayList<Attention>();
	private final static int comefrom = 2;
	ForumOneType fot = new ForumOneType();
	ForumTwoType ftt = new ForumTwoType();

	public void syncretic5() throws IOException {
			String url = "http://127.0.0.1"+request.getContextPath() +"/forumLookStandBy?userid="+userid;
			String forumLookStandBy=WechatKit.sendGet(url);
			url = "http://127.0.0.1"+request.getContextPath() +"/forumMeAttention?userid="+userid;
			String forumMeAttention=WechatKit.sendGet(url);
			url = "http://127.0.0.1"+request.getContextPath() +"/ForumoneTouseid?userid="+userid;
			String ForumoneTouseid=WechatKit.sendGet(url);
			url = "http://127.0.0.1"+request.getContextPath() +"/Fuseid?userid="+userid;
			String Fuseid=WechatKit.sendGet(url);
			url = "http://127.0.0.1"+request.getContextPath() +"/Folook?userid="+userid;
			String Folook=WechatKit.sendGet(url);
			String result = "{\"forumLookStandBy\":" + util.ToJson(forumLookStandBy) + 
					",\"forumMeAttention\":"+ util.ToJson(forumMeAttention) 
					+ ",\"ForumoneTouseid\":" + util.ToJson(ForumoneTouseid)+ 
					",\"Fuseid\":"+ util.ToJson(Fuseid)+ 
					",\"Folook\":"+ util.ToJson(Folook) + "}";

			util.Out().print(result);
	}

	public void removeOneType() throws IOException {
		fot = forumdao.getByIdForumOneType(id);
		if (fot != null) {
			forumdao.Remove(fot);
			OpeFunction.outjS(request.getContextPath() + "/forumgetOneTypeAll",
					"ok");
		}

	}

	public void removeTwoType() throws IOException {
		ftt = forumdao.getByIdForumTwoType(id);

		if (ftt != null) {
			id = ftt.getFOT().getId();
			forumdao.Remove(ftt);
			OpeFunction.outjS(request.getContextPath()
					+ "/forumlookOneTwoType?id=" + id, "ok");
		}

	}

	public void updateTwoType() throws IOException {
		ftt = forumdao.getByIdForumTwoType(id);
		if (ftt != null) {
			ftt.setTitle(title);
			forumdao.update(ftt);
			OpeFunction.outjS(request.getContextPath()
					+ "/forumlookOneTwoType?id=" + ftt.getFOT().getId(), "ok");
		}

	}

	public void updateOneType() throws IOException {
		ForumOneType fot = forumdao.getByIdForumOneType(id);
		if (fot != null) {
			fot.setTitle(title);
			forumdao.update(fot);
		}
		OpeFunction.outjS(request.getContextPath() + "/forumlookOneTwoType?id="
				+ id, "ok");
	}

	public void saveTwoType() throws IOException {
		Admin admin = (Admin) session.getAttribute("admin");
		fot = forumdao.getByIdForumOneType(id);
		if (!OpeFunction.isEmpty(title) && admin != null && fot != null) {
			ftt.setTitle(title);
			ftt.setTime(time);
			ftt.setAdminname(admin.getAdmin());
			ftt.setImg(img);
			ftt.setFOT(fot);
			forumdao.save(ftt);
			OpeFunction.outjS(request.getContextPath()
					+ "/forumlookOneTwoType?id=" + id, "ok");
			return;
		}
		OpeFunction.outjS(request.getContextPath() + "/forumlookOneTwoType?id="
				+ id, "on");

	}

	public String lookOneTwoType() throws IOException {
		ForumOneType fot = forumdao.getByIdForumOneType(id);
		if (fot != null) {
			request.setAttribute("fot", fot);
			return Action.SUCCESS;
		}
		return null;

	}

	public void saveOneType() throws IOException {
		Admin admin = (Admin) session.getAttribute("admin");
		if (!OpeFunction.isEmpty(title) && admin != null) {
			fot.setTitle(title);
			fot.setTime(time);
			fot.setAdminname(admin.getAdmin());
			fot.setImg(img);
			forumdao.save(fot);
			OpeFunction.outjS(request.getContextPath() + "/forumgetOneTypeAll",
					"ok");
			return;
		}
		OpeFunction.outjS(request.getContextPath() + "/forumgetOneTypeAll",
				"no");

	}

	public String getOneTypeAll() {
		List<ForumOneType> fotl = forumdao.getForumOneTypeAll();
		request.setAttribute("fotl", fotl);
		return Action.SUCCESS;

	}

	public void getForumOneTypeAll() throws IOException {
		List<ForumOneType> fotl = forumdao.getForumOneTypeAll();

		for (int i = 0; i < fotl.size(); i++) {
			System.out.println("1:" + fotl.get(i).getTitle());
			List<ForumTwoType> fttl = fotl.get(i).getfTT();

			for (int j = 0; j < fttl.size(); j++) {
				ForumTwoType ft = fttl.get(j);
				if (cdao.Whether_A(comefrom, userid, fttl.get(i).getId()) != null) {
					ft.setAttentionB(true);
				}
				fttl.set(i, ft);
				System.out.println("2:" + fttl.get(j).getTitle());
			}

		}
		OpeFunction.Out().print(JsonUtil.toJsonExpose(fotl));

	}

	public void forumMeAttention() throws IOException {
		afl = cdao.ILikeToo_A(comefrom, userid);
		if (afl.size() > 0) {
			util.Out().print(util.ToJson(afl));

		} else {
			util.Out().print("null");
		}
	}

	public void forumRemoveAttention() throws IOException {
		af = cdao.Whether_A(comefrom, userid, forutypeid);
		if (af != null) {
			cdao.remove(af);
			util.Out().print(true);
		} else {
			util.Out().print(false);
		}
	}

	public void forumAttention() throws IOException {
		if (cdao.Whether_A(comefrom, userid, forutypeid) == null) {
			af.setUserid(userid);
			af.setComefrom(comefrom);
			af.setTime(time);
			af.setObjectid(forumid);
			cdao.save(af);
			util.Out().print(true);
		} else {
			util.Out().print(false);
		}

	}

	public void LookStandBy() throws IOException {
		List<Support> sl = cdao.ILikeToo(comefrom, userid);
		for (int i = 0; i < sl.size(); i++) {
			fones.add(forumdao.getForumOne(sl.get(i).getObjectid()));
		}
		if (fones.size() > 0) {
			util.Out().print(util.ToJson(fones));
		} else {
			util.Out().print("null");
		}
	}
	public void forumRemoveStandBy() throws IOException {
		fone = forumdao.getForumOne(forumid);
		st = cdao.Whether(comefrom, userid, forumid);
		if (st != null && fone != null) {
			cdao.remove(st);
			fone.setSupports(cdao.Frequency(comefrom, forumid).size());
			forumdao.update(fone);
			util.Out().print(true);
		} else {
			util.Out().print(false);
		}
	}

	public void forumStandBy() throws IOException {
		fone = forumdao.getForumOne(forumid);

		if (cdao.Whether(comefrom, userid, forumid) == null && fone != null) {
			st.setComefrom(comefrom);
			st.setTime(time);
			st.setUserid(userid);
			st.setObjectid(forumid);
			cdao.save(st);
			fone.setSupports(cdao.Frequency(comefrom, forumid).size());
			forumdao.update(fone);
			util.Out().print(true);
		} else {
			util.Out().print(false);
		}
	}

	public void likeTitle() throws IOException {

		System.out.println("likeTitle title:" + title);

		cpe = forumdao.likeTitle(title).size();
		if (cpe == 0) {

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

				ftwo = ftwos.get(g);

				ftwosa.add(ftwo);
			} else {

				ftwosa.add(null);
			}

		}

		String result = "{\"fones\":" + util.ToJson(fones) + ",\"ftwos\":"
				+ util.ToJson(ftwosa) + ",\"us\":" + util.ToJson(us)
				+ ",\"cpe\":" + cpe + "" + ",\"currentPage\":" + currentPage
				+ "}";
		util.Out().print(result);

	}

	public void webAdminRemoveForumThree() throws IOException {

		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			util.Out().print("Is null");
			return;
		}
		fehree = forumdao.getForumThree(id);
		if (fehree != null) {
			fone = forumdao.getForumOne(fehree.getForumid());
			forumid = fehree.getForumid();
			forumdao.Remove(fehree);
		} else {
			util.Out().print("û�и���Ϣ!");
			return;
		}

		if (fone != null) {
			response.sendRedirect(request.getContextPath() + "/ForumLook?id="
					+ fone.getId());
		} else {
			util.Out().print("û�л�ȡ����̳");
		}

	}

	public void webAdminRemoveForumTwo() throws IOException {

		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			util.Out().print("�����!");
			return;
		}
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
				util.Out().print("ɾ���쳣!" + e.getMessage());
			}

			response.sendRedirect(request.getContextPath() + "/ForumLook?id="
					+ ftwo.getForumid());

		} else {
			util.Out().print("û�и�����");
		}
	}

	public void webProfessorRemoveForumThree() throws IOException {

		User user = (User) session.getAttribute("useradmin");
		if (user == null) {
			util.Out().print("�û�Ϊ��");
			return;
		}
		if (user.getCompetence() != 4) {
			util.Out().print("�㲻��ר��");
			return;
		}

		fehree = forumdao.getForumThree(id);
		if (fehree != null) {
			forumid = fehree.getForumid();
			forumdao.Remove(fehree);
		}

		response.sendRedirect(request.getContextPath() + "/ForumLookiduser?id="
				+ forumid);
	}

	public void webProfessorRemoveForumTwo() throws IOException {

		User user = (User) session.getAttribute("useradmin");
		if (user == null) {
			util.Out().print("�û�Ϊ��");
			return;
		}
		if (user != null) {
			if (user.getCompetence() != 4) {
				util.Out().print("�㲻��ר��");
				return;
			}
		}
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
				util.Out().print("ɾ���쳣!" + e.getMessage());
			}

			response.sendRedirect(request.getContextPath()
					+ "/ForumLookiduser?id=" + ftwo.getForumid());

		} else {
			util.Out().print("û�и�����");
		}
	}

	public void webProfessorUpdateForumTwo() throws IOException {
		User user = (User) session.getAttribute("useradmin");
		if (user == null) {
			util.Out().print("�û�Ϊ��");
			return;
		}
		if (user.getCompetence() != 4) {
			util.Out().print("�㲻��ר��");
			return;
		}
		ftwo = forumdao.getForumTwoid(id);
		if (ftwo != null) {
			ftwo.setReply(reply);
			forumdao.update(ftwo);
			response.sendRedirect(request.getContextPath()
					+ "/ForumLookiduser?id=" + ftwo.getForumid());
		} else {
			System.out.println("û�и�����");
			util.Out().print("û�и�����");
		}
	}

	public void webForumtwosaveProfessor() throws IOException {
		User user = (User) session.getAttribute("useradmin");
		if (user == null) {
			util.Out().print("�û�Ϊ��");
			return;
		}
		if (user.getCompetence() != 4) {
			util.Out().print("�㲻��ר��");
			return;
		}
		fone = forumdao.getForumOne(forumid);
		if (fone == null) {
			util.Out().print("��̳Ϊ��");
			return;
		}

		ftwo.setForumid(forumid);
		ftwo.setReply(reply);
		ftwo.setTime(time);
		fone.setTotal(fone.getTotal() + 1);
		ftwo.setTouserid(fone.getUserid());
		ftwo.setUserid(user.getId());
		forumdao.save(ftwo);
		fone.setFrs((fone.getFrs() + 1));
		forumdao.update(fone);
		response.sendRedirect(request.getContextPath() + "/ForumLookiduser?id="
				+ forumid);

	}

	public void webForumonesaveapp() throws IOException {
		User u = (User) session.getAttribute("u");
		if (u == null) {
			response.sendRedirect(request.getContextPath()
					+ "/SimulationApp/login.html");
			return;
		}

		userid = u.getId();
		if (userid <= 0) {
			return;

		}

		if (model <= 0) {
			response.sendRedirect(request.getContextPath()
					+ "/SimulationApp/login.html");
			return;
		}
		area = u.getAddress();
		areas = u.getAddcity();
		if (file != null) {

			img = "/IMG/Forumimg/" + userid;
			img = util.ufileToServer(img, file, "jpg");
			System.out.println(img);
			fone.setImg(img);
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
		response.sendRedirect(request.getContextPath()
				+ "/webForumApptype?model=" + model + "");
		return;

	}

	public void webForumthreesappadd() throws IOException, ServletException {

		User u = (User) session.getAttribute("u");
		if (u == null) {
			response.sendRedirect(request.getContextPath()
					+ "/SimulationApp/login.html");
			return;
		}

		userid = u.getId();
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
			util.Out().print("��û�н���Ȩ�ޣ�");
			return;
		}
		fehree.setForumid(forumid);
		fehree.setReply(reply);
		fehree.setTime(time);
		fehree.setTouserid(touserid);
		fehree.setUserid(userid);
		fehree.setForumtwoid(forumtwoid);
		forumdao.save(fehree);
		response.sendRedirect(request.getContextPath() + "/webForumLook?id="
				+ forumid + "");

	}

	public void webForumtwosaveapp() throws IOException {

		User u = (User) session.getAttribute("u");
		if (u == null) {
			response.sendRedirect(request.getContextPath()
					+ "/SimulationApp/login.html");
			return;
		}

		userid = u.getId();
		fone = forumdao.getForumOne(forumid);
		ftwos = forumdao.getForumTwoALL(forumid);
		if (userid <= 0) {
			util.Out().print("null");
			return;

		}

		if (fone != null) {
			if (fone.getType() == 1 && u.getCompetence() != 4) {
				util.Out().print("��û�н���Ȩ��");
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
			response.sendRedirect(request.getContextPath()
					+ "/webForumLook?id=" + forumid + "");

			return;
		}
		util.Out().print("null");
		return;

	}

	public String webForumoneTouseid() throws IOException {
		try {

			User u = (User) session.getAttribute("u");
			if (u == null) {
				response.sendRedirect(request.getContextPath()
						+ "/SimulationApp/login.html");
				return null;
			}

			userid = u.getId();
			if (userid <= 0) {
				return null;

			}

			fones = forumdao.getUseridForumOne(userid);

			request.setAttribute("fones", fones);
		} catch (Exception e) {

		}
		return Action.SUCCESS;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String webForumLook() throws IOException {
		try {
			if (id > 0) {
				fone = forumdao.getForumOne(id);

				if (fone == null) {
					util.Out().print("û�������̳!");
					return null;
				}
				fone.setfHits(fone.getfHits() + 1);

				ftwos = forumdao.getForumTwoALL(id);

				@SuppressWarnings("unused")
				User user = userdao.byid(fone.getUserid());

				forumdao.update(fone);
				List fl = new ArrayList();
				List fu = new ArrayList();
				List fut = new ArrayList();
				System.out.println("������" + ftwos.size());

				forumid = id;

				for (int i = 0; i < ftwos.size(); i++) {
					System.out.println(i);

					userid = ftwos.get(i).getUserid();
					forumtwoid = ftwos.get(i).getId();

					User u = userdao.byid(userid);

					us.add(u);

					fehrees = forumdao.getForumThreeALL(forumid, forumtwoid);

					if (fehrees.size() == 0) {
						fl.add(null);
						fut.add(null);
						fu.add(null);
					} else {
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
				request.setAttribute("fone", fone);
				request.setAttribute("ftwos", ftwos);
				request.setAttribute("fl", fl);
				request.setAttribute("fu", fu);
				request.setAttribute("fut", fut);
				request.setAttribute("us", us);
				// request.setAttribute("u", user);

			}

		} catch (Exception e) {
			util.Out().print(e.getMessage());
		}
		return Action.SUCCESS;

	}

	public String webForumApptype() throws IOException {

		User user = (User) session.getAttribute("u");
		if (user == null) {
			response.sendRedirect(request.getContextPath()
					+ "/SimulationApp/login.html");
			return null;
		}
		if (model <= 0) {
			util.Out().print("model <= 0");
			return null;
		}
		cpe = forumdao.gettypeForumOneALL(model).size();

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

		fones = forumdao.getForumOneALL(pageSize, currentPage, model);

		List<ForumTwo> ftwosa = new ArrayList<ForumTwo>();

		for (int i = 0; i < fones.size(); i++) {

			us.add(userdao.byid(fones.get(i).getUserid()));

			ftwos = forumdao.getForumTwoALL(fones.get(i).getId());
			int g = ftwos.size() - 1;

			if (g >= 0) {

				ftwo = ftwos.get(0);

				ftwosa.add(ftwo);
			} else {

				ftwosa.add(null);
			}

		}

		request.setAttribute("fones", fones);

		request.setAttribute("id", model);

		request.setAttribute("ftwos", ftwosa);

		request.setAttribute("us", us);

		request.setAttribute("cpe", cpe);

		request.setAttribute("currentPage", currentPage);
		return Action.SUCCESS;

	}

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
				fone.setFollectnum(num - 1);
			} else {

				fone.setFollectnum(0);
			}
			forumdao.update(fone);
			util.Out().print(true);
		} else {
			util.Out().print(false);
		}
	}

	public void Folft() throws IOException {

		if (foldao.ufid(userid, forumid) != null) {
			util.Out().print(true);
		} else {
			util.Out().print(false);
		}
	}

	public void Folook() throws IOException {

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
			}

		}

		String result = "{\"fones\":" + util.ToJson(fones) + ",\"us\":"
				+ util.ToJson(us) + "}";
		util.Out().print(result);

	}

	public void Fosave() throws IOException {

		Follect fo = new Follect();

		if (foldao.ufid(userid, forumid) != null) {
			util.Out().print(false);
			return;
		}

		fone = forumdao.getForumOne(forumid);
		int num = fone.getFollectnum();

		if (num > 0) {
			fone.setFollectnum(num + 1);
		} else {

			fone.setFollectnum(1);
		}

		forumdao.update(fone);
		fo.setForumid(forumid);
		fo.setUserid(userid);
		fo.setTime(time);

		foldao.save(fo);
		util.Out().print(true);

	}

	public void ForumApptype() throws IOException {

		if (area == null) {
			cpe = forumdao.getForumOneALL(model).size();
		} else {
			cpe = forumdao.getForumOneareaALL(area, model).size();
		}

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
			if (g >= 0) {
				ftwo = ftwos.get(g);

				ftwosa.add(ftwo);
			} else {
				ftwosa.add(null);
			}

		}

		String result = "{\"fones\":" + util.ToJson(fones) + ",\"ftwos\":"
				+ util.ToJson(ftwosa) + ",\"us\":" + util.ToJson(us)
				+ ",\"cpe\":" + cpe + "" + ",\"currentPage\":" + currentPage
				+ "}";
		util.Out().print(result);

	}

	public void ForumAppRemove() throws IOException {

		User u = userdao.byid(userid);
		if (u == null) {
			util.Out().print(false);
			return;
		}

		fone = forumdao.getForumOne(forumid);
		if (fone == null) {
			util.Out().print(false);
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

		}

		for (int i = 0; i < fehrees.size(); i++) {
			forumdao.Remove(fehrees.get(i));

		}

		if (fone.getImg() != null) {
			try {

				util.fileRemove(fone.getImg());

			} catch (Exception e) {

			}
		}

		forumdao.Remove(fone);
		util.Out().print(true);

	}

	public void Forumall() throws IOException {
		cpe = forumdao.getForumOneALL234().size();

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
		if (currentPage != 0 && pageSize != 0) {
			fones = forumdao.getForumOneALL234(pageSize, currentPage);
			List<ForumTwo> ftwosa = new ArrayList<ForumTwo>();
			for (int i = 0; i < fones.size(); i++) {

				User uf = userdao.byid(fones.get(i).getUserid());

				us.add(uf);

				ftwos = forumdao.getForumTwoALL(fones.get(i).getId());

				int g = ftwos.size() - 1;

				if (g >= 0) {

					ftwo = ftwos.get(g);

					ftwosa.add(ftwo);
				} else {

					ftwosa.add(null);
				}

			}

			String result = "{\"fones\":" + util.ToJson(fones) + ",\"ftwos\":"
					+ util.ToJson(ftwosa) + ",\"us\":" + util.ToJson(us)
					+ ",\"cpe\":" + cpe + "" + ",\"currentPage\":"
					+ currentPage + "}";

			util.Out().print(result);
		} else {
			util.Out().print("null");
		}

	}

	public void Fuseid() throws IOException {
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

			String result = "{\"fones\":" + util.ToJson(fones) + ",\"us\":"
					+ util.ToJson(us) + ",\"ftwos\":" + util.ToJson(fow) + "}";

			util.Out().print(result);

		} else {
			util.Out().print("null");

		}
	}

	public void ForumoneTouseid() throws IOException {

		fones = forumdao.getUseridForumOne(userid);
		for (int i = 0; i < fones.size(); i++) {

			User uf = userdao.byid(fones.get(i).getUserid());

			us.add(uf);

		}

		if (fones.size() > 0) {

			String result = "{\"fones\":" + util.ToJson(fones) + ",\"us\":"
					+ util.ToJson(us) + "}";
			util.Out().print(result);
		} else {
			util.Out().print("null");
		}

	}

	public void Forumtwosaveapp() throws IOException {

		fone = forumdao.getForumOne(forumid);
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

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void ForumLookusers() throws IOException {
		try {

			if (id > 0) {
				fone = forumdao.getForumOne(id);
				if (fone == null) {
					util.Out().print("û�������̳!");
					return;
				}
				fone.setfHits(fone.getfHits() + 1);

				ftwos = forumdao.getForumTwoALL(id);
				User user = userdao.byid(fone.getUserid());

				forumdao.update(fone);

				List fl = new ArrayList();
				List fu = new ArrayList();
				List fut = new ArrayList();

				forumid = id;

				for (int i = 0; i < ftwos.size(); i++) {

					userid = ftwos.get(i).getUserid();

					forumtwoid = ftwos.get(i).getId();
					User u = userdao.byid(userid);
					us.add(u);
					fehrees = forumdao.getForumThreeALL(forumid, forumtwoid);

					if (fehrees.size() == 0) {
						fl.add(null);
						fut.add(null);
						fu.add(null);
					} else {

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

	public void ForumArea() throws IOException {

		fones = forumdao.getForumOneareassALL(area, areas);
		if (fones.size() != 0) {

			util.Out().print(util.ToJson(fones));
		} else {

			util.Out().print("null");
		}
	}

	public void Forumthreesappadd() throws IOException, ServletException {

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

	public void Forumonesaveapp() throws IOException {

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
		if (types <= 0) {

			util.Out().print("null");
			return;

		}
		User u = userdao.byid(userid);
		if (u == null) {

			util.Out().print(false);
			return;
		}

		if (file != null) {

			img = "/IMG/Forumimg/" + util.getDayTime(1) + "/"
					+ util.getDayTime(2) + util.getDayTime(3) + "/" + userid;
			img = util.ufileToServer(img, file, "jpg");
			System.out.println(img);
			fone.setImg(img);

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
		util.Out().print(true);

	}

	public void Forumonesave() throws IOException {

		User u = userdao.byUsernameAccnumnoPhone(reply);

		if (u == null) {

			util.Out().print("<p>userid==null<p>");
			return;

		}
		userid = u.getId();
		if (model <= 0) {

			util.Out().print("id��ʾ����ȷ");
			return;
		}
		fone.setArea(u.getAddress());
		fone.setAreas(u.getAddcity());
		fone.setTitle(title);
		fone.setTime(time);
		fone.setTotal(total);
		fone.setType(model);
		fone.setUserid(userid);
		fone.setContent(content);
		if (file != null) {

			img = "/IMG/Forumimg/" + userid;
			img = util.ufileToServer(img, file, "jpg");
			System.out.println(img);
			fone.setImg(img);
		}
		forumdao.save(fone);

		util.Out().print(true);

		response.sendRedirect(request.getContextPath() + "/ForumLookall");
		return;

	}

	public String ForumLookuser() throws IOException {

		User user = (User) session.getAttribute("useradmin");

		if (user == null) {
			util.Out().print("�����");
			return null;

		}

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

	public String ForumLookall() throws IOException {

		cpe = forumdao.getForumOneNotALL(1).size();
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
		request.setAttribute("ar", "ar");

		return Action.SUCCESS;

	}

	public String ForumLookalltype() throws IOException {

		cpe = forumdao.getForumOneALL(model).size();
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String ForumLook() throws IOException {
		try {

			if (id > 0) {
				fone = forumdao.getForumOne(id);
				if (fone == null) {
					util.Out().print("û�������̳!");

					return null;
				}

				fone.setfHits(fone.getfHits() + 1);

				ftwos = forumdao.getForumTwoALL(id);
				User user = userdao.byid(fone.getUserid());

				forumdao.update(fone);
				List fl = new ArrayList();
				List fu = new ArrayList();
				List fut = new ArrayList();

				forumid = id;

				for (int i = 0; i < ftwos.size(); i++) {
					System.out.println(i);

					userid = ftwos.get(i).getUserid();
					forumtwoid = ftwos.get(i).getId();

					User u = userdao.byid(userid);

					us.add(u);

					fehrees = forumdao.getForumThreeALL(forumid, forumtwoid);

					if (fehrees.size() == 0) {

						fl.add(null);
						fut.add(null);
						fu.add(null);
					} else {

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
				request.setAttribute("fone", fone);
				request.setAttribute("ftwos", ftwos);
				request.setAttribute("fut", fut);
				request.setAttribute("fl", fl);
				request.setAttribute("fu", fu);
				request.setAttribute("us", us);
				request.setAttribute("u", user);

			} else {
				util.Out().print("id���ڿ�");
			}

		} catch (Exception e) {
			util.Out().print(e.getMessage());
		}
		return Action.SUCCESS;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String ForumLookiduser() throws IOException {
		try {

			List fu = new ArrayList();
			List fut = new ArrayList();
			if (id > 0) {
				fone = forumdao.getForumOne(id);
				if (fone == null) {
					util.Out().print("û�������̳!");

					return null;
				}

				User user = userdao.byid(fone.getUserid());

				fone.setfHits(fone.getfHits() + 1);

				forumdao.update(fone);

				ftwos = forumdao.getForumTwoALL(id);

				List fl = new ArrayList();

				forumid = id;
				for (int i = 0; i < ftwos.size(); i++) {
					System.out.println(i);
					userid = ftwos.get(i).getUserid();
					forumtwoid = ftwos.get(i).getId();

					fehrees = forumdao.getForumThreeALL(forumid, forumtwoid);

					User u = userdao.byid(ftwos.get(i).getUserid());

					us.add(u);

					if (fehrees.size() == 0) {

						fl.add(null);
					} else {

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
				System.out.println("threes�ظ���" + fl.size());

				request.setAttribute("fone", fone);
				request.setAttribute("ftwos", ftwos);
				request.setAttribute("fl", fl);
				request.setAttribute("fut", fut);
				request.setAttribute("fu", fu);
				request.setAttribute("us", us);
				request.setAttribute("u", user);

			} else {
				util.Out().print("id���ڿ�");
			}

		} catch (Exception e) {
			util.Out().print(e.getMessage());
		}
		return Action.SUCCESS;

	}

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

	public void Forumthreesave() throws IOException, ServletException {

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

	public void webUserForumRemove() throws IOException {
		User user = (User) session.getAttribute("useradmin");

		if (user == null) {
			util.Out().print("�����");
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
				util.Out().print("û�������̳");
				return;
			}

			ftwos = forumdao.getForumTwoALL(forumid);

			fehrees = forumdao.getForumThreeALL(forumid);

			for (int i = 0; i < ftwos.size(); i++) {
				forumdao.Remove(ftwos.get(i));

			}

			for (int i = 0; i < fehrees.size(); i++) {
				forumdao.Remove(fehrees.get(i));

			}

			if (fone.getImg() != null) {
				try {

					util.fileRemove(fone.getImg());

				} catch (Exception e) {

				}
			}

			forumdao.Remove(fone);

			response.sendRedirect(request.getContextPath() + "/ForumLookuser");
			return;

		}

	}

	/**
	 * admin��̳ɾ��
	 * 
	 * @throws IOException
	 */
	public void webForumRemove() throws IOException {
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			util.Out().print("��û�е��룡");
			return;
		}
		if (admin.getLevel() > 2 || admin.getLevel() <= 0) {
			util.Out().print("��Ȩ�޲��ԣ�");
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
				System.out.println("ѭ��ɾ����̳����" + i);
			}

			for (int i = 0; i < fehrees.size(); i++) {
				forumdao.Remove(fehrees.get(i));
				System.out.println("ѭ��ɾ����̳�ظ���Ϣ" + i);
			}

			if (fone.getImg() != null) {
				try {

					System.out.println("�Ƿ�ɾ���ɹ�"
							+ util.fileRemove(fone.getImg()));

				} catch (Exception e) {
					System.out.println("ɾ���쳣!");
				}
			}

			forumdao.Remove(fone);

		}

	}

	/**
	 * ��̳ɾ�� �ͻ��� ֻ�ܱ���ɾ��
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
				System.out.println("ѭ��ɾ����̳����" + i);
			}

			for (int i = 0; i < fehrees.size(); i++) {
				forumdao.Remove(fehrees.get(i));
				System.out.println("ѭ��ɾ����̳�ظ���Ϣ" + i);
			}

			if (fone.getImg() != null) {
				try {

					System.out.println("�Ƿ�ɾ���ɹ�"
							+ util.fileRemove(fone.getImg()));

				} catch (Exception e) {
					System.out.println("ɾ���쳣!");
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

	public ForumAction(UserDAO userdao, ForumDAO forumdao, FollectDAO foldao,
			CollectDAO cdao) {
		super();
		this.userdao = userdao;
		this.forumdao = forumdao;
		this.foldao = foldao;
		this.cdao = cdao;
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		this.context = arg0;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;

	}

}
