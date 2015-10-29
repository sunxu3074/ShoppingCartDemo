package io.github.sunxu3074.shoppoingdemo.activity;

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
//               Intent intent = new Intent(getApplication(), AddToCartActivity.class);
//               startActivity(intent);
//               overridePendingTransition(R.anim.activity_bottom_to_top, 0);
                if (isPopOpened == false) {
                    setWindowBehind(true);
                    final int x = getWindow().getAttributes().width;
                    final int y = getWindow().getAttributes().height;
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
