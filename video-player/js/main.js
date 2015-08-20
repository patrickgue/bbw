$(document).ready(function(){
    $(".myPlayer").wrap("<div class=\"myPlayerWrapper\"></div>");
    $(".myPlayerWrapper").append("<button class=\"myPlayerPlay\"><span class=\"fa fa-play\"></span></button>"+
			         "<input class=\"myPlayerRange\" type=\"range\"/>"+
				 "<button class=\"myPlayerReset\"><span class=\"fa fa-repeat\"></span></button>"+
				 "<button class=\"myPlayerFullscreen\"><span class=\"fa fa-expand\"></span></button>"+
				 "<div style=\"clear: both;\"></div>");

    setInterval(function(){
	var players = $(".myPlayer");
	for(var it = 0; it < players.length; it++){
	    $(players[it]).parent().find(".myPlayerRange").attr("max", players[it].duration);
	    $(players[it]).parent().find(".myPlayerRange").attr("value", players[it].currentTime);
	}
    }, 50);

    $(".myPlayerRange").on("change", function(){
	$(this).parent().find("video").get(0).currentTime = this.value;
    });

    $(".myPlayerReset").on("click", function(){
	$(this).parent().find("video").get(0).currentTime = 0;
	$(this).parent().find("video").get(0).play();
	$(this).find("span").removeClass("fa-play");
	$(this).parent().find(".myPlayerPlay").find("span").addClass("fa-pause");
    });

    $(".myPlayerFullscreen").on("click", function(){
	if($(this).parent().css("position") != "fixed"){

	    $(this).parent().css({
		"position" : "fixed",
		"left" : "0px",
		"top" : "0px",
		"width": $(window).width()+"px",
		"height" : $(window).height()+"px"
	    });
	    
	    $(this).parent().find("video").css("height", $(window).height() - 30 +"px");
	    $(this).find("span").removeClass("fa-expand");
	    $(this).find("span").addClass("fa-compress");
	    $(this).parent().find(".myPlayerRange").css("width", ($(window).width()-120)+"px");
	    
	    
	} else {
	    $(this).parent().css({
		"position" : "relative",
		"left" : "",
		"top" : "",
		"width": "640px",
		"height" : "auto"
	    });

	    $(this).parent().find("video").css("height","auto");
	    $(this).find("span").removeClass("fa-compress");
	    $(this).find("span").addClass("fa-expand");

	    $(this).parent().find(".myPlayerRange").css("width", "520px");
	    
	}
    });

    $(".myPlayerRange").on("mousedown", function(){
	$(this).parent().find("video").attr("data-paused", $(this).parent().find("video").get(0).paused);
	$(this).parent().find("video").get(0).pause();
    });

    $(".myPlayerRange").on("mouseup", function(){
	if($(this).parent().find("video").attr("data-paused") == "false"){
	    $(this).parent().find("video").get(0).play();
	}
    });
    
    $(".myPlayerPlay").on("click", function(){
	if($(this).parent().find("video").get(0).paused == true){
	    $(this).parent().find("video").get(0).play();
	    $(this).find("span").removeClass("fa-play");
	    $(this).find("span").addClass("fa-pause");
	    
	} else {
	    $(this).parent().find("video").get(0).pause();
	    $(this).find("span").removeClass("fa-pause");
	    $(this).find("span").addClass("fa-play");
	}
    });

    
});
