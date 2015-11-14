package edu.cmu.hustlr.Fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import edu.cmu.hustlr.Entities.*;

import java.text.DecimalFormat;
import java.util.*;
import edu.cmu.hustlr.R;

/**
 * Created by rueiminl on 2015/11/13.
 */
public class StockListFragment extends ListFragment {

    private static final String STOCKS_ID = "STOCKS_ID";
    public static StockListFragment getNewInstance(ArrayList<Stock> stocks) {
        StockListFragment fragment = new StockListFragment();
        Bundle args = new Bundle();
        args.putSerializable(STOCKS_ID, stocks);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Stock> stocks = (ArrayList<Stock>)getArguments().getSerializable(STOCKS_ID);
        StockAdapter adapter = new StockAdapter(stocks);
        setListAdapter(adapter);
    }

    private class StockAdapter extends ArrayAdapter<Stock> {
        public StockAdapter(ArrayList<Stock> stocks) {
            super(getActivity(), android.R.layout.simple_list_item_1, stocks);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // if we weren't given a view, inflate one
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.fragment_stock_list_item, null);
            }

            // configure the view for this stock
            Stock stock = getItem(position);
            TextView textStockSymbol = (TextView)convertView.findViewById(R.id.textStockSymbol);
            textStockSymbol.setText(stock.getSymbol());
            TextView textStockCurrentPrice = (TextView)convertView.findViewById(R.id.textStockCurrentPrice);
            textStockCurrentPrice.setText(DecimalFormat.getCurrencyInstance().format(stock.getCurrPrice()));
            TextView textStockPurchasePrice = (TextView)convertView.findViewById(R.id.textStockPurchasePrice);
            textStockPurchasePrice.setText(DecimalFormat.getCurrencyInstance().format(stock.getPurchasePrice()));
            TextView textStockShares = (TextView)convertView.findViewById(R.id.textStockShares);
            textStockShares.setText(String.valueOf(stock.getShares()));
            return convertView;
        }
    }
}
