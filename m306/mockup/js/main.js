$(document).ready(function() {
  $("header").load("pageelms/menu.html");
  $(".contact").load("pageelms/contact.html");
  $(".hamsterMenu").on("click", function() {
    var id = $(this).attr("data-id");
    $(".hamsterMenuWrapper").hide();
    $("[data-id='w"+id+"']").show();
  });

  $(".close").on("click", function() {
    $(".hamsterWindow").hide();
    $(".hamsterMenuWrapper").show();
  });
});
