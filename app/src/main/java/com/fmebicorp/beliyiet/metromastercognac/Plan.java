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
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class Plan extends AppCompatActivity{

    private static final String[] strs2 = new String[] {
            "二号线 淞虹路站","1.2.8人民广场站","北新泾站"
    };
    private ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        listView2 = (ListView) findViewById(R.id.list_plan2);
        ArrayAdapter<String> MyAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strs2);
        listView2.setAdapter(MyAdapter);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int item, long id) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return strs2.length;
        }

        @Override
        public Object getItem(int position) {
            return strs2[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertview, ViewGroup viewGroup) {
            ViewHolder viewHolder = new ViewHolder();
            if (convertview == null){
                convertview = getLayoutInflater().inflate(R.layout.activity_plan,null);
                viewHolder.textView = (TextView)convertview.findViewById(R.id.list_plan2);
                convertview.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder)convertview.getTag();
            }
            viewHolder.textView.setText(strs2[position]);
            return convertview;
        }


    }

    static class ViewHolder{
        TextView textView;
    }

}
