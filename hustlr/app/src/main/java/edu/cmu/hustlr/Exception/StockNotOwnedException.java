package edu.cmu.hustlr.Exception;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by jacobnelson on 11/24/15.
 */
public class StockNotOwnedException extends Throwable {
    private Context myContext;

    public StockNotOwnedException(Context context){
        myContext = context;
    }

    @Override
    public void printStackTrace() {
        Toast.makeText(myContext, "You do not own this stock", Toast.LENGTH_LONG).show();
        super.printStackTrace();
    }
}
