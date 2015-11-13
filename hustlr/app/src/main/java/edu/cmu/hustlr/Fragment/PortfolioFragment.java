package edu.cmu.hustlr.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import edu.cmu.hustlr.Entities.Portfolio;

/**
 * Created by rueiminl on 2015/11/13.
 */
public class PortfolioFragment extends Fragment {
    public static final String PORTFOLIO_ID = "Portfolio_ID";
    public static PortfolioFragment newInstance(Portfolio p) {
        PortfolioFragment fragment = new PortfolioFragment();
        Bundle args = new Bundle();
        args.putSerializable(PORTFOLIO_ID, p);
        fragment.setArguments(args);
        return fragment;
    }
}
