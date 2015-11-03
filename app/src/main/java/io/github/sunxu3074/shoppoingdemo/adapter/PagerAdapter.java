package io.github.sunxu3074.shoppoingdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import io.github.sunxu3074.shoppoingdemo.fragment.BookFragment;
import io.github.sunxu3074.shoppoingdemo.fragment.FootWashFragment;
import io.github.sunxu3074.shoppoingdemo.fragment.PillFragment;

/**
 * Created by zhangyan on 2015/11/3.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    private int number;

    public PagerAdapter(FragmentManager fm,int number) {
        super(fm);
        this.number = number;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FootWashFragment tab1 = new FootWashFragment();
                return tab1;
            case 1:
                PillFragment tab2 = new PillFragment();
                return tab2;
            case  2:
                BookFragment tab3 = new BookFragment();
                return tab3;
        }
        return null;
    }

    @Override
    public int getCount() {
        return number;
    }
}
