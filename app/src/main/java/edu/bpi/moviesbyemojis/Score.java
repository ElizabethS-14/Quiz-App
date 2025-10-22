package edu.bpi.moviesbyemojis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Score extends AppCompatActivity {

    TextView TitleEnd;

    TextView FinalScore;

    TextView ScoreNumber;

    Button Restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_score);

        TitleEnd = (TextView) findViewById(R.id.TitleEnd);
        FinalScore = (TextView) findViewById(R.id.FinalScore);
        ScoreNumber = (TextView) findViewById(R.id.ScoreNumber);
        Restart = (Button) findViewById(R.id.Restart);

        Intent intent = getIntent();
        //grabs the value of score and totalQuestions from the other class
        int score = intent.getIntExtra("score", 0);
        int totalQuestions = intent.getIntExtra("totalQuestions", 0);
        ScoreNumber.setText(score + " / " + totalQuestions);

//When button is clicked, it takes u back to the main home page to try the quiz again
        Restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Score.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}