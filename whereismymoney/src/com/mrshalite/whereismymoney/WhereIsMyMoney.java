package com.mrshalite.whereismymoney;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class WhereIsMyMoney extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.where_is_my_money);
		
		if (savedInstanceState != null) {
            return;
        }

        // Create an instance of ExampleFragment
        ExpendsListFragment firstFragment = new ExpendsListFragment();

        // In case this activity was started with special instructions from an Intent,
        // pass the Intent's extras to the fragment as arguments
        firstFragment.setArguments(getIntent().getExtras());

        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, firstFragment, ExpendsListFragment.TAG).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.where_is_my_money, menu);
		return true;
	}

}
