package com.niit.DAOImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.DAO.ForumCommentDAO;

@Repository("forumCommentDAO")
public class ForumCommentDAOImpl implements ForumCommentDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	public ForumCommentDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

}
