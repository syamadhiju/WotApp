package com.niit.DAO;

import java.util.ArrayList;
import java.util.List;

import com.niit.Model.Forum;
import com.niit.Model.ForumComments;
import com.niit.Model.ForumRequests;
import com.niit.Model.User;

public interface ForumDAO 
{

	public boolean addForum(Forum forum);
	public boolean updateForum(Forum forum);
	public boolean deleteForum(Forum forum);
	public Forum getForum(int forumId);
	public ArrayList<Forum> getAllForum();
	public ArrayList<ForumRequests> getAllMyForum(int userid);
	
	
	public boolean addForumComment(ForumComments forumcomment);
	public boolean updateForumComment(ForumComments forumcomment);
	public boolean deleteForumComment(ForumComments forumcomment);
	public ForumComments getForumComment(int commentId);
	public ArrayList<ForumComments> getAllForumCommentsById(int forumid);

	
	public boolean addForumRequest(ForumRequests forumrequest);
	public boolean acceptForumRequest(ForumRequests forumrequest);
	public boolean blockUser(ForumRequests forumrequest);
	public ArrayList<ForumRequests> getAllForumRequest();
	public ForumRequests getForumRequest(int ForumReqId);
		
	public ArrayList<ForumRequests> checkIfMyForum(int ForumId, int myid);
	public ArrayList<ForumRequests> forreqbyforid(int forumid);
	public ArrayList<ForumRequests> getAllForumRequestAll(int forumid);
	public boolean deleteForumRequest(ForumRequests forumreq);
	public boolean rejectForumRequest(ForumRequests forumrequest);
	public ForumRequests myforreq(String email,int forumid);
	
}
