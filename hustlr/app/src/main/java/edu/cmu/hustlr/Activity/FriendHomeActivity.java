package edu.cmu.hustlr.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import edu.cmu.hustlr.Entities.MyGlobal;
import edu.cmu.hustlr.Fragment.SearchFriendFragment;
import edu.cmu.hustlr.Fragment.StockListFragment;
import edu.cmu.hustlr.Fragment.SummaryFragment;
import edu.cmu.hustlr.R;
import edu.cmu.hustlr.Util.AccountHomeTask;

/*
 * A page to show other users' portfolio.
 * Views:
 *   Fragments: SearchFriendFragment, SummaryFragment, StockListFragment(readOnly = true)
 *   Button: BackHome
 * xml: activity_friend_home.xml with R.id = typeFriendWidgetName (eg. buttonFriendBackHome)
 * Page flow:
 *   from AccountHomeActivity (SearchFriendFragment => click Search => VisitFriendTask => FriendHomeIntent => this)
 *   from FriendHomeAcitivity (SearchFriendFragment => click Search => VisitFriendTask => FriendHomeIntent => this)
 *   click Search in SearchFriendFragment => send a "getotheruser" request (VisitFriendTask), and would be redirected to FriendHomeActivity.
 *   click BackHome => AccountHomeTask
 */
public class FriendHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_home);

        FragmentManager manager = getSupportFragmentManager();
        Fragment searchFriendFragment = SearchFriendFragment.newInstance();
        Fragment summaryFragment = SummaryFragment.newInstance(MyGlobal.friend.getName(), MyGlobal.friend.getCash());
        boolean readOnly = true;
        Fragment stockListFragment = StockListFragment.getNewInstance(MyGlobal.friend.getPortfolio().getAllStocks(), readOnly);
        manager.beginTransaction()
                .add(R.id.fragmentSearchFriend, searchFriendFragment)
                .add(R.id.fragmentSummary, summaryFragment)
                .add(R.id.fragmentStockList, stockListFragment)
                .commit();

        Button buttonBackHome = (Button)findViewById(R.id.bottonFriendBackHome);
        buttonBackHome.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                    new AccountHomeTask(getApplicationContext()).execute();
                    }
                }
        );
    }

}
