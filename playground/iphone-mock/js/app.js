$(document).on("ready", function(){

    $(window).on("load resize", function(){
	$("#browser-window").css({
	    "height" : $(window).height() - $("header").height() -6 + "px",
	    "margin-top" :$("header").height()
	});

	$("#url").css({
	    "width" : $(window).width() - $(".icon").width() - 16 + "px",
	});

    });

    $("#url").on("submit", function(){
	goToSite($(this).val());
    });

    $("#url").on("keydown", function(event){
	if(event.keyCode == 13){
	    goToSite($("#url").val());
	}
    });

    function goToSite(url){
	$("#browser-window").attr("src", url);
    }

});


