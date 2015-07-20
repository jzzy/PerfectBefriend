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
	 * �û� 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="username")//�û���
	private String username;
	@Column(name="nickname")//�ǳ�
	private String nickname;
	@Column(name="password")//����
	private String password;
	@Column(name="img")//�û�ͷ���ַ
	private String img;
	
	
	@Column(name="stage")//�׶�
	private String stage;
	@Column(name="time")//ע��ʱ��
	private String time;
	@Column(name="address")//��ַ ʡ��
	/**
	 * ��ַ ʡ��
	 */
	private String address;
	
	@Column(name="addcity")//��ַ �м�
	private String addcity;
	@Column(name="phone")//�绰
	private String phone;
	@Column(name="school")//ѧУ
	private String school;
	
	@Column(name="competence")//Ȩ��  0 ��ͨ�û�  1 �м�����Ա 2ʡ������Ա 4�ǽ���
	private int competence;
	@Column(name="gag")//������̳Ȩ��  0 ���Դ�����̳  1 ������  
	private int gag;
	
	@Column(name="loginnum")//�û��������
	private int loginnum;
	
	@Column(name="finaltime")//�û�������ʱ��
	private String finaltime;
	@Column(name="ip")//�û�ip
	private String ip;
	@Column(name="port")//�û�port
	private int port;
	@Column(name="online")//�û� �û�����״̬ 0������ 1����
	private int online;
	@Column(name="accnumno")//�û��˺� 8λ��
	private String accnumno;
	@Column(name="come")// ����null �������Լ����û� 
	private String come;
	@Column(name="os")// 
	private String os;
	
	
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
	public String getAccnumno() {
		return accnumno;
	}
	public void setAccnumno(String accnumno) {
		this.accnumno = accnumno;
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
	

}
