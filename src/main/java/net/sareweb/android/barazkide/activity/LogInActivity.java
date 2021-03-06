package net.sareweb.android.barazkide.activity;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.cache.BarazkideCache;
import net.sareweb.android.barazkide.cache.UserCache;
import net.sareweb.android.barazkide.rest.BarazkideConnectionData;
import net.sareweb.android.barazkide.util.BarazkidePrefs_;
import net.sareweb.android.barazkide.util.ConnectionUtils;
import net.sareweb.android.barazkide.util.PrefUtils;
import net.sareweb.android.barazkide.util.UserUtils;
import net.sareweb.lifedroid.model.User;
import net.sareweb.lifedroid.rest.UserRESTClient;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.log_in)
public class LogInActivity extends Activity implements OnClickListener{
	
	private static String TAG = "LogInActivity";
	@ViewById TextView txEmailAddress;
	@ViewById EditText txPass;
	@Pref BarazkidePrefs_ prefs;
	UserRESTClient userRESTClient;
	private ProgressDialog dialog;
	private Dialog registerDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(PrefUtils.isUserLogged(prefs)){
			BarazkideCache.init(prefs);
			UserCache.init(prefs);
			finish();
			GardensActivity_.intent(this).start();
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		AccountManager am = AccountManager.get(this);
		Account[] accounts = am.getAccountsByType("com.google");

		if(accounts!=null && accounts.length > 0){
			Account account = (Account)accounts[0];
			txEmailAddress.setText(account.name);
		}
		else{
			Toast.makeText(this, "No google email address bound to device!", Toast.LENGTH_LONG).show();
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
		AccountManager am = AccountManager.get(this);
		Account[] accounts = am.getAccountsByType("com.google");

		if(accounts!=null && accounts.length > 0){
			Account account = (Account)accounts[0];
			registerDialog = new Dialog(this);
			registerDialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
			registerDialog.setTitle("Create account");
			registerDialog.setContentView(R.layout.register_dialog);
			TextView txEmailAddress = (TextView)registerDialog.findViewById(R.id.txEmailAddress);
			txEmailAddress.setText(account.name);
			registerDialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_launcher);
			registerDialog.setCanceledOnTouchOutside(true);
			Button createAccountButton = (Button)registerDialog.findViewById(R.id.btnCreateAccount);
			createAccountButton.setOnClickListener(this);
			registerDialog.show();
		}
		else{
			Toast.makeText(this, "No google email address bound to device!", Toast.LENGTH_LONG).show();
		}
		
	}
	
	@Background
	void validateUser(){
		if(txPass.getText().toString().equals("")){
			validateUserResult(null);
		}
		else{
			userRESTClient = new UserRESTClient(new BarazkideConnectionData(prefs));
			User user = userRESTClient.getUserByEmailAddress(txEmailAddress.getText().toString());
			validateUserResult(user);
		}
	}
	
	@UiThread
	void validateUserResult(User user){
		if(UserUtils.isEmptyUser(user)){
			prefs.user().put("");
			prefs.pass().put("");
			Toast.makeText(this, "Incorrect user or password.", Toast.LENGTH_SHORT).show();
		}
		else{
			loginUser(user);
			BarazkideCache.init(prefs);
			UserCache.init(prefs);
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


	@Override
	public void onClick(View v) {
		if(ConnectionUtils.isOnline(this)){
			EditText txName = (EditText)registerDialog.findViewById(R.id.txName);
			EditText txSurname = (EditText)registerDialog.findViewById(R.id.txSurname);
			TextView txEmailAddress = (TextView)registerDialog.findViewById(R.id.txEmailAddress);
			EditText txUserName = (EditText)registerDialog.findViewById(R.id.txUserName);
			EditText txPass1 = (EditText)registerDialog.findViewById(R.id.txPass1);
			EditText txPass2 = (EditText)registerDialog.findViewById(R.id.txPass2);
			if(validRegisterForm(txEmailAddress.getText().toString(), txName.getText().toString(), txSurname.getText().toString(),txUserName.getText().toString(), txPass1.getText().toString(), txPass2.getText().toString())){
				registerDialog.cancel();
				dialog = ProgressDialog.show(this, "", "Creating account", true);
				dialog.show();
				createAccount(txEmailAddress.getText().toString(), txName.getText().toString(), txSurname.getText().toString(), txUserName.getText().toString(), txPass1.getText().toString());
			}
		}
		else{
			Toast.makeText(this, "Sorry.No internet access available.", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	private boolean validRegisterForm(String emailAddress, String name, String surname, String userName, String pass1, String pass2) {
		if(name.equals("")){
			Toast.makeText(this, "Name required!", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(surname.equals("")){
			Toast.makeText(this, "Surname required!", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
			Toast.makeText(this, "Not valid email address!", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(userName.equals("")){
			Toast.makeText(this, "UserName required!", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(pass1.length()==0){
			Toast.makeText(this, "Empty passwords not allowed!", Toast.LENGTH_SHORT).show();
			return false;			
		}
		if(!pass1.equals(pass2.toString())){
			Toast.makeText(this, "Passwords are not equal", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	
	@Background
	void createAccount(String emailAddress, String name, String surname, String userName, String pass){
		userRESTClient = new UserRESTClient(new BarazkideConnectionData(prefs));
		User user = userRESTClient.getUserByEmailAddress(emailAddress);
		if(!UserUtils.isEmptyUser(user)){//Retrieve user
			createAccountResult(USER_EXISTS);
			return;
		}
		user = userRESTClient.getUserByScreenName(userName);
		if(!UserUtils.isEmptyUser(user)){//Retrieve user
			createAccountResult(USER_EXISTS);
			return;
		}
		user = userRESTClient.addUser(
				false, 
				pass, 
				pass,
				false,
				userName,
				emailAddress,
				0,
				"openId",
				"es_ES",
				name,
				null,
				surname,
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
		if(!UserUtils.isEmptyUser(user)){//Check user has been created
			loginUser(user);
			createAccountResult(REGISTER_OK);
		}
		else{
			createAccountResult(REGISTER_ERROR);
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
		case REGISTER_ERROR:
			Toast.makeText(this, "Error creating user!", Toast.LENGTH_LONG).show();
			dialog.cancel();
			break;
		}
	}
	
	private static final int REGISTER_OK = 0;
	private static final int USER_EXISTS = 1;
	private static final int REGISTER_ERROR = 2;

}
