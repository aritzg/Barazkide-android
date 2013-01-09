package net.sareweb.android.barazkide.activity;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.fragment.GardenDetailFragment;
import net.sareweb.android.barazkide.fragment.GardensFragment;
import net.sareweb.android.barazkide.rest.BarazkideConnectionData;
import net.sareweb.android.barazkide.rest.GardenRESTClient;
import net.sareweb.android.barazkide.util.BarazkidePrefs_;
import net.sareweb.android.barazkide.util.PrefUtils;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.FragmentById;
import com.googlecode.androidannotations.annotations.OptionsItem;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;
import net.sareweb.android.barazkide.fragment.EventsFragment;

@EActivity(R.layout.gardens_activity)
@OptionsMenu(R.menu.main_menu)
public class GardensActivity extends SherlockFragmentActivity implements
		OnNavigationListener {

	private static String TAG = "GardensActivity";

	GardenRESTClient gardenRESTClient;
	@FragmentById
	GardensFragment gardensFragment;
	@FragmentById
	GardenDetailFragment gardenDetailFragment;
	@FragmentById
	EventsFragment eventsFragment;
	@Pref
	BarazkidePrefs_ prefs;
	ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		gardenRESTClient = new GardenRESTClient(new BarazkideConnectionData(
				prefs));

		actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

	}

	@Override
	protected void onResume() {
		super.onResume();
		SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this,
				R.array.garden_spinner_menu,
				android.R.layout.simple_selectable_list_item);
		actionBar.setListNavigationCallbacks(mSpinnerAdapter, this);
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		loadGardens(itemPosition);
		return false;
	}

	private void loadGardens(int gardenListType) {
		Log.d(TAG, "Loading gardens " + gardenListType);
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		gardensFragment.setGardenContent(gardenListType);
		fragmentTransaction.commitAllowingStateLoss();
	}

	
	@OptionsItem(R.id.menu_home)
	void homeSelected() {
		finish();
		DashboardActivity_.intent(this).start();
	}
	
	@OptionsItem(R.id.menu_about)
	void aboutSelected() {
		AboutActivity_.intent(this).start();
	}

	@OptionsItem(R.id.menu_log_out)
	void logOutSelected() {
		PrefUtils.clearUserPrefs(prefs);
		finish();
		LogInActivity_.intent(this).start();
	}
}