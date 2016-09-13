class MediaElement{
    constructor(title, text, picture) {
	this.title = title;
	this.text = text;
	this.picture = picture;
    }
}

class PictureElement extends MediaElement {
    constructor(title, text, picture) {
	super(title, text, picture);
    }
}

class AudioElement extends MediaElement {
    constructor(title, text, picture, uri) {
	super(title, text, picture);
	this.uri = uri;
    }
}

angular.module("app").controller("appController", function($scope,httpServicem LocalStorageService) {

    $scope.media = [];
    $scope.loginUserName = "";
    $scope.loginPassword = "";


    
    function addItem(it) {
	$scope.media.push(it);
	setTimeout(function() {
	    $(".materialboxed").materialbox();
	}, 2000);

	
    }

    addItem(new PictureElement(
	    "Alfa Romeo",
	    "Alfa Romeo Automobiles S.p.A. is an Italian car manufacturer. Founded as A.L.F.A., translating to Anonymous Lombard Automobile Factory in English) on June 24, 1910, in Milan, the company has been involved in car racing since 1911.",
	    "data/WP_20160804_07_44_49_Rich.jpg"));
    
    addItem(new AudioElement(
	    "Dance Song 97",
	    "Dance Song 97 by Sleater Kinney on the Album Dig me out (1997)",
	    "data/sleaterkinney-digmeout-1425.jpg",
	"data/sp1105-12_dance_song_97.mp3"));

    addItem(new PictureElement(
	"Nikon Cameras",
	"A graphic showing the development of different Nikon Camera Models",
	"http://www.bythom.com/Images/lineage-chart.jpg"
    ));

    
    $scope.isPhoto = function(elm) {
	return elm instanceof PictureElement;
    };

    $scope.login = function() {
	httpService.post("user/login",
			 {
			     userName : $scope.loginUserName,
			     userPassword : $scope.loginPassword
			 },
			 function(data) {
			     $("#loginModal").closeModal();
			     console.log(data);
			 },
			 function(data) {
			     $scope.loginError = data.data.message;
			     console.log(data);
			 }
			);
    };

    $scope.signup = function() {
	if($scope.signupPassword === $scope.signupPasswordRep) {
	    httpService.post("user/add",
			     {
				 userName: $scope.signupUserName,
				 userPassword: $scope.signupPassword
			     },
			     function(data) { //success
				 $("#signupModal").closeModal();
			     },
			     function(data) {//error
				 $scope.error = data.data.message;
			     }
			    );
	    
	}
	else {
	    $scope.signupError = "Passwords are not equal";
	} 
    };

});


