package edu.cmu.hustlr.Intent;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import edu.cmu.hustlr.Entities.User;
import edu.cmu.hustlr.UI.AccountHomeActivity;
import edu.cmu.hustlr.UI.LoginActivity;

/**
 * Created by rueiminl on 2015/11/13.
 */
public class LoginIntent extends Intent {
    private static final String USER = "USER";
    public LoginIntent(User user, Context context) {
        super(context, AccountHomeActivity.class);
        this.putExtra(USER, user);
    }
}
