package healthygo2.vijayanitech.com.healthygo;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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


public class CustomList extends ArrayAdapter<String> implements View.OnClickListener{
    private String[] testnames;
    private String[] names;
    private String[] location;
    private String[] price;
    private String[] discprice;
    private String[] urlimage;
    private Activity context;
    private static final String REGISTER_URL = "http://healthygo.in/cartservice/cartadd.php?";
    String emailss = LoginActivity.email;
    public static final String KEY_TESTNAME = "testname";
    public static final String KEY_TESTPRICE = "price";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_URL = "urls";
    // ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    Button vieww,addtocart;
    public CustomList(Activity context, String[] testnames, String[] names, String[] location, String[] price, String[] discprice, String[]urlimage) {
        super(context, R.layout.list_row, testnames);
        this.context = context;
        this.testnames = testnames;
        this.names = names;
        this.location = location;
        this.price = price;
        this.discprice = discprice;
        this.urlimage = urlimage;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);//context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_row, null, true);

        ImageView thumbNail = (ImageView) listViewItem.findViewById(R.id.thumbnail);
        TextView testname = (TextView) listViewItem.findViewById(R.id.testname);
        TextView name = (TextView) listViewItem.findViewById(R.id.name);
        TextView locations = (TextView) listViewItem.findViewById(R.id.location);
        TextView testPrice = (TextView) listViewItem.findViewById(R.id.testprice);
        final TextView discprices = (TextView) listViewItem.findViewById(R.id.discprice);
        addtocart = (Button) listViewItem.findViewById(R.id.buttonaddtocart);
        vieww = (Button) listViewItem.findViewById(R.id.buttonView);
        vieww.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String testnam=testnames[position];
                String name=names[position];
                String loc=location[position];
                String dprice=discprice[position];
                String pric=price[position];
                String url=urlimage[position];
                Intent i=new Intent(getContext(),ItemDesc.class);
                i.putExtra("testname",testnam);
                i.putExtra("name",name);
                i.putExtra("loc",loc);
                i.putExtra("dprice",dprice);
                i.putExtra("pric",pric);
                i.putExtra("url",url);

                context.startActivity(i);
            }
        });


        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String testnamee = testnames[0];
                String newtest =  testnamee.replaceAll("\\s+","");
                final String dppp = discprice[0];
                final String urlss = urlimage[0];

                String urlnew = REGISTER_URL+emailss;
                StringRequest stringRequest = new StringRequest(Request.Method.GET, urlnew,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
//                        Intent i = new Intent(ItemDesc.this,CartActivity.class);
//                        startActivity(i);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(),error.toString(), Toast.LENGTH_LONG).show();
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

                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(stringRequest);
            }
        });
        testname.setText(" "+testnames[position]);
        name.setText(names[position]);
        locations.setText(location[position].toUpperCase());
        testPrice.setText("Actual Price:          \u20B9"+price[position]);
        discprices.setText("Discounted Price: ₹"+discprice[position]);

        Picasso.with(context)
                .load(urlimage[position])
                .into(thumbNail);

        listViewItem.setTag(convertView);
        return listViewItem;
    }

    @Override
    public void onClick(View v) {

    }
}