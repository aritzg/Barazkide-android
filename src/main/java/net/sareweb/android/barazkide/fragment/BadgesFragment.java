package net.sareweb.android.barazkide.fragment;

import java.util.List;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.adapter.BadgeAdapter;
import net.sareweb.android.barazkide.adapter.EventAdapter;
import net.sareweb.android.barazkide.adapter.ImageAdapter;
import net.sareweb.android.barazkide.model.Badge;
import net.sareweb.android.barazkide.model.Event;
import net.sareweb.android.barazkide.model.Garden;
import net.sareweb.android.barazkide.rest.BadgeRESTClient;
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

@EFragment(R.layout.badges_fragment)
//@OptionsMenu(R.menu.garden_menu)
public class BadgesFragment extends SherlockFragment implements OnItemClickListener{
	
	BadgeRESTClient badgeRESTClient;
	
	private static String TAG = "BadgesFragment";
	@Pref BarazkidePrefs_ prefs;
	@FragmentArg
	long gardenId;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		badgeRESTClient = new BadgeRESTClient(new BarazkideConnectionData(prefs));
	}
	
	@Override
	public void onAttach(Activity activity) {
		Log.d(TAG,"onAttach");
		super.onAttach(activity);
		if(gardenId!=0)getGardenBadges(gardenId);
	}
	
	@Override
	public void onResume() {
		Log.d(TAG,"onAttach");
		super.onResume();
		if(gardenId!=0)getGardenBadges(gardenId);
	}


	@Background
	void getGardenBadges(long gardenId){
		getGardenBadgesResult(badgeRESTClient.getGardenBadges(gardenId));
	}
	
	@UiThread
	void getGardenBadgesResult(List<Badge> badges){
		if(badges!=null){
			GridView imageGridView = (GridView) getActivity().findViewById(R.id.gridview);
			imageGridView.setAdapter(new BadgeAdapter(getActivity(), badges));
			imageGridView.setOnItemClickListener(this);
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
	}

}