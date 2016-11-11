package healthygo2.vijayanitech.com.healthygo;

import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    Button signin,signup;
    private static long back_pressed;
    ImageView ivgoogle,ivfacebook;
    ConnectionDetector cdObj;
    AccountManager mAccountManager;
    private String facebook_id, f_name, m_name, l_name, gender, profile_image, full_name, email_id;
    private Context mContext;
    ProgressDialog progress;
    FbloginFragment fbloginFragment;
    private CallbackManager mcallCallbackManger;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    //google variables
    private GoogleSignInOptions gso;
    private GoogleApiClient mGoogleApiClient;
    private int RC_SIGN_IN = 100;

    public static int resultlogin;


    private FacebookCallback<LoginResult> mCallback=new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            if(profile != null) {
                //textView.setText(profile.getName());
                String userprofilename = profile.getName();
                Uri uri = profile.getProfilePictureUri(20, 20);
                String dpurl = uri.toString();
                Intent i = new Intent(getApplicationContext(), NavigationBar.class);
                i.putExtra("usernamefb",userprofilename);
                i.putExtra("userdpfb",dpurl);
                startActivity(i);
                Toast.makeText(getApplicationContext(),userprofilename+dpurl,Toast.LENGTH_LONG).show();
                displayMessage(profile);
            }

        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        signin=(Button)findViewById(R.id.btnsignin);
        signup=(Button)findViewById(R.id.btnsignup);
        ivgoogle = (ImageView)findViewById(R.id.ivgoogle);
        ivfacebook = (ImageView)findViewById(R.id.ivfacebook);
        FacebookSdk.sdkInitialize(getApplicationContext());
        mcallCallbackManger = CallbackManager.Factory.create();

        accessTokenTracker= new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {

            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                displayMessage(newProfile);
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();
        final List permissions = new ArrayList();
        permissions.add("email");
        permissions.add("public_profile");

        ivfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                LoginButton loginButton= new LoginButton(getApplication());
//                loginButton.setReadPermissions("user_friends");
                LoginManager.getInstance().logInWithReadPermissions(HomeActivity.this, permissions);
               //ivfacebook.registerCallback(mcallCallbackManger, mcallback);
               // loginButton.performClick();
            }
        });
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)

                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        ivgoogle.setOnClickListener(this);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign=new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(sign);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });






    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }



    private void signIn() {
        //Creating an intent
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        //Starting intent for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mcallCallbackManger.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin
            handleSignInResult(result);

        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        //If the login succeed
        if (result.isSuccess()) {
            //Getting google account
            resultlogin=2;

            GoogleSignInAccount acct = result.getSignInAccount();
            String gname = acct.getDisplayName();
            String personGivenName = acct.getDisplayName();

            String personEmail = acct.getEmail();
            // String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            String dpurl = personPhoto.toString();

            SharedPreferences sharedPreferences = HomeActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
            editor.putString(Config.EMAIL_SHARED_PREF, personEmail);
            editor.putString(Config.googledisplayname, personGivenName);
            editor.putString(Config.googlepic, dpurl);
            editor.commit();

            //Toast.makeText(getApplicationContext(),gname,Toast.LENGTH_LONG).show();
            Intent i = new Intent(HomeActivity.this,NavigationBar.class);
            // i.putExtra("usergname",gname);
            i.putExtra("usergivenname",personGivenName);
            i.putExtra("useremail",personEmail);
            i.putExtra("userphoto",dpurl);
            startActivity(i);
            // Toast.makeText(this, dpurl, Toast.LENGTH_SHORT).show();
            //Displaying name and email
            //textViewName.setText(acct.getDisplayName());
            //textViewEmail.setText(acct.getEmail());

            //Initializing image loader
            //imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext())
            //  .getImageLoader();

            //imageLoader.get(acct.getPhotoUrl().toString(),
            //     ImageLoader.getImageListener(profilePhoto,
//                            R.mipmap.ic_launcher,
//                            R.mipmap.ic_launcher));

            //Loading image
            //  profilePhoto.setImageUrl(acct.getPhotoUrl().toString(), imageLoader);

        } else {
            //If login fails
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    private void displayMessage(Profile profile){
        if(profile != null){
            //textView.setText(profile.getName());
            String userprofilename = profile.getName();
            Uri uri = profile.getProfilePictureUri(20,20);
            String dpurl = uri.toString();

            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
            editor.putString(Config.googlepic, dpurl);
            editor.putString(Config.googledisplayname, userprofilename);
            // editor.putString(Config.googlepic, dpurl);
            editor.commit();

            Intent i = new Intent(getApplicationContext(), NavigationBar.class);
            i.putExtra("usernamefb",userprofilename);
            i.putExtra("userdpfb",dpurl);
            startActivity(i);

        }
    }
    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub

        if(back_pressed+2000>System.currentTimeMillis())
            super.onBackPressed();
        else
            Toast.makeText(getBaseContext(), "Happy to help you..", Toast.LENGTH_SHORT).show();
        back_pressed=System.currentTimeMillis();
        moveTaskToBack(true);
        HomeActivity.this.finish();
    }



    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.ivgoogle){
           resultlogin=2;
            signIn();
        }
    }
}
