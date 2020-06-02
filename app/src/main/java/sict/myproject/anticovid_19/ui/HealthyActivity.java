package sict.myproject.anticovid_19.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import sict.myproject.anticovid_19.Adapter.AdapterSelect;
import sict.myproject.anticovid_19.Adapter.AdapterSelectHealthy;
import sict.myproject.anticovid_19.MainActivity;
import sict.myproject.anticovid_19.R;

public class HealthyActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ViewPager viewSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy);
        Widget();
        initBottomNavigation();
        initSelect();
    }
    public void Widget(){
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        viewSelect = (ViewPager) findViewById(R.id.vp_select_healthy);
    }
    public void initBottomNavigation(){
        //select home
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_healthy:

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
                        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                        overridePendingTransition(0,0);
                        return  true;
                }
                return false;
            }
        });
    }
    public void initSelect(){
        viewSelect.setAdapter(new AdapterSelectHealthy(getSupportFragmentManager()));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_select_healthy);

        tabLayout.setupWithViewPager(viewSelect);

    }
}
