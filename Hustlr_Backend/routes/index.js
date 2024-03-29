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

   if(username == null || password == null){
      return sendJson({result: 'fail', reason: 'invalid request'}, res);  
   }

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
   var cash = req.query.cash;

   if(username == null || password == null || cash == null){
      return sendJson({result: 'fail', reason: 'invalid request'}, res);  
   }

   console.log("User " + username + " tried signing up with password: " + password);


   var success = function(){
      return sendJson({result: 'fail', reason: 'user already exists'}, res);
   };

   var failure = function(){
      var f1 = function(){
         return sendJson({result: 'fail', reason: 'internal error'}, res);
      }

      var s1 = function(){
         return sendJson({result: 'success'}, res);
      }

      db.createUser(username, password, cash, s1, f1)
   }

   db.checkIfUserExists(username, success, failure);
});

/* handle get request for accessing account home */
router.get('/getuser', function(req, res, next) {
   var username = req.query.username;

   if(username == null){
      return sendJson({result: 'fail', reason: 'invalid request'}, res);  
   }

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

   if(username == null || friendUsername == null){
      return sendJson({result: 'fail', reason: 'invalid request'}, res);  
   }

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

   if(username == null || symbol == null){
      return sendJson({result: 'fail', reason: 'invalid request'}, res);  
   }

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

   if(username == null || symbol == null || quantity == null){
      return sendJson({result: 'fail', reason: 'invalid request'}, res);  
   }

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

   if(username == null || id == null){
      return sendJson({result: 'fail', reason: 'invalid request'}, res);  
   }

   console.log("User " + username + " clicked cover on " + id);

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

   if(username == null || symbol == null){
      return sendJson({result: 'fail', reason: 'invalid request'}, res);  
   }

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

/* handle get request for selling stock */
router.get('/sellstock', function(req, res, next) {
   var username = req.query.username;
   var id = req.query.ownedStockId;
   var quantity = req.query.quantity;

   if(username == null || id == null || quantity == null){
      return sendJson({result: 'fail', reason: 'invalid request'}, res);  
   }

   console.log("User " + username + " tried to sell " + quantity + " shares of owned_stock_id:" + id);

   if(quantity<0){
      return sendJson({result: 'fail', reason: 'cannot sell negative amount'}, res);
   }else{

      var failure = function(){
         return sendJson({result: 'fail', reason: 'user does not exist'}, res);
      };

      var success = function(){
         var cashCallback = function(cash){
            var validOwnedStockIdCallback = function(owned_stock){
               if(owned_stock){
                  var os_symbol = owned_stock.symbol;
                  var os_quantity = owned_stock.quantity;
                  var os_start_price = owned_stock.start_price;

                  if(quantity <= os_quantity){
                     var portfolioIdCallback = function(portfolio_id){
                        var priceCallback = function(price){
                           if(price){
                              var createdTransactionCallback = function(newCashAmount){
                                 return sendJson({result: 'success', cash: newCashAmount}, res);
                              }
                              db.sellStock(username, portfolio_id, id, os_quantity, quantity, price, cash, createdTransactionCallback);
                           }else{
                           //means stock that has been bought no longer has price, very rare but possible i guess
                           return sendJson({result: 'fail', reason: 'internal error'}, res);
                        }
                     }
                     getStockPrice(os_symbol, priceCallback);
                  }

                  db.getUserPortfolioId(username, portfolioIdCallback);

               }else{
                  return sendJson({result: 'fail', reason: 'tried selling more shares than you have'}, res);
               }
            }else{
               //means id sent from app doesn't match owned_stock id (AKA NEVER SHOULD GET HERE)
               return sendJson({result: 'fail', reason: 'internal error'}, res);
            }
         };

         db.getOwnedStockById(id, validOwnedStockIdCallback);
      }

      db.getUserCash(username, cashCallback);
   }

   db.checkIfUserExists(username, success, failure);
}
});

/* handle get request for loading cover stock page */
router.get('/loadcover', function(req, res, next) {
   var username = req.query.username;
   var id = req.query.ownedStockId;

   if(username == null || id == null){
      return sendJson({result: 'fail', reason: 'invalid request'}, res);  
   }

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

/* handle get request for shorting stock */
router.get('/shortstock', function(req, res, next) {
   var username = req.query.username;
   var symbol = req.query.symbol;
   var quantity = req.query.quantity;

   if(username == null || symbol == null || quantity == null){
      return sendJson({result: 'fail', reason: 'invalid request'}, res);  
   }

   console.log("User " + username + " tried shorting " + quantity + " shares of " + symbol);

   var failure = function(){
      return sendJson({result: 'fail', reason: 'user does not exist'}, res);
   };

   var success = function(){
      var successCallback = function(cash){
         var priceCallback = function(price){
            if(price){
                  var portfolioIdCallback = function(portfolio_id){
                     var insertedCallback = function(newCashAmount){ return sendJson({result: 'success', cash: newCashAmount}, res); }
                     db.shortStock(username, portfolio_id, cash, symbol, quantity, price, insertedCallback);
                  }
                  db.getUserPortfolioId(username, portfolioIdCallback);
            }else{
               //should never get here
               return sendJson({result: 'fail', reason: 'stock does not exist'}, res);
            }
         };
         getStockPrice(symbol, priceCallback);
      };
      db.getUserCash(username, successCallback);
   };

   db.checkIfUserExists(username, success, failure);
});

/* handle get request for selling stock */
router.get('/coverstock', function(req, res, next) {
   var username = req.query.username;
   var id = req.query.ownedStockId;
   var quantity = req.query.quantity;

   if(username == null || id == null || quantity == null){
      return sendJson({result: 'fail', reason: 'invalid request'}, res);  
   }

   console.log("User " + username + " tried to cover " + quantity + " shares of shorted owned_stock_id:" + id);

   if(quantity<0){
      return sendJson({result: 'fail', reason: 'cannot sell negative amount'}, res);
   }else{
      var failure = function(){
         return sendJson({result: 'fail', reason: 'user does not exist'}, res);
      };

      var success = function(){
         var cashCallback = function(cash){
            var validOwnedStockIdCallback = function(owned_stock){
               if(owned_stock){
                  var os_symbol = owned_stock.symbol;
                  var os_quantity = owned_stock.quantity;
                  var os_start_price = owned_stock.start_price;

                  if(quantity <= os_quantity){
                     var portfolioIdCallback = function(portfolio_id){
                        var priceCallback = function(price){
                           if(price){
                              var createdTransactionCallback = function(newCashAmount){
                                 return sendJson({result: 'success', cash: newCashAmount}, res);
                              }
                              db.coverStock(username, portfolio_id, id, os_quantity, quantity, price, cash, createdTransactionCallback);
                           }else{
                           //means stock that has been bought no longer has price, very rare but possible i guess
                           return sendJson({result: 'fail', reason: 'internal error'}, res);
                        }
                     }
                     getStockPrice(os_symbol, priceCallback);
                  }

                  db.getUserPortfolioId(username, portfolioIdCallback);

               }else{
                  return sendJson({result: 'fail', reason: 'tried covering more shares than you have shorted'}, res);
               }
            }else{
               //means id sent from app doesn't match owned_stock id (AKA NEVER SHOULD GET HERE)
               return sendJson({result: 'fail', reason: 'internal error'}, res);
            }
         };

         db.getOwnedStockById(id, validOwnedStockIdCallback);
      }

      db.getUserCash(username, cashCallback);
   }

   db.checkIfUserExists(username, success, failure);
}
});

module.exports = router;
