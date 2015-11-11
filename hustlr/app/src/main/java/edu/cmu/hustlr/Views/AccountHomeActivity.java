package edu.cmu.hustlr.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AccountHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_home);

        Button buttonSell1 = (Button)findViewById(R.id.buttonSell1);
        buttonSell1.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(AccountHomeActivity.this, SellCoverActivity.class);
                        startActivity(intent);
                    }
                }
        );

        Button buttonSell2 = (Button)findViewById(R.id.buttonSell2);
        buttonSell2.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(AccountHomeActivity.this, SellCoverActivity.class);
                        startActivity(intent);
                    }
                }
        );

        Button buttonEdit1 = (Button)findViewById(R.id.buttonEdit1);
        buttonEdit1.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(AccountHomeActivity.this, BuyShortActivity.class);
                        startActivity(intent);
                    }
                }
        );

        Button buttonEdit2 = (Button)findViewById(R.id.buttonEdit2);
        buttonEdit2.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(AccountHomeActivity.this, BuyShortActivity.class);
                        startActivity(intent);
                    }
                }
        );

        Button buttonSearchStock = (Button)findViewById(R.id.buttonSearchStock);
        buttonSearchStock.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(AccountHomeActivity.this, BuyShortActivity.class);
                        intent.putExtra("searchedStockSymbol", getSearchedStock());
                        startActivity(intent);
                    }
                }
        );

        Button buttonSearchFriend = (Button)findViewById(R.id.buttonSearch);
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
        return ((EditText) findViewById(R.id.editSearchAFriend)).getText().toString();
    }
}
