package com.befriend.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
@SuppressWarnings("all")
@Entity
@Table(name="label")
public class NewsLabel  implements Serializable {
	
	
		
		@Id
		@Column(name="id")
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Expose
		private Integer id;
		
		@Column(name="label")
		@Expose
		private String label;
		@Column(name="time")
		@Expose
		private String time;

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}
		

}
