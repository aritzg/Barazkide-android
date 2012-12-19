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
	
	public void setGardenContent(int gardenListType){
		getGardens(prefs.userId().get(), gardenListType);
	}
	
	@Background
	public void getGardens(long userId, int gardenListType){
		GardenRESTClient gardenRestClient = new GardenRESTClient(new BarazkideConnectionData(prefs));
		switch (gardenListType) {
		case Constants.GARDEN_LIST_ALL:
			getGardensResult(gardenRestClient.getGardens());
			break;
		case Constants.GARDEN_LIST_FOLLWED:
			getGardensResult(gardenRestClient.getFollowedGardensOlderThanDate(userId, System.currentTimeMillis(), Constants.DEFAULT_BLOCK_SIZE));
			break;
		case Constants.GARDEN_LIST_MINE:
			getGardensResult(gardenRestClient.getUserGardensFromDate(userId, 0, false, 0));
			break;
		}
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