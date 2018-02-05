
app.controller("friendcontroller", function ($scope,$http,$location,$rootScope,$cookieStore) {
	$scope.Friend={U_ID:'',friendid:'',status:'P'};	
	function fetchAlluser()
	{
	
	 $http.get("http://localhost:9980/MiddleWare/user/getAllUsers/"+ $rootScope.currentuser.userid)
	    .then(function(response)
	    		{
	    	
	    
		 $scope.users=response.data;
	
		 /*$location.path('/userlist')*/
							
		},function(error){
			console.error("Error while fetching all users");
		});
	}
	
	
	fetchAlluser();
	
	

	function fetchAllUsers()
	{
		
		
		$http.get("http://localhost:9980/MiddleWare/friend/getMyFriends/"+ $rootScope.currentuser.userid)

		.then(function(response) {
			$scope.myfriends = response.data;
			console.log($scope.myfriends)
			console.log("all my friends fetched")
		},function(error)
		{
			console.log("Error on retrieving forums")
		});
		
		
		$http.get("http://localhost:9980/Middleware/friend/getAllOtherUsers/"+ $rootScope.currentuser.userid)

		.then(function(response) {
			$scope.otherusers = response.data;
			console.log("all other users fetched")
		},function(error)
		{
			console.log("Error on retrieving forums")
		});
		

		$http.get("http://localhost:9980/MiddleWare/friend/getOnlineFriends/"+ $rootScope.currentuser.userid)

		.then(function(response) {
			$scope.onlineusers = response.data;
			console.log("all online users fetched")
		},function(error)
		{
			console.log("Error on retrieving forums")
		});
		
		
		$http.get("http://localhost:9980/MiddleWare/friend/getAllMyFriendRequests/"+ $rootScope.currentuser.userid)

		.then(function(response) {
			$scope.myfriendreqs = response.data;
			console.log("all my friendsreqs  fetched")
		},function(error)
		{
			console.log("Error on retrieving forums")
		});
		
		
		
		
	}
	;
	fetchAllUsers();
	
	$scope.insertFriend = function(friendid)
	{
		console.log('entered add friend method'+friendid);
		$http.get('http://localhost:9980/MiddleWare/friend/addFriend/'+$rootScope.currentuser.userid+'/'+friendid)
		.then(fetchAllUsers(), function(response) {
			console.log("successful friend add ");
			$location.path("/userlist")
		});
	}
	
	
	

	$scope.unfriend = function(friendid)
	{
	console.log("in unfriend method")
		$http.get('http://localhost:9980/MiddleWare/friend/unfriend/'+$rootScope.currentuser.userid+'/'+friendid)
		.then(fetchAllUsers(), function(response) {
			console.log("successful friend add ");
			$location.path("/myprofile")
		});
	}
	
	
	$rootScope.friendpreview=function(friendid)
	{
		if(friendid==$rootScope.currentuser.userid)
			{
			$location.path("/myprofile")
			}
		else
			{
		$http.get("http://localhost:9980/MiddleWare/user/getUser/"+friendid)

		.then(function(response) {
			$rootScope.friendpreviewdata = response.data;
			$scope.fr=response.data;
			console.log($rootScope.friendpreviewdata.email_id);
			console.log($rootScope.friendpreviewdata.userid);
			console.log($rootScope.currentuser.userid);
		},function(error)
		{
			console.log("Error on retrieving forums")
		});

	$http.get("http://localhost:9980/MiddleWare/user/ismyfriend/"+$rootScope.friendpreviewdata.userid+"/"+$rootScope.currentuser.userid)

		.then(function(response) {
			$rootScope.ismyfriend = response.data;
		
		},function(error)
		{
			
		});
		
		
		$http.get("http://localhost:9980/MiddleWare/user/friendsfriends/"+$rootScope.friendpreviewdata.userid+"/"+$rootScope.currentuser.userid)

		.then(function(response) {
			$rootScope.friendsfriends = response.data;
		
		},function(error)
		{
			
		});
		
		
		
		
		$http.get("http://localhost:9980/MiddleWare/blogs/getAllMyBlogs/"+$rootScope.friendpreviewdata.userid)
		.then(function(response)
		{
			
			$rootScope.friendblogs=response.data;
			
						
		},function(error)
		{
			console.log("Error on retrieving blogs")
		});	
		
		$http.get("http://localhost:9980/MiddleWare/forums/myforums/"+$rootScope.friendpreviewdata.userid)
		.then(function(response)
		{
			
			$rootScope.friendforums=response.data;
			
						
		},function(error)
		{
			console.log("Error on retrieving blogs")
		});	
		$location.path("/friendwall")
	}
	}
	
	
});

app.controller("friendrequestcontroller", function ($scope,$http,$location,$rootScope) {
function fetchAllfriendreq()
	{
	
	$http.get("http://localhost:9980/MiddleWare/friend/getAllMyFriendRequests/"+ $rootScope.currentuser.userid)

	.then(function(response) {
		$scope.myfriendreqs = response.data;
		console.log($scope.myfriendreqs)
		console.log("all my friendsreqs  fetched")
	},function(error)
	{
		console.log("Error on retrieving request")
		});
		
	}
	fetchAllfriendreq();

	
	
	$scope.acceptfriend = function(friendid)
	{
	console.log("in unfriend method")
		$http.get('http://localhost:9980/MiddleWare/friend/acceptfriend/'+$rootScope.currentuser.userid+'/'+friendid)
		.then(fetchAllfriendreq(), function(response) {
			console.log("successful friend add ");
			$location.path("/friendrequest")
		});
	}
	
	$scope.rejectfriend = function(friendid)
	{
	console.log("in unfriend method")
		$http.get('http://localhost:9980/MiddleWare/friend/rejectfriend/'+$rootScope.currentuser.userid+'/'+friendid)
		.then(fetchAllfriendreq(), function(response) {
			console.log("successful friend reject");
			$location.path("/friendrequest")
		});
	}
	
	
	
});




