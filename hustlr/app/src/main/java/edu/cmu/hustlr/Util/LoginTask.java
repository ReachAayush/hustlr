package edu.cmu.hustlr.Util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.TreeMap;

import edu.cmu.hustlr.Entities.MyGlobal;
import edu.cmu.hustlr.Intent.LoginIntent;


/**
 * Created by rueiminl on 2015/11/26.
 */
// if success => goto home account page automatically
public class LoginTask extends HttpRequestTask {
    private Context context;
    public LoginTask(Context context) {
        this.context = context;
    }

    @Override
    protected String getUrl() {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("username", MyGlobal.me.getName());
        params.put("password", MyGlobal.me.getPassword());
        return getUrl("login", params);
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        try {
            if (json.getString("result").equals("fail")) {
                Toast.makeText(context, json.get("reason").toString(), Toast.LENGTH_LONG).show();
                return;
            }
            new HomeAccountTask(context).execute();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
