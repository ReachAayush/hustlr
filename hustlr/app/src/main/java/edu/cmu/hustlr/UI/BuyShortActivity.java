package edu.cmu.hustlr.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import edu.cmu.hustlr.R;

public class BuyShortActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_short);

        Button buttonBuy = (Button)findViewById(R.id.buttonBuy);
        buttonBuy.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(BuyShortActivity.this, AccountHomeActivity.class);
                        startActivity(intent);
                    }
                }
        );

        Button buttonCancel = (Button)findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(BuyShortActivity.this, AccountHomeActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
