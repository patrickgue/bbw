(function() {
    var imgs = document.getElementsByClassName("img");
    for(var i = 0; i < imgs.length; i++){
	img = imgs[i];
	img.onclick = function(){
	    
	    if(img.classList.contains("open")) {
		img.classList.remove("open");
	    } else {
		img.classList.add("open");
	    }
	};
    }
})();
