package com.befriend.entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User implements Serializable
{

	/**
	 * 用户 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="username")//用户名
	private String username;
	@Column(name="nickname")//昵称
	private String nickname;
	@Column(name="password")//密码
	private String password;
	@Column(name="img")//用户头像地址
	private String img;
	
	
	@Column(name="stage")//孩子阶段
	private String stage;
	public static final double STAGE = 2D;
	@Column(name="time")//注册时间
	private String time;
	@Column(name="address")//地址 省级
	/**
	 * 地址 省级
	 */
	private String address;
	public static final double PROVINCE = 3D;
	
	@Column(name="addcity")//地址 市级
	private String addcity;
	public static final double CITY = 4D;
	@Column(name="phone")//电话
	private String phone;
	@Column(name="school")//学校
	private String school;
	public static final double SCHOOL = 5D;
	
	@Column(name="competence")//权限  0 普通用户  1 市级管理员 2省级管理员 4是教授
	private int competence;
	@Column(name="gag")//创建论坛权限  0 可以创建论坛  1 不可以  
	private int gag;
	
	@Column(name="loginnum")//用户登入次数
	private int loginnum;
	
	@Column(name="finaltime")//用户最后登入时间
	private String finaltime;
	@Column(name="ip")//用户ip
	private String ip;
	@Column(name="port")//用户port
	private int port;
	@Column(name="online")//用户 用户上线状态 0不在线 1在线
	private int online;
	@Column(name="accnumno")//完美ID 8位的
	private String accnumno;
	@Column(name="come")// 等于null 是我们自己的用户 
	private String come;
	@Column(name="os")// 系统
	private String os;
	@Column(name="mac")// mac
	private String mac;
	@Column(name="sex")//性别  男 女
	private String sex;
	@Column(name="signature")// 个性签名
	private String signature;
	@Column(name="childrenage")// 孩子年龄
	private String childrenage;
	public static final double CHILD_AGE = 4D;
	@Column(name="childrensex")// 孩子性别  男 女
	private String childrensex;
	public static final double CHILD_SEX = 3D;
	
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	public String getChildrenage() {
		return childrenage;
	}
	public void setChildrenage(String childrenage) {
		this.childrenage = childrenage;
	}
	public String getChildrensex() {
		return childrensex;
	}
	public void setChildrensex(String childrensex) {
		this.childrensex = childrensex;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getCome() {
		return come;
	}
	public void setCome(String come) {
		this.come = come;
	}
	public int getPort() {
		return port;
	}
	
	
	
	public void setPort(int port) {
		this.port = port;
	}
	public int getOnline() {
		return online;
	}
	public String getAccnumno() {
		return accnumno;
	}
	public void setAccnumno(String accnumno) {
		this.accnumno = accnumno;
	}
	public void setOnline(int online) {
		this.online = online;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getLoginnum() {
		return loginnum;
	}
	public void setLoginnum(int loginnum) {
		this.loginnum = loginnum;
	}
	public String getFinaltime() {
		return finaltime;
	}
	public void setFinaltime(String finaltime) {
		this.finaltime = finaltime;
	}
	public int getGag() {
		return gag;
	}
	public void setGag(int gag) {
		this.gag = gag;
	}
	public int getCompetence() {
		return competence;
	}
	public void setCompetence(int competence) {
		this.competence = competence;
	}
	public String getAddcity() {
		return addcity;
	}
	public void setAddcity(String addcity) {
		this.addcity = addcity;
	}
	public String getSchool()
	{
		return school;
	}
	public void setSchool(String school)
	{
		this.school = school;
	}
	public String getStage()
	{
		return stage;
	}
	public void setStage(String stage)
	{
		this.stage = stage;
	}
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getNickname()
	{
		return nickname;
	}
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	

}
