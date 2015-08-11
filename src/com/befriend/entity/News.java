package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "news")
public class News implements Serializable {

  
    private static final long serialVersionUID = 1L;
    public static final int EDUCATION = 1;
    public static final int LOCAL = 2;
    public static final int THE_ENTRANCE = 3;
    public static final int SCHOOL = 4;
    public static final int HEALTHY = 5;
    public static final int TOP_NEWS = 6;
    public static final int RECOMMEND = 7;
    
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
   
    private String title;
    @Column(name = "summary")
    
    private String summary;
    @Column(name = "content")
    
    private String content;

    @Column(name = "img")
    
    private String img;
    @Column(name = "time")
    
    private String time;
    @Column(name = "collectnum")
    // 被收藏次数
    private Integer collectnum;
    @Column(name = "reviews")
    // 评论数
    private Integer reviews;
    @Column(name = "area")
    // 地区省
    private String area;
    @Column(name = "areas")
    // 地区市
    private String areas;
    @Column(name = "type")
    // 大类
    private int type;
    @Column(name = "types")
    // 小类
    private int types;

    @Column(name = "hits")
    // 新闻点击数
    private Integer hits;
    @Column(name = "cah")
    // 点击数+收藏数
    private Integer cah;
    // 上传的管理员
    @Column(name = "admin")
    private String admin;
    // 是否专家
    @Column(name = "expert")
    private int expert;
    //标签
    @Column(name="label")
    private String label;
   
    @Column(name="supports")
    //点赞数
    private int supports;
    //相似度
    @Transient
    private double similarity=0d;
    
 
    
   
    public int getSupports() {
		return supports;
	}
	public void setSupports(int supports) {
		this.supports = supports;
	}
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
  
   
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getTypes() {
		return types;
	}
	public void setTypes(int types) {
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
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public double getSimilarity() {
		return similarity;
	}
	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}
    
   
   
}
