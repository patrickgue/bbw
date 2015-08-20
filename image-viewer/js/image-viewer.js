$(document).ready(function(){
    $("[data-image]").css("cursor", "pointer");
    $("[data-image]").on("click", function(){
	imageViewer.openFrame();
	imageViewer.setImg($(this).attr("src"));
	imageViewer.currentLabel = $(this).attr("data-image");
    });
    
    
    $(window).on("resize load", function(){
	try{
	    $("#imgvImg").css({
		"width" : "auto",
		"height" : "auto"
	    });
	    $("#imgvPrev span, #imgvNext span").css({
		"padding-top": ($(window).height()/2)-5+"px",
	    });
	    imageViewer.positionImg();
	} catch(e){

	}
    });
    
    
    
    
    var imageViewer = {
	"currentLabel" : "",
	"openFrame" : function(){
	    $("body").append("<div id=\"imgvMainLayer\">"+
			     "  <div id=\"imgvClose\">"+
			     "    <span>close</span>" +
			     "  </div>"+
			     "  <img id=\"imgvImg\"/>"+
			     "  <div id=\"imgvPrev\"><span>prev</span></div>"+
			     "  <div id=\"imgvNext\"><span>next</span></div>"+
			     "</div>");
	    $("#imgvClose, #imgvClose span").on("click", function(){
		imageViewer.closeFrame();
	    });
	    $("#imgvNext").on("click", function(){
		imageViewer.nextImg();
	    });
	    $("#imgvPrev").on("click", function(){
		imageViewer.prevImg();
	    });
	    $("#imgvPrev span, #imgvNext span").css({
		"padding-top": ($(window).height()/2)-5+"px",
	    });
	},
	"closeFrame" : function(){
	    try{
		$("#imgvMainLayer").remove();
	    } catch(e){
		
	    }
	},
	"setImg" : function(uri){
	    $("#imgvImg").attr("src", uri);
	    imageViewer.positionImg();
	},
	"positionImg" : function(){
	    if($(window).width() > $(window).height()){
		if($("#imgvImg").width() > $("imgvImg").height()){
		    $("#imgvImg").height($(window).height()*0.9+"px");
		} else {
		    $("#imgvImg").width($(window).width()*0.9+"px");
		}
	    } else {
		if($("#imgvImg").width() > $("imgvImg").height()){
		    $("#imgvImg").width($(window).width()*0.9+"px");
		} else {
		    $("#imgvImg").height($(window).height()*0.9+"px");
		}
	    }

	    if($("#imgvImg").width() > $(window).width()){
		$("#imgvImg").css({
		    "width" : $(window).width()*0.95+"px",
		    "height" : "auto"
		});
	    }

	    if($("#imgvImg").height() > $(window).height()){
		$("#imgvImg").css({
		    "width" : "auto",
		    "height" : $(window).height()*0.95+"px"
		});
	    }
	    
	    $("#imgvImg").css("margin-left", ($(window).width()-$("#imgvImg").width())/2+"px");
	    $("#imgvImg").css("margin-top", ($(window).height()-$("#imgvImg").height())/2+"px");
	},
	"nextImg" : function(){
	    try{
	
		$("#imgvImg").animate({"margin-left": (-1*$(window).width())+"px"},500,"linear",function(){
		    $("#imgvImg").css("margin-left", $(window).width()+"px");
		});

		setTimeout(function(){
		    var imgs = $("[data-image='"+imageViewer.currentLabel+"']");
		    var it;
		    for(it = 0; it < imgs.length; it++){
			if($(imgs[it]).attr("src") == $("#imgvImg").attr("src") && it < imgs.length+1){
			    imageViewer.setImg($(imgs[it+1]).attr("src"));
			    break;
			} else {
			    console.log($(imgs[it]).attr("src")+" | "+$("#imgvImg").attr("src"));
			    
			}
		    }
		}, 450);
		
		setTimeout(function(){
		    $("#imgvImg").animate({"margin-left": ($(window).width()-$("#imgvImg").width())/2 +"px"},500);
		}, 500);
		
	    } catch(e){

	    }
	    $("#imgvImg").css({"width" : "auto", "height" : "auto"});
	    imageViewer.positionImg();
	},
	"prevImg" : function(){
	    try{
		var margLeft = $("#imgvImg").css("margin-left");
		$("#imgvImg").animate({"margin-left": $(window).width()+"px"},500,"linear",function(){
		    $("#imgvImg").css("margin-left", (-1*$(window).width())+"px");
		});


		setTimeout(function(){
		    var imgs = $("[data-image='"+imageViewer.currentLabel+"']");
		    var it;
		    for(it = 0; it < imgs.length; it++){
			if($(imgs[it]).attr("src") == $("#imgvImg").attr("src") && it > 0){
			    imageViewer.setImg($(imgs[it-1]).attr("src"));
			    break;
			} else {
			    console.log($(imgs[it]).attr("src")+" | "+$("#imgvImg").attr("src"));
			}
		    }
		}, 450);

		setTimeout(function(){
		    $("#imgvImg").animate({"margin-left": ($(window).width()-$("#imgvImg").width())/2 +"px"},500);
		}, 500);
		
	    } catch(e){

	    }
	    $("#imgvImg").css({"width" : "auto", "height" : "auto"});
	    imageViewer.positionImg();
	}
    };

    
});
