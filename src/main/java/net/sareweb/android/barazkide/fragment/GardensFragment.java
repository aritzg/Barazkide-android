package net.sareweb.android.barazkide.fragment;

import java.util.List;

import net.sareweb.android.barazkide.Constants;
import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.adapter.GardenAdapter;
import net.sareweb.android.barazkide.model.Garden;
import net.sareweb.android.barazkide.rest.GardenRESTClient;
import android.app.Fragment;
import android.widget.ListView;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.UiThread;

@EFragment(R.layout.gardens_fragment)
public class GardensFragment extends Fragment {
	
	private static String TAG = "GardensFragment";
	
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
			getGardens(11021);
			break;
		case Constants.GARDEN_LIST_MINE:
			gardenListTypeTextView.setText("My gardens");
			getGardens(10196);
			break;
		}
	}
	
	@Background
	public void getGardens(long ownerUserId){
		GardenRESTClient gardenRestClient = new GardenRESTClient("test", "test1");
		getGardensResult(gardenRestClient.getUserGardensFromDate(ownerUserId, System.currentTimeMillis(), false, 0));
	}
	
	@UiThread
	public void getGardensResult(List<Garden> gardens){
		ListView gardensListView = (ListView) getActivity().findViewById(R.id.garden_list_view);
		gardensListView.setAdapter(new GardenAdapter(getActivity(), gardens));
	}


}
