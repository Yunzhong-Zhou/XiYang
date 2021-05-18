package com.xiyang.xiyang.activity;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiSearch;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.utils.MyLogger;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zyz on 2019-11-14.
 */
public class SelectAddressActivity extends BaseActivity {
    private AMap aMap;//地图的控制器类，用来操作地图
    private MapView mMapView;//地图控件
    private GeocodeSearch geocoderSearch;//查询控制器
    PoiSearch.Query query;
    LatLonPoint point = null;
    Marker marker = null;

    String province = "", city = "", cityCode = "", district = "", addr = "", lat = "", lng = "", name = "";

    //顶部 中部
    EditText et_addr;
    ImageView iv_search;

    RecyclerView rv_sarch, rv_addr;
    CommonAdapter<Tip> mAdapter;
    int i = -1, page = 1;

    Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectaddress);
        //初始化地图
        mMapView = (MapView) findViewById(R.id.route_map);
        mMapView.onCreate(savedInstanceState);// 此方法必须重写
        init();//初始化AMap对象,设置点击位置监听
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    protected void initView() {
        et_addr = findViewByID_My(R.id.et_addr);

//        rv_sarch = findViewByID_My(R.id.rv_sarch);
//        rv_sarch.setLayoutManager(new LinearLayoutManager(this));
        rv_addr = findViewByID_My(R.id.rv_addr);
        rv_addr.setLayoutManager(new LinearLayoutManager(this));
        rv_addr.setVisibility(View.GONE);

        et_addr.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    et_addr.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(final Editable s) {
                            if (runnable != null) {
                                handler.removeCallbacks(runnable);
                                Log.v("tag", "---" + s.toString());
                            }
                            runnable = new Runnable() {
                                @Override
                                public void run() {
                                    MyLogger.i("tag", "输入>>>>>>" + s.toString());
                                    if (!et_addr.getText().toString().trim().equals("")) {

                                        request(et_addr.getText().toString().trim());

                                    }
                                }
                            };
                            handler.postDelayed(runnable, 800);
                        }
                    });
                }
            }
        });

        et_addr.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //关闭软键盘
                    hideInput();
                    //do something
                    //doSearch();
                    MyLogger.i(">>>>>>>>输入后：" + et_addr.getText().toString().trim());
                    if (!et_addr.getText().toString().trim().equals("")) {
                        request(et_addr.getText().toString().trim());
                    }
                    return true;
                }
                return false;
            }
        });
        iv_search = findViewByID_My(R.id.iv_search);
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void initData() {
    }

    private void request(String keyWord) {
       /* query = new PoiSearch.Query(keyWord, "", "");
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(page);//设置查询页码

        PoiSearch poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
            @Override
            public void onPoiSearched(PoiResult poiResult, int i) {
                MyLogger.i(">>>>>>>"+poiResult.getPois().size());
                showMapAddr(poiResult.getPois());
            }

            @Override
            public void onPoiItemSearched(PoiItem poiItem, int i) {
                MyLogger.i(">>>>>>>"+poiItem.getAdName());

            }
        });
        poiSearch.searchPOIAsyn();*/
        //第二个参数传入null或者“”代表在全国进行检索，否则按照传入的city进行检索
        InputtipsQuery inputquery = new InputtipsQuery(keyWord, city);
        inputquery.setCityLimit(true);//限制在当前城市
        Inputtips inputTips = new Inputtips(SelectAddressActivity.this, inputquery);
        inputTips.setInputtipsListener(new Inputtips.InputtipsListener() {
            @Override
            public void onGetInputtips(List<Tip> list, int i) {
                if (list.size() > 0) {
                    //显示弹窗
                    showMapAddr(list);
                }
            }
        });
        inputTips.requestInputtipsAsyn();
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(30 * 1000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);//只定位一次。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次，且将视角移动到地图中心点。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW) ;//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);//连续定位、且将视角移动到地图中心点，地图依照设备方向旋转，定位点会跟随设备移动。（1秒1次定位）
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
        //以下三种模式从5.1.0版本开始提供
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，定位点依照设备方向旋转，并且蓝点会跟随设备移动。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，并且蓝点会跟随设备移动。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，地图依照设备方向旋转，并且蓝点会跟随设备移动。

        myLocationStyle.showMyLocation(true);//显示自己的位置

        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.getUiSettings().setScaleControlsEnabled(true);//控制比例尺控件是否显示

        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
//                latitude = location.getLatitude();
//                longititude = location.getLongitude();

                //获取位置信息
                point = new LatLonPoint(location.getLatitude(), location.getLongitude());
                // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
                RegeocodeQuery query = new RegeocodeQuery(point, 200, GeocodeSearch.AMAP);
                geocoderSearch.getFromLocationAsyn(query);


                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());//构造一个位置
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));//设置地图放大级别

            }
        });
        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
