package healthygo2.vijayanitech.com.healthygo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Billing extends AppCompatActivity {
TextView price;
    EditText etname,etemail,etphone;
    Button checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
        price = (TextView) findViewById(R.id.totaluserprice);
        checkout = (Button) findViewById(R.id.finalpay);
        etname = (EditText) findViewById(R.id.billname);
        etemail = (EditText) findViewById(R.id.billemail);
        etphone = (EditText) findViewById(R.id.billphone);

        Intent i = getIntent();
      final  String sett = i.getExtras().getString("totalmoney");
        price.setText("Rs."+sett);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String emailuserr = sharedPreferences.getString(Config.TOTAL_SUM,"Not Available");
       // etemail.setText(emailuserr);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finalname = etname.getText().toString();
                String finalemail = etemail.getText().toString();
                String finalphone = etphone.getText().toString();
                Double amount = Double.parseDouble(sett);
                Bundle bundle = new Bundle();
                Intent i = new Intent(Billing.this,PayUMoneyActivity.class);
                bundle.putString("finaln",finalname);
                bundle.putString("finale",finalemail);
                bundle.putString("finalp",finalphone);
                bundle.putDouble("finalam",amount);
                i.putExtras(bundle);
                startActivity(i);

            }
        });


    }

}
