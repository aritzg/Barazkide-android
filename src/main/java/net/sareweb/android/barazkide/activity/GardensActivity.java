package net.sareweb.android.barazkide.activity;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.fragment.GardenDetailFragment;
import net.sareweb.android.barazkide.fragment.GardensFragment;
import net.sareweb.android.barazkide.fragment.GardensFragment_;
import net.sareweb.android.barazkide.rest.GardenRESTClient;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.FragmentById;

@EActivity(R.layout.gardens_activity)
public class GardensActivity extends Activity implements OnNavigationListener {

	private static String TAG = "GardensActivity";
	
    GardenRESTClient gardenRESTClient;
    @FragmentById(R.id.gardensFragmentContainer)
    GardensFragment gardensFragment;
    @FragmentById(R.id.gardenDetailContainer)
    GardenDetailFragment gardenDetailFragment;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		gardenRESTClient = new GardenRESTClient("test", "test1");
		
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
		gardensFragment.setGardenContent(gardenListType);
		fragmentTransaction.commitAllowingStateLoss();
		
	}
	
}