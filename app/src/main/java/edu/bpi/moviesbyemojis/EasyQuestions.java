package edu.bpi.moviesbyemojis;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EasyQuestions extends AppCompatActivity {


    TextView QuestionsE;

    TextView EmojisE;

    Button choice1E;

    Button choice2E;

    Button choice3E;

    Button choice4E;

    private int currentQuestionE = 0;
    private int scoreE = 0;

    String[] emojiQuestionsE = {"🧙🍎👩🏻","👠🫅🏻🎃","🧞‍♂️🐒🏰","🐠🔍🐢","🚗🛻"};
    String[][] answerChoicesE = {{"Beauty and the Beast","TinkerBell","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""}};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_easy_questions);



    }
}