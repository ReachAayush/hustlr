package edu.cmu.hustlr.Util;

import org.json.JSONException;
import org.json.JSONObject;

import edu.cmu.hustlr.Entities.Stock;

/**
 * Created by rueiminl on 2015/12/4.
 */
public class JsonToStock {
    public static Stock transfer(JSONObject obj) throws JSONException {
        Stock stock = new Stock();
        stock.setId(obj.getInt("id"));
        stock.setPortfolioId(obj.getInt("portfolio_id"));
        stock.setSymbol(obj.getString("symbol"));
        stock.setQuantity(obj.getInt("quantity"));
        stock.setStartPrice(obj.getDouble("start_price"));
        stock.setShorted(obj.getBoolean("is_short"));
        return stock;
    }
}