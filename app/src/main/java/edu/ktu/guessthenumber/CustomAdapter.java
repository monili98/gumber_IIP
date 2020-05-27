package edu.ktu.guessthenumber;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> { //paveldejimas, nurodom kokio tipo adapteris bus

    private List<PersonData> data; //duomenys kaip stringu sarasas

    public static class CustomViewHolder extends RecyclerView.ViewHolder { //klase, kuri laiko visus vieno iraso atvaizduojamuosius laukus
        public TextView firstNameView;
        public TextView difficultyView;
        public TextView ageView;
        public TextView guessedNumberView;
        public TextView turnsCountView;

        public CustomViewHolder(View view) //konstruktorius
        {
            super(view);
            this.firstNameView = view.findViewById(R.id.first_name);
            this.difficultyView = view.findViewById(R.id.difficulty);
            this.ageView = view.findViewById(R.id.age);
            this.guessedNumberView = view.findViewById(R.id.guessed_number);
            this.turnsCountView = view.findViewById(R.id.turns_count);
        }
    }

    public CustomAdapter(List<PersonData> data)
    {
        this.data = data;
    } //konstruktorius

    //sitie trys butinai overridinami, kitaip programa neveiks
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) //sukuria viena viewholder objekta, i kuri paskui bus idedami duomenys
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(itemView);
        return viewHolder;
    }

    public void onBindViewHolder(CustomViewHolder viewHolder, int position) //kad uzpildytu view objekta duomenimis
    {
        PersonData person = data.get(position);
        viewHolder.firstNameView.setText(person.getName());
        viewHolder.difficultyView.setText(person.getDifficulty());
        viewHolder.ageView.setText(Integer.toString(person.getAge()));
        viewHolder.guessedNumberView.setText(Integer.toString(person.getGuessedNumber()));
        viewHolder.turnsCountView.setText(Integer.toString(person.getTurnsCount()));
    }

    public int getItemCount()
    {
        return data.size();
    } //suzino kiek is viso duomenu yra musu sarase
}
