package com.niit.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "USER")
@Component
public class User implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userid")
	private int userid;
	
	
	@Column(name = "UserName", nullable = false)
	private String username;
	
	
	@Column(name = "FirstName", nullable = false)
	private String first_name;
	
	
	@Column(name = "LastName", nullable = false)
	private String last_name;
	
	@Column(name = "Gender", nullable = false)
	private String gender;
	
	
	@Column(name= "Email_ID",nullable=false)
	private String email_id;
	
	
	@Column(name = "Password", nullable = false)
	private String password;
	
	@Column(name = "phoneno", nullable = false)
	private String phoneno;
	
	@Column(name = "Role", nullable = false)
	 private String role;
	
	@Column(name = "Isonline", nullable = false)
	 private String isonline;
	
	@Column(name = "status", nullable = false)
	 private String status;
	
	@Column(name = "Place")
	 private String place;
	
	@Column(name = "image")
    private String image;

	@Column(name = "cover")
    private String cover;

	

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getIsonline() {
		return isonline;
	}

	public void setIsonline(String isonline) {
		this.isonline = isonline;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
}
