package sict.myproject.anticovid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import sict.myproject.anticovid_19.Adapter.AdapterSelect;

import sict.myproject.anticovid_19.ui.HealthyActivity;
import sict.myproject.anticovid_19.ui.HomeActivity;
import sict.myproject.anticovid_19.ui.Khaibao;
import sict.myproject.anticovid_19.ui.MenuActivity;
import sict.myproject.anticovid_19.ui.Nhapsodienthoai;
import sict.myproject.anticovid_19.ui.RequestActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isFirstTime();
            }
        },5000);
    }

    private void isFirstTime() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("oBoard", Context.MODE_PRIVATE);
        boolean isFirstTime = sharedPreferences.getBoolean("isFirstTime",true);
        if (isFirstTime){
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putBoolean("isFirstTime",false);
//            editor.apply();

            //chuyển sang trang đăng nhập đăng ký
            Intent intent = new Intent(this, Nhapsodienthoai.class);
            startActivity(intent);
            finish();
        }
        else {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
