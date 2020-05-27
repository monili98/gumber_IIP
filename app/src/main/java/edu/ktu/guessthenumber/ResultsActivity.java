package edu.ktu.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {

    private RecyclerView recyclerView; //nuoroda i layout esanti sarasiuka duomenu, kuriuos noresim ideti
    private RecyclerView.Adapter adapter; //objektas, susieja sarasa su duomenim, kuriuos pateiksiu adapteriui ir noresiu atvaizduot
    private RecyclerView.LayoutManager layoutManager; //nurodo kaip bus isdelioti duomenys sarase ekrane
    private ArrayList<PersonData> resultsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        resultsData = new ArrayList<>();
        prepareContent();

        recyclerView = findViewById(R.id.main_recycler_view); //is xml failiukas
        layoutManager = new LinearLayoutManager(this); //visus duomenis isdelios tiesiskai
        adapter = new CustomAdapter(resultsData);

        recyclerView.setLayoutManager(layoutManager); //nustatom kokios krypties manageri naudot
        recyclerView.setAdapter(adapter); //nustatom koki adapteri naudoti

        adapter.notifyDataSetChanged(); //nurodo, kad pasikeite duomenu rinkinys ir reikia kazka daryt
        //geriau naudot adapter.notifyItemChanged(); kad nesvaistyt resursu
    }

    private void prepareContent()
    {
        resultsData = new ArrayList<>();

        ResultsDatabaseHandler dbhandler = new ResultsDatabaseHandler(this);

        /*dbhandler.addData(new PersonData(0, "Jonas", "Lengvas", 200));
        dbhandler.addData(new PersonData(0, "Juozas", "Neimanomas", 100));
        dbhandler.addData(new PersonData(0, "Petras", "Sunkus", 50));*/

        ArrayList<PersonData> data = dbhandler.getAllData();
        resultsData = data;
    }
}
