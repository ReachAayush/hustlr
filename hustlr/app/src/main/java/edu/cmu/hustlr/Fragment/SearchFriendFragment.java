package edu.cmu.hustlr.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import edu.cmu.hustlr.R;
import edu.cmu.hustlr.Util.VisitFriendTask;

/**
 * Created by rueiminl on 2015/11/13.
 * A Fragment for user to search another user's portfolio.
 * Views:
 *   Edit: name
 *   Button: Search
 * xml: fragment_search_fragment with R.id = typeSearchFriendWidgetName
 * Page flow:
 *   click Search: send a "getotheruser" request to the backend (VisitFriendTask)
 */
public class SearchFriendFragment extends Fragment {

    public static SearchFriendFragment newInstance() {
        SearchFriendFragment fragment = new SearchFriendFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_friend, container, false);
        Button buttonSearchFriend = (Button)v.findViewById(R.id.bottonSearchFriend);
        buttonSearchFriend.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        EditText editFriendName = (EditText) getActivity().findViewById(R.id.editSearchFriendName);
                        String friendName = editFriendName.getText().toString();
                        new VisitFriendTask(getActivity().getApplicationContext(), friendName).execute();
                    }
                }
        );
        return v;
    }
}
