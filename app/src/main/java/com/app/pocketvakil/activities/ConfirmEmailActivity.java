package com.app.pocketvakil.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.pocketvakil.R;
import com.app.pocketvakil.asynctask.ForgotPasswordAsyncTask;
import com.app.pocketvakil.utils.AppSharedPreference;

import java.util.HashMap;

/**
 * Created by appinventiv on 10/5/16.
 */
public class ConfirmEmailActivity extends AppCompatActivity {

    private ImageView nextArrow;
    private TextView mail,resendMail;
    private String Intentmail;
    private HashMap postDataParams;
    private int countHit;
    private RelativeLayout rel_confrm;
    private String blank="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_email);
        initView();
        Intent intent = getIntent();
        if (null != intent) {
            Intentmail = intent.getStringExtra("KEY_MAIL");

        }
        mail.setText(Intentmail);
    }

    private void initView(){

        nextArrow = (ImageView) findViewById(R.id.iv_next_arrow);
        mail=(TextView)findViewById(R.id.tv_mail);
        resendMail=(TextView)findViewById(R.id.tv_resend_email);
        rel_confrm=(RelativeLayout)findViewById(R.id.rel_confrm);
        resendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDataParams = new HashMap<String, String>();
                postDataParams.put("user_email", mail.getText().toString());
                countHit= AppSharedPreference.getInt(ConfirmEmailActivity.this, AppSharedPreference.PREF_KEY.HIT_COUNT);
                countHit++;
                AppSharedPreference.putInt(ConfirmEmailActivity.this, AppSharedPreference.PREF_KEY.HIT_COUNT, countHit);
                ForgotPasswordAsyncTask task = new ForgotPasswordAsyncTask(ConfirmEmailActivity.this, postDataParams,blank);
                task.execute();

            }
        });
        nextArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmEmailActivity.this, OtpActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onSuccessfulResendMailSend(String responseString) {


       Toast.makeText(ConfirmEmailActivity.this, responseString, Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(ConfirmEmailActivity.this,OtpActivity.class);
        startActivity(intent);
    }

    public void onConfermMailFailed(String responseString) {

       Toast.makeText(ConfirmEmailActivity.this,responseString,Toast.LENGTH_SHORT).show();
    }
}
