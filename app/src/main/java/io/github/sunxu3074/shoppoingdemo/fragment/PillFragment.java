package io.github.sunxu3074.shoppoingdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.github.sunxu3074.shoppoingdemo.Entity.HealthyEntity;
import io.github.sunxu3074.shoppoingdemo.R;
import io.github.sunxu3074.shoppoingdemo.activity.ProductDetailsActivity;
import io.github.sunxu3074.shoppoingdemo.adapter.HealthyAdapter;

/**
 * Created by zhangyan on 2015/10/26.
 */
public class PillFragment extends Fragment {

    private ListView mListView;

    private List<HealthyEntity> mDatas = new ArrayList<>();

    private HealthyAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.fragment_healthy, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        addListeners();
        initDatas();
    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        mDatas.add(new HealthyEntity("20001", "大山楂丸",
                "免疫调节作用，延缓衰老作用，改变记忆作用，促进生长发育作用，抗疲劳作用，减肥作用，抑制肿瘤作用", 320, 159, R.drawable.pill_1));
        mDatas.add(new HealthyEntity("20002", "六神丸",
                "免疫调节作用，延缓衰老作用，改变记忆作用，促进生长发育作用，抗疲劳作用，减肥作用，抑制肿瘤作用", 99, 122, R.drawable.pill_2));
        mDatas.add(new HealthyEntity("20003", "六味地黄丸",
                "免疫调节作用，延缓衰老作用，改变记忆作用，促进生长发育作用，抗疲劳作用，减肥作用，抑制肿瘤作用", 120, 151, R.drawable.pill_3));
        mDatas.add(new HealthyEntity("20004", "牛黄清心丸(局方)",
                "免疫调节作用，延缓衰老作用，改变记忆作用，促进生长发育作用，抗疲劳作用，减肥作用，抑制肿瘤作用", 520, 139, R.drawable.pill_4));
        mDatas.add(new HealthyEntity("20005", "壮筋续骨丸",
                "免疫调节作用，延缓衰老作用，改变记忆作用，促进生长发育作用，抗疲劳作用，减肥作用，抑制肿瘤作用", 280, 289, R.drawable.pill_5));

        mAdapter = new HealthyAdapter(getActivity(), mDatas);
        mListView.setAdapter(mAdapter);

    }

    private void addListeners() {

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HealthyAdapter adapter = (HealthyAdapter) parent.getAdapter();
                HealthyEntity entity = (HealthyEntity) adapter.getItem(position);
                Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                intent.putExtra("entity", entity);
                getActivity().startActivity(intent);
            }
        });
    }

    private void initViews() {

        mListView = (ListView) getView().findViewById(R.id.fragment_healthy_lv);
    }
}
