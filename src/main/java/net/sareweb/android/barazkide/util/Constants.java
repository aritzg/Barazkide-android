package net.sareweb.android.barazkide.util;

public class Constants {

	public static final int GARDEN_LIST_ALL = 0;
	public static final int GARDEN_LIST_FOLLWED = 1;
	public static final int GARDEN_LIST_MINE = 2;
	
	//public static String SERVICE_URL="192.168.0.14";
	public static String SERVICE_URL="www.sareweb.net";
	public static int SERVICE_PORT=9080;
	
	public static String DEFAULT_USER="test";
	public static String DEFAULT_PASS="test1";
	
	public static int DEFAULT_BLOCK_SIZE=20;
	
	public static final String EVENT_TYPE_IMAGE				="IMAGE";
	public static final String EVENT_TYPE_COMMENT			="COMMENT";
	public static final String EVENT_TYPE_RATE				="RATE";
	public static final String EVENT_TYPE_ADD_MEMBER		="ADD_MEMBER";
	public static final String EVENT_TYPE_REQUEST_MEMBERSHIP="REQUEST_MEMBERSHIP";
	public static final String EVENT_TYPE_FOLLOW			="FOLLOW";
	public static final String EVENT_TYPE_LOCATION			="LOCATION";
	public static final String EVENT_TYPE_ALL			="";
	
	public static final int MEMBERSHIP_STATUS_ANY = -1;
	public static final int MEMBERSHIP_STATUS_REQUESTED = 0;
	public static final int MEMBERSHIP_STATUS_MEMBER = 1;
	public static final int MEMBERSHIP_STATUS_REJECTED = 2;
	
	public static final long IMAGE_FOLDER = 114909;
	public static final long GROUP = 120849;
	
}
