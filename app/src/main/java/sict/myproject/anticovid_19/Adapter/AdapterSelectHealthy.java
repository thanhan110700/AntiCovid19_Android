package sict.myproject.anticovid_19.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import sict.myproject.anticovid_19.Fragment.Frag_Healthy_Canhan;
import sict.myproject.anticovid_19.Fragment.Frag_Healthy_Nguoithan;


public class AdapterSelectHealthy extends FragmentStatePagerAdapter {
    private String ListTab[] = {"Cá nhân","Người thân"};
    private Frag_Healthy_Canhan frag_Canhan;
    private Frag_Healthy_Nguoithan frag_Nguoithan;
    public AdapterSelectHealthy(@NonNull FragmentManager fm) {
        super(fm);
        frag_Canhan= new Frag_Healthy_Canhan();
        frag_Nguoithan = new Frag_Healthy_Nguoithan();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return frag_Canhan;
        }
        if (position==1){
            return frag_Nguoithan;
        }
        else {
            return frag_Canhan;
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
