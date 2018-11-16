package xl1712114143.project_final.youwanxiamen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import xl1712114143.project_final.R;

public class SceneryShowActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView content;
    private Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scenery_show);
        imageView=(ImageView)findViewById(R.id.image);
        content=(TextView)findViewById(R.id.content);
        goBack=(Button)findViewById(R.id.goBack);
        int image=getIntent().getIntExtra("image",R.drawable.gulangyu);
        String show=getIntent().getStringExtra("content");
        System.out.println(show);
        imageView.setBackgroundResource(image);
        content.setText(show);
        goBack.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(SceneryShowActivity.this,SceneryActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

