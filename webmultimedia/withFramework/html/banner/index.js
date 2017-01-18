$(window).on("load resize", function() {
  $(".frame").css({
    "width" : $(window).width() + "px",
    "height" : $(window).height() + "px"
  });

  $("#frameholder").css({
    "width" : ($(window).width() * 3.2) + "px",
    "height" : $(window).height() + "px"
  });


});

setInterval(animation, 8000)
animation();
function animation() {
  setTimeout(function() {
    $("#frameholder").animate({"margin-left" : (-1 * $(window).width()) + "px"}, 300)
  },2000);

  setTimeout(function() {
    $("#frameholder").animate({"margin-left" : (-2 * $(window).width()) + "px"}, 300)
  },4000);

  setTimeout(function() {
    $("#frameholder").animate({"margin-left" : "0px"}, 300)
  },8000);
}
