/**
 * Multimedia in webapplications - Backend
 *
 * This application is the backend of a project at
 * Berufsbildungsschule Winterthur 
 *
 * (c) 2016 by Patrick Günthard
 * This applcation is licensed under the GNU General Public
 * License version 3 or higher.
 * NO WARRANTY
 */

"use strict";


// project settings
const SERVER_PORT = 10080;
const PATH_PREFIX = "/api/v0";


// include libraries
const express = require('express');
const app = express();
const sha1 = require('sha1');
const bodyParser = require('body-parser')
const database = require('./dbservice.js');

// Util methods
function error(_message) {
    this.message = _message;
}


// configuration
app.use(function(req, res, next) {
  res.header("Content-Type", "application/json");
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
});

app.use(bodyParser.json());

app.use(bodyParser.urlencoded({
  extended: true
}));


// Services

/*
=Add User=

POST Parameters:

{
  userName: string
  userPassword: string
}

Return Success:
{
  userId: number
}

*/
app.post(PATH_PREFIX + "/user/add", function(req, res) {
    var user = req.body.userName;
    var password = sha1(req.body.userPassword);
    
    database.run(
	"INSERT INTO TMUL_USER (userName,userPassword) VALUES(?,?)",
	[user, password],
	function(data) {
	    res.end(JSON.stringify({userId : data}));
    });
});


/*
=Login=

POST Parameters: 

{
  userId : number,
  password : string
}

Return Succeess: 

{
  userId: number
}

Return Error: 

{
  message : string
}

*/
app.post(PATH_PREFIX + "/user/login", function(req, res) {
    var userId = req.body.userId;
    var password = sha1(req.body.userPassword);
    
    database.select("SELECT * FROM TMUL_USER WHERE userId = " + userId, function(data) {
	if(data.length > 0) {
	    for(let user of data) {
		if(user.userPassword == password) {
		    res.end(JSON.stringify({userId : userId}));
		}
		else {
		    res.status(500).end(JSON.stringify(new error("Wrong password")));
		}
	    }
	}
	else {
	    res.status(500);
	    res.end(JSON.stringify(new error("user not found")));
	}
    });
});



// Run HTTP Server
const server = app.listen(SERVER_PORT, function() {
  const host = server.address().address;
  const port = server.address().port;
  console.log(host, port);
});
