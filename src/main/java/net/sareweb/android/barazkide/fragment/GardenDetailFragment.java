package net.sareweb.android.barazkide.fragment;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.model.Garden;

import com.googlecode.androidannotations.annotations.EFragment;

import android.app.Fragment;
import android.widget.TextView;

@EFragment(R.layout.garden_detail)
public class GardenDetailFragment extends Fragment {
	
	private static String TAG = "GardenDetailFragment";
	
	public void setGardenContent(Garden garden){
		TextView txName = (TextView)(getActivity().findViewById(R.id.txName));
		txName.setText(garden.getName());
	}

}
