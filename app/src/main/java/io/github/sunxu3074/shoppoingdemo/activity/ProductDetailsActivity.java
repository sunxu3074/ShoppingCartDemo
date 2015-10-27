package io.github.sunxu3074.shoppoingdemo.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.github.sunxu3074.shoppoingdemo.R;

public class ProductDetailsActivity extends ActionBarActivity {

    private String details ;

    private TextView mTVDetails;
    private TextView mTVList;

    private Button mBtnAddToCart;

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
                Intent intent = new Intent(getApplication(), AddToCartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        mTVList = (TextView) findViewById(R.id.tv_activity_product_details_list);
        mTVDetails = (TextView) findViewById(R.id.tv_activity_product_details_details);
        mBtnAddToCart = (Button) findViewById(R.id.btn_activity_product_details_add_to_cart);
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
}
