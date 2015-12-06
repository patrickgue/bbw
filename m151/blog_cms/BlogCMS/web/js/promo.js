/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $(".box-grid").append("<div class=\"clear\"></div>")
});

$(window).on("load resize",function (){
    if($(window).height() < $("header").height() ){
        $("#scroll-down-text").css("display","block");
    } else {
        $("#scroll-down-text").css("display","none");
    }
});


$(document).on("scroll", function(){
    if($(window).height() < $("header").height() ){
        if($(this).scrollTop() > $(window).height()/3){
            if($("#scroll-down-text").css("display") == "block"){
                $("#scroll-down-text").fadeOut();
            }
        } else {
            if($("#scroll-down-text").css("display") == "none"){
                $("#scroll-down-text").fadeIn();
            }
        }
    }
});

$("#scroll-down-text").on("click", function(){
    $('html, body').animate({scrollTop: $("header").height()}, 1000);
});
