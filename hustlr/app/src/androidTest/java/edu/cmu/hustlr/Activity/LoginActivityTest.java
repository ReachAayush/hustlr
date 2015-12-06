package edu.cmu.hustlr.Activity;


import android.test.ActivityUnitTestCase;
import android.widget.Button;
import android.widget.EditText;
import 	junit.framework.Assert;
import edu.cmu.hustlr.R;

/**
 * Created by rueiminl on 2015/12/5.
 */
public class LoginActivityTest extends ActivityUnitTestCase<LoginActivity> {

    public LoginActivityTest(Class<LoginActivity> activityClass) {
        super(activityClass);
    }

    public void testUsername() {
        EditText editUsername = (EditText)getActivity().findViewById(R.id.editLoginUsername);
        Assert.assertNotNull(editUsername);
    }

    public void testPassword() {
        EditText editPassword = (EditText)getActivity().findViewById(R.id.editLoginPassword);
        Assert.assertNotNull(editPassword);
    }

    public void testSignup() {
        Button buttonSignup = (Button)getActivity().findViewById(R.id.buttonLoginSignin);
        Assert.assertNotNull(buttonSignup);
    }

    public void testRegister() {
        Button buttonRegister = (Button)getActivity().findViewById(R.id.buttonLoginRegister);
        Assert.assertNotNull(buttonRegister);
    }
}