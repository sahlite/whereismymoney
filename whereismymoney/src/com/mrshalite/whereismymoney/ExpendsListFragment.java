package com.mrshalite.whereismymoney;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrshalite.whereismymoney.ExpendContract.Expend;

public class ExpendsListFragment extends ListFragment {

	public static final String TAG = "expends list";
	
	ExpendsListLoader loader;
	
	// This is the Adapter being used to display the list's data.
    SimpleCursorAdapter mAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// Give some text to display if there is no data.  In a real
        // application this would come from a resource.
        setEmptyText("No items!");

        // We have a menu item to show in action bar.
        setHasOptionsMenu(true);

        // Create an empty adapter we will use to display the loaded data.
        mAdapter = new SimpleCursorAdapter(getActivity(),
                R.layout.expends_list_item, null,
                new String[] { Expend.COLUMN_NAME_EXPEND, Expend.COLUMN_NAME_MONEY},
                new int[] { R.id.expendName, R.id.expendMoney}, 0);
        
        setListAdapter(mAdapter);

        loader = new ExpendsListLoader();
        
        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        getLoaderManager().initLoader(0, null, loader);
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	class ExpendsListLoader implements LoaderManager.LoaderCallbacks<Cursor>{
		
		// These are the Contacts rows that we will retrieve.
	    final String[] EXPEND_LIST_PROJECTION = new String[] {
	        Expend._ID,
	        Expend.COLUMN_NAME_EXPEND,
	        Expend.COLUMN_NAME_MONEY
	    };

		@Override
		public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
			// TODO Auto-generated method stub
			return new CursorLoader(getActivity(), Expend.CONTENT_URI, 
					EXPEND_LIST_PROJECTION, null, null, null);
		}

		@Override
		public void onLoadFinished(Loader<Cursor> l, Cursor c) {
			// TODO Auto-generated method stub
			mAdapter.swapCursor(c);
		}

		@Override
		public void onLoaderReset(Loader<Cursor> l) {
			// TODO Auto-generated method stub
			mAdapter.swapCursor(null);
		}
		
	}
}
