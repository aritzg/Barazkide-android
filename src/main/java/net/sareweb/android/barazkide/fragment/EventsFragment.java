package net.sareweb.android.barazkide.fragment;

import java.util.List;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.adapter.EventAdapter;
import net.sareweb.android.barazkide.model.Event;
import net.sareweb.android.barazkide.rest.BarazkideConnectionData;
import net.sareweb.android.barazkide.rest.EventRESTClient;
import net.sareweb.android.barazkide.util.BarazkidePrefs_;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EFragment(R.layout.events_fragment)
public class EventsFragment extends SherlockFragment implements OnItemClickListener{
	
	EventRESTClient eventRESTClient;
	
	private static String TAG = "EventsFragment";
	@Pref BarazkidePrefs_ prefs;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		eventRESTClient = new EventRESTClient(new BarazkideConnectionData(prefs));
	}
	
	public void setGardenListEventsContent(int gardenListType){
		
	}
	
	public void setGardenEventsContent(long gardenId){
		getGardenEvents(gardenId);
	}
	
	@Background
	void getGardenEvents(long gardenId){
		getGardenEventsResult(eventRESTClient.findEventsInGardenOlderThanDate(gardenId, System.currentTimeMillis(), 100));
	}
	
	@UiThread
	void getGardenEventsResult(List<Event> events){
		if(events!=null){
			ListView eventListView = (ListView) getActivity().findViewById(R.id.event_list_view);
			eventListView.setAdapter(new EventAdapter(getActivity(), events));
			eventListView.setOnItemClickListener(this);
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
	}

}