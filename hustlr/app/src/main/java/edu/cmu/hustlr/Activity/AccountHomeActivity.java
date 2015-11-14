package edu.cmu.hustlr.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.cmu.hustlr.Entities.*;
import edu.cmu.hustlr.Fragment.*;
import edu.cmu.hustlr.Intent.*;
import edu.cmu.hustlr.R;
import java.text.DecimalFormat;

public class AccountHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_home);

        FragmentManager manager = getSupportFragmentManager();
        Fragment searchFriendFragment = SearchFriendFragment.newInstance();
        Fragment stockListFragment = StockListFragment.getNewInstance(MyGlobal.me.getPortfolio().getAllStocks());
        manager.beginTransaction()
                .add(R.id.fragmentHomeSearchFriend, searchFriendFragment)
                .add(R.id.fragmentHomeStockList, stockListFragment)
                .commit();

        // TODO add UserFragment
        TextView textUsername = (TextView)findViewById(R.id.textHomeUsername);
        textUsername.setText(MyGlobal.me.getName());
        TextView textUserCash = (TextView)findViewById(R.id.textHomeUserCash);
        textUserCash.setText(DecimalFormat.getCurrencyInstance().format(MyGlobal.me.getCash()));

        Portfolio portfolio = MyGlobal.me.getPortfolio();
        TextView textPortfolioCash = (TextView)findViewById(R.id.textHomePortfolioCash);
        textPortfolioCash.setText(DecimalFormat.getCurrencyInstance().format(MyGlobal.me.getPortfolio().getCash()));


        // TODO show transaction history somewhere

        // page flow
        // TODO add SearchStockFragment
        Button buttonSearchStock = (Button)findViewById(R.id.btnHomeSearchStock);
        buttonSearchStock.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        EditText editSearchStockName = (EditText)findViewById(R.id.editHomeSearchStock);
                        Intent intent = new SearchStockIntent(AccountHomeActivity.this.getApplicationContext(), editSearchStockName.getText().toString());
                        startActivityForResult(intent, 0);
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
