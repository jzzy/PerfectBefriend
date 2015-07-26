package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//���ű�
@Entity
@Table(name = "news")
public class News implements Serializable {

    /**
     * ����
     */
    private static final long serialVersionUID = 1L;
    
    //����
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
    // ����
    private String title;
    @Column(name = "summary")
    // ժҪ����
    private String summary;
    @Column(name = "content")
    // ����  
    private String content;

    @Column(name = "img")
    // ����СͼƬ
    private String img;
    @Column(name = "time")
    // ���ŷ���ʱ��
    private String time;
    @Column(name = "collectnum")
    // ���ű��ղش���
    private Integer collectnum;
    @Column(name = "reviews")
    // ���ű����۴���
    private Integer reviews;
    @Column(name = "area")
    // ���ŵ��� ʡ��
    private String area;
    @Column(name = "areas")
    // ���ŵ��� �м�
    private String areas;
    @Column(name = "type")
    // ������� 1���� 2���� 3��ѧ 4ѧУ 5����
    private int type;
    @Column(name = "types")
    // ������� 8�����4����
    private int types;

    @Column(name = "hits")
    // ���ŵ����
    private Integer hits;
    @Column(name = "cah")
    // ���ŵ����+�ղ���
    private Integer cah;
    // ����Ա�˺�
    @Column(name = "admin")
    private String admin;
    // �Ƿ�Ϊר�ҷ��� 0 ���� 1ר��
    @Column(name = "expert")
    private int expert;
    //���ű�ǩ
    @Column(name="label")
    private String label;
    
    //���ƶ�
    private double similarity;
    
 
    
   
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
