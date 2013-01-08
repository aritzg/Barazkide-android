package net.sareweb.android.barazkide.activity;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.model.Garden;
import net.sareweb.android.barazkide.rest.BarazkideConnectionData;
import net.sareweb.android.barazkide.rest.GardenRESTClient;
import net.sareweb.android.barazkide.util.BarazkidePrefs_;
import net.sareweb.android.barazkide.util.Constants;
import net.sareweb.lifedroid.model.DLFolder;
import net.sareweb.lifedroid.rest.DLFolderRESTClient;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsItem;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.garden_edit)
@OptionsMenu(R.menu.edit_menu)
public class EditGardenActivity extends SherlockFragmentActivity{
	
	private static String TAG = "EditGardenActivity";
	@Pref BarazkidePrefs_ prefs;
	GardenRESTClient gardenRESTClient;
	DLFolderRESTClient dlFolderRESTClient;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		gardenRESTClient = new GardenRESTClient(new BarazkideConnectionData(prefs));
		dlFolderRESTClient = new DLFolderRESTClient(new BarazkideConnectionData(prefs));
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
	
	@OptionsItem(R.id.menu_save)
	void saveSelected() {
		dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Saving garden");
        dialog.show();
		saveGarden();
	}
	
	@OptionsItem
	void homeSelected() {
		createConfirmDialog();
	}
	
	@Background
	void saveGarden(){
		Garden garden = gardenRESTClient.addGarden(txName.getText().toString(), txComment.getText().toString(), 0.0, 0.0, 0);
		DLFolder folder = new DLFolder();
		folder.setDescription(garden.getName());
		folder.setGroupId(Constants.GROUP);
		folder.setName(String.valueOf(garden.getGardenId()));
		folder.setParentFolderId(0l);
		folder.setRepositoryId(Constants.GROUP);
		dlFolderRESTClient.addFolder(folder);
		saveGardenResult();
	}
	
	@UiThread
	void saveGardenResult(){
		dialog.cancel();
		finish();
	}
	
	private void createConfirmDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Quit without saving?");
		builder.setMessage("Are you sure you want to discard changes?");

		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	confirmDialog.dismiss();
            }
        });

		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	confirmDialog.dismiss();
            	finish();
            }
        });

		confirmDialog = builder.create();
		confirmDialog.show();
    }
	
	
	@ViewById
	EditText txName;
	@ViewById
	EditText txComment;
	ProgressDialog dialog;
	AlertDialog confirmDialog;
}
