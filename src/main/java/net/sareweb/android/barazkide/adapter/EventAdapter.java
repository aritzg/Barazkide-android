package net.sareweb.android.barazkide.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.image.ImageLoader;
import net.sareweb.android.barazkide.model.Event;
import net.sareweb.android.barazkide.util.Constants;
import net.sareweb.android.barazkide.util.ImageUtils;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EventAdapter extends BaseAdapter{

	private Context context;
	private List<Event> events;
	private static String TAG = "EventAdapter";
	private ImageLoader imgLoader;
	
	public EventAdapter(Context context, List<Event> events){
		this.context = context;
		this.events = events; 
		imgLoader = new ImageLoader(context);
	}
	
	@Override
	public int getCount() {
		if(events!=null)return events.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if(events!=null)return events.get(position);
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
			convertView = inflater.inflate(R.layout.event_row, null);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd hh:mm");
		Event event = events.get(position);
		
		TextView txEventDate = (TextView) convertView.findViewById(R.id.txEventDate);
		Date tmpDate = new Date(event.getCreateDate());
		txEventDate.setText(sdf.format(tmpDate));
		
		if(event.getEventType().equals(Constants.EVENT_TYPE_COMMENT)){
			drawCommentEvent(convertView, event);
		}
		else if(event.getEventType().equals(Constants.EVENT_TYPE_IMAGE)){
			drawImageEvent(convertView, event);
		}
		
		convertView.setTag(events.get(position));
		return convertView;
	}

	
	public void drawCommentEvent(View convertView, Event event){
		TextView txEventText = (TextView) convertView.findViewById(R.id.txEventText);
		txEventText.setText(event.getEventText());
		if(event.getImageTitle()!=null && !event.getImageTitle().equals("")){
			ImageView imgEvent = (ImageView) convertView.findViewById(R.id.imgEvent);
			imgLoader.displayImage(ImageUtils.getEventImageUrl(event), imgEvent);
		}
	}
	
	public void drawImageEvent(View convertView, Event event){
		TextView txEventText = (TextView) convertView.findViewById(R.id.txEventText);
		txEventText.setText("XXX changed garden image");
		
		ImageView imgEvent = (ImageView) convertView.findViewById(R.id.imgEvent);
		imgLoader.displayImage(ImageUtils.getEventImageUrl(event), imgEvent);
	}
	
}
