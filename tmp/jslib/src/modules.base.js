var module,
    main;


(function() {
    "use strict"
    var modules = [];
    
    module = function(title, mdls, fun) {
	
	var usedModules = [];
	for(var ind = 0; ind < modules.length; ind++) {
	    var m = modules[ind]
	    for(var i = 0; i < mdls.length; i++) {
		var md = mdls[i];

		if(m.name === md) {
		    usedModules.push(m.func);
		}
	    }
	}

	modules.push({
	    func : fun(... usedModules)
	    , name: title});
    };
    
    main = function(mdls, fun) {
	var usedModules = [];
	for(var ind = 0; ind < modules.length; ind++) {
	    var m = modules[ind]
	    for(var i = 0; i < mdls.length; i++) {
		var md = mdls[i];

		if(m.name === md) {
		    usedModules.push(m.func);
		}
	    }
	}

	fun(... usedModules);
    }
})();
 
