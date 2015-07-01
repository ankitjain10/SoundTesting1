package com.example.dell.soundtesting;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by dell on 6/24/2015.
 */
public class HTTPExample extends Activity {
    TextView textView;
    HttpClient client;
    JSONObject json;

    private static final String URL = "http://api.twitter.com/1/statusses/user_timeline.json?screen_name=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpexample);
        textView = (TextView) findViewById(R.id.textView);
        client = new DefaultHttpClient();
       // new abc().execute("text");
    }

    public JSONObject lastTweet(String username) throws ClientProtocolException,IOException, JSONException {
        StringBuilder url = new StringBuilder(URL);
        url.append(username);
        HttpGet get = new HttpGet(url.toString());

            HttpResponse response = client.execute(get);
            int status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                HttpEntity e = response.getEntity();
                String data = e.toString();
                JSONArray timeline = new JSONArray(data);
                JSONObject last = timeline.getJSONObject(0);
                return last;
            } else
                return null;
    }/*
    class abc extends AsyncTask<String,Integer,String>{


        *//*protected String doInBackground(String... params) throws JSONException{
                json=lastTweet("mybraingback");
                return json.getString(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }*/
}
