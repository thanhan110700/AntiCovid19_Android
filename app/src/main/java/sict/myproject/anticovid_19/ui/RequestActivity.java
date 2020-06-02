package sict.myproject.anticovid_19.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import sict.myproject.anticovid_19.Adapter.AdapterSelectHealthy;
import sict.myproject.anticovid_19.Adapter.AdapterSelectRequest;
import sict.myproject.anticovid_19.MainActivity;
import sict.myproject.anticovid_19.R;

public class RequestActivity extends AppCompatActivity {
    ViewPager viewSelect;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Widget();
        initBottomNavigation();
        initSelect();
    }
    public void Widget(){
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        viewSelect = (ViewPager) findViewById(R.id.vp_select_request);
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
        viewSelect.setAdapter(new AdapterSelectRequest(getSupportFragmentManager()));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_select_request);

        tabLayout.setupWithViewPager(viewSelect);

    }
}
