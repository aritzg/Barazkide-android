package net.sareweb.android.barazkide.listener.tab;

import net.sareweb.android.barazkide.fragment.BadgesFragment_;
import net.sareweb.android.barazkide.fragment.EventsFragment_;
import net.sareweb.android.barazkide.fragment.GardenDetailFragment_;
import net.sareweb.android.barazkide.fragment.ImagesFragment_;
import net.sareweb.android.barazkide.model.Garden;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;

public class GardenDetailTabListener implements TabListener {
	
	int gardenDetailTab=GARDEN_DETAILS;
	private Fragment mFragment;
	public Activity mActivity;
	private Garden garden;
	
	public GardenDetailTabListener( Garden garden, int gardenDetailTab, Activity activity){
		this.gardenDetailTab=gardenDetailTab;
		mActivity = activity;
		this.garden=garden;
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
		switch (gardenDetailTab) {
		case GARDEN_DETAILS:
			if (mFragment == null) {
				Bundle bundle= new Bundle();
				bundle.putSerializable("garden", garden);
				mFragment = (GardenDetailFragment_)Fragment.instantiate(mActivity, GardenDetailFragment_.class.getName(),bundle);
				fragmentTransaction.add(android.R.id.content, mFragment);
			}
			else{
				fragmentTransaction.attach(mFragment);
			}
			break;

		case GARDEN_EVENTS:
			if (mFragment == null) {
				Bundle bundle= new Bundle();
				bundle.putLong("gardenId", garden.getGardenId());
				mFragment = (EventsFragment_)Fragment.instantiate(mActivity, EventsFragment_.class.getName(),bundle);
				fragmentTransaction.add(android.R.id.content, mFragment);
			}
			else{
				fragmentTransaction.attach(mFragment);
			}
			break;
			
		case GARDEN_IMAGES:
			if (mFragment == null) {
				Bundle bundle= new Bundle();
				bundle.putLong("gardenId", garden.getGardenId());
				mFragment = (ImagesFragment_)Fragment.instantiate(mActivity, ImagesFragment_.class.getName(),bundle);
				fragmentTransaction.add(android.R.id.content, mFragment);
			}
			else{
				fragmentTransaction.attach(mFragment);
			}
			break;
			
		case GARDEN_BADGES:
			if (mFragment == null) {
				Bundle bundle= new Bundle();
				bundle.putLong("gardenId", garden.getGardenId());
				mFragment = (BadgesFragment_)Fragment.instantiate(mActivity, BadgesFragment_.class.getName(),bundle);
				fragmentTransaction.add(android.R.id.content, mFragment);
			}
			else{
				fragmentTransaction.attach(mFragment);
			}
		break;
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {
		if (mFragment != null) {
			fragmentTransaction.detach(mFragment);
        }

	}
	
	public static final int GARDEN_DETAILS=0;
	public static final int GARDEN_EVENTS=1;
	public static final int GARDEN_IMAGES=2;
	public static final int GARDEN_BADGES=3;

}
