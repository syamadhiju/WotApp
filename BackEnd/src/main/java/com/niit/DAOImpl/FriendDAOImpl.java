package com.niit.DAOImpl;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.DAO.FriendDAO;
import com.niit.Model.Friend;
import com.niit.Model.User;

@Repository("friendDAO")
public class FriendDAOImpl implements FriendDAO {
	
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	@Autowired
	public FriendDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	@Transactional
	
	public boolean addFriend(Friend friend) {
		try
		{
		sessionFactory.getCurrentSession().save(friend);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}

	@Transactional
	
	public boolean delete(Friend friend) {
		try
		{
			
		sessionFactory.getCurrentSession().delete(friend);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}

	@Transactional
	
	public boolean accept(int friendreqid) {
		try
		{
		Session session=sessionFactory.openSession();
		Friend friend=(Friend) session.get(Friend.class,friendreqid);
		friend.setStatus("A");
		session.saveOrUpdate(friend);
		session.close();
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}

	@Transactional
	
	public boolean reject(int friendreqid) {
		try
		{
		Session session=sessionFactory.openSession();
		Friend friend=(Friend) session.get(Friend.class,friendreqid);
		friend.setStatus("R");
		session.saveOrUpdate(friend);
		session.close();
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}

	@Transactional
	
	public List<Friend> getfriendrequest(int friendreqid, int myid)
	{
		Session session = sessionFactory.openSession();
		Query q= session.createQuery("from Friend where (U_ID="+myid+" and friendid="+friendreqid+") or (U_ID="+friendreqid+" and friendid="+myid+")" );
		List<Friend> mynfriend=(List<Friend>)q.list();
	return mynfriend;
	}

	@Transactional
	
	public ArrayList<Friend> getAllFriendRequestsByUser(int userid)
	{
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<Friend> myfriends=(ArrayList<Friend>)session.createQuery("from Friend where friendid="+userid+" and status='P'").list();
		session.close();
		return myfriends;
	}

	@Transactional
	
	public ArrayList<Friend> getAllFriend() 
	{
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<Friend> Allfriends=(ArrayList<Friend>)session.createQuery("from Friend").list();
		session.close();
		return Allfriends;	}

	@Transactional
	
	public ArrayList<Friend> getAllMyFriend(int myid)
	{
	Session session = sessionFactory.openSession();
	Query q= session.createQuery("from Friend where status='A' and (U_ID="+myid+" or friendid="+myid+")" );
	@SuppressWarnings("unchecked")
	ArrayList<Friend> myfriends=(ArrayList<Friend>)q.list();
	return myfriends;
	}

	@Transactional
	
	public ArrayList<Friend> getAllpendingentries(int myid)
	{
		Session session = sessionFactory.openSession();
		Query q= session.createQuery("from Friend where  status='P' and( U_ID="+myid+" or friendid="+myid+") ");
	@SuppressWarnings("unchecked")
	ArrayList<Friend> myfriends=(ArrayList<Friend>)q.list();
	return myfriends;
	}

	@Transactional
	
	public ArrayList<Friend> getAllPendingrequests(int userid)
	{
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<Friend> myfriends=(ArrayList<Friend>)session.createQuery("from Friend where U_ID="+userid+" and status='P'").list();
		session.close();
		return myfriends;
	}

	@Transactional

	public User getUserById(int userid) {
		User user=new User();
		try{
			Session session= sessionFactory.openSession();
			Query query=session.createQuery("from User where userid="+userid);
			 user=(User)query.list().get(0);
			session.close();
			
		}
		catch(Exception e)
		{
			
			
		}
		return user;
	}

	@Transactional
	
	public Friend acceptfriendrequest(Friend friend) {
		try
		{
		sessionFactory.getCurrentSession().update(friend);
		return null;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return null;
		}
	}

	@Transactional
	public ArrayList<Friend> getAllMyFriendpend(int myid) {
		Session session = sessionFactory.openSession();
		Query q= session.createQuery("from Friend where (U_ID="+myid+" or friendid="+myid+")");
	ArrayList<Friend> myfriends=(ArrayList<Friend>)q.list();
	return myfriends;
	}

	@Transactional
	public Friend rejectfriendrequest(Friend friend) {
		try
		{
		sessionFactory.getCurrentSession().update(friend);
		return null;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return null;
		}
	}
	
	

}
