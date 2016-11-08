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
	    self.playing = false;
	    video[0].pause();
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
