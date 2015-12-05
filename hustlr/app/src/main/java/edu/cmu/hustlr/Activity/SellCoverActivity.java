package edu.cmu.hustlr.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.cmu.hustlr.R;
import edu.cmu.hustlr.Util.AccountHomeTask;
import edu.cmu.hustlr.Util.CoverStockTask;
import edu.cmu.hustlr.Util.SellStockTask;

// come from sell/cover intent
public class SellCoverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_cover);

        // ref: LoadSellCoverIntent.constructor
        Bundle bundle = getIntent().getExtras();
        final int id = bundle.getInt("id");
        String symbol = bundle.getString("symbol");
        double startPrice = bundle.getDouble("startPrice");
        double currentPrice = bundle.getDouble("currentPrice");
        final int quantity = bundle.getInt("quantity");
        final boolean isShort = bundle.getBoolean("isShort");

        // ref: activity_sell_cover.xml R.id.typeSellCoverWidgetName
        TextView textSymbol = (TextView)findViewById(R.id.textSellCoverSymbol);
        textSymbol.setText(symbol);
        TextView textStartPrice = (TextView)findViewById(R.id.textSellCoverStartPrice);
        textStartPrice.setText(String.valueOf(startPrice));
        TextView textCurrentPrice = (TextView)findViewById(R.id.textSellCoverCurrentPrice);
        textCurrentPrice.setText(String.valueOf(currentPrice));
        TextView textQuantity = (TextView)findViewById(R.id.textSellCoverQuantity);
        textQuantity.setText(String.valueOf(quantity));

        // send sell/cover http request to backend
        // if success => goto home account page
        Button buttonSubmit = (Button)findViewById(R.id.buttonSellCoverSubmit);
        if (isShort)
            buttonSubmit.setText("Cover");
        else
            buttonSubmit.setText("Sell");
        buttonSubmit.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        EditText editQuantity = (EditText)findViewById(R.id.editSellCoverQuantity);
                        int wantedQuantity =  Integer.parseInt(editQuantity.getText().toString());
                        if (isShort)
                            new CoverStockTask(getApplicationContext(), id, wantedQuantity).execute();
                        else
                            new SellStockTask(getApplicationContext(), id, wantedQuantity).execute();
                    }
                }
        );

        // goto account home page
        Button buttonCancel = (Button)findViewById(R.id.buttonSellCoverCancel);
        buttonCancel.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                       new AccountHomeTask(getApplicationContext()).execute();
                    }
                }
        );
    }
}
