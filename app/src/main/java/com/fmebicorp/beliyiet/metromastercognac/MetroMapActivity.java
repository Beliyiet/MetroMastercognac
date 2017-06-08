package com.fmebicorp.beliyiet.metromastercognac;

import android.app.Activity;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;


public class MetroMapActivity extends Activity {
    com.amap.api.maps2d.MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro_map);
        //获取地图控件引用
        mapView = (com.amap.api.maps2d.MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mapView.onCreate(savedInstanceState);

    }



}
