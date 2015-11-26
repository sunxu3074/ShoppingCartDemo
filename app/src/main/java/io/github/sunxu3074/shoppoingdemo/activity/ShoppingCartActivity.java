package io.github.sunxu3074.shoppoingdemo.activity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import io.github.sunxu3074.shoppoingdemo.Entity.ShoppingCartEntity;
import io.github.sunxu3074.shoppoingdemo.R;
import io.github.sunxu3074.shoppoingdemo.consts.ConstUtils;
import io.github.sunxu3074.shoppoingdemo.db.ProductReadDbHelper;
import io.github.sunxu3074.shoppoingdemo.db.ProductReaderContract;

public class ShoppingCartActivity extends BaseActivity {

    private static final int MSG_WHAT = 0x223;

    private ListView mListView;
    /**
     * 结算
     */
    private Button mBtnClearing;

    private TextView mTVTotal;

    private CheckBox mCheckBox;

    /**
     * 合计
     */
    private int mTotalMoney = 0;
    private int mTotalChecked = 0;


    private ArrayList<ShoppingCartEntity> mDatas = new ArrayList<>();

    private ProductReadDbHelper mDbHelper = new ProductReadDbHelper(this);

    private ShoppingCartAdapter mAdapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
                case MSG_WHAT:
                    mAdapter = new ShoppingCartAdapter(getApplication(), mDatas);
                    mListView.setAdapter(mAdapter);
                    addListeners();
                    break;
            }
        }
    };

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        initViews();
        initDatas();
    }

    private void addListeners() {

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
                    for (int i = 0; i < mDatas.size() ; i++) {
                        map.put(i, isChecked);
                    }
                    mAdapter.setmMaps(map);
                    mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initViews() {

        mListView = (ListView) findViewById(R.id.lv_shopping_cart_activity);
        mBtnClearing = (Button) findViewById(R.id.btn_activity_shopping_cart_clearing);
        mTVTotal = (TextView) findViewById(R.id.tv_activity_shopping_cart_total);
        mCheckBox = (CheckBox) findViewById(R.id.cb_activity_shopping_cart);

    }

    private void initDatas() {

        final SQLiteDatabase db = mDbHelper.getReadableDatabase();
        final Cursor cursor = db.query(ProductReaderContract.ProductEntry.TABLE_NAME, null, null,
                null,
                null, null, null);

        new Thread() {
            @Override
            public void run() {
                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        String id = cursor.getString(cursor.getColumnIndex
                                (ProductReaderContract.ProductEntry
                                        .COLUMN_NAME_ENTRY_ID));
                        String name = cursor.getString(cursor.getColumnIndex
                                (ProductReaderContract.ProductEntry
                                        .COLUMN_NAME_NAME));
                        ;
                        String category = cursor.getString(cursor.getColumnIndex
                                (ProductReaderContract.ProductEntry
                                        .COLUMN_NAME_CATEGORY));
                        int price = Integer.parseInt(cursor.getString(cursor.getColumnIndex
                                (ProductReaderContract.ProductEntry.COLUMN_NAME_PRICE)));
                        int number = Integer.parseInt(cursor.getString(cursor.getColumnIndex
                                (ProductReaderContract.ProductEntry.COLUMN_NAME_NUMBER)));
                        ShoppingCartEntity entity = new ShoppingCartEntity(id, name, category,
                                price, number, Integer.parseInt(id));
                        mDatas.add(entity);
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (db != null && db.isOpen()) {
                        db.close();
                    }
                    Message message = Message.obtain();
                    message.what = MSG_WHAT;
                    mHandler.sendMessage(message);
                }
            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shopping_cart, menu);
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

    private  class ShoppingCartAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        private ArrayList<ShoppingCartEntity> mDatas = new ArrayList<>();
        private ViewHolder holder;

        public  HashMap<Integer,Boolean> mMaps = new HashMap<>();

        public  HashMap<Integer, Boolean> getMap() {
            return mMaps;
        }

        public void setmMaps(HashMap<Integer, Boolean> mMaps){
            this.mMaps = mMaps;
        }


        public ShoppingCartAdapter(Context context, ArrayList<ShoppingCartEntity> mDatas) {
            mInflater = LayoutInflater.from(context);
            this.mDatas = mDatas;
            for (int i = 0; i < mDatas.size() ; i++) {
                mMaps.put(i, false);
            }
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_shopping_cart, parent, false);
                holder.cb = (CheckBox) convertView.findViewById(R.id.cb_item_shopping_cart);
                holder.name = (TextView) convertView.findViewById(R.id.tv_item_shopping_cart_name);
                holder.category = (TextView) convertView.findViewById(R.id
                        .tv_item_shopping_cart_category);
                holder.price = (TextView) convertView.findViewById(R.id
                        .tv_item_shopping_cart_price);
                holder.number = (TextView) convertView.findViewById(R.id
                        .tv_item_shopping_cart_number);
                holder.img = (ImageView) convertView.findViewById(R.id
                        .img_item_shopping_cart_number);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final ShoppingCartEntity entity = (ShoppingCartEntity) getItem(position);
            holder.category.setText(entity.getCategory());
            holder.name.setText(entity.getName());
            holder.price.setText(entity.getPrice() + "");
            holder.number.setText("x" + entity.getNumber());
            holder.img.setImageResource(ConstUtils.PICTURES[entity.getImgUrl() / 10000 -
                    1][entity.getImgUrl() % 10000 - 1]);
            holder.cb.setChecked(getMap().get(position));

            holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        mTotalMoney += entity.getNumber() * entity.getPrice();
                        mTotalChecked++;
                    } else {
                        mTotalChecked--;
                        mTotalMoney -= entity.getNumber() * entity.getPrice();
                    }
                    mBtnClearing.setText("结算（" + mTotalChecked + ")");
                    mTVTotal.setText("合计：￥" + mTotalMoney);
                }
            });

            return convertView;
        }

        class ViewHolder {
            CheckBox cb;
            ImageView img;
            TextView name, category, price, number;
        }
    }

}
