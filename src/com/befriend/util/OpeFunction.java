package com.befriend.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.befriend.email.MailSenderInfo;
import com.befriend.email.SimpleMailSender;
import com.befriend.entity.User;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;

/**
 * ������
 * 
 * @author Administrator
 *
 */
public class OpeFunction {
	/**
	 * 
	 * null=true
	 * 
	 * not null=false
	 * @param rstart
	 * @param rend
	 * @return
	 */
	public static boolean isEmpty(String string){
		
		if(string!=null){
			if(StringUtils.isEmpty(string.trim())){
				System.out.println("��");
				return true;
			}else{
				System.out.println("����");
				return false;
			}
		}else{
			System.out.println("��");
			return true;
		}
	}
	
	/**
	 * ��ȡָ����Χ�ڵ����ʱ��
	 * @param rstart
	 * @param rend
	 * @return
	 * @throws ParseException
	 */
	public static String RandomTime(String rstart, String rend)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date start = format.parse(rstart);// ���쿪ʼ����
		Date end = format.parse(rend);// �����������
		long begin = start.getTime();// ��ȡ��ʼʱ���
		long endd = end.getTime();// ��ȡ����ʱ���	
		boolean b = true;
		long rtn = 0;
		while (b) {

			rtn = begin + (long) (Math.random() * (endd - begin));
			// ������ص��ǿ�ʼʱ��ͽ���ʱ�䣬��ݹ���ñ������������ֵ
			if (rtn == start.getTime() || rtn == end.getTime()) {

				continue;

			}

			b = false;
		}
		//System.out.println(rtn);//���ʱ���
		System.out.println("��ȡ�������ʱ��"+format.format(new Date(rtn)));
		return format.format(new Date(rtn));

	}
	/**
	 * ���� html img ��ǩ src�е� ��ַ
	 * 
	 * @param content
	 * @return
	 */
	public static String setImgswidth100(String content) {

		String regEx_img = "<img src=\"(.*?)\".*?>";
		Pattern p_image;
		ArrayList<String> images = new ArrayList<String>();
		Matcher m_image;
		p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(content);

		while (m_image.find()) {
			
			images.add(m_image.group(1));
			//��дд�� 
			String reg = "<img src=\""+m_image.group(1)+"\" .*?>";
			content = content.replaceAll(reg, "<img src=\""+m_image.group(1)+"\" width=\"100%\" />");

		}
		System.out.println("content=="+content);
		return content;
	}
	/**
	 * ���� html img ��ǩ src�е� ��ַ
	 * 
	 * @param content
	 * @return
	 */
	public static List getImgs(String content) {

		String regEx_img = "<img src=\"(.*?)\".*?>";
		Pattern p_image;
		ArrayList<String> images = new ArrayList<String>();
		Matcher m_image;
		p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(content);

		while (m_image.find()) {
			
			images.add(m_image.group(1));
			//��дд�� 
			//m_image.replaceAll("<img src=\""+m_image.group(1)+"\" with=\"100%\" />");

		}

		return images;
	}

	// private static final int FIRST_DAY_OF_WEEK = 0;
	/**
	 * ��ȡʱ��
	 * 1��
	 * 2��
	 * 3��
	 * 4ʱ
	 * 5��
	 * 6��
	 * 
	 * @throws UnknownHostException
	 */
	public static String getDayTime(int reg) {
		Calendar cal = Calendar.getInstance();// ʹ��������
		/**
		System.out.println("��" + cal.get(Calendar.YEAR));
		System.out.println("��" + cal.get(Calendar.MONTH) + 1);
		System.out.println("��" + cal.get(Calendar.DAY_OF_MONTH));
		System.out.println("ʱ" + cal.get(Calendar.HOUR));
		System.out.println("��" + cal.get(Calendar.MINUTE));
		System.out.println("��" + cal.get(Calendar.SECOND));
		*/
		String name="";
		switch (reg) {
		case 1:
			name=Integer.valueOf(cal.get(Calendar.YEAR)).toString();
			break;
		case 2:
			if(cal.get(Calendar.MONTH)+1<10){
			name="0"+Integer.valueOf(cal.get(Calendar.MONTH)+1).toString();
			}else{
				name=Integer.valueOf(cal.get(Calendar.MONTH)+1).toString();
			}
			break;
		case 3:
			if(cal.get(Calendar.DAY_OF_MONTH)<10){
			name="0"+Integer.valueOf(cal.get(Calendar.DAY_OF_MONTH)).toString();
			}else{
				name=Integer.valueOf(cal.get(Calendar.DAY_OF_MONTH)).toString();
			}
			break;
		case 4:
			name=Integer.valueOf(cal.get(Calendar.HOUR)).toString();
			break;
		case 5:
			name=Integer.valueOf(cal.get(Calendar.MINUTE)).toString();
			break;
		case 6:
			name=Integer.valueOf(cal.get(Calendar.SECOND)).toString();
			break;

		default:
			name=Integer.valueOf(cal.get(Calendar.YEAR)).toString();
			break;
		}
		return name;
	}

	/**
	 * ��ȡ����ip
	 * 
	 * @throws UnknownHostException
	 */
	public static String getLIP() throws UnknownHostException {
		InetAddress addr = InetAddress.getLocalHost();
		return addr.getHostAddress().toString();
	}

	/**
	 * ͨ��key ��json�� ȡֵ
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static User getJsonKeyUs(String str) {
		Gson gs = new Gson();
		User u = gs.fromJson(str, User.class);
		return u;

	}

	/**
	 * ͨ��key ��json�� ȡֵ
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static String getJsonKey(String str, String name) {
		Gson gs = new Gson();
		Map<String, String> map = gs.fromJson(str, Map.class);
		return map.get(name);

	}

	/**
	 * ����sha1����
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static String sha1(String str) throws NoSuchAlgorithmException {
		try {

			StringBuffer sb = new StringBuffer();
			// ѡ��sha1����
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			// ����sha1����
			digest.update(str.getBytes());
			// byte����
			byte[] digbyte = digest.digest();
			// ����byte����
			for (byte b : digbyte) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 
	 *
	 */
	public static String ufileToServer(String path, File file,String fileType) throws IOException {

		String realpath = ServletActionContext.getServletContext().getRealPath(
				path);
		if (file != null) {
			File savedir = new File(realpath);
			if (!savedir.getParentFile().exists())
				savedir.getParentFile().mkdirs();
			File saveFile;
			saveFile = new File(savedir, java.util.UUID.randomUUID() + "."
						+ fileType);

			FileUtils.copyFile(file, saveFile);
			return path + "/" + saveFile.getName();
		}

		return null;

	}

	/**
	 * ɾ���ļ�
	 *
	 */
	public static Boolean fileRemove(String path) throws IOException {
		Boolean b = true;
		File file = new File(ServletActionContext.getServletContext()
				.getRealPath(path));
		b = file.delete();

		return b;

	}

	/**
	 * �ļ��ϴ���������
	 *
	 */
	public static String fileToServer(String path, File file, String fileName,
			String fileType, boolean reName) throws IOException {

		String realpath = ServletActionContext.getServletContext().getRealPath(
				path);// ������·��
		if (file != null) {
			File savedir = new File(realpath);// ����Ŀ¼
			if (!savedir.getParentFile().exists())
				savedir.getParentFile().mkdirs();
			File saveFile;
			fileType = fileName.substring(fileName.lastIndexOf(".") + 1);

			// �������� ��������
			if (reName) {

				saveFile = new File(savedir, java.util.UUID.randomUUID() + "."
						+ fileType);

				FileUtils.copyFile(file, saveFile);
				return path + "/" + saveFile.getName();
			} else
				saveFile = new File(savedir, fileName);

			FileUtils.copyFile(file, saveFile);

			return path + "/" + saveFile.getName();
		}

		return null;

	}

	/**
	 * ��ȡ�ļ��Ĵ�С ��λΪKB,������λС��
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static float fileSize(File file) throws IOException {

		FileInputStream fis = new FileInputStream(file);
		float size = 0;
		size = fis.available() / 1024;
		BigDecimal b = new BigDecimal(size);
		float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		return f1;
	}

	/**
	 * ��ȡ�ļ��Ĵ�С ��λΪMB�� ,������λС��
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static float fileSizem(File file) throws IOException {

		FileInputStream fis = new FileInputStream(file);
		float size = 0;
		size = fis.available() / 1024 / 1024;
		BigDecimal b = new BigDecimal(size);
		float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		return f1;
	}

	/**
	 * ��ñ���ʱ��
	 * 
	 * @return
	 */
	public static String getNowTime() {

		Date dt = new Date();
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return matter1.format(dt);

	}

	/**
	 * ��ñ���ʱ��
	 * 
	 * @return
	 */
	public static String getSFMTime() {
		//
		Date dt = new Date();
		SimpleDateFormat matter1 = new SimpleDateFormat("HH:mm:ss");
		return matter1.format(dt);

	}

	/**
	 * +1 ������� -1��ȡ����
	 * 
	 * @return
	 */
	public static String getNumTime(int day) {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -day); // �õ�ǰһ��
		Date date = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);

	}

	/**
	 * ����1 ʱ��
	 * 
	 * @param date
	 * @return
	 */
	public static String getMondayOfWeek1() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		cal.set(Calendar.DAY_OF_WEEK, 2); // ��ȡ����һ������

		return df.format(cal.getTime());
	}

	/**
	 * ������ ʱ��
	 * 
	 * @param date
	 * @return
	 */
	public static String getMondayOfWeek7() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		cal.set(Calendar.DAY_OF_WEEK, 1); // ��

		return df.format(cal.getTime());
	}

	/**
	 * ��װresponse
	 * 
	 * @return
	 * @throws IOException
	 */
	public static PrintWriter Out() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		return out;

	}

	/**
	 * ��װGson to Json
	 * 
	 * @param object
	 * @return
	 */
	public static String ToJson(Object object) {
		Gson gson = new Gson();
		if (object != null) {
			return gson.toJson(object);
		} else {
			return null;
		}

	}

	/**
	 * ��ȡ session
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static Object getsession() {

		return ActionContext.getContext().getSession();

	}

	/**
	 * ��ȡ request
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static HttpServletRequest request()
			throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");

		return request;

	}

	/**
	 * ��ȡ response
	 * 
	 * @return
	 */
	public static <HHttpServletResponse> HHttpServletResponse response() {
		HHttpServletResponse response = (HHttpServletResponse) ServletActionContext
				.getResponse();

		return response;

	}

	/**
	 * gyn �����ʼ����Ͷ��� ��ҪEmail
	 */
	public static void Email(String Email, String information)
			throws IOException {
		// �����ʼ�
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.chaimiyouxi.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("system@chaimiyouxi.com");
		mailInfo.setPassword("nandayong11");// ��������
		mailInfo.setFromAddress("system@chaimiyouxi.com");
		// ���͵ĵ�ַ
		mailInfo.setToAddress(Email);
		// ����
		mailInfo.setSubject("�ҳ�����û����鷴����Ϣ");
		// ʱ��

		mailInfo.setContent("<h1 style='color: red;'>" + getNowTime() + "<br>"
				+ information + "<h1>");
		// �������Ҫ�������ʼ�
		SimpleMailSender sms = new SimpleMailSender();
		// sms.sendTextMail(mailInfo);//���������ʽ
		sms.sendHtmlMail(mailInfo);// ����html��ʽ
	}

	/**
	 * ��ȡ�ͻ���ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

	/**
	 * ���ֻ��ŷ�����Ϣ
	 */
	public static void setphone(String phone, String textp) {
		try {
			String account = "cf_wcsk_jztd";// �û��� cf_wcsk_jztd
			String pwd = "wcsk1212";// ���� wcsk1212
			String postUrl = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";// ��ַ
			// ����Ҫ���͵���Ϣ
			// String content = new String(textp+"!");
			URL url = new URL(postUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);// ���������ύ��Ϣ
			connection.setRequestMethod("POST");// ��ҳ�ύ��ʽ��GET������POST��
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Connection", "Keep-Alive");
			StringBuffer sb = new StringBuffer();
			sb.append("&account=" + account);
			sb.append("&password=" + pwd);
			sb.append("&content=" + textp);
			sb.append("&mobile=" + phone);// Ҫ���͵��ֻ���
			OutputStream os = connection.getOutputStream();
			os.write(sb.toString().getBytes());
			os.close();

			String line, result = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			in.close();
			System.out.println(result);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	public static void main(String[] args) throws ParseException {
		
		System.out.println(setImgswidth100("111<img src=\"/PerfectBefriend/kindeditor/attached/image/20150727/20150727183630_727.jpg\" alt=\"\"  height=\"360\" title=\"\" align=\"left\" />222<img src=\"/PerfectBefriend/kindeditor/attached/image/20150727/20150727183630_727.jpg\" alt=\"\"  height=\"360\" title=\"\" align=\"left\" />"));
		
		
		
		
		      }  
	

}
