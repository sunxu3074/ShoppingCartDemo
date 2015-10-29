package io.github.sunxu3074.shoppoingdemo.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import io.github.sunxu3074.shoppoingdemo.Entity.ShoppingCartEntity;
import io.github.sunxu3074.shoppoingdemo.R;
import io.github.sunxu3074.shoppoingdemo.db.ProductReadDbHelper;

public class ShoppingCartActivity extends ActionBarActivity {

    private ListView mListView;
    /**
     * 结算
     */
    private Button mBtnClearing;

    private ArrayList<ShoppingCartEntity> mDatas = new ArrayList<>();

    private ProductReadDbHelper mDbHelper = new ProductReadDbHelper(this);


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        initDatas();
    }

    private void initDatas() {

        //长时间操作 另启线程
        //查询数据库
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        //-------------->android training

//
//        String[] projection = {
//                ProductReaderContract.ProductEntry._ID,
//                ProductReaderContract.ProductEntry.COLUMN_NAME_TITLE,
//                ProductReaderContract.ProductEntry.COLUMN_NAME_UPDATED,
//        };
//
//        String sortOrder =
//                ProductReaderContract.ProductEntry.COLUMN_NAME_UPDATED + " DESC";
//
//        Cursor c = db.query(
//                ProductReaderContract.ProductEntry.TABLE_NAME,  // The table to query
//                projection,                               // The columns to return
//                selection,                                // The columns for the WHERE clause
//                selectionArgs,                            // The values for the WHERE clause
//                null,                                     // don't group the rows
//                null,                                     // don't filter by row groups
//                sortOrder                                 // The sort order
//        );

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
