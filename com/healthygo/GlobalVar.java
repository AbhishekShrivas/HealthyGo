package healthygo2.vijayanitech.com.healthygo;


import android.content.Context;
import android.content.SharedPreferences;


public class GlobalVar {
	
	private static String notificationMsg;

	public static String getNotificationMsg() {
		return notificationMsg;
	}

	public static void setNotificationMsg(String msg) {
		notificationMsg = msg;
	}

	/** Set Login User name and Its User Id Start */
	private static String UserEmail = "", UserId = "", TokenUserId = "",NavigationType="";
   //  private static UserDetail udObj;
	private static int TabIndex = 0;

	public static int getTabIndex() {
		return TabIndex ;
	}

	public static void setTabIndex(int index) {
		TabIndex  = index;
	}

	// Get Token User Id
	public static String getTokenUserID() {
		return TokenUserId;
	}

	// Set Token User ID
	public static void setTokenUserId(String tokenUserId) {
		TokenUserId = tokenUserId;
	}
	// Get User Email
	public static String getUserEmail() {
		return UserEmail;
	}
	// Set User Email
	public static void setUserEmail(String email) {
		UserEmail = email;
	}
	// Get User Name
	public static String getUserId() {
		return UserId;
	}
	// Set User Name
	public static void setUserId(String userid) {
		UserId = userid;
	}
	/** Set Login User name and Its User Id End */
	public static void initializeVariable() {
		// Assign Global Array
		}
	public static String getNavigationType() {
		return NavigationType;
	}
	// Set User Email
	public static void setNavigationType(String type) {
		NavigationType = type;
	}

	/** Sharing Dilog and Token Id Start */
	private static String tokenId,ClientId;
	private static String shareTitle, shareDesc;
	private static Boolean isShare;
	private static String pList,selectDate;

	public static String getSelectDate() {
		return selectDate;
	}

	public static void setSelectDate(String selectDate) {
		GlobalVar.selectDate = selectDate;
	}

	private static int iSerIndex;

	
	public static String getpList() {
		return pList;
	}

	public static void setpList(String list) {
		pList = list;
	}

	public static String getClientId() {
		return ClientId;
	}

	public static void setClientId(String id) {
		ClientId = id;
	}

	public static Boolean getIsShare() {
		return isShare;
	}

	public static void setIsShare(Boolean isTrue) {
		isShare = isTrue;
	}

	public static String getTokenId() {
		return tokenId;
	}

	public static void setTokenId(String token) {
		tokenId = token;
	}

	public static String getShareTitle() {
		return shareTitle;
	}

	public static void setShareTitle(String title) {
		shareTitle = title;
	}

	public static String getShareDesc() {
		return shareDesc;
	}

	public static void setShareDesc(String desc) {
		shareDesc = desc;
	}

	/** Shareing Dailog and Token Id End */

	// **********************************************************//
		// Shared Preferences Setting Like Plist for android //
		// **********************************************************//

		public static SharedPreferences getPrefs(Context context) {
			return context.getSharedPreferences("Ticketplanet", Context.MODE_PRIVATE);
		}

		public static String getMyStringPref(Context context, String strname) {
			return getPrefs(context).getString(strname, "");
		}

		public static void setMyStringPref(Context context, String strname,
				String value) {
			getPrefs(context).edit().putString(strname, value).commit();
		}

		public static int getMyIntPref(Context context, String id) {
			return getPrefs(context).getInt(id, 0);
		}

		public static void setMyIntPref(Context context, String id, int value) {
			getPrefs(context).edit().putInt(id, value).commit();
		}

		public static float getMyFloatPref(Context context, String id) {
			return getPrefs(context).getFloat(id, 0);
		}

		public static void setMyFloatPref(Context context, String id, float value) {
			getPrefs(context).edit().putFloat(id, value).commit();
		}

		public static boolean getMyBooleanPref(Context context, String strname) {
			return getPrefs(context).getBoolean(strname, false);
		}

		public static void setMyBooleanPref(Context context, String strname,
				boolean value) {
			getPrefs(context).edit().putBoolean(strname, value).commit();
		}
	
	
	
	
	public static void setSerIndex(int index) {
		iSerIndex = index;
	}

	public static int getSerIndex() {
		return iSerIndex;
	}

	// Video gallery image
	
	public static boolean paid;

	public static boolean isPaid() {
		return paid;
	}

	public static void setPaid(boolean paid) {
		GlobalVar.paid = paid;
	}
	public static boolean checkLoginsecondtime;

	private static String Address,Latitude,Longitude;
	public static void setAddress(String address) {
		Address = address;
	}

	public static String getAddress() {
		return Address;
	}

	public static void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public static String getLatitude() {
		return Latitude;
	}

	public static void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public static String getLongitude() {
		return Longitude;
	}

	private static String PickupAddress,PickupLatitude,PickupLongitude;

	public static void setPickupAddress(String address) {
		Address = address;
	}

	public static String getPickupAddress() {
		return Address;
	}

	public static void setPickupLatitude(String latitude) {
		Latitude = latitude;
	}

	public static String getPickupLatitude() {
		return Latitude;
	}

	public static void setPickupLongitude(String longitude) {
		Longitude = longitude;
	}

	public static String getPickupLongitude() {
		return Longitude;
	}



	private static String DropAddress,DropLatitude,DropLongitude;
	private static String strDriverLog="",strDriverLat="";

	public static void setDropAddress(String address) {
		DropAddress = address;
	}

	public static String getDropAddress() {
		return DropAddress;
	}

	public static void setDropLatitude(String latitude) {
		DropLatitude = latitude;
	}

	public static String getDropLatitude() {
		return DropLatitude;
	}

	public static void setDropLongitude(String longitude) {
		DropLongitude = longitude;
	}

	public static String getDropLongitude() {
		return DropLongitude;
	}


	public static void setDriverLog(String s) {
		strDriverLog= s;
	}

	public static String getDriverLog() {
		return strDriverLog;
	}
	public static void setDriverLat(String s) {
		strDriverLat= s;
	}

	public static String getDriverLat() {
		return strDriverLat;
	}
		
}
