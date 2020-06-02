package sict.myproject.anticovid_19.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

import sict.myproject.anticovid_19.R;

public class Frag_VietNam extends Fragment {
    TextView tv_nhiembenhVN,tv_tangnhiembenhVN,tv_tuvongVN,tv_tangtuvongVN,tv_binhphucVN,tv_tangbinhphucVN;
    private View ViewRoot;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewRoot = inflater.inflate(R.layout.activity_frag_vietnam,container,false);
        Widget();
        ReadJSON("https://api.covid19api.com/summary");

        //new ReadJSON().execute("https://api.covid19api.com/summary");
        return ViewRoot;
    }
    public void Widget(){
       tv_nhiembenhVN = (TextView) ViewRoot.findViewById(R.id.tv_nhiembenhVN);
        tv_tangnhiembenhVN = (TextView) ViewRoot.findViewById(R.id.tv_tangnhiembenhVN);
        tv_tuvongVN = (TextView) ViewRoot.findViewById(R.id.tv_tuvongVN);
        tv_tangtuvongVN = (TextView) ViewRoot.findViewById(R.id.tv_tangtuvongVN);
        tv_binhphucVN = (TextView) ViewRoot.findViewById(R.id.tv_binhphucVN);
        tv_tangbinhphucVN = (TextView) ViewRoot.findViewById(R.id.tv_tangbinhphucVN);

    }
    public void ReadJSON(String url){
        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
         JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                 new Response.Listener<JSONObject>() {
                     @Override
                     public void onResponse(JSONObject response) {

                         try {
                             DecimalFormat formatter = new DecimalFormat("#,###,###");
                             JSONArray jsonArray = response.getJSONArray("Countries");


                                 JSONObject country = jsonArray.getJSONObject(181);
                             String nhiembenh = formatter.format(country.getInt("TotalConfirmed"));
                             String tangnhiembenh = formatter.format(country.getInt("NewConfirmed"));
                             String tuvong = formatter.format(country.getInt("TotalDeaths"));
                             String tangtuvong = formatter.format(country.getInt("NewDeaths"));
                             String binhphuc = formatter.format(country.getInt("TotalRecovered"));
                             String tangbinhphuc = formatter.format(country.getInt("NewRecovered"));
                                 tv_nhiembenhVN.setText(nhiembenh);
                                 tv_tangnhiembenhVN.append(tangnhiembenh);
                                 tv_tuvongVN.setText(tuvong);
                                 tv_tangtuvongVN.append(tangtuvong);
                                 tv_binhphucVN.setText(binhphuc);
                                 tv_tangbinhphucVN.append(tangbinhphuc);

                         } catch (JSONException e) {
                             e.printStackTrace();
                         }
                     }
                 }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 error.printStackTrace();
             }
         });
         requestQueue.add(jsonObjectRequest);


    }
//    private class ReadJSON extends AsyncTask<String, Void, String>{
//
//    @Override
//    protected String doInBackground(String... strings) {
//        StringBuilder content = new StringBuilder();
//        try {
//            URL url = new URL(strings[0]);
//            InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//            String line ="";
//            while ((line = bufferedReader.readLine())!=null){
//                content.append(line);
//            }
//            bufferedReader.close();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return content.toString();
//    }
//
//    @Override
//    protected void onPostExecute(String s) {
//        super.onPostExecute(s);
//        try {
//            JSONObject jsonObject = new JSONObject(s);
//            JSONArray array = jsonObject.getJSONArray("Global");
//            Toast.makeText( getContext(), " "+array.length() , Toast.LENGTH_SHORT).show();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
}
