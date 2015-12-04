package edu.cmu.hustlr.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.cmu.hustlr.Entities.Portfolio;
import edu.cmu.hustlr.Entities.Stock;

/**
 * Created by rueiminl on 2015/12/4.
 */
public class JsonToPortfolio {
    public static Portfolio transfer(JSONObject obj) throws JSONException {
        Portfolio portfolio = new Portfolio();
        JSONArray stocks = obj.getJSONArray("owned_stocks");
        for (int i = 0; i < stocks.length(); i++) {
            JSONObject jsonStock = stocks.getJSONObject(i);
            Stock stock = JsonToStock.transfer(jsonStock);
            portfolio.addStock(stock);
        }
        return portfolio;
    }
}
