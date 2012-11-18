package net.sareweb.android.barazkide.fragment;

import net.sareweb.android.barazkide.Constants;
import net.sareweb.android.barazkide.R;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GardensFragment extends ListFragment {
	
	private static String TAG = "GardensFragment";
	int gardenListType = 0;

	public GardensFragment() {
		// new GardensFragment(0);
	}
	
	public GardensFragment(int gardenListType) {
		Log.d(TAG, "GardensFragment Constructor");
		this.gardenListType = gardenListType;
	}

	public void setGardenContent(int gardenListType){
		TextView gerdenListTypeTextView = (TextView) getView().findViewById(
				R.id.gardenListType);
		switch (gardenListType) {
		case Constants.GARDEN_LIST_ALL:
			gerdenListTypeTextView.setText("All gardens");
			break;
		case Constants.GARDEN_LIST_FOLLWED:
			gerdenListTypeTextView.setText("Followed gardens");
			break;
		case Constants.GARDEN_LIST_MINE:
			gerdenListTypeTextView.setText("My gardens");
			break;
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.gardens_fragment, container, false); 
	}


}
