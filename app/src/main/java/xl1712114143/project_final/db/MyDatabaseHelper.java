package xl1712114143.project_final.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    final String CREATE_TABLE_SQL=
            "create table phone_tb(_id integer primary " +
                    "key autoincrement,name,phone,type,keyword)";
    public MyDatabaseHelper(Context context, String name,
                            CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
        init(db);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        System.out.println("---------"+oldVersion+"------->"+newVersion);
    }
    public void init(SQLiteDatabase db){
        db.execSQL("insert into phone_tb values (null,'中国电信','10000','学院号码','中国电信公司')");
        db.execSQL("insert into phone_tb values (null,'中国联通','10010','学院号码','中国联通公司')");
        db.execSQL("insert into phone_tb values (null,'中国移动','10086','学院号码','中国移动公司')");
    }
}
