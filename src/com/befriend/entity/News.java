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
    @Column(name = "imgmax")
    // ���Ŵ�ͼƬ
    private String imgmax;

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
    // ������� 8����
    private String type;
    @Column(name = "types")
    // ������� 8�����4����
    private String types;

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
