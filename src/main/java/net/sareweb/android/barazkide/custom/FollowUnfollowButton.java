package net.sareweb.android.barazkide.custom;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.cache.BarazkideCache;
import net.sareweb.android.barazkide.model.Following;
import net.sareweb.android.barazkide.rest.BarazkideConnectionData;
import net.sareweb.android.barazkide.rest.FollowingRESTClient;
import net.sareweb.android.barazkide.util.BarazkidePrefs_;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EViewGroup;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EViewGroup(R.layout.follow_unfollow_button)
public class FollowUnfollowButton extends RelativeLayout implements OnClickListener{

	private String TAG = "FollowUnfollowButton";
	private Context context;
	@ViewById ImageView imgStar;
	private long gardenId =0;
	private FollowingRESTClient followingRESTClient;
	@Pref BarazkidePrefs_ prefs;
	private boolean followingFlag = false;
	
	public FollowUnfollowButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		imgStar.setOnClickListener(this);
		followingRESTClient = new FollowingRESTClient(new BarazkideConnectionData(prefs));
		
	}

	@Override
	public void onClick(View v) {
		if(followingFlag){
			unfollowGarden();
		}else{
			followGarden();	
		}
	}
	
	public void setGardenId(long gardenId){
		this.gardenId=gardenId;
		if(BarazkideCache.iAmFollowingGarden(gardenId)){
			imgStar.setImageResource(R.drawable.star_true);
			followingFlag=true;
		}
	}
	
	@Background
	public void followGarden(){
		Following following = followingRESTClient.addFollowing(prefs.userId().get(), gardenId);
		followGardenResult(following);
	}
	
	@UiThread
	public void followGardenResult(Following following){
		if(following!=null && following.getFollowingId()!=0){
			imgStar.setImageResource(R.drawable.star_true);
			followingFlag=true;
			BarazkideCache.followGarden(following.getGardenId());
		}
	}
	
	@Background
	public void unfollowGarden(){
		if(followingRESTClient.removeFollowing(prefs.userId().get(), gardenId)){
			unfollowGardenResult();
		}
	}
	
	@UiThread
	public void unfollowGardenResult(){
		imgStar.setImageResource(R.drawable.star_false);
		followingFlag=false;
		BarazkideCache.unfollowGarden(gardenId);
	}
}
