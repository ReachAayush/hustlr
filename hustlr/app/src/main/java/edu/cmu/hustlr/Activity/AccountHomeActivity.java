package edu.cmu.hustlr.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.cmu.hustlr.Entities.*;
import edu.cmu.hustlr.Fragment.*;
import edu.cmu.hustlr.R;

/**
 * The main home page to summary the user's portfolio.
 * Views:
 *   Fragments: SearchFriendFragment, SummaryFragment, StockListFragment, SearchStockFragment
 * xml: activity_account_home.xml
 * Page flow:
 *   from LoginActivity (click Signin => LoginTask => AccountHomeTask => AccountHomeIntent => this)
 *   from SignupActivity (click Signup => SignupTask => AccountHomeTask => AccountHomeIntent => this)
 *   from SellCoverActivity/BuyShortActivity (click Cancel => AccountHomeTask => AccountHomeIntent => this)
 *   from SellCoverActivity (click Sell/Cover => Sell/CoverStockTask => AccountHomeIntent => this)
 *   from BuyShortActivity (click Buy/Short => Buy/ShortStockTask => AccountHomeIntent => this)
 *   click Search in SearchFriendFragment => send a "getotheruser" request (VisitFriendTask), and would be redirected to FriendHomeActivity.
 *   click an item in StockListFragment => send a "loadsell/cover" request (LoadSell/CoverTask), and would be redirected to SellCoverActivity.
 *   click Buy in SearchStockFragment => send a "loadbuy" request (LoadBuyTask), and would be redirected to BuyShortActivity.
 *   click Short in SearchStockFragment => send a "loadshort" request (LoadShortTask), and would be redirected to BuyShortActivity.
 */
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
