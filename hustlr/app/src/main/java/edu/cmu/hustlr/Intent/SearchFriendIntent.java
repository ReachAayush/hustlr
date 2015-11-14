package edu.cmu.hustlr.Intent;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import edu.cmu.hustlr.Activity.BuyShortActivity;
import edu.cmu.hustlr.Activity.FriendHomeActivity;
import edu.cmu.hustlr.Entities.Stock;
import edu.cmu.hustlr.Entities.User;

/**
 * Created by rueiminl on 2015/11/13.
 */
public class SearchFriendIntent extends Intent {
    private static final String USER_ID = "USER_ID";
    public SearchFriendIntent(Context context, String friendname) {
        super(context, FriendHomeActivity.class);
        // TODO get User based on the friendname by market API
        User friend = User.createMockUser();
        friend.setName(friendname);

        this.putExtra(USER_ID, friend);
    }

}
