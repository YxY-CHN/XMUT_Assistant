package xl1712114143.project_final.phone;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xl1712114143.project_final.R;
import xl1712114143.project_final.db.DBHandler;
import xl1712114143.project_final.db.MyDatabaseHelper;

public class PhoneListActivity extends AppCompatActivity {
    public static MyDatabaseHelper myHelper;
    private EditText keyword;
    private Button query;
    private DBHandler dbHandler=new DBHandler();
    private SQLiteDatabase db;
    private ExpandableListView mExpandableList;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.phone_list);
        mExpandableList=(ExpandableListView)findViewById(R.id.mExpandableList);
        myHelper=new MyDatabaseHelper(this,"phone.db",null,1);
        db=myHelper.getReadableDatabase();
        String sql="select distinct type from phone_tb";
        ArrayList<String> type=dbHandler.getType(db,sql);
        ArrayList<Map<String,String>> groups=new ArrayList<Map<String,String>>();
        ArrayList<List<Map<String,String>>> children=new ArrayList<List<Map<String,String>>>();
        for (String str : type){
            Map<String,String> item=new HashMap<String,String>();
            item.put("group",str);
            groups.add(item);
            ArrayList<Map<String,String>> child=dbHandler.getData(db,
                    "select name,phone from phone_tb where type=?",new String[]{str});
            System.out.println(child);
            children.add(child);
        }
        SimpleExpandableListAdapter simpleExpandableListAdapter=new SimpleExpandableListAdapter(
                this,groups,R.layout.group,new String[]{"group"},
                new int[]{R.id.group},children,R.layout.child,
                new String[]{"name","phone"},new int[]{R.id.name,R.id.phone}
        );
        mExpandableList.setAdapter(simpleExpandableListAdapter);
        keyword = (EditText) findViewById(R.id.keyword);
        query = (Button) findViewById(R.id.query);
        query.setOnClickListener(new OnClickListener(){
            String sql="select name,phone from phone_tb where keyword like ?";

            public void onClick(View v){
                ArrayList<Map<String,String>> phoneList=dbHandler.getData(db,sql,
                        new String[]{"%"+keyword.getText().toString()+"%"});
                Intent intent=new Intent(PhoneListActivity.this,ResultActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("result",phoneList);
                System.out.println("phoneList="+phoneList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.phone_manager,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.addphone:
                Intent intent=new Intent(PhoneListActivity.this,AddPhoneActivity.class);
                startActivity(intent);
                break;
            case R.id.exit:
                this.finish();
                break;

                default:
                    break;
        }
        return super.onOptionsItemSelected(item);
    }
}
