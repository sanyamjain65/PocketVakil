package com.app.pocketvakil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.pocketvakil.activities.LoginActivity;
import com.app.pocketvakil.activities.QueryFormActivity;
import com.app.pocketvakil.utils.AppSharedPreference;

/**
 * Created by appinventiv on 10/5/16.
 */
public class SplashActivity extends AppCompatActivity {

    private TextView splashText;
    private ImageView splashLogo;
    private Animation animTranslate;
    private String acess_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashText = (TextView) findViewById(R.id.tv_splash_text);
        splashLogo = (ImageView) findViewById(R.id.iv_splash_image);
        splashText.setVisibility(View.GONE);
        splashLogo.setVisibility(View.GONE);
        //setAnimation();
       // final Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fade);
        final AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
        animation1.setDuration(1000);
        animation1.setStartOffset(100);
        animation1.setFillAfter(true);
        splashLogo.setVisibility(View.VISIBLE);
        splashText.setVisibility(View.VISIBLE);
        splashLogo.startAnimation(animation1);
        splashText.startAnimation(animation1);
        loginActivity();
    }

    /*
    Method to go to next screen from splash screen
     */


    private void loginActivity(){





        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                acess_token=AppSharedPreference.getString(SplashActivity.this, AppSharedPreference.PREF_KEY.Access_token);


                if(acess_token==null||acess_token=="")
                {



                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                }
                else
                {
                    Intent intent = new Intent(SplashActivity.this, QueryFormActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                }



            }


        }, 2000);

    }



}