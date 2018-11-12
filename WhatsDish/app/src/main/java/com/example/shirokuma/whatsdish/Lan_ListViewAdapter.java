package com.example.shirokuma.whatsdish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Lan_ListViewAdapter extends BaseAdapter {

    static class ViewHolder {
        TextView textView;
    }

    private LayoutInflater inflater;
    private int itemLayoutId;
    private String[] titles;

    Lan_ListViewAdapter(Context context, int itemLayoutId, String[] country) {
        super();
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemLayoutId = itemLayoutId;
        this.titles = country;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        ViewHolder holder;
        if (contentView == null) {
            contentView = inflater.inflate(itemLayoutId, parent, false);
            holder = new ViewHolder();
            holder.textView = contentView.findViewById(R.id.country);
            contentView.setTag(holder);
        }
        else {
            holder = (ViewHolder) contentView.getTag();
        }

        holder.textView.setText(titles[position]);

        return contentView;
    }

    @Override
    public int getCount(){
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
