package com.example.waterme;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.text.SimpleDateFormat;

public class popup extends AppCompatDialogFragment {

    private EditText date;
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add, null);

        String gimt = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        date = (EditText) view.findViewById (R.id.editTextDate);
        date.setText(gimt);

        builder.setView(view)
                .setTitle("Naujas augalas")
                .setNegativeButton("Atgal", (dialogInterface, i) -> {

                })
                .setPositiveButton("Gerai", (dialogInterface, i) -> {
                    Toast.makeText(getContext(), "išsaugota", Toast.LENGTH_SHORT).show();
                });


        return builder.create();

    }
}
