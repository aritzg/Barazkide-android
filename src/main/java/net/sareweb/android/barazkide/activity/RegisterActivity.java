package net.sareweb.android.barazkide.activity;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.rest.BarazkideConnectionData;
import net.sareweb.android.barazkide.util.BarazkidePrefs_;
import net.sareweb.android.barazkide.util.ConnectionUtils;
import net.sareweb.lifedroid.model.User;
import net.sareweb.lifedroid.rest.UserRESTClient;
import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.TextView;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.register)
public class RegisterActivity extends Activity {
	
	@ViewById TextView txName;
	@ViewById TextView txSurname;
	@ViewById TextView txUserName;
	@ViewById TextView txEmailAddress;
	@ViewById TextView txPass1;
	@ViewById TextView txPass2;
	@Pref BarazkidePrefs_ prefs;
	UserRESTClient userRESTClient;
	private ProgressDialog dialog;
	
	@Click(R.id.btnCreateAccount)
	void clickOnCreateAccount(){
		if(ConnectionUtils.isOnline(this)){
			if(validForm()){
				dialog = ProgressDialog.show(this, "", "Creating account", true);
				dialog.show();
				createAccount();
			}
		}
		else{
			Toast.makeText(this, "Sorry.No internet access available.", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Background
	void createAccount(){
		userRESTClient = new UserRESTClient(new BarazkideConnectionData(prefs));
		User user = userRESTClient.getUserByEmailAddress(txEmailAddress.getText().toString());
		if(user.getEmailAddress()!=null){//Retrieve user
			createAccountResult(USER_EXISTS);
			return;
		}
		user = userRESTClient.addUser(
				false, 
				txPass1.getText().toString(), 
				txPass2.getText().toString(),
				false,
				txUserName.getText().toString(),
				txEmailAddress.getText().toString(),
				0,
				"openId",
				"es_ES",
				txName.getText().toString(),
				null,
				txName.getText().toString(),
				1,
				1,
				true,
				1,
				1,
				2000,
				null,
				null,
				null,
				null,
				null,
				true);
		if(user!=null){
			loginUser(user);
			createAccountResult(REGISTER_OK);
		}
	}
	
	@UiThread
	void createAccountResult(int result){
		switch (result) {
		case USER_EXISTS:
			Toast.makeText(this, "User already exists!", Toast.LENGTH_LONG).show();
			dialog.cancel();
			break;
		case REGISTER_OK:
			Toast.makeText(this, "User registered", Toast.LENGTH_LONG).show();
			dialog.cancel();
			finish();
			GardensActivity_.intent(this).start();
			break;
		default:
			break;
		}
	}
	
	private boolean validForm() {
		if(txName.getText().toString().equals("")){
			Toast.makeText(this, "Name required!", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(txSurname.getText().toString().equals("")){
			Toast.makeText(this, "Surname required!", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(!android.util.Patterns.EMAIL_ADDRESS.matcher(txEmailAddress.getText().toString()).matches()){
			Toast.makeText(this, "Not valid email address!", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(txPass1.getText().toString().length()==0){
			Toast.makeText(this, "Empty passwords not allowed!", Toast.LENGTH_SHORT).show();
			return false;			
		}
		if(!txPass1.getText().toString().equals(txPass2.getText().toString())){
			Toast.makeText(this, "Passwords are not equal", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	
	private void loginUser(User user){
		prefs.userId().put(user.getUserId());
		prefs.user().put(user.getScreenName());
		prefs.pass().put(txPass1.getText().toString());
	}
	
	private static final int REGISTER_OK = 0;
	private static final int USER_EXISTS = 1;
	private static final int REGISTER_ERROR = 2;
	
}
