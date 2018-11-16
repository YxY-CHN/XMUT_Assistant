package xl1712114143.project_final.phone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import xl1712114143.project_final.R;
import xl1712114143.project_final.db.DBHandler;

public class AddPhoneActivity extends AppCompatActivity {
    private Button submit,reset;
    private EditText name,phone,type,keyword;
    private SQLiteDatabase db = PhoneListActivity.myHelper.getReadableDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_phone);
        submit=(Button) findViewById(R.id.submit);
        reset=(Button) findViewById(R.id.reset);
        name=(EditText) findViewById(R.id.name);
        phone=(EditText) findViewById(R.id.phone);
        type=(EditText) findViewById(R.id.type);
        keyword=(EditText) findViewById(R.id.keyword);
        myOnclickListener myOnClickListener=new myOnclickListener();
        submit.setOnClickListener(myOnClickListener);
        reset.setOnClickListener(myOnClickListener);
    }
    private class myOnclickListener implements View.OnClickListener{
        @SuppressLint("WrongConstant")
        public void onClick(View v){
            switch (v.getId()){
                case R.id.submit:
                    DBHandler dbHandler=new DBHandler();
                    String sql="insert into phone_tb values(null,?,?,?,?)";
                    String keywordStr = keyword.getText().toString();
                    if (keywordStr==null || "".equals(keywordStr)){
                        keywordStr=name.getText().toString()+phone.getText().toString();
                    }
                    dbHandler.insert(db,sql,new String[]{
                            name.getText().toString(),phone.getText().toString(),
                            type.getText().toString(),keywordStr
                    });
                    Toast.makeText(AddPhoneActivity.this, "号码添加成功！",1000).show();
                    Intent intent=new Intent(AddPhoneActivity.this,PhoneListActivity.class);
                    startActivity(intent);
                    finish();
                    break;

                case R.id.reset:
                    name.setText("");
                    phone.setText("");
                    type.setText("");
                    keyword.setText("");
                    break;
                    default:
                        break;
            }
        }
    }
}
