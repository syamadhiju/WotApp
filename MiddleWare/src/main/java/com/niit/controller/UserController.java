package com.niit.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.niit.DAO.FriendDAO;
import com.niit.DAO.JobDAO;
import com.niit.DAO.UserDAO;
import com.niit.Model.Friend;
import com.niit.Model.Job;
import com.niit.Model.User;

@RestController
@RequestMapping("/user")
public class UserController 
{
@Autowired
UserDAO userDAO;
@Autowired
JobDAO jobDAO;	

@Autowired
FriendDAO friendDAO;	
	

	 @RequestMapping(value="/getAllUsers/{userid}",method=RequestMethod.GET)
		public ArrayList<User> getAllUser(@PathVariable("userid") int userid){
		 System.out.println("in rest controller getallusers");
			ArrayList<User> user=(ArrayList<User>)userDAO.requestFriend(userid);		
					System.out.println("in rest controller getallusers");

		return user;		
		}
	 
	 

	 
	 
	 @RequestMapping(value="/getUser/{userid}",method=RequestMethod.GET)
		public ResponseEntity<User> getUser(@PathVariable("userid") int userId){
			
		 if(userDAO.getUser(userId)==null){
				
			}
			return new ResponseEntity<User>(userDAO.getUser(userId),HttpStatus.OK);
					
		}
	 
	 @RequestMapping(value="/register",method=RequestMethod.POST)
		public ResponseEntity<User> createUser(@RequestBody User user){
			System.out.println("In register controller");
			boolean isSaved=userDAO.saveUser(user);
			if(isSaved) {
			return new ResponseEntity<User>(user,HttpStatus.OK);
			}
			else
				return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
			
		}
	
	 @RequestMapping(value="/login",method=RequestMethod.POST)
		public ResponseEntity<User> login(@RequestBody User user,HttpSession http){
		


		 
			if(userDAO.checkLogin(user))
			{
				 User tempuser=userDAO.getUserbyemail_id(user.getEmail_id());
				System.out.println("3..."+tempuser.getEmail_id());
				System.out.println("3..."+tempuser.getPassword());
			tempuser.setIsonline("YES");
				userDAO.updateOnlineStatus(tempuser);
				http.setAttribute("currentuser",tempuser);
			return new ResponseEntity<User>(tempuser,HttpStatus.OK);
				
				
			}
			else
			{
				return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
			}
			
		}
	
	 @RequestMapping(value="/job",method=RequestMethod.POST)
		public ResponseEntity<Job> getJob(){
		
			
				return new ResponseEntity<Job>(jobDAO.getjob(201),HttpStatus.BAD_REQUEST);
			
		}
	 
	 @RequestMapping(value="/logout/{email}",method=RequestMethod.GET)
		public ResponseEntity<String> logout(@PathVariable("email") String email){
		 System.out.println(email);
		 
	 String emaill=email+".com";

	 System.out.println(emaill);
User tempuser=userDAO.getUserbyemail_id(emaill);
		 tempuser.setIsonline("NO");
		userDAO.updateOnlineStatus(tempuser);
		return new ResponseEntity<String>("Lgout success",HttpStatus.OK);		 
}
	 

	 @RequestMapping(value="/getAllUsersreq",method=RequestMethod.GET)
		public ArrayList<User> getAllUserreq()
	 {
		 System.out.println("in rest controller getallusersreq");
	     ArrayList<User> userreq=(ArrayList<User>)userDAO.userrequests();
	     System.out.println("in rest controller getallusersreq");

		 return userreq;		
	 } 
	 
	 @RequestMapping(value="/approveusers/{username}",method=RequestMethod.GET)
	 public void approveusers(@PathVariable("username") String username)
	 {
		 String email_id=username+".com";
		 User user =userDAO.getUserbyemail_id(email_id);
		 user.setStatus("A");
		 userDAO.approveusers(user);
	 
	 }
	 
	 @RequestMapping(value="/rejectusers/{username}",method=RequestMethod.GET)
	 public void rejectusers(@PathVariable("username") String username)
	 {
		 String email_id=username+".com";
		 User user =userDAO.getUserbyemail_id(email_id);
		 user.setStatus("R");
		 userDAO.rejectusers(user);
		 
	 }

