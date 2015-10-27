package io.github.sunxu3074.shoppoingdemo.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import io.github.sunxu3074.shoppoingdemo.R;
import io.github.sunxu3074.shoppoingdemo.consts.ConstUtils;
import io.github.sunxu3074.shoppoingdemo.fragment.HealthyDetailsFragment;

public class CategoryDetailsActivity extends ActionBarActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private String[] mTitles = new String[]{"健康产品", "健康产品", "健康产品", "健康产品", "健康产品", "健康产品",
            "健康产品", "健康产品",};

    /**记录当前的position*/
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);

        initViews();
        initDatas();
        addListeners();

    }

    private void addListeners() {
       /* mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/
    }

    private void initDatas() {

       currentPosition = getIntent().getIntExtra(ConstUtils.ALLACTIVITY_KEY_POSITION,-1);

        //TabLayout.TabLayoutOnPageChangeListener 中记录了tab被点击怎么goto的方法..
        mTabLayout.getTabAt(currentPosition).select();


    }




    private void initViews() {

        mTabLayout = (TabLayout) findViewById(R.id.activity_category_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.activity_category_viewpager);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return new HealthyDetailsFragment();
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });

        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category_details, menu);
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
