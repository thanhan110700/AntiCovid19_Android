package sict.myproject.anticovid_19.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import sict.myproject.anticovid_19.Model.URLdatabase;
import sict.myproject.anticovid_19.R;

public class Frag_Request_khaibaotiepxuc extends Fragment {
    CheckBox cb_coditunuocngoai,cb_cotiepxuc,cb_cobietxungquanh,cb_camket;
    Button btn_guithongtin;
    String coditunuocngoai="",cotiepxuc="",cobietxungquanh="",khaibao=coditunuocngoai+" "+cotiepxuc+" "+cobietxungquanh;
    int check=0;
    private View ViewRoot;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewRoot = inflater.inflate(R.layout.activity_frag_request_khaibaotiepxuc,container,false);

        Widget();
        setBtn();
        return ViewRoot;
    }

    private void Widget() {
        cb_camket = (CheckBox) ViewRoot.findViewById(R.id.check_khaibaotiepxuc_camket);
        cb_coditunuocngoai = (CheckBox) ViewRoot.findViewById(R.id.check_khaibaotiepxuc_coditunuocngoai);
        cb_cotiepxuc = (CheckBox) ViewRoot.findViewById(R.id.check_khaibaotiepxuc_cotiepxuc);
        cb_cobietxungquanh = (CheckBox) ViewRoot.findViewById(R.id.check_khaibaotiepxuc_cobietxungquanh);

        btn_guithongtin = (Button) ViewRoot.findViewById(R.id.btn_khaibaotiepxuc_guithongtinkhaibao);
    }
    private void setBtn() {
        btn_guithongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
                if (check==1){
                    getKhaibaotiepxuc();
                    resetCheckbox();
                }

            }
        });
    }

    private void getKhaibaotiepxuc(){
        URLdatabase link = new URLdatabase();
        final String url = link.getApi()+"getKhaibaotiepxuc";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("success")){
                        Toast.makeText(getContext(), "Khai báo thành công", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(), "Khai báo thất bại", Toast.LENGTH_SHORT).show();
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
                khaibao = cobietxungquanh+" "+ coditunuocngoai+" "+cotiepxuc;
                SharedPreferences userPref = getContext().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
                Map<String,String> params = new HashMap<>();
                params.put("khaibao",khaibao.trim());
                params.put("CMT",userPref.getString("CMT",null));

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void check(){
        if (!cb_camket.isChecked()){
            Toast.makeText(getContext(), "Bạn chưa đồng ý với cam kết", Toast.LENGTH_SHORT).show();
        }
        if (!cb_cobietxungquanh.isChecked() && !cb_cotiepxuc.isChecked() &&!cb_coditunuocngoai.isChecked()){
            check = 0;
            Toast.makeText(getContext(), "Vui lòng chọn nội dung khai báo", Toast.LENGTH_SHORT).show();
        }
        if (cb_coditunuocngoai.isChecked()){
            coditunuocngoai = "Có đi từ nước ngoài về Việt Nam";
        }
        if (!cb_coditunuocngoai.isChecked()){
            coditunuocngoai = "";
        }
        if (cb_cotiepxuc.isChecked()){
            cotiepxuc = "Có tiếp xúc trường hợp đi từ nước ngoài về Việt Nam";
        }
        if (!cb_cotiepxuc.isChecked()){
            cotiepxuc = "";
        }
        if (cb_cobietxungquanh.isChecked()){
            cobietxungquanh = "Có biết xung quanh có trường hợp đi từ nước ngoài về Việt Nam";
        }
        if (!cb_cobietxungquanh.isChecked()){
            cobietxungquanh = "";
        }
        if (cb_camket.isChecked() && (cb_cobietxungquanh.isChecked() || cb_cotiepxuc.isChecked() || cb_coditunuocngoai.isChecked())) {
            check = 1;
        }
    }
    private void resetCheckbox(){
        cb_coditunuocngoai.setChecked(false);
        cb_cotiepxuc.setChecked(false);
        cb_cobietxungquanh.setChecked(false);
        cb_camket.setChecked(false);
    }



}
