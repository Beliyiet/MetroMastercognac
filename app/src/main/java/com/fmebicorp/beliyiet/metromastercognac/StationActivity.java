/*******************************************************************************
 * Copyright © 2017 FMEBI.Corp System and CreateON Studio. All rights reserved.
 * Before using all the features of Metro Master (hereinafter referred to as MeM), please be sure to read and thoroughly understand this statement. You may choose not to use MeM, but if you use it, your use will be deemed to be a recognition of the entire contents of this statement.
 * Disclaimer: In view of MeM is currently not fully developed in the function, and the contents of the data information is limited by the information collected in the collection, processing errors may occur, the data is not entirely collected by the CreateON Studio, so the system of search / analysis The results are not responsible. The system does not assume any liability for any adverse consequences arising from the retrieval / analysis of the system as a basis for any commercial conduct or academic research.
 * About privacy: MeM does not currently collect personal privacy other than geographic location information about the user during use.
 * About copyright:
 * 1. All works of MeM that indicate "Metro Master", "Metro Master", "CreateON", "CreateON Studio", "" are owned by CreateON Studio and MeM. Other media, companies, organizations, websites or individuals may not be reproduced in any form, nor distorted and tampered with the contents of the system.
 * 2.MeM currently only granted to the Shanghai Fire Brigade special detachment of the new Jing squadron all, temporarily not granted any other units and personal use.
 * 3. Units authorized by the system shall not exceed the scope of authorization.
 * 4. The information provided by the system does not conform to the relevant paper text, subject to the paper text.
 * 5. If you are in contact with MeM due to the contents of the work, copyright and other issues, please do so within 30 days after MeM has released the work.
 * The right to interpret the system: the declaration of the system and its modification, renewal and final interpretation are owned by CreateON Studio and MeM.
 ******************************************************************************/

package com.fmebicorp.beliyiet.metromastercognac;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;

public class StationActivity extends AppCompatActivity {

    private static final String[] strs = new String[] {
            "1号线", "2号线", "2号线东延伸段","3号线", "4号线", "5号线","6号线","7号线","8号线","9号线","磁浮线","10号线(新江湾城-航中路)","10号线(新江湾城-虹桥火车站)","11号线(嘉定北-迪士尼)","11号线(花桥-三林)","12号线","16号线"
    };
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_station);
        setContentView(R.layout.activity_station);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "查看线路图", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        listView = (ListView) findViewById(R.id.station_list);
        listView.setAdapter((new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs)));

    }

}
