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
public class Visitor  implements Serializable {

	
		/**
		 * �ο�
		 */
		private static final long serialVersionUID = 1L;
		@Id
		@Column(name="id")
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer id;
		@Column(name="nickname")//�ο��ǳ�
		private String nickname;
		@Column(name="appmac")//�ο�Ψһappmac
		private String appmac;
		@Column(name="time")//�ο͵�һ�ε���ʱ��
		private String time;
		@Column(name="os")// ���Ե�ϵͳ  android ios
		private String os;
		@Column(name="province")// ���Ե�ϵͳ ����
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
