var message;

$(document).ready(function() {
  $("header").load("pageelms/menu.html");
  $(".contact").load("pageelms/contact.html");
  $(".hamsterMenu").on("click", function() {
    var id = $(this).attr("data-id");
    $(".hamsterMenuWrapper").hide();
    $("[data-id='w"+id+"']").show();
  });

  $(".zubtitle").on("click", function() {
    $(this).css("background-color", "#eee");
    var id = $(this).attr("data-id");
    $("[data-id='"+id+"']").show();
  });

  $(".close").on("click", function() {
    $(".hamsterWindow").hide();
    $(".hamsterMenuWrapper").show();
    $(".zubcontent").hide();
    $(".zubtitle").css("background-color", "");
  });

  message = function(text) {
    var t = document.createElement("div");
    $(t).html("<p>" + text + "</p>");
    $(t).css({
      "position" : "fixed",
      "right" : "20px",
      "bottom" : "20px",
      "color" : "#eee",
      "width" : "400px",
      "padding" : "20px",
      "border-radius" : "8px",
      "background-color" : "#FF530D"
    });

    $(document.body).append(t);

    $(t).on("click", function() {
        $(t).remove();
    });
  };

});
