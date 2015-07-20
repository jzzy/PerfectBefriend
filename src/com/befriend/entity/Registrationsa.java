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
 * ͳ���û�ע����
 */
public class Registrationsa  implements Serializable{
		
		private static final long serialVersionUID = 1L;
		@Id
		@Column(name="id")
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer id;
		
		@Column(name="address")
		/**
		 * �û�����ʡ��
		 */
		private String address;

		@Column(name="time")
		/**
		 * ����ͳ��ʱ��
		 */
		private String time;
		@Column(name="addcity")
		/**
		 * �û�������
		 */
		private String addcity;
		@Column(name="note7")
		/**
		 *�õ���7����ע����
		 */
		private int note7;
		@Column(name="note30")
		/**
		 *�õ���30����ע����
		 */
		private int note30;
		@Column(name="note365")
		/**
		 *�õ���365����ע����
		 */
		private int note365;
		@Column(name="note")
		/**
		 *�õ�����������ע����
		 */
		
		private int note;
		@Column(name="noteall")
		/**
		 *�õ���ȫ��ע����
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
