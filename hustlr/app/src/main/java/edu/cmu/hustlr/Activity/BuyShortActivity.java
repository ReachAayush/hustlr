package edu.cmu.hustlr.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.cmu.hustlr.R;
import edu.cmu.hustlr.Util.BuyStockTask;
import edu.cmu.hustlr.Util.AccountHomeTask;
import edu.cmu.hustlr.Util.ShortStockTask;

/**
 * A page for user to buy/short a stock with specific quantity.
 * Views:
 *   Text: symbol, price
 *   Edit: quantity
 *   Button: Buy/Short, Cancel
 * xml: activity_buy_short.xml with R.id = typeBuyShortWidgetName
 * Page flow:
 *   from AccountHomeActivity (SearchStockFragment => click Buy => LoadBuyTask => LoadBuyShortIntent => this)
 *   click Buy/Short => send a "buy/shortstock" request to the backend (Buy/ShortStockTask), and would be redirected to AccountHomeActivity
 *   click Cancel => goto AccountHomeActivity
 */
public class BuyShortActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_short);

        Bundle extra = getIntent().getExtras();
        final String symbol = extra.getString("symbol");
        final String type = extra.getString("type");
        double price = extra.getDouble("price");

        TextView editPrice = (TextView)findViewById(R.id.textBuyShortCurrentPrice);
        editPrice.setText(String.valueOf(price));

        TextView editSymbol = (TextView)findViewById(R.id.textBuyShortSymbol);
        editSymbol.setText(symbol);

        // request for the transcation
        // if success => goto home account page
        Button buttonType = (Button)findViewById(R.id.buttonBuyShortSubmit);
        buttonType.setText(type);
        buttonType.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        EditText editQuantity = (EditText)findViewById(R.id.editBuyShortQuantity);
                        int quantity = Integer.valueOf(editQuantity.getText().toString());
                        if (type.equals("buy"))
                            new BuyStockTask(getApplicationContext(), symbol, quantity).execute();
                        else // type.equals("short")
                            new ShortStockTask(getApplicationContext(), symbol, quantity).execute();
                    }
                }
        );

        // goto home account page
        Button buttonCancel = (Button)findViewById(R.id.buttonBuyShortCancel);
        buttonCancel.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        new AccountHomeTask(getApplicationContext()).execute();
                    }
                }
        );
    }
}
