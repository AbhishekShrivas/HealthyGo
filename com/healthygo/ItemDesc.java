package healthygo2.vijayanitech.com.healthygo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class ItemDesc extends AppCompatActivity implements View.OnClickListener{
    TextView testname,loc,price,dprice;
    ImageView url,cart1;
    Button addcart;
    public static String testnames[]=new String[10];
    public static String dp[]=new String[10];
    public static String urll[]=new String[10];

    private static final String REGISTER_URL = "http://healthygo.in/cartservice/cartadd.php?";

    public static final String KEY_TESTNAME = "testname";
    public static final String KEY_TESTPRICE = "price";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_URL = "urls";
    String emailss = LoginActivity.email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_desc);

        testname = (TextView) findViewById(R.id.testname);
        loc = (TextView) findViewById(R.id.location);
        price = (TextView) findViewById(R.id.price);
        dprice = (TextView) findViewById(R.id.discprice);
        url = (ImageView) findViewById(R.id.testimage);

        cart1=(ImageView)findViewById(R.id.cart);
        cart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ItemDesc.this,CartActivity.class);
                i.putExtra("launched_from",this.getClass().getSimpleName());
                startActivity(i);

            }
        });
        addcart=(Button)findViewById(R.id.ButtonAddToCart);
        addcart.setOnClickListener(this);
        Intent in = getIntent();


        // String names = in.getExtras().getString("name");
        //name.setText(names);

        String l = in.getExtras().getString("loc");
        loc.setText(l);


        String  pr = in.getExtras().getString("pric");
        price.setText(pr);

        for(int i=0;i<10;i++) {
            dp[i] = in.getExtras().getString("dprice");
            dprice.setText(dp[i]);


            testnames[i] = in.getExtras().getString("testname");
            testname.setText(testnames[i]);
            //Toast.makeText(getApplicationContext(),testnames[i],Toast.LENGTH_LONG).show();

            urll[i] = in.getExtras().getString("url");
            Picasso.with(ItemDesc.this).load(urll[i]).into(url);
        }



    }

    private void registerUser(){

        final String testnamee = testnames[0];
         String newtest =  testnamee.replaceAll("\\s+","");
        final String dppp = dp[0];
        final String urlss = urll[0];
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String storeem = sharedPreferences.getString(Config.EMAIL_SHARED_PREF,"Not Available");
      // final String ur = "http://healthygo.in/cartservice/cartadd.php?email=b@gmail.com&testname=ahb hi h&price=456&urls=httpnkj";
       final String urlnew = REGISTER_URL+"email="+storeem+"&testname="+newtest+"&price="+dppp+"&urls="+urlss;
       //  Toast.makeText(getApplicationContext(),urlnew,Toast.LENGTH_LONG).show();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlnew,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ItemDesc.this,response,Toast.LENGTH_LONG).show();
//                        Intent i = new Intent(ItemDesc.this,CartActivity.class);
//                        startActivity(i);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ItemDesc.this,error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_TESTNAME,testnamee);
                params.put(KEY_TESTPRICE,dppp);
                params.put(KEY_URL, urlss);
                params.put(KEY_EMAIL,emailss);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v == addcart){
            registerUser();
        }

    }
}
