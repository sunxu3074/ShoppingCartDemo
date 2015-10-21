package io.github.sunxu3074.shoppoingdemo.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.github.sunxu3074.shoppoingdemo.Entity.CategoryEntity;
import io.github.sunxu3074.shoppoingdemo.R;
import io.github.sunxu3074.shoppoingdemo.adapter.CategoryAdapter;

public class AllActivity extends ActionBarActivity {
    private ListView mListView;
    private List<CategoryEntity> mDatas = new ArrayList<>();
    private CategoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        initViews();
        initData();
    }

    private void initData() { /* 构造假数据*/
        mDatas.add(new CategoryEntity("健康产品", "R.drawable.category",
                "「PUNO培諾®」的天然有機保健品系列，正是以此方式種植及加工，以達至現在都市人的追求標準，確保我們的健康得到保障的承諾.", "000001"));
        mDatas.add(new CategoryEntity("健康理疗", "R.drawable.category",
                "「PUNO培諾®」的天然有機保健品系列，正是以此方式種植及加工，以達至現在都市人的追求標準，確保我們的健康得到保障的承諾.", "000002"));
        mDatas.add(new CategoryEntity("健康产品", "R.drawable.category",
                "「PUNO培諾®」的天然有機保健品系列，正是以此方式種植及加工，以達至現在都市人的追求標準，確保我們的健康得到保障的承諾.", "000003"));
        mDatas.add(new CategoryEntity("健康产品", "R.drawable.category",
                "「PUNO培諾®」的天然有機保健品系列，正是以此方式種植及加工，以達至現在都市人的追求標準，確保我們的健康得到保障的承諾.", "000004"));

        mAdapter = new CategoryAdapter(this,mDatas);
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
