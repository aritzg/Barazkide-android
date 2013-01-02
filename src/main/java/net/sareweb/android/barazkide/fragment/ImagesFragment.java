package net.sareweb.android.barazkide.fragment;

import java.util.List;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.adapter.EventAdapter;
import net.sareweb.android.barazkide.adapter.ImageAdapter;
import net.sareweb.android.barazkide.model.Event;
import net.sareweb.android.barazkide.model.Garden;
import net.sareweb.android.barazkide.rest.BarazkideConnectionData;
import net.sareweb.android.barazkide.rest.EventRESTClient;
import net.sareweb.android.barazkide.util.BarazkidePrefs_;
import net.sareweb.android.barazkide.util.Constants;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.FragmentArg;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EFragment(R.layout.images_fragment)
//@OptionsMenu(R.menu.garden_menu)
public class ImagesFragment extends SherlockFragment implements OnItemClickListener{
	
	EventRESTClient eventRESTClient;
	
	private static String TAG = "EventsFragment";
	@Pref BarazkidePrefs_ prefs;
	@FragmentArg
	long gardenId;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		eventRESTClient = new EventRESTClient(new BarazkideConnectionData(prefs));
	}
	
	@Override
	public void onAttach(Activity activity) {
		Log.d(TAG,"onAttach");
		super.onAttach(activity);
		if(gardenId!=0)getGardenEvents(gardenId);
	}
	
	@Override
	public void onResume() {
		Log.d(TAG,"onAttach");
		super.onResume();
		if(gardenId!=0)getGardenEvents(gardenId);
	}


	public void setGardenListEventsContent(int gardenListType){
		
	}
	
	public void setGardenEventsContent(long gardenId){
		getGardenEvents(gardenId);
	}
	
	@Background
	void getGardenEvents(long gardenId){
		getGardenEventsResult(eventRESTClient.findImageTypeEventsInGardenOlderThanDate(gardenId, System.currentTimeMillis(), 100));
	}
	
	@UiThread
	void getGardenEventsResult(List<Event> events){
		if(events!=null){
			GridView imageGridView = (GridView) getActivity().findViewById(R.id.gridview);
			imageGridView.setAdapter(new ImageAdapter(getActivity(), events));
			imageGridView.setOnItemClickListener(this);
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
	}

}