package sict.myproject.anticovid_19.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import sict.myproject.anticovid_19.MainActivity;
import sict.myproject.anticovid_19.Model.URLdatabase;
import sict.myproject.anticovid_19.R;

public class Frag_Request_Phananh extends Fragment {
    private View ViewRoot;
    private Button btn_call,btn_guithongtin_request;
    TextInputEditText et_noidungphananh,et_diadiemxayra,et_chonngay;
    TextInputLayout layout_NDphananh,layout_DDxayra,layout_chonngay;
    CheckBox cb_conguoitiepxuc,cb_divetuvungdich,cb_txdivetuvungdich,cb_camket;
    String truonghopphananh="",txdivetuvungdich,divetuvungdich,conguoitiepxuc;
    int check =0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewRoot = inflater.inflate(R.layout.activity_frag_request_phananh,container,false);
        Widget();
        setButton();
        return ViewRoot;
    }
    public void Widget(){
        btn_call = (Button) ViewRoot.findViewById(R.id.btn_call);

        et_chonngay = (TextInputEditText) ViewRoot.findViewById(R.id.et_request_chonngay);
        et_noidungphananh = (TextInputEditText) ViewRoot.findViewById(R.id.edit_request_noidungphananh);
        et_diadiemxayra = (TextInputEditText) ViewRoot.findViewById(R.id.edit_request_diadiemxayra);

        layout_NDphananh = (TextInputLayout) ViewRoot.findViewById(R.id.layout_NDphananh);
        layout_DDxayra = (TextInputLayout) ViewRoot.findViewById(R.id.layout_DDxayra);
        layout_chonngay = (TextInputLayout) ViewRoot.findViewById(R.id.layout_chonngay);

        btn_guithongtin_request = (Button) ViewRoot.findViewById(R.id.btn_request_guithongtin);
        cb_conguoitiepxuc = (CheckBox) ViewRoot.findViewById(R.id.check_request_conguoitiepxuc);
        cb_divetuvungdich = (CheckBox) ViewRoot.findViewById(R.id.check_request_cotruonghopdivetuvungdich);
        cb_txdivetuvungdich = (CheckBox) ViewRoot.findViewById(R.id.check_request_conguoitiepxucdive);
        cb_camket = (CheckBox) ViewRoot.findViewById(R.id.check_request_phananh_camket);
    }
    public void setButton(){
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonenumber = "tel:19009095";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phonenumber));
                startActivity(intent);
            }
        });
        et_chonngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonngay();

            }

        });

        btn_guithongtin_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckCamket();
                if (check==1){
                    if (Validate()){
                        truonghopphananh=conguoitiepxuc+divetuvungdich+txdivetuvungdich;
                        getGuithongtin();
                    }
                        ChangeInput();
                }
            }
        });
    }

    private void getGuithongtin(){
        URLdatabase link = new URLdatabase();
        String url = link.api+"getGuithongtinRequest";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject= new JSONObject(response);
                    if (jsonObject.getBoolean("success")){
                        Toast.makeText(getContext(), "OK", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
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
                Map<String,String> params = new HashMap<>();
                params.put("CMT",userPref.getString("CMT",null));
                params.put("truonghopphananh",truonghopphananh);
                params.put("noidungphananh",et_noidungphananh.getText().toString());
                params.put("thoigianphathien",et_chonngay.getText().toString());
                params.put("diadiemphathien",et_diadiemxayra.getText().toString());

                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void chonngay() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
                        et_chonngay.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };
                new TimePickerDialog(getContext(),timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
    public void ChangeInput(){
        et_noidungphananh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!et_noidungphananh.getText().toString().isEmpty()){
                    layout_NDphananh.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_diadiemxayra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!et_diadiemxayra.getText().toString().isEmpty()){
                    layout_DDxayra.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_chonngay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!et_chonngay.getText().toString().isEmpty()){
                    layout_chonngay.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public boolean Validate(){


        if (et_diadiemxayra.length()==0){
            layout_DDxayra.setErrorEnabled(true);
            layout_DDxayra.setError("Vui lòng nhập địa điểm xảy ra");
            return  false;
        }
        if (et_noidungphananh.length()==0){
            layout_NDphananh.setErrorEnabled(true);
            layout_NDphananh.setError("Vui lòng nhập nội dung phản ánh");
            return  false;
        }
        if (et_chonngay.length()==0){
            layout_chonngay.setErrorEnabled(true);
            layout_chonngay.setError("Vui lòng chọn ngày, giờ phản ánh");
            return  false;
        }



        else  return true;
    }

    private void CheckCamket() {
        if (!cb_camket.isChecked()){
            Toast.makeText(getContext(), "Chưa đồng ý cam kết", Toast.LENGTH_SHORT).show();
            check=0;
        }
        if (!cb_divetuvungdich.isChecked() && !cb_txdivetuvungdich.isChecked() && !cb_conguoitiepxuc.isChecked()){
            Toast.makeText(getContext(), "Vui lòng chọn trường hợp phản ánh", Toast.LENGTH_SHORT).show();
            check=0;
        }
        if (cb_conguoitiepxuc.isChecked()){
            conguoitiepxuc="Có người tiếp xúc với trường hợp bệnh hoặc nghi ngờ mắc COVID-19. ";
        }if (!cb_conguoitiepxuc.isChecked()){
            conguoitiepxuc="";
        }
        if (cb_divetuvungdich.isChecked()){
            divetuvungdich="Có trường hợp đi về từ vùng dịch. ";
        }if (!cb_divetuvungdich.isChecked()){
            divetuvungdich="";
        }
        if (cb_txdivetuvungdich.isChecked()){
            txdivetuvungdich="Có người tiếp xúc với trường hợp đi về từ vùng dịch COVID-19. ";
        }if (!cb_txdivetuvungdich.isChecked()){
            txdivetuvungdich="";
        }
        if (cb_camket.isChecked()){
            check=1;
        }
    }
}
