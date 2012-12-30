package net.sareweb.android.barazkide.adapter;

import java.util.List;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.image.ImageLoader;
import net.sareweb.android.barazkide.model.Badge;
import net.sareweb.android.barazkide.model.Event;
import net.sareweb.android.barazkide.util.Constants;
import net.sareweb.android.barazkide.util.ImageUtils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BadgeAdapter extends BaseAdapter {

	private Context context;
	private List<Badge> badges;
	private static String TAG = "BadgeAdapter";
	private ImageLoader imgLoader;
	
	public BadgeAdapter(Context context, List<Badge> badged){
		this.context = context;
		this.badges=badged;
		imgLoader=new ImageLoader(context);
	}
	
	@Override
	public int getCount() {
		return badges.size();
	}

	@Override
	public Object getItem(int position) {
		return badges.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Badge badge = (Badge) getItem(position);
		Log.d(TAG, "Getting Badge " + badge.getBadgeId());
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.badge_item, null);
		}
		ImageView imgBadge = (ImageView)convertView.findViewById(R.id.imgBadge);
		imgLoader.displayImage("http://" + Constants.SERVICE_URL + ":" + Constants.SERVICE_PORT + "/documents/" + Constants.GROUP +  badge.getBadgeImageUrl() , imgBadge);
		return convertView;
	}

}
