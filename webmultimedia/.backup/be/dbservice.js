var fs = require('fs');
var sqlite3 = require('sqlite3').verbose();
var file = "database.db";
var exists = fs.existsSync(file);
var db = new sqlite3.Database(file);

db.serialize(function(){
  if(!exists) {

    /* tables */
    db.run("CREATE TABLE TMUL_USER ("
              + "userId INTEGER PRIMARY KEY AUTOINCREMENT, "
              + "userName STRING, "
              + "userPassword STRING"
            + ")");
  }});


function select(query, done) {
  var rows = [];
  db.each(query, function(err,row) {
    rows.push(row);
  }, function() {
    if(done !== undefined) {
      done(rows);
    }
  });
}

function run(query, argsarray, done) {
  var stmt = db.prepare(query);
  stmt.run(argsarray);
  stmt.finalize();
  select("SELECT last_insert_rowid();", function(d) {
    if(done !== undefined){
	done(d[0]["last_insert_rowid()"]);
    }
  });

}

module.exports = {
  select : select,
  run : run
}
