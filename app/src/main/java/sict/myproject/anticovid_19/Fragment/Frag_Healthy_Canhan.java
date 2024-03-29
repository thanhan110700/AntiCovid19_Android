package sict.myproject.anticovid_19.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import sict.myproject.anticovid_19.Adapter.Adapter_Theodoisuckhoe;
import sict.myproject.anticovid_19.Model.Theodoisuckhoe;
import sict.myproject.anticovid_19.Model.URLdatabase;
import sict.myproject.anticovid_19.R;

public class Frag_Healthy_Canhan extends Fragment {
    private View ViewRoot;

    CheckBox cb_sot,cb_ho,cb_khotho,cb_daunguoi,cb_khoemanh;
    Button btn_guithongtin;
    String tinhtrang=" ";
    Adapter_Theodoisuckhoe adapter_theodoisuckhoe;
    RecyclerView recyclerView;

    ArrayList<Theodoisuckhoe> theodoisuckhoeArrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewRoot = inflater.inflate(R.layout.activity_frag_healthy_canhan,container,false);
        Widget();
        checkCheckbox();
        setBtn_guithongtin();
        initView();

        return ViewRoot;
    }
    private  void Widget(){
        recyclerView = ViewRoot.findViewById(R.id.rcv_lichsutheodoisuckhoe);
        btn_guithongtin = ViewRoot.findViewById(R.id.btn_healthy_guithongtin);
        cb_ho = ViewRoot.findViewById(R.id.check_healthy_ho);
        cb_daunguoi = ViewRoot.findViewById(R.id.check_healthy_daunguoi);
        cb_sot = ViewRoot.findViewById(R.id.check_healthy_sot);
        cb_khotho = ViewRoot.findViewById(R.id.check_healthy_khotho);
        cb_khoemanh = ViewRoot.findViewById(R.id.check_healthy_khoemanh);
    }

    private void initView() {
        getThongtin();

    }
    private void addList(){
        String trangthai="";
        if (cb_khoemanh.isChecked()){
            trangthai = "An toàn";
        }
        else trangthai = "Nguy cơ nhiễm bệnh";

        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        String dateTime = currentDate+" "+currentTime;
        theodoisuckhoeArrayList.add(new Theodoisuckhoe(trangthai,dateTime,tinhtrang));
        adapter_theodoisuckhoe.notifyDataSetChanged();
    }

    private void setBtn_guithongtin(){

        btn_guithongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cb_khoemanh.isChecked() && !cb_khotho.isChecked() && !cb_daunguoi.isChecked() && !cb_sot.isChecked() && !cb_ho.isChecked()){
                    Toast.makeText(getContext(), "Bạn chưa chọn tình trang hiện tại", Toast.LENGTH_SHORT).show();
                }
                else {
                    check();
                    getKhaibaothongtin();
                    addList();
                }

            }
        });
        }
    private void getKhaibaothongtin(){

        URLdatabase link = new URLdatabase();
        String url = link.getApi()+"getTheodoisuckhoe";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    Boolean check = jsonObject.getBoolean("success");
                    if (check){
                        Toast.makeText(getContext(), "Cập nhật tình trạng sức khỏe thành công", Toast.LENGTH_SHORT).show();
                        cb_sot.setChecked(false);
                        cb_ho.setChecked(false);
                        cb_khotho.setChecked(false);
                        cb_daunguoi.setChecked(false);
                        cb_khoemanh.setChecked(false);
                        adapter_theodoisuckhoe.notifyDataSetChanged();

                    }
                    else {
                        Toast.makeText(getContext(), "Cập nhật tình trạng sức khỏe thất bại", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(getContext(), "Lỗi: "+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SharedPreferences   userPref = getContext().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
                String token = userPref.getString("token",null);
                String CMT = userPref.getString("CMT",null);
                Map<String,String> params = new HashMap<>();
                params.put("token",token);
                params.put("CMT",CMT);
                params.put("tinhhinh",tinhtrang);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }


    private void getThongtin() {

        theodoisuckhoeArrayList = new ArrayList<>();
        URLdatabase link = new URLdatabase();
        String url = link.getApi()+"getThongtintheodoisuckhoe";

        StringRequest stringRequestgetTT = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(), "ahah: "+response, Toast.LENGTH_SHORT).show();
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    recyclerView.setHasFixedSize(true);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                    layoutManager.setReverseLayout(true);
                    layoutManager.setStackFromEnd(true);
                    recyclerView.setLayoutManager(layoutManager);

                    theodoisuckhoeArrayList = new ArrayList<>();
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String tinhtrang = "";
                        if (jsonObject.getString("tinhhinhsuckhoe").equals("Khỏe mạnh"))
                        {
                            tinhtrang = "An toàn";
                        }
                        else {
                            tinhtrang = "Nguy cơ nhiễm bệnh";
                        }


                        theodoisuckhoeArrayList.add(new Theodoisuckhoe(tinhtrang,jsonObject.getString("ngaycapnhat"),jsonObject.getString("tinhhinhsuckhoe")));

                    }
                    adapter_theodoisuckhoe =new Adapter_Theodoisuckhoe(getContext(),theodoisuckhoeArrayList);

                    recyclerView.setAdapter(adapter_theodoisuckhoe);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Lỗi: "+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SharedPreferences userPrefgetTT = getContext().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
                Map<String,String> params = new HashMap<>();
                String token = userPrefgetTT.getString("token",null);
                String CMT = userPrefgetTT.getString("CMT",null);

                params.put("token",token);
                params.put("CMT",CMT);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequestgetTT);
    }
    private void check(){

        if (cb_sot.isChecked()){
            tinhtrang += cb_sot.getText().toString()+" ";
        }
        if (cb_ho.isChecked()){
            tinhtrang += cb_ho.getText().toString()+" ";
        }
        if (cb_daunguoi.isChecked()){
            tinhtrang +=cb_daunguoi.getText().toString()+" ";
        }
        if (cb_khotho.isChecked()){
            tinhtrang +=cb_khotho.getText().toString()+" ";
        }

    }
    private void checkCheckbox(){
        cb_khoemanh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    tinhtrang = cb_khoemanh.getText().toString();
                    cb_sot.setChecked(false);
                    cb_ho.setChecked(false);
                    cb_khotho.setChecked(false);
                    cb_daunguoi.setChecked(false);
                    cb_sot.setEnabled(false);
                    cb_ho.setEnabled(false);
                    cb_khotho.setEnabled(false);
                    cb_daunguoi.setEnabled(false);
                }
                else {
                    tinhtrang="";
                    cb_sot.setEnabled(true);
                    cb_ho.setEnabled(true);
                    cb_khotho.setEnabled(true);
                    cb_daunguoi.setEnabled(true);
                }
            }
        });
    }
}
