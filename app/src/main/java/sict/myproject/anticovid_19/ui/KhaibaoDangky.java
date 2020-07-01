package sict.myproject.anticovid_19.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import sict.myproject.anticovid_19.Model.URLdatabase;
import sict.myproject.anticovid_19.R;

public class KhaibaoDangky extends AppCompatActivity {

    int icheck=0;
    String tinhhinh_dk=" ",dauhieu_dk=" ",macbenh_dk=" ";
    RadioGroup rdg_sex_dk;
    RadioButton rdb_nam_dk,rdb_nu_dk;
    TextInputEditText et_hoten_dk,et_soCMT_dk, et_msBHXH_dk,et_NTNS_dk,et_Quoctich_dk, et_diachi_dk, et_Email_dk;
    TextView et_didenvunglanhtho_dk;
    Button btn_Xacnhan_dk;
    CheckBox cb_camket_dk,
            cb_tiepxucnguoibenh_dk,
            cb_divetuvungdich_dk,
            cb_cotiepxucnguoidive_dk,
            cb_sot_dk,
            cb_ho_dk,
            cb_khotho_dk,
            cb_dauhong_dk,
            cb_viemphoi_dk,
            cb_metmoi_dk,
            cb_ganmantinh_dk,
            cb_phoimantinh_dk,
            cb_maumantinh_dk,
            cb_thanmantinh_dk;
    SharedPreferences userPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khaibao_dangky);

        Widget();
        initView();
        setButton();


    }
    public void Widget(){
        et_hoten_dk = (TextInputEditText) findViewById(R.id.ttcn_hoten_dk);
        et_soCMT_dk = (TextInputEditText) findViewById(R.id.ttcn_cmt_dk);
        et_msBHXH_dk = (TextInputEditText) findViewById(R.id.ttcn_bhxh_dk);
        et_NTNS_dk = (TextInputEditText) findViewById(R.id.ttcn_namsinh_dk);
        et_Quoctich_dk = (TextInputEditText) findViewById(R.id.ttcn_quoctich_dk);
        et_diachi_dk = (TextInputEditText) findViewById(R.id.ttcn_diachihientai_dk);
        et_Email_dk = (TextInputEditText) findViewById(R.id.ttcn_email_dk);
        et_didenvunglanhtho_dk = (TextView) findViewById(R.id.ttcn_diquavunglanhtho_dk);

        btn_Xacnhan_dk= (Button) findViewById(R.id.btn_xacnhankhaibao_dk);

        rdg_sex_dk = (RadioGroup) findViewById(R.id.radio_sex_dk);
        rdb_nam_dk = (RadioButton) findViewById(R.id.check_nam_dk);
        rdb_nu_dk = (RadioButton) findViewById(R.id.check_nu_dk);

        cb_camket_dk = (CheckBox) findViewById(R.id.check_camket_dk);
        cb_tiepxucnguoibenh_dk = (CheckBox) findViewById(R.id.check_tiepxucnguoibenh_dk);
        cb_divetuvungdich_dk = (CheckBox) findViewById(R.id.check_divetuvungdich_dk);
        cb_cotiepxucnguoidive_dk = (CheckBox) findViewById(R.id.check_cotiepxucnguoidive_dk);
        cb_sot_dk = (CheckBox) findViewById(R.id.check_sot_dk);
        cb_ho_dk = (CheckBox) findViewById(R.id.check_ho_dk);
        cb_khotho_dk = (CheckBox) findViewById(R.id.check_khotho_dk);
        cb_dauhong_dk = (CheckBox) findViewById(R.id.check_dauhong_dk);
        cb_viemphoi_dk = (CheckBox) findViewById(R.id.check_viemphoi_dk);
        cb_metmoi_dk = (CheckBox) findViewById(R.id.check_metmoi_dk);
        cb_ganmantinh_dk = (CheckBox) findViewById(R.id.check_benhganmantinh_dk);
        cb_phoimantinh_dk = (CheckBox) findViewById(R.id.check_benhphoimantinh_dk);
        cb_maumantinh_dk = (CheckBox) findViewById(R.id.check_benhmaumantinh_dk);
        cb_thanmantinh_dk = (CheckBox) findViewById(R.id.check_benhthanmantinh_dk);
    }
    public void setButton(){
        btn_Xacnhan_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check();
                if(icheck==1){
                    actionKhaibao();
                }
            }
        });
    }
    public void actionKhaibao(){
        URLdatabase link = new URLdatabase();
        String url = link.getApi()+"khaibaoytetunguyendangky";
        RequestQueue requestQueue = Volley.newRequestQueue(this.getApplicationContext());



        StringRequest jsonStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.e("AAAAAAAAAAAAAAAAAA", "onResponse: "+response+"  check: "+jsonObject.getBoolean("success")+" v "+ jsonObject.getString("CMT"));
                    if (jsonObject.getBoolean("success")==true && jsonObject.getString("CMT").equals("NotExist")){
                        JSONObject user = jsonObject.getJSONObject("user");

                        userPref = getApplicationContext().getSharedPreferences("dataLogin",getApplicationContext().MODE_PRIVATE);
                        SharedPreferences oBoard = getApplicationContext().getSharedPreferences("oBoard", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editorOBoard = oBoard.edit();
                        editorOBoard.putBoolean("isFirstTime",false);
                        editorOBoard.apply();
                        SharedPreferences.Editor editor = userPref.edit();
                        editor.putString("token",jsonObject.getString("token"));
                        editor.putString("id",user.getString("id"));
                        editor.putString("sodienthoai",user.getString("sodienthoai"));
                        editor.putString("CMT",user.getString("CMT"));
                        editor.putBoolean("isLoggedIn",true);
                        editor.commit();
                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(),xacnhankhaibao.class);
                        startActivity(intent);
                    }
                    if(jsonObject.getBoolean("success")==false && jsonObject.getString("CMT")=="Exist") {
                        Toast.makeText(KhaibaoDangky.this, "Số chứng minh thư này đã có người đăng ký", Toast.LENGTH_SHORT).show();
                    }
                    if(!jsonObject.getBoolean("success")==false && jsonObject.getString("CMT")=="NotExist1") {
                        Toast.makeText(KhaibaoDangky.this, "Thất bại 1", Toast.LENGTH_SHORT).show();
                    }
                    if(!jsonObject.getBoolean("success")==false && jsonObject.getString("CMT")=="null") {
                        Toast.makeText(KhaibaoDangky.this, "Thất bại 4", Toast.LENGTH_SHORT).show();
                    }
                    if(!jsonObject.getBoolean("success")==false && jsonObject.getString("CMT")=="NotExist2") {
                        Toast.makeText(KhaibaoDangky.this, "Thất bại 2", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("AAAAAAAAA", "onErrorResponse: "+error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //thongtincanhan
                String hovaten = et_hoten_dk.getText().toString().trim();
                String CMT = et_soCMT_dk.getText().toString().trim();
                String BHXH = et_msBHXH_dk.getText().toString().trim();
                String ngaysinh = et_NTNS_dk.getText().toString().trim();
                String gioitinh="Nam";
                if (rdb_nam_dk.isChecked()){
                    gioitinh = "Nam";
                }
                if (rdb_nu_dk.isChecked()){
                    gioitinh = "Nữ";
                }
                String quoctich = et_Quoctich_dk.getText().toString().trim();
                String diachi = et_diachi_dk.getText().toString().trim();
                String email = et_Email_dk.getText().toString().trim();
                //thongtincanhan
                String didenvungquocgia = et_didenvunglanhtho_dk.getText().toString().trim();

                Intent intent = getIntent();
                String sdt = intent.getStringExtra("sodienthoai");
                String matkhau = intent.getStringExtra("matkhau");


                Map<String,String> params = new HashMap<>();
                //thongtincanhan
                params.put("hovaten",hovaten);
                params.put("CMT",CMT);
                params.put("BHXH",BHXH);
                params.put("ngaysinh",ngaysinh);
                params.put("gioitinh",gioitinh);
                params.put("quoctich",quoctich);
                params.put("diachi",diachi);
                params.put("email",email);
                //thongtinbenh
                params.put("tinhhinh",tinhhinh_dk);
                params.put("didenvungquocgia",didenvungquocgia);
                params.put("dauhieu",dauhieu_dk);
                params.put("macbenh",macbenh_dk);
                params.put("sodienthoai",sdt);
                params.put("password",matkhau);
                return params;
            }
        };
        requestQueue.add(jsonStringRequest);


    }
    public void initView(){
        rdb_nam_dk.setChecked(true);
    }
    public void Check(){


        if (isEmpty(et_hoten_dk) || isEmpty(et_soCMT_dk) || isEmpty(et_NTNS_dk) || isEmpty(et_Quoctich_dk) || isEmpty(et_diachi_dk) ){
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin đánh dấu *", Toast.LENGTH_SHORT).show();
        }
        if (!cb_camket_dk.isChecked()){
            Toast.makeText(this, "Chưa đồng ý cam kết", Toast.LENGTH_SHORT).show();
        }
        else {
            icheck = 1;
        }
        if (cb_tiepxucnguoibenh_dk.isChecked()){
            tinhhinh_dk = tinhhinh_dk+cb_tiepxucnguoibenh_dk.getText().toString()+", ";
        }
        if (cb_divetuvungdich_dk.isChecked()){
            tinhhinh_dk+=cb_divetuvungdich_dk.getText().toString()+", ";
        }
        if (cb_tiepxucnguoibenh_dk.isChecked()){
            tinhhinh_dk+=cb_tiepxucnguoibenh_dk.getText().toString();
        }
        if (cb_sot_dk.isChecked()){
            dauhieu_dk+=cb_sot_dk.getText().toString()+", ";
        }
        if (cb_dauhong_dk.isChecked()){
            dauhieu_dk+=cb_dauhong_dk.getText().toString()+", ";
        }
        if (cb_ho_dk.isChecked()){
            dauhieu_dk+=cb_ho_dk.getText().toString()+", ";
        }
        if (cb_khotho_dk.isChecked()){
            dauhieu_dk+=cb_khotho_dk.getText().toString()+", ";
        }
        if (cb_viemphoi_dk.isChecked()){
            dauhieu_dk+=cb_viemphoi_dk.getText().toString()+", ";
        }
        if (cb_metmoi_dk.isChecked()){
            dauhieu_dk+=cb_metmoi_dk.getText().toString()+", ";
        }
        if (cb_viemphoi_dk.isChecked()){
            macbenh_dk+=cb_viemphoi_dk.getText().toString()+", ";
        }
        if (cb_ganmantinh_dk.isChecked()){
            macbenh_dk+=cb_ganmantinh_dk.getText().toString()+", ";
        }
        if (cb_maumantinh_dk.isChecked()){
            macbenh_dk+=cb_maumantinh_dk.getText().toString()+", ";
        }
        if (cb_phoimantinh_dk.isChecked()){
            macbenh_dk+=cb_phoimantinh_dk.getText().toString()+", ";
        }
        if (cb_thanmantinh_dk.isChecked()){
            macbenh_dk+=cb_thanmantinh_dk.getText().toString()+", ";
        }
    }
    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
}
