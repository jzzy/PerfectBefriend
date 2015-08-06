package com.befriend.entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
/**
 * @author sterotto
 * @describe 用户信息
 */
@Entity
@Table(name="users")
public class User implements Serializable
{

	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Expose
	private Integer id;
	@Column(name="username")//锟矫伙拷锟斤拷
	private String username;
	@Column(name="nickname")//锟角筹拷
	@Expose
	private String nickname;
	@Column(name="password")//锟斤拷锟斤拷
	private String password;
	@Column(name="img")//锟矫伙拷头锟斤拷锟街�
	@Expose
	private String img;
	
	
	@Column(name="stage")//锟斤拷锟接阶讹拷
	@Expose
	private String stage;
	public static final double STAGE = 2D;
	@Column(name="time")//注锟斤拷时锟斤拷
	@Expose
	private String time;
	@Column(name="address")//锟斤拷址 省锟斤拷
	/**
	 * 锟斤拷址 省锟斤拷
	 */
	@Expose
	private String address;
	public static final double PROVINCE = 3D;
	
	@Column(name="addcity")//锟斤拷址 锟叫硷拷
	@Expose
	private String addcity;
	public static final double CITY = 4D;
	@Column(name="phone")//锟界话
	@Expose
	private String phone;
	@Column(name="school")//学校
	@Expose
	private String school;
	public static final double SCHOOL = 5D;
	
	@Column(name="competence")//权锟斤拷  0 锟斤拷通锟矫伙拷  1 锟叫硷拷锟斤拷锟斤拷员 2省锟斤拷锟斤拷锟斤拷员 4锟角斤拷锟斤拷
	@Expose
	private int competence;
	@Column(name="gag")//锟斤拷锟斤拷锟斤拷坛权锟斤拷  0 锟斤拷锟皆达拷锟斤拷锟斤拷坛  1 锟斤拷锟斤拷锟斤拷  
	@Expose
	private int gag;
	
	@Column(name="loginnum")//锟矫伙拷锟斤拷锟斤拷锟斤拷锟�
	@Expose
	private int loginnum;
	
	@Column(name="finaltime")//锟矫伙拷锟斤拷锟斤拷锟斤拷时锟斤拷
	@Expose
	private String finaltime;
	@Column(name="ip")//锟矫伙拷ip
	@Expose
	private String ip;
	@Column(name="port")//锟矫伙拷port
	@Expose
	private int port;
	@Column(name="online")//锟矫伙拷 锟矫伙拷锟斤拷锟斤拷状态 0锟斤拷锟斤拷锟斤拷 1锟斤拷锟斤拷
	@Expose
	private int online;
	@Column(name="accnumno")//锟斤拷锟斤拷ID 8位锟斤拷
	@Expose
	private String accnumno;
	@Column(name="come")// 锟斤拷锟斤拷null 锟斤拷锟斤拷锟斤拷锟皆硷拷锟斤拷锟矫伙拷 
	@Expose
	private String come;
	@Column(name="os")// 系统
	@Expose
	private String os;
	@Column(name="mac")// mac
	@Expose
	private String mac;
	@Column(name="sex")//锟皆憋拷  锟斤拷 女
	@Expose
	private String sex;
	@Column(name="signature")// 锟斤拷锟斤拷签锟斤拷
	@Expose
	private String signature;
	@Column(name="childrenage")// 锟斤拷锟斤拷锟斤拷锟斤拷
	@Expose
	private String childrenage;
	@Column(name="age")// 锟斤拷锟斤拷
	@Expose
	private String age;
	public static final double CHILD_AGE = 4D;
	@Column(name="childrensex")// 锟斤拷锟斤拷锟皆憋拷  锟斤拷 女
	@Expose
	private String childrensex;
	public static final double CHILD_SEX = 3D;

	
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="user")
	@Expose
	private Set<UserGroup> userGroup = new HashSet<UserGroup>();//java里面set 或者 list表示多
	
	
	public Set<UserGroup> getUserGroup()
	{
		return userGroup;
	}
	public void setUserGroup(Set<UserGroup> userGroup)
	{
		this.userGroup = userGroup;
	}
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
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
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
	public String getAccnumno() {
		return accnumno;
	}
	public void setAccnumno(String accnumno) {
		this.accnumno = accnumno;
	}
	

}
