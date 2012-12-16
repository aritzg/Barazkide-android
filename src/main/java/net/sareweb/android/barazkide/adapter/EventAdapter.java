package net.sareweb.android.barazkide.adapter;

import java.util.List;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.model.Event;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EventAdapter extends BaseAdapter{

	private Context context;
	private List<Event> events;
	private static String TAG = "EventAdapter";
	
	public EventAdapter(Context context, List<Event> events){
		this.context = context;
		this.events = events; 
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
		TextView txEventType = (TextView) convertView.findViewById(R.id.txEventType);
		
		String type=events.get(position).getEventType();
		txEventType.setText(type);
		convertView.setTag(events.get(position));
		return convertView;
	}

}
