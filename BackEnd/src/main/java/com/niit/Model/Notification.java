package com.niit.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="Notification")
public class Notification implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue 
    @Column(name="Notid")
	private int notid;
	
	
	@Column(name="name")
	private String name;
	
	
	@Column(name="Username")
	private String username;


	public int getNotid() {
		return notid;
	}


	public void setNotid(int notid) {
		this.notid = notid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	}
