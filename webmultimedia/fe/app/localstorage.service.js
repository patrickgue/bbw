angular.module("app").factory("LocalStorageService", function() {
  function store(id, data) {
    localStorage[id] = JSON.stringify(data);
  }

  function load(id) {
    return JSON.parse(localStorage[id]);
  }

  function del(id) {
    localStorage[id] = undefined;
  }

  return {
    load : load,
    store : store,
    del : del
  };
});
