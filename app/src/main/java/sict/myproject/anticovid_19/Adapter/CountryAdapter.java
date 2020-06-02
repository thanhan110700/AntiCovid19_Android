package sict.myproject.anticovid_19.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import sict.myproject.anticovid_19.Interface.ItemClickListener;
import sict.myproject.anticovid_19.MainActivity;
import sict.myproject.anticovid_19.Model.Countries;
import sict.myproject.anticovid_19.R;

class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
    ImageView iv_Country;
    TextView tv_NameCountry;
    ItemClickListener itemClickListener;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        iv_Country = (ImageView) itemView.findViewById(R.id.iv_country);
        tv_NameCountry = (TextView) itemView.findViewById(R.id.tv_namecountry);
        itemView.setOnLongClickListener(this);
        itemView.setOnClickListener(this);
    }


    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }
}
public class CountryAdapter extends RecyclerView.Adapter<ViewHolder> {

    Context context;
    ArrayList<Countries> countriesArrayList;

    public CountryAdapter(Context context, ArrayList<Countries> countriesArrayList) {
        this.context = context;
        this.countriesArrayList = countriesArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.option_countries,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_NameCountry.setText(countriesArrayList.get(position).getmNamecountry());
        Glide.with(context).load(countriesArrayList.get(position).getmImgCountry()).into(holder.iv_Country);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongclick) {
                if (isLongclick){

                }
                else {
                    Intent intent = new Intent(context, MainActivity.class);

                    intent.putExtra("nameCountry",countriesArrayList.get(position).getmNamecountry());


                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return countriesArrayList.size();
    }
}
