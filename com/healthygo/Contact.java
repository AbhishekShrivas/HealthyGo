package healthygo2.vijayanitech.com.healthygo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Contact extends AppCompatActivity implements View.OnClickListener {
    EditText name,email,message;
    Button submit;
    private static final String REGISTER_URL = "http://healthygo.in/cartservice/contact.php?";

    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_MESSAGE = "message";
    String emailss = LoginActivity.email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        name=(EditText)findViewById(R.id.editText);
        email=(EditText)findViewById(R.id.editText2);
        message=(EditText)findViewById(R.id.editText3);

        submit=(Button)findViewById(R.id.button);
        submit.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if(v == submit){
            registerUser();
        }
    }

    private void registerUser(){
        final String name1 = name.getText().toString();
        final String email1 = email.getText().toString();
        final String message1 = message.getText().toString();

        String urlnew = REGISTER_URL+"name="+name1+"&email="+email1+"&message="+message1;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlnew,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Contact.this,response,Toast.LENGTH_LONG).show();
                        name.setText("");
                        email.setText("");
                        message.setText("");
//                        Intent i = new Intent(ItemDesc.this,CartActivity.class);
//                        startActivity(i);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Contact.this,error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_NAME,name1);
                params.put(KEY_EMAIL,email1);
                params.put(KEY_MESSAGE,message1);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
