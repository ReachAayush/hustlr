package edu.cmu.hustlr.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.cmu.hustlr.R;
/**
 * Created by rueiminl on 2015/12/4.
 * A Fragment shows the summary of the user's portfolio.
 * Views:
 *   Text: username, cash
 * xml: fragment_summary.xml with R.id = typeSummaryWidgetname
 */
public class SummaryFragment extends Fragment {
    private String username;
    private double cash;
    public static SummaryFragment newInstance(String username, double cash) {
        SummaryFragment fragment = new SummaryFragment();
        fragment.username = username;
        fragment.cash = cash;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_summary, container, false);
        TextView textUsername = (TextView) v.findViewById(R.id.textSummaryUsername);
        textUsername.setText(username.concat("'s Portfolio"));
        TextView textCash = (TextView)v.findViewById(R.id.textSummaryCash);
        String cashStr = "Cash on Hand: $";
        textCash.setText(cashStr.concat(String.valueOf(cash)));
        return v;
    }
}