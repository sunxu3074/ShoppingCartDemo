package io.github.sunxu3074.shoppoingdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.sunxu3074.shoppoingdemo.Entity.ShoppingCartEntity;
import io.github.sunxu3074.shoppoingdemo.R;

/**
 * Created by zhangyan on 2015/10/29.
 */
public class ShoppingCartAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<ShoppingCartEntity> mDatas = new ArrayList<>();

    public ShoppingCartAdapter(Context context, ArrayList<ShoppingCartEntity> mDatas) {
       mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_shopping_cart,parent,false);
            holder.cb = (CheckBox) convertView.findViewById(R.id.cb_item_shopping_cart);
            holder.name = (TextView) convertView.findViewById(R.id.tv_item_shopping_cart_name);
            holder.category = (TextView) convertView.findViewById(R.id.tv_item_shopping_cart_category);
            holder.price = (TextView) convertView.findViewById(R.id.tv_item_shopping_cart_price);
            holder.number = (TextView) convertView.findViewById(R.id.tv_item_shopping_cart_number);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        ShoppingCartEntity entity = (ShoppingCartEntity) getItem(position);
        holder.category.setText("健康产品系列1");
        holder.name.setText(entity.getName());
        holder.price.setText(entity.getPrice() + "");
        holder.number.setText(entity.getNumber());

        return convertView;
    }

    static class ViewHolder{
        private CheckBox cb ;
//        private ImageView img;
        private TextView name,category,price,number;
    }
}
