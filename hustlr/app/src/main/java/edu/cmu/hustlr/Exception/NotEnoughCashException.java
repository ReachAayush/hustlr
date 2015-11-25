package edu.cmu.hustlr.Exception;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by jacobnelson on 11/13/15.
 */
public class NotEnoughCashException extends Throwable{

    private Context myContext;

    public NotEnoughCashException(Context context){
        myContext = context;
    }

    @Override
    public void printStackTrace() {
        Toast.makeText(myContext, "You do not have enough cash to perform this transaction", Toast.LENGTH_LONG).show();
        super.printStackTrace();
    }
}
