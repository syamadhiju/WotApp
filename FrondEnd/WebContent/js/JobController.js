app.controller("jobcontroller", function ($scope,$http,$location,$rootScope,$route) {
$scope.Job={jobprofile:'',jobdesc:'',qualification:'',salary:'',company:'',companydesc:''};	
$scope.JobApplications={jobid:'',userid:''};	

	function fetchAllJobs()
	{
		console.log("fetched all jobs")
		$http.get("http://localhost:9980/MiddleWare/jobs/getAllJobs")

		.then(function(response) {
			$scope.jobsdata = response.data;
			console.log("all jobs fetched")
		});
		
		
		console.log("fetched all jobs",$rootScope.currentuser)
		$http.get("http://localhost:9980/MiddleWare/jobs/myjobs/"+$rootScope.currentuser.userid)

		.then(function(response) {
			$rootScope.myjobs = response.data;
			console.log("jobs",$rootScope.myjobs)
			console.log("all my jobs fetched")
		});
		
		
		
	};
	
	fetchAllJobs();
	
	$scope.insertJobs = function()
	{
		console.log('entered insertJobs');
		$http.post('http://localhost:9980/MiddleWare/jobs/addJob',
				$scope.Job).then(fetchAllJobs(), function(response) {
			console.log("successful jobs entered");
			$location.path("/jobmanage")
		});
	}
	
	
	$scope.applyJob = function(idd)
	{
		console.log('apply job'+idd);
		$http.get('http://localhost:9980/MiddleWare/jobs/applyJob/'+idd+"/"+$rootScope.currentuser.userid).then(fetchAllJobs(), function(response) {
			console.log("successful jobs applied");
			$location.path("/applyjob")
		});
	}
	
	$scope.getjob = function(idd)
	{
		
		$http.get('http://localhost:9980/MiddleWare/jobs/getJob/'+idd).then(function(response) {
			$rootScope.gjob=response.data;
			console.log($rootScope.gjob.jobprofile+$rootScope.gjob.company)
		
		
	},function(error){
		console.log("Error on retrieving job")
	});
	
				
				
					
		
		$http.get('http://localhost:9980/MiddleWare/jobs/checkifapplied/'+idd+"/"+$rootScope.currentuser.userid).then(function(response) {
			$rootScope.gcheck=response.data;
			console.log(gcheck)
			
			
			});
		
		
		
		$http.get("http://localhost:9980/MiddleWare/jobs/jobapplicants/"+idd)
		.then(function(response)
		{
			
			$rootScope.jobapps=response.data;
			
			
		},function(error)
		{
			
		});

		
		
		
		$location.path("/jobs")

	}
	
	$rootScope.deletejob = function(idd)
	{
		$http({
	        method: 'GET',
	        headers: {
	            'Content-Type': 'application/json'
	        },
	        url: 'http://localhost:9980/MiddleWare/jobs/deleteJob/'+idd,
	        transformResponse: [
	            function (data) { 
	                return data; 
	            }
	        ]
	       
	    }).then(function (response) {
	    	
	    	$route.reload();
	        console.log(response);
	    }, function (response) {
	        console.log(response);
	    });
		
     		
	}	
	
	$scope.fetchjobforedit=function(idd)
	{
		
		$http.get('http://localhost:9980/MiddleWare/jobs/getJob/'+idd).then(function(response) {
			console.log('getjob method ok'+idd)
			$rootScope.ejob=response.data;
			console.log($scope.ejob);
			/*console.log('jobname'+$scope.jobbyid.company)
				console.log('jobname'+$scope.jobbyid.salary)*/
				
				
					});
		$location.path('/updatejob')
		
		
	}
	
	
/*	$scope.editjob=function(idd)
	{
		
		$http.get('http://localhost:9980/MiddleWare/jobs/updateJob/'+idd).then(function(response) {
		
			 console.log("job updated successfully");
				
		},function(error){
			console.error("Error while updating job");
		
		});
		
		
	}*/
	
	$scope.editjob=function(idd)
	{
		console.log(idd)
	if($scope.Job.jobprofile==null)
		{
		
		$scope.Job.jobprofile=$rootScope.ejob.jobprofile;
			}
		
		if($scope.Job.jobdesc==null)
		{
		
		$scope.Job.jobdesc=$rootScope.ejob.jobdesc;
			}
		
		
		if($scope.Job.qualification==null)
		{
		
		$scope.Job.qualification=$rootScope.ejob.qualification;
			}
		
		
		if($scope.Job.salary==null)
		{
		
		$scope.Job.salary=$rootScope.ejob.salary;
			}
		
		
		if($scope.Job.company==null)
		{
		
		$scope.Job.company=$rootScope.ejob.company;
			}
		
		if($scope.Job.companydesc==null)
		{
		
		$scope.Job.companydesc=$rootScope.ejob.companydesc;
			}
		
		$scope.Job.jobid=$rootScope.ejob.jobid;
		console.log("job",$scope.Job);
		$http.post("http://localhost:9980/MiddleWare/jobs/updateJob/",$scope.Job).then(fetchAllJobs(), function(response) {
		
			 console.log("job updated successfully");
				
		},function(error){
			console.error("Error while updating job");
		
		});
		
		$location.path("/jobmanage")
		
		
	}
	
	
	
	
	
});