package sict.myproject.anticovid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import sict.myproject.anticovid_19.Adapter.AdapterSelect;

import sict.myproject.anticovid_19.ui.HealthyActivity;
import sict.myproject.anticovid_19.ui.Khaibao;
import sict.myproject.anticovid_19.ui.MenuActivity;
import sict.myproject.anticovid_19.ui.RequestActivity;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView tv_infomationof;
    ViewPager viewSelect;
    Button btn_khaibao,btn_phongchong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Widget();
        initBottomNavigation();

        initSelect();
        initButton();
    }
    public void Widget(){
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        tv_infomationof = (TextView) findViewById(R.id.tv_informationof);
        viewSelect = (ViewPager) findViewById(R.id.vp_select);
        btn_khaibao = (Button) findViewById(R.id.btn_khaibao);
        btn_phongchong = (Button) findViewById(R.id.btn_phongchong);
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
        viewSelect.setAdapter(new AdapterSelect(getSupportFragmentManager()));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_select);

        tabLayout.setupWithViewPager(viewSelect);
        tabLayout.getTabAt(0).setIcon(R.drawable.vietnam);
        tabLayout.getTabAt(1).setIcon(R.drawable.global);
    }
    public void initButton(){
        btn_phongchong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://ncovi.vnpt.vn/views/huongdan.html");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        btn_khaibao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Khaibao.class);
                startActivity(intent);
            }
        });
    }
}
