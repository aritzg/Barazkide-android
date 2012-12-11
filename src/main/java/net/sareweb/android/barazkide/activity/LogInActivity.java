package net.sareweb.android.barazkide.activity;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.rest.BarazkideConnectionData;
import net.sareweb.android.barazkide.util.BarazkidePrefs_;
import net.sareweb.android.barazkide.util.ConnectionUtils;
import net.sareweb.android.barazkide.util.PrefUtils;
import net.sareweb.lifedroid.model.User;
import net.sareweb.lifedroid.rest.UserRESTClient;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.log_in)
public class LogInActivity extends Activity {
	
	@ViewById TextView txUserName;
	@ViewById TextView txPass;
	@Pref BarazkidePrefs_ prefs;
	UserRESTClient userRESTClient;
	private ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(PrefUtils.isUserLogged(prefs)){
			GardensActivity_.intent(this).start();
		}
	}
	
	@Click(R.id.btnSignIn)
	void clickOnSignIn(){
		if(ConnectionUtils.isOnline(this)){
			dialog = ProgressDialog.show(this, "", "Loging in...", true);
			dialog.show();
			validateUser();
		}
		else{
			Toast.makeText(this, "Sorry.No internet access available.", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Click(R.id.btnCreateAccount)
	void clickOnCreateAccount(){
		finish();
		RegisterActivity_.intent(this).start();
	}
	
	@Background
	void validateUser(){
		prefs.user().put(txUserName.getText().toString());
		prefs.pass().put(txPass.getText().toString());
		userRESTClient = new UserRESTClient(new BarazkideConnectionData(prefs));
		User user = userRESTClient.getUserScreenName(txUserName.getText().toString());
		validateUserResult(user);
	}
	
	@UiThread
	void validateUserResult(User user){
		if(user==null){
			prefs.user().put("");
			prefs.pass().put("");
			Toast.makeText(this, "Incorrect user or password.", Toast.LENGTH_SHORT).show();
		}
		else{
			loginUser(user);
			finish();
			GardensActivity_.intent(this).start();
		}
		dialog.cancel();
	}
	
	private void loginUser(User user){
		prefs.userId().put(user.getUserId());
		prefs.user().put(user.getScreenName());
		prefs.pass().put(txPass.getText().toString());
	}

}
