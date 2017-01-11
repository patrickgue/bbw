angular.module("app").factory("httpService", function($http,appConst) {

    function get(url,success,error) {
	$http.get(appConst.httpPrefix + url).then(
		  function(data) {
		      if(success == undefined) {
			  console.log("no success method", data);
		      }
		      else {
			  success(data);
		      }
		  },
		  function(data) {
		      if(error == undefined) {
			  console.log("no error method", data);
		      }
		      else {
			  error(data);
		      }
		  }
		 );
    }

    function post(url,data,success,error) {
	$http.post(appConst.httpPrefix + url, data).then(
	    function(data) {
		if(success == undefined) {
		    console.log("no success method", data);
		}
		else {
		    success(data);
		}
	    },
	    function(data) {
		if(error == undefined) {
		    console.log("no success method", data);
		}
		else {
		    error(data);
		}
	    }
	);
    }

    
    return {
	get : get,
	post : post,
	http : $http
    };
});
