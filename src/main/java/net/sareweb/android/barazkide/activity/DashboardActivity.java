package net.sareweb.android.barazkide.activity;

import net.sareweb.android.barazkide.R;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;

@EActivity(R.layout.dashboard)
public class DashboardActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Click(R.id.btnAddGarden)
	public void clickOnAddGarden(){
		EditGardenActivity_.intent(this).start();
	}
	
	@Click(R.id.btnList)
	public void clickOnGardenList(){
		GardensActivity_.intent(this).showHomeBack(true).start();
	}
	
	@Click(R.id.btnMap)
	public void clickOnGardenMap(){
		GardensMapActivity_.intent(this).start();
	}
	
	@Click(R.id.btnEventMap)
	public void clickOnEventMap(){
	}
	
	@Click(R.id.btnSettings)
	public void clickOnSettings(){
	}
	
	@Click(R.id.btnAbout)
	public void clickOnAbout(){
	}
	
}
