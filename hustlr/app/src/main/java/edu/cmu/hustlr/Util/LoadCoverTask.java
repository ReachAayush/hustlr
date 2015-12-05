package edu.cmu.hustlr.Util;

import android.content.Context;

/**
 * Created by rueiminl on 2015/12/4.
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
