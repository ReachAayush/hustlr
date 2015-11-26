var express = require('express');
var mysql = require('mysql');

var connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'hustlr',
  password : 'hustle',
  database : 'hustlr_db'
});

var router = express.Router();

function sendJson(jsonData, res){
   res.setHeader('Content-Type', 'application/json');
   res.send(JSON.stringify(jsonData));
}

function checkIfUserExists(username){
   var queryString = 'SELECT * from users WHERE username=\'' + username + '\'';
   connection.query(queryString, function(err, rows, fields) {
      if (err) throw err;

      if (rows.length == 0) return true;
      return false;
   });
}

function validatePassword(username, password){
   var queryString = 'SELECT password from users WHERE username=\'' + username + '\'';
   connection.query(queryString, function(err, rows, fields) {
      if (err) throw err;

      if (rows[0] == password) return true;
      return false;
   });  
}

function createUserPortfolio(cash){
   var queryString = 'SELECT password from users WHERE username=\'' + username + '\'';
   connection.query(queryString, function(err, rows, fields) {
      if (err) throw err;

      if (rows[0] == password) return true;
      return false;
   });  
}

function createUser(username, password){

}

/* handle get request for login attempt */
router.get('/login', function(req, res, next) {
   var username = req.query.username;
   var password = req.query.password;
   console.log("User " + username + " tried logging in with password: " + password);
   //check if user exists
   if(checkIfUserExists(username)){
      //validate password
      if(validatePassword(username, password)){
         sendJson({result: 'success'}, res);
         return;
      }
      sendJson({result: 'fail', reason: 'user exists but password incorrect'}, res);
      return;
   }
   sendJson({result: 'fail', reason: 'user does not exist'}, res);
   return;
});

/* handle get request for signup attempt */
router.get('/signup', function(req, res, next) {
   var username = req.query.username;
   var password = req.query.password;
   console.log("User " + username + " tried signing up with password: " + password);
   
   //check if user exists
   if(checkIfUserExists(username)){
      return sendJson({result: 'fail', reason: 'user already exists'}, res);
   }
   //if user doesnt exist create user and create user's portfolio
   sendJson({result: 'success'}, res);
});



module.exports = router;
