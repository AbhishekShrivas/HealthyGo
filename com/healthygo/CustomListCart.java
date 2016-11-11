package healthygo2.vijayanitech.com.healthygo;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Rishabh on 09-10-2016.
 */
public class CustomListCart extends ArrayAdapter<String> implements View.OnClickListener{

    private String []testnames;
    private String []discprice;
    private String []urlimage;
    private Activity context;
    String email = LoginActivity.email;
    private static final String REGISTER_URL = "http://healthygo.in/cartservice/cartdeleteitem.php?";

    public static final String KEY_TESTNAME = "testname";
    public static final String KEY_EMAIL = "email";
   // public static String urltestname;

    Button vieww;
    public CustomListCart(Activity context, String []testnames, String []discprice, String []urlimage) {
        super(context, R.layout.cart_row,testnames);
        this.context = context;
        this.testnames = testnames;

        this.discprice = discprice;
        this.urlimage = urlimage;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);//context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.cart_row, null, true);

        CircleImageView thumbNail = (CircleImageView) listViewItem.findViewById(R.id.thumbnail);
        TextView testname = (TextView) listViewItem.findViewById(R.id.testname);
        TextView discprices = (TextView) listViewItem.findViewById(R.id.discprice);
        ImageView ivrem = (ImageView) listViewItem.findViewById(R.id.ivremove);
        ivrem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setMessage("Are you sure you want to remove this item?");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                deleteItem(position);
                                Intent reback = new Intent(getContext(),CartActivity.class);
                                context.startActivity(reback);
                                notifyDataSetChanged();


                            }
                        });

                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });

                //Showing the alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

       String testnam=testnames[position];
        String dprice=discprice[position];

        String url=urlimage[position];

        testname.setText(testnam);
        discprices.setText("Discounted Price: Rs."+dprice);
//
        Picasso.with(context)
                .load(url)
                .into(thumbNail);

        listViewItem.setTag(convertView);
        return listViewItem;
    }

    public void deleteItem(int pos) {

            final String testname = testnames[pos];

        SharedPreferences sharedPreferences = context.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String storeemail = sharedPreferences.getString(Config.EMAIL_SHARED_PREF,"Not Available");


        String urlnew = REGISTER_URL+"email="+storeemail+"&testname="+testname;
       // Toast.makeText(getContext(),urlnew,Toast.LENGTH_LONG).show();
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
                    params.put(KEY_TESTNAME,testname);
                    params.put(KEY_EMAIL,email);

                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);
        }



    @Override
    public void onClick(View v) {


    }

}
