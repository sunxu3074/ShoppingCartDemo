package io.github.sunxu3074.shoppoingdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.sunxu3074.shoppoingdemo.Entity.HealthyEntity;
import io.github.sunxu3074.shoppoingdemo.R;
import io.github.sunxu3074.shoppoingdemo.consts.ConstUtils;

/**
 * Created by zhangyan on 2015/10/27.
 */
public class HealthyAdapter extends BaseAdapter {

    private Context context;
    private List<HealthyEntity> mDatas = new ArrayList<>();

    public HealthyAdapter(Context context, List<HealthyEntity> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return  mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fragment_healthy, parent,
                    false);
            holder = new ViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.item_fragment_img);
            holder.price = (TextView) convertView.findViewById(R.id.item_fragment_price);
            holder.number = (TextView) convertView.findViewById(R.id.item_fragment_number);
            holder.details = (TextView) convertView.findViewById(R.id.item_fragment_healthy_details);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        HealthyEntity entity = (HealthyEntity) getItem(position);
        holder.img.setImageResource(entity.getImgUrl());
        holder.price.setText("￥"+entity.getPrice());
        holder.number.setText("已售出"+entity.getNumber()+"件");
        holder.details.setText(entity.getDetails());
        return convertView;
    }

    static class ViewHolder {
        ImageView img;
        TextView price, number, details;
    }
}
