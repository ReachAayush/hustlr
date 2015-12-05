package edu.cmu.hustlr.Intent;

import android.content.Context;
import android.content.Intent;

import edu.cmu.hustlr.Activity.BuyShortActivity;


/**
 * A Intent to BuyShortActivity page.
 * from LoadBuy/ShortTask.
 */
public class LoadBuyShortIntent extends Intent {
    public LoadBuyShortIntent(Context context, String symbol, double price, String type) {
        // type: Buy or Short
        super(context, BuyShortActivity.class);
        this.putExtra("symbol", symbol);
        this.putExtra("price", price);
        this.putExtra("type", type);
        this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
}
