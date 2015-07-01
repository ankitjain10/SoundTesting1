package com.example.dell.soundtesting;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by dell on 6/17/2015.
 */
public class WebBrowser extends Activity implements View.OnClickListener {

    WebView browser;
    Button Go,GoBK,GoFR,GoRF,GoCR;
    EditText inputURL;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webbrowser);

        Go=(Button)findViewById(R.id.Go);
        GoBK=(Button)findViewById(R.id.GoBK);
        GoFR=(Button)findViewById(R.id.GoFR);
        GoRF=(Button)findViewById(R.id.GoRF);
        GoCR=(Button)findViewById(R.id.GoCR);
        inputURL=(EditText)findViewById(R.id.inputURL);
        Go.setOnClickListener(this);
        GoBK.setOnClickListener(this);
        GoFR.setOnClickListener(this);
        GoRF.setOnClickListener(this);
        GoCR.setOnClickListener(this);


        browser=(WebView)findViewById(R.id.browser);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setLoadWithOverviewMode(true);
        browser.getSettings().setUseWideViewPort(true);

        browser.setWebViewClient(new myViewClient());
        try {
            browser.loadUrl("https://www.google.co.in");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Go:
                String URL=inputURL.getText().toString();
                browser.loadUrl(URL);
                InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromInputMethod(inputURL.getWindowToken(),0);
                break;

            case R.id.GoBK:
                if(browser.canGoBack())
                    browser.goBack();
                break;

            case R.id.GoFR:
                if(browser.canGoForward())
                    browser.goForward();
                break;

            case R.id.GoRF:
                browser.reload();
                break;

            case R.id.GoCR:
                browser.clearHistory();
                break;


        }
    }
}
