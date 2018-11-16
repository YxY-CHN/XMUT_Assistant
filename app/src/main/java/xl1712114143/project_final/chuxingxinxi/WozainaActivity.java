package xl1712114143.project_final.chuxingxinxi;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import xl1712114143.project_final.R;

public class WozainaActivity extends AppCompatActivity {
    private  int[] imageIds={R.drawable.p1,R.drawable.p2,R.drawable.p3,
            R.drawable.p4,R.drawable.p5,R.drawable.p6,R.drawable.p7,R.drawable.p8,R.drawable.p9,
            R.drawable.p10,R.drawable.p11
    };
    private BaiduMap baiduMap;
    private MapView mMapView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.wozaina);
        mMapView=findViewById(R.id.bmapView);
        baiduMap=mMapView.getMap();
        MapStatusUpdate mapStatusUpdate1= MapStatusUpdateFactory.zoomTo(17);
        baiduMap.setMapStatus(mapStatusUpdate1);
        LatLng latLng=new LatLng(24.622054,118.080588);
        MapStatusUpdate mapStatusUpdate2=MapStatusUpdateFactory.newLatLng(latLng);
        baiduMap.setMapStatus(mapStatusUpdate2);
        MarkerOptions markerOptions1=new MarkerOptions();
        ArrayList<BitmapDescriptor> bitmapDescriptors=new ArrayList<>();
        for (int i = 0; i <11 ; i++) {
            BitmapDescriptor bitmapDescriptor= BitmapDescriptorFactory.fromResource(imageIds[i]);
            bitmapDescriptors.add(bitmapDescriptor);
        }

        markerOptions1.position(latLng).icons(bitmapDescriptors).period(2).title("我的位置");
        baiduMap.addOverlay(markerOptions1);
        CircleOptions circleOptions=new CircleOptions();
        circleOptions.center(latLng).radius(300).fillColor(0x50ff0000);
        baiduMap.addOverlay(circleOptions);
        TextOptions textOptions=new TextOptions();
        textOptions.text("我的位置").position(latLng).fontColor(0xff0000ff).fontSize(48).rotate(30);
        baiduMap.addOverlay(textOptions);
        MarkerOptions markerOptions2=new MarkerOptions();
        LatLng latLng2=new LatLng(24.623185,118.080711);
        BitmapDescriptor bitmapDescriptor2=BitmapDescriptorFactory.fromResource(R.drawable.bj);
        markerOptions2.position(latLng2).icon(bitmapDescriptor2).title("食堂");
        baiduMap.addOverlay(markerOptions2);
        MarkerOptions markerOptions3=new MarkerOptions();
        LatLng latLng3=new LatLng(24.624502,118.082692);
        BitmapDescriptor bitmapDescriptor3=BitmapDescriptorFactory.fromResource(R.drawable.bj);
        markerOptions3.position(latLng3).icon(bitmapDescriptor3).title("宿舍楼");
        baiduMap.addOverlay(markerOptions3);
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Button button=new Button(getApplicationContext());
                button.setText(marker.getTitle());
                button.setTextColor(Color.BLACK);
                button.setBackgroundResource(R.drawable.popup);
                BitmapDescriptor bitmapDescriptor=BitmapDescriptorFactory.fromView(button);
                LatLng position=marker.getPosition();
                InfoWindow infoWindow=new InfoWindow(bitmapDescriptor, position, -50, new InfoWindow.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick() {
                        baiduMap.hideInfoWindow();
                    }
                });
                baiduMap.showInfoWindow(infoWindow);
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }
}

