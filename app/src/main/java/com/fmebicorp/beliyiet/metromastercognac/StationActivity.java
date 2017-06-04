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
        listView.setAdapter((new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strs)));

    }

}
