package healthygo2.vijayanitech.com.healthygo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class FbloginFragment extends Fragment {

    private CallbackManager callbackManager;
    private TextView textView;

    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            if(profile != null) {
                //textView.setText(profile.getName());
                String userprofilename = profile.getName();
                Uri uri = profile.getProfilePictureUri(20, 20);
                String dpurl = uri.toString();
                Intent i = new Intent(getContext(), NavigationBar.class);
                i.putExtra("usernamefb",userprofilename);
                i.putExtra("userdpfb",dpurl);
                startActivity(i);
                //Toast.makeText(getActivity(),userprofilename+dpurl,Toast.LENGTH_LONG).show();
                displayMessage(profile);
            }
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };

    public FbloginFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fblogin, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_buttonfb);
        //textView = (TextView) view.findViewById(R.id.textView);
        List permissions = new ArrayList();
        permissions.add("email");
        permissions.add("public_profile");
        loginButton.setReadPermissions(permissions);
//        loginButton.setReadPermissions("user_friends");
//        loginButton.setReadPermissions("");
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, callback);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);


    }

    private void displayMessage(Profile profile){
        if(profile != null){
            //textView.setText(profile.getName());
            String userprofilename = profile.getName();
            Uri uri = profile.getProfilePictureUri(20,20);
            String dpurl = uri.toString();

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
            editor.putString(Config.googlepic, dpurl);
            editor.putString(Config.googledisplayname, userprofilename);
           // editor.putString(Config.googlepic, dpurl);
            editor.commit();

            Intent i = new Intent(getContext(), NavigationBar.class);
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
    }

    @Override
    public void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        displayMessage(profile);
    }
}