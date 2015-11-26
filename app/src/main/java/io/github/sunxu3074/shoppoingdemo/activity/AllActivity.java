package io.github.sunxu3074.shoppoingdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.github.sunxu3074.shoppoingdemo.Entity.CategoryEntity;
import io.github.sunxu3074.shoppoingdemo.R;
import io.github.sunxu3074.shoppoingdemo.adapter.CategoryAdapter;
import io.github.sunxu3074.shoppoingdemo.consts.ConstUtils;

public class AllActivity extends BaseActivity {

    private ListView mListView;
    private List<CategoryEntity> mDatas = new ArrayList<>();
    private CategoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        initViews();
        initData();
        addListeners();
    }

    private void addListeners() {

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplication(), CategoryDetailsActivity.class);
                intent.putExtra(ConstUtils.ALLACTIVITY_KEY_POSITION, position);
                startActivity(intent);
            }
        });
    }

    private void initData() { /* 构造假数据*/
        mDatas.add(new CategoryEntity("保健产品", "1",
                "作用范围：失眠，神经衰弱，神经紧张的人，气血循环不良，体虚呆滞者，四肢麻痹，腰酸背痛之人士，肠胃功能不佳，消化不良之人士，运动不足，年长体弱，手脚冰冷者",
                "000001", 1000, 159));
        mDatas.add(new CategoryEntity("丸剂", "2",
                "免疫调节作用，延缓衰老作用，改变记忆作用，促进生长发育作用，抗疲劳作用，减肥作用，抑制肿瘤作用", "000002", 1000, 159));
        mDatas.add(new CategoryEntity("书籍", "3",
                "八卦象素的配方及应用、拔罐疗法，不生病的智慧，科普从头到脚说健康，养心的妙药", "000003", 1000, 159));


        mAdapter = new CategoryAdapter(this, mDatas);
        mListView.setAdapter(mAdapter);

    }

    private void initViews() {

        mListView = (ListView) findViewById(R.id.all_lv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
