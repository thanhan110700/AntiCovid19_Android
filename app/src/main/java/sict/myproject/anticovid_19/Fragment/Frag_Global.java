package sict.myproject.anticovid_19.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import sict.myproject.anticovid_19.R;

public class Frag_Global extends Fragment {
    TextView tv_nhiembenhTG,tv_tangnhiembenhTG,tv_tuvongTG,tv_tangtuvongTG,tv_binhphucTG,tv_tangbinhphucTG;
    private View ViewRoot;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewRoot = inflater.inflate(R.layout.activity_frag_global,container,false);
        Widget();
        ReadJSON("https://api.covid19api.com/summary");
        return ViewRoot;
    }
    public void Widget(){
        tv_nhiembenhTG = (TextView) ViewRoot.findViewById(R.id.tv_nhiembenhTG);
        tv_tangnhiembenhTG = (TextView) ViewRoot.findViewById(R.id.tv_tangnhiembenhTG);
        tv_tuvongTG = (TextView) ViewRoot.findViewById(R.id.tv_tuvongTG);
        tv_tangtuvongTG = (TextView) ViewRoot.findViewById(R.id.tv_tangtuvongTG);
        tv_binhphucTG = (TextView) ViewRoot.findViewById(R.id.tv_binhphucTG);
        tv_tangbinhphucTG = (TextView) ViewRoot.findViewById(R.id.tv_tangbinhphucTG);
    }
    public void ReadJSON(String url){

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            DecimalFormat formatter = new DecimalFormat("#,###,###");

                            JSONObject jsonObject = response.getJSONObject("Global");
                            String nhiembenh = formatter.format(jsonObject.getInt("TotalConfirmed"));
                            String tangnhiembenh = formatter.format(jsonObject.getInt("NewConfirmed"));
                            String tuvong = formatter.format(jsonObject.getInt("TotalDeaths"));
                            String tangtuvong = formatter.format(jsonObject.getInt("NewDeaths"));
                            String binhphuc = formatter.format(jsonObject.getInt("TotalRecovered"));
                            String tangbinhphuc = formatter.format(jsonObject.getInt("NewRecovered"));

                            tv_nhiembenhTG.setText(nhiembenh);
                            tv_tangnhiembenhTG.append(tangnhiembenh);
                            tv_tuvongTG.setText(tuvong);
                            tv_tangtuvongTG.append(tangtuvong);
                            tv_binhphucTG.setText(binhphuc);
                            tv_tangbinhphucTG.append(tangbinhphuc);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }

}
