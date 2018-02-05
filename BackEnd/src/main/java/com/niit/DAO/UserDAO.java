package com.niit.DAO;

import java.util.ArrayList;
import java.util.List;

import com.niit.Model.Friend;
import com.niit.Model.User;

public interface UserDAO
{
	 ArrayList<User> getAllUser();
	    public boolean saveUser(User user);
	    public boolean updateOnlineStatus(User user);
		public User getUser(int userid);
		public boolean checkLogin(User user);
		public User getUserbyId(int userid);
		public User getUserbyemail_id(String email);
		public ArrayList<User> userrequests();
		public boolean approveusers(User user);
		public boolean rejectusers(User user);
		public ArrayList<Friend> checkismyfriend(int userid,int myid);
		public List<User> listUsers(String user);
		public List<User> requestFriend(int user);
		public boolean checkLoginsimp(User user);
	

}