package healthygo2.vijayanitech.com.healthygo;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class NavigationBar extends ActionBarActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    ImageView lab,book,dental,eye,complete;
    private ViewFlipper mViewFlipper;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private Animation.AnimationListener mAnimationListener;
    private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());
    RelativeLayout relnew;
    CircleImageView dpic;
    Activity context;
    int mediumlogin = LoginActivity.resultlogin;
    private boolean loggedIn = false;
    static String usergivenname;
    static String userphotoo;
    TextView nav_user;
    AutoCompleteTextView AcSearch;
    ImageView ivSearch;
    LinearLayout llSearchAutocomplete,ll_menu,llRight;
    TextView tvClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar);
        relnew = (RelativeLayout) findViewById(R.id.relnew);
        mViewFlipper = (ViewFlipper)findViewById(R.id.view_flipper);
        mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_in));
        mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_out));
        // controlling animation
        mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);

      //  TextView tv = (TextView) findViewById(R.id.guestt);
        lab = (ImageView) findViewById(R.id.lab);
        book = (ImageView) findViewById(R.id.book);
        dental = (ImageView) findViewById(R.id.dental);
        eye = (ImageView) findViewById(R.id.eye);
        complete = (ImageView) findViewById(R.id.complete);
        ivSearch=(ImageView)findViewById(R.id.ivsearch);

        tvClose=(TextView) findViewById(R.id.tvclose);
        AcSearch=(AutoCompleteTextView) findViewById(R.id.acsearch);
        ll_menu=(LinearLayout) findViewById(R.id.ll_menu);
        llRight=(LinearLayout) findViewById(R.id.llright);
        ivSearch.setOnClickListener(this);
        tvClose.setOnClickListener(this);
        AcSearch.setVisibility(View.GONE);
        llRight.setVisibility(View.VISIBLE);

        AcSearch.setHint(getResources().getString(R.string.searchactionbartext));



//        Intent in = getIntent();
//        String gname = in.getExtras().getString("usergmail");

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
        viewflipper();


        lab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(NavigationBar.this,Book.class);
                //in.putExtra("url", "http://healthygo.in/product-category/pathlab/");
                startActivity(in);

            }
        });
//        book.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               Intent in = new Intent(NavigationBar.this,Book.class);
////                in.putExtra("url", "http://healthygo.in/product-category/pathlab/");
//               startActivity(in);
//            }
//        });
        dental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(NavigationBar.this,Dental.class);
//                in.putExtra("url", "http://healthygo.in/product-category/dentists/");
                startActivity(in);
            }
        });
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(NavigationBar.this,Eye.class);
//                in.putExtra("url", "http://healthygo.in/product-category/dentists/");
                startActivity(in);
            }
        });
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(NavigationBar.this,Complete.class);

                startActivity(in);
            }
        });
