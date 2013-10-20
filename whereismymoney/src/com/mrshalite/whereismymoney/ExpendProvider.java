package com.mrshalite.whereismymoney;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.mrshalite.whereismymoney.ExpendContract.Expend;

public class ExpendProvider extends ContentProvider{
	// Used for debugging and logging
    private static final String TAG = "ExpendProvider";
    
    static public class ExpendOpenHelper extends SQLiteOpenHelper{
    	public static final String DATABASENAME = "expend";
    	public static final int DATABASEVERSION = 0;
    	
    	private static final String TEXT_TYPE = " TEXT";
		private static final String INTEGER_TYPE = " INTEGER";
		private static final String REAL_TYPE = " REAL";
		private static final String COMMA_SEP = ", ";
		private static final String SQL_CREATE_ENTRIES = 
						"CREATE TABLE " + Expend.TABLE_NAME + " (" +
								Expend._ID + " INTEGER PRIMARY KEY, " +
								Expend.COLUMN_NAME_YEAR + INTEGER_TYPE + COMMA_SEP +
								Expend.COLUMN_NAME_MONTH + INTEGER_TYPE + COMMA_SEP +
								Expend.COLUMN_NAME_DATE + INTEGER_TYPE + COMMA_SEP +
								Expend.COLUMN_NAME_EXPEND + TEXT_TYPE + COMMA_SEP +
								Expend.COLUMN_NAME_MONEY + REAL_TYPE + COMMA_SEP +
								Expend.COLUMN_NAME_TYPE1 + INTEGER_TYPE + COMMA_SEP +
								Expend.COLUMN_NAME_TYPE2 + INTEGER_TYPE + COMMA_SEP +
								Expend.COLUMN_NAME_TYPE3 + INTEGER_TYPE + COMMA_SEP +
								Expend.COLUMN_NAME_TYPE4 + INTEGER_TYPE + 
						"); ";
		private static final String SQL_DELETE_ENTRIES =
						"DROP TABLE IF EXISTS " + Expend.TABLE_NAME;
    	
    	public ExpendOpenHelper(Context context){
    		super(context, DATABASENAME, null, DATABASEVERSION);
    	}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(SQL_CREATE_ENTRIES);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int versionold, int versionnew) {
			// TODO Auto-generated method stub
			
			// Logs that the database is being upgraded
	           Log.w(TAG, "Upgrading database from version " + versionold + " to "
	                   + versionnew + ", which will destroy all old data");
	           
			db.execSQL(SQL_DELETE_ENTRIES);
			
			onCreate(db);
		}
    }
    
    private ExpendOpenHelper mExpendOpenHelper;
    
    /*
     * Constants used by the Uri matcher to choose an action based on the pattern
     * of the incoming URI
     */
    // The incoming URI matches the project URI pattern
    private static final int EXPENDS = 1;

    // The incoming URI matches the project ID URI pattern
    private static final int EXPEND_ID = 2;
    
    /**
     * A projection map used to select columns from the database
     */
    private static HashMap<String, String> sExpendsProjectionMap;
    private static HashMap<String, String> sExpendItemsProjectionMap;
    
    /**
     * A UriMatcher instance
     */
    private static final UriMatcher sUriMatcher;
    
    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(ExpendContract.AUTHORITY, Expend.PATH_EXPENDS, EXPENDS);
        sUriMatcher.addURI(ExpendContract.AUTHORITY, Expend.PATH_EXPEND_ID, EXPEND_ID);

        sExpendsProjectionMap = new HashMap<String, String>();
        sExpendsProjectionMap.put(Expend._ID, Expend._ID);
        sExpendsProjectionMap.put(Expend.COLUMN_NAME_EXPEND, Expend.COLUMN_NAME_EXPEND);
        sExpendsProjectionMap.put(Expend.COLUMN_NAME_MONEY, Expend.COLUMN_NAME_MONEY);

        sExpendItemsProjectionMap = new HashMap<String, String>();
        sExpendItemsProjectionMap.put(Expend._ID, Expend._ID);
        sExpendItemsProjectionMap.put(Expend.COLUMN_NAME_YEAR, Expend.COLUMN_NAME_YEAR);
        sExpendItemsProjectionMap.put(Expend.COLUMN_NAME_MONTH, Expend.COLUMN_NAME_MONTH);
        sExpendItemsProjectionMap.put(Expend.COLUMN_NAME_DATE, Expend.COLUMN_NAME_DATE);
        sExpendItemsProjectionMap.put(Expend.COLUMN_NAME_EXPEND, Expend.COLUMN_NAME_EXPEND);
        sExpendItemsProjectionMap.put(Expend.COLUMN_NAME_MONEY, Expend.COLUMN_NAME_MONEY);
        sExpendItemsProjectionMap.put(Expend.COLUMN_NAME_TYPE1, Expend.COLUMN_NAME_TYPE1);
        sExpendItemsProjectionMap.put(Expend.COLUMN_NAME_TYPE2, Expend.COLUMN_NAME_TYPE2);
        sExpendItemsProjectionMap.put(Expend.COLUMN_NAME_TYPE3, Expend.COLUMN_NAME_TYPE3);
        sExpendItemsProjectionMap.put(Expend.COLUMN_NAME_TYPE4, Expend.COLUMN_NAME_TYPE4);
        
    }

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db;
		String id;	
        int count;
        
        switch (sUriMatcher.match(uri)) {
        case EXPEND_ID:
        	db = mExpendOpenHelper.getWritableDatabase();
        	id = uri.getPathSegments().get(1);
            count = db.delete(Expend.TABLE_NAME, Expend._ID + "=" + id
                    + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
            break;


        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch (sUriMatcher.match(uri)) {
        case EXPENDS:
        	return Expend.CONTENT_TYPE;
        	
        case EXPEND_ID:
            return Expend.CONTENT_ITEM_TYPE;

        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
	}

	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {
		// TODO Auto-generated method stub
		// Validate the requested uri
        if (sUriMatcher.match(uri) != EXPENDS) {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        ContentValues values;
        if (initialValues != null) {
            values = new ContentValues(initialValues);
        } else {
            values = new ContentValues();
        }
        
        if (sUriMatcher.match(uri) == EXPENDS){
        	SQLiteDatabase db = mExpendOpenHelper.getWritableDatabase();
            long rowId = db.insert(Expend.TABLE_NAME, null, values);
            if (rowId > 0) {
                Uri noteUri = ContentUris.withAppendedId(Expend.CONTENT_URI, rowId);
                getContext().getContentResolver().notifyChange(noteUri, null);
                return noteUri;
            }
        }
        
        throw new SQLException("Failed to insert row into " + uri);
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		mExpendOpenHelper = new ExpendOpenHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri arg0, String[] arg1, String arg2, String[] arg3,
			String arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db;
		String id;
        int count;
        
        switch (sUriMatcher.match(uri)) {
        case EXPEND_ID:
        	db = mExpendOpenHelper.getWritableDatabase();
        	id = uri.getPathSegments().get(1);
            count = db.update(Expend.TABLE_NAME, values, Expend._ID + "=" + id
        			+ (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
            break;

        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
	}

}
