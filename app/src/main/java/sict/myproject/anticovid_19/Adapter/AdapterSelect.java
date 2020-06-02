package sict.myproject.anticovid_19.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import sict.myproject.anticovid_19.Fragment.Frag_Global;
import sict.myproject.anticovid_19.Fragment.Frag_VietNam;

public class AdapterSelect extends FragmentStatePagerAdapter {
    private String ListTab[] = {"Việt Nam","Thế Giới"};
    private Frag_VietNam frag_vietNam;
    private Frag_Global frag_global;
    public AdapterSelect(@NonNull FragmentManager fm) {
        super(fm);
        frag_vietNam= new Frag_VietNam();
        frag_global = new Frag_Global();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return frag_vietNam;
        }
        if (position==1){
            return frag_global;
        }
        else {
            return frag_vietNam;
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
