package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="visitor")
public class Visitor {

	
		/**
		 * 游客
		 */
		private static final long serialVersionUID = 1L;
		@Id
		@Column(name="id")
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer id;
		@Column(name="nickname")//游客昵称
		private String nickname;
		@Column(name="appmac")//游客唯一appmac
		private String appmac;
		@Column(name="time")//游客第一次登入时间
		private String time;
		@Column(name="os")// 来自的系统  android ios
		private String os;
		@Column(name="province")// 来自的系统 地区
		private String province;
		
		public String getProvince() {
			return province;
		}
		public void setProvince(String province) {
			this.province = province;
		}
		public String getOs() {
			return os;
		}
		public void setOs(String os) {
			this.os = os;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		
		public String getAppmac() {
			return appmac;
		}
		public void setAppmac(String appmac) {
			this.appmac = appmac;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		
}
