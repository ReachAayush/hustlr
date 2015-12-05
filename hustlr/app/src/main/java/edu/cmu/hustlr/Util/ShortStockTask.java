package edu.cmu.hustlr.Util;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.ContentHandler;
import java.util.TreeMap;

import edu.cmu.hustlr.Entities.MyGlobal;
import edu.cmu.hustlr.Intent.HomeAccountIntent;

/**
 * Created by rueiminl on 2015/12/4.
 */
public class ShortStockTask extends HttpRequestTask {
    private Context context;
    private String symbol;
    private int quantity;
    public ShortStockTask(Context context, String symbol, int quantity) {
        this.context = context;
        this.symbol = symbol;
        this.quantity = quantity;
    }
    @Override
    protected String getUrl() {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("username", MyGlobal.me.getName());
        params.put("symbol", symbol);
        params.put("quantity", String.valueOf(quantity));
        return getUrl("shortstock", params);
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        try {
            if (json.get("result").equals("fail")) {
                Toast.makeText(context, json.get("reason").toString(), Toast.LENGTH_LONG).show();
                return;
            }
            // goto home account page
            context.startActivity(new HomeAccountIntent(context));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
