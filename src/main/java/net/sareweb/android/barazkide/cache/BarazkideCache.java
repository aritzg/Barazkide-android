package net.sareweb.android.barazkide.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sareweb.android.barazkide.model.Garden;
import net.sareweb.android.barazkide.rest.BarazkideConnectionData;
import net.sareweb.android.barazkide.rest.GardenRESTClient;
import net.sareweb.android.barazkide.util.BarazkidePrefs_;

public class BarazkideCache {

	private static BarazkidePrefs_ prefs;
	
	private static GardenRESTClient gardenRESTClient;

	private static Map<Long, Garden> gardens_member;
	private static Map<Long, Garden> gardens_followed;
	
	public static void init(BarazkidePrefs_ preferences){
		prefs = preferences;
		gardenRESTClient = new GardenRESTClient(new BarazkideConnectionData(prefs));
		loadGardenMenber();
		loadGardenFollwed();
	}

	private static void loadGardenMenber() {
		gardens_member = new HashMap<Long, Garden>();
		List<Garden>gardens = gardenRESTClient.getUserGardensFromDate(prefs.userId().get(), System.currentTimeMillis(), true, 100);
		if(gardens!=null){
			for(Garden garden : gardens){
				gardens_member.put(garden.getGardenId(), garden);
			}
		}
	}
	
	private static void loadGardenFollwed() {
		gardens_followed = new HashMap<Long, Garden>();
		List<Garden>gardens = gardenRESTClient.getFollowedGardensOlderThanDate(prefs.userId().get(), System.currentTimeMillis(), 100);
		if(gardens!=null){
			for(Garden garden : gardens){
				gardens_followed.put(garden.getGardenId(), garden);
			}
		}
	}
	
	public static void addMember(Garden garden){
		if(!gardens_member.containsKey(garden.getGardenId())){
			gardens_member.put(garden.getGardenId(), garden);
		}
	}
	
	public static void followGarden(Garden garden){
		if(!gardens_followed.containsKey(garden.getGardenId())){
			gardens_followed.put(garden.getGardenId(), garden);
		}
	}
	
	public static void removeMember(Garden garden){
		if(gardens_member.containsKey(garden.getGardenId())){
			gardens_member.remove(garden.getGardenId());
		}
	}

	public static void unfollowGarden(Garden garden){
		if(gardens_followed.containsKey(garden.getGardenId())){
			gardens_followed.remove(garden.getGardenId());
		}
	}
	
	public static boolean iAmFollowingGarden(long gardenId){
		return gardens_followed.containsKey(gardenId);
	}
	
	public static boolean iAmMemberOfGarden(long gardenId){
		return gardens_member.containsKey(gardenId);
	}

}
