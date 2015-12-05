package edu.cmu.hustlr.Util;

import android.content.Context;

/**
 * Created by rueiminl on 2015/12/4.
 */
public class LoadSellTask extends LoadSellCoverTask {
    public LoadSellTask(Context context, int ownedStockId) {
        super(context, ownedStockId);
    }

    @Override
    String getWebPage() {
        return "loadsell";
    }

    @Override
    boolean isShort() {
        return false;
    }
}
