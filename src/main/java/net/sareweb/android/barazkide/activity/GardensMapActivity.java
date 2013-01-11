package net.sareweb.android.barazkide.activity;

import net.sareweb.android.barazkide.R;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMapOptionsCreator;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CameraPositionCreator;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.MarkerOptionsCreator;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsItem;

@EActivity
public class GardensMapActivity extends SherlockFragmentActivity {

	private static final String TAG = "GardensMapActivity";
	private SupportMapFragment mapFragment;
	private ActionBar actionBar;
	
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
		
		MarkerOptions mo = new MarkerOptions();
		mo.position(pagola);
		mapFragment.getMap().addMarker(mo);
		
	}
	
	@OptionsItem(android.R.id.home)
	void homeSelected() {
		finish();
	}

}
