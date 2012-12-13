package net.sareweb.android.barazkide.fragment;

import java.util.List;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.adapter.MemberAdapter;
import net.sareweb.android.barazkide.model.Garden;
import net.sareweb.android.barazkide.rest.BarazkideConnectionData;
import net.sareweb.android.barazkide.rest.MembershipRESTClient;
import net.sareweb.android.barazkide.util.BarazkidePrefs_;
import net.sareweb.lifedroid.model.User;

import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

@EFragment(R.layout.garden_detail)
public class GardenDetailFragment extends SherlockFragment {
	
	private static String TAG = "GardenDetailFragment";
	@Pref BarazkidePrefs_ prefs;
	MembershipRESTClient membershipRESTClient;
	@ViewById
	GridView memberGrid;
	Garden garden;
	
	
	public void setGardenContent(Garden garden){
		this.garden=garden;
		TextView txComment = (TextView)(getActivity().findViewById(R.id.txComment));
		txComment.setText(garden.getComment());
		getMembers();
	}
	
	@Background
	public void getMembers(){
		membershipRESTClient = new MembershipRESTClient(new BarazkideConnectionData(prefs));
		getMembersResult(membershipRESTClient.findMemberUsers(garden.getGardenId()));
	}
	
	@UiThread
	public void getMembersResult(List<User> members){
		if(members!=null){
			Log.d(TAG, "members: " + members.size());
			memberGrid.setAdapter(new MemberAdapter(getActivity(), members));
		}
	}

}
