package com.niit.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.BlogDAO;
import com.niit.DAO.UserDAO;
import com.niit.Model.Blog;
import com.niit.Model.BlogComments;
import com.niit.Model.User;

@RestController
@RequestMapping("/blogs")
public class BlogController {
	@Autowired 
	BlogDAO blogDAO;
	@Autowired 
	UserDAO userDAO;
	
	

	@RequestMapping(value="/getAllBlogs",method=RequestMethod.GET,headers = "Accept=application/json")
	public ArrayList<Blog> getAllBlogs(){
		
		System.out.println("in getall blogs");
		ArrayList<Blog> blogs=(ArrayList<Blog>)blogDAO.getAllBlogs();
		for(Blog blo:blogs)
		{
			System.out.println(blo.getBlogcontent());
			System.out.println(blo.getBlogname());
			
		
		}
				return blogs;
				
	
	
	
	}
	
	
	
	@RequestMapping(value="/getAllMyBlogs/{userid}",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Blog>> getAllMyBlogs(@PathVariable("userid") int userid){
		
		User user=userDAO.getUser(userid);
		ArrayList<Blog> myblogs=blogDAO.getAllMyBlogs(user.getEmail_id());
		return new ResponseEntity<ArrayList<Blog>>(myblogs,HttpStatus.OK);
	
	
	
	}


	
	
	@RequestMapping(value="/addBlog",method=RequestMethod.POST)
	public ResponseEntity<String> addBlog(@RequestBody Blog blog){
	
	
		
		
		boolean isSaved=blogDAO.addBlog(blog);
		if(isSaved)
		{
			
		return new ResponseEntity<String>("Adding blog successful",HttpStatus.OK);
		}
		else
		{	
			return new ResponseEntity<String>("Error in adding blog",HttpStatus.BAD_REQUEST);
		}

	}
	
	
	@RequestMapping(value="/getBlogById/{blogid}",method=RequestMethod.GET)
	public ResponseEntity<Blog> getBlog(@PathVariable("blogid") int blogId){
	
	Blog tempblog=blogDAO.getBlog(blogId);
	if(tempblog==null){
		return new ResponseEntity<Blog>(tempblog,HttpStatus.BAD_REQUEST);		
	}
	else
	{
	return new ResponseEntity<Blog>(tempblog,HttpStatus.OK);	
	}		
	
	
	
	}
	
	
	@RequestMapping(value="/deleteBlog/{blogid}",method=RequestMethod.GET)
	public ResponseEntity<String> deleteBlog(@PathVariable("blogid") int blogId){
	
		Blog blog=blogDAO.getBlog(blogId);
	if(blogDAO.deleteBlog(blog))
	{
		return new ResponseEntity<String>("Blog deleted successfully",HttpStatus.OK);	
	}
	else
	{
	
	return new ResponseEntity<String>("Problem in deleting blog",HttpStatus.BAD_REQUEST);	
	
	}
	
	
	
	}
	
	@RequestMapping(value="/updateBlog/{blogid}/{blogname}/{blogcontent}",method=RequestMethod.GET)
	public ResponseEntity<String> updateBlog(@PathVariable("blogid") int blogid,@PathVariable("blogname") String blogname,@PathVariable("blogcontent") String blogcontent){
		System.out.println(blogid+"  "+blogname+" "+blogcontent);
		
		Blog tempblog=blogDAO.getBlog(blogid);
		
		tempblog.setBlogcontent(blogcontent);
		tempblog.setBlogname(blogname);
		tempblog.setStatus("P");
		
		
		boolean isSaved=blogDAO.updateBlog(tempblog);
		if(isSaved)
		return new ResponseEntity<String>("Blog updated successfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("problrm in updating blog",HttpStatus.BAD_REQUEST);
		
	}
	
	
	@RequestMapping(value="/approveBlog/{blogId}",method=RequestMethod.GET)
	public ResponseEntity<String> approveBlog(@PathVariable("blogId") int blogId){
		Blog blog=blogDAO.getBlog(blogId);
		blog.setStatus("A");
		
		boolean isSaved=blogDAO.updateBlog(blog);
		if(isSaved)
		return new ResponseEntity<String>("Blog approved successfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Problem in approving blog",HttpStatus.BAD_REQUEST);
		
		
	}
	
	
	@RequestMapping(value="/rejectBlog/{blogId}",method=RequestMethod.GET)
	public ResponseEntity<String> rejectBlog(@PathVariable("blogId") int blogId){
		Blog blog=blogDAO.getBlog(blogId);
		blog.setStatus("R");
		
		boolean isSaved=blogDAO.updateBlog(blog);
		if(isSaved)
		return new ResponseEntity<String>("Blog rejected successfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Problem in rejecting blog",HttpStatus.BAD_REQUEST);
		
	}	
	
	
	
	@RequestMapping(value="/likeBlog/{blogId}",method=RequestMethod.GET)
	public ResponseEntity<String> likeBlog(@PathVariable("blogId") int blogId){
		
		Blog blog=blogDAO.getBlog(blogId);
		blog.setLikes(blog.getLikes()+1);
		
		boolean isSaved=blogDAO.updateBlog(blog);
		if(isSaved)
		return new ResponseEntity<String>("Blog liked successfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Problem in liking blog",HttpStatus.BAD_REQUEST);
		
	}	
	
	
	@RequestMapping(value="/dislikeBlog/{blogId}",method=RequestMethod.GET)
	public ResponseEntity<String> dislikeBlog(@PathVariable("blogId") int blogId){
		Blog blog=blogDAO.getBlog(blogId);
		blog.setLikes(blog.getDislikes()+1);
		
		boolean isSaved=blogDAO.updateBlog(blog);
		if(isSaved)
		return new ResponseEntity<String>("Blog disliked successfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Problem in disliking blog",HttpStatus.BAD_REQUEST);
		
	}	

	
	@RequestMapping(value="/incview/{blogId}",method=RequestMethod.GET)
	public ResponseEntity<String> incview(@PathVariable("blogId") int blogId){
		
		Blog blog=blogDAO.getBlog(blogId);
		blog.setViews(blog.getViews()+1);
		boolean isSaved=blogDAO.updateBlog(blog);
		if(isSaved)
		return new ResponseEntity<String>("Blog view incremented successfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Problem in view incrementing blog",HttpStatus.BAD_REQUEST);
		
	}
	
	@RequestMapping(value="/addBlogComments/{blogid}/{UID}/{blogcomm}/{username}",method=RequestMethod.GET)
	public ResponseEntity<String> addBlogComments(@PathVariable("blogid") int blogid,@PathVariable("UID")int  u,@PathVariable("blogcomm") String blogcomm,@PathVariable("username") String username){
		System.out.println(blogid+u+blogcomm+username);
BlogComments blogcomment=new BlogComments();
blogcomment.setBlogid(blogid);
blogcomment.setUserid(u);
blogcomment.setBlogcomm(blogcomm);
blogcomment.setUsername(username);

		boolean isSaved=blogDAO.addBlogComment(blogcomment);
		if(isSaved)
		{
		return new ResponseEntity<String>("Blogcomment added successfully",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Problem in adding blog comment",HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@RequestMapping(value="/updateBlogComments",method=RequestMethod.POST)
	public ResponseEntity<String> updateBlogComments(@RequestBody BlogComments blogcomment){
		BlogComments tempblogcomment=blogDAO.getBlogComment(blogcomment.getBlogcomid());
		tempblogcomment.setBlogcomm(blogcomment.getBlogcomm());
		boolean isSaved=blogDAO.updateBlogComment(tempblogcomment);
		if(isSaved)
		return new ResponseEntity<String>("Blog comment updated successfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Problem in updating blog commnet",HttpStatus.BAD_REQUEST);
		
	}
	
	
	@RequestMapping(value="/getBlogComment/{blogcommentId}",method=RequestMethod.GET,headers = "Accept=application/json")
	public BlogComments getBlogComment(@PathVariable("blogid") int blogcommentId){
	
	BlogComments blogcomment=blogDAO.getBlogComment(blogcommentId);
	if(blogcomment==null){
		return null;
	}
	else
	{
	return blogcomment;
	}		
	
	
	
	}
	
	
	@RequestMapping(value="/getAllBlogComments/{blogId}",method=RequestMethod.GET,headers = "Accept=application/json")
	public ArrayList<BlogComments> getAllBlogComment(@PathVariable("blogId") int blogId){
	
	ArrayList<BlogComments> blogcomments=blogDAO.getAllBlogComments(blogId);
	if(blogcomments.isEmpty()){
		return null;
	}
	else
	{
	return blogcomments;
			
	}
	}
	
	
	@RequestMapping(value="/deleteBlogComment/{blogcommentid}",method=RequestMethod.GET)
	public ResponseEntity<String> deleteBlogComment(@PathVariable("blogcommentid") int blogcommentId){
	
	BlogComments blogComments=blogDAO.getBlogComment(blogcommentId);

	if(blogDAO.deleteBlogComment(blogComments))
	{
		return new ResponseEntity<String>("BlogComment deleted successfully",HttpStatus.OK);	
	}
	else
	{
		return new ResponseEntity<String>("Problem in deleting blogcomment",HttpStatus.BAD_REQUEST);
	}		
	}
	
	
	
	
	@RequestMapping(value="/blogrequests",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Blog>> getBlogRequest()
	{
		ArrayList<Blog> blogreq=(ArrayList<Blog>)blogDAO.getAllBlogRequests(); 
	return new ResponseEntity<ArrayList<Blog>>(blogreq,HttpStatus.OK);
	
	}
	
	@RequestMapping(value="/approveblogRequests/{blogid}",method=RequestMethod.GET)
	public ResponseEntity<Blog> approveBlogRequest(@PathVariable("blogid") int blogid)
	{
	Blog blog=blogDAO.getBlog(blogid);
	blog.setStatus("A");
	blogDAO.updateBlog(blog);
	return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	
	}
	
	@RequestMapping(value="/rejectblogRequests/{blogid}",method=RequestMethod.GET)
	public ResponseEntity<Blog> rejectBlogRequest(@PathVariable("blogid") int blogid)
	{
	Blog blog=blogDAO.getBlog(blogid);
	blog.setStatus("R");
	blogDAO.updateBlog(blog);
	return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	
	}
	
	
	

}
