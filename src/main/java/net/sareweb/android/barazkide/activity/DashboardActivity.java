package net.sareweb.android.barazkide.activity;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.custom.DashboardButton;
import android.app.Activity;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsItem;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.dashboard)
public class DashboardActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Click(R.id.btnAddGarden)
	public void clickOnAddGarden(){
		
	}
	
	@Click(R.id.btnList)
	public void clickOnGardenList(){
		GardensActivity_.intent(this).showHomeBack(true).start();
	}
	
	@Click(R.id.btnMap)
	public void clickOnGardenMap(){
		GardensActivity_.intent(this).start();
	}
	
	@Click(R.id.btnEventMap)
	public void clickOnEventMap(){
		GardensActivity_.intent(this).start();
	}
	
	@Click(R.id.btnSettings)
	public void clickOnSettings(){
		GardensActivity_.intent(this).start();
	}
	
	@Click(R.id.btnAbout)
	public void clickOnAbout(){
		GardensActivity_.intent(this).start();
	}
	
}
