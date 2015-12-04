package edu.cmu.hustlr.Util;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.cmu.hustlr.Entities.MyGlobal;
import edu.cmu.hustlr.Intent.HomeAccountIntent;

/**
 * Created by rueiminl on 2015/12/4.
 */
public class HomeAccountTask extends HttpRequestTask {
    private Context context;
    public HomeAccountTask(Context context) {
        this.context = context;
    }

    @Override
    protected String getUrl() {
        StringBuffer address = new StringBuffer();
        address.append("http://");
        address.append(MyGlobal.host);
        address.append(":");
        address.append(MyGlobal.port);
        address.append("/getuser");
        address.append("?username=");
        address.append(MyGlobal.me.getName());
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
            // backup username
            String username = MyGlobal.me.getName();

            MyGlobal.me = JsonToUser.transfer(json);
            MyGlobal.me.setName(username);

            // goto home account page
            context.startActivity(new HomeAccountIntent(context));
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "failed to transfer json string to json object: " + jsonString, Toast.LENGTH_LONG).show();
        }

    }
}
