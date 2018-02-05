package com.niit.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.NotificationDAO;
import com.niit.Model.Notification;

import com.niit.Model.User;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	@Autowired 
	NotificationDAO notificationDAO;
	
	
	
	@RequestMapping(value="/getAllNotis",method=RequestMethod.GET,headers = "Accept=application/json")
	public ResponseEntity<ArrayList<Notification>> getAllNotis(HttpSession session){
		User user=(User)session.getAttribute("currentuser");
		System.out.println("in getall notis");
		System.out.println(user.getEmail_id());
		ArrayList<Notification> notis=(ArrayList<Notification>)notificationDAO.getAllNotifications(user.getEmail_id());
		for(Notification n:notis)
		{
			System.err.println(n.getName());
		}
				return new ResponseEntity<ArrayList<Notification>>(notis,HttpStatus.OK);
				
	
	
	
	}
	
	
	@RequestMapping(value="/deleteNoti/{notifid}",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Notification>> deleteNoti(@PathVariable("notifid") int notifid,HttpSession session){
	
	User us=(User)session.getAttribute("currentuser");
		
		Notification noti=notificationDAO.getNotifications(notifid);
	if(notificationDAO.deleteNotifications(noti))
	{
		ArrayList<Notification> bc=notificationDAO.getAllNotifications(us.getEmail_id());
	
		return new ResponseEntity<ArrayList<Notification>>(bc,HttpStatus.OK);	
		
			
		
		
	}
	else
	{
		return null;
	}
	
	
	
	
	}

}
