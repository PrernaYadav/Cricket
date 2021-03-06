package com.example.prashantbehera.cricket;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;
import static com.example.prashantbehera.cricket.R.string.fav;

/**
 * Created by Prashant Behera on 27-Oct-17.
 */

public class AdapterLive extends RecyclerView.Adapter<AdapterLive.LiveHolder> implements RecyclerView.OnClickListener {
    private static final String dialog_pattern = "[0-9]+-[0-9]+";
    Context ctx;
    String mp, mp2, sp1, sp2,iddd,idd;
    private ArrayList<Live> live;
    private Activity activity;
    String teamname1,teamname2;
    String[] team = new String[2];






    public AdapterLive(ArrayList<Live> live, Activity activity, Context ctx) {
        this.live = live;
        this.activity = activity;
        this.ctx = ctx;

    }

    @Override
    public LiveHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new LiveHolder(view, ctx, live);
    }

    @Override
    public void onBindViewHolder(LiveHolder holder, int position) {
        final Live liveobject = live.get(position);

        holder.setteam1(liveobject.getTeam1());
        holder.setteam2(liveobject.getTeam2());
        holder.setdate(liveobject.getDate());
        holder.setground(liveobject.getGround());
        holder.setmatch_format(liveobject.getFormat());
        holder.settime(liveobject.getTime());
        holder.setId(liveobject.getId());
        // holder.setMarketPrice(liveobject.getMarketPrice());

        Glide.with(activity).load(liveobject.getImageTeam1()).into(holder.imageViewTeam1);
        Glide.with(activity).load(liveobject.getImageTeam2()).into(holder.imageViewTeam2);
    }

    @Override
    public int getItemCount() {
        if (live == null)
            return 0;
        return live.size();
    }

    @Override
    public void onClick(View v) {

    }


    public class LiveHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {
        public ImageView imageViewTeam1, imageViewTeam2, bell;
        TextView textView;
        public TextView team1, team2, ground, match_format, date, time, id, marketPrice;
        Context ctx;
        ArrayList<Live> live = new ArrayList<Live>();
//        public Button btnJoinEvent;

        public LiveHolder(final View itemView1, final Context ctx, ArrayList<Live> live) {
            super(itemView1);
            this.live = live;
            this.ctx = ctx;
            bell = (ImageView) itemView1.findViewById(R.id.bell);
            imageViewTeam1 = (ImageView) itemView1.findViewById(R.id.iv_image1);
            imageViewTeam2 = (ImageView) itemView1.findViewById(R.id.iv_image2);
            team1 = (TextView) itemView1.findViewById(R.id.tv_country1);
            team2 = (TextView) itemView1.findViewById(R.id.tv_country2);
            // marketPrice = (TextView) itemView1.findViewById(R.id.market_price);
            ground = (TextView) itemView1.findViewById(R.id.tv_venue);
            id = (TextView) itemView1.findViewById(R.id.id);
            match_format = (TextView) itemView1.findViewById(R.id.tv_match_format);
            date = (TextView) itemView1.findViewById(R.id.tv_date);
            time = (TextView) itemView1.findViewById(R.id.tv_time);






            /* textView = (TextView) itemView1.findViewById(R.id.id);
          final   String iddd = textView.getText().toString();

            SharedPreferences sharedPreferences = itemView1.getContext().getSharedPreferences("MatchId", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("iddddddd", iddd);
            editor.commit();*/
//            btnJoinEvent = (Button) itemView1.findViewById(R.id.btn_join_event);


            bell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    textView = (TextView) itemView1.findViewById(R.id.id);
                        iddd = textView.getText().toString();


                    teamname1=team1.getText().toString();
                    teamname2=team2.getText().toString();
                    Log.d("teammmm1",teamname1);
                    Log.d("teammmm2",teamname2);


                    team[0]=teamname1;
                    team[1]=teamname2;

                    SharedPreferences sharedPreferences = itemView1.getContext().getSharedPreferences("MatchId", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("iddddddd", iddd);
                    editor.commit();
                    //change bel icon
                    bell.setImageResource(R.drawable.bell_iconfilled);


                    //Alert Dialog
                    // get prompts.xml view
                    LayoutInflater li = LayoutInflater.from(ctx);
                    View promptsView = li.inflate(R.layout.alert_activity, null);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            ctx);





                    // set prompts.xml to alertdialog builder
                    alertDialogBuilder.setView(promptsView);

                       final Spinner spinnerTeam =(Spinner) promptsView.findViewById(R.id.spinner1);
                  //  final EditText userInput2 = (EditText) promptsView.findViewById(R.id.et_favrt);
                    final EditText userInput3 = (EditText) promptsView.findViewById(R.id.et_amount);
                    final Spinner spinner1 = (Spinner) promptsView.findViewById(R.id.sp_1);
                    final Spinner spinner2 = (Spinner) promptsView.findViewById(R.id.sp_2);



                    spinnerTeam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    ArrayAdapter aa = new ArrayAdapter(itemView1.getContext(),android.R.layout.simple_spinner_item,team);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    spinnerTeam.setAdapter(aa);





                    // set dialog message
                    alertDialogBuilder.setCancelable(false).setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    bell.setClickable(true);
                                    mp = spinnerTeam.getSelectedItem().toString();
                                    mp2 = userInput3.getText().toString().toLowerCase();
                                    sp1 = spinner1.getSelectedItem().toString();
                                    sp2 = spinner2.getSelectedItem().toString();


                                    Intent intent = new Intent("custom-message");
                                    intent.putExtra("quantity", mp);
                                    intent.putExtra("quantity2", mp2);
                                    intent.putExtra("spinner1", sp1);
                                    intent.putExtra("spinner2", sp2);
                                    intent.putExtra("idw", iddd);

                                    LocalBroadcastManager.getInstance(ctx).sendBroadcast(intent);

                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    bell.setImageResource(R.drawable.bell_iconoutline);
                                }
                            });
                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    // show it
                    alertDialog.show();
                }
            });


            itemView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    idd=id.getText().toString();


                    /*TextView textView = (TextView) itemView1.findViewById(R.id.id);
                    String iddd = textView.getText().toString();*/

                    Intent intent = new Intent(activity, HomeWebView.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("id", idd);
                    intent.putExtra("fav", mp);
                    activity.startActivity(intent);





                }
            });

        }

        public void setteam1(String team_1) {
//            this.txtEventDate = txtEventDate;
            team1.setText(team_1);
        }

        public void setId(String id1) {
            id.setText(id1);
        }

        public void setteam2(String team_2) {
//            this.txtEventDate = txtEventDate;
            team2.setText(team_2);
        }
/*

        public void setMarketPrice(String marketprice) {
//            this.txtEventDate = txtEventDate;
            marketPrice.setText(marketprice);
        }
*/

        public void setground(String ground_set) {
//            this.txtEventDate = txtEventDate;
            ground.setText(ground_set);
        }

        public void setmatch_format(String match_format_set) {
//            this.txtEventDate = txtEventDate;
            match_format.setText(match_format_set);
        }

        public void setdate(String dateset) {
//            this.txtEventDate = txtEventDate;
            date.setText(dateset);
        }

        public void settime(String timeset) {
//            this.txtEventDate = txtEventDate;
            time.setText(timeset);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Live live = this.live.get(position);
            // String liveeee=live.getId();
            // Toast.makeText(this.ctx,live,Toast.LENGTH_LONG).show();
            Intent intent = new Intent(activity, ActivityScore.class);
            intent.putExtra("id", live.getId());

            activity.startActivity(intent);
        }
    }


}