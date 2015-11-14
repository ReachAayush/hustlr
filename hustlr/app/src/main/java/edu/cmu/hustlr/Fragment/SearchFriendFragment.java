package edu.cmu.hustlr.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import edu.cmu.hustlr.Activity.FriendHomeActivity;
import edu.cmu.hustlr.Intent.SearchFriendIntent;
import edu.cmu.hustlr.R;

/**
 * Created by rueiminl on 2015/11/13.
 */
public class SearchFriendFragment extends Fragment {

    private static final String FRIEND_NAME = "FRIEND_NAME";

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
        Button buttonSearchFriend = (Button)v.findViewById(R.id.btnHomeSearchFriend);
        buttonSearchFriend.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        EditText editFriendName = (EditText) getActivity().findViewById(R.id.editSearchFriendName);
                        Intent intent = new SearchFriendIntent(getActivity().getApplicationContext(), editFriendName.getText().toString());
                        startActivity(intent);
                    }
                }
        );
        return v;
    }
}
