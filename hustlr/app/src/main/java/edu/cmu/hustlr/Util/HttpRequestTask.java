package edu.cmu.hustlr.Util;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Map;

import edu.cmu.hustlr.Entities.MyGlobal;
import edu.cmu.hustlr.Intent.LoginIntent;

/**
 * Created by rueiminl on 2015/12/4.
 * A task to get the response of the http request.
 */
public abstract class HttpRequestTask extends AsyncTask<String, Void, JSONObject> {
    abstract protected String getUrl();
    abstract protected void onPostExecute(JSONObject json);

    // return null if something wrong
    @Override
    protected JSONObject doInBackground(String... params) {
        String address = getUrl();
        URL url = null;
        try {
            url = new URL(address.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return fail(e.toString());
        }
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            return fail(e.toString());
        }
        // set timeout to 5 sec
        httpURLConnection.setConnectTimeout(5000);
        try {
            httpURLConnection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
            return fail(e.toString());
        }
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        int responseCode = 0;
        try {
            responseCode = httpURLConnection.getResponseCode();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            return fail(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return fail(e.toString());
        }
        if (responseCode != 200) {
            return fail("responseCode != 200");
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            return fail(e.toString());
        }
        String inputLine;
        StringBuffer response = new StringBuffer();
        try {
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return fail(e.toString());
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return fail(e.toString());
        }
        return str2json(response.toString());
    }

    private JSONObject fail(String reason) {
        StringBuffer str = new StringBuffer();
        str.append("{");
        str.append("result:fail, ");
        str.append("reason:\"");
        str.append(reason);
        str.append("\"}");
        return str2json(str.toString());
    }

    private JSONObject str2json(String str) {
        try {
            return new JSONObject(str.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            // panic
            return null;
        }
    }

    // utility
    static protected String getUrl(String page, Map<String, String> params) {
        StringBuffer address = new StringBuffer();
        address.append("http://");
        address.append(MyGlobal.host);
        address.append(":");
        address.append(MyGlobal.port);
        address.append("/");
        address.append(page);
        boolean first = true;
        for (Map.Entry entry : params.entrySet()) {
            if (first) {
                address.append("?");
                first = false;
            }
            else
                address.append("&");
            address.append(entry.getKey());
            address.append("=");
            address.append(entry.getValue());
        }
        return address.toString();
    }
}
