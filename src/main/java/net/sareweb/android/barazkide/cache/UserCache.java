package net.sareweb.android.barazkide.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sareweb.android.barazkide.model.Garden;
import net.sareweb.android.barazkide.rest.BarazkideConnectionData;
import net.sareweb.android.barazkide.rest.GardenRESTClient;
import net.sareweb.android.barazkide.util.BarazkidePrefs_;
import net.sareweb.lifedroid.model.User;
import net.sareweb.lifedroid.rest.UserRESTClient;

public class UserCache {

	private static BarazkidePrefs_ prefs;
	
	private static UserRESTClient userRESTClient;

	private static Map<Long, User> users = new HashMap<Long, User>();
	
	public static void init(BarazkidePrefs_ preferences){
		prefs = preferences;
		userRESTClient = new UserRESTClient(new BarazkideConnectionData(prefs));
	}

	
	public static User getUser(long userId){
		if(users.containsKey(userId)){
			return users.get(userId);
		}
		else{
			User user = userRESTClient.getUserById(userId);
			if(user!=null){
				users.put(userId, user);
			}
			return user;
		}
	}

}
