package com.niit.DAOImpl;

import java.util.ArrayList;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.DAO.BlogDAO;
import com.niit.Model.Blog;
import com.niit.Model.BlogComments;

@Repository("blogDAO")
@EnableTransactionManagement
public class BlogDAOImpl implements BlogDAO
{
	
	@Autowired
	SessionFactory sessionFactory;
	

	
	@Autowired
	public BlogDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}



@Transactional
	public boolean like(int blogid) {
		try
		{
			Session session=sessionFactory.openSession();
			Blog blog = (Blog) session.get(Blog.class, blogid);
			
			session.update(blog);
			
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}
@Transactional
	public boolean dislike(int blogid) {
		try
		{
			Session session=sessionFactory.openSession();
			Blog blog = (Blog) session.get(Blog.class, blogid);
			blog.setLikes(blog.getDislikes()+1);
			session.update(blog);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}
@Transactional
	public boolean incview(int blogid) {
		try
		{
			Session session=sessionFactory.openSession();
			Blog blog = (Blog) session.get(Blog.class, blogid);
			
			session.update(blog);
			
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}	
	}


@Transactional
	
	public boolean addBlog(Blog blog) {
		try
		{
		sessionFactory.getCurrentSession().save(blog);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}
@Transactional
	
	public boolean updateBlog(Blog blog) {
		try
		{
		sessionFactory.getCurrentSession().update(blog);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}
@Transactional
	
	public boolean deleteBlog(Blog blog) {
		try
		{
		sessionFactory.getCurrentSession().delete(blog);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}
@Transactional
	
	public Blog getBlog(int blogId) {
		Session session=sessionFactory.openSession();
		Blog blog = (Blog) session.get(Blog.class, blogId);
		session.close();
		return blog;
	}
@Transactional

	public ArrayList<Blog> getAllBlogs() 
	{

		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<Blog> blogList=(ArrayList<Blog>)session.createQuery("from Blog where status='A'").list();
		session.close();
		return blogList;
	}

@Transactional
	
	public boolean approveBlog(Blog blog)
	{
		try
		{
			blog.setStatus("A");
			sessionFactory.getCurrentSession().saveOrUpdate(blog);
			return true;
			
		}
		catch(Exception e)
		{
		
		return false;
		}
	}

@Transactional
	
	public boolean rejectBlog(Blog blog)
	{
		try
		{
			blog.setStatus("R");
			sessionFactory.getCurrentSession().saveOrUpdate(blog);
			return true;
			
		}
		catch(Exception e)
		{
		
		return false;
		}
	}

@Transactional

	public boolean addBlogComment(BlogComments blogcomment)
	{
		try
		{
		sessionFactory.getCurrentSession().save(blogcomment);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}

@Transactional
	
	public boolean deleteBlogComment(BlogComments blogcomment) 
	{
		try
		{
		sessionFactory.getCurrentSession().delete(blogcomment);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}

@Transactional
	
	public boolean updateBlogComment(BlogComments blogcomment) 
	{
		try
		{
		sessionFactory.getCurrentSession().update(blogcomment);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}

	}

@Transactional

	public BlogComments getBlogComment(int commentId)
	{
		Session session=sessionFactory.openSession();
		BlogComments blogcomment = (BlogComments) session.get(BlogComments.class, commentId);
		session.close();
		return blogcomment;
	}

@Transactional

	public ArrayList<BlogComments> getAllBlogComments(int blogid)
	{
        Session ssn=sessionFactory.openSession();
		
		org.hibernate.Query q= ssn.createQuery("from BlogComments where blogid="+blogid);
		@SuppressWarnings("unchecked")
		ArrayList<BlogComments> l=(ArrayList<BlogComments>) q.list();
		
	    ssn.close();

		return l;
	}

@Transactional

	public ArrayList<Blog> getAllBlogRequests()
	{

		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<Blog> blogreq=(ArrayList<Blog>)session.createQuery("from Blog where status='P'").list();
		session.close();
		return blogreq;	
	}

@Transactional
	
	public ArrayList<Blog> getAllMyBlogs(String email_id)
	{
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<Blog> myblogs=(ArrayList<Blog>)session.createQuery("from Blog where username='"+email_id+"'").list();
		session.close();
		return myblogs;
	}

	
	

}
