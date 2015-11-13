package edu.cmu.hustlr.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import edu.cmu.hustlr.R;

public class FriendHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_home);

        Button buttonBackHome = (Button)findViewById(R.id.buttonBackHome);
        buttonBackHome.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        Intent intent = new Intent(FriendHomeActivity.this, AccountHomeActivity.class);
                        startActivity(intent);
                    }
                }
        );

        Button buttonSearch = (Button)findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        Intent intent = new Intent(FriendHomeActivity.this, FriendHomeActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }

}
