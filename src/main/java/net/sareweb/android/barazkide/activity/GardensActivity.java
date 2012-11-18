package net.sareweb.android.barazkide.activity;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.fragment.GardensFragment;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

public class GardensActivity extends Activity implements OnNavigationListener {

	private static String TAG = "GardensActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		setContentView(R.layout.gardens_activity);

		SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this,
				R.array.garden_spinner_menu,
				android.R.layout.simple_spinner_dropdown_item);

		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionBar.setListNavigationCallbacks(mSpinnerAdapter, this);

	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		loadGardens(itemPosition);
		return false;
	}

	private void loadGardens(int gardenListType) {
		Log.d(TAG, "Loading gardens");
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		GardensFragment gardensFragment = (GardensFragment) fragmentManager.findFragmentById(R.id.gardensFragmentContainer);
		gardensFragment.setGardenContent(gardenListType);
		fragmentTransaction.commit();
		
	}


}
