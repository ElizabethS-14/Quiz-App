package edu.bpi.moviesbyemojis;

import static android.graphics.Color.GREEN;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainQuestions extends AppCompatActivity {

    TextView Title;

    TextView Emojis;

    Button choice1;

    Button choice2;

    Button choice3;

    Button choice4;

    private int currentQuestion = 0;
    private int score = 0;
    private String difficulty;

    private String[]emojiQuestions;
    private String[][] answerChoices;
    private String[] answers;
    private int originalButtonColor;

    //EASY DIFFICULTY
    String[] easyEmojiQuestions = {"🎈👴🏠","🦁👑","🧊️👭⛄","🕷️🧑‍🎓","🚢💔🌊"};

    String[][] easyAnswerChoices ={{"IT", "Up","Toy Story","Home Alone"},
            {"Jungle Book", "The Lion King","Madagascar","Tarzan"},
            {"Frozen", "Cinderella","Encanto","Tangled"},
            {"Ant-Man", "Spider-Man","Batman","Iron Man"},
            {"Titanic", "Moana","Shipwrecked","The Perfect Storm"}};

    String[] easyAnswers = {"Up","The Lion King","Frozen","Spider-Man","Titanic"};

    //MEDIUM DIFFICULTY
    String[] mediumEmojiQuestions = {"🐠🔍", "👽📞🏠", "🦈🌊🏖️", "🐻🍯", "🌪️🏠🌈"};

    String[][] mediumAnswerChoices = {
            {"Finding Dory", "Finding Nemo", "The Little Mermaid", "Shark Tale"},
            {"Close Encounters", "E.T.", "Alien", "Arrival"},
            {"The Meg", "Deep Blue Sea", "Jaws", "Open Water"},
            {"Paddington", "Winnie the Pooh", "Yogi Bear", "Brother Bear"},
            {"Twister", "Into the Storm", "The Wizard of Oz", "Dorothy's Return"}
    };

    String[] mediumAnswers = {"Finding Nemo", "E.T.", "Jaws", "Winnie the Pooh", "The Wizard of Oz"};

    //HARD DIFFICULTY
    String[] hardEmojiQuestions = {"🎹👁️🔪🚪", "🕰️🍊🥛", "🐉👧🎨", "💀🌹🎭", "🦢🖤🩰"};

    String[][] hardAnswerChoices = {
            {"The Sixth Sense", "Psycho", "Eyes Wide Shut", "Shutter Island"},
            {"Back to the Future", "The Time Machine", "Orange County", "A Clockwork Orange"},
            {"Gone Girl", "The Girl on the Train", "The Girl with the Dragon Tattoo", "Lisbeth"},
            {"The Mask of Zorro", "Phantom of the Opera", "Les Misérables", "Sweeney Todd"},
            {"The Nutcracker", "Center Stage", "Black Swan", "Swan Lake"}
    };

    String[] hardAnswers = {"Eyes Wide Shut", "A Clockwork Orange", "The Girl with the Dragon Tattoo", "Phantom of the Opera", "Black Swan"};

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

        //makes the buttons a Medium Dark Blue
        originalButtonColor=Color.parseColor("#1976D2");

        //Gets the difficulty from MainActivity
        difficulty = getIntent().getStringExtra("difficulty");

        loadQuestions();
        theQuestions();

//creates a listener obj to handle clicks for all 4 buttons
        View.OnClickListener answerClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //turns View v into a button since its going to be one of the 4 buttons
                Button buttonClicked = (Button) v;
                //takes the text of whatever button is clicked and stores it into a variable
                String selected = buttonClicked.getText().toString();
                checkAnswer(selected,buttonClicked);
            }
        };
        //now each button will run on the same onClick
        choice1.setOnClickListener(answerClick);
        choice2.setOnClickListener(answerClick);
        choice3.setOnClickListener(answerClick);
        choice4.setOnClickListener(answerClick);
    }

    private void loadQuestions(){
        //Gets the questions based on difficulty chosen
        if(difficulty.equals("Easy")){
            emojiQuestions = easyEmojiQuestions;
            answerChoices = easyAnswerChoices;
            answers = easyAnswers;
        } else if (difficulty.equals("Medium")) {
            emojiQuestions = mediumEmojiQuestions;
            answerChoices = mediumAnswerChoices;
            answers = mediumAnswers;
        } else if (difficulty.equals("Hard")) {
            emojiQuestions = hardEmojiQuestions;
            answerChoices = hardAnswerChoices;
            answers = hardAnswers;
        }

    }
    //resets the button color after u get the answer right or wrong
    private void resetButtonColors() {
        choice1.setBackgroundColor(originalButtonColor);
        choice2.setBackgroundColor(originalButtonColor);
        choice3.setBackgroundColor(originalButtonColor);
        choice4.setBackgroundColor(originalButtonColor);
    }


    private void theQuestions(){
        Title.setText("Question " + (currentQuestion + 1) + " of " + emojiQuestions.length);
        Emojis.setText(emojiQuestions[currentQuestion]);

        choice1.setText(answerChoices[currentQuestion][0]);
        choice2.setText(answerChoices[currentQuestion][1]);
        choice3.setText(answerChoices[currentQuestion][2]);
        choice4.setText(answerChoices[currentQuestion][3]);

        resetButtonColors();

    }

    private void checkAnswer(String selected, Button buttonClicked){
        //checks if the button pressed is equal to the answer to the question
        if(selected.equals(answers[currentQuestion])){
            // CORRECT! Make button GREEN
            buttonClicked.setBackgroundColor(Color.GREEN);
            score++;
        } else {
            // WRONG! Make button RED
            buttonClicked.setBackgroundColor(Color.RED);
        }
// makes it so we wait a second before moving on to the next question so the user sees which answer they got wrong or right.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentQuestion++;
//added to current question so after the button is pressed, you go to the next question if the length is less than the array
                if (currentQuestion < emojiQuestions.length) {
                    theQuestions();
                } else {
                    Intent intent = new Intent(MainQuestions.this, Score.class);
                    intent.putExtra("score", score);
                    intent.putExtra("totalQuestions", emojiQuestions.length);
                    startActivity(intent);
                }
            }
        }, 1000);  // 1000 = 1 second
    }


}