package net.sareweb.android.barazkide.activity;

import java.util.List;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.adapter.GardenAdapter;
import net.sareweb.android.barazkide.model.Garden;
import net.sareweb.android.barazkide.rest.BarazkideConnectionData;
import net.sareweb.android.barazkide.rest.GardenRESTClient;
import net.sareweb.android.barazkide.util.BarazkidePrefs_;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptionsCreator;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CameraPositionCreator;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.MarkerOptionsCreator;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsItem;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.rest.Post;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EActivity
public class GardensMapActivity extends SherlockFragmentActivity {

	private static final String TAG = "GardensMapActivity";
	private SupportMapFragment mapFragment;
	private ActionBar actionBar;
	@Pref BarazkidePrefs_ prefs; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		LinearLayout layout = new LinearLayout(this);
		layout.setId(1111);
		
		mapFragment = SupportMapFragment.newInstance();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(layout.getId(), mapFragment);
		ft.commit();
		
		setContentView(layout);
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		LatLng pagola = new LatLng(43.300251, -1.99838);
		
		CameraPosition cameraPosition = new CameraPosition(pagola, 8, 0, 0);
		CameraUpdate cu = CameraUpdateFactory.newCameraPosition(cameraPosition);
		mapFragment.getMap().animateCamera(cu);
		
		getGardens();
		
	}
	
	@Background
	public void getGardens(){
		GardenRESTClient gardenRestClient = new GardenRESTClient(new BarazkideConnectionData(prefs));
		getGardensResult(gardenRestClient.getGardens());
	}
	
	@UiThread
	public void getGardensResult(List<Garden> gardens){
		GoogleMap map = mapFragment.getMap();
		for(Garden garden : gardens){
			if(garden.getLat()!=0 && garden.getLng()!=0){
				LatLng gardenPosition = new LatLng(garden.getLat(), garden.getLng());
				MarkerOptions mo = new MarkerOptions();
				mo.position(gardenPosition);
				map.addMarker(mo);
			}
		}
	}
	
	@OptionsItem(android.R.id.home)
	void homeSelected() {
		finish();
	}

}
