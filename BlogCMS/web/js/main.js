$(document).ready(function(){
    $("#menu-button").on("click", function(){
        $(".left").toggle();
        if($(".left").css("display") == "none"){
            $(".content").css({"overflow" : "auto", "height" : "auto"});
            $(".left").css("height", "auto");
        } else {
            $(".content").css({"overflow" : "hidden", "height" : ($(window).height() - $("header").height())+"px"});
            $(".left").css("height", "auto");
            }
        });

    $(window).on("resize", function(){
        if($(this).width() > 768){
            $(".left").css("display", "block");
        } else {
            $(".left").css("display", "none");
        }
    });
    
    $(".general-search").on("focus", function(){
        if($(this).val() == "Search"){
            $(this).val("");
        }
    })
    
    $(".general-search").on("blur", function(){
        if($(this).val() == ""){
            $(this).val("Search");
        }
    })
    $(".general-comment").on("focus", function(){
        if($(this).val() == "Kommentar eingeben"){
            $(this).val("");
        }
    })
    
    $(".general-comment").on("blur", function(){
        if($(this).val() == ""){
            $(this).val("Kommentar eingeben");
        }
    })
});
