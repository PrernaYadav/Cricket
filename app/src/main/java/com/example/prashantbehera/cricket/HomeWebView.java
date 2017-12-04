package com.example.prashantbehera.cricket;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import android.speech.tts.TextToSpeech;

import org.json.JSONArray;
import org.json.JSONObject;

public class HomeWebView extends AppCompatActivity  {
    Context context;
    private TextToSpeech tts;
    LinearLayout llrefresh;
    String url;
    final Handler handler = new Handler();
//     String textaa;
String idOffer = "";
    String idi,idirc;
    TextView tvspeak;
    private TextToSpeech ttsHindi;
    String url1 = "http://www.webpetalsoftware.com/cricket/web/new_live.php?match_id=";
    //    private WebView webview;
    private Button btnrefresh;
    private WebView webview = null;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_web_view);
        this.webview = (WebView) findViewById(R.id.webview1);
        btnrefresh = (Button) findViewById(R.id.btn_refresh);
        llrefresh = (LinearLayout) findViewById(R.id.ll_refresh);
        tvspeak = (TextView)findViewById(R.id.tv_speak);

//        textaa=tvspeak.getText().toString();


        //fetching values
        Intent intent = getIntent();
        idi = intent.getStringExtra("id");
        String mp = intent.getStringExtra("fav");
        url = url1 + idi + "& favourite=" + mp;


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


        if (isOnline()) {
            webview.setVisibility(View.VISIBLE);
            web();

              Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        web();
                                        texttospeech();
                                        handler.postDelayed(this, 5000);
                                    }
                                };

//Start
                                handler.postDelayed(runnable, 5000);




//

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


                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            web();
                            texttospeech();
                            handler.postDelayed(this, 5000);
                        }
                    };

//Start
                    handler.postDelayed(runnable, 5000);



                } else {
                    Toast.makeText(HomeWebView.this, "Please turn on Your Internet Connection", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    protected void onStop()
    {
        super.onStop();
        handler.removeCallbacksAndMessages(null);
        // insert here your instructions
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onStart()
    {
        super.onStart();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                web();
                texttospeech();
                handler.postDelayed(this, 5000);
            }
        };

//Start
        handler.postDelayed(runnable, 5000);


        /* web();
        texttospeech();*/
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void web() {
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setMediaPlaybackRequiresUserGesture(true);
        webSettings.setAllowContentAccess(true);
        webview.setAccessibilityDelegate(new View.AccessibilityDelegate());
        WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        webview.setWebViewClient(webViewClient);
        webview.loadUrl(url);
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (webview.canGoBack()) {
                webview.goBack();


            } else {
                super.onBackPressed();
                handler.removeCallbacksAndMessages(null);
            }
            return true;
        }else if ((keyCode==KeyEvent.KEYCODE_POWER)) {

Log.d("power","power key pressed");

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




            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(url);
            return true;
        }

    }


    public void texttospeech(){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigInfo.texttospeech,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try
                        {
                            JSONObject result = new JSONObject(response);

                            JSONArray   routearray=result.getJSONArray("speak_rate");
                            for (int i = 0; i < routearray.length(); i++) {
                                JSONObject jsonObject = routearray.getJSONObject(i);
                              final String  textaa = jsonObject.getString("rate_array");
                                Log.i("textaaattt",textaa);

                                StartSpeak(textaa);


                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HomeWebView.this, "Error Sign Up Process.Please Check your entered Credentials", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {


                Map<String, String> params = new HashMap<String, String>();
                params.put("match_id",idi );
                Log.d("matchiddd",idi);





                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(HomeWebView.this);
        requestQueue.add(stringRequest);
    }

    private void StartSpeak(final String data) {

        tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int initStatus) {
                if (initStatus == TextToSpeech.SUCCESS) {
                    if(tts.isLanguageAvailable(Locale.US)==TextToSpeech.LANG_AVAILABLE)
                        tts.setLanguage(Locale.US);
                    tts.setPitch(1.3f);
                    tts.setSpeechRate(0.7f);
                    // start speak
                    speakWords(data);
                }
                else if (initStatus == TextToSpeech.ERROR) {
                    Toast.makeText(getApplicationContext(), "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
                }
            }


        });
    }


    private void speakWords(String speech) {
        tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
    }

}