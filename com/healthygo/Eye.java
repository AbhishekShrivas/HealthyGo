package healthygo2.vijayanitech.com.healthygo;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Eye extends ListActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String TAG = Book.class.getSimpleName();
    // private Button buttonGet;
    // Movies json url
    private static final String url = "http://healthygo.in/android/eye.php";
    private ProgressDialog pDialog;
    //private List<Movie> movieList = new ArrayList<Movie>();
    private ListView listView;
    // private CustomListAdapter adapter;
    ImageView ivSearch;
    AutoCompleteTextView AcSearch;
    Button viewcart,addtocart;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private ViewFlipper mViewFlipper;
    private Animation.AnimationListener mAnimationListener;
    private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());
    ImageView ivd1,ivd2,ivd3,ivd4,ivd1o,ivd2o,ivd3o,ivd4o,ivback;
    LinearLayout llMain,llRight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Window window=getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
        //  AcSearch=(AutoCompleteTextView)findViewById(R.id.acsearch);
        llRight=(LinearLayout)findViewById(R.id.llright);
        listView = (ListView) findViewById(android.R.id.list);
        listView.setClickable(true);
        ivback = (ImageView) findViewById(R.id.imageView2);
        viewcart = (Button) findViewById(R.id.buttonView);
        addtocart = (Button) findViewById(R.id.buttonaddtocart);
        ivback.setOnClickListener(this);
        // listView.setOnItemClickListener(this);
//        ivback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent returnn = new Intent(Book.this,NavigationBar.class);
//                startActivity(returnn);
//            }
//        });
//        AcSearch.setVisibility(View.GONE);
        //      AcSearch.setHint(getResources().getString(R.string.searchactionbartext));
        mViewFlipper = (ViewFlipper)findViewById(R.id.view_flipper);
        mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_in));
        mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_out));
        // controlling animation
        mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
        llMain=(LinearLayout)findViewById(R.id.llmain);
//        llMain.setOnClickListener(this);

        mViewFlipper.setFlipInterval(3000);

        mViewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                detector.onTouchEvent(event);

                return true;
            }
        });

        try{

            mViewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {
                    int displayedChild = mViewFlipper.getDisplayedChild();
                    // displayedChild;
                    //  showWhiteAndRedtext(displayedChild);
                    //Toast.makeText(ViewFlipperSampleActivity.this,"start animation "+mViewFlipper.getDisplayedChild()+"\n child count"+mViewFlipper.getChildCount(),Toast.LENGTH_SHORT).show();
                    //Log.d("Rup","Animation "+animation);
                }
                public void onAnimationRepeat(Animation animation) {
                    // Toast.makeText(mContext, "Repeat", Toast.LENGTH_SHORT).show();
                }
                public void onAnimationEnd(Animation animation) {

                }
            });
            mViewFlipper.startFlipping();

        }
        catch(Exception e){

        }



        // private void sendRequest(){

        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Eye.this, "Not Available", Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }

    private void showJSON(String json) {
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        CustomList cl = new CustomList(this, ParseJSON.testname, ParseJSON.name, ParseJSON.location, ParseJSON.testprice, ParseJSON.discprice,ParseJSON.urlImage);
        listView.setAdapter(cl);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pos=position;
                switch (view.getId()) {
                    default:
                        Toast.makeText(getApplicationContext(), "Added To Cart", Toast.LENGTH_LONG).show();
                        break;
                }
//               int positionn = position;
//                String str = (String) listView.getItemAtPosition(positionn);
//
//                String cities = String.valueOf(parent.getItemAtPosition(position));
//                Toast.makeText(Book.this, cities, Toast.LENGTH_LONG).show();
//                Intent i = new Intent(getBaseContext(),ItemDesc.class);
//                i.putExtra("data",str);
//                startActivityForResult(i,position);
            }
        });
        viewflipper();
        GeneralFunction.setListViewHeightBasedOnChildren(listView);
        listView.setFocusable(false);
    }
    @Override
    public void onListItemClick(ListView parent, View v, int position,long id) {
        switch (position)
        {
            default:
                Toast.makeText(getApplicationContext(),"asdfghjkl",Toast.LENGTH_LONG).show();
                break;

        }



    }
    private void viewflipper(){
        mViewFlipper.setFlipInterval(3000);
        mViewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });

        try{
            mViewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {
                    int displayedChild = mViewFlipper.getDisplayedChild();
                    // displayedChild;
                    //  showWhiteAndRedtext(displayedChild);
                    //Toast.makeText(ViewFlipperSampleActivity.this,"start animation "+mViewFlipper.getDisplayedChild()+"\n child count"+mViewFlipper.getChildCount(),Toast.LENGTH_SHORT).show();
                    //Log.d("Rup","Animation "+animation);
                }
                public void onAnimationRepeat(Animation animation) {
                    // Toast.makeText(mContext, "Repeat", Toast.LENGTH_SHORT).show();
                }
                public void onAnimationEnd(Animation animation) {

                }
            });
            mViewFlipper.startFlipping();

        }
        catch(Exception e){

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivback:
                finish();
                Intent i = new Intent(Eye.this,NavigationBar.class);
                startActivity(i);
                break;

//           case R.id.ivsearch:
//               AcSearch.setVisibility(View.VISIBLE);
//               llRight.setVisibility(View.GONE);
//               break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                // right to left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_in));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_out));
                    // controlling animation
                    mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
                    mViewFlipper.showNext();
                    return true;
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_in));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.right_out));
                    // controlling animation
                    mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
                    mViewFlipper.showPrevious();
                    return true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }
    }
    private void showWhiteAndRedtext(int imageno){
        startingShow();
        if(imageno==0){
            ivd1o.setVisibility(View.VISIBLE);
            ivd1.setVisibility(View.GONE);
        }
        else  if(imageno==1){
            ivd2o.setVisibility(View.VISIBLE);
            ivd2.setVisibility(View.GONE);
        }
        else  if(imageno==2){
            ivd3o.setVisibility(View.VISIBLE);
            ivd3.setVisibility(View.GONE);
        }
        else  if(imageno==3){
            ivd4o.setVisibility(View.VISIBLE);
            ivd4.setVisibility(View.GONE);
        }

    }

    private void startingShow(){
        ivd1o.setVisibility(View.GONE);
        ivd2o.setVisibility(View.GONE);
        ivd3o.setVisibility(View.GONE);
        ivd4o.setVisibility(View.GONE);

        ivd1.setVisibility(View.VISIBLE);
        ivd2.setVisibility(View.VISIBLE);
        ivd3.setVisibility(View.VISIBLE);
        ivd4.setVisibility(View.VISIBLE);

    }


}