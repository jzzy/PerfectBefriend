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

@Entity
@Table(name="users")
public class User implements Serializable
{

	/**
	 * �û� 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Expose
	private Integer id;
	@Column(name="username")
	private String username;
<<<<<<< HEAD
	@Column(name="nickname")
=======
	@Column(name="nickname")//�ǳ�
	@Expose
>>>>>>> 5ba30930a030c51f4cac1af26126b0f7be9c928c
	private String nickname;
	@Column(name="password")
	private String password;
<<<<<<< HEAD
	@Column(name="img")//
	private String img;
	
	
	@Column(name="stage")//
	private String stage;
	public static final double STAGE = 2D;
	@Column(name="time")//
	private String time;
	@Column(name="address")//
	
	private String address;
	public static final double PROVINCE = 3D;
	
	@Column(name="addcity")//
	private String addcity;
	public static final double CITY = 4D;
	@Column(name="phone")//
=======
	@Column(name="img")//�û�ͷ���ַ
	@Expose
	private String img;
	
	
	@Column(name="stage")//���ӽ׶�
	@Expose
	private String stage;
	public static final double STAGE = 2D;
	@Column(name="time")//ע��ʱ��
	@Expose
	private String time;
	@Column(name="address")//��ַ ʡ��
	/**
	 * ��ַ ʡ��
	 */
	@Expose
	private String address;
	public static final double PROVINCE = 3D;
	
	@Column(name="addcity")//��ַ �м�
	@Expose
	private String addcity;
	public static final double CITY = 4D;
	@Column(name="phone")//�绰
	@Expose
>>>>>>> 5ba30930a030c51f4cac1af26126b0f7be9c928c
	private String phone;
	@Column(name="school")//ѧУ
	@Expose
	private String school;
	public static final double SCHOOL = 5D;
	
<<<<<<< HEAD
	@Column(name="competence")//
	private int competence;
	@Column(name="gag")//
	private int gag;
	
	@Column(name="loginnum")//
	private int loginnum;
	
	@Column(name="finaltime")//
	private String finaltime;
	@Column(name="ip")//
	private String ip;
	@Column(name="port")//
	private int port;
	@Column(name="online")//
	private int online;
	@Column(name="accnumno")//
	private String accnumno;
	@Column(name="come")// 
	private String come;
	@Column(name="os")// 
=======
	@Column(name="competence")//Ȩ��  0 ��ͨ�û�  1 �м�����Ա 2ʡ������Ա 4�ǽ���
	@Expose
	private int competence;
	@Column(name="gag")//������̳Ȩ��  0 ���Դ�����̳  1 ������  
	@Expose
	private int gag;
	
	@Column(name="loginnum")//�û��������
	@Expose
	private int loginnum;
	
	@Column(name="finaltime")//�û�������ʱ��
	@Expose
	private String finaltime;
	@Column(name="ip")//�û�ip
	@Expose
	private String ip;
	@Column(name="port")//�û�port
	@Expose
	private int port;
	@Column(name="online")//�û� �û�����״̬ 0������ 1����
	@Expose
	private int online;
	@Column(name="accnumno")//����ID 8λ��
	@Expose
	private String accnumno;
	@Column(name="come")// ����null �������Լ����û� 
	@Expose
	private String come;
	@Column(name="os")// ϵͳ
	@Expose
>>>>>>> 5ba30930a030c51f4cac1af26126b0f7be9c928c
	private String os;
	@Column(name="mac")// mac
	@Expose
	private String mac;
<<<<<<< HEAD
	@Column(name="sex")//
	private String sex;
	@Column(name="signature")// 
	private String signature;
	@Column(name="childrenage")// 
	private String childrenage;
	@Column(name="age")// 
	private String age;
	public static final double CHILD_AGE = 4D;
	@Column(name="childrensex")// 
=======
	@Column(name="sex")//�Ա�  �� Ů
	@Expose
	private String sex;
	@Column(name="signature")// ����ǩ��
	@Expose
	private String signature;
	@Column(name="childrenage")// ��������
	@Expose
	private String childrenage;
	@Column(name="age")// ����
	@Expose
	private String age;
	public static final double CHILD_AGE = 4D;
	@Column(name="childrensex")// �����Ա�  �� Ů
	@Expose
>>>>>>> 5ba30930a030c51f4cac1af26126b0f7be9c928c
	private String childrensex;
	public static final double CHILD_SEX = 3D;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="user")
	@Expose
	private Set<UserGroup> userGroup = new HashSet<UserGroup>();
	
	
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
