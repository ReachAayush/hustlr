var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var mysql      = require('mysql');

var routes = require('./routes/index');
var users = require('./routes/users');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

// uncomment after placing your favicon in /public
//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

//DATABASE LOGIC
var connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'hustlr',
  password : 'hustle',
  database : 'hustlr_db'
});

var createUserTable = 'CREATE TABLE IF NOT EXISTS users (id MEDIUMINT NOT NULL AUTO_INCREMENT, username CHAR(50) NOT NULL, password CHAR(50) NOT NULL, portfolio_id MEDIUMINT, PRIMARY KEY (id));'

var createPortfolioTable = 'CREATE TABLE IF NOT EXISTS portfolio (id MEDIUMINT NOT NULL AUTO_INCREMENT, cash DECIMAL(10,2) NOT NULL, PRIMARY KEY (id));'

var createOwnedStocksTable = 'CREATE TABLE IF NOT EXISTS owned_stock (id MEDIUMINT NOT NULL AUTO_INCREMENT, portfolio_id MEDIUMINT, symbol CHAR(5) NOT NULL, quantity MEDIUMINT, start_price DECIMAL(5,2), is_short TINYINT(1), PRIMARY KEY (id));'

var createTransactionTable = 'CREATE TABLE IF NOT EXISTS transaction (id MEDIUMINT NOT NULL AUTO_INCREMENT, owned_stock_id MEDIUMINT, portfolio_id MEDIUMINT, final_price DECIMAL(5,2), PRIMARY KEY (id));'

connection.connect();

connection.query(createUserTable, function(err, rows, fields) {
  if (err) throw err;
  console.log("CREATED USER TABLE!");
});

connection.query(createPortfolioTable, function(err, rows, fields) {
  if (err) throw err;
  console.log("CREATED PORTFOLIO TABLE!");
});

connection.query(createOwnedStocksTable, function(err, rows, fields) {
  if (err) throw err;
  console.log("CREATED OWNED_STOCK TABLE!");
});

connection.query(createTransactionTable, function(err, rows, fields) {
  if (err) throw err;
  console.log("CREATED TRANSACTION TABLE!");
});

connection.end();

app.use('/', routes);
app.use('/users', users);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});

// error handlers

// development error handler
// will print stacktrace
if (app.get('env') === 'development') {
  app.use(function(err, req, res, next) {
    res.status(err.status || 500);
    res.render('error', {
      message: err.message,
      error: err
    });
  });
}

// production error handler
// no stacktraces leaked to user
app.use(function(err, req, res, next) {
  res.status(err.status || 500);
  res.render('error', {
    message: err.message,
    error: {}
  });
});


module.exports = app;
