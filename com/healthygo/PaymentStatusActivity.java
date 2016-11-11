package healthygo2.vijayanitech.com.healthygo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class PaymentStatusActivity extends AppCompatActivity {
    public static final String succeesspayrul = "http://www.healthygo.in/cartservice/success.php?email=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_status);
        Intent getPay = getIntent();
        String payemail = getPay.getExtras().getString("email_pay");
        String transaction_id = getPay.getExtras().getString("transaction_id");
        String id = getPay.getExtras().getString("id");
        String isFromOrder = getPay.getExtras().getString("isFromOrder");

        String finalpayurl = succeesspayrul+payemail;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, finalpayurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(PaymentStatusActivity.this,response,Toast.LENGTH_LONG).show();
//                        Intent i = new Intent(ItemDesc.this,CartActivity.class);
//                        startActivity(i);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PaymentStatusActivity.this,error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){


        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}
