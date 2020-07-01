package sict.myproject.anticovid_19.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import sict.myproject.anticovid_19.Adapter.AdapterNguoithan;
import sict.myproject.anticovid_19.Adapter.CustomDialogNguoithan;
import sict.myproject.anticovid_19.Adapter.CustomDialogXemNguoithan;
import sict.myproject.anticovid_19.Model.Nguoithan;
import sict.myproject.anticovid_19.Model.URLdatabase;
import sict.myproject.anticovid_19.R;

public class Frag_Healthy_Nguoithan extends Fragment {
    private View ViewRoot;

    ListView listView;
    Button btn_add;
    List<Nguoithan> listNguoithan;
    AdapterNguoithan adapterNguoithan;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewRoot = inflater.inflate(R.layout.activity_frag_healthy_nguoithan,container,false);

        Widget();
        initView();
        initSetButtonAdd();
        return ViewRoot;
    }

    private void Widget() {
        listView = (ListView) ViewRoot.findViewById(R.id.lv_nguoithan);
        btn_add = (Button) ViewRoot.findViewById(R.id.btn_add_nguoithan);


    }

    private void initView() {

        URLdatabase link = new URLdatabase();
        String urlViewNguoithan = link.api+"getNguoithan";
        StringRequest viewNguoithan = new StringRequest(Request.Method.POST, urlViewNguoithan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArrayNguoithan = new JSONArray(response);
                    int length = jsonArrayNguoithan.length();
                    //Log.e("AAAAAAAAAAA", "lenght : "+length );
                    listNguoithan = new ArrayList<>();
                    for (int i = 0;i<jsonArrayNguoithan.length();i++){
                        JSONObject jsonObject = jsonArrayNguoithan.getJSONObject(i);
                       // Log.e("BBBBBBBB", "onResponse: "+jsonObject);

                        listNguoithan.add(new Nguoithan(i,jsonObject.getString("tennguoithan")));
                    }
                    adapterNguoithan = new AdapterNguoithan(listNguoithan,getContext());
                    listView.setAdapter(adapterNguoithan);

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
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(viewNguoithan);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tennguoithan = listNguoithan.get(position).getmName();
                CustomDialogXemNguoithan customDialogXemNguoithan = new CustomDialogXemNguoithan(getContext(),tennguoithan);
                customDialogXemNguoithan.show();
            }
        });
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = new MenuInflater(getContext());
        menuInflater.inflate(R.menu.menu_nguoithan,menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.menu_nguoithan_xoa:

                adapterNguoithan.notifyDataSetChanged();
                String tennguoithan =listNguoithan.get(info.position).getmName();

                getRemoveNguoithan(tennguoithan);
                listNguoithan.remove(info.position);
        }

        return super.onContextItemSelected(item);
    }

    private void getRemoveNguoithan(final String Tennguoithan) {

        URLdatabase link = new URLdatabase();
        String urlGetXoanguoithan = link.api+"GetXoanguoithan";
        StringRequest stringXoanguoithan = new StringRequest(Request.Method.POST, urlGetXoanguoithan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("success")){
                        Toast.makeText(getContext(), "Xóa thành công!!!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(), "Xóa Thất bại", Toast.LENGTH_SHORT).show();
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
                String tennguoithan = Tennguoithan;
                Map<String,String> params = new HashMap<>();
                params.put("CMT",CMT);
                params.put("tennguoithan",tennguoithan);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringXoanguoithan);
    }

    private void initSetButtonAdd(){
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });


    }


    private void showDialog(){


        CustomDialogNguoithan customDialogNguoithan = new CustomDialogNguoithan(getContext(),listNguoithan,adapterNguoithan);
        customDialogNguoithan.show();

    }



}
