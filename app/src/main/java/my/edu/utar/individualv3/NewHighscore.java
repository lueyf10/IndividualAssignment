package my.edu.utar.individualv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewHighscore extends AppCompatActivity {

    private EditText usernameEditText;
    private Button submitButton;
    private int score;
    private int level;
    private SQLiteAdapter mySQLiteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_highscore);
        //Initialize intent
        Intent intent = getIntent();
        score = intent.getIntExtra("score",0);
        level = intent.getIntExtra("level",0);

        mySQLiteAdapter = new SQLiteAdapter(this);
        mySQLiteAdapter.openToWrite();

        usernameEditText = findViewById(R.id.usernameEditText);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySQLiteAdapter.insert(usernameEditText.getText().toString(), level, score);

                // Create new intent and add username to it
                Intent nextIntent = new Intent(NewHighscore.this, MainActivity.class);
                startActivity(nextIntent);
            }
        });
    }
}