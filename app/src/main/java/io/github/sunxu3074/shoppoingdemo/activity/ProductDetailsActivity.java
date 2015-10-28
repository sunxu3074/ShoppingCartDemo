package io.github.sunxu3074.shoppoingdemo.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import io.github.sunxu3074.shoppoingdemo.R;

public class ProductDetailsActivity extends ActionBarActivity {

    private String details ;

    private TextView mTVDetails;
    private TextView mTVList;

    private Button mBtnAddToCart;

    private View mPop;

    private  PopupWindow mPopupWindow;

    private ImageView mImageView;

    private Button mBtnOK;

    /**PopupWindow是否打开*/
    private boolean isPopOpened;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        initViews();
        initDatas();
        addListeners();
    }

    private void addListeners() {
        mBtnAddToCart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//               Intent intent = new Intent(getApplication(), AddToCartActivity.class);
//               startActivity(intent);
//               overridePendingTransition(R.anim.activity_bottom_to_top, 0);
                if (isPopOpened == false) {
                    final int x = getWindow().getAttributes().width;
                    final int y = getWindow().getAttributes().height;
                    mPopupWindow.setAnimationStyle(R.style.PopupWindowStyle);
                    mPopupWindow.showAtLocation(mImageView, Gravity.BOTTOM, 0, 0);
                }
            }
        });

        mBtnOK.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (isPopOpened==true) {
                    mPopupWindow.dismiss();
                }
            }
        });

    }

    private void initViews() {
        mTVList = (TextView) findViewById(R.id.tv_activity_product_details_list);
        mTVDetails = (TextView) findViewById(R.id.tv_activity_product_details_details);
        mBtnAddToCart = (Button) findViewById(R.id.btn_activity_product_details_add_to_cart);
        mImageView = (ImageView) findViewById(R.id.img_activity_product);

        mPop = LayoutInflater.from(this).inflate(R.layout.popup_add_to_cart,null);
        mBtnOK = (Button) mPop.findViewById(R.id.btn_pop_ok);
        mPopupWindow = new PopupWindow(mPop, getWindow().getAttributes().width, 1080);
    }

    private void initDatas() {

        String details = getIntent().getStringExtra("details");
        mTVDetails.setText(details);
        mTVList.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG| Paint.ANTI_ALIAS_FLAG);

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
            mPopupWindow.dismiss();
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isPopOpened) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
    }
}
