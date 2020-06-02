package sict.myproject.anticovid_19.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import sict.myproject.anticovid_19.R;

public class Dangky extends AppCompatActivity {
    TextView sdt_dk;
    TextInputLayout layout_matkhauDK,layout_nhaplaimkDK;
    TextInputEditText et_matkhauDK,et_nhaplaimkDK;
    Button btn_chuyenkhaibao;
    String sodienthoai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        Widget();
        initView();
        setButtonChuyen();
    }
    public void Widget(){
        sdt_dk = (TextView) findViewById(R.id.sdt_dk);
        et_matkhauDK = (TextInputEditText) findViewById(R.id.et_matkhauDK);
        et_nhaplaimkDK = (TextInputEditText) findViewById(R.id.et_nhaplaimatkhauDK);
        btn_chuyenkhaibao = (Button) findViewById(R.id.btn_chuyenkhaibao);
        layout_matkhauDK= (TextInputLayout) findViewById(R.id.layout_matkhauDK);
        layout_nhaplaimkDK= (TextInputLayout) findViewById(R.id.layout_nhaplaimatkhauDK);
    }
    public void initView(){
        Intent intent = getIntent();
        sodienthoai = intent.getStringExtra("sodienthoai");
        sdt_dk.setText(sodienthoai);
    }

    public void setButtonChuyen(){
        btn_chuyenkhaibao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validate()){
                    Intent intent = new Intent(getApplicationContext(),KhaibaoDangky.class);
                    intent.putExtra("sodienthoai",sodienthoai);
                    intent.putExtra("matkhau",et_matkhauDK.getText().toString());
                    intent.putExtra("matkhaun",et_nhaplaimkDK.getText().toString());
                    startActivity(intent);
                }
                ChangeInput();
            }
        });
    }
    public void ChangeInput(){
        et_matkhauDK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!et_matkhauDK.getText().toString().isEmpty()){
                    layout_matkhauDK.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_nhaplaimkDK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!et_nhaplaimkDK.getText().toString().isEmpty()){
                    layout_nhaplaimkDK.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public boolean Validate(){

        if (et_matkhauDK.length()==0){
            layout_matkhauDK.setErrorEnabled(true);
            layout_matkhauDK.setError("Vui lòng nhập mật khẩu");
            return  false;
        }
        if (et_nhaplaimkDK.length()==0){
            layout_nhaplaimkDK.setErrorEnabled(true);
            layout_nhaplaimkDK.setError("Vui lòng nhập lại mật khẩu");
            return  false;
        }
        if (et_matkhauDK.getText().toString().equals(et_nhaplaimkDK.getText().toString())){
            return true;
        }
        if (!et_matkhauDK.getText().toString().equals(et_nhaplaimkDK.getText().toString())){
            layout_nhaplaimkDK.setErrorEnabled(true);
            layout_nhaplaimkDK.setError("Nhập lại mật khẩu không chính xác"+et_matkhauDK.getText().toString().trim() + et_nhaplaimkDK.getText().toString().trim()+"j");
            return  false;
        }

        else  return false;
    }
}
