package io.github.sunxu3074.shoppoingdemo.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import io.github.sunxu3074.shoppoingdemo.Entity.HealthyEntity;
import io.github.sunxu3074.shoppoingdemo.R;
import io.github.sunxu3074.shoppoingdemo.db.ProductReadDbHelper;
import io.github.sunxu3074.shoppoingdemo.db.ProductReaderContract;

public class ProductDetailsActivity extends BaseActivity {

    private static final int QUERY_YES = 0x100;
    private static final int QUERY_NO = 0x101;

    private static boolean ISQUERYED = false;

    private HealthyEntity entity;

    private TextView mTVDetails;
    private TextView mTVList;
    private TextView mTVNumber;
    private TextView mTVPopDetails;
    private TextView mTVPrice;
    private TextView mTVTopPrice;
    private TextView mTVPopCategory;

    private Button mBtnAddToCart;
    private Button mBtnMinute;
    private Button mBtnPlus;

    private View mPop;

    private PopupWindow mPopupWindow;

    private ImageView mImgDetails;
    private ImageView mImgClose;
    private ImageView mImgIcon;

    private Button mBtnOK;

    /**
     * PopupWindow是否打开
     */
    private boolean isPopOpened;

    /**
     * 购买商品的数量
     */
    private int number = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
                case QUERY_YES:
                    int number = (int) msg.obj;
                    updateDatebase(number);
                    break;
                case QUERY_NO:
                    insert2Sqlite();
                    break;
            }
        }
    };

    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33000000")));
        //google的actionbar是分为上下两栏显示的，上面的代码只能设置顶部actionbar的背景色，
        //为了让下面的背景色一致，还需要添加一行代码：
        actionBar.setSplitBackgroundDrawable(new ColorDrawable(Color.parseColor("#33000000")));

        initViews();
        initDatas();
        addListeners();
    }

    private void addListeners() {
        mBtnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPopOpened == false) {
                    setWindowBehind(true);
                    mPopupWindow.setAnimationStyle(R.style.PopupWindowStyle);
                    mPopupWindow.showAtLocation(mImgDetails, Gravity.BOTTOM, 0, 0);
                    isPopOpened = true;

                }
            }
        });

        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO 发送请求到后台
                // 保存产品信息到数据库

                queryDatebase();

                Toast.makeText(getApplication(), "您已经成功添加到购物车~", Toast.LENGTH_LONG).show();

                if (isPopOpened == true) {
                    setWindowBehind(false);
                    mPopupWindow.dismiss();
                    isPopOpened = false;
                }
            }
        });

        mImgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPopOpened == true) {
                    setWindowBehind(false);
                    mPopupWindow.dismiss();
                    isPopOpened = false;
                }
            }
        });

        mBtnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++number;
                mTVNumber.setText(number + "");
            }
        });

        mBtnMinute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number != 1) {
                    number--;
                    mTVNumber.setText(number + "");
                }
            }
        });

    }

    /**
     * 插入数据库
     */
    private void insert2Sqlite() {

        // 购买数量 number
        // 购买种类
        int price = entity.getPrice();   // 购买价格
        String id = entity.getId(); // 购买id
        String category = "保健产品";
        if (Integer.parseInt(id) > 30000) {
            category = "书籍";
        } else if (Integer.parseInt(id) > 20000) {
            category = "丸剂";
        } else {
            category = "保健产品";
        }
        String name = entity.getName();  // 购买名称

        ProductReadDbHelper mDbHelper = new ProductReadDbHelper(getApplication());
        db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductReaderContract.ProductEntry.COLUMN_NAME_ENTRY_ID, id);
        values.put(ProductReaderContract.ProductEntry.COLUMN_NAME_PRICE, price + "");
        values.put(ProductReaderContract.ProductEntry.COLUMN_NAME_NAME, name);
        values.put(ProductReaderContract.ProductEntry.COLUMN_NAME_NUMBER, number + "");
        values.put(ProductReaderContract.ProductEntry.COLUMN_NAME_CATEGORY, category);

        long rawID = -1;

        rawID = db.insert(
                ProductReaderContract.ProductEntry.TABLE_NAME,
                null,
                values);

        Toast.makeText(getApplication(), "rawID = " + rawID, Toast.LENGTH_LONG).show();
    }

    /**
     * 查询数据库
     */
    private void queryDatebase() {
//        String[] projection = {
//                ProductReaderContract.ProductEntry.COLUMN_NAME_ENTRY_ID,
//                ProductReaderContract.ProductEntry.COLUMN_NAME_NUMBER,
//        };

        ISQUERYED = false;

        ProductReadDbHelper mDbHelper = new ProductReadDbHelper(getApplication());
        db = mDbHelper.getWritableDatabase();

        Cursor c = null;
        try {
            String selection = ProductReaderContract.ProductEntry.COLUMN_NAME_ENTRY_ID + " = ?";
            String[] selectionArgs = new String[]{entity.getId()};
            c = db.query(
                    ProductReaderContract.ProductEntry.TABLE_NAME,  // The table to query
//                projection,                               // The columns to return
                    null,
                    selection,                                // The columns for the WHERE clause
                    selectionArgs,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null                                      // The sort order
            );

            if (c != null) {
                for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                    String itemId = c.getString(c.getColumnIndex(ProductReaderContract.ProductEntry
                            .COLUMN_NAME_ENTRY_ID));
                    int number = Integer.parseInt(c.getString(c.getColumnIndex(ProductReaderContract
                            .ProductEntry
                            .COLUMN_NAME_NUMBER)));
                    if (itemId.equals(entity.getId())) {
                        Toast.makeText(getApplication(), "true", Toast.LENGTH_LONG).show();
                        ISQUERYED = true;
                        Message message = Message.obtain();
                        message.what = QUERY_YES;
                        message.obj = number;
                        handler.sendMessage(message);
                    }
                }
            }
            if (!ISQUERYED) {
                Message message = Message.obtain();
                message.what = QUERY_NO;
                handler.sendMessage(message);
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }

    }


    /**
     * 更新数据库
     */
    private void updateDatebase(int sqlNumber) {

        sqlNumber += number;// 购买数量

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(ProductReaderContract.ProductEntry.COLUMN_NAME_NUMBER, sqlNumber + "");

        // Which row to update, based on the ID
        String selection = ProductReaderContract.ProductEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(entity.getId())};

        int count = db.update(
                ProductReaderContract.ProductEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        Toast.makeText(getApplication(), "count = " + count + "/r/n number=" + sqlNumber, Toast
                .LENGTH_LONG).show();
    }


    private void initViews() {
        mTVList = (TextView) findViewById(R.id.tv_activity_product_details_list);
        mTVDetails = (TextView) findViewById(R.id.tv_activity_product_details_details);
        mBtnAddToCart = (Button) findViewById(R.id.btn_activity_product_details_add_to_cart);
        mImgDetails = (ImageView) findViewById(R.id.img_activity_product);
        mTVTopPrice = (TextView) findViewById(R.id.tv_activity_product_details_price);

        mPop = LayoutInflater.from(this).inflate(R.layout.popup_add_to_cart, null);
        mImgIcon = (ImageView) mPop.findViewById(R.id.img_pop_icon);
        mBtnOK = (Button) mPop.findViewById(R.id.btn_pop_ok);
        mBtnMinute = (Button) mPop.findViewById(R.id.btn_pop_minute);
        mBtnPlus = (Button) mPop.findViewById(R.id.btn_pop_plus);
        mImgClose = (ImageView) mPop.findViewById(R.id.img_pop_close);
        mTVNumber = (TextView) mPop.findViewById(R.id.tv_pop_number);
        mTVPopDetails = (TextView) mPop.findViewById(R.id.tv_pop_details);
        mTVPrice = (TextView) mPop.findViewById(R.id.tv_pop_price);
        mTVPopCategory = (TextView) mPop.findViewById(R.id.tv_pop_category);
        mPopupWindow = new PopupWindow(mPop, getWindow().getAttributes().width, 1080);
    }

    private void initDatas() {

        entity = (HealthyEntity) getIntent().getSerializableExtra("entity");

        String category = "";
        mImgDetails.setImageResource(entity.getImgUrl());
        mTVDetails.setText(entity.getDetails());
        mTVTopPrice.setText("￥" + entity.getPrice());
        mTVPrice.setText("￥" + entity.getPrice());
        mTVPopDetails.setText(entity.getDetails());
        mTVList.setText("￥" + (entity.getPrice() + 900));
        mImgIcon.setImageResource(entity.getImgUrl());
        if (Integer.parseInt(entity.getId()) / 10000 == 1) {
            category = "保健产品";
        } else if (Integer.parseInt(entity.getId()) / 10000 == 2) {
            category = "丸剂";
        } else {
            category = "书籍";
        }
        mTVPopCategory.setText(category);
        mTVList.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_details, menu);
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
        } else if (id == R.id.action_shopping) {
            Intent intent = new Intent(getApplication(), ShoppingCartActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (isPopOpened) {
            setWindowBehind(false);
            mPopupWindow.dismiss();
            isPopOpened = false;
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isPopOpened) {
            setWindowBehind(false);
            mPopupWindow.dismiss();
            isPopOpened = false;
            mPopupWindow = null;
        }

        if (db != null && db.isOpen()) {
            db.close();
            db = null;
        }
    }

    private void setWindowBehind(boolean isSetWindowBehind) {
        final WindowManager.LayoutParams lp = getWindow().getAttributes();
        if (isSetWindowBehind) {
            lp.alpha = 0.3f;
        } else {
            lp.alpha = 1.0f;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getWindow().setAttributes(lp);
            }
        }, 300);

    }

}
