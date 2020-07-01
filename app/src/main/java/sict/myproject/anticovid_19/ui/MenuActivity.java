package sict.myproject.anticovid_19.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import sict.myproject.anticovid_19.MainActivity;
import sict.myproject.anticovid_19.Model.URLdatabase;
import sict.myproject.anticovid_19.R;

public class MenuActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Button btn_logout;
    SharedPreferences userPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Widget();
        initBottomNavigation();
        setBtn_logout();
    }
    public void Widget(){
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        btn_logout = (Button) findViewById(R.id.btn_menu_logout);
    }

    public void setBtn_logout(){
        URLdatabase urLdatabase = new URLdatabase();
        final String url = urLdatabase.getApi()+"logout";
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean check = jsonObject.getBoolean("success");
                            Toast.makeText(MenuActivity.this, "res: "+response, Toast.LENGTH_SHORT).show();
                            if (check){
                                Log.e("AAAAAAAAAAAAAAAAA", "onResponse: Thành công" );
                                SharedPreferences oBoard = getApplicationContext().getSharedPreferences("oBoard", Context.MODE_PRIVATE);
                                userPref = getApplicationContext().getSharedPreferences("dataLogin",getApplicationContext().MODE_PRIVATE);
                                SharedPreferences.Editor editorOBoard = oBoard.edit();
                                SharedPreferences.Editor editorUser = userPref.edit();

                                editorOBoard.putBoolean("isFirstTime",true);
                                editorOBoard.apply();

                                editorUser.putString("token",null);
                                editorUser.putString("id",null);
                                editorUser.putString("sodienthoai",null);
                                editorUser.putString("CMT",null);
                                editorUser.putBoolean("isLoggedIn",false);
                                editorUser.apply();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Log.e("AAAAAAAAAAAAAAAAA", "onResponse: thất bại, token: "+jsonObject.getString("token") );
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("AAAAAAAAAAAAAAAAA", "lỗi: "+error );
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        userPref = getApplicationContext().getSharedPreferences("dataLogin",getApplicationContext().MODE_PRIVATE);
                        String token = userPref.getString("token",null);
                        HashMap<String,String> params = new HashMap<>();
                        params.put("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xOTIuMTY4LjMuNzQ6ODA4MFwvQW50aUNvdmlkMTlcL3B1YmxpY1wvYXBpXC9sb2dpbiIsImlhdCI6MTU5MjM3MzI4MCwiZXhwIjoxNTkyMzc2ODgwLCJuYmYiOjE1OTIzNzMyODAsImp0aSI6IjR1QUh3azNYNnRFZmQzSXciLCJzdWIiOjQyLCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.sQHciTVyJ1Zg86yJosCmE6UyDLZ7X_kedTKXpwAcZsk");
                        return params;
                    }
                } ;
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        }) ;
    }
    public void initBottomNavigation(){
        //select home
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_healthy:
                        startActivity(new Intent(getApplicationContext(), HealthyActivity.class));
                        overridePendingTransition(0,0);
                        return  true;
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        return  true;
                    case R.id.nav_request:
                        startActivity(new Intent(getApplicationContext(), RequestActivity.class));
                        overridePendingTransition(0,0);
                        return  true;
                    case R.id.nav_menu:

                        return  true;
                }
                return false;
            }
        });
    }
}
