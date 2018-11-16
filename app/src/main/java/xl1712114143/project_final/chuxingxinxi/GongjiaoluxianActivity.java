package xl1712114143.project_final.chuxingxinxi;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.busline.BusLineResult;
import com.baidu.mapapi.search.busline.BusLineResult.BusStation;
import com.baidu.mapapi.search.busline.BusLineSearch;
import com.baidu.mapapi.search.busline.BusLineSearchOption;
import com.baidu.mapapi.search.busline.OnGetBusLineSearchResultListener;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.PoiInfo.POITYPE;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import xl1712114143.project_final.R;

public class GongjiaoluxianActivity extends AppCompatActivity{
    private EditText bus, city;
    private MapView mapView;
    private BaiduMap bMap;
    private PoiSearch poiSearch;
    private BusLineSearch busLineSearch;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.gongjiaoluxian);
        bus = (EditText) findViewById(R.id.bus);
        city = (EditText) findViewById(R.id.city);
        mapView = (MapView) findViewById(R.id.bmapView);
        bMap=mapView.getMap();
        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(new MyPoiSearchListener());
        busLineSearch = BusLineSearch.newInstance();
        busLineSearch
                .setOnGetBusLineSearchResultListener(new MyBusLineSearchListener());
        bMap.setOnMarkerClickListener(new OnMarkerClickListener() {

            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(GongjiaoluxianActivity.this, marker.getTitle(),
                        Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    public void search(View view) {
        String cityString = city.getText().toString().trim();
        String busString = bus.getText().toString().trim();
        if ("".equals(cityString) || "".equals(busString)) {
            Toast.makeText(this, "请输入城市和公交路线", Toast.LENGTH_LONG).show();
            return;
        }
        poiSearch.searchInCity(new PoiCitySearchOption().city(cityString)
                .keyword(busString));
    }

    private class MyPoiSearchListener implements OnGetPoiSearchResultListener {

        public void onGetPoiDetailResult(PoiDetailResult result) {

        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }

        public void onGetPoiResult(PoiResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                Toast.makeText(GongjiaoluxianActivity.this, "没有相关结果！",
                        Toast.LENGTH_LONG).show();
                return;
            }
            bMap.clear();
            for (PoiInfo pInfo : result.getAllPoi()) {
                    busLineSearch.searchBusLine(new BusLineSearchOption().city(
                            city.getText().toString()).uid(pInfo.uid));
                    break;
            }

        }

    }

    private class MyBusLineSearchListener implements
            OnGetBusLineSearchResultListener {

        public void onGetBusLineResult(BusLineResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                Toast.makeText(GongjiaoluxianActivity.this, "没有相关结果！",
                        Toast.LENGTH_LONG).show();
                return;
            }
            List<LatLng> points = new ArrayList<LatLng>();
            for (BusLineResult.BusStep step : result.getSteps()) {
                if (step.getWayPoints() != null) {
                    points.addAll(step.getWayPoints());
                }
            }
            if (points.size() > 0) {
                bMap.addOverlay((new PolylineOptions().width(10)
                        .color(Color.argb(178, 0, 78, 255)).zIndex(0)
                        .points(points)));
            }
            List<BusStation> stations=result.getStations();
            for(int i=0;i<stations.size();i++){
                BusStation station=stations.get(i);
                MarkerOptions markerOption = new MarkerOptions()
                        .position(station.getLocation()).title(station.getTitle());
                if(i==0){
                    markerOption.icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.icon_st));
                }else if(i==stations.size()-1){
                    markerOption.icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.icon_en));
                }else{
                    markerOption.icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.my_location));
                }
                bMap.addOverlay(markerOption);
            }
            bMap.animateMapStatus(MapStatusUpdateFactory
                    .newLatLngZoom(stations.get(0).getLocation(),11)); }
    }

    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        poiSearch.destroy();
        busLineSearch.destroy();
    }
}
