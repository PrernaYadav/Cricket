package com.example.prashantbehera.cricket;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.prashantbehera.cricket.AdapterLive;
import com.google.firebase.iid.FirebaseInstanceId;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Prashant Behera on 26-Oct-17.
 */

public class ActivityLive extends AppCompatActivity {

    public String team1, team2, image1, image2, date, format, ground, MatchId,idw;
    ProgressDialog pd;
    private String id, gcm_token, mp,mp2,sp1,sp2;
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mp = intent.getStringExtra("quantity");
            mp2 = intent.getStringExtra("quantity2");
            sp1 = intent.getStringExtra("spinner1");
            sp2 = intent.getStringExtra("spinner2");
            idw = intent.getStringExtra("idw");
            datasending();

        }

    };
    private RecyclerView recyclerViewEventPrevious;
    private AdapterLive adapterLive;
    //    public Jsonparse jsonParser = new Jsonparse(this);
    private ArrayList<Live> liveArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);

        ArrayList<String> teamname,team;
        team= new ArrayList<String>();








        final SharedPreferences prefs = getSharedPreferences("MatchId", MODE_PRIVATE);
         MatchId = prefs.getString("iddddddd", null);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Cricket Ground Line");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
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

        gcm_token = FirebaseInstanceId.getInstance().getToken();
        initEventPrevious();


        //passing values from dialog
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));
    }

    private void initEventPrevious() {

        recyclerViewEventPrevious = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerViewEventPrevious.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEventPrevious.setHasFixedSize(true);

        recyclerViewEventPrevious.setAdapter(adapterLive);
        liveArrayList = new ArrayList<>();
        adapterLive = new AdapterLive(liveArrayList, ActivityLive.this, this);
        new FetchEventPreviousDataTask().execute();


    }

    private void datasending() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://webpetalsoftware.com/cricket/app/notification",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ActivityLive.this, response, Toast.LENGTH_LONG).show();
                        ;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                /*SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(ConfigInfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                String        email =sharedPreferences.getString(ConfigInfo.EMAIL_SHARED_PREF, "");
*/
                Map<String, String> params = new HashMap<String, String>();
                params.put("gcm_token", gcm_token);
                params.put("favourite", mp);
                params.put("rate", mp2);
                params.put("rate_type", sp1);
                params.put("rate_varriation", sp2);
                params.put("match_id", idw);

               Log.i("hgsah",""+params);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
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

    public class FetchEventPreviousDataTask extends AsyncTask<Object, Object, Boolean> {

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(ActivityLive.this);
            pd.setMessage("loading");
            pd.show();
        }

        @Override
        protected Boolean doInBackground(Object... params) {
            try {
                String dataurl = "http://webpetalsoftware.com/cricket/app/live_match_list";
                HttpPost httppost = new HttpPost(dataurl);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);
                Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                int status = response.getStatusLine().getStatusCode();
                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);
                    JSONObject jsono = new JSONObject(data);
                    JSONArray jarray = jsono.getJSONArray("live_list");
                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject object = jarray.getJSONObject(i);
                        String team1 = object.getString("team1");
                        String team2 = object.getString("team2");
                        String image1 = object.getString("team1_logo");
                        String image2 = object.getString("team2_logo");
                        String date = object.getString("match_time");
                        String time = object.getString("match_time");
                        String format = object.getString("match_format");
                        String ground = object.getString("ground");
                        id = object.getString("id");
                        Live live = new Live();
                        live.setDate(date);
                        live.setTime(time);
                        live.setGround(ground);
                        live.setTeam1(team1);
                        live.setTeam2(team2);
                        live.setFormat(format);
                        live.setId(id);
                        live.setImageTeam1(image1);
                        live.setImageTeam2(image2);


                       /* SharedPreferences sharedPreferences =getApplicationContext().getSharedPreferences("teamname", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("t1", team1);
                        editor.putString("t2", team2);
                        editor.commit();*/

//                        homeList.clear();
                        liveArrayList.add(live);
                    }

                    return true;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            pd.dismiss();
            recyclerViewEventPrevious.setAdapter(adapterLive);
            adapterLive.notifyDataSetChanged();
        }
    }
}
