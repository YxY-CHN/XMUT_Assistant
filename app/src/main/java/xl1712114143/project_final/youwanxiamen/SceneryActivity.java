package xl1712114143.project_final.youwanxiamen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import xl1712114143.project_final.R;

public class SceneryActivity extends AppCompatActivity {
    int[] images = new int[] { R.drawable.gulangyu,
            R.drawable.nanputuo, R.drawable.riguangyan,
            R.drawable.zengcuoan, R.drawable.zhongshanlu,};
    String[] names = new String[] { "鼓浪屿", "南普陀寺", "日光岩", "曾厝垵", "中山路",};
    String[] briefs = new String[] { "国家5A级旅游景区 世界文化遗产", "厦门八大风景区之一",
            "全国AAAA级景区", "中国最文艺渔村", "中国历史文化名街" };
    String[] contents = new String[] {
            "\t\t\t鼓浪屿原名“圆沙洲”，别名“圆洲仔”，南宋时期命“五龙屿”，明朝改称“鼓浪屿”。\n\t\t\t因岛西南方海滩上有一块两米多高、中有洞穴的礁石，每当涨潮水涌，浪击礁石，声似擂鼓，人们称“鼓浪石”，鼓浪屿因此而得名。因涨潮水涌，浪击礁石，声似擂鼓而得名。鼓浪屿街道短小，纵横交错，是厦门最大的一个屿。",
            "\t\t\t南普陀寺位于福建省厦门市东南五老峰下，面临碧澄海港，该寺占地面积25.8万平方米，建筑面积2.1270万平方米。\n\t\t\t始建于唐朝末年，称为泗洲寺，宋治平年间改名为普照寺，明朝初年，寺院荒芜，直到清朝康熙年间才得到重建。因其供奉观世音菩萨，与浙江普陀山观音道场类似，又在普陀山以南而得名“南普陀寺”，为闽南佛教胜地之一。",
            "\t\t\t日光岩俗称“岩仔山”，别名“晃岩”，相传1641年，郑成功来到晃岩，看到这里的景色胜过日本的日光山，便把“晃”字拆开，称之为“日光岩”。\n\t\t\t日光岩游览区由日光岩和琴园两个部分组成。日光岩耸峙于鼓浪屿中部偏南，是由两块巨石一竖一横相倚而立，成为龙头山的顶峰，海拔92.7米，为鼓浪屿最高峰。",
            "\t\t\t曾厝垵，中国最文艺渔村，为“曾厝垵文创村”的简称。别名“曾里”，又称“曾家沃”、“曾家湾”，位于厦门岛东南部，有兔耳岭之草，太姥山之石，火山岛之礁，自然人文为一体。至今已有八百多年历史。" + "\n\t\t\t作为景区的曾厝垵文创村只占0.33平方公里。",
            "\t\t\t中山路是厦门最老牌的商业街，人流旺，商品多，名气大，不论往昔还是如今，人们一提及厦门，就言必中山路，好似纽约的曼哈顿、东京的银座、香港的中环。到厦门，中山路是不能不去的，因为它代表了厦门的繁华，富有和时代韵律。到这里可享受丰富的物质世界，领略现代风采。中山路是厦门人的骄傲！"};

    private ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scenery);
        myList = (ListView) findViewById(R.id.sceneryList);
        ArrayList<Map<String, String>> sceneryList = new ArrayList<Map<String, String>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, String> sceneryItem = new HashMap<String, String>();
            sceneryItem.put("name", names[i]);
            sceneryItem.put("brief", briefs[i]);
            sceneryItem.put("image", images[i] + "");
            sceneryList.add(sceneryItem);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, sceneryList,
                R.layout.scenery_item,
                new String[] { "image", "name", "brief" }, new int[] {
                R.id.image, R.id.name, R.id.brief });
        myList.setAdapter(adapter);
        myList.setOnItemClickListener(new myOnItemClickListener());

    }
    private class myOnItemClickListener implements OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Intent intent = new Intent();
            intent.setClass(SceneryActivity.this, SceneryShowActivity.class);
            intent.putExtra("image", images[position]);
            intent.putExtra("content", contents[position]);
            startActivity(intent);
        }
    }
}
