package io.github.sunxu3074.shoppoingdemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhangyan on 2015/10/29.
 */
public class ProductReadDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ProductReader.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ProductReaderContract.ProductEntry.TABLE_NAME + " (" +
                    ProductReaderContract.ProductEntry.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY " +
                    "AUTOINCREMENT NOT NULL," +
                    ProductReaderContract.ProductEntry.COLUMN_NAME_CATEGORY + TEXT_TYPE +
                    COMMA_SEP +
                    ProductReaderContract.ProductEntry.COLUMN_NAME_NUMBER + TEXT_TYPE +
                    COMMA_SEP +
                    ProductReaderContract.ProductEntry.COLUMN_NAME_PRICE + TEXT_TYPE +
                    COMMA_SEP +
                    ProductReaderContract.ProductEntry.COLUMN_NAME_NAME + TEXT_TYPE +
//                    COMMA_SEP +
                    " )";




    public ProductReadDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
