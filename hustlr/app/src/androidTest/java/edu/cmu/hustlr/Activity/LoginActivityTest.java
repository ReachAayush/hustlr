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

    private LoginActivity mActivity;
    public LoginActivityTest() {
        super(LoginActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
    }

    public void testUsername() {
        EditText edit = (EditText) mActivity.findViewById(R.id.editLoginUsername);
        Assert.assertNotNull(edit);
    }

    public void testPassword() {
        EditText edit = (EditText) mActivity.findViewById(R.id.editLoginPassword);
        Assert.assertNotNull(edit);
    }

    public void testSignup() {
        Button button = (Button) mActivity.findViewById(R.id.buttonLoginSignin);
        Assert.assertNotNull(button);
    }

    public void testRegister() {
        Button button = (Button) mActivity.findViewById(R.id.buttonLoginRegister);
        Assert.assertNotNull(button);
    }
}