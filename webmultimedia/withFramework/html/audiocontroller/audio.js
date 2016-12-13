(function() {

    function AudioPlayerController() {
	let self = this;
	self.playing = false;
	
	
	self.play = function(e) {

	    let audio = $(e.target).parent().parent().find("audio");
	    audio[0].play();
	    self.playing = true;
	}

	self.pause = function(e) {
	    let audio = $(e.target).parent().parent().find("audio");
	    audio[0].pause();
	    self.playing = false;
	}

	
	self.replay = function(e) {
	    let audio = $(e.target).parent().parent().find("audio");
	    audio[0].pause();
	    let source = $(audio[0]).find("source");
	    source[0].src = source[0].src;
	    audio[0].currentTime = '1';
	    self.playing = false;
	}
    }

    angular.module("app").component("audioPlayer", {
	controller : AudioPlayerController,
	bindings : {
	    "src" : "="
	},
	templateUrl : "audiocontroller/audio.html"
    });

})();
