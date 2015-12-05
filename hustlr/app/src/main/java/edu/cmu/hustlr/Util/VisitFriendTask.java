package edu.cmu.hustlr.Util;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

import edu.cmu.hustlr.Entities.MyGlobal;
import edu.cmu.hustlr.Intent.AccountHomeIntent;
import edu.cmu.hustlr.Intent.FriendHomeIntent;

/**
 * Created by rueiminl on 2015/12/4.
 * A task to get the response of the request "getotheruser". Would be redirected to the FriendHomeActivity.
 */
public class VisitFriendTask extends HttpRequestTask {
    private Context context;
    private String friendName;
    public VisitFriendTask(Context context, String friendName) {
        this.context = context;
        this.friendName = friendName;
    }
    @Override
    protected String getUrl() {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("username", MyGlobal.me.getName());
        params.put("friendname", friendName);
        return getUrl("getotheruser", params);
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        try {
            if (json.getString("result").equals("fail")) {
                Toast.makeText(context, json.get("reason").toString(), Toast.LENGTH_LONG).show();
                return;
            }
            MyGlobal.friend = JsonToUser.transfer(json);
            MyGlobal.friend.setName(friendName);

            // goto friend home page
            context.startActivity(new FriendHomeIntent(context));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
