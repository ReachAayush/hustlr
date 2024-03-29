package edu.cmu.hustlr.Exception;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by jacobnelson on 11/13/15.
 */
public class StockNotFoundException extends Throwable {

    private Context myContext;

    public StockNotFoundException(Context context){
        myContext = context;
    }

    @Override
    public void printStackTrace() {
        Toast.makeText(myContext, "This stock could not be found", Toast.LENGTH_LONG).show();
        super.printStackTrace();
    }
}
