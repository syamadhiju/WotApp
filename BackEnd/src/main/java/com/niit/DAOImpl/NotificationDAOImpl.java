package com.niit.DAOImpl;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.DAO.NotificationDAO;
import com.niit.Model.Notification;

@Repository("NotificationsDAO")
public class NotificationDAOImpl implements NotificationDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	public NotificationDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	@Transactional
	public boolean addNotifications(Notification notification) {
		try
		{
		sessionFactory.getCurrentSession().save(notification);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}

	@Transactional
	public ArrayList<Notification> getAllNotifications(String username) {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<Notification> notis=(ArrayList<Notification>)session.createQuery("from Notifications where username='"+username+"'").list();
		session.close();
		return notis;
	}

	@Transactional
	public boolean deleteNotifications(Notification notification) {
		try
		{
		sessionFactory.getCurrentSession().delete(notification);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}

	@Transactional
	public Notification getNotifications(int notifid) {
		Session session=sessionFactory.openSession();
		Notification noti = (Notification) session.get(Notification.class,notifid);
		session.close();
		return noti;
		
	}
	
}
