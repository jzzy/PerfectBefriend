package com.befriend.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
@Table(name = "forum_onetype")
public class ForumOneType  implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id;
	@Column(name = "title")
	@Expose
	private String title;
	@Column(name = "adminname")
	@Expose
	private String adminname;
	@Column(name = "time")
	@Expose
	private String time;

	@Column(name = "img")
	@Expose
	private String img;

	@Column(name="supports")
	//被赞次数
	@Expose
	private int supports;
	/**
	 * 建立一对多关系
	 */
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="FOT")
	@Expose
	public List<ForumTwoType> fTT =new ArrayList<ForumTwoType>();//这里直接转会有一个死循环,因为每个ForumOneType有ftt的有
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getSupports() {
		return supports;
	}
	public void setSupports(int supports) {
		this.supports = supports;
	}
	public List<ForumTwoType> getfTT() {
		return fTT;
	}
	public void setfTT(List<ForumTwoType> fTT) {
		this.fTT = fTT;
	}
		
}
