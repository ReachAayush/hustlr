package edu.cmu.hustlr.Intent;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import edu.cmu.hustlr.Activity.AccountHomeActivity;
import edu.cmu.hustlr.Activity.BuyShortActivity;
import edu.cmu.hustlr.Entities.MyGlobal;
import edu.cmu.hustlr.Entities.Stock;

/**
 * Created by rueiminl on 2015/11/13.
 */
public class SearchStockIntent extends Intent {
    private static final String STOCK_ID = "STOCK_ID";
    public SearchStockIntent(Context context, String symbol) {
        super(context, BuyShortActivity.class);
        // TODO get Stock based on the symbol by market API
        double mockCurrentPrice = 29.14;
        Stock mockStock = new Stock();
        mockStock.setSymbol(symbol);
        mockStock.setCurrentPrice(mockCurrentPrice);
        mockStock.setPurchasePrice(0);
        mockStock.setShares(0);
        /////
        Stock stock = mockStock;

        this.putExtra(STOCK_ID, (Parcelable)stock);
    }
}
