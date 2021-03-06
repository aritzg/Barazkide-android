package net.sareweb.android.barazkide.custom;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.cache.UserCache;
import net.sareweb.android.barazkide.image.ImageLoader;
import net.sareweb.android.barazkide.util.Constants;
import net.sareweb.lifedroid.model.User;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EViewGroup;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

@EViewGroup(R.layout.user_portrait_small)
public class UserPortrait extends RelativeLayout {

	private String TAG ="UserPortrait";
	@ViewById
	TextView txMemberName;
	@ViewById
	ImageView imgMember;
	private ImageLoader imgLoader;
	
	public UserPortrait(Context context, AttributeSet attrs) {
		super(context, attrs);
		imgLoader = new ImageLoader(context);
	}
	
	public void updateForUser(long userId){
		getUser(userId);
	}

	@Background
	void getUser(long userId){
		User user = UserCache.getUser(userId);
		getUserResult(user);
	}
	
	@UiThread
	void getUserResult(User user){
		if(user!=null){
			txMemberName.setText(user.getScreenName());
			imgLoader.displayImage("http://" + Constants.SERVICE_URL + ":" + Constants.SERVICE_PORT + "/image/user_male_portrait?img_id="+user.getPortraitId(), imgMember);
		}
		else{
			imgLoader.displayImage(null, imgMember);
		}
	}
}
