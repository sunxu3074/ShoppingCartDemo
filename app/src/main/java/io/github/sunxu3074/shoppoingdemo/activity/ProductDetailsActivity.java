package io.github.sunxu3074.shoppoingdemo.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
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

public class ProductDetailsActivity extends ActionBarActivity {

    private HealthyEntity entity;

    private TextView mTVDetails;
    private TextView mTVList;
    private TextView mTVNumber;
    private TextView mTVPopDetails;
    private TextView mTVPrice;

    private Button mBtnAddToCart;
    private Button mBtnMinute;
    private Button mBtnPlus;

    private View mPop;

    private PopupWindow mPopupWindow;

    private ImageView mImgDetails;
    private ImageView mImgClose;

    private Button mBtnOK;

    /**
     * PopupWindow是否打开
     */
    private boolean isPopOpened;

    /**
     * 购买商品的数量
     */
    private int number = 1;

    private Handler handler = new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

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

                insert2Sqlite();

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

    /***
     * 插入数据库
     */
    private void insert2Sqlite() {

        // 购买数量 number
        // 购买种类
        int price = entity.getPrice();   // 购买价格
        String id = entity.getId(); // 购买id
        String name = entity.getName();  // 购买名称

        ProductReadDbHelper mDbHelper = new ProductReadDbHelper(getApplication());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

//        boolean flag = queryDatebase(db);
//        if(flag==false){
            ContentValues values = new ContentValues();
            values.put(ProductReaderContract.ProductEntry.COLUMN_NAME_ENTRY_ID, Integer.parseInt(id));
            values.put(ProductReaderContract.ProductEntry.COLUMN_NAME_PRICE, price+"");
            values.put(ProductReaderContract.ProductEntry.COLUMN_NAME_NAME, name);
            values.put(ProductReaderContract.ProductEntry.COLUMN_NAME_NUMBER, number+"");

            long rawID = -1;

            rawID = db.insert(
                    ProductReaderContract.ProductEntry.TABLE_NAME,
                    null,
                    values);

            Toast.makeText(getApplication(), "rawID = " + rawID, Toast.LENGTH_LONG).show();

            values.clear();
            if (db != null && db.isOpen()) {
                db.close();
            }
//        }
    }

    /***
     * 查询数据库
     */
    private boolean queryDatebase(SQLiteDatabase db) {
//        String[] projection = {
//                ProductReaderContract.ProductEntry.COLUMN_NAME_ENTRY_ID,
//                ProductReaderContract.ProductEntry.COLUMN_NAME_NUMBER,
//        };

        String selection = ProductReaderContract.ProductEntry.COLUMN_NAME_ENTRY_ID+" = ?";
        String[] selectionArgs = new String[]{Integer.parseInt(entity.getId())+""};
        Cursor c = db.query(
                ProductReaderContract.ProductEntry.TABLE_NAME,  // The table to query
//                projection,                               // The columns to return
                null,
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // The sort order
        );
        if(c!=null) {
            do{
                c.moveToFirst();
                int itemId = c.getInt(c.getColumnIndex(ProductReaderContract.ProductEntry
                        .COLUMN_NAME_ENTRY_ID));
                int number = Integer.parseInt(c.getString(c.getColumnIndex(ProductReaderContract
                        .ProductEntry
                        .COLUMN_NAME_NUMBER)));
                Toast.makeText(getApplication(), "itemId = " + itemId+"\r\n+numnber"+number, Toast.LENGTH_LONG).show();
                if (itemId == Integer.parseInt(entity.getId())) {
                    //TODO 更新number
                    updateDatebase(number, db);
                    Toast.makeText(getApplication(), "true", Toast.LENGTH_LONG).show();
                    return true;
                }
            }while(c.moveToNext());
        }

        if (db != null && db.isOpen()) {
            db.close();
        }
        return false;
    }

    /**
     * 更新数据库
     */
    private void updateDatebase(int sqlNumber,SQLiteDatabase db) {
        sqlNumber+=number;// 购买数量

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(ProductReaderContract.ProductEntry.COLUMN_NAME_NUMBER, sqlNumber+"");

        // Which row to update, based on the ID
        String selection = ProductReaderContract.ProductEntry.COLUMN_NAME_ENTRY_ID+ " LIKE ?";
        String[] selectionArgs = { String.valueOf(entity.getId()) };

        int count = db.update(
                ProductReaderContract.ProductEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        Toast.makeText(getApplication(), "count = " + count+"/r/n number="+sqlNumber, Toast.LENGTH_LONG).show();
        if (db != null && db.isOpen()) {
            db.close();
        }
        values.clear();
    }


    private void initViews() {
        mTVList = (TextView) findViewById(R.id.tv_activity_product_details_list);
        mTVDetails = (TextView) findViewById(R.id.tv_activity_product_details_details);
        mBtnAddToCart = (Button) findViewById(R.id.btn_activity_product_details_add_to_cart);
        mImgDetails = (ImageView) findViewById(R.id.img_activity_product);

        mPop = LayoutInflater.from(this).inflate(R.layout.popup_add_to_cart, null);
        mBtnOK = (Button) mPop.findViewById(R.id.btn_pop_ok);
        mBtnMinute = (Button) mPop.findViewById(R.id.btn_pop_minute);
        mBtnPlus = (Button) mPop.findViewById(R.id.btn_pop_plus);
        mImgClose = (ImageView) mPop.findViewById(R.id.img_pop_close);
        mTVNumber = (TextView) mPop.findViewById(R.id.tv_pop_number);
        mTVPopDetails = (TextView) mPop.findViewById(R.id.tv_pop_details);
        mTVPrice = (TextView) mPop.findViewById(R.id.tv_pop_price);
        mPopupWindow = new PopupWindow(mPop, getWindow().getAttributes().width, 1080);
    }

    private void initDatas() {

        entity = (HealthyEntity) getIntent().getSerializableExtra("entity");
        mTVDetails.setText(entity.getDetails());
        mTVPrice.setText("￥"+entity.getPrice());
        mTVPopDetails.setText(entity.getDetails());
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
        }else if(id == R.id.action_shopping) {
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
    }

    private void setWindowBehind(boolean flag) {
        final WindowManager.LayoutParams lp = getWindow().getAttributes();
        if (flag) {
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
