package com.wefour.triton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class demoweb extends AppCompatActivity {

    WebView paymentgate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demoweb);

        paymentgate = (WebView) findViewById(R.id.paymentgate);

        paymentgate.setWebViewClient(new MyBrowser());

        paymentgate.getSettings().setLoadsImagesAutomatically(true);
        paymentgate.getSettings().setJavaScriptEnabled(true);
        paymentgate.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        // loading http://www.google.com url in the the WebView.
        paymentgate.loadUrl("https://kpayment.000webhostapp.com/Paytm/Paymentpage.php");

        paymentgate.setWebViewClient(new WebViewClient());

    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl("https://kpayment.000webhostapp.com/Paytm/Paymentpage.php");
            return true;
        }
    }
}
