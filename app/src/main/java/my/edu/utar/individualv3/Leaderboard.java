package my.edu.utar.individualv3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Leaderboard extends AppCompatActivity {

    private TextView databaseTextView;
    private SQLiteAdapter mySQLiteAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        mySQLiteAdapter = new SQLiteAdapter(this);
        databaseTextView = findViewById(R.id.textView1);
        mySQLiteAdapter.openToRead();
        String result = mySQLiteAdapter.queueAll();
        mySQLiteAdapter.close();
        databaseTextView.setText(result);
    }
}