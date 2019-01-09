package com.example.chch2.baseapp.demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chch2.baseapp.R;
import com.example.chch2.baseapp.demo.bean.RedPacketUser;

import java.util.List;

public class RedPacketUserAdapter extends BaseAdapter {

    private List<RedPacketUser> list;
    private Context context;

    public RedPacketUserAdapter(List<RedPacketUser> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null ;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.lay_redpacket_user,parent,false);
            viewHolder.money = convertView.findViewById(R.id.money);
            viewHolder.name = convertView.findViewById(R.id.user_name);
            viewHolder.time = convertView.findViewById(R.id.time);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.money.setText(list.get(position).getMoney()+"元");
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.time .setText(list.get(position).getTime());

        return convertView;
    }

    class ViewHolder{
        TextView time;
        TextView money;
        TextView name;
    }
}
