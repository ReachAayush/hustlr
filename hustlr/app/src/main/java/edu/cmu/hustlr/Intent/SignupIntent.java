package edu.cmu.hustlr.Intent;
import android.content.Intent;
import android.provider.ContactsContract.CommonDataKinds.Email;

/**
 * Created by rueiminl on 2015/11/13.
 */
// not used so far. Might be used when clicking signup button in signup page and directing to a information page indicating success or not.
public class SignupIntent extends Intent {
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";
    private static final String EMAIL = "EMAIL";
    private static final String CASH = "CASH";
    public SignupIntent(String username, String password, String address, float cash) {
        super();
        this.putExtra(USERNAME, username);
        this.putExtra(PASSWORD, password);
        this.putExtra(EMAIL, address);
        this.putExtra(CASH, cash);
    }
}
