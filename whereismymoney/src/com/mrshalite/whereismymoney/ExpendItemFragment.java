package com.mrshalite.whereismymoney;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mrshalite.whereismymoney.ExpendContract.Expend;

public class ExpendItemFragment extends Fragment {

	public static final String TAG = "expend items";
	
	TextView date;
	TextView expend;
	TextView money;
	TextView type;
	
	ExpendItemsLoader loader;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View root = inflater.inflate(R.layout.expends_item, container, false); 
		
		date = (TextView)root.findViewById(R.id.date);
		expend = (TextView)root.findViewById(R.id.expend);
		money = (TextView)root.findViewById(R.id.money);
		type = (TextView)root.findViewById(R.id.type);
		
		loader = new ExpendItemsLoader();
		
		getLoaderManager().initLoader(0, null, loader);
		
		return root;
	}
	
	
	
	void update(Cursor c){
		
	}
	
	class ExpendItemsLoader implements LoaderManager.LoaderCallbacks<Cursor>{
		// These are the Contacts rows that we will retrieve.
	    final String[] EXPEND_ITEM_PROJECTION = new String[] {
	        Expend._ID,
	        Expend.COLUMN_NAME_YEAR,
	        Expend.COLUMN_NAME_MONTH,
	        Expend.COLUMN_NAME_DATE,
	        Expend.COLUMN_NAME_EXPEND,
	        Expend.COLUMN_NAME_MONEY,
	        Expend.COLUMN_NAME_TYPE1,
	        Expend.COLUMN_NAME_TYPE2,
	        Expend.COLUMN_NAME_TYPE3,
	        Expend.COLUMN_NAME_TYPE4
	    };
	    
	    static final int INDEX_YEAR = 1;
	    static final int INDEX_MONTH = 2;
	    static final int INDEX_DATE = 3;
	    static final int INDEX_EXPEND = 4;
	    static final int INDEX_MONEY = 5;
	    static final int INDEX_TYPE1 = 6;
	    static final int INDEX_TYPE2 = 7;
	    static final int INDEX_TYPE3 = 8;
	    static final int INDEX_TYPE4 = 9;

		@Override
		public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void onLoadFinished(Loader<Cursor> l, Cursor c) {
			// TODO Auto-generated method stub
			if (0 < c.getCount()){
				c.moveToFirst();
				String tmp = c.getString(INDEX_YEAR);
				tmp += c.getString(INDEX_MONTH);
				tmp += c.getString(INDEX_DATE);
				
			}
			
		}

		@Override
		public void onLoaderReset(Loader<Cursor> l) {
			// TODO Auto-generated method stub
			update(null);
		}
		
	}
}
