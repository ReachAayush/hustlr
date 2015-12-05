package edu.cmu.hustlr.Util;

import android.content.Context;

/**
 * Created by rueiminl on 2015/12/4.
 * A task to get the response of the request "loadshort". Would be redirected to the BuyShortActivity.
 */
public class LoadShortTask extends LoadBuyShortTask {
    public LoadShortTask(Context context, String symbol) {
        super(context, symbol);
    }
    @Override
    protected String getWebPage() {
        return "loadshort";
    }

    @Override
    protected String getType() {
        return "short";
    }
}
