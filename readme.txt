This week we have mainly focused on the backend of our app. Due to us originally
mistakenly thinking we could use mongoDB we were set back a few days and had to 
redo much of our implementation. Then since passport doesn't play nicely with 
sqlite we just recently switched to mysql and have begun to make a little more 
progess. Currently user signup and login is almost complete and we are also starting
up on the business logic of it.


To run this (assuming you have node.js/mysql installed) run the following command
from the Hustlr_Backend folder:

npm install
npm start

In addition to this we have added to and expanded our exception handling.

Also, we have come up with a very detailed plan for our integration layer
as follows:

Login

First page.
Click Login:
request: POST/login?username=x&password=y
response:
{result:success} // goto home account page with authentication
{result:fail, reason: username not exists/password not matches} // Toast the error message

Click Signup:
goto Signup page.

Signup

Click Signup:
request: POST /signup?username=x&password=y
response:
{result:success} // goto home account page
{result:fail, reason: existed user} // Toast

Click Login:
goto Login page.

Home Account
request: POST /getuser?username=x  // with authentication; the request would be issued periodically in order to update the current price
response:
{result:success, user:username, access:readwrite, cash:123, stocks:[{symbol:CSCO, current:29.14, bought:30.11, quantity:2000}, {symbol:BA, current:147.18, bought:146.11, quantity:500}]} // render the home account page
{result:fail, reason: non-existed user} // should not happened in normal operation

Click any stock:
goto Sell page (use intent to pass the stock clicked by user)

Click Search (Friend):
request: POST /getuser?username=x
response:
{result:success, user:username, access:readonly, cash:123, stocks:[{symbol:CSCO, current:29.14, bought:30.11, quantity:2000}, {symbol:BA, current:147.18, bought:146.11, quantity:500}]} // render the friend home page
{result:fail, reason: non-existed user} // Toast

Click Search (Stock):
request: POST /getstock?symbol=x
response:
{result:success, symbol:CSCO, current:29.14} // render Buy page
{result:fail, reason: non-existed symbol} // Toast

Sell
Click Submit:
request: POST /sellstock?symbol=x&quantity=y
response:
{result:success, price:30.11} // goto home account page (re-get the user to refresh the portfolio)
{result:fail, reason: stock not found/insufficient quantity} // Toast

Click Cancel:
goto home account page (could skip the getuser step)
Buy
Click Buy:
request: POST /buystock?symbol=x&quantity=y
response:
{result:success, price:30.11} // goto home account page (re-get the user to refresh the portfolio)
{result:fail, reason: stock not found/insufficient cash} // Toast

Click Cancel:
goto home account page (could skip the getuser step)

Friend Home
Click Search (Friend):
request: POST /getuser?username=x
response:
{result:success, user:username, access:readonly, cash:123, stocks:[{symbol:CSCO, current:29.14, bought:30.11, quantity:2000}, {symbol:BA, current:147.18, bought:146.11, quantity:500}]} // render the friend home page
{result:fail, reason: non-existed user} // Toast
Click Back Home:
goto home account page (could skip the getuser step)



