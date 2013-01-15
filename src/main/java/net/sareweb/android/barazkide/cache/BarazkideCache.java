package net.sareweb.android.barazkide.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sareweb.android.barazkide.model.Garden;
import net.sareweb.android.barazkide.rest.BarazkideConnectionData;
import net.sareweb.android.barazkide.rest.GardenRESTClient;
import net.sareweb.android.barazkide.util.BarazkidePrefs_;
import net.sareweb.lifedroid.model.User;
import net.sareweb.lifedroid.rest.UserRESTClient;

public class BarazkideCache {

	private static BarazkidePrefs_ prefs;
	
	private static GardenRESTClient gardenRESTClient;
	private static UserRESTClient userRESTClient;

	private static Map<Long, Garden> gardens_member;
	private static List<Long> gardens_followed;
	private static Map<Long, User> users = new HashMap<Long, User>();
	
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
		gardens_followed = new ArrayList<Long>();
		List<Garden>gardens = gardenRESTClient.getFollowedGardensOlderThanDate(prefs.userId().get(), System.currentTimeMillis(), 100);
		if(gardens!=null){
			for(Garden garden : gardens){
				gardens_followed.add(garden.getGardenId());
			}
		}
	}
	
	public static void addMember(Garden garden){
		if(!gardens_member.containsKey(garden.getGardenId())){
			gardens_member.put(garden.getGardenId(), garden);
		}
	}
	
	public static void followGarden(Garden garden){
		if(garden!=null){
			followGarden(garden.getGardenId());
		}
	}
	
	public static void followGarden(long gardenId){
		if(!gardens_followed.contains(gardenId)){
			gardens_followed.add(gardenId);
		}
	}
	
	public static void removeMember(Garden garden){
		if(gardens_member.containsKey(garden.getGardenId())){
			gardens_member.remove(garden.getGardenId());
		}
	}

	public static void unfollowGarden(Garden garden){
		if(garden!=null){
			unfollowGarden(garden.getGardenId());
		}
	}
	
	public static void unfollowGarden(long gardenId){
		if(gardens_followed.contains(gardenId)){
			gardens_followed.remove(gardenId);
		}
	}
	
	public static boolean iAmFollowingGarden(long gardenId){
		return gardens_followed.contains(gardenId);
	}
	
	public static boolean iAmMemberOfGarden(long gardenId){
		return gardens_member.containsKey(gardenId);
	}

}
