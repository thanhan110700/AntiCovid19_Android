package sict.myproject.anticovid_19.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sict.myproject.anticovid_19.Model.Nguoithan;
import sict.myproject.anticovid_19.Model.URLdatabase;
import sict.myproject.anticovid_19.R;

public class CustomDialogNguoithan extends Dialog {
    CheckBox cb_sot,cb_ho,cb_khotho,cb_daunguoi,cb_khoemanh;
    Button btn_action_add;
    EditText et_hotennguoithan,et_moiquanhe;
    String tinhhinh="",sot,ho,khotho,daunguoi,khoemanh;
    List<Nguoithan> listNguoithan;
    AdapterNguoithan adapterNguoithan;
    public CustomDialogNguoithan(@NonNull Context context,List<Nguoithan> listNguoithan,AdapterNguoithan adapterNguoithan) {
        super(context);
        this.listNguoithan = listNguoithan;
        this.adapterNguoithan = adapterNguoithan;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_addnguoithan);

        cb_ho = findViewById(R.id.check_nguoithan_ho);
        cb_daunguoi = findViewById(R.id.check_nguoithan_daunguoi);
        cb_sot = findViewById(R.id.check_nguoithan_sot);
        cb_khotho = findViewById(R.id.check_nguoithan_khotho);
        cb_khoemanh = findViewById(R.id.check_nguoithan_khoemanh);
        btn_action_add = (Button)findViewById(R.id.btn_guithongtinnguoithan);
        et_hotennguoithan = (EditText) findViewById(R.id.et_hotennguoithan);
        et_moiquanhe = (EditText) findViewById(R.id.et_moiquanhe);

        checkCheckbox();

        btn_action_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTinhtrang();

                if (checkND()){
                    tinhhinh=sot+" "+ho+" "+khotho+" "+daunguoi+" "+khoemanh;
                    getGuithongtin();

                }
                else Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getGuithongtin(){

        final URLdatabase link = new URLdatabase();
        String url = link.api+"getGuithongtinNguoithan";
        StringRequest getKhaibaonguoithan = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("success")){
                        Toast.makeText(getContext(), "Khai báo thành công", Toast.LENGTH_SHORT).show();
                        listNguoithan.add(new Nguoithan(listNguoithan.get(listNguoithan.size()-1).getmID()+1,et_hotennguoithan.getText().toString().trim()));
                        adapterNguoithan.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
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
                SharedPreferences userPref = getContext().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
                String CMT = userPref.getString("CMT",null);
                Map<String,String> params = new HashMap<>();
                params.put("CMT",CMT);
                params.put("tennguoithan",et_hotennguoithan.getText().toString().trim());
                params.put("quanhe",et_moiquanhe.getText().toString().trim());
                params.put("tinhhinh",tinhhinh.trim());
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(getKhaibaonguoithan);
    }

    private boolean checkND(){
        if(!cb_ho.isChecked() &&!cb_sot.isChecked() &&!cb_daunguoi.isChecked() &&!cb_khotho.isChecked() &&!cb_khoemanh.isChecked())
        {
            return false;
        }
        if (et_hotennguoithan.length()==0 || et_moiquanhe.length()==0){
            return false;
        }
        return true;
    }

    private void checkTinhtrang(){
        if (cb_sot.isChecked()){
            sot= cb_sot.getText().toString();
        }if (!cb_sot.isChecked()){
            sot= "";
        }
        if (cb_ho.isChecked()){
            ho= cb_ho.getText().toString();
        }if (!cb_ho.isChecked()){
            ho= "";
        }
        if (cb_daunguoi.isChecked()){
            daunguoi=cb_daunguoi.getText().toString();
        }if (!cb_daunguoi.isChecked()){
            daunguoi= "";
        }
        if (cb_khotho.isChecked()){
            khotho=cb_khotho.getText().toString();
        }if (!cb_khotho.isChecked()){
            khotho= "";
        }

    }
    private void checkCheckbox(){
        cb_khoemanh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    khoemanh = cb_khoemanh.getText().toString();
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
                    tinhhinh="";
                    cb_sot.setEnabled(true);
                    cb_ho.setEnabled(true);
                    cb_khotho.setEnabled(true);
                    cb_daunguoi.setEnabled(true);
                }
            }
        });
    }
}
