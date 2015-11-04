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

import io.github.sunxu3074.shoppoingdemo.Entity.CategoryEntity;
import io.github.sunxu3074.shoppoingdemo.R;
import io.github.sunxu3074.shoppoingdemo.consts.ConstUtils;

/**
 * Created by zhangyan on 2015/10/21.
 */
public class CategoryAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<CategoryEntity> mDatas = new ArrayList<>();

    public CategoryAdapter(Context context,List<CategoryEntity> mDatas) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_category, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.label = (TextView) convertView.findViewById(R.id.item_category_name);
            viewHolder.details = (TextView) convertView.findViewById(R.id.item_category_details);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.item_category_img);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CategoryEntity entity = (CategoryEntity) getItem(position);
        viewHolder.label.setText(entity.getName());
        viewHolder.details.setText(entity.getDetails());
        viewHolder.image.setImageResource(ConstUtils.CATEGORY_PICTURES[Integer.parseInt(entity.getImgUrl())-1]);
        return convertView;
    }
   static class ViewHolder{
        TextView label,details;
        ImageView image;
    }
}
