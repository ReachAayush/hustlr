package edu.cmu.hustlr.Intent;

import android.content.Context;
import android.content.Intent;

import edu.cmu.hustlr.Activity.SignupActivity;

public class LoadSignupIntent extends Intent {
    public LoadSignupIntent(Context context) {
        super(context, SignupActivity.class);
        this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

}
