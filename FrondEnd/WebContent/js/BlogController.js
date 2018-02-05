app.controller("blogcontroller", function ($scope,$http,$location,$rootScope,$route,$cookieStore) {
	console.log("blog controller",$rootScope.currentuser.email_id)
	 
	 $scope.Blog={blogname:'',blogcontent:'',status:'P',likes:'0',dislikes:'0',views:'0',username:$rootScope.currentuser.email_id};
	$scope.BlogComments={blogcomm:'',blogid:'',userid:'',username:''};

	function fetchAllBlogs()
	{
	$http.get("http://localhost:9980/MiddleWare/blogs/getAllBlogs")
		.then(function(response)
		{
			console.log("Blogs retrieve successfully")
			$scope.blogs=response.data;
			console.log($scope.blogs)
						
		},function(error)
		{
			console.log("Error on retrieving blogs")
		});
	};
	fetchAllBlogs();
	
	function myallblogs()
	{
		console.log("in all my blogs method")
		console.log($rootScope.currentuser.email_id)
		$http.get("http://localhost:9980/MiddleWare/blogs/getAllMyBlogs/"+$rootScope.currentuser.userid)
		.then(function(response)
		{
			
			$rootScope.myblogs=response.data;
			
						
		},function(error)
		{
			console.log("Error on retrieving blogs")
		});	
		
		
	}
	myallblogs();
	
	function fetchBlogById(idd)
	{
		
		 $http.get("http://localhost:9980/MiddleWare/blogs/getBlogById/"+idd).then(function(response){
				$scope.blogbyid=response.data;
				$rootScope.gblog=response.data;
				 
				
				console.log("blog fetched successfully")				
				},function(error){
					console.log("error in fetching blog")
				});
		
	}
	
	
	
	
	 $scope.maximum=getSelectedBlog
	 function getSelectedBlog(idd)
	 {
		 console.log("in add max method------"+idd)
		 
		 
		  $http.get("http://localhost:9980/MiddleWare/blogs/incview/"+idd).then(function(response){
			
			},
			function(error){
				
			});
		 
		 console.log("view incremented")

		 $http.get("http://localhost:9980/MiddleWare/blogs/getBlogById/"+idd).then(function(response){
				$scope.blogbyid=response.data;
				$rootScope.gblog=response.data;
				 $cookieStore.put('blog',$rootScope.gblog);
				
				console.log("blog fetched successfully")				
				},function(error){
					
					console.log("error in fetching blog")
				});
	
		
		 $http.get("http://localhost:9980/MiddleWare/blogs/getAllBlogComments/"+idd)
			.then(function(response)
			{
				
				$rootScope.gblogcomm=response.data;
				console.log($rootScope.gblogcomm)
				 $cookieStore.put('blogcomm',$rootScope.gblogcomm);
			},function(error)
			{
			});		
		
		 
		
		 $location.path('/blogview')
		 
	 }
	 
	
	 
	 
	 

	 $scope.addBlog=function()
	 {
		console.log("in add blog method"+$scope.Blog.blogname)
		 $http.post("http://localhost:9980/MiddleWare/blogs/addBlog",$scope.Blog).then(fetchAllBlogs(),function(response)
				 {
			 $scope.User=response.data;
			 console.log("Blog added successfully")
								
			},function(error){
				console.error("Error while creating blog")
			});
		$location.path('/blog')
		 
	 }

	 
	 
	 $scope.fetchforedit=function(idd)
	 {
		 
		 $http.get("http://localhost:9980/MiddleWare/blogs/getBlogById/"+idd).then(function(response){
				$rootScope.eblog=response.data; 
					
				},function(error){
				
				});
		 $location.path('/updateblog')	 
	 }
	 
	 
	 
	 

	 $scope.editBlog=function()
	 {
		 console.log("blog nammme : ",$scope.Blog.blogname+"---Blog Contents "+$scope.Blog.blogcontent)
			console.log("rootscope blog",$rootScope.eblog)
			console.log("scope blog",$scope.Blog)
			if($scope.Blog.blogname=='')
				{
				  alert("Blog Name is empty..");
				  $scope.Blog.blogname=$rootScope.eblog.blogname;
				}
			if($scope.Blog.blogcontent=='')
			{
			  alert("Blog blogcontent is empty..");
			  $scope.Blog.blogcontent=$scope.eblog.blogcontent;
			}
			console.log("scope blog",$rootScope.Blog)
		 $http.get("http://localhost:9980/MiddleWare/blogs/updateBlog/"+$rootScope.eblog.blogid+"/"+$scope.Blog.blogname+"/"+$scope.Blog.blogcontent).then(function(response){
			 console.log("Blog updated successfully");
								
			},function(error){
				console.error("Error while updating blog");
			});
		 
		 
		 $http.get("http://localhost:9980/MiddleWare/blogs/getBlogById/"+$rootScope.eblog.blogid).then(function(response){
				$rootScope.eblog=response.data; 
					
				},function(error){
				
				});
		
		$location.path('/myblogmanage')	 
		 
	 }
	 
	 $scope.deleteBlog=function(idd)
	 {
		console.log("in delete blog method")
		 $http.get("http://localhost:9980/MiddleWare/blogs/deleteBlog/"+idd).then(fetchAllBlogs(),function(response){
			 console.log("Blog deleted successfully");
								
			},function(error){
				console.error("Error while deleting blog");
			});
		
		
		
		$location.path('/myblogmanage')	 
		 
	 }
	 
	 
	
	 
	 $scope.rejetcBlog=function(id)
	 {
		console.log("in reject blog method")
		 $http.get("http://localhost:9980/MiddleWare/blogs/rejectBlog/"+id).then(fetchBlog(id),fetchAllBlogs(),function(response){
			 console.log("Blog rejected successfully");
								
			},function(error){
				console.error("Error while rejecting blog");
			});
		$location.path('/blog')	 
		 
	 }
	 
	 $scope.likeBlog=function(idd)
	 {
		console.log("in like blog method")
		 $http.get("http://localhost:9980/MiddleWare/blogs/likeBlog/"+idd).then(fetchBlogById(idd),function(response){
			 console.log("Blog liked successfully");
								
			},function(error){
				console.error("Error while liking blog");
			});
		
		$location.path('/blogview')	 
		 
	 }
	 
	 
	 $scope.dislikeBlog=function(idd)
	 {
		console.log("in like blog method")
		 $http.get("http://localhost:9980/MiddleWare/blogs/dislikeBlog/"+idd).then(fetchBlogById(idd),function(response){
			 console.log("Blog disliked successfully");
								
			},function(error){
				console.error("Error while disliking blog");
			});
		
		$location.path('/blog')	 
		 
	 }

	 
	 
	 $scope.addBlogComment=function()
	 {
		console.log("in add blogComment method")
		console.log($rootScope.gblog.blogid+$rootScope.currentuser.email_id+$scope.BlogComments.blogcomm)

		$http.get("http://localhost:9980/MiddleWare/blogs/addBlogComments/"+$rootScope.gblog.blogid+"/"+$rootScope.currentuser.userid+"/"+$scope.BlogComments.blogcomm+"/"+$rootScope.currentuser.email_id).then(function(response){
			 console.log("BlogComments added successfully")
								
			},function(error){
				
			});
		
		$http.get("http://localhost:9980/MiddleWare/blogs/getAllBlogComments/"+$rootScope.gblog.blogid)
		.then(function(response)
		{
			
			$rootScope.gblogcomm=response.data;
			
			
		},function(error)
		{
			
		});		
		
		$location.path('/blogview')	 
		 
	 }
	 
	 
	 $scope.updateBlogComment=function(idd)
	 {
		console.log("in update blogComment method")
		 $http.post("http://localhost:9980/MiddleWare/blogs/updateBlogComments/"+idd,$scope.BlogComments).then(function(response){
			 console.log("BlogComments updated successfully")
								
			},function(error){
				console.error("Error while updating blogComments")
			});
		$location.path('/blog')	 
		 
	 }
	 
	 
	 $scope.deleteBlogComment=function(idd)
	 {
		console.log("in delete blogcomment method")
		 $http.get("http://localhost:9980/MiddleWare/blogs/deleteBlogComment/"+idd).then(function(response){
			 console.log("Blogcomments deleted successfully");
								
			},function(error){
				console.error("Error while deleting blogcomments");
			});
		
		$location.path('/blog')	 
		 
	 }
	 
	 
	 
});



