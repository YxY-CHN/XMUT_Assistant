package xl1712114143.project_final.campuslife;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

import xl1712114143.project_final.R;

public class CampusSceneryActivity extends AppCompatActivity {
    int[] images=new int[]{R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,
            R.drawable.five,R.drawable.six,R.drawable.seven,R.drawable.eight
    };
    private Button goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campus_scenery);
        goBack=(Button)findViewById(R.id.goBack);
        goBack.setOnClickListener(new OnClickListener() {
            public void onClick(View v){
                Intent intent=new Intent(CampusSceneryActivity.this,CampusLifeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        final Gallery gallery=(Gallery) findViewById(R.id.gallery);
        final ImageSwitcher switcher=(ImageSwitcher)findViewById(R.id.switcher2);
        switcher.setFactory(new ViewFactory(){
            public View makeView(){
                ImageView imageView=new ImageView(CampusSceneryActivity.this);
                imageView.setBackgroundColor(0xff0000);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT
                ));
                return imageView;
            }
        });
        switcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        switcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));
        BaseAdapter adapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView=new ImageView(CampusSceneryActivity.this);
                imageView.setImageResource(images[position % images.length]);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(new Gallery.LayoutParams(200,300));
                return imageView;
            }
        };
        gallery.setAdapter(adapter);
        gallery.setOnItemSelectedListener(new OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0,View arg1,int position,long id){
                switcher.setImageResource(images[position%images.length]);
            }
            public void onNothingSelected(AdapterView<?> arg0){

            }
        });
    }
}
