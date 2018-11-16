package xl1712114143.project_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import xl1712114143.project_final.campuslife.CampusLifeActivity;
import xl1712114143.project_final.chuxingxinxi.ChuxingxinxiActivity;
import xl1712114143.project_final.phone.PhoneListActivity;
import xl1712114143.project_final.youwanxiamen.SceneryActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int[] btnIDs=new int[]{
                R.id.phoneAssist,R.id.campusLife,R.id.scenery,R.id.trafficAssist
        };
        Button[] buttons=new Button[btnIDs.length];
        MyOnClickListener myOnClickListener=new MyOnClickListener();
        for (int i=0;i<btnIDs.length;i++){
            buttons[i]=(Button) findViewById(btnIDs[i]);
            buttons[i].setOnClickListener(myOnClickListener);
        }
    }
    public class MyOnClickListener implements View.OnClickListener{
        Intent intent=null;
        public void onClick(View v){
            switch(v.getId()){
               case R.id.phoneAssist:
                    intent=new Intent(MainActivity.this,PhoneListActivity.class);
                    break;
               case R.id.trafficAssist:
                    intent=new Intent(MainActivity.this,ChuxingxinxiActivity.class);
                    break;
                case R.id.campusLife:
                    intent=new Intent(MainActivity.this,CampusLifeActivity.class);
                    break;
               case  R.id.scenery:
                    intent=new Intent(MainActivity.this,SceneryActivity.class);
                    break;
                    default:
                        break;
            }
            startActivity(intent);
        }
    }
}
