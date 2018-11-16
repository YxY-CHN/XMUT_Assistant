package xl1712114143.project_final.phone;

import java.util.ArrayList;
import java.util.Map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import xl1712114143.project_final.R;

public class ResultActivity extends AppCompatActivity {
    private ListView resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        resultList = (ListView) findViewById(R.id.result_list);
        Bundle bundle = getIntent().getExtras();
        ArrayList<Map<String, String>> phoneList = (ArrayList<Map<String, String>>) bundle
                .getSerializable("result");
        System.out.println(phoneList);
        System.out.println(phoneList.size());
        SimpleAdapter adapter = new SimpleAdapter(this, phoneList,
                R.layout.child, new String[] { "name", "phone" }, new int[] {
                R.id.name, R.id.phone });
        resultList.setAdapter(adapter);
    }
}

