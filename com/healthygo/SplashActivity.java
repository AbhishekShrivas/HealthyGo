package healthygo2.vijayanitech.com.healthygo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by pc on 8/6/2016.
 */
public class SplashActivity extends AppCompatActivity {
ImageView img;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        img=(ImageView)findViewById(R.id.imageView);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        new PrefetchData().execute();

        }
    private class PrefetchData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            try { 		 // Thread will sleep for 2 seconds
                Thread.sleep(4 * 1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

//            if(!GeneralFunction.isEmptyCheck(GlobalVar.getMyStringPref(mContext, CommonUtilities.loginUserId))){
//                if(GeneralFunction.isCompare(GlobalVar.getMyStringPref(mContext,CommonUtilities.IslocationSelect),"true")){
                   Intent i=new Intent(SplashActivity.this,HomeActivity.class);
                 startActivity(i);
//                    finish();
//                }
//                else{
                   Intent ii=new Intent(SplashActivity.this,HomeActivity.class);
                   startActivity(ii);
//                    finish();
//                }
//
//            }
//            else{
//                Intent i=new Intent(SplashActivity.this,HomeActivity.class);
//                startActivity(i);
//                finish();
//            }

            /*if (GlobalVar.getMyBooleanPref(mContext, CommonUtilities.IsUSER_Login)) {
                String struserid=GlobalVar.getMyStringPref(mContext,CommonUtilities.AppUserId);
                if(struserid.equalsIgnoreCase("0")){
                    Intent i=new Intent(SplashActiivty.this,LoginActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Intent i=new Intent(SplashActiivty.this,MapScreenDefault.class);
                    i.putExtra("CurrentActivity", "Splash");
                    startActivity(i);
                    finish();
                }
            }
            else{
                Intent i=new Intent(SplashActiivty.this,LoginActivity.class);
                startActivity(i);
                finish();
            }*/

        }
    }




}



