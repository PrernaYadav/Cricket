package com.example.prashantbehera.cricket;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class AdapterLiveMatch extends RecyclerView.Adapter<AdapterLiveMatch.LiveMatchHolder> implements RecyclerView.OnClickListener {
    private ArrayList<LiveMatch> liveMatch;
    private Activity activity;
    Context ctx;
    String fav,matchid;


    public AdapterLiveMatch(ArrayList<LiveMatch> liveMatch, Activity activity, Context ctx) {
        this.liveMatch = liveMatch;
        this.activity = activity;
        this.ctx = ctx;

    }

    @Override
    public LiveMatchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_match, parent, false);
        return new LiveMatchHolder(view, ctx, liveMatch);
    }

    @Override
    public void onBindViewHolder(LiveMatchHolder holder, int position) {
        final LiveMatch liveobject = liveMatch.get(position);

    }

    @Override
    public int getItemCount() {
        if (liveMatch == null)
            return 0;
        return liveMatch.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class LiveMatchHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {
        Context ctx;
        ArrayList<LiveMatch> liveMatch = new ArrayList<LiveMatch>();
        public ImageView imageViewTeam1, imageViewTeam2,imageViewBat,imageViewBall;
        public TextView team1, team2, ground, match_format, date, time, iiiid;
//        public Button btnJoinEvent;

        public LiveMatchHolder(final View itemView1, final Context ctx, ArrayList<LiveMatch> liveMatch) {
            super(itemView1);
            this.liveMatch = liveMatch;
            this.ctx = ctx;
            imageViewTeam1 = (ImageView) itemView1.findViewById(R.id.iv_scoreTeam1);
            imageViewTeam2 = (ImageView) itemView1.findViewById(R.id.iv_scoreTeam2);
            imageViewBat = (ImageView) itemView1.findViewById(R.id.iv_bat);
            imageViewBall = (ImageView) itemView1.findViewById(R.id.iv_ball);


            team1 = (TextView) itemView1.findViewById(R.id.tv_country1);
            team2 = (TextView) itemView1.findViewById(R.id.tv_country2);

            ground = (TextView) itemView1.findViewById(R.id.tv_venue);
            iiiid = (TextView) itemView1.findViewById(R.id.id);
            match_format = (TextView) itemView1.findViewById(R.id.tv_match_format);
            date = (TextView) itemView1.findViewById(R.id.tv_date);
            time = (TextView) itemView1.findViewById(R.id.tv_time);
//            btnJoinEvent = (Button) itemView1.findViewById(R.id.btn_join_event);




            itemView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    TextView textView = (TextView) itemView1.findViewById(R.id.id);
                    String iddd = textView.getText().toString();
                    Intent intent = new Intent(activity, ActivityScore.class);



                    intent.putExtra("id", iddd);

                    activity.startActivity(intent);
                }
            });

        }

        public void setteam1(String team_1) {
//            this.txtEventDate = txtEventDate;
            team1.setText(team_1);
        }

        public void setId(String id1) {
            iiiid.setText(id1);
        }

        public void setteam2(String team_2) {
//            this.txtEventDate = txtEventDate;
            team2.setText(team_2);
        }

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
/*            int position = getAdapterPosition();
            LiveMatch liveMatch = this.liveMatch.get(position);
           // String liveeee=live.getId();
           // Toast.makeText(this.ctx,live,Toast.LENGTH_LONG).show();
            Intent intent = new Intent(activity, ActivityScore.class);

            intent.putExtra("id", liveMatch.getId());

            activity.startActivity(intent);*/
        }
    }
}