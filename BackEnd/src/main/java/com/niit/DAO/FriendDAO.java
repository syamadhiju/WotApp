package com.niit.DAO;

import java.util.ArrayList;
import java.util.List;

import com.niit.Model.Friend;
import com.niit.Model.User;

public interface FriendDAO 
{
	public boolean addFriend(Friend friend);
	public boolean delete(Friend friend );
	public boolean accept(int friendreqid);
	public boolean reject(int friendreqid);
	public List<Friend> getfriendrequest(int friendreqid,int myid);
	public ArrayList<Friend> getAllFriendRequestsByUser(int userid);
	public ArrayList<Friend> getAllFriend();
	public ArrayList<Friend> getAllMyFriend(int myid);
	public ArrayList<Friend> getAllpendingentries(int myid);
	public ArrayList<Friend> getAllPendingrequests( int userid);
    public User getUserById(int userid);
    public Friend acceptfriendrequest(Friend friend);
    public Friend rejectfriendrequest(Friend friend);
    public ArrayList<Friend> getAllMyFriendpend(int myid);
}
