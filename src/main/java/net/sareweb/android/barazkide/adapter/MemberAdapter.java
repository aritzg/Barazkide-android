package net.sareweb.android.barazkide.adapter;

import java.util.List;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.image.ImageLoader;
import net.sareweb.android.barazkide.model.Garden;
import net.sareweb.android.barazkide.util.Constants;
import net.sareweb.lifedroid.model.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MemberAdapter extends BaseAdapter{

	private Context context;
	private List<User> members;
	private ImageLoader imgLoader;
	private static String TAG = "MemberAdapter";
	
	public MemberAdapter(Context context, List<User> members){
		this.context = context;
		this.members = members; 
		imgLoader = new ImageLoader(context);
	}
	
	@Override
	public int getCount() {
		if(members!=null)return members.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if(members!=null)return members.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.member_portrait_small, null);
		}
		
		User member = members.get(position);
		
		TextView txMemberName = (TextView) convertView.findViewById(R.id.txMemberName);
		String name=member.getScreenName();
		txMemberName.setText(name);
		
		ImageView imgMember = (ImageView) convertView.findViewById(R.id.imgMember);
		imgLoader.displayImage("http://" + Constants.SERVICE_URL + ":" + Constants.SERVICE_PORT + "/image/user_male_portrait?img_id="+member.getPortraitId(), imgMember);
		
		convertView.setTag(members.get(position));
		
		return convertView;
	}

}
