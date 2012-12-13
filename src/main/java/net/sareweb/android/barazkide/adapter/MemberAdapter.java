package net.sareweb.android.barazkide.adapter;

import java.util.List;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.model.Garden;
import net.sareweb.lifedroid.model.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MemberAdapter extends BaseAdapter{

	private Context context;
	private List<User> members;
	private static String TAG = "MemberAdapter";
	
	public MemberAdapter(Context context, List<User> members){
		this.context = context;
		this.members = members; 
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
		TextView txMemberName = (TextView) convertView.findViewById(R.id.txMemberName);
		
		String name=members.get(position).getScreenName();
		txMemberName.setText(name);
		convertView.setTag(members.get(position));
		return convertView;
	}

}
