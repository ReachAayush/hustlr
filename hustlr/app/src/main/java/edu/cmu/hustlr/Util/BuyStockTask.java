package edu.cmu.hustlr.Util;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

import edu.cmu.hustlr.Entities.MyGlobal;
import edu.cmu.hustlr.Intent.AccountHomeIntent;
/**
 * Created by rueiminl on 2015/12/4.
 * A task to get the response of the request "buystock". Would be redirected to the AccountHomeActivity.
 */
public class BuyStockTask extends BuyShortStockTask {
    public BuyStockTask(Context context, String symbol, int quantity) {
        super(context, symbol, quantity);
    }

    @Override
    protected String getWebPage() {
        return "buystock";
    }
}
