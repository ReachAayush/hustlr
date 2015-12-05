package edu.cmu.hustlr.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import edu.cmu.hustlr.R;
import edu.cmu.hustlr.Util.LoadBuyTask;
import edu.cmu.hustlr.Util.LoadShortTask;
import edu.cmu.hustlr.Util.VisitFriendTask;

/**
 * Created by rueiminl on 2015/12/5.
 */
// related xml: fragment_search_stock.xml
public class SearchStockFragment extends Fragment {
    public static SearchStockFragment newInstance() {
        SearchStockFragment fragment = new SearchStockFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_stock, container, false);

        // goto buy page
        Button btnBuyStock = (Button)v.findViewById(R.id.bottonSearchStockBuy);
        btnBuyStock.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        EditText editSearchStockName = (EditText)v.findViewById(R.id.editHomeSearchStock);
                        new LoadBuyTask(getActivity().getApplicationContext(), editSearchStockName.getText().toString()).execute();
                    }
                }
        );
        // goto short page
        Button btnShortStock = (Button)v.findViewById(R.id.bottonSearchStockShort);
        btnShortStock.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        EditText editSearchStockName = (EditText)v.findViewById(R.id.editHomeSearchStock);
                        new LoadShortTask(getActivity().getApplicationContext(), editSearchStockName.getText().toString()).execute();
                    }
                }
        );

        return v;
    }
}
