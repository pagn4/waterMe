package com.example.waterme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class outActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out);
        this.setTitle("Laukas");

        WebView webView = (WebView) findViewById(R.id.web);
        webView.loadUrl("https://www.gismeteo.lt/weather-klaip%C4%97da-4157/weekly/");



        //skaiciuojama ir issaugojama pries kiek dienu buvo laistyti augalai esantys lauke
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        SharedPreferences dayL = getSharedPreferences("PREFS",0);
        int lastDay = dayL.getInt("day",0);
        if(lastDay!=currentDay){
            SharedPreferences.Editor editor = dayL.edit();
            editor.putInt("day",currentDay);
            editor.apply();

            SharedPreferences days = getSharedPreferences("days",0);
            int countDays = days.getInt("number",0);
            countDays+=1;
            SharedPreferences.Editor editDays = days.edit();
            editDays.putInt("number",countDays);
            editDays.apply();
        }
        SharedPreferences days = getSharedPreferences("days",0);
        int countDays = days.getInt("number",0);
        String a=String.valueOf(countDays);
        TextView textView = (TextView) findViewById(R.id.days);
        textView.setText(a + " d.");

    }


    public boolean itHasBeenWatered(View view){
        //paspaudus mygtuka "palaisciau" dienu skaicius numazinamas iki 0
        TextView textView = (TextView) findViewById(R.id.days);
        textView.setText("0 d.");
        SharedPreferences day = getSharedPreferences("days",0);
        SharedPreferences.Editor edit = day.edit();
        edit.putInt("number",0);
        edit.apply();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.nav_menu,menu);
        return true;
    }

    public boolean navigationMenuNextActivity(@NonNull MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                Intent intentOut = new Intent(this, MainActivity.class);
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
}