package xl1712114143.project_final.campuslife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import xl1712114143.project_final.MainActivity;
import xl1712114143.project_final.R;

public class CampusLifeActivity extends AppCompatActivity {
    private Button campusBuild,campusScenery,freshAssist,goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campus_life);
        campusBuild=(Button) findViewById(R.id.campusBuild);
        campusScenery=(Button) findViewById(R.id.campusScenery);
        freshAssist=(Button) findViewById(R.id.freshAssist);
        goBack=(Button) findViewById(R.id.goBack);
        myOnClickListener myListener=new myOnClickListener();
        campusBuild.setOnClickListener(myListener);
        campusScenery.setOnClickListener(myListener);
        freshAssist.setOnClickListener(myListener);
        goBack.setOnClickListener(myListener);
    }

    private class myOnClickListener implements OnClickListener {
        Intent intent=null;
        public void onClick(View v){
            switch (v.getId()){
                case R.id.campusBuild:
                    intent=new Intent(CampusLifeActivity.this,CampusBuildActivity.class);
                    break;
                case R.id.campusScenery:
                    intent=new Intent(CampusLifeActivity.this,CampusSceneryActivity.class);
                    break;
                case R.id.freshAssist:
                    intent=new Intent(CampusLifeActivity.this,FreshAssistActivity.class);
                    break;
                case R.id.goBack:
                    intent=new Intent(CampusLifeActivity.this, MainActivity.class);
                    break;
                    default:
                        break;
            }
            startActivity(intent);
        }
    }
}
