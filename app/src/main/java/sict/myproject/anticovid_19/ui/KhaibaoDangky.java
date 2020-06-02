package sict.myproject.anticovid_19.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import sict.myproject.anticovid_19.R;

public class KhaibaoDangky extends AppCompatActivity {

    String sdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khaibao_dangky);

        Intent intent = getIntent();
        sdt = intent.getStringExtra("matkhau")+" mk lập nhạo" + intent.getStringExtra("matkhaun");

    }
}
