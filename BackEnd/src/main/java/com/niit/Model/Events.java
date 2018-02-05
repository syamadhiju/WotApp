package com.niit.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="Events")
public class Events implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="EventId",nullable=false)
	public int eventid;
	@Column(name="EventDate",nullable=false)
	public Date eventdate;
	@Column(name="EventName",nullable=false)
	public String eventname;
	@Column(name="EventDesc",nullable =false)
	public String eventesc;
	@Column(name="EventVenue",nullable =false)
	public String eventvenue;
	public int getEventid() {
		return eventid;
	}
	public void setEventid(int eventid) {
		this.eventid = eventid;
	}
	public Date getEventdate() {
		return eventdate;
	}
	public void setEventdate(Date eventdate) {
		this.eventdate = eventdate;
	}
	public String getEventname() {
		return eventname;
	}
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}
	public String getEventesc() {
		return eventesc;
	}
	public void setEventesc(String eventesc) {
		this.eventesc = eventesc;
	}
	public String getEventvenue() {
		return eventvenue;
	}
	public void setEventvenue(String eventvenue) {
		this.eventvenue = eventvenue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
