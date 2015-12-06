package edu.cmu.hustlr.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;

import edu.cmu.hustlr.Entities.MyGlobal;
import edu.cmu.hustlr.Intent.LoadLoginIntent;
import edu.cmu.hustlr.R;
import edu.cmu.hustlr.Util.SignupTask;

/**
 * A page for user to signup a new account.
 * Views:
 *   Edit: username, password, cash
 *   Button: Signup, Login
 * xml: activity_signup.xml with R.id = typeSignupWidgetName
 * Page flow:
 *   click Signup: send a "signup" request to the backend (SignupTask), and would be redirected to the AccountHomeActivity
 *   click Login: goto the LoginActivity (LoadLoginIntent)
 */
public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // send a signup request for a new account, if success, goto home account page
        Button buttonSignup = (Button)findViewById(R.id.buttonSignupSubmit);
        buttonSignup.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        try {
                            EditText editUsername = (EditText) findViewById(R.id.editSignupUsername);
                            MyGlobal.me.setName(editUsername.getText().toString());
                            EditText editPassword = (EditText) findViewById(R.id.editSignupPassword);
                            MyGlobal.me.setPassword(editPassword.getText().toString());
                            EditText editCash = (EditText) findViewById(R.id.editSignupCash);
                            MyGlobal.me.setCash(Double.parseDouble(editCash.getText().toString()));
                            // TODO check email, confirm password, ...etc

                            EditText editHost = (EditText)findViewById(R.id.editSignupHost);
                            MyGlobal.host = editHost.getText().toString();

                            new SignupTask(getApplicationContext()).execute();
                        } catch (Exception e) {
                            // TODO customized exception here
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

        // go back to login page
        Button buttonLogin = (Button)findViewById(R.id.buttonSignupLogin);
        buttonLogin.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        startActivity(new LoadLoginIntent(SignupActivity.this));
                    }
                }
        );
    }
}
