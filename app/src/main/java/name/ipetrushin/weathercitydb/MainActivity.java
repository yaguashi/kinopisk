package name.ipetrushin.weathercitydb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {
    SQLiteDatabase db;
    SQLiteDatabase db_read;
    DBHelper dbHelper;
    ListView citiesList;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        citiesList = findViewById(R.id.citiesList);

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        dbHelper.onCreate(db);
        db_read = dbHelper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM cities2;", null);
        String[] fields = {"_id", "name", "country"};
        int[] resIds = {R.id.id, R.id.city_name, R.id.country};
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item, c, fields, resIds,0);
        citiesList.setAdapter(adapter);
        citiesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView t_id = (TextView)view.findViewById(R.id.id);
                db.execSQL("DELETE FROM cities2 WHERE _id="+t_id.getText().toString()+";");
                ((SimpleCursorAdapter)citiesList.getAdapter()).notifyDataSetChanged();
                update();
            }
        });
        button=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                List<String> list = new ArrayList<>();
                Cursor cursor = db_read.rawQuery("SELECT name from cities2  ",null);
                if (cursor.moveToFirst()){
                    list.add(cursor.getString(0));
                    while(cursor.moveToNext()){
                        list.add(cursor.getString(0));
                    }
                }
                Random rand = new Random();
                builder.setTitle("ПРЕДЛАГАЮ ФИЛЬМ!")
                        .setMessage(list.get(rand.nextInt(list.size())))
                        .setCancelable(false)
                        .setNegativeButton("ОК, подумаю",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });



    }
    public void update(){
        citiesList = findViewById(R.id.citiesList);
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM cities2;", null);
        String[] fields = {"_id", "name", "country"};
        int[] resIds = {R.id.id, R.id.city_name, R.id.country};
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item, c, fields, resIds,0);
        adapter.notifyDataSetChanged();
        citiesList.setAdapter(adapter);
        citiesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView t_id = (TextView)view.findViewById(R.id.id);
                db.execSQL("DELETE FROM cities2 WHERE _id="+t_id.getText().toString()+";");
                ((SimpleCursorAdapter)citiesList.getAdapter()).notifyDataSetChanged();
                update();
            }
        });
    }
}
