(function() {
    var menuButton = document.getElementById("menu-button");
    var menu = document.getElementById("menu")

    menuButton.onclick = function(){
	if(menu.classList.contains("open")){
	    menu.classList.remove("open");
	    menuButton.classList.remove("active");
	} else {
	    menu.classList.add("open");
	    menuButton.classList.add("active");
	}
    };
})();
