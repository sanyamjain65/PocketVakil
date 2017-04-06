package com.app.pocketvakil.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.pocketvakil.R;
import com.app.pocketvakil.asynctask.ForgotPasswordAsyncTask;
import com.app.pocketvakil.utils.AppSharedPreference;
import com.app.pocketvakil.utils.AppUtils;

import java.util.HashMap;

/**
 * Created by appinventiv on 10/5/16.
 */
public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText etEmail;
    private Button btLink;
    private HashMap postDataParams;
    private int  counthit;
    private String blank="";
    private RelativeLayout rel_forgot;
    private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        initView();
    }

    private void initView(){

        etEmail = (EditText)findViewById(R.id.et_email);
        etEmail.addTextChangedListener(emailWatcher);
        rel_forgot=(RelativeLayout)findViewById(R.id.rel_forgot);
        btLink = (Button)findViewById(R.id.bt_send_link);
        btLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSendLinkWebService(v);
            }
        });

        etEmail.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    String mail=etEmail.getText().toString();
                    if(!mail.equals(""))
                    {

                        InputMethodManager inputManager = (InputMethodManager) ForgotPasswordActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(ForgotPasswordActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        postDataParams = new HashMap<String, String>();
                        postDataParams.put("user_email", etEmail.getText().toString());
                        counthit=0;
                        AppSharedPreference.putInt(ForgotPasswordActivity.this, AppSharedPreference.PREF_KEY.HIT_COUNT, counthit);

                        ForgotPasswordAsyncTask task = new ForgotPasswordAsyncTask(ForgotPasswordActivity.this, postDataParams,blank);
                        task.execute();
                    }
                    else
                    {

                        Snackbar snack = Snackbar.make(rel_forgot, "Enter Email", Snackbar.LENGTH_LONG);
                        View view = snack.getView();
                        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                        tv.setTextColor(getResources().getColor(R.color.white));
                        snack.show();



                        //Toast.makeText(ForgotPasswordActivity.this,"Enter Email",Toast.LENGTH_SHORT).show();
                    }

                }
                return false;
            }
        });
    }

    private void callSendLinkWebService(View view){

        String email = etEmail.getText().toString();
        if(email == null && email.equalsIgnoreCase("")){
            AppUtils.showSnackBar(view,"Please enter email address");
            return;
        }
        if (!email.matches(EMAIL_PATTERN)){
            AppUtils.showSnackBar(view,"Please enter valid email address");
            return;
        }

        InputMethodManager inputManager = (InputMethodManager) ForgotPasswordActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(ForgotPasswordActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        postDataParams = new HashMap<String, String>();
        postDataParams.put("user_email", etEmail.getText().toString());
        counthit=0;
        AppSharedPreference.putInt(ForgotPasswordActivity.this, AppSharedPreference.PREF_KEY.HIT_COUNT, counthit);

        ForgotPasswordAsyncTask task = new ForgotPasswordAsyncTask(ForgotPasswordActivity.this, postDataParams,blank);
        task.execute();

       /* Intent intent = new Intent(ForgotPasswordActivity.this,OtpVerificationActivity.class);
        startActivity(intent);*/
    }
    private final TextWatcher emailWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if (s.length() >= 8) {
                if(etEmail.getText().toString().matches(EMAIL_PATTERN)) {
                    int leftPadding = btLink.getPaddingLeft();
                    int rightPadding = btLink.getPaddingRight();
                    int topPadding = btLink.getPaddingTop();
                    int bottomPadding = btLink.getPaddingBottom();
                    btLink.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_blue_button));
                    btLink.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
                }else {
                    int leftPadding = btLink.getPaddingLeft();
                    int rightPadding = btLink.getPaddingRight();
                    int topPadding = btLink.getPaddingTop();
                    int bottomPadding = btLink.getPaddingBottom();
                    btLink.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_grey_button));
                    btLink.setPadding(leftPadding,topPadding,rightPadding,bottomPadding);
                }
                //textView.setVisibility(View.GONE);
            } else{
                int leftPadding = btLink.getPaddingLeft();
                int rightPadding = btLink.getPaddingRight();
                int topPadding = btLink.getPaddingTop();
                int bottomPadding = btLink.getPaddingBottom();
                btLink.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_grey_button));
                btLink.setPadding(leftPadding,topPadding,rightPadding,bottomPadding);

                //textView.setText("You have entered : " + passwordEditText.getText());
            }

        }
    };

    public void onSuccessfulMailSend(String responseString) {



        Toast.makeText(ForgotPasswordActivity.this, responseString, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(ForgotPasswordActivity.this, ConfirmEmailActivity.class);
        intent.putExtra("KEY_MAIL", etEmail.getText().toString());
        startActivity(intent);

    }

    public void onmailsendFailed(String responseString) {

        Toast.makeText(ForgotPasswordActivity.this, responseString, Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        AppUtils.hideKeyBoard(ForgotPasswordActivity.this);
        return true;
    }
}
