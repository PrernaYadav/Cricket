package com.example.prashantbehera.cricket;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ActivityHome extends AppCompatActivity implements View.OnClickListener {
    WebView webview;
    private Button btnrefresh;
    Context context;
    LinearLayout llrefresh;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab,fab1,fab2,fab3;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        webview = (WebView) findViewById(R.id.webview);
        web();


       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
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
        });*/
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab3 = (FloatingActionButton)findViewById(R.id.fab3);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
    }

       /* webview = (WebView) findViewById(R.id.webview);
        webview.setWebViewClient(new MyCustomWebViewClient());
        webview .getSettings().setJavaScriptEnabled(true);
        webview .getSettings().setDomStorageEnabled(true);
        webview.loadUrl("http://webpetalsoftware.com/cricket/web");*/





       /* final String url = "http://webpetalsoftware.com/cricket/web";


            webview.setWebViewClient(new MyCustomWebViewClient());
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl(url);*/







    public void web() {
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        ActivityHome.WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        webview.setWebViewClient(webViewClient);
        webview.loadUrl("http://webpetalsoftware.com/cricket/web");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (webview.canGoBack()) {
                webview.goBack();
            } else {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }




    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id){
            case R.id.fab:

                animateFAB();
                break;
            case R.id.fab1:

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Your App Link here";
                String shareSub = "Your subject here";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));

                break;
            case R.id.fab2:
                Intent intent=new Intent(ActivityHome.this,AboutUs.class);
                startActivity(intent);


                break;
            case R.id.fab3:


                break;
        }

    }

    private void animateFAB() {

        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab3.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            isFabOpen = false;
           // Log.d("Raj", "close");

        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab3.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
            isFabOpen = true;
            Log.d("Raj","open");

        }
    }



    public class WebViewClientImpl extends WebViewClient {

        private Activity activity = null;

        public WebViewClientImpl(Activity activity) {
            this.activity = activity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
          //  if(url.indexOf("http://www.webpetalsoftware.com/cricket/web/new_live.php?match_id=") > -1 )

            if(url.indexOf("http://webpetalsoftware.com/cricket/web") > -1 )
                return false;

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            webview.setWebViewClient(new WebViewClient());
            activity.startActivity(intent);


           /* webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(url);*/
            return true;
        }

    }

}

