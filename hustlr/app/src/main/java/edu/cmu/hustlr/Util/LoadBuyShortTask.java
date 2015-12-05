package edu.cmu.hustlr.Util;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

import edu.cmu.hustlr.Entities.MyGlobal;
import edu.cmu.hustlr.Intent.LoadBuyShortIntent;

/**
 * Created by rueiminl on 2015/12/4.
 * A task to get the response of the request "loadbuy/short". Would be redirected to the BuyShortActivity.
 */
abstract public class LoadBuyShortTask extends HttpRequestTask {

    abstract protected String getWebPage();
    abstract protected String getType();
    String symbol;
    Context context;
    LoadBuyShortTask(Context context, String symbol) {
        this.context = context;
        this.symbol = symbol;
    }
    @Override
    protected String getUrl() {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("username", MyGlobal.me.getName());
        params.put("symbol", symbol);
        return getUrl(getWebPage(), params);
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        try {
            if (json.get("result").equals("fail")) {
                Toast.makeText(context, json.get("reason").toString(), Toast.LENGTH_LONG).show();
                return;
            }
            double price = json.getDouble("price");
            context.startActivity(new LoadBuyShortIntent(context, symbol, price, getType()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
