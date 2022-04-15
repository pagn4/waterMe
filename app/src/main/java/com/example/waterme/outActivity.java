package com.example.waterme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class outActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out);
        this.setTitle("Laukas");
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