package net.sareweb.android.barazkide.fragment;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sareweb.android.barazkide.R;
import net.sareweb.android.barazkide.activity.AddCommentActivity_;
import net.sareweb.android.barazkide.activity.LocationSelectorActivity_;
import net.sareweb.android.barazkide.adapter.MemberAdapter;
import net.sareweb.android.barazkide.cache.BarazkideCache;
import net.sareweb.android.barazkide.image.ImageLoader;
import net.sareweb.android.barazkide.model.Garden;
import net.sareweb.android.barazkide.rest.BarazkideConnectionData;
import net.sareweb.android.barazkide.rest.EventRESTClient;
import net.sareweb.android.barazkide.rest.FollowingRESTClient;
import net.sareweb.android.barazkide.rest.GardenRESTClient;
import net.sareweb.android.barazkide.rest.MembershipRESTClient;
import net.sareweb.android.barazkide.util.BarazkidePrefs_;
import net.sareweb.android.barazkide.util.Constants;
import net.sareweb.android.barazkide.util.ImageUtils;
import net.sareweb.lifedroid.model.DLFileEntry;
import net.sareweb.lifedroid.model.User;
import net.sareweb.lifedroid.rest.DLFileEntryRESTClient;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.FragmentArg;
import com.googlecode.androidannotations.annotations.FragmentById;
import com.googlecode.androidannotations.annotations.OptionsItem;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EFragment(R.layout.garden_detail)
@OptionsMenu(R.menu.garden_menu)
public class GardenDetailFragment extends SherlockFragment implements  OnClickListener{
	
	private static String TAG = "GardenDetailFragment";
	@Pref BarazkidePrefs_ prefs;
	MembershipRESTClient membershipRESTClient;
	FollowingRESTClient followingRESTClient;
	DLFileEntryRESTClient dlFileEntryRESTClient;
	GardenRESTClient gardenRESTClient;
	EventRESTClient eventRESTClient;
	@ViewById
	GridView memberGrid;
	@ViewById
	TextView txComment;
	@ViewById
	ImageView imgGarden;
	@ViewById
	ImageView imgMap;
	@FragmentArg
	Garden garden;
	@FragmentById
	SupportMapFragment mapFragment;
	Dialog dialog;
	ImageLoader imgLoader;
	String imageMessage="";
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		membershipRESTClient = new MembershipRESTClient(new BarazkideConnectionData(prefs));
		followingRESTClient = new FollowingRESTClient(new BarazkideConnectionData(prefs));
		dlFileEntryRESTClient = new DLFileEntryRESTClient(new BarazkideConnectionData(prefs));
		gardenRESTClient = new GardenRESTClient(new BarazkideConnectionData(prefs));
		eventRESTClient = new EventRESTClient(new BarazkideConnectionData(prefs));

