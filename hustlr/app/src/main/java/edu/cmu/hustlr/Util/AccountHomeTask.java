package edu.cmu.hustlr.Util;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

import edu.cmu.hustlr.Entities.MyGlobal;
import edu.cmu.hustlr.Intent.AccountHomeIntent;
/**
 * Created by rueiminl on 2015/12/4.
 * A task to get the response of the request "getuser". Would be redirected to AccountHomeActivity.
 */
public class AccountHomeTask extends HttpRequestTask {
    private Context context;
    public AccountHomeTask(Context context) {
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
            context.startActivity(new AccountHomeIntent(context));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
