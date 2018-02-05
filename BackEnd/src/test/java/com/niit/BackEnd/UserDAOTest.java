package com.niit.BackEnd;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.DAO.UserDAO;
import com.niit.Model.User;

public class UserDAOTest {

	@Autowired
	public static UserDAO userDAO;
		
		
		@BeforeClass
		public static void initialize()
		{
			AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
			context.scan("com.niit");
			context.refresh();
			
			userDAO=(UserDAO)context.getBean("userDAO");
		}
		
	    @Ignore
		@Test
		public void getAllUsersTest()
		{
			ArrayList<User> user= userDAO.getAllUser();
			for(User u:user)
			{
				System.out.println(u.getFirst_name());
				System.out.println(u.getLast_name());
				System.out.println(u.getPassword());
			
			
		}
		}
            @Ignore
			@Test
			public void addUserTest()
			{
				User user =new User();
				user.setFirst_name("syama ");
				user.setLast_name("dheeraj");
				user.setUsername("diju");
				user.setEmail_id("syamadiju@gmail.com");
				user.setIsonline("N");
				user.setGender("F");
				user.setPassword("maludiju");
				user.setRole("ROLE_USER");
				user.setPhoneno("34234234");
				user.setStatus("Accept");
				user.setPlace("Thrissur");
				//user.setUser_ID(1237);
				
				
				
			
				assertTrue("Problem in Inserting USer",userDAO.saveUser(user));
				
				
				
				
			}
			
			@Ignore
			@Test
			public void getUserTest()
			{
				
				User user=(User)userDAO.getUser(1);
				System.out.println(user.getEmail_id());
				
			}
			
			@Ignore
			@Test
			public void updateOnlineStatusTest()

			{
				User user=userDAO.getUser(2);
				
				assertTrue("Problem in updating Online Status",userDAO.updateOnlineStatus(user));
				
				
			}
			@Ignore
			@Test
			public void checklogin()
			{
				User user=(User)userDAO.getUser(6);
				System.out.println(user.getEmail_id());
				
				assertTrue("Problem in login Status",userDAO.checkLogin(user));
				
			}
			@Ignore
			@Test
			public void getuserbyEmail_id()
			{
				User user=(User)userDAO.getUserbyId(1);
				System.out.println(user.getEmail_id());
				
				
				
			}



}
