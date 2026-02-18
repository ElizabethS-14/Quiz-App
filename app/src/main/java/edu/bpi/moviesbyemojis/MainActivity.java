package edu.bpi.moviesbyemojis;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Locale;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView Title;

    Button Easy;

    Button Medium;

    Button Hard;

    Button englishButton;

    Button frenchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Title = (TextView) findViewById(R.id.Title);
        Easy = (Button) findViewById(R.id.Easy);
        Medium = (Button) findViewById(R.id.Medium);
        Hard = (Button) findViewById(R.id.Hard);
        englishButton = (Button) findViewById(R.id.englishButton);
        frenchButton = (Button) findViewById(R.id.frenchButton);

        Easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainQuestions.class);
                intent.putExtra("difficulty", "easy");
                startActivity(intent);
            }
        });

        Medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainQuestions.class);
                intent.putExtra("difficulty", "medium");
                startActivity(intent);
            }
        });

        Hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainQuestions.class);
                intent.putExtra("difficulty", "hard");
                startActivity(intent);
            }
        });

        englishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLanguage("en");
            }
        });

        frenchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLanguage("fr");
            }
        });


    }

    private void changeLanguage(String languageCode){
        //Create a method called changeLanguage that receives a language code
        Locale locale = new Locale(languageCode);
        //use this translator as the default one
        Locale.setDefault(locale);

        //new setting object
        Configuration config = new Configuration();
        //use this language translator
        config.setLocale(locale);

        //get all app texts and update them with the new language setting
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        //saves info when app closes
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        //writes down language choice
        editor.putString("Language", languageCode);
        //saves what was written
        editor.apply();

        // Restart the screen
        recreate();
    }
}