		imgLoader = new ImageLoader(getActivity());
		
	}

	@Override
	public void onResume() {
		super.onResume();
		if(garden!=null)setGardenContent(garden);
	}

	public void setGardenContent(Garden garden){
		Log.d(TAG, "setGardenContent");
		this.garden=garden;
		txComment = (TextView)(getSherlockActivity().findViewById(R.id.txComment));
		txComment.setText(garden.getComment());
		showEventsFragment();
		getMembers();
		imgLoader.displayImage(ImageUtils.getGardenImageUrl(garden), imgGarden);
		showHideMap(garden);
	}
	
	@OptionsItem(R.id.add_image)
	void addImage(){
		showAddImageDialog(true);
	}
	
	@Click({R.id.mapCover})
	void clickMap(){
		openLocationSelector();
	}
	
	@Click(R.id.imgGarden)
	void clickImage(){
		 showAddImageDialog(false);
	}
	
	private void showAddImageDialog(boolean withMessage){
		dialog = new Dialog(getActivity());
		dialog.setTitle("Gallery or Camera?");
		dialog.setContentView(R.layout.img_camera_dialog);
		dialog.setCanceledOnTouchOutside(true);
		if(withMessage){
			TextView txImageMessage =(TextView)dialog.findViewById(R.id.txImageMessage);
			txImageMessage.setVisibility(View.VISIBLE);
		}
		ImageView imgGallery = (ImageView)dialog.findViewById(R.id.imgGallery);
		imgGallery.setOnClickListener(this);
		ImageView imgCamera = (ImageView)dialog.findViewById(R.id.imgCamera);
		imgCamera.setOnClickListener(this);
		dialog.show();
	}
	
	@OptionsItem(R.id.add_comment)
	void addSelected() {
		//TODO: Should this be a dialog instead of an activity?
		AddCommentActivity_.intent(getActivity()).garden(garden).start();
	}
	
	@Background
	public void getMembers(){
		getMembersResult(membershipRESTClient.findMemberUsers(garden.getGardenId(), Constants.MEMBERSHIP_STATUS_MEMBER));
	}
	
	@UiThread
	public void getMembersResult(List<User> members){
		if(members!=null){
			Log.d(TAG, "members: " + members.size());
			memberGrid.setAdapter(new MemberAdapter(getActivity(), members));
		}
	}

	public void showEventsFragment(){
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		EventsFragment eventsFragment = (EventsFragment)fragmentManager.findFragmentById(R.id.eventsFragment);
		if(eventsFragment!=null){
			Log.d(TAG, "Loading garden events");
			eventsFragment.setGardenEventsContent(garden.getGardenId());
			fragmentTransaction.commitAllowingStateLoss();
		}
	}

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

	@Override
	public void onClick(View v) {
		Log.d(TAG, "Click");
		Intent intent;
		TextView txImageMessage =(TextView)dialog.findViewById(R.id.txImageMessage);
		imageMessage=txImageMessage.getText().toString();
		int reqCode; 
		switch (v.getId()) {
		case R.id.imgGallery:
			if(txImageMessage.getVisibility()==View.VISIBLE){
				reqCode=GET_IMG_FROM_GALLERY_ACTIVITY_REQUEST_CODE_FOR_COMMENT;
			}
			else{
				reqCode=GET_IMG_FROM_GALLERY_ACTIVITY_REQUEST_CODE_FOR_GARDEN;
			}
			intent = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(intent,
					reqCode);
			dialog.cancel();
			break;

		case R.id.imgCamera:
			if(txImageMessage.getVisibility()==View.VISIBLE){
				reqCode=CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_FOR_COMMENT;
			}
			else{
				reqCode=CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_FOR_GARDEN;
			}
			intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			Uri fileUri = Uri.fromFile(new File(""));
			intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
			startActivityForResult(intent, reqCode);
			dialog.cancel();
			break;
		
		case R.id.mapFragment:
			Log.d(TAG, "Click on map");
			openLocationSelector();
			break;
		}
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d(TAG,"onActivityResult");
		switch (requestCode) {
		case GET_IMG_FROM_GALLERY_ACTIVITY_REQUEST_CODE_FOR_GARDEN:
			if (resultCode == getActivity().RESULT_OK) {
				Uri targetUri = data.getData();

	        	File dest = ImageUtils.getOutputMediaFile(String.valueOf(garden.getGardenId()));
	        	
				try {
					ImageUtils.copyInputStreamToFile(getActivity().getContentResolver().openInputStream(targetUri), dest);
					ImageUtils.resizeFile(dest);
					DLFileEntry dlFile = ImageUtils.composeDLFileEntry(garden, dest);
					updateGardenImage(dlFile, dest);
				} catch (IOException e) {
					Log.e(TAG, "Error gettig/copying or uploading file",e);
				}
			}
			break;
		case GET_IMG_FROM_GALLERY_ACTIVITY_REQUEST_CODE_FOR_COMMENT:
			if (resultCode == getActivity().RESULT_OK) {
				Uri targetUri = data.getData();

	        	File dest = ImageUtils.getOutputMediaFile(String.valueOf(garden.getGardenId()));
	        	
				try {
					ImageUtils.copyInputStreamToFile(getActivity().getContentResolver().openInputStream(targetUri), dest);
					ImageUtils.resizeFile(dest);
					DLFileEntry dlFile = ImageUtils.composeDLFileEntry(garden, dest);
					addImageWithComment(dlFile, dest);
				} catch (IOException e) {
					Log.e(TAG, "Error gettig/copying or uploading file",e);
				}
			}
			break;
		case REQUEST_CODE_FOR_LOCATION:
			garden.setLat(data.getDoubleExtra("lat", 0));
			garden.setLng(data.getDoubleExtra("lng", 0));
			updateGardenLocation();
			setGardenContent(garden);
			break;
		default:
			break;
		}
	}
	
	@Background
	void updateGardenImage(DLFileEntry dlFileEntry, File file){
		DLFileEntry dlFile = dlFileEntryRESTClient.addFileEntry(dlFileEntry, file);
		garden.setImageTitle(dlFileEntry.getTitle());
		garden  = gardenRESTClient.updateGardenImage(garden.getGardenId(), garden.getImageTitle());
		updateGardenImageResult(dlFile);
	}
	
	@UiThread
	void updateGardenImageResult(DLFileEntry dlFileEntry){
		imgLoader.displayImage(ImageUtils.getGardenImageUrl(garden), imgGarden);
	}
	
	@Background
	void updateGardenLocation(){
		gardenRESTClient.updateGardenLocation(garden.getGardenId(), garden.getLat(), garden.getLng());
	}
	
	@Background
	void addImageWithComment(DLFileEntry dlFileEntry, File file){
		DLFileEntry dlFile = dlFileEntryRESTClient.addFileEntry(dlFileEntry, file);
		eventRESTClient.addEvent(garden.getGardenId(), prefs.userId().get(), 0, Constants.EVENT_TYPE_COMMENT, imageMessage, garden.getGardenFolderId(),dlFileEntry.getTitle()); 
	}
	
	private void showHideMap(Garden garden){
		if(garden.getLat()==0L && garden.getLng()==0L){
			mapFragment.getFragmentManager().beginTransaction().hide(mapFragment).commit();
			imgMap.setVisibility(View.VISIBLE);
		}
		else{
			mapFragment.getFragmentManager().beginTransaction().show(mapFragment).commit();
			
			GoogleMap map = mapFragment.getMap();
			map.clear();
			LatLng latLng = new LatLng(garden.getLat(), garden.getLng());
			CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(latLng, 8);
			map.moveCamera(cu);
			MarkerOptions mo =new MarkerOptions();
			mo.position(latLng);
			map.addMarker(mo);
			imgMap.setVisibility(View.GONE);
		}
	}
	
	private void openLocationSelector(){
		startActivityForResult(LocationSelectorActivity_.intent(getSherlockActivity()).garden(garden).get(), REQUEST_CODE_FOR_LOCATION);
	}
	
	final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_FOR_GARDEN = 100;
	final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_FOR_COMMENT = 101;
	
	final int GET_IMG_FROM_GALLERY_ACTIVITY_REQUEST_CODE_FOR_GARDEN = 200;
	final int GET_IMG_FROM_GALLERY_ACTIVITY_REQUEST_CODE_FOR_COMMENT = 201;
	
	final int REQUEST_CODE_FOR_LOCATION = 300;

}
