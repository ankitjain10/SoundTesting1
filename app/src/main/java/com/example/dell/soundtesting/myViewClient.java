package com.example.dell.soundtesting;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by dell on 6/18/2015.
 */
public class myViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
