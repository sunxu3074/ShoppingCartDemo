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
public class HealthyDetailsFragment extends Fragment {

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        initViews();
        addListeners();
        initDatas();
    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        mDatas.add(new HealthyEntity("00001", "健康产品1",
                "「PUNO培諾®」的天然有機保健品系列，正是以此方式種植及加工，以達至現在都市人的追求標準，確保我們的健康得到保障的承諾.", 2100, 159, R.drawable.healthy_1));
        mDatas.add(new HealthyEntity("00001", "健康产品1",
                "「PUNO培諾®」的天然有機保健品系列，正是以此方式種植及加工，以達至現在都市人的追求標準，確保我們的健康得到保障的承諾.", 2100, 159, R.drawable.healthy_2));
        mDatas.add(new HealthyEntity("00001", "健康产品1",
                "「PUNO培諾®」的天然有機保健品系列，正是以此方式種植及加工，以達至現在都市人的追求標準，確保我們的健康得到保障的承諾.", 2100, 159, R.drawable.healthy_3));
        mDatas.add(new HealthyEntity("00001", "健康产品1",
                "「PUNO培諾®」的天然有機保健品系列，正是以此方式種植及加工，以達至現在都市人的追求標準，確保我們的健康得到保障的承諾.", 2100, 159, R.drawable.healthy_4));
        mDatas.add(new HealthyEntity("00001", "健康产品1",
                "「PUNO培諾®」的天然有機保健品系列，正是以此方式種植及加工，以達至現在都市人的追求標準，確保我們的健康得到保障的承諾.", 2100, 159, R.drawable.healthy_3));
        mDatas.add(new HealthyEntity("00001", "健康产品1",
                "「PUNO培諾®」的天然有機保健品系列，正是以此方式種植及加工，以達至現在都市人的追求標準，確保我們的健康得到保障的承諾.", 2100, 159, R.drawable.healthy_2));
        mDatas.add(new HealthyEntity("00001", "健康产品1",
                "「PUNO培諾®」的天然有機保健品系列，正是以此方式種植及加工，以達至現在都市人的追求標準，確保我們的健康得到保障的承諾.", 2100, 159, R.drawable.healthy_1));
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

        mListView = (ListView) getActivity().findViewById(R.id.fragment_healthy_lv);
    }
}
