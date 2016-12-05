package xyz.georgihristov.scorecard;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_STROKECOUNT = "STROKE_COUNT";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Holes[] holesArray = new Holes[18];
    private ScoreCardAdapter adapter;
    private ListView golfListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        editor = sharedPreferences.edit();
        golfListView = (ListView) findViewById(R.id.golfListView);

        int score = 0;
        for (int i = 0; i < holesArray.length; i++) {
            score = sharedPreferences.getInt(KEY_STROKECOUNT + i,0);

            holesArray[i] = new Holes("Hole " + (i + 1) + " :", score);

        }

        adapter = new ScoreCardAdapter(holesArray, this);


        golfListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            editor.clear();
            editor.apply();

            for (Holes h : holesArray){
                h.setPointCount(0);
            }
            adapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

        for (int i = 0; i < holesArray.length; i++){
             editor.putInt(KEY_STROKECOUNT + i, holesArray[i].getPointCount());
             }
        editor.apply();
    }
}
