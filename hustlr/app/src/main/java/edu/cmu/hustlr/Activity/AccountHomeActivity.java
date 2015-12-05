package edu.cmu.hustlr.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.cmu.hustlr.Entities.*;
import edu.cmu.hustlr.Fragment.*;
import edu.cmu.hustlr.R;
import edu.cmu.hustlr.Util.LoadBuyTask;
import edu.cmu.hustlr.Util.LoadShortTask;

// fragments: summary of portfolio (SummaryFragment), list of stocks (StockListFragment), search friend (SearchFriendFragment), search stock (SearchStockFragment)
// relative xml: activity_account_home.xml
// page flow:
//   in SearchFriendFragment:
//     click search => goto other's home page (VisitFriendTask)
//   in StockListFragment:
//     click stock => goto sell/cover page (LoadSellTask or LoadCoverTask depending on isShort)
//   in SearchStockFragment:
//     click buy => goto buy stock page (LoadBuyTask)
//     click short => goto short stock page (LoadShortTask)
public class AccountHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_home);

        FragmentManager manager = getSupportFragmentManager();
        Fragment searchFriendFragment = SearchFriendFragment.newInstance();
        Fragment summaryFragment = SummaryFragment.newInstance(MyGlobal.me.getName(), MyGlobal.me.getCash());
        Fragment searchStockFragment = SearchStockFragment.newInstance();
        boolean readOnly = false;
        Fragment stockListFragment = StockListFragment.getNewInstance(MyGlobal.me.getPortfolio().getAllStocks(), readOnly);
        manager.beginTransaction()
                .add(R.id.fragmentSearchFriend, searchFriendFragment)
                .add(R.id.fragmentSummary, summaryFragment)
                .add(R.id.fragmentStockList, stockListFragment)
                .add(R.id.fragmentSearchStock, searchStockFragment)
                .commit();
    }
}
