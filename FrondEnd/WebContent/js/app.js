

var app = angular.module("myApp", ["ngRoute","ngCookies"]);
app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "view/main.html",
    })
    .when("/home", {
        templateUrl : "view/main.html",
    })
    .when("/login", {
        templateUrl : "view/login.html"
    })
    .when("/registration", {
        templateUrl : "view/registration.html"
    })
    .when("/job", {
        templateUrl : "view/job.html"
    })
     .when("/forum", {
        templateUrl : "view/forum.html"
    })
     .when("/chat", {
        templateUrl : "view/chat.html"
    })
      .when("/myprofile", {
        templateUrl : "view/myprofile.html",
   
        
    })
     .when("/applyjob", {
        templateUrl : "view/applyjob.html",
   
        
    })
    
     .when("/newblog", {
        templateUrl : "view/newblog.html",
       
       
    })
    
      .when("/forummanage", {
        templateUrl : "view/forummanage.html",
       
       
    })
    
    .when("/updateforum", {
        templateUrl : "view/updateforum.html",
       
       
    })
    
   
    
      .when("/forumrequests", {
        templateUrl : "view/forumrequests.html",
       
       
    })
    
      .when("/newforum", {
        templateUrl : "view/newforum.html",
       
       
    })
    
      .when("/updateforum", {
        templateUrl : "view/updateforum.html",
       
       
    })
       .when("/newjob", {
        templateUrl : "view/newjob.html",
       
       
    })
      .when("/userlist", {
        templateUrl : "view/userlist.html",
       
       
    })
    
     .when("/friendrequest", {
        templateUrl : "view/friendrequest.html",
       
       
    })
    
     .when("/notification", {
        templateUrl : "view/notification.html",
       
       
    })
    
     .when("/blogview", {
        templateUrl : "view/blogview.html",
       
       
    })
    
      .when("/blogrequests", {
        templateUrl : "view/blogrequests.html",
       
        
    })
     .when("/blog", {
        templateUrl : "view/home.html",
       
       
    })
    
    .when('/Chat',
		{
			templateUrl : "view/chat.html",
			controller : 'ChatController'	
		})
    
      .when("/userrequests", {
        templateUrl : "view/userrequests.html",
       
        
    })
      .when("/blogmanage", {
        templateUrl : "view/blogmanage.html",
        
    })
    .when("/jobmanage", {
        templateUrl : "view/jobmanage.html",
        
    })
    
     .when("/updateblog", {
        templateUrl : "view/updateblog.html",
        
    })
      .when("/myblogmanage", {
        templateUrl : "view/myblogmanage.html",
        
    })
    .when("/updatejob", {
        templateUrl : "view/updatejob.html",
        
    })
    
     .when("/myforums", {
        templateUrl : "view/myforums.html",
        
    })
   
      .when("/friends", {
        templateUrl : "view/friends.html",
        
    })
   
      .when("/viewforum", {
        templateUrl : "view/viewforum.html",
        
    })
   
    
    
    .when("/blog", {
        templateUrl : "view/blog.html"
    });
});


app.run( function ($rootScope, $location, $cookieStore, $http) 
		{
			 $rootScope.$on('$locationChangeStart', function (event, next, current) 
			 {
				 console.log("$locationChangeStart")
				    
				 var userPages = ['/blog','/forum','/chat'];
				 var adminPages = [];
				 
				 var currentPage = $location.path();
				 
				 var isUserPage = $.inArray(currentPage, userPages);
				 var isAdminPage = $.inArray(currentPage, adminPages);
				 
				 var isLoggedIn = $rootScope.currentuser.username;
			        
			     console.log("isLoggedIn:" +isLoggedIn)
			     console.log("isUserPage:" +isUserPage)
			     console.log("isAdminPage:" +isAdminPage)
			        
			        if(!isLoggedIn)
			        	{
			        	
			        		if(isUserPage!=-1 || isAdminPage!=-1)  
			        	 	{
				        	  console.log("Navigating to login page:")
				        	  alert("You need to Login first!")
				              $location.path('/login');
				         	}
			        	}
			        
					 else //logged in
			        	{
			        	
						 var role = $rootScope.currentuser.role;
						 if(isAdminPage!=-1 && role!='ADMIN' )
							 {
							  alert("You cannot view this page as a " + role )
							  $location.path('/');
							 }
			        	}
			 });
			 
			 // to keep the user logged in after page refresh
		    $rootScope.currentuser = $cookieStore.get('currentUser') || {};
		    if ($rootScope.currentuser)
		    {
		        $http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentuser; 
		    }

		 // to keep the blog view in after page refresh
		 $rootScope.gblog = $cookieStore.get('blog') || {};
		 if ($rootScope.gblog)
		 {
		     $http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.gblog; 
		 }


		 $rootScope.gblogcomm = $cookieStore.get('blogcomm') || {};
		 if ($rootScope.gblogcomm)
		 {
		     $http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.gblogcomm; 
		 }

		  // to keep the forum view  in after page refresh
		   $rootScope.ForumByid = $cookieStore.get('forum') || {};
		    if ($rootScope.ForumByid)
		    {
		        $http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.ForumByid; 
		    }
		    
		    $rootScope.fcheck = $cookieStore.get('forumcheck') || {};
		    if ($rootScope.fcheck)
		    {
		        $http.defaults.headers.common['Authorization'] = 'Basic' +$rootScope.fcheck; 
		    }
		    
		    
		    $rootScope.gforumcomm = $cookieStore.get('forumcomm') || {};
		    if ($rootScope.gforumcomm)
		    {
		        $http.defaults.headers.common['Authorization'] = 'Basic' +$rootScope.gforumcomm; 
		    }
		
		});
