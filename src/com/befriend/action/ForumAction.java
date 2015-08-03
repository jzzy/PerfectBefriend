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
import com.befriend.entity.Attention_Forum;
import com.befriend.entity.Follect;
import com.befriend.entity.ForumOne;
import com.befriend.entity.ForumThree;
import com.befriend.entity.ForumTwo;
import com.befriend.entity.Support_forum;
import com.befriend.entity.User;
import com.befriend.util.OpeFunction;
import com.opensymphony.xwork2.Action;

public class ForumAction {
	OpeFunction util;
	private UserDAO userdao;
	private ForumDAO forumdao;//
	private FollectDAO foldao;//
	List<User> us = new ArrayList<User>();
	Follect fo = new Follect();//
	ForumOne fone = new ForumOne();//
	ForumTwo ftwo = new ForumTwo();//
	ForumThree fehree = new ForumThree();//
	List<Follect> fos = new ArrayList<Follect>();//
	List<ForumOne> fones = new ArrayList<ForumOne>();//
	List<ForumTwo> ftwos = new ArrayList<ForumTwo>();//
	List<ForumThree> fehrees = new ArrayList<ForumThree>();//
	private HttpSession session = ServletActionContext.getRequest()
			.getSession();//
	public HttpServletRequest request = ServletActionContext.getRequest();//

	private int id = 0;// id

	private int model = 0;

	private int types = 0;
	private String time = util.getNowTime();// ʱ��

	private int total = 0;//
	private int userid = 0;//
	private String content;//
	private String title;//
	private String area;//
	private String areas;//
	private String img;//
	public File file;//
	private String fileFileName;//
	private String fileContentType;//
	private String reply; //
	private int forumid = 0; //
	private int touserid = 0;//
	private int forumtwoid = 0;//
	private int forutypeid = 0;
	private int pageSize = 0;//
	private int currentPage = 0;//
	private int cpe = 0;// \
	Support_forum sf = new Support_forum();
	Attention_Forum af = new Attention_Forum();
	List<Attention_Forum> afl = new ArrayList<Attention_Forum>();

	public void forumMeAttention() throws IOException {
		afl = forumdao.atAllU(userid);
		if (afl.size() > 0) {
			util.Out().print(util.ToJson(afl));

		} else {
			util.Out().print(false);
		}
	}

	public void forumRemoveAttention() throws IOException {
		af = forumdao.aufid(userid, forutypeid);
		if (af != null) {
			forumdao.remove(af);
			util.Out().print(true);
		} else {
			util.Out().print(false);
		}
	}

	public void forumAttention() throws IOException {

		if (forumdao.aufid(userid, forutypeid) == null) {
			af.setUserid(userid);
			af.setForutypeid(forutypeid);
			af.setTime(time);
			forumdao.save(af);
			util.Out().print(true);
		} else {
			util.Out().print(false);
		}

	}

	public void forumRemoveStandBy() throws IOException {
		fone = forumdao.getForumOne(forumid);
		sf = forumdao.sufid(userid, forumid);
		if (sf != null && fone != null) {
			forumdao.remove(sf);
			fone.setSupports(forumdao.stAllF(forumid).size());
			forumdao.update(fone);
			util.Out().print(true);
		} else {
			util.Out().print(false);
		}
	}

