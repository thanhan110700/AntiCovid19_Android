package sict.myproject.anticovid_19.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sict.myproject.anticovid_19.MainActivity;
import sict.myproject.anticovid_19.R;

public class xacnhankhaibao extends AppCompatActivity {
    Button btn_quaylai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xacnhankhaibao);
        btn_quaylai = (Button) findViewById(R.id.btn_quaylaitrangchu);
        btn_quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
