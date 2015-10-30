package io.github.sunxu3074.shoppoingdemo.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import io.github.sunxu3074.shoppoingdemo.Entity.ShoppingCartEntity;
import io.github.sunxu3074.shoppoingdemo.R;
import io.github.sunxu3074.shoppoingdemo.adapter.ShoppingCartAdapter;
import io.github.sunxu3074.shoppoingdemo.db.ProductReadDbHelper;
import io.github.sunxu3074.shoppoingdemo.db.ProductReaderContract;

public class ShoppingCartActivity extends ActionBarActivity {

    private ListView mListView;
    /**
     * 结算
     */
    private Button mBtnClearing;

    private ArrayList<ShoppingCartEntity> mDatas = new ArrayList<>();

    private ProductReadDbHelper mDbHelper = new ProductReadDbHelper(this);

    private ShoppingCartAdapter mAdapter;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
           int what = msg.what;
            switch (what) {
                case 1:
                    mAdapter = new ShoppingCartAdapter(getApplication(), mDatas);
                    mListView.setAdapter(mAdapter);
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

    private void initViews() {

        mListView = (ListView) findViewById(R.id.lv_shopping_cart_activity);
    }

    private void initDatas() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        final Cursor cursor = db.query(ProductReaderContract.ProductEntry.TABLE_NAME, null, null,
                null,
                null, null, null);

        new Thread() {
            @Override
            public void run() {
                if (cursor != null) {
                    do {
                        cursor.moveToFirst();
            /*
            String id ;
            String name;
            String category
            int price;
            int number ;
            String imgUrl;
            */
                        String id = "0000" + cursor.getInt(cursor.getColumnIndex
                                (ProductReaderContract.ProductEntry
                                .COLUMN_NAME_ENTRY_ID));
                        String name = "健康产品系列1";
                        String category = "健康产品";
                        int price = Integer.parseInt(cursor.getString(cursor.getColumnIndex
                                (ProductReaderContract.ProductEntry.COLUMN_NAME_PRICE)));
                        int number = Integer.parseInt(cursor.getString(cursor.getColumnIndex
                                (ProductReaderContract.ProductEntry.COLUMN_NAME_NUMBER)));
                        ShoppingCartEntity entity = new ShoppingCartEntity(id, name, category,
                                price, number, null);
                        mDatas.add(entity);
                    } while (cursor.moveToNext());
                    Message message = Message.obtain();
                    message.what = 1;
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
}
