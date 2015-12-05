package edu.cmu.hustlr.Util;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

import edu.cmu.hustlr.Entities.MyGlobal;
import edu.cmu.hustlr.Entities.Stock;
import edu.cmu.hustlr.Intent.LoadSellCoverIntent;

/**
 * Created by rueiminl on 2015/12/4.
 * A task to get the response of the request "loadsell/cover". Would be redirected to the SellCoverActivity.
 */

abstract public class LoadSellCoverTask extends HttpRequestTask {
    abstract String getWebPage();
    abstract boolean isShort();
    private Context context;
    private int ownedStockId = 0;
    public LoadSellCoverTask(Context context, int ownedStockId) {
        this.context = context;
        this.ownedStockId = ownedStockId;
    }
    @Override
    protected String getUrl() {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("username", MyGlobal.me.getName());
        params.put("ownedStockId", String.valueOf(ownedStockId));
        return getUrl(getWebPage(), params);
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        try {
            if (json.get("result").equals("fail")) {
                Toast.makeText(context, json.get("reason").toString(), Toast.LENGTH_LONG).show();
                return;
            }
            Stock stock = new Stock();
            stock.setId(json.getInt("id"));
            stock.setSymbol(json.getString("symbol"));
            stock.setQuantity(json.getInt("quantity"));
            stock.setCurrentPrice(json.getDouble("curPrice"));
            stock.setStartPrice(json.getDouble("startPrice"));
            stock.setShorted(isShort());
            context.startActivity(new LoadSellCoverIntent(context, stock));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
