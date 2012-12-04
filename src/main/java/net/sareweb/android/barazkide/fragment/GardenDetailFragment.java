package net.sareweb.android.barazkide.fragment;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.model.Garden;

import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.EFragment;

import android.widget.TextView;

@EFragment(R.layout.garden_detail)
public class GardenDetailFragment extends SherlockFragment {
	
	private static String TAG = "GardenDetailFragment";
	
	public void setGardenContent(Garden garden){
		TextView txName = (TextView)(getActivity().findViewById(R.id.txName));
		txName.setText(garden.getName());
	}

}
