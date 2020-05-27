package edu.ktu.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    private  static  final  String PREFS_FILE = "Prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences prefs = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);

        //pradines settingu reiksmes
        String name = prefs.getString("playerName", "Name");
        int age = prefs.getInt("playerAge", 1);
        int difficulty = prefs.getInt("difficulty", 0);
        boolean sound = prefs.getBoolean("sound", true);

        //settingu laukai
        EditText playerNameField = findViewById(R.id.playerName);
        EditText playerAgeField = findViewById(R.id.PlayerAge);
        Spinner difficultySpinner = findViewById(R.id.difficulty_spinner);
        Switch soundSwitch = findViewById(R.id.sound_switch);

        playerNameField.setText(name);
        playerAgeField.setText(Integer.toString(age));
        difficultySpinner.setSelection(difficulty);
        soundSwitch.setChecked(sound);
    }

    public void saveClick(View view){
        SharedPreferences.Editor prefsEditor = getSharedPreferences(PREFS_FILE, MODE_PRIVATE).edit(); //nueinam i faila ir naudojam edit metoda, i prefseditor isirasysim gautas reiksmes

        EditText playerNameField = findViewById(R.id.playerName);
        EditText playerAgeField = findViewById(R.id.PlayerAge);
        Spinner difficultySpinner = findViewById(R.id.difficulty_spinner);
        Switch soundSwitch = findViewById(R.id.sound_switch);

        String name = playerNameField.getText().toString();
        int age = Integer.parseInt(playerAgeField.getText().toString());
        int difficulty = difficultySpinner.getSelectedItemPosition();
        boolean sound = soundSwitch.isChecked();

        prefsEditor.putString("playerName", name);
        prefsEditor.putInt("playerAge", age);
        prefsEditor.putInt("difficulty", difficulty);
        prefsEditor.putBoolean("sound", sound);

        prefsEditor.apply();

        finish();

    }
}
