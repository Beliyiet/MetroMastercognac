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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by BELIYIET on 2017/9/20.
 */

public class MyDatabaseHelpler extends SQLiteOpenHelper{

    private static final String DATABASENAME = "ourmap.db";
    private static final int DATABASEVERSION = 1;
    private static final String TABLENAME = "ourmap";


    public MyDatabaseHelpler(Context context) {

        super(context, DATABASENAME, null, DATABASEVERSION);
// TODO Auto-generated constructor stub

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

// TODO Auto-generated method stub

        String sql = "CREATE TABLE " + TABLENAME + "(" +
                "_id integer primary key autoincrement,"+
                "Address varchar(50)," +        //地址名称
                "Latitude double," +                        //纬度
                "Longitude double )";                       //经度
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

// TODO Auto-generated method stub
        String sql = "drop table if exists" + TABLENAME;
        db.execSQL(sql);
        this.onCreate(db);

    }


    public void insert(String address,double latitude,double longitude)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "insert into " + TABLENAME + "(Address,Latitude,Longitude) values('" + address + "','" + latitude + "','" + longitude + "')";
        db.execSQL(sql);
        db.close();

    }

    public void delete(String address)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from " + TABLENAME + "where Address=" + address;
        db.execSQL(sql);
        db.close();

    }

    public void search(String sql)
    {

        SQLiteDatabase dbb = this.getWritableDatabase();
        dbb.execSQL(sql);
        dbb.close();

    }
}
