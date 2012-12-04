package net.sareweb.android.barazkide.adapter;

import java.util.List;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.model.Garden;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GardenAdapter extends BaseAdapter{

	private Context context;
	private List<Garden> gardens;
	private static String TAG = "GardenAdapter";
	
	public GardenAdapter(Context context, List<Garden> gardens){
		this.context = context;
		this.gardens = gardens; 
	}
	
	@Override
	public int getCount() {
		if(gardens!=null)return gardens.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if(gardens!=null)return gardens.get(position);
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
			convertView = inflater.inflate(R.layout.garden_row, null);
		}
		TextView txGardenName = (TextView) convertView.findViewById(R.id.txGardenName);
		
		
		
		
		String name=gardens.get(position).getName();
		txGardenName.setText(name);
		convertView.setTag(gardens.get(position));
		return convertView;
	}

}
