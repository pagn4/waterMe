package com.example.waterme;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.text.SimpleDateFormat;

public class popup extends AppCompatDialogFragment {

    private EditText dateT;
    private ArrayList<String> arrList;
    private ArrayAdapter<String> adapter;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add, null);

        String gimt = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        dateT = (EditText) view.findViewById (R.id.editTextDate);
        dateT.setText(gimt);

        EditText nameT = (EditText) view.findViewById (R.id.name);
        EditText speciesT = (EditText) view.findViewById (R.id.editTextSpecies);
        EditText daysT = (EditText) view.findViewById (R.id.editTextDays);

        builder.setView(view)
                .setTitle("Naujas augalas")
                .setNegativeButton("Atgal", (dialogInterface, i) -> {

                })
                .setPositiveButton("Gerai", (dialogInterface, i) -> {
                    String name = nameT.getText().toString();
                    String species = speciesT.getText().toString();
                    String date = dateT.getText().toString();
                    String days = daysT.getText().toString();

                    if(!name.equals("") && !species.equals("") && !date.equals("")&& !days.equals("")){
                        save(name,species,date,days);
                        Toast.makeText(getContext(), "Išsaugota", Toast.LENGTH_SHORT).show();
                        go();
                    }
                    else{
                        Toast.makeText(getContext(),"Trūksta duomenų", Toast.LENGTH_SHORT).show();
                    }
                });


        return builder.create();

    }

    public void save(String name, String species, String date, String days){

        arrList = file.readData(getContext());
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, arrList);
        adapter.add(name+'\n'+"Rūšis: "+species+'\n'+"Gimtadienis: "+date+"     Laistymo dažnis: "+days+ " d.");
        file.saving(arrList,getContext());

    }
    public void go(){
        //taip padariau, kad pridejus nauja augala nereiketu perkrauti programeles ir iskarto butu galima matyti
        Intent restart = new Intent(getContext(), MainActivity.class);
        startActivity(restart);
        getActivity().overridePendingTransition(0,0);
    }
}
