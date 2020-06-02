package sict.myproject.anticovid_19.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sict.myproject.anticovid_19.R;

public class Frag_Request_Phananh extends Fragment {
    private View ViewRoot;
    private Button btn_call;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewRoot = inflater.inflate(R.layout.activity_frag_request_phananh,container,false);
        btn_call = (Button) ViewRoot.findViewById(R.id.btn_call);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonenumber = "tel:19009095";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phonenumber));
                startActivity(intent);
            }
        });
        return ViewRoot;
    }
}
