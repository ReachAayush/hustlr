var mysql = require('mysql');

var connection = mysql.createConnection({
	host     : 'localhost',
	user     : 'hustlr',
	password : 'hustle',
	database : 'hustlr_db'
});

var checkIfUserExists = function(username, success, failure) {
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

var validatePassword = function(username, password, success, failure){
	var queryString = 'SELECT password from users WHERE username=\'' + username + '\'';
	connection.query(queryString, function(err, rows, fields) {
		if (err) throw err;

		if (rows[0].password == password){
			return success();
		}else{
			return failure();
		}
	});
}

var createUser = function(username, password, cash, success, failure){
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

var getUserPortfolioId = function(username, callback){
	var queryString = 'SELECT portfolio_id from users WHERE username=\'' + username + '\'';
	connection.query(queryString, function(err, rows, fields) {
		if (err) throw err;

		return callback(rows[0].portfolio_id);
	});
}

var getUserCash = function(username, callback){
	var getPortfolioIdCallback = function(portfolio_id){
		var queryString = 'SELECT cash from portfolio WHERE id=\'' + portfolio_id + '\'';
		connection.query(queryString, function(err, rows, fields) {
			if (err) throw err;

			return callback(rows[0].cash);
		});
	};

	return getUserPortfolioId(username, getPortfolioIdCallback);
}

var getUserStocks = function(username, callback){
	var getPortfolioIdCallback = function(portfolio_id){
		var queryString = 'SELECT * from owned_stock WHERE portfolio_id=\'' + portfolio_id + '\'';
		connection.query(queryString, function(err, rows, fields) {
			if (err) throw err;

			return callback(rows);
		});
	};
	return getUserPortfolioId(username, getPortfolioIdCallback);
}

var getOwnedStockById = function(owned_stock_id, callback){
	var queryString = 'SELECT * from owned_stock WHERE id=' + owned_stock_id + ';';
	connection.query(queryString, function(err, rows, fields) {
		if (err) throw err;

		return callback(rows[0]);
	});
}

var buyStock = function(username, portfolio_id, cash, symbol, quantity, price, callback){
	var queryString = 'INSERT INTO owned_stock (id, portfolio_id, symbol, quantity, start_price, is_short) VALUES (NULL, ' + portfolio_id + ', \'' + symbol + '\', ' + quantity + ', ' + price + ', 0);';
	connection.query(queryString, function(err, rows, fields) {
		if (err) console.log(err);
		updateCash(portfolio_id, cash - (price * quantity), callback);
	});
}

var sellStock = function(username, portfolio_id, owned_stock_id, start_quant, sell_quant, curPrice, curCash, callback){
	var newCashAmount = curCash + curPrice * sell_quant;
	var newOwnedQuantity = start_quant - sell_quant;
	var queryString = 'INSERT INTO transaction (id, owned_stock_id, portfolio_id, final_price) VALUES (NULL, ' + owned_stock_id + ', ' + portfolio_id + ', ' + curPrice + ');';
	connection.query(queryString, function(err, rows, fields) {
		if (err) console.log(err);
		updateOwnedStockAmount(owned_stock_id, newOwnedQuantity);
		updateCash(portfolio_id, newCashAmount, callback);
	});
}

var updateCash = function(portfolio_id, newCashAmount, callback){
	var queryString = 'UPDATE portfolio SET cash = ' + newCashAmount + ' WHERE id=\'' + portfolio_id + '\'';
	connection.query(queryString, function(err, rows, fields) {
		if (err) console.log(err);
		callback(newCashAmount);
	});
}

var updateOwnedStockAmount = function(owned_stock_id, newOwnedQuantity){
	var queryString = 'UPDATE owned_stock SET quantity = ' + newOwnedQuantity + ' WHERE id=\'' + owned_stock_id + '\'';
	connection.query(queryString, function(err, rows, fields) {
		if (err) console.log(err);
	});
}

module.exports = {
	checkIfUserExists: checkIfUserExists,
	validatePassword: validatePassword,
	createUser: createUser,
	getUserPortfolioId: getUserPortfolioId,
	getUserCash: getUserCash,
	getUserStocks: getUserStocks,
	buyStock: buyStock,
	getOwnedStockById: getOwnedStockById,
	sellStock: sellStock
}