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

    Button emailButton;

    EditText emailInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_score);

        TitleEnd = (TextView) findViewById(R.id.TitleEnd);
        FinalScore = (TextView) findViewById(R.id.FinalScore);
        ScoreNumber = (TextView) findViewById(R.id.ScoreNumber);
        Restart = (Button) findViewById(R.id.Restart);
        emailButton = (Button) findViewById(R.id.emailButton);
        emailInput = (EditText)findViewById(R.id.emailInput);

        Intent intent = getIntent();
        //grabs the value of score, totalQuestions, and difficulty from the other class
        int score = intent.getIntExtra("score", 0);
        int totalQuestions = intent.getIntExtra("totalQuestions", 0);
        String difficulty = getIntent().getStringExtra("difficulty");
        ScoreNumber.setText(score + " / " + totalQuestions);


        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmailScore(score, totalQuestions, difficulty);
            }
        });


//When button is clicked, it takes u back to the main home page to try the quiz again
        Restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Score.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
    private void sendEmailScore(int score, int totalQuestions, String difficulty){

        //Get the email the user typed
        String userEmail = emailInput.getText().toString().trim();

        //Calculate %
        int percentage = (score * 100)/totalQuestions;

        //Email Subject
        String subject ="My Movies by Emoji Quiz Score!";

        //Email Body
        String body =getString(R.string.email_intro) +
                getString(R.string.email_title)  +
                getString(R.string.email_difficulty, difficulty)  +
                getString(R.string.email_score, score, totalQuestions) +
                getString(R.string.email_percentage, percentage) +
                getString(R.string.email_thanks);

        //Saying I want to send something to the code
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        //Supposed to work better with new lines
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{userEmail});
        //Put the text in subject in the subject line of the email
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        //Put the text in body as the main text of the email
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        //Opens a menu showing email apps on the phone
        startActivity(Intent.createChooser(emailIntent, "Send email using"));
    }
}
//"Score: " + score + " out of " + totalQuestions + "                          " +
//                "Percent: " + percentage + "%                                                            " +
//                "Thank you for playing! Hope you enjoyed!";