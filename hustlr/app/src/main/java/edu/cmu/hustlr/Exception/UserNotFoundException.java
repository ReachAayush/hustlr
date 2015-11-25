package edu.cmu.hustlr.Exception;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by jacobnelson on 11/13/15.
 */
public class UserNotFoundException extends Throwable{

    private Context myContext;

    public UserNotFoundException(Context context){
        myContext = context;
    }

    @Override
    public void printStackTrace() {
        Toast.makeText(myContext, "This user could not be found", Toast.LENGTH_LONG).show();
        super.printStackTrace();
    }


}
