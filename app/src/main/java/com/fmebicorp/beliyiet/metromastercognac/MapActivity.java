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

        import android.content.DialogInterface;
        import android.os.Bundle;
        import android.os.Vibrator;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.text.InputType;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.baidu.location.LocationClient;
        import com.baidu.location.LocationClientOption;
        import com.baidu.mapapi.SDKInitializer;
        import com.baidu.mapapi.map.BaiduMap;
        import com.baidu.mapapi.map.MapView;
        import com.baidu.location.BDLocation;
        import com.baidu.location.BDLocationListener;


public class MapActivity extends AppCompatActivity {

    MapView mMapview = null;
    private String searchText = "";
    private Button startLocation;
    private Button trafficLayer;
    private Button mapLayer;
    private Button searchButton;
    private LocationClient mLocationClient;
    public LocationService locationService;
    private LocationService LocationApplication;
    public Vibrator mVibrator;
    public BaiduMap mBaiduMap;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        mMapview = (MapView) findViewById(R.id.map);
        mBaiduMap = mMapview.getMap();

        startLocation = (Button)findViewById(R.id.button_map_loc);
        mLocationClient = new LocationClient(this);
        mLocationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                StringBuffer sb = new StringBuffer(256);
                sb.append("time : ");
                BDLocation location = new BDLocation();
                sb.append(location.getTime());
                sb.append("\nerror code : ");
                sb.append(location.getLocType());
                sb.append("\nlatitude : ");
                sb.append(location.getLatitude());
                sb.append("\nlontitude : ");
                sb.append(location.getLongitude());
                sb.append("\nradius : ");
                sb.append(location.getRadius());
                if (location.getLocType() == BDLocation.TypeGpsLocation) {
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());
                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());
                    sb.append("\ndirection : ");
                    sb.append("\naddr : ");
                    sb.append(location.getAddrStr());
                    sb.append(location.getDirection());
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                    sb.append("\naddr : ");
                    sb.append(location.getAddrStr());
                    // 运营商信息
                    sb.append("\noperationers : ");
                    sb.append(location.getOperators());
                }
                Log.v("BaiduSdk", sb.toString());
            }
        });

        LocationClientOption option = new LocationClientOption();
        // 设置定位模式,一共三种模式，高精度（使用GPS、网络定位，精度最高），低功耗（仅使用网络定位），仅设备（仅使用GPS定位）
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        // 设置坐标系，gcj02表示国测局加密经纬度坐标，bd0911百度加密经纬度坐标，bd09百度加密墨卡托坐标
        option.setCoorType("bd0911");
        // 设置发起定位请求的间隔时间为1000ms
        option.setScanSpan(1000);
        //设置是否反地理编码，TRUE表示会显示位置的文字信息
        option.setIsNeedAddress(true);

        mLocationClient.setLocOption(option);

        startLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLocationClient.isStarted()) {
                    mLocationClient.stop();
                    startLocation.setText("开始定位");
                } else {
                    mLocationClient.start();
                    startLocation.setText("停止定位");
                }
            }
        });

        final int[] number1 = {0};
        trafficLayer = (Button)findViewById(R.id.button_map_traLayer);
        trafficLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (number1[0] ==0){
                    //交通图开启
                    mBaiduMap.setTrafficEnabled(true);
                    Toast.makeText(MapActivity.this,"交通图开启",Toast.LENGTH_SHORT).show();
                }else if (number1[0] == 1){
                    //交通图关闭
                    mBaiduMap.setTrafficEnabled(false);
                    Toast.makeText(MapActivity.this,"交通图关闭",Toast.LENGTH_SHORT).show();
                }
                number1[0] = (number1[0] + 1)%2;
            }
        });

        final int[] number2 = {0};
        mapLayer = (Button)findViewById(R.id.button_map_layerMode);
        mapLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (number2[0] ==0){
                    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                    Toast.makeText(MapActivity.this,"标准地图",Toast.LENGTH_SHORT).show();
                }else if (number2[0] ==1){
                    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                    Toast.makeText(MapActivity.this,"卫星地图",Toast.LENGTH_SHORT).show();
                }
                number2[0] = (number2[0] + 1)%2;
            }
        });


        ///地点检索用 可输入型弹出框部分///
        ///context:input_dialogbox.xml///
        searchButton = (Button)findViewById(R.id.button_map_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
            builder.setTitle("搜索");
            //设定输入
            final EditText input = new EditText(MapActivity.this);
            //指定输入类型
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
            //设定按钮
                builder.setPositiveButton("搜索", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        searchText = input.getText().toString();
                    }
                });
                builder.show();
            }
        });

    }





/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startLocation = (Button) findViewById(R.id.menu_map_loc);
        int id = item.getItemId();

        if (id == R.id.menu_map_loc) {


        } else if (id == R.id.menu_map_nav) {

            return true;
        }


        return super.onOptionsItemSelected(item);


    }
*/



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapview.onDestroy();

        if (mLocationClient.isStarted()) {
            mLocationClient.stop();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        mMapview.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mMapview.onPause();
    }
}

