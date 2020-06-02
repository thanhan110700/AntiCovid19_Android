package sict.myproject.anticovid_19.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import sict.myproject.anticovid_19.Model.Thongtinnguoidung;
import sict.myproject.anticovid_19.Model.URLdatabase;
import sict.myproject.anticovid_19.R;


public class Khaibao extends AppCompatActivity {
    int icheck=0;
    String tinhhinh=" ",dauhieu=" ",macbenh=" ";
    RadioGroup rdg_sex;
    RadioButton rdb_nam,rdb_nu;
    EditText et_hoten,et_soCMT, et_msBHXH,et_NTNS,et_Quoctich, et_diachi, et_sdt, et_Email,et_didenvunglanhtho;
    Button btn_Xacnhan;
    CheckBox cb_camket,
            cb_tiepxucnguoibenh,
            cb_divetuvungdich,
            cb_cotiepxucnguoidive,
            cb_sot,
            cb_ho,
            cb_khotho,
            cb_dauhong,
            cb_viemphoi,
            cb_metmoi,
            cb_ganmantinh,
            cb_phoimantinh,
            cb_maumantinh,
            cb_thanmantinh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khaibao);

        Widget();
        initView();
        setButton();
    }

    public void initView(){
        rdb_nam.setChecked(true);
    }

    public void Widget(){
        et_hoten = (EditText) findViewById(R.id.ttcn_hoten);
        et_soCMT = (EditText) findViewById(R.id.ttcn_cmt);
        et_msBHXH = (EditText) findViewById(R.id.ttcn_bhxh);
        et_NTNS = (EditText) findViewById(R.id.ttcn_namsinh);
        et_Quoctich = (EditText) findViewById(R.id.ttcn_quoctich);
        et_diachi = (EditText) findViewById(R.id.ttcn_diachihientai);
        et_sdt = (EditText) findViewById(R.id.ttcn_sdt);
        et_Email = (EditText) findViewById(R.id.ttcn_email);
        et_didenvunglanhtho = (EditText) findViewById(R.id.ttcn_diquavunglanhtho);

        btn_Xacnhan= (Button) findViewById(R.id.btn_xacnhankhaibao);

        rdg_sex = (RadioGroup) findViewById(R.id.radio_sex);
        rdb_nam = (RadioButton) findViewById(R.id.check_nam);
        rdb_nu = (RadioButton) findViewById(R.id.check_nu);

        cb_camket = (CheckBox) findViewById(R.id.check_camket);
        cb_tiepxucnguoibenh = (CheckBox) findViewById(R.id.check_tiepxucnguoibenh);
        cb_divetuvungdich = (CheckBox) findViewById(R.id.check_divetuvungdich);
        cb_cotiepxucnguoidive = (CheckBox) findViewById(R.id.check_cotiepxucnguoidive);
        cb_sot = (CheckBox) findViewById(R.id.check_sot);
        cb_ho = (CheckBox) findViewById(R.id.check_ho);
        cb_khotho = (CheckBox) findViewById(R.id.check_khotho);
        cb_dauhong = (CheckBox) findViewById(R.id.check_dauhong);
        cb_viemphoi = (CheckBox) findViewById(R.id.check_viemphoi);
        cb_metmoi = (CheckBox) findViewById(R.id.check_metmoi);
        cb_ganmantinh = (CheckBox) findViewById(R.id.check_benhganmantinh);
        cb_phoimantinh = (CheckBox) findViewById(R.id.check_benhphoimantinh);
        cb_maumantinh = (CheckBox) findViewById(R.id.check_benhmaumantinh);
        cb_thanmantinh = (CheckBox) findViewById(R.id.check_benhthanmantinh);
    }
    public void setButton(){
        btn_Xacnhan.setOnClickListener(new View.OnClickListener() {
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
        String url = link.getUrl()+"khaibaoytetunguyen";
        RequestQueue requestQueue = Volley.newRequestQueue(this.getApplicationContext());



        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //thongtincanhan
                String hovaten = et_hoten.getText().toString().trim();
                String CMT = et_soCMT.getText().toString().trim();
                String BHXH = et_msBHXH.getText().toString().trim();
                String ngaysinh = et_NTNS.getText().toString().trim();
                String gioitinh="Nam";
                if (rdb_nam.isChecked()){
                    gioitinh = "Nam";
                }
                if (rdb_nu.isChecked()){
                    gioitinh = "Nữ";
                }
                String quoctich = et_Quoctich.getText().toString().trim();
                String diachi = et_diachi.getText().toString().trim();
                String sodienthoai = et_sdt.getText().toString().trim();
                String email = et_Email.getText().toString().trim();
                //thongtincanhan
                String didenvungquocgia = et_didenvunglanhtho.getText().toString().trim();

                Map<String,String> params = new HashMap<>();
                //thongtincanhan
                params.put("hovaten",hovaten);
                params.put("CMT",CMT);
                params.put("BHXH",BHXH);
                params.put("ngaysinh",ngaysinh);
                params.put("gioitinh",gioitinh);
                params.put("quoctich",quoctich);
                params.put("diachi",diachi);
                params.put("sodienthoai",sodienthoai);
                params.put("email",email);
                //thongtinbenh
                params.put("tinhhinh",tinhhinh);
                params.put("didenvungquocgia",didenvungquocgia);
                params.put("dauhieu",dauhieu);
                params.put("macbenh",macbenh);
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);


    }
    public void Check(){


        if (isEmpty(et_hoten) || isEmpty(et_soCMT) || isEmpty(et_NTNS) || isEmpty(et_Quoctich) || isEmpty(et_diachi) || isEmpty(et_sdt)){
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin đánh dấu *", Toast.LENGTH_SHORT).show();
        }
        if (!cb_camket.isChecked()){
            Toast.makeText(this, "Chưa đồng ý cam kết", Toast.LENGTH_SHORT).show();
        }
        else {
            icheck = 1;
        }
        if (cb_tiepxucnguoibenh.isChecked()){
            tinhhinh = tinhhinh+cb_tiepxucnguoibenh.getText().toString()+", ";
        }
        if (cb_divetuvungdich.isChecked()){
            tinhhinh+=cb_divetuvungdich.getText().toString()+", ";
        }
        if (cb_tiepxucnguoibenh.isChecked()){
            tinhhinh+=cb_tiepxucnguoibenh.getText().toString();
        }
        if (cb_sot.isChecked()){
            dauhieu+=cb_sot.getText().toString()+", ";
        }
        if (cb_dauhong.isChecked()){
            dauhieu+=cb_dauhong.getText().toString()+", ";
        }
        if (cb_ho.isChecked()){
            dauhieu+=cb_ho.getText().toString()+", ";
        }
        if (cb_khotho.isChecked()){
            dauhieu+=cb_khotho.getText().toString()+", ";
        }
        if (cb_viemphoi.isChecked()){
            dauhieu+=cb_viemphoi.getText().toString()+", ";
        }
        if (cb_metmoi.isChecked()){
            dauhieu+=cb_metmoi.getText().toString()+", ";
        }
        if (cb_viemphoi.isChecked()){
            macbenh+=cb_viemphoi.getText().toString()+", ";
        }
        if (cb_ganmantinh.isChecked()){
            macbenh+=cb_ganmantinh.getText().toString()+", ";
        }
        if (cb_maumantinh.isChecked()){
            macbenh+=cb_maumantinh.getText().toString()+", ";
        }
        if (cb_phoimantinh.isChecked()){
            macbenh+=cb_phoimantinh.getText().toString()+", ";
        }
        if (cb_thanmantinh.isChecked()){
            macbenh+=cb_thanmantinh.getText().toString()+", ";
        }
    }
    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
}
