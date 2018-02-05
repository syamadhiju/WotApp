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
@Table(name="Friend")
public class Friend implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue 
    @Column(name="FriendReqId")
	private int friendreqid;
    
    private int U_ID;

    private int friendid;
    
    @Column(name="Status")
	private  String status;

	public int getFriendreqid() {
		return friendreqid;
	}

	public void setFriendreqid(int friendreqid) {
		this.friendreqid = friendreqid;
	}

	public int getU_ID() {
		return U_ID;
	}

	public void setU_ID(int u_ID) {
		U_ID = u_ID;
	}

	

	public int getFriendid() {
		return friendid;
	}

	public void setFriendid(int friendid) {
		this.friendid = friendid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
    

}
