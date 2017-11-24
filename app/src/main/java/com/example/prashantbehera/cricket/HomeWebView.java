package com.example.prashantbehera.cricket;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class HomeWebView extends AppCompatActivity {
    Context context;
    LinearLayout llrefresh;
    String url;
    String url1 = "http://www.webpetalsoftware.com/cricket/web/new_live.php?match_id=";
    //    private WebView webview;
    private Button btnrefresh;
    private WebView webview = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_web_view);
        this.webview = (WebView) findViewById(R.id.webview1);
        btnrefresh = (Button) findViewById(R.id.btn_refresh);
        llrefresh = (LinearLayout) findViewById(R.id.ll_refresh);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Your App Link here";
                String shareSub = "Your subject here";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });


        //fetching values
        Intent intent = getIntent();
        String idi = intent.getStringExtra("id");
        String mp = intent.getStringExtra("fav");
        url = url1 + idi + "& favourite=" + mp;

        if (isOnline()) {
            webview.setVisibility(View.VISIBLE);
 /*   webview.setWebViewClient(new MyCustomWebViewClient());
    webview.getSettings().setJavaScriptEnabled(true);
    webview.loadUrl(url);*/
            web();
        } else {
            webview.setVisibility(View.GONE);
            llrefresh.setVisibility(View.VISIBLE);
        }


        btnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOnline()) {

                    llrefresh.setVisibility(View.GONE);
                    webview.setVisibility(View.VISIBLE);

                  /*  webview.setWebViewClient(new MyCustomWebViewClient());
                    webview.getSettings().setJavaScriptEnabled(true);
                    webview.loadUrl(url);*/
                    web();

                } else {
                    Toast.makeText(HomeWebView.this, "Please turn on Your Internet Connection", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void web() {
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        webview.setWebViewClient(webViewClient);
        webview.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (webview.canGoBack()) {
                webview.goBack();
            } else {
                super.onBackPressed();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public boolean isOnline() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
    public class WebViewClientImpl extends WebViewClient {

        private Activity activity = null;

        public WebViewClientImpl(Activity activity) {
            this.activity = activity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            if(url.indexOf("http://www.webpetalsoftware.com/cricket/web/new_live.php?match_id=") > -1 )
                return false;

       /* Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            webview.setWebViewClient(new WebViewClient());
        activity.startActivity(intent);*/


            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(url);
            return true;
        }

    }
}