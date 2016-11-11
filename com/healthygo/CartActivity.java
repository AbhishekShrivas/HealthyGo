package healthygo2.vijayanitech.com.healthygo;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class CartActivity extends ListActivity {
    ListView lv;
    ImageView iv;
    private static final String url = "http://healthygo.in/cartservice/cartfetch.php?email=";
    private static final String urltotal = "http://healthygo.in/cartservice/totalsum.php?email=";
    String email = LoginActivity.email;
    Button pay;
    TextView totals,subtotal;
    static  String str;
    Activity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        lv=(ListView)findViewById(android.R.id.list);
        iv=(ImageView)findViewById(R.id.ivremove);
        pay = (Button) findViewById(R.id.Proceed);
        totals = (TextView) findViewById(R.id.tot);
        subtotal = (TextView) findViewById(R.id.sub);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String totalmon = totals.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                String sumcart = sharedPreferences.getString(Config.TOTAL_SUM,"Not Available");
                int money = Integer.parseInt(sumcart);

                    //  Toast.makeText(CartActivity.this, sumcart, Toast.LENGTH_LONG).show();
                    Intent payy = new Intent(CartActivity.this, Billing.class);
                    payy.putExtra("totalmoney", sumcart);
                    startActivity(payy);

            }
        });


      /* // CustomListCart cl = new CustomListCart();
        CustomListCart c=new CustomListCart(this,ItemDesc.testnames, ItemDesc.dp,ItemDesc.urll);
        lv.setAdapter(c);

*/
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String storeemail = sharedPreferences.getString(Config.EMAIL_SHARED_PREF,"Not Available");
       // Toast.makeText(getApplicationContext(),storeemail,Toast.LENGTH_LONG).show();
        String newurl = url + storeemail;

        StringRequest stringRequest = new StringRequest(newurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                        showtotal();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CartActivity.this, "Not Available", Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showJSON(String json) {
        ParseJsonForCart pj = new ParseJsonForCart(json);
        pj.parseJSON();
        CustomListCart cl = new CustomListCart(this, ParseJsonForCart.testname,ParseJsonForCart.discprice,ParseJsonForCart.urlImage);
        cl.notifyDataSetChanged();
        lv.setAdapter(cl);

       // cl.notifyDataSetChanged();
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int pos=position;
//                switch (view.getId()) {
//                    default:
//                        Toast.makeText(getApplicationContext(), "Added To Cart", Toast.LENGTH_LONG).show();
//                        break;
//                }
////               int positionn = position;
////                String str = (String) listView.getItemAtPosition(positionn);
////
////                String cities = String.valueOf(parent.getItemAtPosition(position));
////                Toast.makeText(Book.this, cities, Toast.LENGTH_LONG).show();
////                Intent i = new Intent(getBaseContext(),ItemDesc.class);
////                i.putExtra("data",str);
////                startActivityForResult(i,position);
//            }
//        });

        lv.setFocusable(false);
    }

    private void showtotal(){
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String storeemail = sharedPreferences.getString(Config.EMAIL_SHARED_PREF,"Not Available");

        String newurl = urltotal + storeemail;

        StringRequest stringRequest = new StringRequest(newurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      showtest(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CartActivity.this, "Not Available", Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showtest(String total){

     totals.setText(total);
     String summ = totals.getText().toString();
        SharedPreferences sharedPreferences = CartActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Creating editor to store values to shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Adding values to editor
        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
        editor.putString(Config.TOTAL_SUM, summ);

        //Saving values to editor
        editor.commit();


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            this.finish();
        }

        return false;
    }
    @Override

    public void onBackPressed() {

        // Write your code here

        super.onBackPressed();
        Intent bck = new Intent(getApplicationContext(),NavigationBar.class);
        startActivity(bck);
          finish();


    }

}
