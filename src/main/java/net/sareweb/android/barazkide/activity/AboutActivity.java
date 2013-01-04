package net.sareweb.android.barazkide.activity;

import net.sareweb.android.barazkide.R;
import android.app.Activity;
import android.os.Bundle;

import com.googlecode.androidannotations.annotations.EActivity;

@EActivity(R.layout.about)
public class AboutActivity extends Activity {
	
	private static String TAG = "AboutActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

}
