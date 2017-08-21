var helloAjaxApp = angular.module("myApp", ['ui.multiselect']);
 
helloAjaxApp.controller("myCtrl", [ '$scope', '$http', function($scope, $http) {
 
	$scope.trialid = [];
	
	$scope.cname = ["Cluster1", "Cluster2", "Cluster3", "Cluster4", "Cluster5"];
	
    $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";

    $http({
    	method : 'GET',
        url : 'FetchTrialList'
    }).then(function(response) {
    	$scope.trialid = response.data; 
    });
    
    $scope.sendPost = function() {
    	var result = '';
    	for (var i = 0; i < $scope.studyid.length; i++) {
    		if ($scope.studyid.length == i+1) {
    			result += $scope.studyid[i].studyid;
    		} else {
    			result += $scope.studyid[i].studyid + ',';	
    		}
    	}
    	if ($scope.clusterchangeform.$valid) {
    		$http({
    			url : 'MyServlet',
    			method : "POST",
    			data : {
    				'studyid' : result,
    				'clustername' : $scope.clustername
    			}
    		}).then(function(response) {
    			console.log(response.data);
    			$scope.message = response.data;
    		}, function(response) {
    			//fail case
    			console.log(response);
    			$scope.message = response;
    		});
    	}
    	else{
    		alert('Please mention the Trial ID and cluster name to be asssigned');
    	}
    };
} ]);