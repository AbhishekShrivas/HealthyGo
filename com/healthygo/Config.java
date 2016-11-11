package healthygo2.vijayanitech.com.healthygo;

import android.graphics.Bitmap;

/**
 * Created by pc on 8/19/2016.
 */
public class Config {

    public static final String LOGIN_URL = "http://healthygo.in/Logintoo1.php";


    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";


    public static final String LOGIN_SUCCESS = "success";


    public static final String SHARED_PREF_NAME = "myloginapp";


    public static final String EMAIL_SHARED_PREF = "email";


    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
    public static final String TOTAL_SUM = "0";
    public static final String resultcode = "0";
    public static final String googledisplayname = "displayname";
    public static final String googlepic = "picurl";


    public static String[] names;
    public static String[] urls;
    public static Bitmap[] bitmaps;

    public static final String GET_URL = "http://healthygo.in/android/pathlab.php";
    public static final String TAG_IMAGE_URL = "pathlab_image";
    public static final String TAG_IMAGE_NAME = "pathlab_test_name";
    public static final String TAG_JSON_ARRAY="result";

    public Config(int i){
        names = new String[i];
        urls = new String[i];
        bitmaps = new Bitmap[i];
    }
}
