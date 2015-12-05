package edu.cmu.hustlr.Activity;

import android.content.Intent;
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

// fragments: search friend, summary, stock list
// R.id: typeFriendWidgetName
public class FriendHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_home);

        FragmentManager manager = getSupportFragmentManager();
        Fragment searchFriendFragment = SearchFriendFragment.newInstance();
        Fragment summaryFragment = SummaryFragment.newInstance(MyGlobal.friend.getName(), MyGlobal.friend.getCash());
        Fragment stockListFragment = StockListFragment.getNewInstance(MyGlobal.friend.getPortfolio().getAllStocks());
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
