package net.sareweb.android.barazkide.fragment;

import java.util.List;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.activity.AddCommentActivity_;
import net.sareweb.android.barazkide.adapter.MemberAdapter;
import net.sareweb.android.barazkide.cache.BarazkideCache;
import net.sareweb.android.barazkide.image.ImageLoader;
import net.sareweb.android.barazkide.model.Garden;
import net.sareweb.android.barazkide.rest.BarazkideConnectionData;
import net.sareweb.android.barazkide.rest.FollowingRESTClient;
import net.sareweb.android.barazkide.rest.MembershipRESTClient;
import net.sareweb.android.barazkide.util.BarazkidePrefs_;
import net.sareweb.lifedroid.model.User;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.OptionsItem;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EFragment(R.layout.garden_detail)
@OptionsMenu(R.menu.garden_menu)
public class GardenDetailFragment extends SherlockFragment implements OnCheckedChangeListener{
	
	private static String TAG = "GardenDetailFragment";
	@Pref BarazkidePrefs_ prefs;
	MembershipRESTClient membershipRESTClient;
	FollowingRESTClient followingRESTClient;
	@ViewById
	GridView memberGrid;
	@ViewById
	TextView txComment;
	@ViewById
	ToggleButton tgFollow;
	@ViewById
	ImageView imgGarden;
	Garden garden;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		membershipRESTClient = new MembershipRESTClient(new BarazkideConnectionData(prefs));
		followingRESTClient = new FollowingRESTClient(new BarazkideConnectionData(prefs));
	}

	public void setGardenContent(Garden garden){
		this.garden=garden;
		txComment = (TextView)(getActivity().findViewById(R.id.txComment));
		txComment.setText(garden.getComment());
		tgFollow.setChecked(BarazkideCache.iAmFollowingGarden(garden.getGardenId()));
		tgFollow.setOnCheckedChangeListener(this);
		getMembers();
		ImageLoader imgLoader = new ImageLoader(getActivity());
		imgLoader.displayImage("http://192.168.0.14:9080/documents/10180/0/huerta1", imgGarden);
	}
	
	@OptionsItem(R.id.add_comment)
	void addSelected() {
		AddCommentActivity_.intent(getActivity()).garden(garden).start();
	}
	
	@Background
	public void getMembers(){
		getMembersResult(membershipRESTClient.findMemberUsers(garden.getGardenId()));
	}
	
	@UiThread
	public void getMembersResult(List<User> members){
		if(members!=null){
			Log.d(TAG, "members: " + members.size());
			memberGrid.setAdapter(new MemberAdapter(getActivity(), members));
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if(isChecked){
			followingRESTClient.addFollowing(prefs.userId().get(), garden.getGardenId());
			BarazkideCache.followGarden(garden);
		}
		else{
			followingRESTClient.removeFollowing(prefs.userId().get(), garden.getGardenId());
			BarazkideCache.unfollowGarden(garden);
		}
	}
	
	

}
