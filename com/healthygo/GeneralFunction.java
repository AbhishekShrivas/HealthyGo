package healthygo2.vijayanitech.com.healthygo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GeneralFunction {

	static String TAG = "General Function";
	public static int Diff_Min =0;
	public static int Diff_Hours =0;
	

	static int year;
    static int month;
    static int day;     
	final static int DATE_PICKER_ID = 1111;


	public static void messageBox(Context context, String method, String message) {
		if (message != null) {

			AlertDialog.Builder messageBox = new AlertDialog.Builder(context);
			messageBox.setTitle("Error:-" + method);
			messageBox.setMessage(message);
			messageBox.setCancelable(false);
			messageBox.setNeutralButton("OK", null);
			messageBox.show();
		}
	}



	public static boolean isEmptyCheck(final String string) {
		if(TextUtils.isEmpty(string))
		{
			return true;
		}
		else
		{
			return false;
		}

	}
		
   public static boolean isCompare(final String str,final String compare) {
		if(str.equalsIgnoreCase(compare))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static boolean isEmailValid(final String mailAddress) {

        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(mailAddress);
        return matcher.matches();

    }

	/****  * @param mobileNo	 *  -Mobile No * @return True or False */
	public static boolean isValidMobile(String phone2)
	{
		boolean check=false;
		if(!Pattern.matches("[a-zA-Z]+", phone2))
		{
			if(phone2.length() < 8 || phone2.length() > 13)
			{
				check = false;
                /*txtPhone.setError("Not Valid Number");*/
			}
			else
			{
				check = true;
			}
		}
		return check;
	}

	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("#.###");
		return Double.valueOf(twoDForm.format(d));
	}
	
	/***
	 * Convert Date FROM  DD/MM/YYYY to MM/DD/YYYY* @param date * @return */
	public static String ConvertDateToMM(String date) {
		
		SimpleDateFormat newformat = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat oldformat = new SimpleDateFormat("dd/MM/yyyy");
		String reformattedStr = null;
		try {
			reformattedStr = newformat.format(oldformat.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reformattedStr;
	}
	
	/***
	 * Convert Date FROM  MM/DD/YYYY to DD/MM/YYYY @param date * @return */

	public static String ConvertDateToDD(String date) {
		
		SimpleDateFormat oldformat= new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat newformat  = new SimpleDateFormat("dd/MM/yyyy");
		String reformattedStr = null;
		try {
			reformattedStr = newformat.format(oldformat.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reformattedStr;
	}

	public static String ConvertDateToMMM(String date) {

		SimpleDateFormat oldformat= new SimpleDateFormat("MM/dd/yyyy");
		//SimpleDateFormat newformat  = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat newformat  = new SimpleDateFormat("MMM dd yyyy");
		String reformattedStr = null;
		try {
			reformattedStr = newformat.format(oldformat.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reformattedStr;
	}
	
	/**** Convert Date FROM MM/DD/YYYY to DD/MM/YYYY	 * @param date * @return */
	public static Date ConvertStringToDate(String date) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date convertedDate = new Date();
		try {
			convertedDate = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return convertedDate;
	}
	
    public static String ConvertstringToMM(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		//SimpleDateFormat newformat  = new SimpleDateFormat("dd/MM/yyyy");
		String reformattedStr = null;
		Date myDate = null;
		try {
			myDate = dateFormat.parse(date);
			reformattedStr= dateFormat.format(myDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reformattedStr;
	}
	
	/***
	 * Convert Short Date To Full Date From  dd/MM/yyyy to dd/mm/yyyy HH:mm:ss@param date * @return */

	public static Date ConvertStringFullDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/mm/yyyy HH:mm:ss");
		Date convertedDate = new Date();
		try {
			convertedDate = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return convertedDate;
	}

	public static boolean isToday(Date date) {
		return isSameDay(date, Calendar.getInstance().getTime());
	}

	public static boolean isSameDay(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The dates must not be null");
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameDay(cal1, cal2);
	}

	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The dates must not be null");
		}
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
				&& cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1
					.get(Calendar.DAY_OF_YEAR) == cal2
				.get(Calendar.DAY_OF_YEAR));
	}
	
	
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}
	
    public static String SetCurrentDate() {
        String currentdateStr = null;
        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);	
        currentdateStr=""+(month+1)+"/"+day+"/"+year;
		return currentdateStr;
	}
    
    public static void isViewDisable(ViewGroup layout, boolean isDisable) {
		layout.setEnabled(isDisable);
		layout.setClickable(isDisable);
		for (int i = 0; i < layout.getChildCount(); i++) {
			View child = layout.getChildAt(i);
			if (child instanceof ViewGroup) {
				child.getParent().requestDisallowInterceptTouchEvent(true);
				isViewDisable((ViewGroup) child, isDisable);
			} else {
				//child.setOnTouchListener(null);
				child.getParent().requestDisallowInterceptTouchEvent(false);
				child.setEnabled(isDisable);
			}
		}
	}
    
    public static ArrayList<String> GetCardtypeList() {
		ArrayList<String> arrStateList = new ArrayList<String>();
		arrStateList.add("American Express");
		arrStateList.add("Master Card");
		arrStateList.add("Visa");
		arrStateList.add("Discovery");
		return arrStateList;
	}
    
    public static boolean islengthis(final String string) {
		if(string.length()==4)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
    
    public static void ValidateMessageBox(Context context, String Title,
			String message) {
		if (message != null) {
			AlertDialog.Builder messageBox = new AlertDialog.Builder(context);
			messageBox.setTitle(Title);
			messageBox.setMessage(message);
			messageBox.setCancelable(false);
			messageBox.setNeutralButton("OK", null);
			messageBox.show();
		}
	}

	// **************************************************************
	// Function For Send One Activity To Other Activity Via Intent
	// **************************************************************
	public static void ActivityChange(Context packContext, Class<?> cls) {

		try {
			Intent intent = new Intent(packContext, cls);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			packContext.startActivity(intent);
		} catch (Exception e) {
			GeneralFunction.messageBox(packContext, packContext
					.getPackageName().toString(), Log.getStackTraceString(e));
		}
	}

	public static void LogOut(final Context mContext, String msg) {
		try {
			AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
			builder.setTitle("Sign Out")
					.setMessage(msg)
					.setCancelable(false)
					.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id) {

									Intent intent = new Intent(mContext,
											HomeActivity.class);
									intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
									mContext.startActivity(intent);

								}
							});

			AlertDialog alert = builder.create();
			alert.show();
		} catch (Exception e) {
			GeneralFunction.messageBox(mContext, "Logout",
					Log.getStackTraceString(e));
		}
	}

	// *********************************************************
	// Get Device Information
	// *********************************************************
	private static String capitalize(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		char first = s.charAt(0);
		if (Character.isUpperCase(first)) {
			return s;
		} else {
			return Character.toUpperCase(first) + s.substring(1);
		}
	}

	// *********************************************************
	// Get Device Name
	// *********************************************************
	public static String GetDeviceName() {
		String manufacturer = Build.MANUFACTURER;
		String model = Build.MODEL;

		if (model.startsWith(manufacturer)) {
			return capitalize(model);
		} else {
			return capitalize(manufacturer) + " " + model;
		}
	}

	public static String GetAndroidVersion() {
		return Build.VERSION.RELEASE;
	}
	// *********************************************************
	// Get Device Id
	// *********************************************************
	public static String GetDeviceId(Context context) {
		String deviceId = "";
		final TelephonyManager mTelephony = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		if (mTelephony.getDeviceId() != null) {
			deviceId = mTelephony.getDeviceId();
		} else {
			deviceId = Settings.Secure.getString(context.getContentResolver(),
					Settings.Secure.ANDROID_ID);
		}
		return deviceId;
	}
	/**
	 * Get Device Resolution	 *
	 * @param context	 *            -Activity Context
	 * @return resolution as string
	 */
	public static String getDeviceResoultion(Context context) {
		int density = context.getResources().getDisplayMetrics().densityDpi;
		if (density == DisplayMetrics.DENSITY_LOW)
			return "LDPI";
		else if (density == DisplayMetrics.DENSITY_MEDIUM)
			return "MDPI";
		else if (density == DisplayMetrics.DENSITY_HIGH)
			return "HDPI";
		else if (density == DisplayMetrics.DENSITY_XHIGH)
			return "XHDPI";         // Resolution 1280* 720
		else if (density == DisplayMetrics.DENSITY_XXHIGH)
			return "XXHDPI";
		else if (density == DisplayMetrics.DENSITY_TV)
			return "MDPI";
		else
			return "MDPI";
	}




	public static boolean CheckGpsAvailable(final Context mContext) {

		LocationManager manager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE );
		boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		/*ContentResolver contentResolver = mContext.getContentResolver();
		boolean gpsStatus = Settings.Secure.isLocationProviderEnabled(contentResolver, LocationManager.GPS_PROVIDER);*/
		return statusOfGPS;
	}

	public static void GpsAlertMessage(final Context mContext,final Activity activity){
		try {
			AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
			builder.setTitle("Gps setting")
					.setMessage("GPS is disabled in your device.Please enable it and select agree")
					.setCancelable(false)
					.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id) {
									Intent callGPSSettingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
									callGPSSettingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
									mContext.startActivity(callGPSSettingIntent);
									activity.finish();

								}
							})
					/*.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener(){
								public void onClick(DialogInterface dialog, int id){
									dialog.cancel();
									activity.finish();
								}
							})*/;

			AlertDialog alert = builder.create();
			alert.show();
		} catch (Exception e) {
			GeneralFunction.messageBox(mContext, "Logout",
					Log.getStackTraceString(e));
		}
	}

	public static void hideKeyboard(Activity activity) {
		InputMethodManager inputManager = (InputMethodManager) activity
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		// check if no view has focus:
		View view = activity.getCurrentFocus();
		if (view != null) {
			inputManager.hideSoftInputFromWindow(view.getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	public static Bitmap Blurimage(Bitmap image,Context context){
		//RenderScript rs=RenderScript.create(context);
		final float BITMAP_SCALE = 0.4f;
		final float BLUR_RADIUS = 7.5f;
		int width = Math.round(image.getWidth() * BITMAP_SCALE);
		int height = Math.round(image.getHeight() * BITMAP_SCALE);

		Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
		Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

		RenderScript rs = RenderScript.create(context);
		ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
		Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
		Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
		theIntrinsic.setRadius(BLUR_RADIUS);
		theIntrinsic.setInput(tmpIn);
		theIntrinsic.forEach(tmpOut);
		tmpOut.copyTo(outputBitmap);

		return outputBitmap;
	}

	//ListView Height BasedOn Children
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ArrayAdapter listAdapter = (ArrayAdapter) listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}

	public static String IsNull(String str){

		if (str == null && str.equals("null")){
			str="";
		}
			return str;
	}





}
