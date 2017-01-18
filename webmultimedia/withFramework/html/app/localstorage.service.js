angular.module("app").factory("LocalStorageService", function() {
  function store(id, data) {
    localStorage[id] = JSON.stringify(data);
  }

  function load(id) {
    try{
        return JSON.parse(localStorage[id]);
    } catch(e){
      try {
        return localStorage[id];
      } catch(e) {}
    }

  }

  function del(id) {
    try{
      localStorage[id] = undefined;
    } catch(e){}

  }

  return {
    load : load,
    store : store,
    del : del
  };
});
