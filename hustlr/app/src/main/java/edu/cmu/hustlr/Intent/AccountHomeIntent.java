package edu.cmu.hustlr.Intent;

import android.content.Context;
import android.content.Intent;

import edu.cmu.hustlr.Activity.AccountHomeActivity;
import edu.cmu.hustlr.Entities.MyGlobal;

/**
 * Created by rueiminl on 2015/12/4.
 */
public class AccountHomeIntent extends Intent {
    public AccountHomeIntent(Context context) {
        super(context, AccountHomeActivity.class);
        this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
}

