package com.example.prashantbehera.cricket;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /** TabHost will have Tabs */
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

        TabHost.TabSpec firstTabSpec = tabHost.newTabSpec("tab_id1");
        TabHost.TabSpec secondTabSpec = tabHost.newTabSpec("tab_id2");


        firstTabSpec.setIndicator("Home").setContent(new Intent(this,ActivityHome.class));
        secondTabSpec.setIndicator("Live ").setContent(new Intent(this,ActivityLive.class));


        tabHost.addTab(firstTabSpec);
        tabHost.addTab(secondTabSpec);


    } }
