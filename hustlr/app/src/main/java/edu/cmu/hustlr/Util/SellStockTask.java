package edu.cmu.hustlr.Util;

import android.content.Context;

/**
 * Created by rueiminl on 2015/12/4.
 * A task to get the response of the request "sellstock". Would be redirected to the SellCoverActivity.
 */
public class SellStockTask extends SellCoverStockTask {
    public SellStockTask(Context context, int ownedStockId, int quantity) {
        super(context, ownedStockId, quantity);
    }

    @Override
    protected String getWebPage() {
        return "sellstock";
    }
}
