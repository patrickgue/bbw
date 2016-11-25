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
 

(function() {
    "use strict";
    module("log",[], function() {
	function info(...elm) {
	    console.log("[info]", ... elm)
	}

	function debug(...elm) {
	    console.log("[debug]", ... elm);
	}

	function err(...elm) {
	    console.log("[error]", ... elm);
	}

	return {
	    info,
	    debug,
	    err
	}
    });

    module("dom",["log"], function(log) {
	function getAll(query) {
	    var domElements = document.querySelectorAll(query);
	    if(domElements === undefined) {
		log.err("Element is undefined");
	    }
	    return domElements;
	}

	function getSingle(query) {
	    return getAll(query)[0];
	}

	return {
	    get : getSingle,
	    getSingle,
	    getAll
	}
    });

    module("css", [], function() {
	function css(dom, property, value) {

	    if(dom instanceof Array || dom instanceof NodeList) {
		for(var i = 0; i < dom.length; i++) {
		    var d = dom[i];
		    d.style[property] = value;
		}
	    }
	    else {
		dom.style[property] = value;
	    }
	}
	
	return css;
    });

    module("d", ["dom","css"], function(dom, css) {
	var chainedFuncs = function(selector) {
	    var self = new Object();

	    if(selector instanceof Node) {
		self.domObjs = [selector];
	    }
	    else if (selector instanceof Array) {
		self.domObjs = selector;
	    }
	    else if(typeof selector === "string") {
		self.domObjs = dom.getAll(selector);
	    }
	    else  if(selector.domObjs !== undefined) {
		self = selector
	    }
		
	    self.style = function (property, style) {
		css(self.domObjs, property, style);
		return self;
	    };
	    
	    self.get = function (index) {
		var singleDomObj = self.domObjs[index];
		self.domObjs = [singleDomObj];
		return self;
	    };

	    self.content = function(newContent) {
		if(newContent !== undefined) {
		    for(var d of self.domObjs) {
			d.innerHTML = newContent;
		    }
		}
		else {
		    var singleObj = self.get(0);
		    return singleObj.domObjs[0].innerHTML;
		}
	    };


	    
	    self.each = function(func) {
		for(var i = 0; i < self.domObjs.length; i++) {
		    func(chainedFuncs(self.domObjs[i]), i, self.domObjs.length);
		}
	    };
	    
	    return self;
	}
	
	return chainedFuncs;
    });

    module("repeat",["log"], function(log) {
	var self = Object();

	self.intervalId = undefined;
	
	self.start = function(func, interv) {
	    if(self.intervalId === undefined) {
		self.intervalId = setInterval(func, interv);
	    }
	    else {
		log.err("interval already started");
	    }
	};

	self.stop = function() {
	    if(self.intervalId === undefined) {
		log.err("no interval started");
	    }
	    else {
		clearInterval(self.intervalId);
		self.intervalId = undefined;
	    }
	};

	return self;
    });
})();
