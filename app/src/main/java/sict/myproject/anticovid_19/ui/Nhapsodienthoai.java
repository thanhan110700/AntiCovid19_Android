package sict.myproject.anticovid_19.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

import sict.myproject.anticovid_19.Model.URLdatabase;
import sict.myproject.anticovid_19.R;

public class Nhapsodienthoai extends AppCompatActivity {

    TextInputLayout layout_sodienthoai;
    TextInputEditText et_first_sdt;
    Button btn_dangnhap,btn_dangky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhapsodienthoai);
        Widget();
        setClickButtonDangnhap();
        setClickButtonDangky();
    }
    public void Widget(){
        et_first_sdt = (TextInputEditText) findViewById(R.id.et_first_sdt);
        btn_dangky = (Button) findViewById(R.id.btn_dangky);
        btn_dangnhap = (Button) findViewById(R.id.btn_dangnhap);
        layout_sodienthoai = (TextInputLayout) findViewById(R.id.layout_sodienthoai);


    }
    public void setClickButtonDangnhap(){
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Validate()){
                    initNhapsodienthoaiDN();

                }
                    ChangeInput();
            }
        });
    }

    public void setClickButtonDangky() {
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validate()){
                    initNhapsodienthoaiDK();

                }
                ChangeInput();
            }
        });
    }

    public void ChangeInput(){
        et_first_sdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!et_first_sdt.getText().toString().isEmpty()){
                    layout_sodienthoai.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public boolean Validate(){
        if (et_first_sdt.length()<9 && et_first_sdt.length()>0 || et_first_sdt.length()>11){
            layout_sodienthoai.setErrorEnabled(true);
            layout_sodienthoai.setError("Vui lòng nhập đúng định dạng số điện thoại");
            return  false;
        }
        if (et_first_sdt.length()==0){
            layout_sodienthoai.setErrorEnabled(true);
            layout_sodienthoai.setError("Vui lòng nhập số điện thoại");
            return  false;
        }
        return true;
    }
    public void initNhapsodienthoaiDN(){
        URLdatabase link = new URLdatabase();
        String url = link.getApi()+"checksodienthoai";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("ok")){

                    Intent intent = new Intent(getApplicationContext(),Dangnhap.class);
                    intent.putExtra("sodienthoai",et_first_sdt.getText().toString());
                    startActivity(intent);
                }
                if (response.equals("no")){
                    Toast.makeText(Nhapsodienthoai.this, "Số điện thoại này chưa được đăng ký", Toast.LENGTH_SHORT).show();
                    Log.e("AAAAAAAAAAAAAAAA", "onResponse: "+response );
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Nhapsodienthoai.this, "Lỗi: "+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("sodienthoai",et_first_sdt.getText().toString());
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }



    public void initNhapsodienthoaiDK(){
        URLdatabase link = new URLdatabase();
        String url = link.getApi()+"checksodienthoai";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("ok")){
                    Toast.makeText(Nhapsodienthoai.this, "Số điện thoại này đã được đăng ký", Toast.LENGTH_SHORT).show();
                    Log.e("AAAAAAAAAAAAAAAA", "onResponse: "+response );

                }
                if (response.equals("no")){

                    Intent intent = new Intent(getApplicationContext(),Dangky.class);
                    intent.putExtra("sodienthoai",et_first_sdt.getText().toString());
                    startActivity(intent);
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Nhapsodienthoai.this, "Lỗi: "+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("sodienthoai",et_first_sdt.getText().toString());
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

}
