package sict.myproject.anticovid_19.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import sict.myproject.anticovid_19.Model.URLdatabase;
import sict.myproject.anticovid_19.R;

public class Dangnhap extends AppCompatActivity {
    TextView sdt_dn;
    TextInputLayout layout_Matkhau;
    TextInputEditText et_matkhau;
    Button btn_actiondangnhap;
    SharedPreferences userPref;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        Widget();
        initView();
        initButton();

    }

    public void Widget(){
        sdt_dn = (TextView) findViewById(R.id.sdt_dn);
        et_matkhau = (TextInputEditText) findViewById(R.id.et_matkhau);
        btn_actiondangnhap = (Button) findViewById(R.id.btn_actiondangnhap);
        layout_Matkhau= (TextInputLayout) findViewById(R.id.layout_matkhau);
    }
    public void initView(){
        Intent intent = getIntent();
        String sodienthoai = intent.getStringExtra("sodienthoai");
        sdt_dn.setText(sodienthoai);
    }
    public void initButton(){
        btn_actiondangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDangnhap();
            }
        });
    }

    public void initDangnhap(){
        if (Validate()){
            Dangnhap();
        }
        ChangeInput();

    }
    public void ChangeInput(){
        et_matkhau.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!et_matkhau.getText().toString().isEmpty()){
                    layout_Matkhau.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public boolean Validate(){

        if (et_matkhau.length()==0){
            layout_Matkhau.setErrorEnabled(true);
            layout_Matkhau.setError("Vui lòng nhập mật khẩu");
            return  false;
        }
        return true;
    }
    public void Dangnhap(){

        URLdatabase link = new URLdatabase();
        String url = link.getApi()+"login";
        RequestQueue requestQueue = Volley.newRequestQueue(this.getApplicationContext());
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getString("success")=="true"){
                        JSONObject user = jsonObject.getJSONObject("user");
                        //tạo user
                        userPref = getApplicationContext().getSharedPreferences("dataLogin",getApplicationContext().MODE_PRIVATE);
                        SharedPreferences oBoard = getApplicationContext().getSharedPreferences("oBoard", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editorOBoard = oBoard.edit();
                        editorOBoard.putBoolean("isFirstTime",false);
                        editorOBoard.apply();
                        SharedPreferences.Editor editorUser = userPref.edit();
                        editorUser.putString("token",jsonObject.getString("token"));
                        editorUser.putString("id",user.getString("id"));
                        editorUser.putString("sodienthoai",user.getString("sodienthoai"));
                        editorUser.putString("CMT",user.getString("CMT"));
                        editorUser.putBoolean("isLoggedIn",true);
                        editorUser.commit();
                        editorUser.apply();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);

                    }
                    if (jsonObject.getString("success")=="false"){
                        Toast.makeText(Dangnhap.this, "Mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Dangnhap.this, "Lỗi: "+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Intent intent = getIntent();
                String sodienthoai = intent.getStringExtra("sodienthoai").trim();
                String matkhau = et_matkhau.getText().toString().trim();
                HashMap<String,String> params = new HashMap<>();
                params.put("sodienthoai",sodienthoai);
                params.put("password",matkhau);
                return params;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }
}
