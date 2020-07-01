package sict.myproject.anticovid_19.Adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sict.myproject.anticovid_19.Model.Theodoisuckhoe;
import sict.myproject.anticovid_19.Model.URLdatabase;
import sict.myproject.anticovid_19.R;

public class CustomDialogXemNguoithan extends Dialog {

    CheckBox cb_sot,cb_ho,cb_khotho,cb_daunguoi,cb_khoemanh;
    Button btn_guithongtin;
    String tinhtrang=" ";
    Adapter_Theodoisuckhoe adapter_theodoisuckhoe;
    ArrayList<Theodoisuckhoe> theodoisuckhoeArrayList;
    TextView tv_xemnguoithan_tennguoithan,tv_xemnguoithan_quanhe,tv_xemnguoithan_tinhtrang;
    String tennguoithan;
    public CustomDialogXemNguoithan(@NonNull Context context,String tennguoithan) {
        super(context);
        this.tennguoithan = tennguoithan;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog_xem_nguoithan);

        Widget();
        initView();
        checkCheckbox();
        setBTNSuaTT();
    }


    private  void Widget(){

        tv_xemnguoithan_tennguoithan = (TextView) findViewById(R.id.tv_xemnguoithan_tennguoithan);
        tv_xemnguoithan_quanhe = (TextView) findViewById(R.id.tv_xemnguoithan_quanhe);
        tv_xemnguoithan_tinhtrang = (TextView) findViewById(R.id.tv_xemnguoithan_tinhtrang);

        btn_guithongtin = (Button) findViewById(R.id.btn_xemnguoithan_guithongtin);
        cb_ho = (CheckBox) findViewById(R.id.check_xemnguoithan_ho);
        cb_daunguoi = (CheckBox) findViewById(R.id.check_xemnguoithan_daunguoi);
        cb_sot = (CheckBox) findViewById(R.id.check_xemnguoithan_sot);
        cb_khotho = (CheckBox) findViewById(R.id.check_xemnguoithan_khotho);
        cb_khoemanh = (CheckBox) findViewById(R.id.check_xemnguoithan_khoemanh);
    }
    private void initView() {
        getThongtin();

    }

    private void getThongtin() {

        theodoisuckhoeArrayList = new ArrayList<>();
        URLdatabase link = new URLdatabase();
        String url = link.getApi()+"getThongtinNguoithan";

        StringRequest stringRequestgetTT = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText(getContext(), "ahah: "+response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //Toast.makeText(getContext(), "ahah: "+jsonObject.getString("quanhe"), Toast.LENGTH_SHORT).show();

                    tv_xemnguoithan_tennguoithan.setText(jsonObject.getString("tennguoithan"));
                    tv_xemnguoithan_quanhe.setText(jsonObject.getString("quanhe"));
                    if(jsonObject.getString("tinhhinh").equals("Khỏe mạnh")){
                        tv_xemnguoithan_tinhtrang.setText(jsonObject.getString("tinhhinh"));
                        tv_xemnguoithan_tinhtrang.setTextColor(Color.GREEN);
                    }
                    if(!jsonObject.getString("tinhhinh").equals("Khỏe mạnh")){
                        tv_xemnguoithan_tinhtrang.setText("Nguy cơ nhiễm bệnh");
                        tv_xemnguoithan_tinhtrang.setTextColor(Color.RED);
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
                SharedPreferences userPrefgetTT = getContext().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
                Map<String,String> params = new HashMap<>();
                String token = userPrefgetTT.getString("token",null);
                String CMT = userPrefgetTT.getString("CMT",null);

                params.put("token",token);
                params.put("CMT",CMT);
                params.put("tennguoithan",tennguoithan);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequestgetTT);
    }


    private void setBTNSuaTT() {
        btn_guithongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cb_khoemanh.isChecked() && !cb_khotho.isChecked() && !cb_daunguoi.isChecked() && !cb_sot.isChecked() && !cb_ho.isChecked()){
                    Toast.makeText(getContext(), "Bạn chưa chọn tình trang hiện tại", Toast.LENGTH_SHORT).show();
                }
                else {
                    check();
                    getKhaibaothongtin();
                    Dialog dialog = new Dialog(getContext());

                    dialog.dismiss();
                }
            }


        });
    }
    private void getKhaibaothongtin() {

        URLdatabase link = new URLdatabase();
        String url = link.getApi()+"getCapnhatsuckhoeNguoithan";

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
                params.put("tennguoithan",tv_xemnguoithan_tennguoithan.getText().toString());
                params.put("tinhhinh",tinhtrang);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
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
