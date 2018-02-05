package com.niit.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.DAO.UserDAO;
import com.niit.Model.Friend;
import com.niit.Model.User;
@Repository("userDAO")
@EnableTransactionManagement
public class UserDAOImpl implements UserDAO
{

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	public UserDAOImpl(SessionFactory sessionfactory)
	{
		this.sessionFactory=sessionfactory;
	}
	 @SuppressWarnings("unchecked")
	@Transactional
		public ArrayList<User> getAllUser() {
			
			String hql = "from User";
			Query query =sessionFactory.getCurrentSession().createQuery(hql);		
			return (ArrayList<User>) query.list();
			
		}
	  @Transactional
	  public boolean saveUser(User user) {
	  	
	  	try {
	  		sessionFactory.getCurrentSession().save(user);
	  		return true;
	  	} catch (HibernateException e) {
	  		// TODO Auto-generated catch block
	  		e.printStackTrace();
	  		return false;
	  	}
	  }
	  
	  @Transactional	  
	public boolean updateOnlineStatus(User user) {
		try{
			
			sessionFactory.getCurrentSession().saveOrUpdate(user);
			return true;
		}
		catch(Exception e)
		{
			
			return false;
		}
		
	}
	  
	@Transactional 
	public User getUser(int User_ID) {
		
		System.err.println(User_ID);
		User user=new User();
		try{
			Session session= sessionFactory.openSession();
			Query query=session.createQuery("from User where userid="+User_ID);
			 user=(User)query.list().get(0);
			session.close();
			
		}
		catch(Exception e)
		{
			
			
		}
		return user;
		
	}
	
	
	@Transactional 
	public User getUserbyId(int User_ID) {
		
		
		User user=new User();
		try{
			Session session= sessionFactory.openSession();
			Query query=session.createQuery("from User where userid="+User_ID);
			 user=(User)query.list().get(0);
			session.close();
			
		}
		catch(Exception e)
		{
			
			
		}
		return user;
		
	}

	@Transactional
	public boolean checkLogin(User user) {
		try{
			Session session=sessionFactory.openSession();
			System.out.println(user.getEmail_id());
			System.out.println(user.getPassword());
			Query query=session.createQuery("from User where email_id='"+user.getEmail_id()+"' and password='"+user.getPassword()+"'");
			
			User user1=(User)query.list().get(0);
			
			if(user1==null)
			{
				
				return false;
			}
			else
			{
				System.out.println("2.."+user1.getEmail_id());
				System.out.println("2..."+user1.getPassword());
				return true;
			}
		}catch(Exception e)
		{
			return false;
		}
	}

	@Transactional

	public ArrayList<User> userrequests() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<User> userreq=(ArrayList<User>) session.createQuery("from User where status='P'").list();
		session.close();
		return userreq;
	}
	@Transactional
	
	public boolean approveusers(User user) {
		try {
	  		sessionFactory.getCurrentSession().saveOrUpdate(user);
	  		return true;
	  	} 
		catch (HibernateException e) 
		{
	  		e.printStackTrace();
	  		return false;
	  	}
		
	}
	@Transactional

	public boolean rejectusers(User user) {
		try {
	  		sessionFactory.getCurrentSession().saveOrUpdate(user);
	  		return true;
	  	} 
		catch (HibernateException e)
		{
	  		e.printStackTrace();
	  		return false;
	  	}
	}
	@Transactional
	public User getUserbyemail_id(String email_id) {
		User user=new User();
		try{
			Session session= sessionFactory.openSession();
			Query query=session.createQuery("from User where email_id='"+email_id+"'");
			 user=(User)query.list().get(0);
			session.close();
			
		}
		catch(Exception e)
		{
			
			
		}
		return user;
	}
	@Transactional
	public ArrayList<Friend> checkismyfriend(int userid, int myid) {
		Session session = sessionFactory.openSession();
		ArrayList<Friend> myfriends=(ArrayList<Friend>) session.createQuery("from Friend where (U_ID="+myid+" and friendid="+userid+") or (U_ID="+userid+" or friendid="+myid+") and (status='YES')").list();
		session.close();
		return myfriends;
	}
	
	@Transactional
	public List<User> listUsers(String user) {
Query query = sessionFactory.getCurrentSession().createQuery("FROM User where userid not in(select friendid From Friend where userid='"+user+"') ");
		
		return (List<User>)query.list();
	}
	
	
	@Transactional
	public boolean checkLoginsimp(User user) {
		Session session=sessionFactory.openSession();
		
		Query query=session.createQuery("from Users where email_id='"+user.getEmail_id()+"' and password='"+user.getPassword()+"'");
		
		ArrayList<User> user1=(ArrayList<User>)query.list();
		if(user1.isEmpty())
		{
			return false;
		}
		else
		{
			return true;
		}
	
	}
	
	@Transactional
	public List<User> requestFriend(int user) {
     Query query = sessionFactory.getCurrentSession().createQuery("from User where userid not in(select friendid From Friend where U_ID='"+user+"') ");
		System.err.println("UserLIst : "+(List<User>)query.list());
		return (List<User>)query.list();
	}
	
	
	}



