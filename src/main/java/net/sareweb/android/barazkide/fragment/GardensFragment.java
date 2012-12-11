package net.sareweb.android.barazkide.fragment;

import java.util.List;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.activity.GardenDetailActivity_;
import net.sareweb.android.barazkide.adapter.GardenAdapter;
import net.sareweb.android.barazkide.model.Garden;
import net.sareweb.android.barazkide.rest.BarazkideConnectionData;
import net.sareweb.android.barazkide.rest.GardenRESTClient;
import net.sareweb.android.barazkide.util.BarazkidePrefs_;
import net.sareweb.android.barazkide.util.ConnectionUtils;
import net.sareweb.android.barazkide.util.Constants;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EFragment(R.layout.gardens_fragment)
public class GardensFragment extends SherlockFragment implements OnItemClickListener{
	
	private static String TAG = "GardensFragment";
	@Pref BarazkidePrefs_ prefs; 
	
	int gardenListType = 0;

	public void setGardenContent(int gardenListType){
		TextView gardenListTypeTextView = (TextView) getView().findViewById(
				R.id.gardenListType);
		switch (gardenListType) {
		case Constants.GARDEN_LIST_ALL:
			gardenListTypeTextView.setText("All gardens");
			getGardens(0);
			break;
		case Constants.GARDEN_LIST_FOLLWED:
			gardenListTypeTextView.setText("Followed gardens");
			getFollowedGardes(prefs.userId().get());
			break;
		case Constants.GARDEN_LIST_MINE:
			gardenListTypeTextView.setText("My gardens");
			getGardens(10196);
			break;
		}
	}
	
	@Background
	public void getGardens(long ownerUserId){
		GardenRESTClient gardenRestClient = new GardenRESTClient(new BarazkideConnectionData(prefs));
		getGardensResult(gardenRestClient.getUserGardensFromDate(ownerUserId, 0, false, 0));
	}
	
	@Background
	public void getFollowedGardes(long userId){
		GardenRESTClient gardenRestClient = new GardenRESTClient(new BarazkideConnectionData(prefs));
		getGardensResult(gardenRestClient.getFollowedGardensOlderThanDate(userId, System.currentTimeMillis(), Constants.DEFAULT_BLOCK_SIZE));
	}
	
	@UiThread
	public void getGardensResult(List<Garden> gardens){
		ListView gardensListView = (ListView) getActivity().findViewById(R.id.garden_list_view);
		gardensListView.setAdapter(new GardenAdapter(getActivity(), gardens));
		gardensListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Garden garden = (Garden) view.getTag();
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		GardenDetailFragment gardenDetailFragment = (GardenDetailFragment)fragmentManager.findFragmentById(R.id.gardenDetailFragment);
		if(gardenDetailFragment!=null){
			gardenDetailFragment.setGardenContent(garden);
			fragmentTransaction.commitAllowingStateLoss();
		}
		else{
			GardenDetailActivity_.intent(getSherlockActivity()).garden(garden).start();
		}
		
	}

}