package net.sareweb.android.barazkide.activity;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.fragment.GardenDetailFragment;
import net.sareweb.android.barazkide.model.Garden;
import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.FragmentById;
import com.googlecode.androidannotations.annotations.OptionsItem;

@EActivity(R.layout.garden_detail_activity)
public class GardenDetailActivity extends SherlockFragmentActivity{
	
	@FragmentById
    GardenDetailFragment gardenDetailFragment;
	@Extra
	Garden garden;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle(garden.getName());
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		gardenDetailFragment.setGardenContent(garden);
	}
	
	@OptionsItem
	void homeSelected() {
		finish();
	}
	
	
}
