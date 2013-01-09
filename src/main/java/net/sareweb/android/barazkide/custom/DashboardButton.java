package net.sareweb.android.barazkide.custom;

import net.sareweb.android.barazkide.R;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.EViewGroup;
import com.googlecode.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.dashboard_button)
public class DashboardButton extends RelativeLayout {

	private String TAG ="DasboardButton";
	@ViewById
	ImageView imgButton;
	@ViewById
	TextView txButtonText;
	int imgResId;
	String text="";
	
	public DashboardButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		imgResId  = attrs.getAttributeIntValue("net.sareweb.android.barazkide", "imgResId", R.drawable.logo);
		text = attrs.getAttributeValue("net.sareweb.android.barazkide", "imgResId");
	}
	

	@Override
	protected void onDraw(Canvas canvas) {
		Log.d(TAG, "aaaaaaa " + text);
		imgButton.setImageResource(imgResId);
		txButtonText.setText(text);
		super.onDraw(canvas);
	}
	
}
