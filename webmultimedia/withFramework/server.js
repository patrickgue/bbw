"use strict";

const mimeTypes = {
    ".html" : "text/html",
    ".js" : "application/html",
    ".css" : "text/css",
    ".png" : "png/image",
    ".jpg" : "jpeg/image",
    ".jpeg" : "jpeg/image",
    ".svg" : "application/xml+svg",
    ".ico" : "image/x-icon"
};

const defaultFile =  "index.html";


function generateToken(length) {
    var tokenString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    var token = "";
    for(var i = 0; i < length; i++) {
	token += tokenString[Math.floor(Math.random() * tokenString.length)];
    }
    return token;
}

const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const fs = require("fs");
const os = require('os');
const sha1 = require('sha1');
const database = require('./services/dbservice.mysql.js');
const multer = require('multer');
const PATH_PREFIX = "/api/v0";

const htmlFilesPath = "/html/";


var storage = multer.diskStorage({
  destination: function (req, file, cb) {
    cb(null, '/tmp/my-uploads')
  },
  filename: function (req, file, cb) {
    cb(null, file.fieldname + '-' + Date.now())
  }
})

var upload = multer({ storage: storage })


// Util methods
function error(_message) {
    this.message = _message;
}

app.use(bodyParser.json());

app.use(bodyParser.urlencoded({
    extended: true
}));

app.use(function(req, res, next) {

    if(req.originalUrl.indexOf("/api/") == -1) {

	var defaultF = "";

	if(req.originalUrl.charAt(req.originalUrl.length - 1) == "/") {
	    defaultF = defaultFile;
	}
	else {
	    defaultF = "";
	}

	console.log( __dirname, htmlFilesPath, req.originalUrl,  defaultF);

	fs.readFile( __dirname + htmlFilesPath + req.originalUrl + defaultF, function (err, data) {
	    if (err) {
		if(err.code === "ENOENT") {
		    res.status(404);
		    res.type("text/plain");
		    res.end("File not found: '"+req.originalUrl + "'");
		    console.log("err")
		}
		else {
		    res.status(500);
		    console.log(err);
		    res.end(JSON.stringify( err));
		}
	    } else {
		for(var mimeType in mimeTypes) {
		    if(req.originalUrl.indexOf(mimeType) != -1) {
			res.type(mimeTypes[mimeType]);
			break;
		    }
		}
		res.end(data);
	    }
	});
    } else {
	res.header("Content-Type", "application/json");
	next();
    }
});


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


app.post(PATH_PREFIX + '/upload', multer().array(), function (req, res, next) {
  res.end("[]")
})

app.get(PATH_PREFIX + "/media/all", function(req, res) {
    database.select("SELECT * FROM TMUL_MEDIA", function(data) {
	res.end(JSON.stringify(data));
    });
});


app.get(PATH_PREFIX + "/token", function(req, res) {
    res.end(JSON.stringify({
	token : generateToken(32)
    }));
});

var server = app.listen(4083, function() {
    var host = server.address().address;
    var port = server.address().port;
    console.log(host, port);
});