//

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
         nav_user = (TextView)hView.findViewById(R.id.guestt);
        dpic = (CircleImageView)hView.findViewById(R.id.iv_profilepicc);

       if(mediumlogin==1){
           //getting value from shared preferences
           SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
           String storeemail = sharedPreferences.getString(Config.EMAIL_SHARED_PREF,"Not Available");

           nav_user.setText(storeemail);

       }
        if(mediumlogin==2){
            Intent getone = getIntent();
       // String gname = getone.getExtras().getString("usergname");
//        String usergivenname = getone.getExtras().getString("usergivenname");
//        String useremail = getone.getExtras().getString("useremail");
//        String userphotoo = getone.getExtras().getString("userphoto");
            SharedPreferences sharedPreferences = NavigationBar.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);
            if(loggedIn) {
//                usergivenname = sharedPreferences.getString(Config.googledisplayname, null);
//                userphotoo = sharedPreferences.getString(Config.googlepic, null);
                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                editor.commit();
               // Toast.makeText(this, userphotoo + usergivenname, Toast.LENGTH_SHORT).show();
                nav_user.setText(usergivenname);
                Picasso.with(NavigationBar.this).load(userphotoo).into(dpic);
            }
        }
         if(mediumlogin==3) {
             Intent fbtask = getIntent();
             String usernamefb = fbtask.getExtras().getString("usernamefb");
             String fbdpurl = fbtask.getExtras().getString("userdpfb");
             //Toast.makeText(getApplicationContext(),usernamefb+fbdpurl,Toast.LENGTH_LONG).show();
             nav_user.setText(usernamefb);
             Picasso.with(NavigationBar.this).load(fbdpurl).into(dpic);
         }

     // Toast.makeText(getApplicationContext(),"Welcome"+userphotoo,Toast.LENGTH_LONG).show();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Coming Soon", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();


        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if(loggedIn){


            usergivenname = sharedPreferences.getString(Config.googledisplayname, null);
            userphotoo = sharedPreferences.getString(Config.googlepic, null);
            //Toast.makeText(getApplicationContext(),usergivenname+userphotoo,Toast.LENGTH_LONG).show();
            nav_user.setText(usergivenname);
            Picasso.with(NavigationBar.this).load(userphotoo).into(dpic);

        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void logout(){

        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Config.EMAIL_SHARED_PREF, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(NavigationBar.this, LoginActivity.class);
                        startActivity(intent);
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
    //rate code starts here

    private void showRateAppDialogIfNeeded() {
        boolean bool = AppPreferences.getInstance(getApplicationContext()).getAppRate();
        int i = AppPreferences.getInstance(getApplicationContext()).getLaunchCount();

            createAppRatingDialog(getString(R.string.rate_app_title), getString(R.string.rate_app_message)).show();

    }

    private AlertDialog createAppRatingDialog(String rateAppTitle, String rateAppMessage) {
        AlertDialog dialog = new AlertDialog.Builder(this).setPositiveButton(getString(R.string.dialog_app_rate), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                openAppInPlayStore(NavigationBar.this);
                AppPreferences.getInstance(NavigationBar.this.getApplicationContext()).setAppRate(false);
            }
        }).setNegativeButton(getString(R.string.dialog_your_feedback), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                openFeedback(NavigationBar.this);
                AppPreferences.getInstance(NavigationBar.this.getApplicationContext()).setAppRate(false);
            }
        }).setNeutralButton(getString(R.string.dialog_ask_later), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                paramAnonymousDialogInterface.dismiss();
                AppPreferences.getInstance(NavigationBar.this.getApplicationContext()).resetLaunchCount();
            }
        }).setMessage(rateAppMessage).setTitle(rateAppTitle).create();
        return dialog;
    }

    public static void openAppInPlayStore(Context paramContext) {
        paramContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=healthygo2.vijayanitech.com.healthygo#details-reviews")));
    }

    public static void openFeedback(Context paramContext) {
        Intent localIntent = new Intent(Intent.ACTION_SEND);
        localIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"healthygo.in@gmail.com"});
        localIntent.putExtra(Intent.EXTRA_CC, "");
        String str = null;
        try {
            str = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
            localIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for Your Android App");
            localIntent.putExtra(Intent.EXTRA_TEXT, "\n\n----------------------------------\n Device OS: Android \n Device OS version: " +
                    Build.VERSION.RELEASE + "\n App Version: " + str + "\n Device Brand: " + Build.BRAND +
                    "\n Device Model: " + Build.MODEL + "\n Device Manufacturer: " + Build.MANUFACTURER);
            localIntent.setType("message/rfc822");
            paramContext.startActivity(Intent.createChooser(localIntent, "Choose an Email client :"));
        } catch (Exception e) {
            Log.d("OpenFeedback", e.getMessage());
        }
    }
//rate code ends here


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            logout();
            LoginManager.getInstance().logOut();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent home=new Intent(NavigationBar.this,NavigationBar.class);
            startActivity(home);

        } else if (id == R.id.history) {
            Intent hist=new Intent(NavigationBar.this,History.class);
            startActivity(hist);

        } else if (id == R.id.cart) {
            Intent cartt=new Intent(NavigationBar.this,CartActivity.class);
            startActivity(cartt);

        }

        else if (id == R.id.support) {

            Intent sup = new Intent(NavigationBar.this,Support.class);
            startActivity(sup);


        } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Find us on PlayStore https://play.google.com/store/apps/details?id=healthygo2.vijayanitech.com.healthygo");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        } else if (id == R.id.contact) {
            Intent con = new Intent(NavigationBar.this, Contact.class);
            startActivity(con);

        }
        else if (id == R.id.offers) {
            Intent con = new Intent(NavigationBar.this, Offers.class);
            startActivity(con);

        }
        else if (id == R.id.rate) {
              showRateAppDialogIfNeeded();

        }

//

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
        switch (v.getId()){
            case R.id.ivsearch:
                AcSearch.setVisibility(View.VISIBLE);
                llRight.setVisibility(View.GONE);
                break;
            case R.id.tvclose:

                AcSearch.setVisibility(View.GONE);
                llRight.setVisibility(View.VISIBLE);
                break;
        }

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
//        if(imageno==0){
//            ivd1o.setVisibility(View.VISIBLE);
//            ivd1.setVisibility(View.GONE);
//        }
//        else  if(imageno==1){
//           ivd2o.setVisibility(View.VISIBLE);
//            ivd2.setVisibility(View.GONE);
//        }
//        else  if(imageno==2){
//            ivd3o.setVisibility(View.VISIBLE);
//            ivd3.setVisibility(View.GONE);
//        }
//        else  if(imageno==3){
//            ivd4o.setVisibility(View.VISIBLE);
//            ivd4.setVisibility(View.GONE);
//        }

    }

    private void startingShow(){
//        ivd1o.setVisibility(View.GONE);
//        ivd2o.setVisibility(View.GONE);
//        ivd3o.setVisibility(View.GONE);
//        ivd4o.setVisibility(View.GONE);
//
//        ivd1.setVisibility(View.VISIBLE);
//        ivd2.setVisibility(View.VISIBLE);
//        ivd3.setVisibility(View.VISIBLE);
//        ivd4.setVisibility(View.VISIBLE);

    }

}
