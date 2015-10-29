package io.github.sunxu3074.shoppoingdemo.adapter;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

/**
 * Created by zhangyan on 2015/10/29.
 */
public class CustomCursorAdapter extends SimpleCursorAdapter {
    public CustomCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to,
                               int flags) {
        super(context, layout, c, from, to, flags);
    }
}
