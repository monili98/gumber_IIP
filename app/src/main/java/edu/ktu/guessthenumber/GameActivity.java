package edu.ktu.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private  int minNumber = 0;
    private  int maxNumber; //private  int maxNumber = 10;

    private  int randomNumber;

    private int maxTurns; //private int maxTurns = 7;
    private int currentTurn = 0;

    private int result = 0;

    private TextView numberRangeText;
    private TextView resultText;
    private TextView turnsText; //ejimu skc
    private EditText numberField; //irasomas skaiciukas(spejimas)

    private static final String PREFS_FILE = "Prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maxTurns = setMaxTurns();
        maxNumber = setMaxNumber();

        setContentView(R.layout.activity_game);

        numberRangeText = findViewById(R.id.number_range_txt); //(R.id.playerName);
        resultText = findViewById(R.id.result_txt);
        turnsText = findViewById(R.id.turns_txt);
        numberField = findViewById(R.id.number_field);

        updateTexts(0);

        Random random = new Random();
        randomNumber = random.nextInt(maxNumber - minNumber) + minNumber; //kad gauti random numeri tarp reziu
    }

    private void updateTexts(int guessNumber){
        numberRangeText.setText(String.format(getResources().getString(R.string.number_range_format), minNumber, maxNumber));//is strings failo paima reiksme
        if (result > 0){
            //perdidelis
            resultText.setText(String.format(getResources().getString(R.string.result_format), guessNumber, getResources().getString(R.string.result_high)));
        }
        else if (result < 0){
            //permazas
            resultText.setText(String.format(getResources().getString(R.string.result_format), guessNumber , getResources().getString(R.string.result_low)));
        }
        turnsText.setText(String.format(getResources().getString(R.string.turns_format), currentTurn, maxTurns));//currentTurn ir maxTurns isiraso i stringa
    }

    public  void  guessClick(View view)
    {
        currentTurn++; //atliktas vienas ejimas
        int gueassNumber = Integer.parseInt(numberField.getText().toString()); //pasiverciam ivesta skaiciu is stringo i int

        if (randomNumber > gueassNumber){
            result = -1;
        }
        else if(randomNumber < gueassNumber){
            result = 1;
        }
        else
            result = 0;
        //jeigu atspejam, result islieka 0

        if (currentTurn >= maxTurns && result !=0){
            //Lose
            Intent intent = new Intent(this, GameOverActivity.class);
            intent.putExtra("guessedNumber", gueassNumber);
            intent.putExtra("randomNumber", randomNumber);
            intent.putExtra("turnsCount", currentTurn);
            intent.putExtra("win", false);
            startActivity(intent);
            finish();
        }
        else if(result == 0){
            //Win

            Intent intent = new Intent(this, GameOverActivity.class);
            intent.putExtra("guessedNumber", gueassNumber);
            intent.putExtra("randomNumber", randomNumber);
            intent.putExtra("turnsCount", currentTurn);
            intent.putExtra("win", true);
            startActivity(intent);
            finish();
        }

        updateTexts(gueassNumber);
    }

    private int setMaxTurns(){
        SharedPreferences prefs = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);

        int diff = prefs.getInt("difficulty", 0);
        switch(diff)
        {
            case 0: //jeigu easy
                return 5;
            case 1: //medium
                return 10;
            case 2: //hard
                return 15;
            case 3: //impossible
                return 5;
        }
        return 0;
    }

    private int setMaxNumber(){
        SharedPreferences prefs = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);

        int diff = prefs.getInt("difficulty", 0);
        switch(diff)
        {
            case 0: //jeigu easy
                return 10;
            case 1: //medium
                return 30;
            case 2: //hard
                return 60;
            case 3: //impossible
                return 100;
        }
        return 0;
    }
}