//                latitude = latLng.latitude;
//                longititude = latLng.longitude;
                //获取位置信息
                point = new LatLonPoint(latLng.latitude, latLng.longitude);
                // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
                RegeocodeQuery query = new RegeocodeQuery(point, 200, GeocodeSearch.AMAP);
                geocoderSearch.getFromLocationAsyn(query);
            }
        });
        //坐标转地址
        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
                //解析result获取地址描述信息
                if (rCode == 1000) {
                    //成功
                    if (result != null && result.getRegeocodeAddress() != null
                            && result.getRegeocodeAddress().getFormatAddress() != null) {
//                        addr = result.getRegeocodeAddress().getFormatAddress(); // 逆转地里编码不是每次都可以得到对应地图上的opi
                        province = result.getRegeocodeAddress().getProvince();//省
                        city = result.getRegeocodeAddress().getCity();//市
                        district = result.getRegeocodeAddress().getDistrict();//区
                        cityCode = result.getRegeocodeAddress().getCityCode();//市代码

                        MyLogger.i(">>选取地址>>>>" + result.getRegeocodeAddress().getFormatAddress());

//                        lat = result.getRegeocodeQuery().getPoint().getLatitude() + "";
//                        lng = result.getRegeocodeQuery().getPoint().getLongitude() + "";

                        //移动到指定点
                        LatLng latLng = new LatLng(result.getRegeocodeQuery().getPoint().getLatitude(),
                                result.getRegeocodeQuery().getPoint().getLongitude());
                        //设置中心点
                        aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));

                        //设置marker
                        if (marker != null){
                            marker.remove();
                        }
                        marker = aMap.addMarker(new MarkerOptions().position(latLng)
                                .title(name)
                                .snippet(addr)
                                .visible(true));
                    }

                }
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int rCode) {

            }
        });


    }

    @Override
    protected void updateView() {
        titleView.setTitle("添加门店");
        titleView.showRightTextview("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void showMapAddr(List<Tip> tips) {
        i = -1;
        mAdapter = new CommonAdapter<Tip>
                (SelectAddressActivity.this, R.layout.item_selectaddr, tips) {
            @Override
            protected void convert(ViewHolder holder, Tip model, int position) {
                holder.setText(R.id.name, model.getName());
                holder.setText(R.id.addr, model.getAddress());
                ImageView imageView = holder.getView(R.id.imageView);
                if (i == position) {
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    imageView.setVisibility(View.GONE);
                }

                holder.getView(R.id.shadowLayout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyLogger.i(">>>>>>" + model.getAddress());
                        name = model.getName();
                        addr = model.getAddress();

                        lat = model.getPoint().getLatitude() + "";
                        lng = model.getPoint().getLongitude() + "";

                        i = position;
                        mAdapter.notifyDataSetChanged();

                        //获取位置信息
                        point = model.getPoint();

                        // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
                        RegeocodeQuery query = new RegeocodeQuery(point, 200, GeocodeSearch.AMAP);
                        geocoderSearch.getFromLocationAsyn(query);
                    }
                });
            }
        };

        rv_addr.setAdapter(mAdapter);
        rv_addr.setVisibility(View.VISIBLE);
    }


    /**
     * 隐藏键盘
     */
    protected void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

}
