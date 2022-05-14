package com.example.waterme;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button button;

    private ArrayList<String> arrList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitleColor(Color.rgb(239,239,205));
        this.setTitle("Namai");


        ListView list = findViewById(R.id.list);
        arrList = file.readData(this);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrList);
        list.setAdapter(adapter);

        list.setOnItemLongClickListener((adapterView, view, i, l) -> {
            deletion(i);
            return true;
        });


        //kasdieninis priminimas
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if (calendar.getTime().compareTo(new Date()) < 0) calendar.add(Calendar.HOUR_OF_DAY, 0);
        Intent intent = new Intent(getApplicationContext(), notify.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

    //paspaudus mygtuka atidaromas auglo duomenu suvedimo langas
    public void openDialog(View view){
        popup dialog = new popup();
        dialog.show(getSupportFragmentManager(), "popup");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.nav_menu,menu);
        return true;
    }

    public boolean navigationMenuNextActivity(@NonNull MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.nav_out:
                Intent intentOut = new Intent(this, outActivity.class);
                startActivity(intentOut);
                this.overridePendingTransition(0, 0);
                break;
            case R.id.nav_info:
                Intent intentSettings = new Intent(this, infoActivity.class);
                startActivity(intentSettings);
                this.overridePendingTransition(0, 0);
                break;
        }
        return true;
    }


    //istrynimo funkcija
    public void deletion (int i){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setMessage("Ar tikrai norite ištrinti?");
        alert.setCancelable(false);
        alert.setPositiveButton("Taip", (dialogInterface, i1) -> {
            arrList.remove(i);
            adapter.notifyDataSetChanged();
            file.saving(arrList, getApplicationContext());
            Toast.makeText(getApplicationContext(), "Ištrinta", Toast.LENGTH_SHORT).show();
        });
        alert.setNegativeButton("Ne", (dialogInterface, i12) -> {

        });
        alert.setTitle("Ištrynimas");
        AlertDialog a = alert.create();
        a.show();
    }
}