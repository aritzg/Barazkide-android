package net.sareweb.android.barazkide.activity;

import net.sareweb.android.barazkide.R;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationSelectorActivity extends SherlockFragmentActivity {

	private static final String TAG = "LocationSelectorActivity";
	private SupportMapFragment mapFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
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
		MarkerOptions mo = new MarkerOptions();
		mapFragment.getMap().addMarker(mo);
	}

}