	public void forumStandBy() throws IOException {
		fone = forumdao.getForumOne(forumid);

		if (forumdao.sufid(userid, forumid) == null && fone != null) {
			sf.setUserid(userid);
			sf.setForumid(forumid);
			sf.setTime(time);
			forumdao.save(sf);
			fone.setSupports(forumdao.stAllF(forumid).size());
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
		System.out.println("webProfessorRemoveForumThreeadmin forumid"
				+ forumid);

		if (fone != null) {
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/ForumLook?id=" + fone.getId());
		} else {
			util.Out().print("û�л�ȡ����̳");
		}

	}

	/**
	 * ��̨web admin ɾ������ �Լ�ɾ�� �ظ���Ϣ
	 * 
	 * @throws IOException
	 */

	public void webAdminRemoveForumTwo() throws IOException {

		System.out.println("����webProfessorRemoveForumThreeadmin");
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			util.Out().print("�����!");
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
				util.Out().print("ɾ���쳣!" + e.getMessage());
			}

			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/ForumLook?id=" + ftwo.getForumid());

		} else {
			System.out.println("û�и�����");
			util.Out().print("û�и�����");
		}
	}

	/**
	 * ��̨web ר��ɾ�� �ظ���Ϣ
	 * 
	 * @throws IOException
	 */

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

		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/ForumLookiduser?id=" + forumid);
	}

	/**
	 * ��̨web ר��ɾ������ �Լ�ɾ�� �ظ���Ϣ
	 * 
	 * @throws IOException
	 */

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
				util.Out().print("ɾ���쳣!" + e.getMessage());
			}

			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath()
					+ "/ForumLookiduser?id="
					+ ftwo.getForumid());

		} else {
			System.out.println("û�и�����");
			util.Out().print("û�и�����");
		}
	}

	/**
	 * ��̨web ר���޸Ļظ��ظ�
	 * 
	 * @throws IOException
	 */

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
			System.out.println("û�и�����");
			util.Out().print("û�и�����");
		}
	}

	/**
	 * ��̨web ר�һظ��û�
	 * 
	 * @throws IOException
	 */

	public void webForumtwosaveProfessor() throws IOException {

		System.out.println("webForumtwosaveProfessor forumid=" + forumid);
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
		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/ForumLookiduser?id=" + forumid);

	}

	/**
	 * ������̳ web��
	 * 
	 * @throws IOException
	 */

	public void webForumonesaveapp() throws IOException {
		System.out.println("����web�����̳��");

		User u = (User) session.getAttribute("u");
		if (u == null) {

			System.out.println("�����µ���!");
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/SimulationApp/login.html");
			return;
		}

		userid = u.getId();
		if (userid <= 0) {

			System.out.println("id����");
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
		System.out.println("�����̳" + area + areas);

		if (file != null) {

			img = "/IMG/Forumimg/" + userid;
			img = util.ufileToServer(img, file, "jpg");
			System.out.println(img);
			fone.setImg(img);

			System.out.println("ͷ���ϴ��ɹ�!");
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
		System.out.println("�����̳�ɹ�!��̳����" + model);

		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/webForumApptype?model=" + model + "");
		return;

	}

	/**
	 * web Three �û��ظ��û�
	 * 
	 * @param forumdao
	 * @throws ServletException
	 */
	public void webForumthreesappadd() throws IOException, ServletException {

		User u = (User) session.getAttribute("u");
		if (u == null) {

			System.out.println("�����µ���!");
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/SimulationApp/login.html");
			return;
		}

		userid = u.getId();
		/**
		 * if(userid==touserid){ System.out.println("��ֹ��  �Լ��ظ��Լ�������");
		 * util.Out().print(false); return; }
		 */

		System.out.println("��̳id " + forumid);
		System.out.println("�ظ����� " + reply);
		System.out.println("�ظ��� " + touserid);
		System.out.println("�ҵ�id " + userid);
		System.out.println("�ҵ�¥�� id" + forumtwoid);
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
		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/webForumLook?id=" + forumid + "");

	}

	/**
	 * web appTwo �����̳�û��ظ�¥��
	 * 
	 * @throws IOException
	 */
	public void webForumtwosaveapp() throws IOException {

		User u = (User) session.getAttribute("u");
		if (u == null) {

			System.out.println("�����µ���!");
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/SimulationApp/login.html");
			return;
		}

		userid = u.getId();
		System.out.println("����web�����̳�ظ�");
		System.out.println("��̳id" + forumid);
		System.out.println("�ظ�����" + reply);

		System.out.println("�ҵ�id" + userid);

		fone = forumdao.getForumOne(forumid);

		ftwos = forumdao.getForumTwoALL(forumid);
		/**
		 * if(userid==touserid){ System.out.println("��ֹ��  �Լ��ظ��Լ�������");
		 * util.Out().print(false); return; }
		 */
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
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/webForumLook?id=" + forumid + "");

			return;
		}
		util.Out().print("null");
		return;

	}

	/**
	 * web ͨ���û�userid ��ѯ�Ҵ�������̳
	 */
	public String webForumoneTouseid() throws IOException {
		try {

			User u = (User) session.getAttribute("u");
			if (u == null) {

				System.out.println("�����µ���!");
				((HttpServletResponse) util.response()).sendRedirect(request
						.getContextPath() + "/SimulationApp/login.html");
				return null;
			}

			userid = u.getId();
			System.out.println("ͨ���û�userid��ѯ��̳id��:" + userid);
			if (userid <= 0) {
				System.out.println("userid���Ϸ�!");
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
			 * if (fones.size() != 0) { System.out.println("����"); String
			 * result = "{\"fones\":" + util.ToJson(fones) + ",\"us\":" +
			 * util.ToJson(us) + "}"; util.Out().print(result); } else {
			 * System.out.println("��  û����̳"); util.Out().print("null"); }
			 */
		} catch (Exception e) {
			System.out.println("�쳣" + e.getMessage());
		}
		return Action.SUCCESS;

	}

	/**
	 * web �û�ͨ��id�鿴��̳
	 * 
	 * @param forumdao
	 * @throws IOException
	 */
	public String webForumLook() throws IOException {
		try {
			System.out.println("web�û��鿴��̳");
			System.out.println("��̳id   ==" + id);
			if (id > 0) {
				fone = forumdao.getForumOne(id);

				if (fone == null) {
					util.Out().print("û�������̳!");
					System.out.println("û�������̳!");
					return null;
				}
				System.out.println("��̳����:" + fone.getTitle());
				fone.setfHits(fone.getfHits() + 1);

				ftwos = forumdao.getForumTwoALL(id);
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
					System.out.println("user" + u.getUsername());

					fehrees = forumdao.getForumThreeALL(forumid, forumtwoid);

					if (fehrees.size() == 0) {
						System.out.println("fehrees==null��" + i + "��ѭ��");

						fl.add(null);
						fut.add(null);
						fu.add(null);
					} else {
						System.out.println("fehrees��" + i + "��ѭ�� ��ϢΪ"
								+ fehrees.size() + "��");
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
				request.setAttribute("fu", fu);
				request.setAttribute("fut", fut);
				request.setAttribute("us", us);
				request.setAttribute("u", user);
				System.out.println(user.getUsername());
				System.out.println("------------------------------");

			} else {
				System.out.println("id����ȷ");
			}

		} catch (Exception e) {
			util.Out().print(e.getMessage());
		}
		return Action.SUCCESS;

	}

	/**
	 * web ���� ���� ��ѯ��̳ ��̳
	 * 
	 * @throws IOException
	 */
	public String webForumApptype() throws IOException {

		System.out.println(" web�û������� �鿴ȫ����̳");
		System.out.println();
		User user = (User) session.getAttribute("u");
		if (user == null) {
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/SimulationApp/login.html");
			return null;
		}
		if (model <= 0) {
			System.out.println("�����µ���!");
			util.Out().print("model <= 0");

			return null;
		}
		cpe = forumdao.gettypeForumOneALL(model).size();
		System.out.println("����type�� ��" + model + user.getAddress() + "��"
				+ cpe + "����̳");

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
				System.out.println("�����");
				ftwo = ftwos.get(0);

				ftwosa.add(ftwo);
			} else {
				System.out.println("û�����");
				ftwosa.add(null);
			}

		}
		System.out.println("Ӧ����ͬ������" + fones.size() + "-" + ftwosa.size()
				+ "-" + us.size());

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
	 * �ж��Ƿ��ղع�
	 */
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

	}

	/**
	 * app ���� ���� ��ѯ��̳ ��̳
	 * 
	 * @throws IOException
	 */
	public void ForumApptype() throws IOException {

		System.out.println("����type�� ��" + model);
		if (area == null) {
			cpe = forumdao.getForumOneALL(model).size();
		} else {
			cpe = forumdao.getForumOneareaALL(area, model).size();
		}
		System.out.println("ȫ����" + cpe + "����̳");
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
				System.out.println("�����" + i);
				ftwo = ftwos.get(g);

				ftwosa.add(ftwo);
			} else {
				System.out.println("û�����" + i);
				ftwosa.add(null);
			}

		}
		System.out.println("Ӧ����ͬ������" + fones.size() + "-" + ftwosa.size()
				+ "-" + us.size());

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

	/**
	 * �鿴ȫ����̳
	 */
	public void Forumall() throws IOException {
		System.out.println(" appuser�鿴ȫ����̳");

		cpe = forumdao.getForumOneALL234().size();

		System.out.println("ȫ����" + cpe + "����̳");
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
		System.out.println("��" + cpe + "ҳ");
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
					System.out.println("�����" + i);
					ftwo = ftwos.get(g);

					ftwosa.add(ftwo);
				} else {
					System.out.println("û�����" + i);
					ftwosa.add(null);
				}

			}
			System.out.println("Ӧ����ͬ������" + fones.size() + "-"
					+ ftwosa.size() + "-" + us.size());

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
	 * ͨ���û�id ��ѯ�����۹��� ��̳
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
			System.out.println("�û�" + us.size());
			System.out.println("��̳" + fones.size());

			String result = "{\"fones\":" + util.ToJson(fones) + ",\"us\":"
					+ util.ToJson(us) + ",\"ftwos\":" + util.ToJson(fow) + "}";

			util.Out().print(result);

		} else {
			System.out.println("��  û����̳");
			util.Out().print("null");

		}
	}

	/**
	 * ͨ���û�id��ѯ��̳
	 */
	public void ForumoneTouseid() throws IOException {
		System.out.println("ͨ���û�userid��ѯ��̳id��:" + userid);
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
			System.out.println("����");
			String result = "{\"fones\":" + util.ToJson(fones) + ",\"us\":"
					+ util.ToJson(us) + "}";
			util.Out().print(result);
		} else {
			System.out.println("��  û����̳");
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

	/**
	 * app �û� ͨ�� id �鿴��̳����
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
					util.Out().print("û�������̳!");
					System.out.println("û�������̳!");
					return;
				}
				System.out.println("��̳����:" + fone.getTitle());
				fone.setfHits(fone.getfHits() + 1);

				ftwos = forumdao.getForumTwoALL(id);
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
					System.out.println("user" + u.getUsername());

					fehrees = forumdao.getForumThreeALL(forumid, forumtwoid);

					if (fehrees.size() == 0) {
						System.out.println("fehrees==null��" + i + "��ѭ��");

						fl.add(null);
						fut.add(null);
						fu.add(null);
					} else {
						System.out.println("fehrees��" + i + "��ѭ�� ��ϢΪ"
								+ fehrees.size() + "��");
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
				System.out.println("threes�ظ���" + fl.size());
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
	 * ��ѯ ��ǰ ʡ�� �м� ��̳
	 * 
	 * @throws IOException
	 */
	public void ForumArea() throws IOException {

		System.out.println("ForumArea����Ϊ" + area + areas);
		fones = forumdao.getForumOneareassALL(area, areas);
		System.out.println(area + areas + "��" + fones.size() + "����̳");
		if (fones.size() != 0) {
			System.out.println("����");
			util.Out().print(util.ToJson(fones));
		} else {
			System.out.println("��  û����̳");
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

	/**
	 * �����̳ app��
	 * 
	 * @throws IOException
	 */
	public void Forumonesaveapp() throws IOException {

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
		if (types <= 0) {

			util.Out().print("null");
			return;

		}
		User u = userdao.byid(userid);
		if (u == null) {
			System.out.println("userid���Ϸ�!");
			util.Out().print(false);
			return;
		}

		System.out.println("�����̳" + area + areas);

		if (file != null) {

			img = "/IMG/Forumimg/" + util.getDayTime(1) + "/"
					+ util.getDayTime(2) + util.getDayTime(3) + "/" + userid;
			img = util.ufileToServer(img, file, "jpg");
			System.out.println(img);
			fone.setImg(img);

			System.out.println("ͷ���ϴ��ɹ���");
		}
		fone.setTypes(types);
		fone.setArea(area);
		fone.setAreas(areas);
		fone.setTitle(title);
		fone.setTime(time);
		fone.setTotal(total);
		fone.setType(model);
		fone.setUserid(userid);
		fone.setContent(content);
		forumdao.save(fone);
		System.out.println("��ӳɹ�");
		util.Out().print(true);

	}

	/**
	 * WEB
	 */

	/**
	 * �����̳ ��ҳ
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
			System.out.println("id��ʾ����ȷ");
			util.Out().print("id��ʾ����ȷ");
			return;
		}
		System.out.println("�����̳" + area + areas);
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

			System.out.println("ͷ���ϴ��ɹ�!");
		}
		forumdao.save(fone);

		util.Out().print(true);

		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/ForumLookall");
		return;

	}

	/**
	 * user����Ա�鿴��̳
	 */
	public String ForumLookuser() throws IOException {

		User user = (User) session.getAttribute("useradmin");

		if (user == null) {
			util.Out().print("�����");
			return null;

		}
		System.out.println(" �û�����Ա�鿴" + user.getUsername() + "�鿴��̳");

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
	 * �鿴ȫ����̳ admin �� ��̳��������Ա
	 */
	public String ForumLookall() throws IOException {

		System.out.println(" admin �� ��̳��������Ա�鿴ȫ����̳");
		cpe = forumdao.getForumOneNotALL(1).size();
		System.out.println("ȫ����" + cpe + "����̳");
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
		request.setAttribute("ar", "ȫ��");

		System.out.println("ȫ����" + fones.size() + "����̳ �û�" + us.size());

		return Action.SUCCESS;

	}

	/**
	 * �鿴ȫ����̳ admin �� ��̳��������Ա �鿴ר�Ҵ�����̳
	 */
	public String ForumLookalltype() throws IOException {

		System.out.println("admin����̳��������Ա�����Ͳ鿴ȫ����̳");
		cpe = forumdao.getForumOneALL(model).size();
		System.out.println("ȫ����" + cpe + "����̳");
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
	 * admin �� ��̳��������Ա ͨ��id�鿴��̳
	 * 
	 * @param forumdao
	 * @throws IOException
	 */
	public String ForumLook() throws IOException {
		try {
			System.out.println(" ForumLook����鿴��̳");
			System.out.println("��̳id   ==" + id);
			if (id > 0) {
				fone = forumdao.getForumOne(id);
				if (fone == null) {
					util.Out().print("û�������̳!");
					System.out.println("û�������̳!");
					return null;
				}
				System.out.println("��̳����:" + fone.getTitle());
				fone.setfHits(fone.getfHits() + 1);

				ftwos = forumdao.getForumTwoALL(id);
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
					System.out.println("user" + u.getUsername());

					fehrees = forumdao.getForumThreeALL(forumid, forumtwoid);

					if (fehrees.size() == 0) {
						System.out.println("fehrees==null��" + i + "��ѭ��");

						fl.add(null);
						fut.add(null);
						fu.add(null);
					} else {
						System.out.println("fehrees��" + i + "��ѭ�� ��ϢΪ"
								+ fehrees.size() + "��");
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
				request.setAttribute("fut", fut);
				request.setAttribute("fl", fl);
				request.setAttribute("fu", fu);
				request.setAttribute("us", us);
				request.setAttribute("u", user);
				System.out.println(user.getUsername());
				System.out.println("------------------------------");

			} else {
				util.Out().print("id���ڿ�");
			}

		} catch (Exception e) {
			util.Out().print(e.getMessage());
		}
		return Action.SUCCESS;

	}

	/**
	 * �û�����Աͨ��id�鿴��̳
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
					util.Out().print("û�������̳!");
					System.out.println("û�������̳!");
					return null;
				}
				System.out.println("��̳����:" + fone.getTitle());

				User user = userdao.byid(fone.getUserid());

				fone.setfHits(fone.getfHits() + 1);

				forumdao.update(fone);

				ftwos = forumdao.getForumTwoALL(id);

				List fl = new ArrayList();
				System.out.println("������" + ftwos.size());

				forumid = id;
				for (int i = 0; i < ftwos.size(); i++) {
					System.out.println(i);
					userid = ftwos.get(i).getUserid();
					forumtwoid = ftwos.get(i).getId();

					fehrees = forumdao.getForumThreeALL(forumid, forumtwoid);

					User u = userdao.byid(ftwos.get(i).getUserid());

					us.add(u);

					if (fehrees.size() == 0) {
						System.out.println("fehrees==null��" + i + "��ѭ��");

						fl.add(null);
					} else {
						System.out.println("fehrees��" + i + "��ѭ�� ��ϢΪ"
								+ fehrees.size() + "��");

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

	/**
	 * Two �����̳�ظ�
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
	 * Three �û��ظ��û�
	 * 
	 * @param forumdao
	 * @throws ServletException
	 */
	public void Forumthreesave() throws IOException, ServletException {
		System.out.println("��̳id " + forumid);
		System.out.println("�ظ����� " + reply);
		System.out.println("�ظ��� " + touserid);
		System.out.println("�ҵ�id " + userid);
		System.out.println("�ҵ�¥��id " + forumtwoid);
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
	 * �û�����Ա ��̳ɾ��
	 * 
	 * @throws IOException
	 */
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

			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/ForumLookuser");
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
