package edu.bpi.moviesbyemojis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MediumQuestions extends AppCompatActivity {

    TextView Title;

    TextView Emojis;

    Button choice1;

    Button choice2;

    Button choice3;

    Button choice4;

    private int currentQuestion = 0;
    private int score = 0;

    String[] emojiQuestions = {"🎈👴🏠🚲","🦁👑🌅🐘","🧊❄️👭⛄","🕷️🧑‍🎓🏙️","🚢💔🌊🎻"};

    String[][] answerChoices ={{"IT", "Up","Toy Story","Home Alone"},{"Jungle Book", "The Lion King","Madagascar","Tarzan"},{"Frozen", "Ice Age","Encanto","Tangled"},{"Ant-Man", "Spider-Man","Batman","Iron Man"},{"Titanic", "Moana","Shipwrecked","The Perfect Storm"}};

    String[] answers = {"Up","The Lion King","Frozen","Spider-Man","Titanic"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_questions);

        Title = (TextView) findViewById(R.id.Title);
        Emojis = (TextView) findViewById(R.id.Emojis);
        choice1 = (Button) findViewById(R.id.choice1);
        choice2 = (Button) findViewById(R.id.choice2);
        choice3 = (Button) findViewById(R.id.choice3);
        choice4 = (Button) findViewById(R.id.choice4);

        theQuestions();

//creates a listener obj to handle clicks for all 4 buttons
        View.OnClickListener answerClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //turns View v into a button since its going to be one of the 4 buttons
                Button buttonClicked = (Button) v;
                //takes the text of whatever button is clicked and stores it into a variable
                String selected = buttonClicked.getText().toString();
                checkAnswer(selected);
            }
        };
        //now each button will run on the same onClick
        choice1.setOnClickListener(answerClick);
        choice2.setOnClickListener(answerClick);
        choice3.setOnClickListener(answerClick);
        choice4.setOnClickListener(answerClick);
    }

    private void theQuestions(){
        Title.setText("Question " + (currentQuestion + 1) + " of " + emojiQuestions.length);
        Emojis.setText(emojiQuestions[currentQuestion]);

        choice1.setText(answerChoices[currentQuestion][0]);
        choice2.setText(answerChoices[currentQuestion][1]);
        choice3.setText(answerChoices[currentQuestion][2]);
        choice4.setText(answerChoices[currentQuestion][3]);
    }

    private void checkAnswer(String selected){
        //checks if the button pressed is equal to the answer to the question
        if(selected.equals(answers[currentQuestion])){
            score++;
        }

        currentQuestion++;
        //added to current question so after the button is pressed, you go to the next question if the length is less than the array
        if(currentQuestion < emojiQuestions.length){
            theQuestions();
        } else{
            Intent intent = new Intent(MediumQuestions.this, Score.class);
           //carries over the score and # of questions so the other screen
            intent.putExtra("score", score);
            intent.putExtra("totalQuestions", emojiQuestions.length);
            startActivity(intent);
        }
    }


}