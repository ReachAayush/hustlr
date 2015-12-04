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
import java.net.URL;

import edu.cmu.hustlr.Entities.MyGlobal;
import edu.cmu.hustlr.Intent.LoginIntent;

/**
 * Created by rueiminl on 2015/12/4.
 */
public abstract class HttpRequestTask extends AsyncTask<String, Void, String> {
    abstract protected String getUrl();
    abstract protected void onPostExecute(String jsonString);

    // return null if something wrong
    @Override
    protected String doInBackground(String... params) {
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
        return response.toString();
    }

    private String fail(String reason) {
        StringBuffer str = new StringBuffer();
        str.append("{");
        str.append("result:fail, ");
        str.append("reason:"); str.append(reason);
        str.append("}");
        return str.toString();
    }
}
