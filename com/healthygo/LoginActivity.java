package healthygo2.vijayanitech.com.healthygo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin,fblogin;
    private boolean loggedIn = false;
   // ImageView ivfb,ivgoogle;

    ProgressDialog prgDialog;
    //Signing Options
    ImageView ivback;
    public static String email;

    private SignInButton signInButton;
    private GoogleSignInOptions gso;
    private GoogleApiClient mGoogleApiClient;
    private int RC_SIGN_IN = 100;

    TextView forgt;
    public static int resultlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
        editTextEmail = (EditText) findViewById(R.id.etemail);
        editTextPassword = (EditText) findViewById(R.id.etpwd);
        buttonLogin = (Button) findViewById(R.id.btnsignin);
        forgt = (TextView) findViewById(R.id.tvforgotped);
        ivback = (ImageView) findViewById(R.id.ivback);

        prgDialog = new ProgressDialog(this);

        prgDialog.setMessage("Please wait...");

        prgDialog.setCancelable(false);
        forgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgt = new Intent(LoginActivity.this,ForgotPwdActivity.class);
                startActivity(forgt);
            }
        });
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backhome = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(backhome);
            }
        });
      //  fblogin = (Button) findViewById(R.id.login_button);
      //  fblogin.setOnClickListener(this);
      //  ivfb = (ImageView) findViewById(R.id.ivfacebook);
       // ivgoogle = (ImageView) findViewById(R.id.ivgoogle);
       // ivfb.setOnClickListener(this);


        buttonLogin.setOnClickListener(this);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)//.requestScopes(new Scope(Scopes.PLUS_LOGIN))
              //  .requestScopes(new Scope(Scopes.PLUS_ME))
                .requestEmail()//.requestIdToken(this.getResources().getString(R.string.default_web_client_id))//requestServerAuthCode(getString(R.string.default_web_client_id), false)
                .build();

        //Initializing signinbutton
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setScopes(gso.getScopeArray());

        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        //Setting onclick listener to signing button
        signInButton.setOnClickListener(this);
    }



    @Override
    protected void onResume() {
        super.onResume();


        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);

            //If we will get true
            if (loggedIn) {
                //We will start the Profile Activity
                Intent intent = new Intent(LoginActivity.this, NavigationBar.class);
                startActivity(intent);
            }

    }

    //This function will option signing intent
    private void signIn() {
        //Creating an intent
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        //Starting intent for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //If signin
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

            SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
            editor.putString(Config.EMAIL_SHARED_PREF, personEmail);
            editor.putString(Config.googledisplayname, personGivenName);
            editor.putString(Config.googlepic, dpurl);
            editor.commit();

            //Toast.makeText(getApplicationContext(),gname,Toast.LENGTH_LONG).show();
            Intent i = new Intent(LoginActivity.this,NavigationBar.class);
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

            String i =  result.getStatus().toString();
            Toast.makeText(this, i+" ", Toast.LENGTH_LONG).show();
        }
    }
    public void loginUser(){

        email = editTextEmail.getText().toString();

        String password = editTextPassword.getText().toString();

        RequestParams params = new RequestParams();

        if(Utility.isNotNull(email) && Utility.isNotNull(password)){

            if(Utility.validate(email)){

                params.put("email", email);

                params.put("password", password);

                invokeWS(params);
            }

            else{
                Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_LONG).show();
            }
        } else{
            Toast.makeText(getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_LONG).show();
        }

    }
    public void invokeWS(RequestParams params){

        prgDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://healthygo.in/restservice/v1/login",params ,new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(String response) {

                prgDialog.hide();
                try {

                    JSONObject obj = new JSONObject(response);

                    if(!obj.getBoolean("error")){
                        Toast.makeText(getApplicationContext(), "You are successfully logged in!", Toast.LENGTH_LONG).show();
                        Intent homeIntent = new Intent(getApplicationContext(),NavigationBar.class);
                        SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                        //Creating editor to store values to shared preferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        //Adding values to editor
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                        editor.putString(Config.EMAIL_SHARED_PREF, email);

                        //Saving values to editor
                        editor.commit();
                       navigatetoHomeActivity();
                    }

                    else{
                       // errorMsg.setText(obj.getString("error_msg"));
                        Toast.makeText(getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {

                prgDialog.hide();

                if(statusCode == 404){
                    Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                }

                else if(statusCode == 500){
                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                }

                else{
                    Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void navigatetoHomeActivity(){
        Intent homeIntent = new Intent(getApplicationContext(),NavigationBar.class);
        homeIntent.putExtra("emailofuser",email);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
    public void navigatetoRegisterActivity(View view){
        Intent loginIntent = new Intent(getApplicationContext(),SignUpActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
    }




    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnsignin){
            resultlogin = 1;
            loginUser();
        }
        if(v.getId()==R.id.sign_in_button){
            resultlogin = 2;
            signIn();
        }
        if(v.getId()==R.id.login_buttonfb){
            resultlogin = 3;


        }

//        switch (v.getId()) {
//            case  R.id.btnsignin:
////                Intent i = new Intent(LoginActivity.this,NavigationBar.class);
////                startActivity(i);
//                  loginUser();
//                break;
//            case R.id.sign_in_button:
//                signIn();
//                break;
//            case R.id.ivback:
//                Intent returnn = new Intent(LoginActivity.this,HomeActivity.class);
//                startActivity(returnn);
//
//        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
