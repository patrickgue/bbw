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
const fileUpload = require('express-fileupload');

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

app.use(fileUpload());

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
  userName : string,
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
    var userName = req.body.userName;
    var plainPassword = req.body.userPassword;
    var password = sha1(plainPassword);
    
    database.select("SELECT * FROM TMUL_USER WHERE userName = \"" + userName + "\"", function(data) {
	console.log("SELECT * FROM TMUL_USER WHERE userName = \"" + userName + "\"");
	if(data.length > 0) {
	    for(let user of data) {
		if(user.userPassword == password) {
		    res.end(JSON.stringify({userId : user.userId}));
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


app.post(PATH_PREFIX + "/files/upload", function(req, res) {
    console.log(req.files);
    res.end();
});


// Run HTTP Server
const server = app.listen(SERVER_PORT, function() {
  const host = server.address().address;
  const port = server.address().port;
  console.log(host, port);
});
