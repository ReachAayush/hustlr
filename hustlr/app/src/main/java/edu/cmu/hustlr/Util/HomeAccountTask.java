package edu.cmu.hustlr.Util;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

import edu.cmu.hustlr.Entities.MyGlobal;
import edu.cmu.hustlr.Intent.HomeAccountIntent;

/**
 * Created by rueiminl on 2015/12/4.
 */
// goto home account page
public class HomeAccountTask extends HttpRequestTask {
    private Context context;
    public HomeAccountTask(Context context) {
        this.context = context;
    }

    @Override
    protected String getUrl() {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("username", MyGlobal.me.getName());
        return getUrl("getuser", params);
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        try {
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
        }

    }
}
