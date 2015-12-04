package edu.cmu.hustlr.Util;

import android.content.Context;

import edu.cmu.hustlr.Intent.LoadPriceIntent;

/**
 * Created by rueiminl on 2015/12/4.
 */
public class LoadShortTask extends LoadPriceTask {
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
