package edu.cmu.hustlr.Activity;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import junit.framework.Assert;

import edu.cmu.hustlr.R;

/**
 * Created by rueiminl on 2015/12/5.
 */
public class SignupActivityTest extends ActivityInstrumentationTestCase2<SignupActivity> {

    private SignupActivity mActivity;
    public SignupActivityTest() {
        super(SignupActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
    }

    public void testUsername() {
        EditText editUsername = (EditText) mActivity.findViewById(R.id.editSignupUsername);
        Assert.assertNotNull(editUsername);
    }

    public void testPassword() {
        EditText editPassword = (EditText) mActivity.findViewById(R.id.editSignupPassword);
        Assert.assertNotNull(editPassword);
    }

    public void testSubmit() {
        Button buttonSubmit = (Button) mActivity.findViewById(R.id.buttonSignupSubmit);
        Assert.assertNotNull(buttonSubmit);
    }

    public void testLogin() {
        Button buttonLogin = (Button) mActivity.findViewById(R.id.buttonSignupLogin);
        Assert.assertNotNull(buttonLogin);
    }

}