	 @RequestMapping(value="/up",method = RequestMethod.POST)
	 public ModelAndView  upload(HttpServletRequest request,@RequestParam("uploadedFile") MultipartFile file,HttpSession session )
	 {
	 	  /* String filepath = request.getSession().getServletContext().getRealPath("/") + "resources/product/" + file.getOriginalFilename();
	 		*/
		 
		 User user = (User)session.getAttribute("currentuser");
		 	System.out.println(user.getUsername());
		 		user.setImage(user.getUsername()+".jpg");
		 	userDAO.updateOnlineStatus(user); 
		 	
		 	
	 	    String filepath ="C:/project2nd/FrondEnd/WebContent/images/" + user.getUsername()+".jpg";
	 		
	 		
	 		System.out.println(filepath);
	 		try {
	 			byte imagebyte[] = file.getBytes();
	 			BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(filepath));
	 			fos.write(imagebyte);
	 			fos.close();
	 			} catch (IOException e) {
	 			e.printStackTrace();
	 			} catch (Exception e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 			}
	 		
	 	
	 	ModelAndView mv = new ModelAndView("/backhome");
		return mv;
	 }
	 
	 
	 @RequestMapping(value="/upcover",method = RequestMethod.POST)
	 public ModelAndView uploadcover(HttpServletRequest request,@RequestParam("uploadedFile") MultipartFile file,HttpSession session )
	 {
	 	  /* String filepath = request.getSession().getServletContext().getRealPath("/") + "resources/product/" + file.getOriginalFilename();
	 		*/
		 
		 User user = (User)session.getAttribute("currentuser");
		 	
		 		user.setCover(user.getUsername()+"cover.jpg");
		 	userDAO.updateOnlineStatus(user);
	 	    String filepath ="C:/project2nd/FrondEnd/WebContent/images/" +user.getUsername()+"cover.jpg";
	 		String img=file.getOriginalFilename();
	 		System.out.println(img);
	 		System.out.println(filepath);
	 		try {
	 			byte imagebyte[] = file.getBytes();
	 			BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(filepath));
	 			fos.write(imagebyte);
	 			fos.close();
	 			} catch (IOException e) {
	 			e.printStackTrace();
	 			} catch (Exception e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 			}
	 		
	 	
	 	ModelAndView mv = new ModelAndView("backhome");
		return mv;
	 }
	 
	 
	 @RequestMapping(value="/ismyfriend/{userid}/{myid}",method = RequestMethod.GET)
	 public ArrayList<User> ismyfriend(@PathVariable("userid") int userid,@PathVariable("myid") int myid)
	 {
		 System.out.println("in is my friend controller");
		 ArrayList<Friend> friends = (ArrayList<Friend>)userDAO.checkismyfriend(userid, myid);
		ArrayList<User> users= new  ArrayList<User>();
		for(Friend f:friends)
		{
			if(f.getU_ID()==myid)
			{
				users.add(userDAO.getUserbyId(f.getFriendid()));
			}
			else if(f.getFriendid()==myid)
			{
				users.add(userDAO.getUserbyId(f.getU_ID()));
			} 
			
		}
	 return users;
	
}
	 
	 
	 @RequestMapping(value="/friendsfriends/{userid}/{myid}",method = RequestMethod.GET)
	 public ArrayList<User> friendsfriends(@PathVariable("userid") int userid,@PathVariable("myid") int myid )
	 { 
		 System.out.println(userid+" "+myid);
	 ArrayList<User> fp=new ArrayList<User>(); 
	 ArrayList<Friend> myfriends=(ArrayList<Friend>)friendDAO.getAllMyFriendpend(myid);
	 ArrayList<String> myfriendsemail=new ArrayList<String>();
	 for(Friend s:myfriends)
	 {
	 	if(s.getU_ID()==myid)
	 	{
	 	 myfriendsemail.add(userDAO.getUser(s.getFriendid()).getEmail_id());
	 	}
	 	else if(s.getFriendid()==myid)
	 	{
	 		System.out.println(userDAO.getUser(s.getU_ID()).getEmail_id());
	 		
	 		myfriendsemail.add(userDAO.getUser(s.getU_ID()).getEmail_id());
	 	}
	 }

	 
	 ArrayList<Friend> hisfriends=(ArrayList<Friend>)friendDAO.getAllMyFriend(userid);
	 ArrayList<String> hisfriendsemail=new ArrayList<String>();
	 for(Friend s:hisfriends)
	 {
	 	if(s.getU_ID()==userid)
	 	{
	 		hisfriendsemail.add(userDAO.getUser(s.getFriendid()).getEmail_id());
	 	}
	 	else if(s.getFriendid()==userid)
	 	{
	 		hisfriendsemail.add(userDAO.getUser(s.getU_ID()).getEmail_id());
	 	}
	 }

	 for(String hs:hisfriendsemail)
	 {
		 User u=userDAO.getUserbyId(myid);
		if(hs==u.getEmail_id())
		{
			
		}
		else
		{
		 int count=0;
		 
		 for(String mf:myfriendsemail)
		 {
			 
			 if(mf!=hs)
			 {
				 count++;
			 }
			 
		 }
		 
		 if(count==myfriendsemail.size())
		 {
			 User us=userDAO.getUserbyemail_id(hs);
			 fp.add(us);
					 
		 }
		}
		 
		 
	 }
	
	 return fp;
	 
}
}
