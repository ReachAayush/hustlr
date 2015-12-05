package edu.cmu.hustlr.Fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import edu.cmu.hustlr.Entities.*;

import java.text.DecimalFormat;
import java.util.*;

import edu.cmu.hustlr.R;
import edu.cmu.hustlr.Util.LoadCoverTask;
import edu.cmu.hustlr.Util.LoadSellCoverTask;
import edu.cmu.hustlr.Util.LoadSellTask;

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

    // send http request of sell/cover stock (for price)
    // if success => goto sell or cover page
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // get the Stock from the adapter
        Stock stock = ((StockAdapter)getListAdapter()).getItem(position);
        if (stock.isShorted()) {
            new LoadCoverTask(getActivity().getApplicationContext(), stock.getId()).execute();
        } else {
            new LoadSellTask(getActivity().getApplicationContext(), stock.getId()).execute();
        }
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
            TextView textStockSymbol = (TextView)convertView.findViewById(R.id.textStockListSymbol);
            textStockSymbol.setText(stock.getSymbol());
            TextView textStockStartPrice = (TextView)convertView.findViewById(R.id.textStockListStartPrice);
            textStockStartPrice.setText(DecimalFormat.getCurrencyInstance().format(stock.getStartPrice()));
            TextView textQuantity = (TextView)convertView.findViewById(R.id.textStockListQuantity);
            textQuantity.setText(String.valueOf(stock.getQuantity()));
//            Button buttonSubmit = (Button)convertView.findViewById(R.id.buttonStockListSellCover);
//            buttonSubmit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
            return convertView;
        }
    }
}
