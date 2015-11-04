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
public class BookFragment extends Fragment {

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
        mDatas.add(new HealthyEntity("30001", "90%的薬不能吃",
                "作用范围：失眠，神经衰弱，神经紧张的人，气血循环不良，体虚呆滞者，四肢麻痹，腰酸背痛之人士，肠胃功能不佳，消化不良之人士，运动不足，年长体弱，手脚冰冷者", 92, 123, R.drawable.book_1));
        mDatas.add(new HealthyEntity("30002", "关心中老年人的健康",
                "作用范围：失眠，神经衰弱，神经紧张的人，气血循环不良，体虚呆滞者，四肢麻痹，腰酸背痛之人士，肠胃功能不佳，消化不良之人士，运动不足，年长体弱，手脚冰冷者", 34, 159, R.drawable.book_2));
        mDatas.add(new HealthyEntity("30003", "科舟求健",
                "作用范围：失眠，神经衰弱，神经紧张的人，气血循环不良，体虚呆滞者，四肢麻痹，腰酸背痛之人士，肠胃功能不佳，消化不良之人士，运动不足，年长体弱，手脚冰冷者", 45, 55, R.drawable.book_3));
        mDatas.add(new HealthyEntity("30004", "吃的百科",
                "作用范围：失眠，神经衰弱，神经紧张的人，气血循环不良，体虚呆滞者，四肢麻痹，腰酸背痛之人士，肠胃功能不佳，消化不良之人士，运动不足，年长体弱，手脚冰冷者", 36, 25, R.drawable.book_4));
        mDatas.add(new HealthyEntity("30005", "一辈子做纤柔女人",
                "作用范围：失眠，神经衰弱，神经紧张的人，气血循环不良，体虚呆滞者，四肢麻痹，腰酸背痛之人士，肠胃功能不佳，消化不良之人士，运动不足，年长体弱，手脚冰冷者", 77, 78, R.drawable.book_5));
        mDatas.add(new HealthyEntity("30006","最伟大排毒术",
                "作用范围：失眠，神经衰弱，神经紧张的人，气血循环不良，体虚呆滞者，四肢麻痹，腰酸背痛之人士，肠胃功能不佳，消化不良之人士，运动不足，年长体弱，手脚冰冷者", 19, 104, R.drawable.book_6));
        mDatas.add(new HealthyEntity("30007", "速食主义",
                "作用范围：失眠，神经衰弱，神经紧张的人，气血循环不良，体虚呆滞者，四肢麻痹，腰酸背痛之人士，肠胃功能不佳，消化不良之人士，运动不足，年长体弱，手脚冰冷者", 27, 23, R.drawable.book_7));
        mDatas.add(new HealthyEntity("30008", "我们到底应该怎么吃",
                "作用范围：失眠，神经衰弱，神经紧张的人，气血循环不良，体虚呆滞者，四肢麻痹，腰酸背痛之人士，肠胃功能不佳，消化不良之人士，运动不足，年长体弱，手脚冰冷者", 48, 88, R.drawable.book_8));

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
