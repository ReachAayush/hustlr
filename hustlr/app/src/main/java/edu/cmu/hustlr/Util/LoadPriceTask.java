package edu.cmu.hustlr.Util;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

import edu.cmu.hustlr.Entities.MyGlobal;
import edu.cmu.hustlr.Intent.LoadPriceIntent;

/**
 * Created by rueiminl on 2015/12/4.
 */
// if success => goto load price page automatically
abstract public class LoadPriceTask extends HttpRequestTask {

    abstract protected String getWebPage();
    abstract protected String getType();
    String symbol;
    Context context;
    LoadPriceTask(Context context, String symbol) {
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
            context.startActivity(new LoadPriceIntent(context, price, getType()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
