package edu.cmu.hustlr.Util;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

import edu.cmu.hustlr.Entities.MyGlobal;

/**
 * Created by rueiminl on 2015/12/4.
 * A task to get the response of the request "sell/coverstock". Would be redirected to the SellCoverActivity.
 */
abstract public class SellCoverStockTask extends HttpRequestTask {
    abstract protected String getWebPage();
    private Context context;
    private int ownedStockId;
    private int quantity;

    public SellCoverStockTask(Context context, int ownedStockId, int quantity) {
        this.context = context;
        this.ownedStockId = ownedStockId;
        this.quantity = quantity;
    }

    @Override
    protected String getUrl() {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("username", MyGlobal.me.getName());
        params.put("ownedStockId", String.valueOf(ownedStockId));
        params.put("quantity", String.valueOf(quantity));
        return getUrl(getWebPage(), params);
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        try {
            if (json.getString("result").equals("fail")) {
                Toast.makeText(context, json.get("reason").toString(), Toast.LENGTH_LONG).show();
                return;
            }
            new AccountHomeTask(context).execute();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
