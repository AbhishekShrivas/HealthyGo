package healthygo2.vijayanitech.com.healthygo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class Support extends AppCompatActivity implements View.OnClickListener {

    ImageView ivBack,ivDone;
    EditText Feedback;
    TextView tvBottom;

    private static final String REGISTER_URL = "http://healthygo.in/cartservice/feedback.php?";

    public static final String KEY_FEEDBACK="feedback";
    //String emailss = LoginActivity.email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbacksupport);
        InitViews();
    }

    private void InitViews(){
        ivBack=(ImageView)findViewById(R.id.ivback);
        ivDone=(ImageView)findViewById(R.id.ivdone);

        Feedback=(EditText)findViewById(R.id.etfeedback);

        tvBottom=(TextView)findViewById(R.id.tvbottomtext);
        ivBack.setOnClickListener(this);
        ivDone.setOnClickListener(this);

        String strbottomtext=getResources().getString(R.string.ifyouhavesomething)+" <b>"+
                getResources().getString(R.string.feedbackemail)+"</b>";
        tvBottom.setText(Html.fromHtml(strbottomtext));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivback:
                finish();
                break;
            case R.id.ivdone:
                feedbackUser();
                break;
        }
    }

    public void  feedbackUser() {

        final String feed = Feedback.getText().toString();
        int lengthfeed = feed.length();
        if (lengthfeed == 0) {
            Toast.makeText(Support.this,"Please enter something",Toast.LENGTH_LONG).show();

        } else {

            String urlnew = REGISTER_URL + "feedback=" + feed;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, urlnew,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(Support.this, response, Toast.LENGTH_LONG).show();
                            Feedback.setText("");

//                        Intent i = new Intent(ItemDesc.this,CartActivity.class);
//                        startActivity(i);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Support.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(KEY_FEEDBACK, feed);

                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }
}
