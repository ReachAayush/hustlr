package edu.cmu.hustlr.Activity;


import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import 	junit.framework.Assert;
import edu.cmu.hustlr.R;

/**
 * Created by rueiminl on 2015/12/5.
 */
public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    private LoginActivity mLoginActivity;
    public LoginActivityTest() {
        super(LoginActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        mLoginActivity = getActivity();
    }

    public void testUsername() {
        EditText editUsername = (EditText)mLoginActivity.findViewById(R.id.editLoginUsername);
        Assert.assertNotNull(editUsername);
    }

    public void testPassword() {
        EditText editPassword = (EditText)mLoginActivity.findViewById(R.id.editLoginPassword);
        Assert.assertNotNull(editPassword);
    }

    public void testSignup() {
        Button buttonSignup = (Button)mLoginActivity.findViewById(R.id.buttonLoginSignin);
        Assert.assertNotNull(buttonSignup);
    }

    public void testRegister() {
        Button buttonRegister = (Button)mLoginActivity.findViewById(R.id.buttonLoginRegister);
        Assert.assertNotNull(buttonRegister);
    }
}