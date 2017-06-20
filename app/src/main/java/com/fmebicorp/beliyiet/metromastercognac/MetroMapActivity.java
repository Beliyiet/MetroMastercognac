package com.fmebicorp.beliyiet.metromastercognac;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.AMap;


import com.amap.api.maps.UiSettings;


public class MetroMapActivity extends Activity implements LocationSource {
    com.amap.api.maps.MapView mapView;
    AMap aMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro_map);
        //获取地图控件引用
        mapView = (com.amap.api.maps.MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mapView.onCreate(savedInstanceState);
        final com.amap.api.maps.AMap aMap = mapView.getMap();
        aMap.showIndoorMap(true);


        UiSettings mUiSettings;//定义一个UiSettings对象
        mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象


        final int[] number = {0};
        ImageButton mapbutton1 = (ImageButton)findViewById(R.id.map_layers);
        mapbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number[0] == 0){
                    aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 设置卫星地图模式
                    Toast.makeText(MetroMapActivity.this,"卫星地图模式",Toast.LENGTH_SHORT).show();
                } else if (number[0] == 1){
                    aMap.setMapType(AMap.MAP_TYPE_NIGHT);//夜景地图
                    Toast.makeText(MetroMapActivity.this,"夜景地图模式",Toast.LENGTH_SHORT).show();
                } else if (number[0] == 2){
                    aMap.setMapType(AMap.MAP_TYPE_NORMAL);//标准地图
                    Toast.makeText(MetroMapActivity.this,"标准地图模式",Toast.LENGTH_SHORT).show();
                }
                number[0] = (number[0] +1)%3;
            }
        });
        

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mapView.onSaveInstanceState(outState);
    }


    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

    }
}
