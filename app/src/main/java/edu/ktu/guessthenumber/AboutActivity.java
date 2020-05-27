package edu.ktu.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button moreInformationBtn = findViewById(R.id.more_information_btn);
        moreInformationBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                toWebsiteClick(v);
            }
        });
    }

    public void toWebsiteClick(View view)
    {
        String google = "http://www.google.com";
        Uri webaddress = Uri.parse(google);

        Intent goToGoogle = new Intent(Intent.ACTION_VIEW, webaddress);
        if (goToGoogle.resolveActivity(getPackageManager()) != null) {
            startActivity(goToGoogle);
        }
    }
}
