package io.github.sunxu3074.shoppingdemo2.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import io.github.sunxu3074.shoppingdemo2.R;
import io.github.sunxu3074.shoppingdemo2.fragment.HealthyFragment;


public class MainActivity extends ActionBarActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private String[] mTitles = new String[]{"健康产品","健康产品","健康产品","健康产品","健康产品","健康产品"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initData();
    }

    private void initData() {

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new HealthyFragment();
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

    private void initViews() {
        mTabLayout = (TabLayout) findViewById(R.id.main_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
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
