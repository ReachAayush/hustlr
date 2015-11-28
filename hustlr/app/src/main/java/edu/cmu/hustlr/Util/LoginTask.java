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

import edu.cmu.hustlr.Entities.MyGlobal;
import edu.cmu.hustlr.Intent.LoginIntent;


/**
 * Created by rueiminl on 2015/11/26.
 */
public class LoginTask extends AsyncTask<String, Void, String> {
    private boolean test = true; // set false to communicate with real server (please modify MyGlobal.host and port if necessary)
    private Context context;
    public LoginTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        if (test)
            return "{'result':'success'}";
        StringBuffer address = new StringBuffer();
        address.append("http://");
        address.append(MyGlobal.host);
        address.append(":");
        address.append(MyGlobal.port);
        address.append("/login");
        address.append("?username=");
        address.append(MyGlobal.me.getName());
        address.append("&password=");
        address.append(MyGlobal.me.getPassword());
        URL url = null;
        try {
            url = new URL(address.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            httpURLConnection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        int responseCode = 0;
        try {
            responseCode = httpURLConnection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (responseCode != 200) {
            Toast.makeText(context, "responseCode = " + responseCode, Toast.LENGTH_LONG).show();
            return null;
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String inputLine;
        StringBuffer response = new StringBuffer();
        try {
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    @Override
    protected void onPostExecute(String json) {
        if (json == null)
            return;
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "failed to transfer json string to json object: " + json, Toast.LENGTH_LONG);
            return;
        }
        assert jsonObject != null;
        try {
            if (jsonObject.get("result").equals("success")) {
                // login success, goto main page
                context.startActivity(new LoginIntent(context));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "failed to get jsonObject['result']", Toast.LENGTH_LONG);
            return;
        }
    }
}
