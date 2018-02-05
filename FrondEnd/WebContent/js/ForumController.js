app.controller("forumcontroller", function ($scope,$http,$location,$rootScope,$cookieStore) {
	console.log("in forum controller")
	$scope.Forum={formname:'',formcontent:'',forumid:''};
	$scope.ForumRequests={forumname:'',forumid:'',status:'P',userid:'',username:''};
	$scope.ForumComments={forumcomm:'',forumid:'',userid:'',username:''};
	function fetchAllForums()
	{
		console.log("in fetch all forums method")
	$http.get("http://localhost:9980/MiddleWare/forums/getAllForums")
		.then(function(response)
		{
			console.log("Forums retrieve successfully")
			$scope.forums=response.data;
	console.log($scope.forums)
						
		},function(error)
		{
			console.log("Error on retrieving forums")
		});
	};
	
	
	fetchAllForums();
	
	
	
	function myallforums()
	{
		
	
		$http.get("http://localhost:9980/MiddleWare/forums/myforums/"+$rootScope.currentuser.userid)
		.then(function(response)
		{
			
			$rootScope.myforum=response.data;
			
						
		},function(error)
		{
			console.log("Error on retrieving blogs")
		});	
		
		
	}
	myallforums();
	
	
	
	
	
	
	function fetchForumByIdd(idd)
	{
		
		 $http.get("http://localhost:9980/MiddleWare/forums/getForumById/"+idd).then(function(response){
				

				$rootScope.ForumByid=response.data; 
				
				},function(error){
					console.log("Error on retrieving forum")
				});
		
		

	$http.get("http://localhost:9980/MiddleWare/forums/checkIfMyForum/"+idd+"/"+$rootScope.currentuser.userid).then(function(response){
		$rootScope.fcheck=response.data;
		
		
			


		

			});
	$location.path('/viewforum');
		
		
		
	}
	
	/*$scope.addForum = function()
	{
		console.log('in add forum method');
		$http.post('http://localhost:9980/MiddleWare/forums/addForum',
				$scope.Forum).then(fetchAllForums(), function(response) {
			console.log("Forum added successfully");
			$location.path('/forummanage')
		});
	}*/
	
	
	 $scope.addForum=function()
	 {
		console.log("in add forum method"+$scope.Forum.forumname)
		 $http.post("http://localhost:9980/MiddleWare/forums/addForum",$scope.Forum).then(fetchAllForums(),function(response)
				 {
			 $scope.User=response.data;
			 console.log("Forum added successfully")
								
			},function(error){
				console.error("Error while creating forum")
			});
		$location.path('/forummanage')
		 
	 }
	 
$rootScope.fetchforumbyid=function(idd)
{
console.log('in fetch forum by id method'+idd)
	 $http.get("http://localhost:9980/MiddleWare/forums/getForumById/"+idd).then(function(response){
		

			$rootScope.ForumByid=response.data; 
			 $cookieStore.put('forum',$rootScope.ForumByid);
			},function(error){
				console.log("Error on retrieving forum")
			});
	
	

$http.get("http://localhost:9980/MiddleWare/forums/checkIfMyForum/"+idd+"/"+$rootScope.currentuser.userid).then(function(response){
	$rootScope.fcheck=response.data;
	$cookieStore.put('forumcheck',$rootScope.fcheck);
	
		


	

		});


$http.get("http://localhost:9980/MiddleWare/forums/getAllForumComments/"+idd)
.then(function(response)
{
	console.log(response.data);
	$rootScope.gforumcomm=response.data;
	console.log("Forum Comments : "+$rootScope.gforumcomm)
	$cookieStore.put('forumcomm',$rootScope.gforumcomm);
	
},function(error)
{
	
});		


$http.get("http://localhost:9980/MiddleWare/forums/forumreqbyforumid/"+idd)
.then(function(response)
{
	
	$rootScope.forusers=response.data;
	
	
},function(error)
{
	
});





$location.path('/viewforum');
}

$scope.fetchforumforedit=function(idd)
{
	console.log("in forum fetch for edit  method")
	 $http.get("http://localhost:9980/MiddleWare/forums/getForumById/"+idd).then(function(response){
		 

			$rootScope.eforum=response.data; 
		
			},function(error){
				console.log("Error on retrieving forum")
			});
	
	$location.path('/updateforum')

}

	 $scope.editForum=function(idd)
	 {
		console.log("in edit forum method")
		if($scope.Forum.formname==null)
			{
			$scope.Forum.formname=$rootScope.eforum.formname;
			}
		if($scope.Forum.formcontent==null)
			{
			$scope.Forum.formcontent=$rootScope.eforum.formcontent;
			}
		 $http.post("http://localhost:9980/MiddleWare/forums/updateForum/"+idd+"/"+$scope.Forum.formname+"/"+$scope.Forum.formcontent).then(fetchAllForums(),function(response){
			 console.log("Forum updated successfully");
								
			},function(error){
				console.error("Error while updating Forum");
			});
		
		
		
		 $http.get("http://localhost:9980/MiddleWare/forums/getForumById/"+$rootScope.eforum.forumid).then(function(response){
				$rootScope.eforum=response.data; 
					
				},function(error){
				
				});
		 
		 $location.path('/forummanage')
		 
		 
		 
	 }
	
	
	 $scope.deleteForum=function(idd)
	 {
		console.log("in delete forum method")
		 $http.get("http://localhost:9980/MiddleWare/forums/deleteForum/"+idd).then(fetchAllForums(),function(response){
			 console.log("Forum deleted successfully");
			 location.path('/forummanage')
								
			},function(error){
				console.error("Error while deleting Forum");
			});
		
		 $location.path('/forummanage')
	 }
	

	
	
	$scope.myforums=function()
	{
		
 $http.get("http://localhost:9980/MiddleWare/forums/myforums/"+$rootScope.currentuser.userid).then(function(response){
			 
			$scope.myforums=response.data;
								
			},function(error){
				console.error("Error while accepting forumrequets Forum");
			});	
		
		
	}
	
	
	
	 $rootScope.sendforumrequests=function(id)
	 {
		console.log('in send froum request')
		 $http.get("http://localhost:9980/MiddleWare/forums/addForumReq/"+id+"/"+$rootScope.currentuser.userid).then(function(response){
			 console.log("Forumrequested successfully");
			 $location.path('/forum')
		 });
		
		
	 }
	 
	 
	 $rootScope.leaveforum=function()
	 {
		
		
		 $http.get("http://localhost:9980/MiddleWare/forums/leaveforum/"+$rootScope.ForumByid.forumid+"/"+$rootScope.currentuser.userid).then(fetchForumByIdd($rootScope.ForumByid.forumid),function(response){
			 
		 });
		
		 $location.path('/forum')
	 }
	 
	 
	 
	 
	 $scope.addForumComment=function()
	 {
		console.log("in add forumComment method")
		console.log($rootScope.ForumByid.forumid+$rootScope.currentuser.username+$scope.ForumComments.forumcomm)

		$http.get("http://localhost:9980/MiddleWare/forums/addForumComments/"+$rootScope.ForumByid.forumid+"/"+$rootScope.currentuser.username+"/"+$scope.ForumComments.forumcomm).then(function(response){
			 console.log("BlogComments added successfully")
								
			},function(error){
				
			});
		
		$http.get("http://localhost:9980/MiddleWare/forums/getAllForumComments/"+$rootScope.ForumByid.forumid)
		.then(function(response)
		{
			
			$rootScope.gforumcomm=response.data;
			
			
		},function(error)
		{
			
		});		
		
		$location.path('/viewforum')	 
		 
	 
	 
	 
	 
	 
	 
	 
	 }
	
	
	
	
});


