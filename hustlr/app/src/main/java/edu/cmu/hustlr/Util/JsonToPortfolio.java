package edu.cmu.hustlr.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.cmu.hustlr.Entities.Portfolio;
import edu.cmu.hustlr.Entities.Stock;

/**
 * An utility function to transfer json object (from backend) to a Portfolio object
 */
public class JsonToPortfolio {
    public static Portfolio transfer(JSONObject obj) throws JSONException {
        Portfolio portfolio = new Portfolio();
        JSONArray stocks = obj.getJSONArray("owned_stocks");
        for (int i = 0; i < stocks.length(); i++) {
            JSONObject jsonStock = stocks.getJSONObject(i);
            Stock stock = JsonToStock.transfer(jsonStock);
            // only add those stocks with size > 0
            if (stock.getQuantity() > 0)
                portfolio.addStock(stock);
        }
        return portfolio;
    }
}
