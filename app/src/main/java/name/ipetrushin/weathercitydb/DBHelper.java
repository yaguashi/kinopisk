package name.ipetrushin.weathercitydb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    final static String DB_NAME = "citydb.db";
    final static String TABLE_NAME = "cities2";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_NAME + " (_id INT PRIMARY KEY, " +
                                                    "name VARCHAR(30) NOT NULL, " +
                                                    "country VARCHAR(3) NOT NULL)");
        db.execSQL("INSERT OR REPLACE INTO  "+ TABLE_NAME + " VALUES (473537, \"Люди в чорном\", \"RU\");");
        db.execSQL("INSERT OR REPLACE INTO "+ TABLE_NAME + " VALUES (2023469, \"Ну погоди\", \"RU\");");
        db.execSQL("INSERT OR REPLACE INTO "+ TABLE_NAME + " VALUES (4735371, \"Крепкий орешек\", \"RU\");");
        db.execSQL("INSERT OR REPLACE INTO "+ TABLE_NAME + " VALUES (2023111, \"Матрица\", \"RU\");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
