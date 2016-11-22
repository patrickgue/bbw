(function() {

    function VideoPlayerController() {
	let self = this;
	self.playing = false;
	
	
	self.play = function(e) {

	    let video = $(e.target).parent().parent().find("video");
	    video[0].play();
	    self.playing = true;
	}

	self.pause = function(e) {
	    let video = $(e.target).parent().parent().find("video");
	    video[0].pause();
	    self.playing = false;
	}

	
	self.replay = function(e) {
	    let video = $(e.target).parent().parent().find("video");
	    video[0].pause();
	    video[0].currentTime = '0';
	    self.playing = false;
	}
    }

    angular.module("app").component("videoPlayer", {
	controller : VideoPlayerController,
	bindings : {
	    "src" : "="
	},
	templateUrl : "videocontroller/video.html"
    });

})();
