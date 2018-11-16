package xl1712114143.project_final.chuxingxinxi;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import xl1712114143.project_final.R;

public class GuanjiandianActivity extends Activity {
    private EditText keyPoint, city;
    private MapView mapView;
    private PoiSearch poiSearch;
    private BaiduMap bMap;
    private int[] markerIds = new int[] { R.drawable.icon_marka,
            R.drawable.icon_markb, R.drawable.icon_markc,
            R.drawable.icon_markd, R.drawable.icon_marke,
            R.drawable.icon_markf, R.drawable.icon_markg,
            R.drawable.icon_markh, R.drawable.icon_marki, R.drawable.icon_markj };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.guanjiandian);
        keyPoint = (EditText) findViewById(R.id.keypoint);
        mapView = (MapView) findViewById(R.id.bmapView);
        bMap = mapView.getMap();
        city = (EditText) findViewById(R.id.city);
        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
                    public void onGetPoiResult(PoiResult result) {
                        if (result == null
                                || result.error != SearchResult.ERRORNO.NO_ERROR) {
                            Toast.makeText(GuanjiandianActivity.this,
                                    "没有相关结果！", Toast.LENGTH_LONG).show();
                            return;
                        }
                        bMap.clear();
                        List<PoiInfo> pInfos = result.getAllPoi();
                        for (int i = 0; i < pInfos.size(); i++) {
                            PoiInfo pInfo = pInfos.get(i);
                            MarkerOptions markerOption = new MarkerOptions()
                                    .position(pInfo.location)
                                    .icon(BitmapDescriptorFactory
                                            .fromResource(markerIds[i]))
                                    .title(pInfo.name);
                            bMap.addOverlay(markerOption);
                        }
                        bMap.animateMapStatus(MapStatusUpdateFactory
                                .newLatLngZoom(pInfos.get(0).location,11));
                    }

                    public void onGetPoiDetailResult(PoiDetailResult result) {

                    }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        });
        bMap.setOnMarkerClickListener(new OnMarkerClickListener() {

            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(GuanjiandianActivity.this, marker.getTitle(),
                        Toast.LENGTH_LONG).show();
                return false;
            }
        });
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
    }

    public void search(View view) {
        String cityString = city.getText().toString().trim();
        String keyPointString = keyPoint.getText().toString().trim();
        if ("".equals(cityString) || "".equals(keyPointString)) {
            Toast.makeText(this, "请输入城市和关键字", Toast.LENGTH_LONG).show();
            return;
        }
        poiSearch.searchInCity(new PoiCitySearchOption().city(cityString)
                .keyword(keyPointString));
    }
}

