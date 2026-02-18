package edu.bpi.moviesbyemojis;

import static android.graphics.Color.GREEN;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainQuestions extends AppCompatActivity {

    TextView Title;

    TextView Emojis;

    Button choice1;

    Button choice2;

    Button choice3;

    Button choice4;

    Button Hint;

    private int currentQuestion = 0;
    private int score = 0;
    private String difficulty;

    private String[]emojiQuestions;
    private String[][] answerChoices;
    private String[] answers;

    private String[] hints;
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
    String[] mediumEmojiQuestions = {"🐠🔍", "👽📞🏠", "🦈🌊🏖️", "🐻👨🏻", "🌪️🏠🌈"};

    String[][] mediumAnswerChoices = {
            {"Finding Dory", "Finding Nemo", "The Little Mermaid", "Shark Tale"},
            {"Close Encounters", "E.T.", "Alien", "Arrival"},
            {"The Meg", "Deep Blue Sea", "Jaws", "Open Water"},
            {"Paddington", "Ted", "Yogi Bear", "Brother Bear"},
            {"Twister", "Into the Storm", "The Wizard of Oz", "Dorothy's Return"}
    };

    String[] mediumAnswers = {"Finding Nemo", "E.T.", "Jaws", "Ted", "The Wizard of Oz"};

    //HARD DIFFICULTY
    String[] hardEmojiQuestions = {"🎹👁️🔪🚪", "🕰️🍊🥛", "👧🎨", "💀🌹🎭", "🦢🖤🩰"};

    String[][] hardAnswerChoices = {
            {"The Sixth Sense", "Psycho", "Eyes Wide Shut", "Shutter Island"},
            {"Back to the Future", "The Time Machine", "Orange County", "A Clockwork Orange"},
            {"Gone Girl", "The Girl on the Train", "The Girl with the Dragon Tattoo", "Lisbeth"},
            {"The Mask of Zorro", "Phantom of the Opera", "Les Misérables", "Sweeney Todd"},
            {"The Nutcracker", "Center Stage", "Black Swan", "Swan Lake"}
    };

    String[] hardAnswers = {"Eyes Wide Shut", "A Clockwork Orange", "The Girl with the Dragon Tattoo", "Phantom of the Opera", "Black Swan"};

    // EASY HINTS
    String[] easyHints = {
            "This movie is about an old man's floating house adventure",
            "The king of the jungle, set in Africa",
            "Let it go... ❄️",
            "He was bit my a radioactive spider",
            "Never let go, Jack... 🚢"
    };

    // MEDIUM HINTS
    String[] mediumHints = {
            "Just keep swimming! 🐟",
            "Phone home 📞",
            "You're gonna need a bigger boat",
            "Teddy bear comes to life",
            "There's no place like home 🌈"
    };

    // HARD HINTS
    String[] hardHints = {
            "Stanley Kubrick's final film with Tom Cruise",
            "Ultraviolence and the Ludovico technique",
            "Swedish thriller with a hacker protagonist",
            "Musical set in the Paris Opera House",
            "Natalie Portman's Oscar-winning role"
    };

    // EASY FRENCH
    String[][] easyAnswerChoicesFrench = {
            {"ÇA", "Là-haut", "Toy Story", "Maman, j'ai raté l'avion"},
            {"Le Livre de la jungle", "Le Roi Lion", "Madagascar", "Tarzan"},
            {"Congelée", "Cendrillon", "Encanto", "Raiponce"},
            {"Ant-Man", "Spider-Man", "Batman", "Iron Man"},
            {"Titanic", "Moana", "Naufragé", "En pleine tempête"}
    };

    String[] easyAnswersFrench = {"Là-haut", "Le Roi Lion", "Congelée", "Spider-Man", "Titanic"};

    // MEDIUM FRENCH
    String[][] mediumAnswerChoicesFrench = {
            {"Le Monde de Dory", "Le Monde de Nemo", "La Petite Sirène", "Gang de requins"},
            {"Rencontres du troisième type", "E.T. l'extra-terrestre", "Alien", "Premier contact"},
            {"En eaux troubles", "Peur bleue", "Les Dents de la mer", "Open Water"},
            {"Paddington", "Ted", "Yogi l'ours", "Frère des ours"},
            {"Twister", "Tempête de feu", "Le Magicien d'Oz", "Le Retour de Dorothy"}
    };

    String[] mediumAnswersFrench = {"Le Monde de Nemo", "E.T. l'extra-terrestre", "Les Dents de la mer", "Ted", "Le Magicien d'Oz"};

    // HARD FRENCH
    String[][] hardAnswerChoicesFrench = {
            {"Sixième Sens", "Psychose", "Eyes Wide Shut", "Shutter Island"},
            {"Retour vers le futur", "La Machine à explorer le temps", "Orange County", "Orange mécanique"},
            {"Les Apparences", "La Fille du train", "Millénium", "Lisbeth"},
            {"Le Masque de Zorro", "Le Fantôme de l'Opéra", "Les Misérables", "Sweeney Todd"},
            {"Casse-Noisette", "Dance Floor", "Black Swan", "Le Lac des cygnes"}
    };

    String[] hardAnswersFrench = {"Eyes Wide Shut", "Orange mécanique", "Millénium", "Le Fantôme de l'Opéra", "Black Swan"};

    // EASY HINTS FRENCH
    String[] easyHintsFrench = {
            "Ce film parle de la maison flottante d'un vieil homme",
            "Le roi de la jungle, situé en Afrique",
            "Libérée, délivrée... ❄️",
            "Il a été mordu par une araignée radioactive",
            "Ne me lâche jamais, Jack... 🚢"
    };

    // MEDIUM HINTS FRENCH
    String[] mediumHintsFrench = {
            "Continue de nager! 🐟",
            "Téléphone maison 📞",
            "Il va nous falloir un plus gros bateau",
            "Un ours en peluche prend vie",
            "Il n'y a pas d'endroit comme la maison 🌈"
    };

    // HARD HINTS FRENCH
    String[] hardHintsFrench = {
            "Le dernier film de Stanley Kubrick avec Tom Cruise",
            "L'ultraviolence et la technique Ludovico",
            "Thriller suédois avec une hacker protagoniste",
            "Musical situé à l'Opéra de Paris",
            "Le rôle oscarisé de Natalie Portman"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLanguage();
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_questions);

        Title = (TextView) findViewById(R.id.Title);
        Emojis = (TextView) findViewById(R.id.Emojis);
        choice1 = (Button) findViewById(R.id.choice1);
        choice2 = (Button) findViewById(R.id.choice2);
        choice3 = (Button) findViewById(R.id.choice3);
        choice4 = (Button) findViewById(R.id.choice4);
        Hint = (Button) findViewById(R.id.Hint);

        //makes the buttons a Medium Dark Blue
        originalButtonColor=Color.parseColor("#0D47A1");

        //Gets the difficulty from MainActivity
        difficulty = getIntent().getStringExtra("difficulty");

        loadQuestions();
        theQuestions();

        Hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHint();
            }
        });

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

    private void showHint(){
        Toast.makeText(this, getString(R.string.hint_prefix, hints[currentQuestion]), Toast.LENGTH_LONG).show();
    }

    private void loadLanguage(){
        // Get access to the saved settings file called "Settings"
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        // Read the saved language code from the file. If nothing was saved, use "en" (English) as default
        String language = prefs.getString("Language", "en");
        // Create a Locale object with the saved language code ("en" for English and "fr" for French)
        Locale locale = new Locale(language);
        // Set this locale as the default for the entire app
        Locale.setDefault(locale);
        // Create a new Configuration object
        Configuration config = new Configuration();
        // Tell the configuration to use our chosen language
        config.setLocale(locale);
        // Apply this language configuration to all the app's text
        // This makes all strings from strings.xml use the correct language
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    private void loadQuestions(){
        // Check current language
        String currentLanguage = Locale.getDefault().getLanguage();

        if(difficulty.equals("easy")){
            emojiQuestions = easyEmojiQuestions;

            // Load based on language
            if(currentLanguage.equals("fr")){
                answerChoices = easyAnswerChoicesFrench;
                answers = easyAnswersFrench;
                hints = easyHintsFrench;  // French hints
            } else {
                answerChoices = easyAnswerChoices;
                answers = easyAnswers;
                hints = easyHints;  // English hints
            }

        } else if (difficulty.equals("medium")) {
            emojiQuestions = mediumEmojiQuestions;

            if(currentLanguage.equals("fr")){
                answerChoices = mediumAnswerChoicesFrench;
                answers = mediumAnswersFrench;
                hints = mediumHintsFrench;  // French
            } else {
                answerChoices = mediumAnswerChoices;
                answers = mediumAnswers;
                hints = mediumHints;  // English
            }

        } else if (difficulty.equals("hard")) {
            emojiQuestions = hardEmojiQuestions;

            if(currentLanguage.equals("fr")){
                answerChoices = hardAnswerChoicesFrench;
                answers = hardAnswersFrench;
                hints = hardHintsFrench;  // French
            } else {
                answerChoices = hardAnswerChoices;
                answers = hardAnswers;
                hints = hardHints;  // English
            }
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
        Title.setText(getString(R.string.question_title, currentQuestion + 1, emojiQuestions.length));
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
                    intent.putExtra("difficulty", difficulty);
                    startActivity(intent);
                }
            }
        }, 1000);  // 1000 = 1 second
    }


}