package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//新闻表
@Entity
@Table(name = "news")
public class News implements Serializable {

    /**
     * 新闻
     */
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    // 标题
    private String title;
    @Column(name = "summary")
    // 摘要内容
    private String summary;
    @Column(name = "content")
    // 内容  
    private String content;
    @Column(name = "imgmax")
    // 新闻大图片
    private String imgmax;

    @Column(name = "img")
    // 新闻小图片
    private String img;
    @Column(name = "time")
    // 新闻发布时间
    private String time;
    @Column(name = "collectnum")
    // 新闻被收藏次数
    private Integer collectnum;
    @Column(name = "reviews")
    // 新闻被评论次数
    private Integer reviews;
    @Column(name = "area")
    // 新闻地区 省级
    private String area;
    @Column(name = "areas")
    // 新闻地区 市级
    private String areas;
    @Column(name = "type")
    // 新闻类别 8大类
    private String type;
    @Column(name = "types")
    // 新闻类别 8大类的4大类
    private String types;

    @Column(name = "hits")
    // 新闻点击数
    private Integer hits;
    @Column(name = "cah")
    // 新闻点击数+收藏数
    private Integer cah;
    // 管理员账号
    @Column(name = "admin")
    private String admin;
    // 是否为专家发布 0 正常 1专家
    @Column(name = "expert")
    private int expert;
    
 
    
   
    public int getExpert() {
		return expert;
	}
	public void setExpert(int expert) {
		this.expert = expert;
	}
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
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getImgmax() {
        return imgmax;
    }
    public void setImgmax(String imgmax) {
        this.imgmax = imgmax;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public Integer getCollectnum() {
        return collectnum;
    }
    public void setCollectnum(Integer collectnum) {
        this.collectnum = collectnum;
    }
    public Integer getReviews() {
        return reviews;
    }
    public void setReviews(Integer reviews) {
        this.reviews = reviews;
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTypes() {
        return types;
    }
    public void setTypes(String types) {
        this.types = types;
    }
    public Integer getHits() {
        return hits;
    }
    public void setHits(Integer hits) {
        this.hits = hits;
    }
    public Integer getCah() {
        return cah;
    }
    public void setCah(Integer cah) {
        this.cah = cah;
    }
    public String getAdmin() {
        return admin;
    }
    public void setAdmin(String admin) {
        this.admin = admin;
    }
  
   
   
}
