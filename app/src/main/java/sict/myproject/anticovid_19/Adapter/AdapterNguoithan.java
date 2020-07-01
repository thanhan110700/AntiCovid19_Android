package sict.myproject.anticovid_19.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import sict.myproject.anticovid_19.Model.Nguoithan;
import sict.myproject.anticovid_19.R;

public class AdapterNguoithan extends BaseAdapter {
    List<Nguoithan> nguoithanList;
    LayoutInflater layoutInflater;
    Context context;


    public AdapterNguoithan(List<Nguoithan> nguoithanList, Context context) {
        this.nguoithanList = nguoithanList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return nguoithanList.size();
    }

    @Override
    public Object getItem(int position) {

        return nguoithanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_list_nguoithan, null);
            holder = new ViewHolder();

            holder.tv_tennguoithan = (TextView) convertView.findViewById(R.id.tv_tennguoithan);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Nguoithan nguoithan = this.nguoithanList.get(position);
        holder.tv_tennguoithan.setText(nguoithan.getmName());

        return convertView;
    }
    static class ViewHolder {
        TextView tv_tennguoithan;
    }
}
