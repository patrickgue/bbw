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

angular.module("app").controller("appController", function($scope) {

    $scope.media = [];

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

    $scope.$onChange = function() {
	$(".materialboxed").materialbox();
    }

    $scope.$watch("media", function(v) {
	$(".materialboxed").materialbox();
    });
    
    $scope.$init = function() {
	$(".materialboxed").materialbox();
    };

    $scope.isPhoto = function(elm) {
	return elm instanceof PictureElement;
    }

});


