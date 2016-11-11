package healthygo2.vijayanitech.com.healthygo;

/**
 * Created by pc on 9/28/2016.
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ParseJsonForCart {
    public static String[] testname;
    public static String[] discprice;
    public static String[] urlImage;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_TEST_NAME = "testname";
    public static final String KEY_DESCPRICE = "price";
    public static final String KEY_IMAGEURL ="urls";



    private JSONArray users = null;

    private String json;

    public ParseJsonForCart(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            testname = new String[users.length()];
            discprice = new String[users.length()];
            urlImage = new String[users.length()];


            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                testname[i] = jo.getString(KEY_TEST_NAME);
                discprice[i] = jo.getString(KEY_DESCPRICE);
                urlImage[i] = jo.getString(KEY_IMAGEURL);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
