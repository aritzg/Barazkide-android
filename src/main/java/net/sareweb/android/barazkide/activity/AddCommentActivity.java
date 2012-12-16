package net.sareweb.android.barazkide.activity;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.model.Garden;
import net.sareweb.android.barazkide.util.BarazkidePrefs_;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.add_comment)
public class AddCommentActivity extends SherlockActivity{
	
	private static String TAG = "AddCommentActivity";
	@Pref BarazkidePrefs_ prefs;
	@Extra Garden garden;
	@ViewById
	TextView txTitle;
	@ViewById
	EditText txComment;
	ProgressDialog dialog;
	AlertDialog confirmDialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("Add comment");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		txTitle.setText("Comment on \"" + garden.getName() + "\"");
	}
	
	void addSelected() {
		EditGardenActivity_.intent(this).start();
	}
	
	@Background
	void saveComment(){
		saveCommentResult();
	}
	
	@UiThread
	void saveCommentResult(){
		dialog.cancel();
		finish();
	}

}
