package edu.cmu.hustlr.Intent;

import android.content.Context;
import android.content.Intent;

import edu.cmu.hustlr.Entities.MyGlobal;
import edu.cmu.hustlr.Entities.User;
import edu.cmu.hustlr.Activity.AccountHomeActivity;

/**
 * Created by rueiminl on 2015/11/13.
 */
public class LoginIntent extends Intent {
    private static final String USER = "USER";
    public LoginIntent(Context context) {
        super(context, AccountHomeActivity.class);
        this.putExtra(USER, MyGlobal.me);
        this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
}
