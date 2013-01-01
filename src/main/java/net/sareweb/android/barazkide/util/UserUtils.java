package net.sareweb.android.barazkide.util;

import net.sareweb.lifedroid.model.User;

public class UserUtils {
	
	public static boolean isEmptyUser(User user){
		return (user==null || user.getScreenName()==null || user.getScreenName().equals(""));
	}

}
