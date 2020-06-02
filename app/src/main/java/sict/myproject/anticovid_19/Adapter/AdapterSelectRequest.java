package sict.myproject.anticovid_19.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import sict.myproject.anticovid_19.Fragment.Frag_Healthy_Canhan;
import sict.myproject.anticovid_19.Fragment.Frag_Healthy_Nguoithan;
import sict.myproject.anticovid_19.Fragment.Frag_Request_Phananh;
import sict.myproject.anticovid_19.Fragment.Frag_Request_khaibaotiepxuc;


public class AdapterSelectRequest extends FragmentStatePagerAdapter {
    private String ListTab[] = {"Phản ánh","Khai báo tiếp xúc"};
    private Frag_Request_khaibaotiepxuc frag_request_khaibaotiepxuc;
    private Frag_Request_Phananh frag_request_phananh;
    public AdapterSelectRequest(@NonNull FragmentManager fm) {
        super(fm);
        frag_request_khaibaotiepxuc= new Frag_Request_khaibaotiepxuc();
        frag_request_phananh = new Frag_Request_Phananh();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return frag_request_phananh;
        }
        if (position==1){
            return frag_request_khaibaotiepxuc;
        }
        else {
            return frag_request_phananh;
        }
    }

    @Override
    public int getCount() {
        return ListTab.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return ListTab[position];
    }
}
