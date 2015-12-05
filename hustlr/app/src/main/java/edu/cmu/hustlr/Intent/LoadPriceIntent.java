package edu.cmu.hustlr.Intent;

import android.content.Context;
import android.content.Intent;

import edu.cmu.hustlr.Activity.BuyShortActivity;

/**
 * Created by rueiminl on 2015/12/4.
 */

public class LoadPriceIntent extends Intent {
    public LoadPriceIntent(Context context, String symbol, double price, String type) {
        // type: Buy or Short
        super(context, BuyShortActivity.class);
        this.putExtra("symbol", symbol);
        this.putExtra("price", price);
        this.putExtra("type", type);
        this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
}
