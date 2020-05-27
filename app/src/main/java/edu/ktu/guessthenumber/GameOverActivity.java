package edu.ktu.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    private TextView winText;
    private ImageView imgChange;
    private static final String PREFS_FILE = "Prefs"; //visus nustatymus surasom i sita faila

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        winText = findViewById(R.id.win_txt);
        imgChange = findViewById(R.id.change_img);

        Intent intent = getIntent();//is kito activity paima
        boolean win = intent.getBooleanExtra("win", false);//raktas win toks pat turi but pagal nutylejima reiksme false
        int guessedNumber = intent.getIntExtra("randomNumber", 0);
        int turnsCount = intent.getIntExtra("turnsCount", 0);

        if (win){
            winText.setText("You win!");
            imgChange.setImageResource(R.drawable.winn);//pakeicia i laimejimo paveiksliuka
            addDataToResults(guessedNumber, turnsCount); //jeigu laimi, tada i duombaze irasines
        }
        else{
            winText.setText("Game over");
            imgChange.setImageResource(R.drawable.sad);
        }
    }

    private void addDataToResults(int guessedNumber, int turnsCount) //iraso i duombaze
    {
        ResultsDatabaseHandler dbhandler = new ResultsDatabaseHandler(this);
        SharedPreferences prefs = getSharedPreferences(PREFS_FILE, MODE_PRIVATE); //failiukas su setingais

        String name = prefs.getString("playerName", "Name"); //def value paimamas tada jei nepavyko paimt key reiksmes
        int age = prefs.getInt("playerAge", 1);
        int diff = prefs.getInt("difficulty", 0);
        String difficulty = "";
        switch(diff) //kai kiekvienas difficulty(is arrejaus index pavercia i string pavadinima)
        {
            case 0:  //jeigu diff=0
                difficulty = getResources().getStringArray(R.array.difficulty_items)[0];
                break;
            case 1:
                difficulty = getResources().getStringArray(R.array.difficulty_items)[1];
                break;
            case 2:
                difficulty = getResources().getStringArray(R.array.difficulty_items)[2];
                break;
            case 3:
                difficulty = getResources().getStringArray(R.array.difficulty_items)[3];
                break;
        }

        dbhandler.addData(new PersonData(0, name, difficulty, age, guessedNumber, turnsCount));
    }
}

