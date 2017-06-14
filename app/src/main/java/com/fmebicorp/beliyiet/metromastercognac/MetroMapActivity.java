package com.fmebicorp.beliyiet.metromastercognac;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;


public class MetroMapActivity extends Activity {
    com.amap.api.maps.MapView mapView;
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






}
