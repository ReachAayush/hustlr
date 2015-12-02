var express = require('express');
var request = require('request');
var db = require('../db_utils.js');

var router = express.Router();

function sendJson(jsonData, res){
   res.setHeader('Content-Type', 'application/json');
   res.send(JSON.stringify(jsonData));
}

function getStockPrice(symbol, callback){
   var url = "http://dev.markitondemand.com/MODApis/Api/v2/Quote/json?symbol=" + symbol;
   request({
      url: url,
      json: true
   },function (err, response, body){
      if(!err && response.statusCode === 200){
         callback(body.LastPrice);
      }
   });
}

/* handle get request for login attempt */
router.get('/login', function(req, res, next) {
   var username = req.query.username;
   var password = req.query.password;
   console.log("User " + username + " tried logging in with password: " + password);

   var failure = function(){
      return sendJson({result: 'fail', reason: 'user does not exist'}, res);
   };

   var success = function(){
      var f1 = function(){
         return sendJson({result: 'fail', reason: 'incorrect password'}, res);
      }

      var s1 = function(){
         return sendJson({result: 'success'}, res);
      }

      db.validatePassword(username, password, s1, f1);
   }

   db.checkIfUserExists(username, success, failure);
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

      db.createUser(username, password, '100000.00', s1, f1)
   }

   db.checkIfUserExists(username, success, failure);
});

/* handle get request for accessing account home */
router.get('/getuser', function(req, res, next) {
   var username = req.query.username;
   console.log("User " + username + " tried accessing account home");

   var failure = function(){
      return sendJson({result: 'fail', reason: 'user does not exist'}, res);
   };

   var success = function(){
      var successCallback = function(cash){
         var successCallback2 = function(owned_stocks){
            return sendJson({result: 'success', cash: cash, owned_stocks: owned_stocks}, res);
         }
         db.getUserStocks(username, successCallback2);
      }
      db.getUserCash(username, successCallback);
   }

   db.checkIfUserExists(username, success, failure);
});

/* handle get request for accessing friend home */
router.get('/getotheruser', function(req, res, next) {
   var username = req.query.username;
   var friendUsername = req.query.friendname;
   console.log("User " + username + " tried accessing " + friendUsername + "'s page");

   var failure = function(){
      return sendJson({result: 'fail', reason: 'user does not exist'}, res);
   };

   var success = function(){
      var f1 = function(){
         return sendJson({result: 'fail', reason: 'friend does not exist'}, res);
      }

      var s1 = function(){
         var successCallback = function(cash){
            var successCallback2 = function(owned_stocks){
               return sendJson({result: 'success', cash: cash, owned_stocks: owned_stocks}, res);
            }
            db.getUserStocks(friendUsername, successCallback2);
         }
         db.getUserCash(friendUsername, successCallback);
      }

      db.checkIfUserExists(friendUsername, s1, f1);
   }

   db.checkIfUserExists(username, success, failure);
});

/* handle get request for loading buy stock page */
router.get('/loadbuy', function(req, res, next) {
   var username = req.query.username;
   var symbol = req.query.symbol;
   console.log("User " + username + " wants to buy " + symbol);

   var failure = function(){
      return sendJson({result: 'fail', reason: 'user does not exist'}, res);
   };

   var success = function(){
      var priceCallback = function(price){
         if(price){
            return sendJson({result: 'success', symbol: symbol, price: price}, res);
         }else{
            return sendJson({result: 'fail', reason: 'no stock found from that symbol'}, res);  
         }
      }

      getStockPrice(symbol, priceCallback);
   };

   db.checkIfUserExists(username, success, failure);
});

/* handle get request for buying stock */
router.get('/buystock', function(req, res, next) {
   var username = req.query.username;
   var symbol = req.query.symbol;
   var quantity = req.query.quantity;
   console.log("User " + username + " tried buying " + quantity + " shares of " + symbol);

   var failure = function(){
      return sendJson({result: 'fail', reason: 'user does not exist'}, res);
   };

   var success = function(){
      var successCallback = function(cash){
         var priceCallback = function(price){
            if(price){
               var total_cost = price * quantity;
               if(total_cost > cash){
                  return sendJson({result: 'fail', reason: 'you do not have enough funds'}, res);
               }else{
                  var portfolioIdCallback = function(portfolio_id){
                     var insertedCallback = function(newCashAmount){ return sendJson({result: 'success', cash: newCashAmount}, res); }
                     db.buyStock(username, portfolio_id, cash, symbol, quantity, price, insertedCallback);
                  }
                  db.getUserPortfolioId(username, portfolioIdCallback);
               }
            }else{
               return sendJson({result: 'fail', reason: 'stock does not exist'}, res);
            }
         };
         getStockPrice(symbol, priceCallback);
      };
      db.getUserCash(username, successCallback);
   };

   db.checkIfUserExists(username, success, failure);
});

/* handle get request for loading sell stock page */
router.get('/loadsell', function(req, res, next) {
   var username = req.query.username;
   var id = req.query.ownedStockId;
   console.log("User " + username + " clicked sell on " + id);

   var failure = function(){
      return sendJson({result: 'fail', reason: 'user does not exist'}, res);
   };

   var success = function(){
      var validOwnedStockIdCallback = function(owned_stock){
         if(owned_stock){
            var os_symbol = owned_stock.symbol;
            var os_quantity = owned_stock.quantity;
            var os_start_price = owned_stock.start_price;
            
            var priceCallback = function(price){
               if(price){
                  return sendJson({result: 'success', symbol: os_symbol, quantity: os_quantity, id: id, curPrice: price, startPrice: os_start_price}, res);
               }else{
                  //means stock that has been bought no longer has price, very rare but possible i guess
                  return sendJson({result: 'fail', reason: 'internal error'}, res);
               }
            }

            getStockPrice(os_symbol, priceCallback);
         }else{
            //means id sent from app doesn't match owned_stock id (AKA NEVER SHOULD GET HERE)
            return sendJson({result: 'fail', reason: 'internal error'}, res);
         }
      };

      db.getOwnedStockById(id, validOwnedStockIdCallback);
   };

   db.checkIfUserExists(username, success, failure);
});

/* handle get request for loading short stock page */
router.get('/loadshort', function(req, res, next) {
   var username = req.query.username;
   var symbol = req.query.symbol;
   console.log("User " + username + " wants to short " + symbol);

   var failure = function(){
      return sendJson({result: 'fail', reason: 'user does not exist'}, res);
   };

   var success = function(){
      var priceCallback = function(price){
         if(price){
            return sendJson({result: 'success', symbol: symbol, price: price}, res);
         }else{
            return sendJson({result: 'fail', reason: 'no stock found from that symbol'}, res);  
         }
      }

      getStockPrice(symbol, priceCallback);
   };

   db.checkIfUserExists(username, success, failure);
});

module.exports = router;
