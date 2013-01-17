package net.sareweb.android.barazkide.activity;

import net.sareweb.android.barazkide.model.Garden;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.OptionsItem;

@EActivity
public class LocationSelectorActivity extends SherlockFragmentActivity implements OnMarkerDragListener {
	
	@Extra Garden garden;
	private static final String TAG = "LocationSelectorActivity";
	private SupportMapFragment mapFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		initDefaultLatLng();
		
		LinearLayout layout = new LinearLayout(this);
		layout.setId(1111);
		
		CameraPosition cameraPosition = new CameraPosition(defaultLatLng, 8, 0, 0);
		GoogleMapOptions options = new GoogleMapOptions();
		options.camera(cameraPosition);
		mapFragment = SupportMapFragment.newInstance(options);
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		
		ft.add(layout.getId(), mapFragment);
		ft.commit();
		
		setContentView(layout);
		
	}
	

	@Override
	protected void onResume() {
		super.onResume();
		setMarker();
	}
	
	@OptionsItem
	void homeSelected() {
		Toast.makeText(this, ""+garden.getLat(), Toast.LENGTH_LONG).show();
	}
	
	@Override
	public void onMarkerDrag(Marker arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public void onMarkerDragEnd(Marker marker) {
		garden.setLat(marker.getPosition().latitude);
		garden.setLng(marker.getPosition().longitude);
	}


	@Override
	public void onMarkerDragStart(Marker arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private void initDefaultLatLng() {
		if(garden!=null && garden.getLat()!=0 && garden.getLng()!=0){
			defaultLatLng = new LatLng(garden.getLat(), garden.getLng());
		}
	}
	
	private void setMarker(){
		MarkerOptions mo = new MarkerOptions();
		mo.position(defaultLatLng);
		mo.draggable(true);
		mapFragment.getMap().setOnMarkerDragListener(this);
		mapFragment.getMap().addMarker(mo);
	}
	
	LatLng defaultLatLng = new LatLng(43.300251, -1.99838);
}
