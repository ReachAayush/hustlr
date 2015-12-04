package edu.cmu.hustlr.Util;

import android.content.Context;

/**
 * Created by rueiminl on 2015/12/4.
 */
public class LoadBuyTask extends LoadPriceTask {

    public LoadBuyTask(Context context, String symbol) {
        super(context, symbol);
    }

    @Override
    protected String getWebPage() {
        return "loadbuy";
    }

    @Override
    protected String getType() {
        return "buy";
    }
}
