package edu.cmu.hustlr.Util;

import android.content.Context;

/**
 * Created by rueiminl on 2015/12/4.
 * A task to get the response of the request "loadcover". Would be redirected to the SellCoverActivity.
 */
public class LoadCoverTask extends LoadSellCoverTask {
    public LoadCoverTask(Context context, int ownedStockId) {
        super(context, ownedStockId);
    }

    @Override
    String getWebPage() {
        return "loadcover";
    }

    @Override
    boolean isShort() {
        return true;
    }
}
