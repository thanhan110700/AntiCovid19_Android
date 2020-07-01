package sict.myproject.anticovid_19.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sict.myproject.anticovid_19.Model.Theodoisuckhoe;
import sict.myproject.anticovid_19.R;

public class Adapter_Theodoisuckhoe extends RecyclerView.Adapter<Adapter_Theodoisuckhoe.ViewHolder> {

    Context context;
    ArrayList<Theodoisuckhoe> theodoisuckhoeArrayList;

    public Adapter_Theodoisuckhoe(Context context, ArrayList<Theodoisuckhoe> theodoisuckhoeArrayList) {
        this.context = context;
        this.theodoisuckhoeArrayList = theodoisuckhoeArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_theodoisuckhoe,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_tinhtrangsuckhoe.setText(theodoisuckhoeArrayList.get(position).getmTinhtrang());
        holder.tv_tgkhaisuckhoe.setText(theodoisuckhoeArrayList.get(position).getmTgiankhaibao());
        if (holder.tv_tinhtrangsuckhoe.getText().equals("Nguy cơ nhiễm bệnh")){
            holder.tv_tinhtrangsuckhoe.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_texttheodoisuckhoewarning));
        }
        if (holder.tv_tinhtrangsuckhoe.getText().equals("An toàn")){
            holder.tv_tinhtrangsuckhoe.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_texttheodoisuckhoe));
        }
        holder.tv_ttsuckhoe.setText(theodoisuckhoeArrayList.get(position).getmThongtin());
    }

    @Override
    public int getItemCount() {
        return theodoisuckhoeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_ttsuckhoe, tv_tgkhaisuckhoe, tv_tinhtrangsuckhoe,ttsk;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tinhtrangsuckhoe = (TextView) itemView.findViewById(R.id.tv_tinhtrangsuckhoe);
            tv_tgkhaisuckhoe = (TextView) itemView.findViewById(R.id.tv_tgkhaisuckhoe);
            tv_ttsuckhoe = (TextView) itemView.findViewById(R.id.tv_ttsuckhoe);
            ttsk = (TextView) itemView.findViewById(R.id.tv_ttsk);

        }
    }
}
