package net.sareweb.android.barazkide.adapter;

import java.util.List;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.image.ImageLoader;
import net.sareweb.android.barazkide.model.Event;
import net.sareweb.android.barazkide.util.ImageUtils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {

	private Context context;
	private List<Event> imageEvents;
	private static String TAG = "ImageAdapter";
	private ImageLoader imgLoader;
	
	public ImageAdapter(Context context, List<Event> imageEvents){
		this.context = context;
		this.imageEvents=imageEvents;
		imgLoader=new ImageLoader(context);
	}
	
	@Override
	public int getCount() {
		return imageEvents.size();
	}

	@Override
	public Object getItem(int position) {
		return imageEvents.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Event event = (Event) getItem(position);
		Log.d(TAG, "Getting image event " + event.getEventId());
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.image_item, null);
		}
		TextView txEvent = (TextView) convertView.findViewById(R.id.txEvent);
		String text = event.getEventText();
		if(text==null)text="";
		if(text.length()>20)text=event.getEventText().substring(0,20);
		txEvent.setText(text);
		ImageView imgEvent = (ImageView)convertView.findViewById(R.id.imgEvent);
		imgLoader.displayImage(ImageUtils.getEventImageUrl(event), imgEvent);
		return convertView;
	}

}
