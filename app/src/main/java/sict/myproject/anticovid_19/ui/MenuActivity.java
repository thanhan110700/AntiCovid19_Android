package sict.myproject.anticovid_19.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import sict.myproject.anticovid_19.MainActivity;
import sict.myproject.anticovid_19.R;

public class MenuActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Widget();
        initBottomNavigation();
    }
    public void Widget(){
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
    }
    public void initBottomNavigation(){
        //select home
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_healthy:
                        startActivity(new Intent(getApplicationContext(), HealthyActivity.class));
                        overridePendingTransition(0,0);
                        return  true;
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return  true;
                    case R.id.nav_request:
                        startActivity(new Intent(getApplicationContext(), RequestActivity.class));
                        overridePendingTransition(0,0);
                        return  true;
                    case R.id.nav_menu:

                        return  true;
                }
                return false;
            }
        });
    }
}
