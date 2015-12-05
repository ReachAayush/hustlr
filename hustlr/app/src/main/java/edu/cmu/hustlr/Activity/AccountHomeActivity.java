package edu.cmu.hustlr.Activity;

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
import edu.cmu.hustlr.R;
import edu.cmu.hustlr.Util.LoadBuyTask;
import edu.cmu.hustlr.Util.LoadShortTask;

import java.text.DecimalFormat;

// fragments: summary of portfolio, list of portfolio, search friend, search stock (to buy or to short)
// relative xml: activity_account_home.xml
public class AccountHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_home);

        FragmentManager manager = getSupportFragmentManager();
        Fragment searchFriendFragment = SearchFriendFragment.newInstance();
        Fragment summaryFragment = SummaryFragment.newInstance(MyGlobal.me.getName(), MyGlobal.me.getCash());
        boolean readOnly = false;
        Fragment stockListFragment = StockListFragment.getNewInstance(MyGlobal.me.getPortfolio().getAllStocks(), readOnly);
        manager.beginTransaction()
                .add(R.id.fragmentSearchFriend, searchFriendFragment)
                .add(R.id.fragmentSummary, summaryFragment)
                .add(R.id.fragmentStockList, stockListFragment)
                .commit();

        // page flow
        // TODO add SearchStockFragment
        // goto buy page
        Button btnBuyStock = (Button)findViewById(R.id.btnHomeBuyStock);
        btnBuyStock.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        EditText editSearchStockName = (EditText)findViewById(R.id.editHomeSearchStock);
                        new LoadBuyTask(getApplicationContext(), editSearchStockName.getText().toString()).execute();
                    }
                }
        );
        // goto short page
        Button btnShortStock = (Button)findViewById(R.id.btnHomeShortStock);
        btnShortStock.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        EditText editSearchStockName = (EditText)findViewById(R.id.editHomeSearchStock);
                        new LoadShortTask(getApplicationContext(), editSearchStockName.getText().toString()).execute();
                    }
                }
        );
    }
}
