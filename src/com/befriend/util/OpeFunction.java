package com.befriend.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
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
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.annotations.Synchronize;

import sun.misc.GC;

import com.befriend.dao.NewsDAO;
import com.befriend.dao.UserDAO;
import com.befriend.email.MailSenderInfo;
import com.befriend.email.SimpleMailSender;
import com.befriend.entity.News;
import com.befriend.entity.User;
import com.befriend.listener.WechaToken;
import com.befriend.wechat.RefreshAccessToken;
import com.befriend.wechat.WechatKit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.opensymphony.xwork2.ActionContext;
/**
 * Util
 * @author Administrator
 *
 */
public class OpeFunction {
	/**
	 * js返回到html
	 * 
	 * @throws IOException
	 */
	public static void outjS(String URL, String string) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();

		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String loginPage = URL;
		StringBuilder builder = new StringBuilder();
		builder.append("<script type=\"text/javascript\">");
		builder.append("alert('" + string + "');");
		builder.append("window.top.location.href='");
		builder.append(loginPage);
		builder.append("';");
		builder.append("</script>");
		out.print(builder.toString());
	}

	/**
	 * 
	 * null=true
	 * 
	 * not null=false
	 * 
	 * @param rstart
	 * @param rend
	 * @return
	 */
	public static boolean isEmpty(String string) {

		if (string != null) {
			if (StringUtils.isEmpty(string.trim())) {
			
				return true;
			} else {
				
				return false;
			}
		} else {
			return true;
		}
	}

	/**
	 * 两个日期内的随机时间
	 * 
	 * @param rstart
	 * @param rend
	 * @return
	 * @throws ParseException
	 */
	public static String RandomTime(String rstart, String rend)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date start = format.parse(rstart);
		Date end = format.parse(rend);
		long begin = start.getTime();
		long endd = end.getTime();
		boolean b = true;
		long rtn = 0;
		while (b) {

			rtn = begin + (long) (Math.random() * (endd - begin));
			if (rtn == start.getTime() || rtn == end.getTime()) {

				continue;

			}

			b = false;
		}

		return format.format(new Date(rtn));

	}

	/**
	 * 添加img 的属性
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
			String reg = "<img src=\"" + m_image.group(1) + "\" .*?>";
			content = content.replaceAll(reg, "<img src=\"" + m_image.group(1)
					+ "\" width=\"100%\" />");

		}
		System.out.println("content==" + content);
		return content;
	}

	/**
	 *
	 * 
	 * @param 取出img
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
			// ��дд��
			// m_image.replaceAll("<img src=\""+m_image.group(1)+"\" with=\"100%\" />");

		}

		return images;
	}

	/**
	 * 
	 * 
	 * @param 年月日/时分秒
	 * 20150824/024326
	 * 命名
	 */
	public static String getNameDayTime() {
		Calendar cal = Calendar.getInstance();
		String name = "";
			name = Integer.valueOf(cal.get(Calendar.YEAR)).toString();
			name+="/";
			if (cal.get(Calendar.MONTH) + 1 < 10) {
				name = name
						+ "0"
						+ Integer.valueOf(cal.get(Calendar.MONTH) + 1)
								.toString();
			} else {
				name = name
						+ Integer.valueOf(cal.get(Calendar.MONTH) + 1)
								.toString();
			}
			name+="/";
			if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
				name = name
						+ "0"
						+ Integer.valueOf(cal.get(Calendar.DAY_OF_MONTH))
								.toString();
			} else {
				name = name
						+ Integer.valueOf(cal.get(Calendar.DAY_OF_MONTH))
								.toString();
			}
			name+="/";
			if (cal.get(Calendar.HOUR) + 1 < 10) {
				name = name + "0"
						+ Integer.valueOf(cal.get(Calendar.HOUR)).toString();
			} else {
				name = name
						+ Integer.valueOf(cal.get(Calendar.MONTH) + 1)
								.toString();
			}
			name+="/";
			if (cal.get(Calendar.MINUTE) < 10) {
				name = name + "0"
						+ Integer.valueOf(cal.get(Calendar.MINUTE)).toString();
			} else {
				name = name
						+ Integer.valueOf(cal.get(Calendar.MINUTE)).toString();
			}
			if (cal.get(Calendar.SECOND) < 10) {
				name = name + "0"
						+ Integer.valueOf(cal.get(Calendar.SECOND)).toString();
			} else {
				name = name
						+ Integer.valueOf(cal.get(Calendar.SECOND)).toString();
			}
		
		return name;
	}

	/**
	 * 获取本机ip
	 * 
	 * @throws UnknownHostException
	 */
	public static String getLIP() throws UnknownHostException {
		InetAddress addr = InetAddress.getLocalHost();
		return addr.getHostAddress().toString();
	}

	/**
	 * json转实体类 T可为实体类或集合
	 * 
	 * @param json
	 * @param type
	 *        Type.class TypeToken<List<Type>>(){}.getType()
	 * @return
	 */
	public static <T> T fromJson(String json, Type type) {
		Gson gson = new Gson();
		return gson.fromJson(json, type);
	}

	/**
	 * 
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static String getJsonKey(String str, String name) {
		Gson gs = new Gson();
		Map<String, String> map = gs.fromJson(str, Map.class);
		return map.get(name);

	}

	/**
	 * sha1
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static String sha1(String str) throws NoSuchAlgorithmException {
		try {

			StringBuffer sb = new StringBuffer();
			
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			
			digest.update(str.getBytes());
			
			byte[] digbyte = digest.digest();
			
			for (byte b : digbyte) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 重新定义文件格式
	 * 
	 * @param path
	 * @param file
	 * @param fileType
	 * @return
	 * @throws IOException
	 */
	public static String ufileToServer(String path, File file, String fileType)
			throws IOException {

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
 * 删除文件
 * @param path
 * @return
 * @throws IOException
 */
	public static Boolean fileRemove(String path) throws IOException {
		Boolean b = true;
		File file = new File(ServletActionContext.getServletContext()
				.getRealPath(path));
		b = file.delete();

		return b;

	}

	public static String fileToServer(String path, File file, String fileName,
			String fileType, boolean reName) throws IOException {

		String realpath = ServletActionContext.getServletContext().getRealPath(
				path);
		if (file != null) {
			File savedir = new File(realpath);
			if (!savedir.getParentFile().exists())
				savedir.getParentFile().mkdirs();
			File saveFile;
			fileType = fileName.substring(fileName.lastIndexOf(".") + 1);

			
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
	 * 
	 * 
	 * @param fileSize
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

	public static float fileSizem(File file) throws IOException {

		FileInputStream fis = new FileInputStream(file);
		float size = 0;
		size = fis.available() / 1024 / 1024;
		BigDecimal b = new BigDecimal(size);
		float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		return f1;
	}

	
	public static String getNowTime() {

		Date dt = new Date();
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return matter1.format(dt);

	}

	
	public static String getSFMTime() {
		//
		Date dt = new Date();
		SimpleDateFormat matter1 = new SimpleDateFormat("HH:mm:ss");
		return matter1.format(dt);

	}

	public static String getNumTime(int day) {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -day); // �õ�ǰһ��
		Date date = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);

	}

	
	public static String getMondayOfWeek1() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		cal.set(Calendar.DAY_OF_WEEK, 2);

		return df.format(cal.getTime());
	}

	public static String getMondayOfWeek7() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		cal.set(Calendar.DAY_OF_WEEK, 1); // ��

		return df.format(cal.getTime());
	}

	public static PrintWriter Out() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		return out;

	}

	/**
	 * Gson to Json 需要 	@Expose
	 * 
	 * @param object
	 * @return
	 */
	public static <T> String ToJson(T entity) {
		String result = "";
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		result = gson.toJson(entity);
		return result;

	}

	


	public static HttpServletRequest request()
			throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");

		return request;

	}

	
	public static <HHttpServletResponse> HHttpServletResponse response() {
		HHttpServletResponse response = (HHttpServletResponse) ServletActionContext
				.getResponse();

		return response;

	}

	
	public static void Email(String Email, String information)
			throws IOException {
		
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.chaimiyouxi.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("system@chaimiyouxi.com");
		mailInfo.setPassword("nandayong11");
		mailInfo.setFromAddress("system@chaimiyouxi.com");
		mailInfo.setToAddress(Email);
	
		mailInfo.setSubject("title");
		

		mailInfo.setContent("<h1 style='color: red;'>" + getNowTime() + "<br>"
				+ information + "<h1>");
	
		SimpleMailSender sms = new SimpleMailSender();
		// sms.sendTextMail(mailInfo);
		sms.sendHtmlMail(mailInfo);
	}

	public static String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

	
	public static void setphone(String phone, String textp) {
		try {
			String account = "cf_wcsk_jztd";
			String pwd = "wcsk1212";
			String postUrl = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

			URL url = new URL(postUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Connection", "Keep-Alive");
			StringBuffer sb = new StringBuffer();
			sb.append("&account=" + account);
			sb.append("&password=" + pwd);
			sb.append("&content=" + textp);
			sb.append("&mobile=" + phone);
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


	public static void main(String[] args) throws ParseException, InterruptedException, IOException {
//		for (int i = 0; i < args.length; i++) {
//			System.out.println(args[i]);
//		}
//		System.out.println(getNameDayTime());
		
		while (true) {
			//Thread.sleep(10);
			
		System.out.println(WechatKit.sendGet("http://www.baidu.com"));
			
		}
	
		
	}

	public static class SSSS implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			if (o1 > o2)
				return 1;
			else if (o1 < o2)
				return -1;
			else
				return 0;
		}

	}

}
