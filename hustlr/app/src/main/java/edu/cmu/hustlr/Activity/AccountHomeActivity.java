package edu.cmu.hustlr.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.cmu.hustlr.Entities.*;
import edu.cmu.hustlr.R;
import java.text.DecimalFormat;

public class AccountHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_home);
        FragmentManager manager = getSupportFragmentManager();
        User user = (User) getIntent().getParcelableExtra("USER");

        TextView textUsername = (TextView)findViewById(R.id.textHomeUsername);
        textUsername.setText(user.getName());
        TextView textUserCash = (TextView)findViewById(R.id.textHomeUserCash);
        textUserCash.setText(DecimalFormat.getCurrencyInstance().format(user.getCash()));

        Portfolio portfolio = user.getPortfolio();
        TextView textPortfolioCash = (TextView)findViewById(R.id.textHomePortfolioCash);
        textPortfolioCash.setText(DecimalFormat.getCurrencyInstance().format(user.getPortfolio().getCash()));

        // TODO use ListFragment; Here I just show the first stock temporarilily
        Stock stock = portfolio.getStock(0);

        TextView textStockSymbol = (TextView)findViewById(R.id.textStockSymbol);
        textStockSymbol.setText(stock.getSymbol());
        TextView textStockCurrentPrice = (TextView)findViewById(R.id.textStockCurrentPrice);
        textStockCurrentPrice.setText(DecimalFormat.getCurrencyInstance().format(stock.getCurrPrice()));
        TextView textStockPurchasePrice = (TextView)findViewById(R.id.textStockPurchasePrice);
        textStockPurchasePrice.setText(DecimalFormat.getCurrencyInstance().format(stock.getPurchasePrice()));
        TextView textStockShares = (TextView)findViewById(R.id.textStockShares);
        textStockShares.setText(String.valueOf(stock.getShares()));

        // TODO show transaction history somewhere

//        Button buttonSell1 = (Button)findViewById(R.id.buttonSell1);
//        buttonSell1.setOnClickListener(
//                new Button.OnClickListener() {
//                    public void onClick(View v) {
//                        Intent intent = new Intent(AccountHomeActivity.this, SellCoverActivity.class);
//                        startActivity(intent);
//                    }
//                }
//        );
//
//        Button buttonSell2 = (Button)findViewById(R.id.buttonSell2);
//        buttonSell2.setOnClickListener(
//                new Button.OnClickListener() {
//                    public void onClick(View v) {
//                        Intent intent = new Intent(AccountHomeActivity.this, SellCoverActivity.class);
//                        startActivity(intent);
//                    }
//                }
//        );
//
//        Button buttonEdit1 = (Button)findViewById(R.id.buttonEdit1);
//        buttonEdit1.setOnClickListener(
//                new Button.OnClickListener() {
//                    public void onClick(View v) {
//                        Intent intent = new Intent(AccountHomeActivity.this, BuyShortActivity.class);
//                        startActivity(intent);
//                    }
//                }
//        );
//
//        Button buttonEdit2 = (Button)findViewById(R.id.buttonEdit2);
//        buttonEdit2.setOnClickListener(
//                new Button.OnClickListener() {
//                    public void onClick(View v) {
//                        Intent intent = new Intent(AccountHomeActivity.this, BuyShortActivity.class);
//                        startActivity(intent);
//                    }
//                }
//        );

        Button buttonSearchStock = (Button)findViewById(R.id.btnHomeSearchStock);
        buttonSearchStock.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(AccountHomeActivity.this, BuyShortActivity.class);
                        intent.putExtra("searchedStockSymbol", getSearchedStock());
                        startActivity(intent);
                    }
                }
        );

        Button buttonSearchFriend = (Button)findViewById(R.id.btnHomeSearchFriend);
        buttonSearchFriend.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(AccountHomeActivity.this, FriendHomeActivity.class);
                        intent.putExtra("searchedStockSymbol", getSearchedFriend());
                        startActivity(intent);
                    }
                }
        );
    }

    public String getSearchedStock(){
        return ((EditText) findViewById(R.id.editSearchStock)).getText().toString();
    }

    public String getSearchedFriend(){
        return ((EditText) findViewById(R.id.editHomeSearchFriend)).getText().toString();
    }
}
