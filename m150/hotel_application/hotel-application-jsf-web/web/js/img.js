(function() {
    var imgs = document.getElementsByClassName("img");
    for(var i = 0; i < imgs.length; i++){
	imgs[i].onclick = function(){
	    if(this.classList.contains("open")) {
		this.classList.remove("open");
	    } else {
		this.classList.add("open");
	    }
	};
    }
})();
