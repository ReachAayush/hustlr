package edu.cmu.hustlr.Intent;

import android.content.Context;
import android.content.Intent;

import edu.cmu.hustlr.Activity.AccountHomeActivity;
import edu.cmu.hustlr.Activity.FriendHomeActivity;
import edu.cmu.hustlr.Entities.MyGlobal;
import edu.cmu.hustlr.Entities.User;

/**
 * Created by rueiminl on 2015/12/4.
 * A Intent to FriendHomeActivity page.
 * from VisitFriendTask.
 */
public class FriendHomeIntent extends Intent {
    public FriendHomeIntent(Context context) {
        super(context, FriendHomeActivity.class);
        this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

}
