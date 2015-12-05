package edu.cmu.hustlr.Util;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

import edu.cmu.hustlr.Entities.MyGlobal;

/**
 * Created by rueiminl on 2015/12/4.
 * A task to get the response of the request "signup". Would be redirected to the AccountHomeActivity.
 */
public class SignupTask extends HttpRequestTask {
    private Context context;
    public SignupTask(Context context) {
        this.context = context;
    }

    @Override
    protected String getUrl() {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("username", MyGlobal.me.getName());
        params.put("password", MyGlobal.me.getPassword());
        params.put("cash", String.valueOf(MyGlobal.me.getCash()));
        return getUrl("signup", params);
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        try {
            if (json.getString("result").equals("fail")) {
                Toast.makeText(context, json.get("reason").toString(), Toast.LENGTH_LONG).show();
                return;
            }
            new AccountHomeTask(context).execute();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
