package com.example.prashantbehera.cricket;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    public final int SPLASH_TIME_OUT = 3000;

    //    public GifImageView gifImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__splash);

//        // Set the application environment
//        Mint.setApplicationEnvironment(Mint.appEnvironmentStaging);
//
//        // TODO: Update with your API key
//        Mint.initAndStartSession(this.getApplication(), "29cc6a24");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */


                if(isOnline()) {


                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
               /* mp.stop();*/
                    SplashActivity.this.finish();
                }else {
                    Toast.makeText(SplashActivity.this,"Please Check your Internet Connection",Toast.LENGTH_LONG).show();
                }
            }
        }, SPLASH_TIME_OUT);
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

}