app.controller("blogrequestcontroller", function ($scope,$http,$location,$rootScope) {
	function fetchAllblogreq()
	{
		console.log("in fetch all blog REquests....");
	
	 $http.get("http://localhost:9980/MiddleWare/blogs/blogrequests")
	    .then(function(response)
	    		{
	    	
	    
		 $scope.blogequests=response.data;
	
		 $location.path('/blogrequests')
							
		},function(error){
			
		});
	}
	fetchAllblogreq();
	
	 $scope.acceptblogrequests=function(id)
	 {
		 
		 console.log('in acceptblog request method')
		
		 $http.get("http://localhost:9980/MiddleWare/blogs/approveblogRequests/"+id).then(fetchAllblogreq(),function(response){
			 
		console.log("accepted successfully")
			 $location.path('/blogrequests')
								
			},function(error){
				console.error("Error while accepting blogrequests Forum");
			});
		 
	 }
	 $scope.rejectblogrequests=function(id)
	 {
		 
		 console.log('in reject request method')
		
		 $http.get("http://localhost:9980/MiddleWare/blogs/rejectblogRequests/"+id).then(fetchAllblogreq(),function(response){
			 
		console.log("rejected successfully")
			 $location.path('/blogrequests')
								
			},function(error){
				console.error("Error while accepting blogrequests Forum");
			});
		 
	 }
	 
	 
	 
	
	
});