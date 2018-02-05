package com.niit.BackEnd;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.DAO.BlogDAO;
import com.niit.DAO.UserDAO;
import com.niit.Model.Blog;

import com.niit.Model.BlogComments;
import com.niit.Model.User;

public class BlogDAOTest
{
	
	@Autowired
	private static BlogDAO blogDAO;
		@Autowired
	private static UserDAO userDAO;	
		
		
		
		@BeforeClass
		public static void initialize()
		{
			@SuppressWarnings("resource")
			AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
			context.scan("com.niit");
			context.refresh();
			
			blogDAO=(BlogDAO)context.getBean("blogDAO");
			userDAO=(UserDAO)context.getBean("userDAO");
		
		}

		
		
		
		
		
	@Ignore
		@Test
		public void addBlogTest()
		{
			Blog blog=new Blog();
			
			blog.setBlogcontent("vbcvshvcb");
			blog.setBlogname("hibernate spring");
			blog.setDislikes(4);
			blog.setLikes(10);
			blog.setStatus("A");
			blog.setUsername("Amaal");
			blog.setViews(14);
			User user=(User)userDAO.getUser(1);
			
			System.out.println(user.getEmail_id());
			assertTrue("Problem in inserting   Blog",blogDAO.addBlog(blog));
			
			
			
		
		}
		
		
		
		
		@Ignore
		@Test
		public void getblogTest()
		{
			
			Blog blog=blogDAO.getBlog(3);
			System.out.println(blog.getBlogcontent());
			System.out.println(blog.getBlogname());
		}
		
		
		
		
	@Ignore
		@Test
		public void updateBlogTest()
		{
			
			Blog blog=(Blog)blogDAO.getBlog(3);
			blog.setBlogcontent("it is hibernate based");
			blog.setBlogname("hibernate core");
			assertTrue("Problem in Updating  Blog",blogDAO.updateBlog(blog));
			
			
		}
		
		
		
		
		@Ignore
		@Test
		public void deleteBlogTest()
		{
			Blog blog=(Blog)blogDAO.getBlog(33);
			assertTrue("Problem in Updating  Blog",blogDAO.deleteBlog(blog));
			
			
			
		}
		
		
		
		
		@Ignore
		@Test
		public void getAllBlogTest()
		{
			
		
			ArrayList<Blog> blog=(ArrayList<Blog>)blogDAO.getAllBlogs();
		for(Blog b:blog)
		{
			System.out.println(b.getBlogname());
		}
		
		}
		
		
		
		
	   @Ignore
		@Test
		public void approveBlogTest()
		{
			Blog blog=(Blog)blogDAO.getBlog(3);
			blog.setStatus("A");
			assertTrue("Problem in Approving  Blog",blogDAO.approveBlog(blog));
			
		}
		
		
		
		
		@Ignore
		@Test
		public void rejectBlogTest()
		{
			Blog blog=(Blog)blogDAO.getBlog(3);
			blog.setStatus("R");
			assertTrue("Problem in Rejecting  Blog",blogDAO.rejectBlog(blog));
			
		}
		
		
		
		
		@Ignore
		@Test
		public void likeBlogTest()
		{
		
			Blog blog =blogDAO.getBlog(3);
			blog.setLikes(blog.getLikes()+1);
					assertTrue("problemin liking blog",blogDAO.updateBlog(blog));
			
		}
		
		
		
		
		@Ignore
		@Test
		public void dislikeBlogTest()
		{
		
			Blog blog =blogDAO.getBlog(3);
			blog.setDislikes(blog.getDislikes()+1);
			assertTrue("problemin disliking blog",blogDAO.updateBlog(blog));
			
			
		}
		
		
		
		
		
	@Ignore
		@Test
		public void addBlogComment()
		{
			BlogComments blogcomments = new BlogComments();
			
			blogcomments.setBlogid(4);
			blogcomments.setBlogcomm("v good");
			blogcomments.setBlogcomid(1000);
		
			assertTrue("Problem in Inserting BlogComment",blogDAO.addBlogComment(blogcomments));
		
		}
		
		
		
		
	@Ignore
		@Test
		public void getBlogCommentTest()
		{
			BlogComments blogcomments=(BlogComments)blogDAO.getBlogComment(1);
			System.out.println(blogcomments.getBlogcomm());
			
		}
		
		
		

	@Ignore
		@Test
		public void updateBlogCommentTest()
		{
			BlogComments blogcomments =blogDAO.getBlogComment(1);
			blogcomments.setBlogcomm("amazing");
			assertTrue("Problem in Updating  Blog",blogDAO.updateBlogComment(blogcomments));		
			
		}
		
		@Ignore
		@Test
		public void deleteBlogCommentTest()
		{
			BlogComments blogcomments=(BlogComments)blogDAO.getBlogComment(1);
			assertTrue("Problem in deleting  Blog",blogDAO.deleteBlogComment(blogcomments));
			
			

		}
		


}
