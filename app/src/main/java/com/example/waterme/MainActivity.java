package com.example.waterme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Namai");
    }

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

}