package edu.cmu.hustlr.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import android.content.*;

import edu.cmu.hustlr.Entities.MyGlobal;
import edu.cmu.hustlr.R;
import edu.cmu.hustlr.Util.SignupTask;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // send a signup request for a new account, if success, goto home account page
        Button buttonSignup = (Button)findViewById(R.id.buttonSignup);
        buttonSignup.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        EditText editUsername = (EditText)findViewById(R.id.editSignupUsername);
                        MyGlobal.me.setName(editUsername.getText().toString());
                        EditText editPassword = (EditText)findViewById(R.id.editSignupPassword);
                        MyGlobal.me.setPassword(editPassword.getText().toString());
                        // TODO check initial cash, email or confirm password ...etc
                        new SignupTask(getApplicationContext()).execute();
                    }
                }
        );

        // go back to login page
        Button buttonLogin = (Button)findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
