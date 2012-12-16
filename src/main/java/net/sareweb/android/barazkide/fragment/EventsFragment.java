package net.sareweb.android.barazkide.fragment;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.util.BarazkidePrefs_;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EFragment(R.layout.events_fragment)
public class EventsFragment extends SherlockFragment implements OnItemClickListener{
	
	private static String TAG = "EventsFragment";
	@Pref BarazkidePrefs_ prefs; 
	
	public void setEventsContent(int gardenListType){
		
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
	}

}