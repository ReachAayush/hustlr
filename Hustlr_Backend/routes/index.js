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

function checkIfUserExists(username, success, failure){
   var queryString = 'SELECT * from users WHERE username=\'' + username + '\'';
   var result;
   connection.query(queryString, function(err, rows, fields) {
      if (err) throw err;

      if (rows.length != 0){
         return success();
      }else{
         return failure();
      }
   });
}

function validatePassword(username, password, success, failure){
   var queryString = 'SELECT password from users WHERE username=\'' + username + '\'';
   connection.query(queryString, function(err, rows, fields) {
      if (err) throw err;

      if (rows[0] == password){
         return success();
      }else{
         return failure();
      }
   });  
}

function createUser(username, password, cash, success, failure){
   var queryString = 'INSERT INTO portfolio (id, cash) VALUES (NULL, ' + cash + ');';
   connection.query(queryString, function(err, rows, fields) {
      if (err) return failure();
   });
      
   var queryString1 = 'INSERT INTO users (id, username, password, portfolio_id) VALUES (NULL, \'' + username + '\' , \'' + password + '\',  LAST_INSERT_ID());';
   connection.query(queryString1, function(err, rows, fields) {
      if (err){
         return failure();
      }else{
         return success();
      }
   });
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
   }else{
      return sendJson({result: 'fail', reason: 'user does not exist'}, res);
   }
});

/* handle get request for signup attempt */
router.get('/signup', function(req, res, next) {
   var username = req.query.username;
   var password = req.query.password;
   console.log("User " + username + " tried signing up with password: " + password);


   var success = function(){
      return sendJson({result: 'fail', reason: 'user already exists'}, res);
   };

   var failure = function(){
      var f1 = function(){
         return sendJson({result: 'fail', reason: 'internal error'});
      }

      var s1 = function(){
         return sendJson({result: 'success'}, res);
      }

      createUser(username, password, '100000.00', s1, f1)
   }
   
   console.log('checking if user exists = ' + checkIfUserExists(username, success, failure));
});

module.exports = router;
