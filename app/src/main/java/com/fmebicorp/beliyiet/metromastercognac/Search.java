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

/**
 * Created by BELIYIET on 2017/9/20.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;

public class Search extends FragmentActivity implements OnGetPoiSearchResultListener,OnGetSuggestionResultListener{

    String searchContent;
    public EditText searchet2;

    public Button startSearchbtn;
    public Button nextbtn;

    private PoiSearch mPoiSearch = null;
    private SuggestionSearch mSuggestionSearch = null;
    private MapView mapView;
    private BaiduMap mBaiduMap = null;

    private int load_Index = 0;

    @Override
    protected void onCreate(Bundle savedInstenceState) {

        super.onCreate(savedInstenceState);
        setContentView(R.layout.search);
        searchet2 = (EditText)findViewById(R.id.searchkey);

        // 初始化搜索模块，注册搜索事件监听
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        mSuggestionSearch = SuggestionSearch.newInstance();
        mSuggestionSearch.setOnGetSuggestionResultListener(this);
        mapView = (MapView)findViewById(R.id.map);
        mBaiduMap = mapView.getMap();
        mapView.showZoomControls(false);

        // //设置刚启动时地图的默认中心点以及缩放级别
        double latitude = 34.237171;
        double longitude = 108.923064;
        LatLng xdLatLng = new LatLng(latitude, longitude);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLngZoom(xdLatLng, 15.0f);
        mBaiduMap.setMapStatus(msu);

        // 开始搜索数据
        startSearchbtn = (Button) findViewById(R.id.startsearch);
        startSearchbtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startSearch(v);
            }
        });

        // 查找下一组数据
        nextbtn = (Button) findViewById(R.id.map_next_data);
        nextbtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                goToNextPage(v);
            }
        });



    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mPoiSearch.destroy();
        mSuggestionSearch.destroy();
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
    }

    private void goToNextPage(View v) {
        load_Index++;
        mPoiSearch.searchInCity((new PoiCitySearchOption()).city("上海").keyword(searchet2.getText().toString()).pageNum(load_Index));
    }

    private void startSearch(View v) {

        load_Index = 0;
        mPoiSearch.searchInCity((new PoiCitySearchOption()).city("上海").keyword(searchet2.getText().toString()).pageNum(load_Index));
    }

    @Override
    public void onGetPoiResult(PoiResult result) {

        if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {

            Toast.makeText(Search.this, "未找到结果", Toast.LENGTH_LONG).show();
            return;

        }
        /*
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {

            mBaiduMap.clear();
            PoiOverlay overlay = new MyPoiOverlay(mBaiduMap);
            mBaiduMap.setOnMarkerClickListener(overlay);
            overlay.setData(result);
            overlay.addToMap();
            overlay.zoomToSpan();
            return;

        }
        */

    }

    @Override
    public void onGetPoiDetailResult(final PoiDetailResult result) {

        if (result.error != SearchResult.ERRORNO.NO_ERROR) {

            Toast.makeText(Search.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();

        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(result.getName() + ": " + result.getAddress());
            builder.setNegativeButton("取消", null);
            builder.setPositiveButton("收藏", new DialogInterface.OnClickListener() {


                @Override
                public void onClick(DialogInterface dialog, int which) {

                    MyDatabaseHelpler helper = new MyDatabaseHelpler(Search.this);
                    helper.insert(result.getName(), result.getLocation().latitude, result.getLocation().longitude);

                }

            });
            builder.show();

        }
    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }

    @Override
    public void onGetSuggestionResult(SuggestionResult suggestionResult) {

    }

    private class MyPoiOverlay extends PoiOverlay {
        public MyPoiOverlay(BaiduMap mBaiduMap) {
            super(mBaiduMap);
        }
    }

    private class PoiOverlay implements BaiduMap.OnMarkerClickListener {
        private PoiResult data;

        public PoiOverlay(BaiduMap mBaiduMap) {

        }

        public void setData(PoiResult data) {
            this.data = data;
        }

        @Override
        public boolean onMarkerClick(Marker marker) {
            return false;
        }
    }
}
