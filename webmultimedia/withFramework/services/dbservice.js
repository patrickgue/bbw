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
	db.run("CREATE TABLE TMUL_MEDIA ("
	       + "mediaId INTEGER PRIMARY KEY AUTOINCREMENT, "
	       + "mediaType STRING, "
	       + "mediaTitle STRING,"
	       + "mediaDescription STRING,"
	       + "mediaPicturePath STRING, "
	       + "mediaResourcePath STRING,"
	       + "mediaUserId INTEGER"
	       + ");");

	db.run("INSERT INTO TMUL_USER (userId, userName, userPassword) "
	       + "VALUES(1, \"pat\", \"a9993e364706816aba3e25717850c26c9cd0d89d\")");

	db.run("INSERT INTO TMUL_MEDIA (mediaId, mediaType, mediaTitle, mediaDescription, "
	       + "mediaPicturePath, mediaResourcePath, mediaUserId)"
	       + " VALUES (1, \"IMAGE\", \"Alfa Romeo\", \"Alfa Romeo Automobiles S.p.A. is an Italian car manufacturer. Founded as A.L.F.A., translating to Anonymous Lombard Automobile Factory in English) on June 24, 1910, in Milan, the company has been involved in car racing since 1911.\", \"data/WP_20160804_07_44_49_Rich.jpg\", \"data/WP_20160804_07_44_49_Rich.jpg\", 1)");


	

	       
	    
	       
	
      
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
