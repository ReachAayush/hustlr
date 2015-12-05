package edu.cmu.hustlr.Intent;


import android.content.Context;
import android.content.Intent;

import edu.cmu.hustlr.Activity.LoginActivity;

/**
 * A Intent to LoginActivity page.
 * Page flow:
 *   from SignupActivity.
 */
public class LoadLoginIntent extends Intent {
    public LoadLoginIntent(Context context) {
        super(context, LoginActivity.class);
        this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

}
