package edu.cmu.hustlr.Util;

import android.content.Context;

/**
 * Created by rueiminl on 2015/12/4.
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
