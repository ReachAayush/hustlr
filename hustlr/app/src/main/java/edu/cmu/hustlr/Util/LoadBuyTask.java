package edu.cmu.hustlr.Util;

import android.content.Context;

/**
 * Created by rueiminl on 2015/12/4.
 * A task to get the response of the request "loadbuy". Would be redirected to the BuyShortActivity.
 */
public class LoadBuyTask extends LoadBuyShortTask {

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
