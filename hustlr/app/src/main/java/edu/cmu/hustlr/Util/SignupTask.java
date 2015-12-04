package edu.cmu.hustlr.Util;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.cmu.hustlr.Entities.MyGlobal;
import edu.cmu.hustlr.Intent.LoginIntent;

/**
 * Created by rueiminl on 2015/12/4.
 */
public class SignupTask extends HttpRequestTask {
    private Context context;
    public SignupTask(Context context) {
        this.context = context;
    }

    @Override
    protected String getUrl() {
        StringBuffer address = new StringBuffer();
        address.append("http://");
        address.append(MyGlobal.host);
        address.append(":");
        address.append(MyGlobal.port);
        address.append("/signup");
        address.append("?username=");
        address.append(MyGlobal.me.getName());
        address.append("&password=");
        address.append(MyGlobal.me.getPassword());
        return address.toString();
    }

    @Override
    protected void onPostExecute(String jsonString) {
        JSONObject json = null;
        try {
            json = new JSONObject(jsonString);
            if (json.get("result").equals("fail")) {
                Toast.makeText(context, json.get("reason").toString(), Toast.LENGTH_LONG).show();
                return;
            }
            new HomeAccountTask(context).execute();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "failed to transfer json string to json object: " + jsonString, Toast.LENGTH_LONG);
        }

    }
}
