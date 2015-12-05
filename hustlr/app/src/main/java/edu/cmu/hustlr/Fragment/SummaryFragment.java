package edu.cmu.hustlr.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.cmu.hustlr.R;
import edu.cmu.hustlr.Util.VisitFriendTask;

/**
 * Created by rueiminl on 2015/12/4.
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
        textUsername.setText(username);
        TextView textCash = (TextView)v.findViewById(R.id.textSummaryCash);
        textCash.setText(String.valueOf(cash));
        return v;
    }
}