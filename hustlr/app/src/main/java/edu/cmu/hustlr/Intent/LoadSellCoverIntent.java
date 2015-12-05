package edu.cmu.hustlr.Intent;

import android.content.Context;
import android.content.Intent;

import edu.cmu.hustlr.Activity.BuyShortActivity;
import edu.cmu.hustlr.Activity.SellCoverActivity;
import edu.cmu.hustlr.Entities.Stock;

/**
 * Created by rueiminl on 2015/12/4.
 */
// goto sell/cover activity
public class LoadSellCoverIntent extends Intent {
    public LoadSellCoverIntent(Context context, Stock stock) {
        super(context, SellCoverActivity.class);
        this.putExtra("id", stock.getId());
        this.putExtra("symbol", stock.getSymbol());
        this.putExtra("startPrice", stock.getStartPrice());
        this.putExtra("currentPrice", stock.getCurrentPrice());
        this.putExtra("quantity", stock.getQuantity());
        this.putExtra("isShort", stock.isShorted());
        this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
}
