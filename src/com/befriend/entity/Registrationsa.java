package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="registration")
/**
 * 统计用户注册量
 */
public class Registrationsa  implements Serializable{
		
		private static final long serialVersionUID = 1L;
		@Id
		@Column(name="id")
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer id;
		
		@Column(name="address")
		/**
		 * 用户所在省份
		 */
		private String address;

		@Column(name="time")
		/**
		 * 数据统计时间
		 */
		private String time;
		@Column(name="addcity")
		/**
		 * 用户所在市
		 */
		private String addcity;
		@Column(name="note7")
		/**
		 *该地区7天内注册量
		 */
		private int note7;
		@Column(name="note30")
		/**
		 *该地区30天内注册量
		 */
		private int note30;
		@Column(name="note365")
		/**
		 *该地区365天内注册量
		 */
		private int note365;
		@Column(name="note")
		/**
		 *该地区当天天内注册量
		 */
		
		private int note;
		@Column(name="noteall")
		/**
		 *该地区全部注册量
		 */
		
		private int noteall;
		
		public int getNoteall() {
			return noteall;
		}
		public void setNoteall(int noteall) {
			this.noteall = noteall;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getAddcity() {
			return addcity;
		}
		public void setAddcity(String addcity) {
			this.addcity = addcity;
		}
		public int getNote7() {
			return note7;
		}
		public void setNote7(int note7) {
			this.note7 = note7;
		}
		public int getNote30() {
			return note30;
		}
		public void setNote30(int note30) {
			this.note30 = note30;
		}
		public int getNote365() {
			return note365;
		}
		public void setNote365(int note365) {
			this.note365 = note365;
		}
		public int getNote() {
			return note;
		}
		public void setNote(int note) {
			this.note = note;
		}
		
		
		

}
