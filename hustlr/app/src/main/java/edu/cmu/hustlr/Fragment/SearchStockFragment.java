package edu.cmu.hustlr.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
 * A Fragment that allows user to search the stock to buy/short.
 * xml: fragment_search_stock.xml with R.id = typeSearchStockWidgetname
 * Views:
 *   Edit: symbol
 *   Button: Buy, Short
 * Page flow:
 *   click Buy: send a "loadbuy" request to the backend (LoadBuyTask), and would be redirected to BuyShortActivity.
 *   click Short: send a "loadshort" request to the backend (LoadShortTask), and would be redirected to BuyShortActivity.
 */
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
        Button btnBuyStock = (Button) v.findViewById(R.id.buttonSearchStockBuy);
        btnBuyStock.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create(); //Read Update
                        alertDialog.setTitle("Buy Stock");

                        final EditText input = new EditText(getActivity());
                        input.setHint("Enter Symbol:");
                        alertDialog.setView(input);

                        alertDialog.setButton("Search", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                new LoadBuyTask(getActivity().getApplicationContext(), input.getText().toString()).execute();
                            }
                        });

                        alertDialog.show();
                    }
                }
        );
        // goto short page
        Button btnShortStock = (Button) v.findViewById(R.id.buttonSearchStockShort);
        btnShortStock.setOnClickListener(

                new Button.OnClickListener() {
                    public void onClick(View v) {

                        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create(); //Read Update
                        alertDialog.setTitle("Short Stock");

                        final EditText input = new EditText(getActivity());
                        input.setHint("Enter Symbol:");
                        alertDialog.setView(input);

                        alertDialog.setButton("Search", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                new LoadShortTask(getActivity().getApplicationContext(), input.getText().toString()).execute();
                            }
                        });

                        alertDialog.show();
                    }
                }

        );

        return v;
    }
}
