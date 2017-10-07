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

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

        //主界面项目点击事件:实战资料速查
        Button button  = (Button)findViewById(R.id.main_content_button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,StationInformation.class);
                startActivity(intent);
            }
        });
        //主界面项目点击事件:作战编程
        Button button2  = (Button)findViewById(R.id.main_content_button4);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                Intent intent2 = new Intent(MainActivity.this,PdfViewActivity.class);
                startActivity(intent2);
            }
        });

        //主界面项目点击事件:路线导航
        Button button3  = (Button)findViewById(R.id.main_content_button1);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view3) {
                Intent intent3 = new Intent(MainActivity.this,MapActivity.class);
                startActivity(intent3);
            }
        });

        //主界面项目点击事件:考试系统
        Button button4  = (Button)findViewById(R.id.main_content_button6);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view4) {
                Intent intent4 = new Intent(MainActivity.this,com.example.answer.MainActivity.class);
                startActivity(intent4);
            }
        });

        //主界面项目点击事件:考试系统
        Button button5  = (Button)findViewById(R.id.main_content_button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view4) {

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);}

        public void click(View view2) {
            int id = view2.getId();
            switch (id) {
                case R.id.main_content_button1:
                    Intent intent = new Intent(MainActivity.this, CombatInformationActivity.class);
                    startActivity(intent);
                    break;
                case R.id.main_content_button2:
            }
        }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        super.onPostCreate(savedInstanceState);
        //Toolbar 必须在onCreate()之后设置标题文本，否则默认标签将覆盖我们的设置
        if (toolbar != null) {//mActionBarToolbar就是android.support.v7.widget.Toolbar
            toolbar.setTitle("");//设置为空，可以自己定义一个居中的控件，当做标题控件使用
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar,menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;



    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_MainPagesettings) {

        } else if (id == R.id.settings) {
            Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_station) {
            Intent intent = new Intent(MainActivity.this,StationActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_info) {
            Intent intent = new Intent(MainActivity.this,Plan.class);
            startActivity(intent);

        } else if (id == R.id.nav_map) {
            Intent intent = new Intent(MainActivity.this,MapActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_tools) {
            Intent intent4 = new Intent(MainActivity.this,ToolsActivity.class);
            startActivity(intent4);

        } else if (id == R.id.nav_settings) {
            Intent intent5 = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(intent5);
        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_about) {
            Intent intent7 = new Intent(MainActivity.this,AboutActivity.class);
            startActivity(intent7);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    private boolean mIsExit;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                this.finish();
            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    }



/*
Metro Master cognac 1.0 preview
Production by CreateON studio
Developer:Konvirs Beliyiet
=￣ω￣=
 */

