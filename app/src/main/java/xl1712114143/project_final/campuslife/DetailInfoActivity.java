package xl1712114143.project_final.campuslife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import xl1712114143.project_final.R;

public class DetailInfoActivity extends AppCompatActivity {
    private TextView info;
    private Button goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_info);
        String detail=getIntent().getStringExtra("info");
        info=(TextView)findViewById(R.id.info);
        goBack=(Button)findViewById(R.id.goBack);
        info.setText(detail);
        goBack.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(DetailInfoActivity.this,FreshAssistActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
