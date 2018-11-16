package xl1712114143.project_final.chuxingxinxi;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;

import xl1712114143.project_final.MainActivity;
import xl1712114143.project_final.R;

public class ChuxingxinxiActivity extends AppCompatActivity {
    private Button xianluchaxun, wozaina, guanjiandian, goBack;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chuxingxinxi);
        xianluchaxun = (Button) findViewById(R.id.xianluchaxun);
        wozaina = (Button) findViewById(R.id.wozaina);
        guanjiandian = (Button) findViewById(R.id.guanjiandian);
        goBack = (Button) findViewById(R.id.back);
        MyOnClickListener myListener = new MyOnClickListener();
        goBack.setOnClickListener(myListener);
        xianluchaxun.setOnClickListener(myListener);
        wozaina.setOnClickListener(myListener);
        guanjiandian.setOnClickListener(myListener);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

    }

    private class MyOnClickListener implements OnClickListener {
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.xianluchaxun:
                    intent = new Intent(ChuxingxinxiActivity.this, GongjiaoluxianActivity.class);
                    break;
                case R.id.wozaina:
                    intent = new Intent(ChuxingxinxiActivity.this, WozainaActivity.class);
                    break;
                case R.id.guanjiandian:
                    intent = new Intent(ChuxingxinxiActivity.this, GuanjiandianActivity.class);
                    break;
                case R.id.back:
                    intent = new Intent(ChuxingxinxiActivity.this, MainActivity.class);
                    break;
                default:
                    intent = new Intent(ChuxingxinxiActivity.this, MainActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(this, "你拒绝了相关权限授权，无法获取位置！", Toast.LENGTH_SHORT).show();
            }
        }

    }
}

