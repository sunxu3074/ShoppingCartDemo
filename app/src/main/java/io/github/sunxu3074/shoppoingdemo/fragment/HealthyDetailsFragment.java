package io.github.sunxu3074.shoppoingdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by zhangyan on 2015/10/26.
 */
public class HealthyDetailsFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return new FrameLayout(getActivity());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //TODO
        super.onViewCreated(view, savedInstanceState);
    }
}