app.controller("forumrequestcontroller", function ($scope,$http,$location,$rootScope) {
	function fetchAllForumreq()
	{
	
	 $http.get("http://localhost:9980/MiddleWare/forums/getForumRequests")
	    .then(function(response)
	    		{
	    	
	    
		 $scope.forumrequests=response.data;
	
		 $location.path('/forumrequests')
							
		},function(error){
			console.error("Error while deleting Forum");
		});
	}
	
	
	fetchAllForumreq();
	
	
	
	
	
	
	 $rootScope.acceptforumrequests=function(id)
	 {
		 
		 
		console.log("in forum request  accept method")
		 $http.get("http://localhost:9980/MiddleWare/forums/approveForumRequests/"+id).then(fetchAllForumreq(),function(response){
			 
			 console.log("Forumrequests accepted  successfully");
			 $location.path('/forumrequests')
								
			},function(error){
				console.error("Error while accepting forumrequets Forum");
			});
		$location.path('/forummanage')
		 
	 }
	 $rootScope.rejectforumrequests=function(id)
	 {
		 
		 
		console.log("in forum reject  accept method")
		 $http.get("http://localhost:9980/MiddleWare/forums/rejectForumRequests/"+id).then(fetchAllForumreq(),function(response){
			 
			 console.log("Forumrequests rejected  successfully");
			 $location.path('/forumrequests')
								
			},function(error){
				console.error("Error while rejecting forumrequets Forum");
			});
		$location.path('/forummanage')
		 
	 }

	 
	
	
});