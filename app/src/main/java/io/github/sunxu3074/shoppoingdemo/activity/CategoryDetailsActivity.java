package io.github.sunxu3074.shoppoingdemo.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import io.github.sunxu3074.shoppoingdemo.R;
import io.github.sunxu3074.shoppoingdemo.consts.ConstUtils;
import io.github.sunxu3074.shoppoingdemo.fragment.BookFragment;
import io.github.sunxu3074.shoppoingdemo.fragment.FootWashFragment;
import io.github.sunxu3074.shoppoingdemo.fragment.PillFragment;

public class CategoryDetailsActivity extends BaseActivity {



    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private String[] mTitles = new String[]{"保健产品", "丸剂", "书籍",};

    /**
     * 记录当前的position
     */
    private int currentPosition;

    private int[] icons = {R.drawable.foot_icon, R.drawable.pill_icon, R.drawable.book_icon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);

        initViews();

    }

    private void initViews() {

        mViewPager = (ViewPager) findViewById(R.id.activity_category_viewpager);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Log.d("position", position+"");
                if (position == 0) {
                    return new FootWashFragment();
                } else if (position == 1) {
                    return new PillFragment();
                } else if (position == 2) {
                    return new BookFragment();
                }
                return null;
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                // Generate title based on item position
                Drawable d = getResources().getDrawable(icons[position]);
                d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());

                // Replace blank spaces with image icon
                SpannableString sb = new SpannableString("   " + mTitles[position]);
                ImageSpan imageSpan = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);
                sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                return sb;
            }

        });

        //TODO
        currentPosition = getIntent().getIntExtra(ConstUtils.ALLACTIVITY_KEY_POSITION, 0);
        mTabLayout = (TabLayout) findViewById(R.id.activity_category_tablayout);
        mTabLayout.setupWithViewPager(mViewPager);

//        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

/*
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("tab position", tab.getPosition() + "");
//                mViewPager.setCurrentItem(tab.getPosition());
//                mTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
*/
        //TabLayout.TabLayoutOnPageChangeListener 中记录了tab被点击怎么goto的方法..
        mTabLayout.getTabAt(currentPosition).select();

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
