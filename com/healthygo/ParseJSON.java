package healthygo2.vijayanitech.com.healthygo;

/**
 * Created by pc on 9/28/2016.
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ParseJSON {
    public static String[] testname;
    public static String[] name;
    public static String[] location;
    public static String[] testprice;
    public static String[] discprice;
    public static String[] urlImage;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_TEST_NAME = "pathlab_test_name";
    public static final String KEY_NAME = "pathlab_name";
    public static final String KEY_LOCATION = "pathlab_location";
    public static final String KEY_PRICE = "pathlab_test_price";
    public static final String KEY_DESCPRICE = "pathlab_disc_price";
    public static final String KEY_IMAGEURL ="pathlab_image";



    private JSONArray users = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            testname = new String[users.length()];
            name = new String[users.length()];
            location = new String[users.length()];
            testprice = new String[users.length()];
            discprice = new String[users.length()];
            urlImage = new String[users.length()];


            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                testname[i] = jo.getString(KEY_TEST_NAME);
                name[i] = jo.getString(KEY_NAME);
                location[i] = jo.getString(KEY_LOCATION);
                testprice[i] = jo.getString(KEY_PRICE);
                discprice[i] = jo.getString(KEY_DESCPRICE);
                urlImage[i] = jo.getString(KEY_IMAGEURL);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
