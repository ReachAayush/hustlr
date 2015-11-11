package edu.cmu.hustlr.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import android.content.*;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button buttonSignup = (Button)findViewById(R.id.buttonSignup);
        buttonSignup.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        Intent intent = new Intent(SignupActivity.this, AccountHomeActivity.class);
                        startActivity(intent);
                    }
                }
        );

        Button buttonLogin = (Button)findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        Intent intent = new Intent(SignupActivity.this, AccountHomeActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
