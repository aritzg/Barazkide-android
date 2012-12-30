package net.sareweb.android.barazkide.activity;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.fragment.GardenDetailFragment;
import net.sareweb.android.barazkide.listener.tab.GardenDetailTabListener;
import net.sareweb.android.barazkide.model.Garden;
import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
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
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle(garden.getName());

		Tab gardenDetailTab = actionBar.newTab();
		gardenDetailTab.setText("Detalis");
		gardenDetailTab.setTabListener(new GardenDetailTabListener(garden, GardenDetailTabListener.GARDEN_DETAILS, this));
		actionBar.addTab(gardenDetailTab);
		
		Tab gardenEventsTab = actionBar.newTab();
		gardenEventsTab.setText("Events");
		gardenEventsTab.setTabListener(new GardenDetailTabListener(garden, GardenDetailTabListener.GARDEN_EVENTS, this));
		actionBar.addTab(gardenEventsTab);
		
		Tab imagesTab = actionBar.newTab();
		imagesTab.setText("Images");
		imagesTab.setTabListener(new GardenDetailTabListener(garden, GardenDetailTabListener.GARDEN_IMAGES, this));
		actionBar.addTab(imagesTab);
		
		Tab badgesTab = actionBar.newTab();
		badgesTab.setText("Badges");
		badgesTab.setTabListener(new GardenDetailTabListener(garden, GardenDetailTabListener.GARDEN_BADGES, this));
		actionBar.addTab(badgesTab);
		
		//actionBar.setSelectedNavigationItem(0);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		//gardenDetailFragment.setGardenContent(garden);
	}
	
	@OptionsItem
	void homeSelected() {
		finish();
	}
	
	
}
