package com.befriend.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.mail.search.DateTerm;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.befriend.dao.CollectDAO;
import com.befriend.dao.NewsDAO;
import com.befriend.dao.ReviewDAO;
import com.befriend.dao.UserDAO;
import com.befriend.entity.Admin;
import com.befriend.entity.App;
import com.befriend.entity.Collect;
import com.befriend.entity.News;
import com.befriend.entity.NewsLabel;
import com.befriend.entity.Review;
import com.befriend.entity.User;
import com.befriend.util.OpeFunction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class NewsAction {
	private static final List<App> Map = null;// Map����
	private OpeFunction util;// �Խ��Ĺ�����

	private UserDAO userdao;// �û�dao
	private NewsDAO ndao; // ����dao
	private CollectDAO cdao;// �ղ�dao
	private ReviewDAO rdao;// ����dao

	private int num = 0;// ��Ҫ��������
	private List<News> nl = new ArrayList<News>();
	private News n = new News();// ������
	private Collect c = new Collect();// �ղ���
	private Review r = new Review();// ������
	private List<Review> rl = new ArrayList<Review>();// ������
	private List<Collect> cl = new ArrayList<Collect>();// �ղ� List
	private List<User> ul = new ArrayList<User>();// �û� List
	private int userid;// �û�id
	private int newsid;// ����id
	private String review;// ��������
	private int reviewid;// ���۵�id
	private int expert = 0;// ��ȡ�����Ƿ�Ϊ ר�� 0���� 1��
	private String types;// 4С��
	private String type;// 8����
	private int itypes;// 4С��
	private int itype;// 8����
	private String summary;// ���
	private String title;// ����
	private String timet;// ʱ��

	private String area;// ʡ��
	private String areas;// �м�

	private File imgFile1;// Сͼ�ļ�
	private File imgFile2;// Сͼ�ļ�
	private File imgFile3;// Сͼ�ļ�
	/**
	 * private File imgFilemax;// ��ͼ�ļ� private String imgFileFileName;// Сͼ�ļ���
	 * 
	 * private String imgFileContentType;// Сͼ�ļ����� private String
	 * imgFilemaxFileName;// ��ͼ�ļ��� private String imgFilemaxContentType;//
	 * ��ͼ�ļ�����
	 */

	private String savePath;// Ŀ¼
	private int tp;// ѡ�� �˴�������
	private String htmlData;// ������Ҫ����
	private String A;// һ�� ��ʾ �����ж� ������ת �� �ж� �� �˴������� ���� ��������
	private int pageSize = 10;// ÿҳ��ʾ ����������
	private int currentPage = 1;// ���ǵڶ���ҳ
	private int id;// id
	private String username;// ���۵��û���
	Map Mapsession = (Map) ActionContext.getContext().get("session");
	HttpServletRequest request = ServletActionContext.getRequest();
	// httpsession
	HttpSession session = ServletActionContext.getRequest().getSession();

	private String province;// ʡ��

	private String city;// �м�
	public File xlsxFile;//���ű�ǩ��
	private String xlsxFileFileName;//�ļ�����
	public String getXlsxFileFileName() {
		return xlsxFileFileName;
	}
	public void setXlsxFileFileName(String xlsxFileFileName) {
		this.xlsxFileFileName = xlsxFileFileName;
	}
	/**
	 * �ϴ����ű�ǩ
	 * @throws InvalidFormatException 
	 */
	public void saveLabel() throws IOException, InvalidFormatException {
		System.out.println("�ļ�����"+xlsxFileFileName);
		
		ServletResponse srp=(ServletResponse) util.response();
		srp.setCharacterEncoding("GBK");
		PrintWriter out = srp.getWriter();
		String loginPage = "/PerfectBefriend/SuperAdmin/AdminNews/newshome.jsp";
		StringBuilder builder = new StringBuilder();
		builder.append("<script type=\"text/javascript\">");
		
		if (xlsxFile==null) {
			loginPage = "/PerfectBefriend/SuperAdmin/AdminNews/newshome.jsp";
			builder.append("alert('��ӱ�ǩʧ�ܣ�');");
			builder.append("window.top.location.href='");
			builder.append(loginPage);
			builder.append("';");
			builder.append("</script>");
			out.print(builder.toString());
			return;
		}
		if(!xlsxFileFileName.split("\\.")[1].equals("xlsx")){
			loginPage = "/PerfectBefriend/SuperAdmin/AdminNews/newshome.jsp";
			builder.append("alert('�ļ����Ͳ��� �����: ."+xlsxFileFileName.split("\\.")[1]+"');");
			builder.append("window.top.location.href='");
			builder.append(loginPage);
			builder.append("';");
			builder.append("</script>");
			out.print(builder.toString());
			return;
		}
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(xlsxFile);

		// ѭ��������Sheet
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			
			// ѭ����Row
			for (int rowNum = 1; rowNum < xssfSheet.getLastRowNum(); rowNum++) {
				//����
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow == null) {
					continue;
				}
				System.out.println("���ű�ǩ��"+xssfRow.getCell(0));
				System.out.println("��"+numSheet+"��������Sheet,�ĵ�"+rowNum+"��");
				System.out.println("׼�����!");
				String label=null;
				//����һ��
				if(xssfRow.getCell(0)!=null&&!util.isNull(xssfRow.getCell(0).toString())){
					label=xssfRow.getCell(0).toString();
				
					if(ndao.byNewsLabelName(label)!=null){
						System.out.println("�Ѿ���ӹ�!");
						continue;
					}
					else
					{
						//���Ҫ�洢����Ϣ
						NewsLabel nlb=new NewsLabel();
						nlb.setLabel(label);
						nlb.setTime(util.getNowTime());
						ndao.Save(nlb);
					}
				}
			}
		}
		
		builder.append("alert('��ӱ�ǩ�ɹ�!');");
		builder.append("window.top.location.href='");
		builder.append(loginPage);
		builder.append("';");
		builder.append("</script>");
		out.print(builder.toString());
		
		
	}
	/**
	 * ͨ��id��ѯ���� ���Ƿ��и���
	 * 
	 * @throws IOException
	 */
	public void newNewsId() throws IOException {
		nl = ndao.n2ews(newsid);
		if (nl.size() > 0) {

			util.Out().print(nl.get(0).getId());
		} else {
			util.Out().print(0);
		}
	}

	/**
	 * ΢�ŷ�ҳ ��ѯ ��������
	 * 
	 * @throws IOException
	 */
	public String webWeiXinHotarea() throws IOException {
		if (pageSize <= 0) {
			pageSize = 10;
		}
		if (currentPage <= 0) {
			currentPage = 1;
		}

		int a = 0;

		Object Oprovince = session.getAttribute("province");
		if (Oprovince != null) {
			province = Oprovince.toString();

		} else {
			province = "����";
		}
		System.out.println(province);
		a = ndao.area(province, 0).size();

		if (a % pageSize == 0) {
			a = a / pageSize;
		} else {
			a = a / pageSize + 1;
		}
		if (currentPage > a) {
			currentPage = a;
		}

		nl = ndao.Hotarea(province, pageSize, currentPage);
		System.out.println(" -��" + a + "ҳ");
		System.out.println("ÿҳ������-" + pageSize);
		System.out.println("��-" + currentPage + "-ҳ");

		request.setAttribute("currentPage", currentPage);

		request.setAttribute("nl", nl);

		request.setAttribute("a", a);
		return Action.SUCCESS;
	}

	/**
	 * ͨ��username��ѯ���� ���źͶ�ÿ�����ŵ����� ����
	 * 
	 * @throws IOException
	 */
	public String webReviewsusername() throws IOException {
		try {

			User u = (User) session.getAttribute("u");

			if (u == null) {

				((HttpServletResponse) util.response()).sendRedirect(request
						.getContextPath() + "/SimulationApp/login.html");
				return null;
			}

			username = u.getUsername();

			List<Integer> rn = new ArrayList<Integer>();// �ղ� List
			for (Review r1 : rdao.Allu(username)) {

				Boolean b = true;

				for (int i = 0; i < rn.size(); i++) {

					if (rn.get(i) == r1.getNewsid()) {

						b = false;
					}

				}

				if (b) {
					System.out.println("�����۹�������  �û���������id" + username + "+"
							+ r1.getNewsid() + "ʱ���ǣ�" + r1.getTime());
					rl.add(rdao.unid(username, r1.getNewsid()).get(0));
					nl.add(ndao.byid(r1.getNewsid()));
				}

				rn.add(r1.getNewsid());

			}

			request.setAttribute("nl", nl);
			request.setAttribute("rl", rl);
		} catch (Exception e) {
			System.out.println("�쳣��" + e.getMessage());
		}
		return Action.SUCCESS;
	}

	/**
	 * ��ѯ�ղ� ͨ���û�id���в�ѯ
	 */
	public String webSearchBookmark() throws IOException {

		User u = (User) session.getAttribute("u");

		if (u == null) {

			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/SimulationApp/login.html");
			return null;
		}
		userid = u.getId();
		System.out.println("������webSearchBookmark");

		for (Collect c : cdao.Allu(userid)) {
			n = ndao.byid(c.getNewsid());
			nl.add(n);

		}
		request.setAttribute("nl", nl);
		System.out.println("�û�" + u.getUsername() + "�ղ���" + nl.size() + "������");
		return Action.SUCCESS;

	}

	/**
	 * ����ղ� ͨ�� �û�id ����id
	 * 
	 * @throws IOException
	 */
	public void webCsave() throws IOException {
		try {
			System.out.println("webCsave�㻹û�е���!");

			User u = (User) session.getAttribute("u");

			if (u == null) {

				((HttpServletResponse) util.response()).sendRedirect(request
						.getContextPath() + "/SimulationApp/login.html");
				System.out.println("�㻹û�е���!");
				return;
			}
			userid = u.getId();
			System.out.println("userid" + u.getId());
			if (cdao.unid(userid, newsid) != null) {
				util.Out().print("�Ѿ��ղع�!");
				return;
			} else {
				c.setNewsid(newsid);
				c.setUserid(userid);
				c.setTime(util.getNowTime());
				cdao.save(c);

				n = ndao.byid(newsid);

				System.out.println("newsid��" + n.getId());
				System.out.println("1�����±��ղ�" + n.getCollectnum());
				int n1;
				if (n.getCollectnum() != null) {
					n1 = n.getCollectnum() + 1;
				} else {
					n1 = 1;
				}

				if (n.getHits() != null) {
					n.setCah(n1 * 2 + n.getHits());
				} else {
					n.setCah(n1 * 2);
				}

				System.out.println("2�����±��ղ�" + n1);
				n.setCollectnum(n1);

				ndao.Upnews(n);
				util.Out().print("�ղسɹ�");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * web������� ͨ�� �û�username ����newsid
	 * 
	 * @throws IOException
	 */
	public void webRsave() throws IOException {
		try {

			User u = (User) session.getAttribute("u");

			if (u == null) {

				((HttpServletResponse) util.response()).sendRedirect(request
						.getContextPath() + "/SimulationApp/login.html");
				System.out.println("�㻹û����!");
				return;
			}
			System.out.println("�����������Rsave");

			if (newsid <= 0) {
				util.Out().print("null");
				return;

			}
			if (review == null || review.equals("")) {
				util.Out().print("����д��������");
				return;
			}
			if (u.getUsername() == null) {
				util.Out().print("�������û���!");
				return;
			}
			r.setNewsid(newsid);
			r.setUsername(u.getUsername());
			r.setTime(util.getNowTime());
			r.setReview(review);
			rdao.save(r);

			n = ndao.byid(newsid);
			//
			if (n == null) {
				util.Out().print("û�и�����");
				return;

			}
			int a = rdao.Alln(newsid).size();
			System.out.println("�Ƿ�������" + n != null);
			System.out.println("��������" + review);
			System.out.println("newsid��" + n.getId());
			System.out.println("�����±�����" + a);
			n.setReviews(a);

			ndao.Upnews(n);

			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/webNewsId?id=" + newsid + "");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * ��ҳ ��ѯ ��������
	 * 
	 * @throws IOException
	 */
	public String webHotareaf() throws IOException {

		User u = (User) session.getAttribute("u");
		if (u == null) {

			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/SimulationApp/login.html");
			System.out.println("�㻹û����!");

			return null;
		}
		if (pageSize <= 0) {
			pageSize = 10;
		}

		if (currentPage <= 0) {
			currentPage = 1;
		}

		int a = 0;

		a = ndao.area(u.getAddress(), 0).size();

		if (a % pageSize == 0) {
			a = a / pageSize;
		} else {
			a = a / pageSize + 1;
		}
		if (currentPage > a) {
			currentPage = a;
		}

		nl = ndao.Hotarea(u.getAddress(), pageSize, currentPage);
		System.out.println(" -��" + a + "ҳ");
		System.out.println("ÿҳ������-" + pageSize);
		System.out.println("��-" + currentPage + "-ҳ");

		request.setAttribute("currentPage", currentPage);

		request.setAttribute("nl", nl);

		request.setAttribute("a", a);
		return Action.SUCCESS;
	}

	/**
	 * ȫ���������� ��ҳ��ѯ
	 */
	public String webHottest() {
		try {
			int a = 0;

			a = ndao.Hottest(0).size();

			if (a % pageSize == 0) {
				a = a / pageSize;
			} else {
				a = a / pageSize + 1;
			}
			if (currentPage >= a) {
				currentPage = a;
			}
			if (currentPage <= 0) {
				currentPage = 1;
			}

			System.out.println("ÿҳ������-" + pageSize);
			System.out.println("��-" + currentPage + "-ҳ");

			nl = ndao.Hottest(pageSize, currentPage);

			request.setAttribute("currentPage", currentPage);

			request.setAttribute("nl", nl);

			request.setAttribute("a", a);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Action.SUCCESS;
	}

	/**
	 * �˴��� ��4С�� ��ѯ type
	 * 
	 * @throws IOException
	 */
	public String webTypes() throws IOException {
		try {
			switch (tp) {
			case 1:
				type = "����С";

				break;
			case 2:
				type = "С����";
				break;
			case 3:
				type = "�п�";
				break;
			case 4:
				type = "�߿�";
				break;

			default:
				System.out.println("��������ȷ����tp");
				type = "No";
				break;
			}
			System.out.println("������" + tp + "�����" + type);
			int a = 0;

			a = ndao.types(type).size();

			if (a % pageSize == 0) {
				a = a / pageSize;
			} else {
				a = a / pageSize + 1;
			}
			if (currentPage >= a) {
				currentPage = a;
			}
			if (currentPage <= 0) {
				currentPage = 1;
			}
			System.out.println(type + " -��" + a + "ҳ");
			System.out.println("ÿҳ������-" + pageSize);
			System.out.println("��-" + currentPage + "-ҳ");

			nl = ndao.types(type, pageSize, currentPage);

			request.setAttribute("currentPage", currentPage);

			request.setAttribute("type", type);

			request.setAttribute("nl", nl);

			request.setAttribute("tp", tp);

			request.setAttribute("a", a);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Action.SUCCESS;

	}

	/**
	 * web��ҳ ��ѯ �˴���type
	 * 
	 * @throws IOException
	 */
	public String webNewtype() throws IOException {
		try {
			if (pageSize <= 0) {

				pageSize = 10;
			}
			if (tp <= 0) {
				util.Out().print("tpΪ�գ�");
				return null;
			}
			if (currentPage <= 0) {
				currentPage = 1;
			}

			System.out.println("����webnewtype");
			System.out.println("����" + tp);
			
			int a = 0;

			a = ndao.type(0, tp).size();

			if (a % pageSize == 0) {
				a = a / pageSize;
			} else {
				a = a / pageSize + 1;
			}
			if (currentPage >= a) {
				currentPage = a;
			}

			System.out.println(type + " -��" + a + "ҳ");
			System.out.println("ÿҳ������-" + pageSize);
			System.out.println("��-" + currentPage + "-ҳ");

			System.out.println(type);

			nl = ndao.type(0, tp, pageSize, currentPage);

			request.setAttribute("currentPage", currentPage);

			request.setAttribute("type", type);

			request.setAttribute("nl", nl);

			request.setAttribute("tp", tp);

			request.setAttribute("a", a);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Action.SUCCESS;
	}

	/**
	 * web ͨ��id��ѯ����
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public String webNewsId() throws IOException, ParseException {
		System.out.println("web ͨ��id��ѯ����" + id);
		if (id <= 0) {
			util.Out().print("û�л�ȡ������id");
			System.out.println("û�л�ȡ������id");
			return null;
		}
		// ��ȡ������Ϣ
		rl = rdao.Alln(id);
		if (rl.size() != 0) {
			System.out.println("������");
			for (int i = 0; i < rl.size(); i++) {

				ul.add(userdao.byUsernameAccnumnoPhone(rl.get(i).getUsername()));
			}
			System.out.println("����" + rl.size());

			request.setAttribute("ul", ul);
			request.setAttribute("rl", rl);
		}

		n = ndao.byid(id);

		n.setHits(n.getHits() + 1);

		n.setCah(n.getCah() + 1);

		System.out.println("�����:" + n.getHits() + "�ղ���+�����:" + n.getCah()
				+ "�ղ���:" + n.getCollectnum());

		ndao.Upnews(n);
		request.setAttribute("n", n);

		return Action.SUCCESS;
	}

	/**
	 * ΢�Ź��ں� ͨ��id��ѯ����
	 * 
	 * @throws IOException
	 */
	public String weiXniNewsId() throws IOException {

		System.out.println("΢�Ź��ں� ͨ��id��ѯ����" + id);
		if (id <= 0) {
			util.Out().print("û�л�ȡ������id");
			System.out.println("û�л�ȡ������id");
			return null;
		}

		rl = rdao.Alln(id);
		if (rl.size() != 0) {
			System.out.println("������");
			for (int i = 0; i < rl.size(); i++) {

				ul.add(userdao.byUsernameAccnumnoPhone(rl.get(i).getUsername()));
			}
			System.out.println("����" + rl.size());

			request.setAttribute("ul", ul);
			request.setAttribute("rl", rl);
		}

		n = ndao.byid(id);

		n.setHits(n.getHits() + 1);

		n.setCah(n.getCah() + 1);
		request.setAttribute("n", n);

		return Action.SUCCESS;
	}

	/**
	 * ΢�Ź��ں� ��������
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 */
	public String weiXniBDN() throws IOException, InterruptedException {
		System.out.println("province" + province);
		System.out.println("city" + city);

		if (province != null && !province.equals("ʡ����")) {

			area = province;

			session.setAttribute("province", province);
			if (city != null) {

				session.setAttribute("city", city);
			}
		}

		System.out.println("΢�ű���������ҳ");
		if (area == null || area == "") {
			area = "����";
		}

		Object oprovince = session.getAttribute("province");

		Object ocity = session.getAttribute("city");

		if (oprovince != null) {

			province = oprovince.toString();
			area = province;
		}
		if (ocity != null) {

			city = ocity.toString();

		}

		nl = ndao.Hotarea(8, area);
		request.setAttribute("nl", nl);
		request.setAttribute("Address", area);
		request.setAttribute("province", province);
		request.setAttribute("city", city);
		System.out.println("" + area + nl.size() + "����");
		return Action.SUCCESS;
	}

	/**
	 * ��ѯ�������� �Ͱ˴����� Ϊר�ҵ�
	 * 
	 * @throws IOException
	 */
	public void Newsexpert() throws IOException {

		nl = ndao.area(area);
		int ext = nl.size();
		System.out.println(area + " ר��������" + ext);
		for (int i = 0; i < ext; i++) {
			System.out.println(nl.get(i).getArea() + nl.get(i).getTime());
		}
		if (ext >= 1) {
			util.Out().print(util.ToJson(nl));
		} else {
			util.Out().print("null");
		}
	}

	/**
	 * ��ҳ ��ѯ ��������
	 * 
	 * @throws IOException
	 */
	public void Hotareaf() throws IOException {

		if (pageSize <= 0) {
			pageSize = 10;
		}

		if (currentPage <= 0) {
			currentPage = 1;
		}
		if (area == null) {
			util.Out().print("ʡ��Ϊ�գ�");
			return;
		}

		int a = 0;
		nl = ndao.Hotarea(0, area);
		a = nl.size();
		if (a % pageSize == 0) {
			a = a / pageSize;
		} else {
			a = a / pageSize + 1;
		}
		if (currentPage > a) {
			currentPage = a;
		}

		System.out.println(" -��" + a + "ҳ");
		System.out.println("ÿҳ������-" + pageSize);
		System.out.println("��-" + currentPage + "-ҳ");
		nl = ndao.Hotarea(area, pageSize, currentPage);
		String result = "{\"nl\":" + util.ToJson(nl) + ",\"cpe\":" + a
				+ ",\"currentPage\":" + currentPage + "}";

		if (nl.size() > 0) {
			util.Out().print(result);
		} else {
			util.Out().print("null");
		}
	}

	/**
	 * ��ҳ ��ѯ ��������
	 * 
	 * @throws IOException
	 */
	public void cah() throws IOException {

		if (pageSize <= 0) {
			pageSize = 10;
		}

		if (currentPage <= 0) {
			currentPage = 10;
		}
		nl = ndao.cah(0);
		int a = 0;

		a = nl.size();
		if (a % pageSize == 0) {
			a = a / pageSize;
		} else {
			a = a / pageSize + 1;
		}
		if (currentPage > a) {
			currentPage = a;
		}

		System.out.println(" -��" + a + "ҳ");
		System.out.println("ÿҳ������-" + pageSize);
		System.out.println("��-" + currentPage + "-ҳ");
		nl = ndao.cah(pageSize, currentPage);
		String result = "{\"nl\":" + util.ToJson(nl) + ",\"cpe\":" + a
				+ ",\"currentPage\":" + currentPage + "}";

		if (nl.size() > 0) {
			util.Out().print(result);
		} else {
			util.Out().print("null");
		}
	}

	/**
	 * ��ҳ ��ѯ �˴���type
	 * 
	 * @throws IOException
	 */
	public void newtype() throws IOException {
		try {
			if (pageSize <= 0) {
				pageSize = 10;

			}
			if (tp <= 0) {
				util.Out().print("tp==null��");
				return;
			}

			System.out.println("����newtype");
			System.out.println("����" + tp);
			
			int a = ndao.type(0, tp).size();

			if (a % pageSize == 0) {
				a = a / pageSize;
			} else {
				a = a / pageSize + 1;
			}
			if (currentPage > a) {
				currentPage = a;
			}
			if (currentPage <= 0) {
				currentPage = 1;
			}

			System.out.println( " -��" + a + "ҳ");
			System.out.println("ÿҳ������-" + pageSize);
			System.out.println("��-" + currentPage + "-ҳ");
			nl = ndao.type(0, tp, pageSize, currentPage);
			System.out.println(type);
			String result = "{\"nl\":" + util.ToJson(nl) + ",\"cpe\":" + a
					+ ",\"currentPage\":" + currentPage + "}";

			if (nl.size() > 0) {
				util.Out().print(result);
			} else {
				util.Out().print("null");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * ͨ��idɾ������
	 * 
	 * @throw
	 * @throws IOException
	 * @throws ServletException
	 */
	public void NewsRM() throws IOException {

		n = ndao.byid(newsid);

		if (n != null) {

			cl = cdao.Alln(newsid);

			rl = rdao.Alln(newsid);

			for (int i = 0; i < cl.size(); i++) {

				cdao.remove(cl.get(i));
			}

			for (int i = 0; i < rl.size(); i++) {

				rdao.remove(rl.get(i));
			}
			boolean b = true;
			if (n.getImg() != null) {
				File file = new File(ServletActionContext.getServletContext()
						.getRealPath(n.getImg()));
				b = file.delete();
				System.out.println("����Сͼɾ���Ƿ�ɹ���" + b);
			}

			ndao.rm(n);

			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/Newsget");
		} else {
			util.Out().print("ɾ��ʧ�� û��Ҫɾ�������ţ�");

		}

	}

	/**
	 * ��ҳ��ѯ
	 * 
	 * @return
	 * @throws IOException
	 */

	public String Newsget() throws IOException {
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			util.Out().print("ϵͳ�����û�е��룡�����µ������ԣ�");
			return null;
		}
		if (admin.getLevel() > 2) {
			util.Out().print("ϵͳ�����û�е��룡�����µ������ԣ�");
			return null;
		}

		pageSize = 20;

		System.out.println("currentPage:" + currentPage);
		if (currentPage < 1) {
			currentPage = 1;
		}
		int n = 0;
		
		List<News> l = new ArrayList<News>();
		if (admin.getLevel() == 2) {
			l = ndao.Pagination(pageSize, currentPage, admin.getAdmin());
			n = ndao.Hottimes(admin.getAdmin());
		} else {
			l = ndao.Pagination(pageSize, currentPage);
			n = ndao.Hottimes(0);
		}
		if (n % 20 == 0) {
			n = n / 20;
		} else {
			n = n / 20 + 1;
		}
		if (currentPage >= n) {
			currentPage = n;
		}

		request.setAttribute("l", l);
		request.setAttribute("n", n);
		request.setAttribute("currentPage", currentPage);
		return Action.SUCCESS;
	}

	/**
	 * �鿴��ҳ���� 12/10
	 * 
	 * @throws IOException
	 */
	public void NewsA10() throws IOException {
		try {
			System.out.println("ʱ����" + util.getNowTime());
			System.out.println("������NewsA10!!");
			int day = 14;
			System.out.println(util.getNumTime(day) + "ǰ" + day + "�죿");
			List<News> nt = new ArrayList<News>();
			num = 11;

			nt = ndao.Hottime(num);

			List<News> nh = new ArrayList<News>();
			num = 3;

			String htime = util.getMondayOfWeek1();
			String time = util.getMondayOfWeek7();
			System.out.println("����1��" + htime + "��������" + time);

			if (ndao.cah(num, time, htime).size() != 3) {
				htime = util.getNumTime(10);

				if (ndao.cah(num, time, htime).size() != 3) {
					htime = util.getNumTime(15);

					if (ndao.cah(num, time, htime).size() != 3) {
						htime = util.getNumTime(30);

						if (ndao.cah(num, time, htime).size() != 3) {
							htime = util.getNumTime(365);

							if (ndao.cah(num, time, htime).size() != 3) {
								htime = util.getNumTime(720);
							}
						}
					}
				}
			}
			nh = ndao.cah(num, time, htime);

			List<News> n4 = new ArrayList<News>();

			num = 2;
			// ��ʡ �������� С��2
			if (ndao.area(area, num).size() < 2) {
				System.out.println("��ʡ����С��2��");
				

			} else {
				num = 2;
				System.out.println("��ʡ����");
				n4 = ndao.area(area, num);

			}

			System.out.println("ʡ��" + area);
			System.out.println("�У�" + areas);

			
			List<News> n5 = new ArrayList<News>();
		

			List<News> n8 = new ArrayList<News>();
			

			List<News> n9 = new ArrayList<News>();
			
			System.out.println("ȫ��������-" + nt.size() + ",ȫ������-" + nh.size()
					+ "��������-" + n4.size() + "������վ" + n5.size() + "��������"
					+ n8.size() + "�����" + n9.size());
			String result = "{\"Hottime\":" + util.ToJson(nt) + ",\"Hottest\":"
					+ util.ToJson(nh) + ",\"Hotarea\":" + util.ToJson(n4)
					+ ",\"typeqs\":" + util.ToJson(n5) + ",\"typejk\":"
					+ util.ToJson(n8) + ",\"typegj\":" + util.ToJson(n9) + "}";
			util.Out().print(result);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

	/**
	 * �鿴��ҳ���� 2014/12/10
	 * 
	 * @throws IOException
	 */
	public String webNewsA10() throws IOException {
		try {
			User u = (User) session.getAttribute("u");
			if (u != null) {

				System.out.println("��ȡ���û���Ϣ��");

				area = u.getAddress();
				// �м�
				areas = u.getAddcity();
			}
			System.out.println("ʱ����" + util.getNowTime());
			System.out.println("������webNewsA10!!");
			int day = 14;
			System.out.println(util.getNumTime(day) + "ǰ" + day + "�죿");
			List<News> nt = new ArrayList<News>();
			num = 11;

			// ȫ������
			nt = ndao.Hottime(num);

			List<News> nh = new ArrayList<News>();
			num = 3;

			// ����
			String htime = util.getMondayOfWeek1();
			String time = util.getMondayOfWeek7();
			System.out.println("����1��" + htime + "��������" + time);

			if (ndao.cah(num, time, htime).size() != 3) {
				htime = util.getNumTime(10);

				if (ndao.cah(num, time, htime).size() != 3) {
					htime = util.getNumTime(15);

					if (ndao.cah(num, time, htime).size() != 3) {
						htime = util.getNumTime(30);

						if (ndao.cah(num, time, htime).size() != 3) {
							htime = util.getNumTime(365);

							if (ndao.cah(num, time, htime).size() != 3) {
								htime = util.getNumTime(720);
							}
						}
					}
				}
			}
			nh = ndao.cah(num, time, htime);

			List<News> n4 = new ArrayList<News>();

			num = 2;

			if (ndao.area(area, num).size() < 2) {

				System.out.println("��ʡ����С��2��");
				n4 = ndao.area("����", num);

			} else {
				num = 2;
				System.out.println("��ʡ����");
				n4 = ndao.area(area, num);

			}

			System.out.println("ʡ��" + area);
			System.out.println("�У�" + areas);

			System.out.println("ȫ��������-" + nt.size() + ",ȫ������-" + nh.size()
					+ "��������-" + n4.size());
			
			List<News> n5 = new ArrayList<News>();
			

			List<News> n8 = new ArrayList<News>();
			

			System.out.println("ʱ������" + nt.size() + "��������" + nh.size() + "��������"
					+ n4.size() + "���ɵ�����1--" + n5.get(0).getType()
					+ "--����������1---" + n8.get(0).getType());

			request.setAttribute("Hottime", nt);

			request.setAttribute("Hottest", nh);

			request.setAttribute("Hotarea", n4);

			request.setAttribute("typeqs", n5);

			request.setAttribute("typejk", n8);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return Action.SUCCESS;

	}

	/**
	 * ��ѯȫ���������� 12/09
	 */

	public void Cah() {
		try {

			num = 3;
			String htime = util.getMondayOfWeek1();
			String time = util.getMondayOfWeek7();
			System.out.println("����1��" + htime + "��������" + time);

			if (ndao.cah(num, time, htime).size() != 3) {
				htime = util.getNumTime(10);

				if (ndao.cah(num, time, htime).size() != 3) {
					htime = util.getNumTime(15);

					if (ndao.cah(num, time, htime).size() != 3) {
						htime = util.getNumTime(30);

						if (ndao.cah(num, time, htime).size() != 3) {
							htime = util.getNumTime(365);
						}
					}
				}
			}
			util.Out().print(util.ToJson(ndao.cah(0, time, htime)));

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * �鿴��ҳ���� 12/9
	 * 
	 * @throws IOException
	 */
	public void NewsA() throws IOException {
		try {
			System.out.println("ʱ����" + util.getNowTime());
			System.out.println("������NewsA");
			List<News> nt = new ArrayList<News>();
			num = 4;

			nt = ndao.Hottime(num);
			List<News> nh = new ArrayList<News>();
			num = 3;

			String htime = util.getMondayOfWeek1();
			String time = util.getMondayOfWeek7();
			System.out.println("����1��" + htime + "��������" + time);

			if (ndao.cah(num, time, htime).size() != 3) {
				htime = util.getNumTime(10);

				if (ndao.cah(num, time, htime).size() != 3) {
					htime = util.getNumTime(15);

					if (ndao.cah(num, time, htime).size() != 3) {
						htime = util.getNumTime(30);

						if (ndao.cah(num, time, htime).size() != 3) {
							htime = util.getNumTime(365);
						}
					}
				}
			}
			System.out.println("ȷ��������" + htime);
			nh = ndao.cah(num, time, htime);

			List<News> n4 = new ArrayList<News>();
			num = 2;

			if (ndao.area(area, num).size() < 2) {
				System.out.println("��ʡ����С��2��");
				

			} else {
				num = 2;
				System.out.println("��ʡ����");
				n4 = ndao.area(area, num);

			}

			System.out.println("ʡ��" + area);
			System.out.println("�У�" + areas);

			System.out.println("ȫ��������-" + nt.size() + ",ȫ������-" + nh.size()
					+ "��������-" + n4.size());
			// 1��ȫ������
			String result = "{\"Hottime\":" + util.ToJson(nt) + ",\"Hottest\":"
					+ util.ToJson(nh) + ",\"Hotarea\":" + util.ToJson(n4) + "}";
			util.Out().print(result);
			System.out.println("ʱ������" + nt.size() + "��������" + nh.size() + "��������"
					+ n4.size());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}

	}

	/**
	 * ��ѯ��������
	 */
	public void Hotarea() {
		try {

			nl = ndao.Hotarea(0, area);
			if (nl.size() == 0) {
				util.Out().print("null");
			}
			util.Out().print(util.ToJson(nl));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * ȫ����������
	 */
	public void Hottest() {
		try {

			nl = ndao.Hottest(0);

			util.Out().print(util.ToJson(nl));
			;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * �˴��� ��ѯ type
	 * 
	 * @throws IOException
	 */
	public void type() throws IOException {
		try {
			System.out.println("����type");
			System.out.println("����" + tp);
			switch (tp) {

			case 1:
				type = "��ѧָ��";

				break;
			case 2:
				type = "���Ӿ���";

				break;
			case 3:
				type = "�ɳ�·��";
				break;
			case 4:
				type = "������ѧ";
				break;
			case 5:
				type = "��Ȥ�س�";
				break;
			case 6:
				type = "���˽���";
				break;
			case 7:
				type = "��������";
				break;
			case 8:
				type = "������վ";
				break;
			case 9:
				type = "�����";
				break;

			default:
				System.out.println("��������ȷ����tp");
				type = "No";
				break;
			}

			System.out.println(type);

		

			if (nl.size() > 0) {
				util.Out().print(util.ToJson(nl));
			} else {
				util.Out().print("null");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * �˴��� ��4С�� ��ѯ type
	 * 
	 * @throws IOException
	 */
	public void types() throws IOException {
		try {
			switch (tp) {
			case 1:
				type = "����С";

				break;
			case 2:
				type = "С����";
				break;
			case 3:
				type = "�п�";
				break;
			case 4:
				type = "�߿�";
				break;

			default:
				System.out.println("��������ȷ����tp");
				util.Out().print("not tp");
				return;
			}

			System.out.println("������" + tp + "�����" + type);

			int a = ndao.types(type).size();

			if (pageSize <= 0) {
				pageSize = 10;
			}
			if (a % pageSize == 0) {
				a = a / pageSize;
			} else {
				a = a / pageSize + 1;
			}
			if (currentPage > a) {
				currentPage = a;
			}
			if (currentPage <= 0) {
				currentPage = 1;
			}

			System.out.println(type + " -��" + a + "ҳ");
			System.out.println("ÿҳ������-" + pageSize);
			System.out.println("��-" + currentPage + "-ҳ");
			nl = ndao.types(type, pageSize, currentPage);
			System.out.println(type);
			String result = "{\"nl\":" + util.ToJson(nl) + ",\"cpe\":" + a
					+ ",\"currentPage\":" + currentPage + "}";

			if (nl.size() > 0) {
				util.Out().print(result);
			} else {
				util.Out().print("null");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * �ϴ�����
	 * 
	 * @return
	 * @throws IOException
	 */

	public String UPtext() throws IOException {
		// 1���������ʱ��
		String rt = "";

		System.out.println("�������ϴ�����UPtext");
		savePath = "/Newsimg" + "/"
				+ util.getfileName(1) + "/" + util.getfileName(2) + "/"
				+ util.getfileName(1) + util.getfileName(2)
				+ util.getfileName(3);
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			util.Out().print("ϵͳ�����û�е��룡�����µ������ԣ�");
			return null;
		}
		if (admin.getLevel() > 2) {
			util.Out().print("ϵͳ�����û�е��룡�����µ������ԣ�");
			return null;
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		htmlData = request.getParameter("content1");
		List<String> images = new ArrayList<String>();
		System.out.println("��������" + htmlData);
		if (htmlData == null) {
			util.Out().print("��������û�л�ȡ��" + htmlData);
			return null;
		}
		if (htmlData.length() < 3) {
			util.Out().print("��������û�л�ȡ��" + htmlData);
			return null;
		}
		News n = new News();
		List<NewsLabel> nbl=ndao.getNewsLabelAll();
		StringBuffer sb=new StringBuffer();
		
		for(int i=0;i<nbl.size();i++){
			NewsLabel nb=nbl.get(i);
			if(nb!=null)
			{
				if(htmlData.contains(nb.getLabel())){
					sb.append(nb.getLabel()+",");
				}
			}
		}
		
		if(sb!=null){
		n.setLabel(sb.toString());
		}
		n.setTitle(title);
		n.setSummary(summary);
		n.setContent(htmlData);
		sb=new StringBuffer();
		if (imgFile1 != null || imgFile2 != null || imgFile3 != null) {

			if (imgFile1 != null) {
				images.add(util.ufileToServer(savePath, imgFile1, "jpg"));
			}
			if (imgFile2 != null) {
				images.add(util.ufileToServer(savePath, imgFile2, "jpg"));
			}
			if (imgFile3 != null) {
				images.add(util.ufileToServer(savePath, imgFile3, "jpg"));
			}
			if (images != null) {
				for (int i = 0; i < images.size(); i++) {
					System.out.println("·��" + images.get(i));
				
						sb.append(images.get(i)+",");
					

				}
			}
			if(sb!=null){
			n.setImg(sb.toString());
			}
			// String [] s=n.getImg().toString().split("xy-x");
			// for(int i=0;i<s.length;i++){
			// System.out.println("��ȡ��·��---"+s[i]);
			// }

		} else {

			images = util.getImgs(htmlData);
			if (images != null) {
				for (int i = 0; i < images.size(); i++) {
					System.out.println("·�� "+images.get(i).replaceAll("/PerfectBefriend","")+",");
					
					
						sb.append(images.get(i).replaceAll("/PerfectBefriend","")+",");
					
				}
			}
			/**
			 * System.out.println(sb.toString()); String []
			 * s=sb.toString().split(","); for(int i=0;i<s.length;i++){
			 * System.out.println("��ȡ��·��"+s[i]); }
			 */
			System.out.println("���� "+sb.toString());
			n.setImg(sb.toString());

		}

		if (timet == null) {
			timet = util.getNowTime();
		}

		if (expert == 1) {
			try {
				n.setTime(util.RandomTime("2014-01-01  00:00:00",
						"2015-01-01  00:00:00"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rt = "A";
		} else {
			n.setTime(timet);
			rt = "B";
		}

	
		
		if (area.length() >= 2) {
			n.setArea(area);
			n.setAreas(areas);
		}
		n.setCollectnum(0);
		n.setCah(0);
		n.setHits(0);
		n.setReviews(0);
		n.setExpert(expert);
		n.setAdmin(admin.getAdmin());
		n.setType(itype);
		ndao.Save(n);

		return rt;

		/**
		 * if (imgFile == null) { util.Out().print("��û���ϴ�Сͼ �������������ԣ�"); return
		 * null; } if (imgFilemax == null) {
		 * util.Out().print("��û���ϴ���ͼ�������������ԣ�"); return null; }
		 * 
		 * BufferedImage sourceImg = ImageIO .read(new
		 * FileInputStream(imgFile)); BufferedImage sourceImgmax =
		 * ImageIO.read(new FileInputStream( imgFilemax));
		 * 
		 * 
		 * float fimg = util.fileSize(imgFile); float fimgmax =
		 * util.fileSize(imgFilemax);
		 * 
		 * if (sourceImgmax.getWidth() != 720 || sourceImgmax.getHeight() !=
		 * 360) { util.Out().print("��ͼ�ߴ�Ϊ 720*360 �������¼���£������������ԣ�"); return
		 * null; } if (sourceImg.getWidth() != 180 || sourceImg.getHeight() !=
		 * 140) { util.Out().print("Сͼ�ߴ�Ϊ 180*140 �������¼���£������������ԣ�"); return
		 * null; }
		 * 
		 * if (fimg > 512.00) {
		 * util.Out().print("Сͼ��СΪ 0.5MB ���£��������¼���£������������ԣ�"); return null; } if
		 * (fimgmax > 1024.00) {
		 * util.Out().print("��ͼ��СΪ 1MB ���£��������¼���£������������ԣ�"); return null; }
		 * System.out.println("��" + sourceImg.getWidth());
		 * System.out.println("��" + sourceImg.getHeight());
		 * System.out.println("�Ƿ���ר�� 0���� 1�� :" + expert);
		 * 
		 * String imgmax = util.fileToServer(savePath, imgFilemax,
		 * imgFilemaxFileName, imgFilemaxContentType, true); String img =
		 * util.fileToServer(savePath, imgFile, imgFileFileName,
		 * imgFileContentType, true);
		 */

	}

	/**
	 * ͨ��id�鿴����
	 * 
	 * @throws IOException
	 */
	public void NewsId() {
		try {
			System.out.println("����NewsId��" + newsid);
			if (newsid > 0) {
				System.out.println("����id" + newsid);
				n = ndao.byid(newsid);
				int nn;
				System.out.println("���ű���" + n.getTitle());
				if (n.getHits() == null) {
					System.out.println("Hitsnull");
					nn = 1;
				} else {
					nn = n.getHits() + 1;
				}

				System.out.println("�����Ϊ" + nn);

				n.setHits(nn);

				if (n.getCollectnum() != null) {
					n.setCah(n.getCollectnum() * 2 + nn);
				} else {
					n.setCah(nn);
				}
				ndao.Upnews(n);
				util.Out().print(util.ToJson(ndao.byid(newsid)));
			} else {
				util.Out().print("null");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * �鿴��ҳ����
	 * 
	 * @throws IOException
	 */
	public void NewsAll() throws IOException {
		try {
			System.out.println("ʱ����" + util.getNowTime());
			System.out.println("������NewsAll");
			List<News> nt = new ArrayList<News>();
			num = 3;

			for (News n2 : ndao.Hottime(num)) {
				nt.add(n2);
				System.out.println("ȫ����������id-" + n2.getId());
			}
			List<News> nn = new ArrayList<News>();
			num = 2;

			for (News n : ndao.Hottest(num)) {
				System.out.println("��������id-" + n.getId());
				nn.add(n);
			}
			List<News> n4 = new ArrayList<News>();
			num = 2;
			if (ndao.Hotarea(num, area).size() < 2) {
				area = "����";
				areas = "������";
			}
			for (News ns : ndao.Hotarea(num, area)) {
				n4.add(ns);
			}
			System.out.println("ʡ��" + area);
			System.out.println("�У�" + areas);

			System.out.println("ȫ��������-" + nt.size() + ",ȫ������-" + nn.size()
					+ "��������-" + n4.size());

			String result = "{\"Hottime\":" + util.ToJson(nt) + ",\"Hottest\":"
					+ util.ToJson(nn) + ",\"Hotarea\":" + util.ToJson(n4) + "}";
			util.Out().print(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}

	}

	/**
	 * ����ղ� ͨ�� �û�id ����id
	 * 
	 * @throws IOException
	 */
	public void Csave() throws IOException {
		try {
			System.out.println("���������ղ�userid:" + userid + "newsid:" + newsid);
			if (cdao.unid(userid, newsid) != null) {
				System.out.println("�Ѿ��ղع�!");
				util.Out().print(false);
			} else {
				User u = userdao.byid(userid);
				if (u == null || newsid <= 0) {
					util.Out().print(false);
					return;
				}
				c.setNewsid(newsid);
				c.setUserid(userid);
				c.setTime(util.getNowTime());
				cdao.save(c);

				n = ndao.byid(newsid);

				int n1;

				if (n.getCollectnum() != null) {
					n1 = n.getCollectnum() + 1;
				} else {
					n1 = 1;
				}

				if (n.getHits() != null) {
					n.setCah(n1 * 2 + n.getHits());
				} else {
					n.setCah(n1 * 2);
				}

				n.setCollectnum(n1);

				ndao.Upnews(n);
				System.out.println("�ղسɹ�!");
				util.Out().print(true);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * ��̨����鿴��������
	 * 
	 * @throws IOException
	 */
	public String adminNewsId() throws IOException {

		System.out.println("��̨����鿴��������ͨ��id��ѯ����" + id);
		if (id <= 0) {
			util.Out().print("û�л�ȡ������id");
			System.out.println("û�л�ȡ������id");
			return null;
		}

		rl = rdao.Alln(id);
		if (rl.size() != 0) {
			System.out.println("������");
			for (int i = 0; i < rl.size(); i++) {

				ul.add(userdao.byUsernameAccnumnoPhone(rl.get(i).getUsername()));
			}
			System.out.println("����" + rl.size());

			request.setAttribute("ul", ul);
			request.setAttribute("rl", rl);
		}

		n = ndao.byid(id);

		n.setHits(n.getHits() + 1);

		n.setCah(n.getCah() + 1);

		System.out.println("�����:" + n.getHits() + "�ղ���+�����:" + n.getCah()
				+ "�ղ���:" + n.getCollectnum());

		ndao.Upnews(n);
		request.setAttribute("n", n);

		return Action.SUCCESS;
	}

	/**
	 * ��̨����鿴��������
	 * 
	 * @throws IOException
	 */
	public String adminNewsIdup() throws IOException {

		System.out.println("��̨����鿴��������ͨ��id��ѯ����" + id);
		if (id <= 0) {
			util.Out().print("û�л�ȡ������id");
			System.out.println("û�л�ȡ������id");
			return null;
		}
		n = ndao.byid(id);		

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
				htmlData = request.getParameter("content1");
				List images = new ArrayList();
				System.out.println("��������" + htmlData);
				if(htmlData==null){
					util.Out().print("��������û�л�ȡ��" + htmlData);
					return null;
				}
				if (htmlData.length()<3) {
					util.Out().print("��������û�л�ȡ��" + htmlData);
					return null;
				}
				

				n.setTitle(title);
				n.setSummary(summary);
				n.setContent(htmlData);
				n.setType(itype);
				n.setArea(area);
				n.setAreas(areas);
				StringBuffer sb = new StringBuffer();
				if (imgFile1 != null || imgFile2 != null || imgFile3 != null) {
					
					if (imgFile1 != null) {
						images.add(util.ufileToServer(savePath, imgFile1, "jpg"));
					}
					if (imgFile2 != null) {
						images.add(util.ufileToServer(savePath, imgFile2, "jpg"));
					}
					if (imgFile3 != null) {
						images.add(util.ufileToServer(savePath, imgFile3, "jpg"));
					}
					if (images != null) {
						for (int i = 0; i < images.size(); i++) {
							System.out.println("·��" + images.get(i));
							if (i == 0) {
								sb.append(images.get(i));
							} else {
								sb.append("," + images.get(i));
							}

						}
					}
					n.setImg(sb.toString());
//					 String [] s=n.getImg().toString().split("xy-x"); 
//		  			 for(int i=0;i<s.length;i++){
//					  System.out.println("��ȡ��·��---"+s[i]); 
//					  }

				} else {
					
					images = util.getImgs(htmlData);
					if (images != null) {
						for (int i = 0; i < images.size(); i++) {
							System.out.println("·��" + images.get(i));
							if (i == 0) {
								sb.append(images.get(i));
							} else {
								sb.append("," + images.get(i));
							}

						}
					}
					/**
					 * System.out.println(sb.toString()); String []
					 * s=sb.toString().split(","); for(int i=0;i<s.length;i++){
					 * System.out.println("��ȡ��·��"+s[i]); }
					 */
					

					n.setImg(sb.toString());

				}

				if (timet==null) {
					timet = util.getNowTime();
				}
				n.setTime(timet);
				ndao.Upnews(n);
		request.setAttribute("n", n);

		return Action.SUCCESS;
	}

	/**
	 * ��̨����鿴��������
	 * 
	 * @throws IOException
	 */
	public String adminNewsIds() throws IOException {

		System.out.println("��̨����鿴��������ͨ��id��ѯ����" + id);
		if (id <= 0) {
			util.Out().print("û�л�ȡ������id");
			System.out.println("û�л�ȡ������id");
			return null;
		}

		n = ndao.byid(id);
		request.setAttribute("n", n);

		return Action.SUCCESS;
	}

	/**
	 * ɾ���ղ� ͨ�� �û�id ����id
	 * 
	 * @throws IOException
	 */
	public void RemoveC() throws IOException {
		try {
			if (cdao.unid(userid, newsid) != null) {
				cdao.remove(cdao.unid(userid, newsid));
				n = ndao.byid(newsid);
				// ��ѯ�����±��ղض��ٴ� ����д��
				System.out.println("�����±��ղ�" + n.getCollectnum());
				int n1 = n.getCollectnum() - 1;
				System.out.println("�����±��ղ�" + n1);
				if (n.getHits() != null) {
					n.setCah(n1 * 2 + n.getHits());
				} else {
					n.setCah(n1 * 2);
				}

				n.setCollectnum(n1);
				ndao.Upnews(n);
				util.Out().print(true);
			} else {
				util.Out().print(false);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * �ж��Ƿ��ղ�
	 */
	public void Whether() {
		try {

			if (cdao.unid(userid, newsid) == null) {
				util.Out().print(false);
			} else {
				util.Out().print(true);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * ��ѯ�ղ� ͨ���û�id���в�ѯ
	 */
	public void SearchBookmark() throws IOException {
		try {
			System.out.println("������SearchBookmark");
			User u = userdao.byid(userid);
			if (u == null) {
				util.Out().print("null");
				return;
			}
			for (Collect c : cdao.Allu(userid)) {
				n = ndao.byid(c.getNewsid());
				nl.add(n);

			}
			System.out.println("�û�" + u.getUsername() + "�ղ���" + nl.size()
					+ "������");
			if (nl.size() == 0) {
				util.Out().print("null");
			} else {

				util.Out().print(util.ToJson(nl));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * ɾ������ ͨ�� ���۵�reviewid �û� username
	 * 
	 * @throws IOException
	 */
	public void RemoveR() throws IOException {
		try {
			System.out.println("ɾ�����۷���RemoveR");
			System.out.println("reviewid+" + reviewid);
			System.out.println("username+" + username);
			if (rdao.byid(reviewid, username) == null) {
				util.Out().print("null");
				System.out.println("����  Ϊ��");
				return;
			}
			newsid = rdao.byid(reviewid, username).getNewsid();
			rdao.remove(rdao.byid(reviewid, username));

			n = ndao.byid(newsid);
			System.out.println("�޸Ĵ���");
			System.out.println("newsid��" + n.getId());
			System.out.println("�����±�����" + rdao.Alln(newsid).size());
			n.setReviews(rdao.Alln(newsid).size());
			ndao.Upnews(n);

			rl = rdao.unid(username, newsid);
			if (rl.size() > 0) {
				System.out.println("������");
				util.Out().print(util.ToJson(rl));
				return;
			} else {
				System.out.println("û������");
				util.Out().print("null");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * ������� ͨ�� �û�username ����newsid
	 * 
	 * @throws IOException
	 */
	public void Rsave() throws IOException {
		try {
			username = "gyn781369549";
			System.out.println("�����������Rsave" + username);

			if (newsid <= 0 || username == null) {
				util.Out().print("null");
				return;

			}

			if (username == null) {
				util.Out().print("null");
				return;
			}
			User u = userdao.byUsernameAccnumnoPhone(username);
			if (u == null) {
				util.Out().print("null");
				return;
			}
			r.setNewsid(newsid);
			r.setUsername(username);
			r.setTime(util.getNowTime());
			r.setReview(review);
			rdao.save(r);
			// ��ѯ�����±����۶��ٴ� ����д��
			n = ndao.byid(newsid);
			if (n == null) {
				util.Out().print("null");
				return;

			}
			rl = rdao.Alln(newsid);
			int a = rl.size();
			System.out.println("�Ƿ�������" + n != null);
			System.out.println("��������" + review);
			System.out.println("newsid��" + n.getId());
			System.out.println("�����±�����" + a);
			n.setReviews(a);
			ndao.Upnews(n);

			if (a > 0) {
				System.out.println("������");
				util.Out().print(util.ToJson(rl));

			} else {
				System.out.println("û������");
				util.Out().print("null");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * ͨ��newsid��ѯ ����
	 * 
	 * @throws IOException
	 */
	public void ReviewsInquiry() throws IOException {
		System.out.println("newsid��-" + newsid);
		rl = rdao.Alln(newsid);
		if (rl.size() > 0) {
			System.out.println("������");
			util.Out().print(util.ToJson(rl));

		} else {
			System.out.println("û������");
			util.Out().print("null");
		}

	}

	/**
	 * ͨ��newsid��ѯ ����
	 * 
	 * @throws IOException
	 */
	public void ReviewsInquiry513() throws IOException {
		System.out.println("newsid��-" + newsid);
		rl = rdao.Alln(newsid);
		if (rl.size() > 0) {
			System.out.println("������");
			for (int i = 0; i < rl.size(); i++) {
				User u1 = userdao.byUsernameAccnumnoPhone(rl.get(i)
						.getUsername());

				ul.add(u1);
			}
			System.out.println("������");
			String result = "{\"rl\":" + util.ToJson(rl) + ",\"ul\":"
					+ util.ToJson(ul) + "}";
			util.Out().print(result);

		} else {
			System.out.println("û������");
			util.Out().print("null");
		}

	}

	/**
	 * ͨ��username��ѯ���� ���źͶ�ÿ�����ŵ����� ����
	 * 
	 * @throws IOException
	 */
	public void Reviewsusername() throws IOException {
		try {

			List<Integer> rn = new ArrayList<Integer>();// �ղ� List
			for (Review r1 : rdao.Allu(username)) {

				Boolean b = true;

				for (int i = 0; i < rn.size(); i++) {

					if (rn.get(i) == r1.getNewsid()) {

						b = false;
						break;
					}

				}

				if (b) {
					System.out.println("�����۹�������  �û���������id" + username + "+"
							+ r1.getNewsid() + "ʱ���ǣ�" + r1.getTime());
					rl.add(rdao.unid(username, r1.getNewsid()).get(0));
					nl.add(ndao.byid(r1.getNewsid()));

					rn.add(r1.getNewsid());
				}

			}
			String result = "{\"news\":" + util.ToJson(nl) + ",\"review\":"
					+ util.ToJson(rl) + "}";
			if (nl.size() > 0 && rl.size() > 0) {
				util.Out().print(result);

			} else {
				util.Out().print("null");
			}
		} catch (Exception e) {
			System.out.println("�쳣��" + e.getMessage());
		}
	}

	/**
	 * ͨ���û��� username newsid ��ѯ����
	 * 
	 * @param ndao
	 * @param cdao
	 * @param rdao
	 * @throws IOException
	 */
	public void getRy() throws IOException {

		rl = rdao.unid(username, newsid);
		if (rl.size() <= 0) {
			util.Out().print("null");
		} else {
			util.Out().print(util.ToJson(rl));
		}
	}

	public NewsAction(UserDAO userdao, NewsDAO ndao, CollectDAO cdao,
			ReviewDAO rdao) {
		super();
		this.userdao = userdao;
		this.ndao = ndao;
		this.cdao = cdao;
		this.rdao = rdao;
	}

	public int getReviewid() {
		return reviewid;
	}

	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Collect getC() {
		return c;
	}

	public void setC(Collect c) {
		this.c = c;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getNewsid() {
		return newsid;
	}

	public void setNewsid(int newsid) {
		this.newsid = newsid;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
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

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSavePath() {
		return savePath;
	}

	public String getTimet() {
		return timet;
	}

	public void setTimet(String timet) {
		this.timet = timet;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public int getTp() {
		return tp;
	}

	public void setTp(int tp) {
		this.tp = tp;
	}

	public String getHtmlData() {
		return htmlData;
	}

	public void setHtmlData(String htmlData) {
		this.htmlData = htmlData;
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

	public String getA() {
		return A;
	}

	public void setA(String a) {
		A = a;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getExpert() {
		return expert;
	}

	public void setExpert(int expert) {
		this.expert = expert;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getItypes() {
		return itypes;
	}

	public void setItypes(int itypes) {
		this.itypes = itypes;
	}

	public int getItype() {
		return itype;
	}

	public void setItype(int itype) {
		this.itype = itype;
	}
	public File getXlsxFile() {
		return xlsxFile;
	}
	public void setXlsxFile(File xlsxFile) {
		this.xlsxFile = xlsxFile;
	}

}
